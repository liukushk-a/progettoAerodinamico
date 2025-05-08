clear
close all
clc

U_inf_abs = 1;
chord = 1;

% Singolo angolo di attacco (in gradi)
alpha_deg = -15;
alpha_w = alpha_deg*pi/180; % Converti in radianti
U_inf_w = [U_inf_abs * cos(alpha_w), U_inf_abs * sin(alpha_w)];

% Leggi il file .dat
filename = 'S1223.dat';
data = load(filename);

% Estrai le coordinate
x = data(:, 1);
y = data(:, 2);

% Dividi il profilo in superiore e inferiore
[~, idx_min] = min(x);
x_us = x(1:idx_min);
y_us = y(1:idx_min);
x_ls = x(idx_min:end);
y_ls = y(idx_min:end);

% Calcolo della linea media (camber line)
x_common = linspace(0, 1, 200);
y_us_interp = interp1(x_us, y_us, x_common, 'linear', 'extrap');
y_ls_interp = interp1(x_ls, y_ls, x_common, 'linear', 'extrap');

x_ml_w = x_common';
y_ml_w = (y_us_interp + y_ls_interp)' / 2;

% Calcolare il vettore normale ai pannelli
N = length(x_common); % numero di pannelli
n = zeros(N, 1); % inizializzare il vettore delle normali

for j = 1:N-1
    % Calcolare il vettore tangente
    dx = x_common(j+1) - x_common(j);
    dy = y_ml_w(j+1) - y_ml_w(j);
    
    % Calcolare il vettore normale
    n(j) = -dy / sqrt(dx^2 + dy^2); % componente normale in y
end

% Matrici di influenza
A = zeros(N, N); % Matrice per sorgente
B = zeros(N, N); % Matrice per vortice

% Calcolo delle matrici A e B
epsilon = 1e-10;  % Un piccolo valore per evitare log(0)
for j = 1:N-1  % Modificato da 1:N a 1:N-1
    for k = 1:N-1  % Modificato da 1:N a 1:N-1
        % Distanza tra i pannelli j e k
        dx_jk = x_ml_w(k) - x_ml_w(j);
        dy_jk = y_ml_w(k) - y_ml_w(j);
        r_jk = max(sqrt(dx_jk^2 + dy_jk^2), epsilon);  % Usa un valore minimo per evitare log(0)

        % Angoli dei pannelli j e k
        theta_j = atan2(y_ml_w(j+1) - y_ml_w(j), x_ml_w(j+1) - x_ml_w(j));
        theta_k = atan2(y_ml_w(k+1) - y_ml_w(k), x_ml_w(k+1) - x_ml_w(k));

        % Influenza sorgente (matrice A)
        A(j, k) = 1/(2*pi) * (log(r_jk) - cos(theta_j) * cos(theta_k));

        % Influenza vortice (matrice B)
        B(j, k) = 1/(2*pi) * atan2(dy_jk, dx_jk);
    end
end

%% Risoluzione del sistema lineare

rhs = -U_inf_abs * n;  % Velocità normale ai pannelli

% Creiamo il sistema combinato di influenze A e B
coeff = [A, B; B, A];  % Matrizzone combinato di dimensione 2N x 2N

% Creiamo un vettore rhs combinato di dimensione 2N
rhs_combined = [rhs; rhs];  % Estensione del vettore rhs per sorgente e vortice

% Controllo della condizione della matrice
cond_coeff = cond(coeff);
fprintf('Condizione della matrice dei coefficienti: %.2e\n', cond_coeff);

if cond_coeff > 1e10  % Un valore di condizione molto alto può essere problematico
    error('La matrice dei coefficienti è mal condizionata, potrebbe essere singolare.');
end

% Risolviamo il sistema per trovare le intensità delle sorgenti (sigma) e dei vortici (gamma)
solution = coeff \ rhs_combined;  % Risoluzione del sistema lineare

% Le prime N variabili sono le intensità delle sorgenti (sigma)
sigma = solution(1:N);  % Intensità delle sorgenti

% Le altre N variabili sono le intensità dei vortici (gamma)
gamma = solution(N+1:end);  % Intensità dei vortici

% Controllo dei risultati
if any(isnan(sigma)) || any(isnan(gamma))
    warning('Le intensità delle sorgenti o dei vortici contengono NaN. Verifica la matrice dei coefficienti.');
end

disp('Intensità delle sorgenti (sigma):');
disp(sigma);
disp('Intensità dei vortici (gamma):');
disp(gamma);


