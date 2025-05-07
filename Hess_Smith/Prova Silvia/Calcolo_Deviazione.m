clear
close all
clc

tic

U_inf_abs = 1;
chord = 1;

% Range di angoli d'attacco
alpha_range = -15:1:15;
alpha_ind_array = zeros(size(alpha_range));
alpha_tot_array = zeros(size(alpha_range));

% Leggi il file .dat
filename = 'S1223.dat';
data = load(filename);

% Estrai le coordinate
x = data(:, 1);
y = data(:, 2);

% Loop su tutti gli angoli d'attacco
for idx = 1:length(alpha_range)
    alpha_w = alpha_range(idx)*pi/180; % Converti in radianti
    U_inf_w = [U_inf_abs * cos(alpha_w), U_inf_abs * sin(alpha_w)];

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

    % Calcolo dell'angolo di incidenza indotto al bordo d'uscita
    dx = x_ml_w(end) - x_ml_w(end-1);
    dy = y_ml_w(end) - y_ml_w(end-1);

    if abs(dx) < 1e-8
        alpha_ind_w = 0;
    else
        dy_dx_exit = dy / dx;
        alpha_ind_w = atan(dy_dx_exit);
    end

    % Calcolo della deviazione totale
    alpha_tot = alpha_w + alpha_ind_w;  % Somma algebrica diretta degli angoli

    % Salva i risultati negli array
    alpha_ind_array(idx) = alpha_ind_w;
    alpha_tot_array(idx) = alpha_tot;
end

% Plot dei risultati
figure(1);
plot(alpha_range, rad2deg(alpha_ind_array), 'b-', 'LineWidth', 2);
hold on;
plot(alpha_range, rad2deg(alpha_tot_array), 'r-', 'LineWidth', 2);
plot(alpha_range, alpha_range, 'k--', 'LineWidth', 1);
grid on;
xlabel('Angolo di attacco \alpha [deg]');
ylabel('Angolo [deg]');
legend('Angolo indotto', 'Deviazione totale', 'Angolo di attacco');
title('Analisi della deviazione del flusso');

% Plot dei risultati degli angoli
figure(2);
plot(alpha_range, rad2deg(alpha_ind_array), 'b-', 'LineWidth', 2);
hold on;
plot(alpha_range, rad2deg(alpha_tot_array), 'r-', 'LineWidth', 2);
plot(alpha_range, alpha_range, 'k--', 'LineWidth', 1);
grid on;
xlabel('Angolo di attacco \alpha [deg]');
ylabel('Angolo [deg]');
legend('Angolo indotto', 'Deviazione totale', 'Angolo di attacco');
title('Analisi della deviazione del flusso');

% Stampa risultati in una tabella
fprintf('\nRisultati:\n');
fprintf('AoA [deg] | Ang. Indotto [deg] | Dev. Totale [deg]\n');
fprintf('----------+-------------------+------------------\n');
for i = 1:length(alpha_range)
    fprintf('%8.1f  |%15.2f    |%15.2f\n', ...
        alpha_range(i), ...
        rad2deg(alpha_ind_array(i)), ...
        rad2deg(alpha_tot_array(i)));
end

toc

