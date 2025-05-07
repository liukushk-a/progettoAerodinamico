clear
close all
clc

tic

U_inf_abs = 1;
chord = 1;

% Definizione della velocità del flusso libero
alpha_w = 2.5 * pi / 180; % Angolo di attacco in radianti
U_inf_w = [U_inf_abs * cos(alpha_w), U_inf_abs * sin(alpha_w)]; % Vettore velocità del flusso libero

%% Profile generation 

% Leggi il file .dat
filename = 'S1223.dat'; % Sostituisci con il nome del tuo file
data = load(filename); % Carica i dati (assumi due colonne: x e y)

% Estrai le coordinate
x = data(:, 1); % Colonna x
y = data(:, 2); % Colonna y

% Dividi il profilo in superiore e inferiore
% I punti superiori vanno da bordo di uscita a bordo di attacco
% I punti inferiori vanno da bordo di attacco a bordo di uscita
[~, idx_min] = min(x); % Trova il punto di bordo di attacco (minimo x)
x_us = x(1:idx_min); % Parte superiore
y_us = y(1:idx_min);
x_ls = x(idx_min:end); % Parte inferiore
y_ls = y(idx_min:end);

% Combina le coordinate per il profilo completo
x_w = [x_us; x_ls];
y_w = [y_us; y_ls];

% Calcola i punti centrali dei pannelli
n_panels_tot = length(x_w) - 1;
xc_w = zeros(n_panels_tot, 1);
yc_w = zeros(n_panels_tot, 1);

for i = 1:n_panels_tot
    xc_w(i) = 0.5 * (x_w(i) + x_w(i+1));
    yc_w(i) = 0.5 * (y_w(i) + y_w(i+1));
end

% Calcola la linea media
%x_ml = (x_us + flipud(x_ls)) / 2; % Media dei punti x
%y_ml = (y_us + flipud(y_ls)) / 2; % Media dei punti y

% Visualizza il profilo
figure(1);
hold on;
box on;
axis on;
grid on;
plot(x_w, y_w, 'b', 'LineWidth', 1.5); % Profilo completo
%plot(x_ml, y_ml, 'k--', 'LineWidth', 1.5); % Linea media
plot(xc_w, yc_w, 'r*'); % Punti centrali dei pannelli
axis equal;
legend('Profile');
title('Airfoil Profile','Panels Extrema');
xlabel('x');
ylabel('y');

%% Normal and tangent vectors - Wing

% Numero di pannelli sulla superficie superiore e inferiore
n_panels_surf = length(x_us) - 1; % Numero di pannelli sulla superficie superiore

% Inizializza i vettori normali, tangenti e lunghezze dei pannelli
normals_us_w = zeros(n_panels_surf, 2);
tangents_us_w = zeros(n_panels_surf, 2);
normals_ls_w = zeros(n_panels_surf, 2);
tangents_ls_w = zeros(n_panels_surf, 2);
panel_length_us_w = zeros(n_panels_surf, 1);
panel_length_ls_w = zeros(n_panels_surf, 1);

% Calcola i vettori tangenti e normali per la superficie superiore
for i = 1:n_panels_surf
    panel_length_us_w(i) = sqrt((x_us(i+1) - x_us(i))^2 + (y_us(i+1) - y_us(i))^2);
    tangents_us_w(i, 1) = (x_us(i+1) - x_us(i)) / panel_length_us_w(i);
    tangents_us_w(i, 2) = (y_us(i+1) - y_us(i)) / panel_length_us_w(i);
    normals_us_w(i, 1) = -(y_us(i+1) - y_us(i)) / panel_length_us_w(i);
    normals_us_w(i, 2) = (x_us(i+1) - x_us(i)) / panel_length_us_w(i);
end

% Numero di pannelli sulla superficie inferiore
n_panels_surf_ls = length(x_ls) - 1; % Numero di pannelli sulla superficie inferiore

% Calcola i vettori tangenti e normali per la superficie inferiore
for i = 1:n_panels_surf_ls
    panel_length_ls_w(i) = sqrt((x_ls(i+1) - x_ls(i))^2 + (y_ls(i+1) - y_ls(i))^2);
    tangents_ls_w(i, 1) = (x_ls(i+1) - x_ls(i)) / panel_length_ls_w(i);
    tangents_ls_w(i, 2) = (y_ls(i+1) - y_ls(i)) / panel_length_ls_w(i);
    normals_ls_w(i, 1) = -(y_ls(i+1) - y_ls(i)) / panel_length_ls_w(i);
    normals_ls_w(i, 2) = (x_ls(i+1) - x_ls(i)) / panel_length_ls_w(i);
end

% Calcola gli angoli beta per la superficie superiore
beta_us_w = zeros(n_panels_surf, 1);
for i = 1:n_panels_surf
    beta_us_w(i) = atan2(tangents_us_w(i, 2), tangents_us_w(i, 1));
end

% Calcola gli angoli beta per la superficie inferiore
beta_ls_w = zeros(n_panels_surf_ls, 1);
for i = 1:n_panels_surf_ls
    beta_ls_w(i) = atan2(tangents_ls_w(i, 2), tangents_ls_w(i, 1));
end

%% System Building - Wing

% Combina i dati delle superfici superiore e inferiore
beta_w = [flipud(beta_ls_w); beta_us_w];
panel_length_w = [flipud(panel_length_ls_w); panel_length_us_w];
normals_w = [flipud(normals_ls_w); normals_us_w];
tangents_w = [flipud(tangents_ls_w); tangents_us_w];

% Inizializza la matrice A e il vettore b
A_w = zeros(n_panels_tot+1);
b_w = zeros(n_panels_tot+1, 1);

% Costruzione della matrice A
for i = 1:n_panels_tot
    for j = 1:n_panels_tot
        % Calcola le velocità indotte dalla sorgente e dal vortice
        [u_sou_w, v_sou_w] = source_gen(x_w(j), y_w(j), x_w(j+1), y_w(j+1), xc_w(i), yc_w(i));
        [u_vor_w, v_vor_w] = vortex_gen(x_w(j), y_w(j), x_w(j+1), y_w(j+1), xc_w(i), yc_w(i));
        
        % Aggiorna la matrice A
        A_w(i, j) = u_sou_w * normals_w(i, 1) + v_sou_w * normals_w(i, 2);
        A_w(i, n_panels_tot+1) = A_w(i, n_panels_tot+1) + u_vor_w * normals_w(i, 1) + v_vor_w * normals_w(i, 2);
    end
end

% Condizione di Kutta
for j = 1:n_panels_tot
    % Velocità indotte al primo pannello
    [u_sou_w, v_sou_w] = source_gen(x_w(j), y_w(j), x_w(j+1), y_w(j+1), xc_w(1), yc_w(1));
    [u_vor_w, v_vor_w] = vortex_gen(x_w(j), y_w(j), x_w(j+1), y_w(j+1), xc_w(1), yc_w(1));
    A_w(n_panels_tot+1, j) = u_sou_w * tangents_w(1, 1) + v_sou_w * tangents_w(1, 2);
    A_w(n_panels_tot+1, n_panels_tot+1) = A_w(n_panels_tot+1, n_panels_tot+1) + u_vor_w * tangents_w(1, 1) + v_vor_w * tangents_w(1, 2);
    
    % Velocità indotte all'ultimo pannello
    [u_sou_w, v_sou_w] = source_gen(x_w(j), y_w(j), x_w(j+1), y_w(j+1), xc_w(n_panels_tot), yc_w(n_panels_tot));
    [u_vor_w, v_vor_w] = vortex_gen(x_w(j), y_w(j), x_w(j+1), y_w(j+1), xc_w(n_panels_tot), yc_w(n_panels_tot));
    A_w(n_panels_tot+1, j) = A_w(n_panels_tot+1, j) + u_sou_w * tangents_w(n_panels_tot, 1) + v_sou_w * tangents_w(n_panels_tot, 2);
    A_w(n_panels_tot+1, n_panels_tot+1) = A_w(n_panels_tot+1, n_panels_tot+1) + u_vor_w * tangents_w(n_panels_tot, 1) + v_vor_w * tangents_w(n_panels_tot, 2);
end

% Costruzione del vettore b
for i = 1:n_panels_tot
    b_w(i) = -U_inf_w * (normals_w(i, :))';
end

% Condizione di Kutta nel vettore b
b_w(n_panels_tot+1) = -U_inf_w * (tangents_w(1, :) + tangents_w(n_panels_tot, :))';

% Risoluzione del sistema lineare
solution_w = A_w \ b_w;

% Estrai le soluzioni
q_w = solution_w(1:n_panels_tot); % Intensità delle sorgenti
gamma_w = solution_w(n_panels_tot+1); % Intensità del vortice