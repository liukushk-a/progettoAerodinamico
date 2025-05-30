% inserire file txt con punti su 2 colonne altrimenti il codice non
% funziona, è possibile modificare fattore di scala e distanze tra airfoils
% (è consigliato controllare che i tuoi airfoils non si intersichino prima
% di runnare su2)
% DRS = 1 se attivato
close all
clear
clc
%%

sc = 0.6;
airfoils = ["airfoils/be122_125.txt" "airfoils/be122_155.txt"...
    "airfoils/s1223.txt" "airfoils/be092_075.txt" "airfoils/be122_075.txt"];
name_airfoil = ["be122125" "be122155"...
    "s1223" "be092075" "be122075"];
l = 1;
j = 5;

DRS = 0;

alpha1 = deg2rad(-4);
alpha2 = deg2rad(-40);

[xv1, yv1, xd1, yd1] = points(airfoils(l));
[xv2, yv2, xd2, yd2] = points(airfoils(j));

x1 = [xd1*cos(alpha1)+(-yd1)*sin(alpha1) xv1(1:end)*cos(alpha1)+(-yv1(1:end))*sin(alpha1)];
y1 = [(-xd1*sin(alpha1) + (-yd1) *cos(alpha1)) (-xv1(1:end)*sin(alpha1) + (-yv1(1:end))*cos(alpha1))];

x2 = [sc*xd2*cos(alpha2)+(-sc*yd2)*sin(alpha2) sc*xv2(1:end)*cos(alpha2)+(-sc*yv2(1:end))*sin(alpha2)];
y2 = [(-sc*xd2*sin(alpha2)+(-sc*yd2)*cos(alpha2)) (-sc*xv2(1:end)*sin(alpha2)+(-sc*yv2(1:end))*cos(alpha2))];


%%
distx = x1(1)  + 5/100;
ydisty = y1(1) + 4/100;

if DRS == 1
    [distx_prov, tortellino] = max(x2 + distx);
    distx = distx_prov - max(xd2)*sc;
    ydisty = y2(tortellino) + ydisty - yd2(1);
    alpha2 = deg2rad(0);

    x2 = [sc*xd2*cos(alpha2)+(-sc*yd2)*sin(alpha2) sc*xv2(1:end)*cos(alpha2)+(-sc*yv2(1:end))*sin(alpha2)];
    y2 = [(-sc*xd2*sin(alpha2)+(-sc*yd2)*cos(alpha2)) (-sc*xv2(1:end)*sin(alpha2)+(-sc*yv2(1:end))*cos(alpha2))];
end

%%
massimo1 = length(x1);
med1 = length(xd1);
massimo2 = length(x2);
med2 = length(xd2);
dx = diff(x1);
dy = diff(y1);
ds = sqrt(dx.^2 + dy.^2);  % Distanza tra punti consecutivi
curvature1 = abs(diff(atan2(dy, dx)) ./ ds(1:end-1));
[~, m1] = max(curvature1);

if m1 > med1-10
    [~, m1] = max(curvature1(1:89));
end
if mod(massimo1, 2) == 1
    q_w1 = [linspace(0.1, 1, m1)';linspace(1, 0.05, med1 - m1)';linspace(0.05, 1, m1)';linspace(1, 0.1, med1 -m1+1)'];
else
    q_w1 = [linspace(0.1, 1, m1)';linspace(1, 0.05, med1 - m1)';linspace(0.05, 1, m1)';linspace(1, 0.1, med1 -m1)'];
end
dx = diff(x2);
dy = diff(y2);
ds = sqrt(dx.^2 + dy.^2);  % Distanza tra punti consecutivi
curvature2 = abs(diff(atan2(dy, dx)) ./ ds(1:end-1));
[~, m2] = max(curvature2);

if m2 > med2-10
    [~, m2] = max(curvature2(1:89));
end
if mod(massimo2, 2) == 1
    q_w2 = [linspace(0.1, 1, m2)';linspace(1, 0.05, med2 - m2)';linspace(0.05, 1, m2)';linspace(1, 0.1, med2 -m2+1)'];
else
    q_w2 = [linspace(0.1, 1, m2)';linspace(1, 0.05, med2 - m2)';linspace(0.05, 1, m2)';linspace(1, 0.1, med2 -m2)'];
end
%%
% Nome del file
str_name = name_airfoil(l) + "_" + name_airfoil(j);

if DRS == 0
    filename1 = "piccola" + ".geo";
    filename2 = "grande" + ".geo";
    nome = str_name + "_OFF";
else

    filename1 = "piccola" + ".geo";
    filename2 = "grande" + ".geo";
    nome = str_name + "_ON";
end

percorso_principale = 'D:\Scuola\Uni Magi Primo Semestre Quinto Anno\Computational Fluid Dynamics\dockerData\Prova_unsteady\DRS_uRans\CFD';
nuovo_percorso = fullfile(percorso_principale, nome);

if ~exist(nuovo_percorso, 'dir')
    mkdir(nuovo_percorso);  % Crea la cartella se non esiste
    disp(['Cartella creata: ', nuovo_percorso]);
else
    disp(['La cartella esiste già: ', nuovo_percorso]);
end

percorso_completo2 = fullfile(nuovo_percorso, filename2);

% Apri il file in modalità scrittura
fid = fopen(percorso_completo2, 'w');

% Verifica che il file sia stato aperto correttamente
if fid == -1
    error('Errore nell''apertura del file');
end

fprintf(fid, '// ===========================================\n// ==================================MESH FILE\n// ===========================================\nh = 0.025;\nH = 1.5;\nR = 50;\nr = 0.63;\nH2 = 0.01;\n');

fprintf(fid, 'xcentro = %f;\nycentro = %f;\n', x2(1) + distx, y2(1) + ydisty);

% Scrivi i dati nel file con il formato richiesto
for i = 1:length(x1)
    fprintf(fid, 'Point(%d) = {%f, %f, %f, %f*h};\n', i, x1(i), y1(i), 0, q_w1(i)); % calcolare i valori di h
end


fprintf(fid, '\n// farfield\n');

fprintf(fid, 'Point(%d) = {15, 0, 0, h};\n', 1 + length(x1) + length(x2));
fprintf(fid, 'Point(%d) = {R+15, 0, 0, H};\n', 2 + length(x1) + length(x2));
fprintf(fid, 'Point(%d) = {15, R, 0, H};\n', 3 + length(x1) + length(x2));
fprintf(fid, 'Point(%d) = {-R+15, 0, 0, H};\n', 4 + length(x1) + length(x2));
fprintf(fid, 'Point(%d) = {15, -R, 0, H};\n', 5 + length(x1) + length(x2));

fprintf(fid, '\n// interface\n');

fprintf(fid, 'Point(%d) = {r+xcentro, ycentro, 0, H2};\n', 6 + length(x1) + length(x2));
fprintf(fid, 'Point(%d) = {xcentro, r+ycentro, 0, H2};\n', 7 + length(x1) + length(x2));
fprintf(fid, 'Point(%d) = {-r+xcentro, ycentro, 0, H2};\n', 8 + length(x1) + length(x2));
fprintf(fid, 'Point(%d) = {xcentro, -r+ycentro, 0, H2};\n', 9 + length(x1) + length(x2));

fprintf(fid, 'Point(%d) = {xcentro, ycentro, 0, h};\n', 10 + length(x1) + length(x2));

fprintf(fid, '\n//=====================================DEFINE LINES\n');
fprintf(fid, '\n//Airfoil 1\n');
fprintf(fid, 'Spline(%d) = {%d:%d};\n', 1, 1, m1);
fprintf(fid, 'Spline(%d) = {%d:%d};\n', 2, m1, med1);
fprintf(fid, 'Spline(%d) = {%d:%d};\n', 3, med1, 2*med1-m1);
fprintf(fid, 'Spline(%d) = {%d:%d, %d};\n', 4, 2*med1-m1, massimo1, 1);

fprintf(fid, '\n// farfield\n');
fprintf(fid, 'Circle(%d) = {%d,%d,%d};\n', 5, 2+length(x1) + length(x2), 1+length(x1) + length(x2), 3+length(x1) + length(x2));
fprintf(fid, 'Circle(%d) = {%d,%d,%d};\n', 6, 3+length(x1) + length(x2), 1+length(x1) + length(x2), 4+length(x1) + length(x2));
fprintf(fid, 'Circle(%d) = {%d,%d,%d};\n', 7, 4+length(x1) + length(x2), 1+length(x1) + length(x2), 5+length(x1) + length(x2));
fprintf(fid, 'Circle(%d) = {%d,%d,%d};\n', 8, 5+length(x1) + length(x2), 1+length(x1) + length(x2), 2+length(x1) + length(x2));

fprintf(fid, '\n//Interface\n');
fprintf(fid, 'Circle(%d) = {%d,%d,%d};\n', 9, 6+length(x1) + length(x2), 10+length(x1) + length(x2), 7+length(x1) + length(x2));
fprintf(fid, 'Circle(%d) = {%d,%d,%d};\n', 10, 7+length(x1) + length(x2), 10+length(x1) + length(x2), 8+length(x1) + length(x2));
fprintf(fid, 'Circle(%d) = {%d,%d,%d};\n', 11, 8+length(x1) + length(x2), 10+length(x1) + length(x2), 9+length(x1) + length(x2));
fprintf(fid, 'Circle(%d) = {%d,%d,%d};\n', 12, 9+length(x1) + length(x2), 10+length(x1) + length(x2), 6+length(x1) + length(x2));


fprintf(fid, '\n// =====================================LOOPS\n');
fprintf(fid, 'Line Loop(%d) = {%d,%d,%d,%d};\n', 1, 1, 2, 3, 4);
fprintf(fid, 'Line Loop(%d) = {%d,%d,%d,%d};\n', 2, 5, 6, 7, 8);
fprintf(fid, 'Line Loop(%d) = {%d,%d,%d,%d};\n', 3, 9, 10, 11, 12);

fprintf(fid, '\n// =====================================PLANE SURFACE\n');
fprintf(fid, 'Plane Surface(%d) = {%d,%d,%d};\n', 1, 2, 1, 3);

fprintf(fid, '\n// =====================================SURFS\n');
fprintf(fid, 'Physical Surface("Volume") = {%d};\n', 1);
fprintf(fid, 'Physical Line("FARFIELD") = {%d,%d,%d,%d};\n', 5, 6, 7, 8);
fprintf(fid, 'Physical Line("AIRFOILRIGID") = {%d,%d,%d,%d};\n', 1,2,3,4);
fprintf(fid, 'Physical Line("INTERFACEG") = {%d,%d,%d,%d};\n', 9, 10, 11, 12);

fprintf(fid, '\nTransfinite Curve{9,10,11,12} = 101 Using Progression 1;');
% fprintf(fid, '\nTransfinite Curve{1,3} = 151 Using Progression 1;');
% fprintf(fid, '\nTransfinite Curve{2,4} = 151 Using Progression 1;\n');

fprintf(fid, '\n// 1: MeshAdapt, 2: Automatic, 3: Initial mesh only, 5: Delaunay, 6: Frontal-Delaunay, 7: BAMG, 8: Frontal-Delaunay for Quads, 9: Packing of Parallelograms\n');
fprintf(fid, 'Mesh.RandomFactor = 1e-9;\n');
fprintf(fid, 'Mesh.Algorithm = 2;\n');
fprintf(fid, '\nMesh.RandomFactor = 1e-09;');

% Chiudi il file
fclose(fid);

disp(['File salvato come ', filename2]);





percorso_completo1 = fullfile(nuovo_percorso, filename1);

% Apri il file in modalità scrittura
fid = fopen(percorso_completo1, 'w');

% Verifica che il file sia stato aperto correttamente
if fid == -1
    error('Errore nell''apertura del file');
end

fprintf(fid, '// ===========================================\n// ==================================MESH FILE\n// ===========================================\nh = 0.025;\nH = 0.06;\nr = 0.63;\n');

fprintf(fid, 'xcentro = %f;\nycentro = %f;\n', x2(1) + distx, y2(1)+ydisty);

% Scrivi i dati nel file con il formato richiesto
for i = 1:length(x2)
    fprintf(fid, 'Point(%d) = {%f, %f, %f, %f*h};\n', i + length(x1), x2(i) + distx, y2(i) + ydisty, 0, q_w2(i));
end

fprintf(fid, '\n// interface\n');

fprintf(fid, 'Point(%d) = {r+xcentro, ycentro, 0, H};\n', 11 + length(x1) + length(x2));
fprintf(fid, 'Point(%d) = {xcentro, r+ycentro, 0, H};\n', 12 + length(x1) + length(x2));
fprintf(fid, 'Point(%d) = {-r+xcentro, ycentro, 0, H};\n', 13 + length(x1) + length(x2));
fprintf(fid, 'Point(%d) = {xcentro, -r+ycentro, 0, H};\n', 14 + length(x1) + length(x2));

fprintf(fid, 'Point(%d) = {xcentro, ycentro, 0, h};\n', 15 + length(x1) + length(x2));

fprintf(fid, '\n//Interface\n');
fprintf(fid, 'Circle(%d) = {%d,%d,%d};\n', 13, 11+length(x1) + length(x2), 15+length(x1) + length(x2), 12+length(x1) + length(x2));
fprintf(fid, 'Circle(%d) = {%d,%d,%d};\n', 14, 12+length(x1) + length(x2), 15+length(x1) + length(x2), 13+length(x1) + length(x2));
fprintf(fid, 'Circle(%d) = {%d,%d,%d};\n', 15, 13+length(x1) + length(x2), 15+length(x1) + length(x2), 14+length(x1) + length(x2));
fprintf(fid, 'Circle(%d) = {%d,%d,%d};\n', 16, 14+length(x1) + length(x2), 15+length(x1) + length(x2), 11+length(x1) + length(x2));


fprintf(fid, '\n//Airfoil 2\n');
fprintf(fid, '\n\nSpline(%d) = {%d:%d};\n', 17, 1+length(x1), m2+length(x1));
fprintf(fid, 'Spline(%d) = {%d:%d};\n', 18, m2+length(x1), med2+length(x1));
fprintf(fid, 'Spline(%d) = {%d:%d};\n', 19, med2+length(x1), 2*med2-m2+length(x1));
fprintf(fid, 'Spline(%d) = {%d:%d,%d};\n', 20, 2*med2-m2+length(x1), massimo2+length(x1), 1+length(x1));

fprintf(fid, '\n// =====================================LOOPS\n');
fprintf(fid, 'Line Loop(%d) = {%d,%d,%d,%d};\n', 4, 13, 14, 15, 16);
fprintf(fid, 'Line Loop(%d) = {%d,%d,%d,%d};\n', 5, 17, 18, 19, 20);

fprintf(fid, '\n// =====================================PLANE SURFACE\n');
fprintf(fid, 'Plane Surface(%d) = {%d,%d};\n', 2, 4, 5);

fprintf(fid, '\n// =====================================SURFS\n');
fprintf(fid, 'Physical Surface("Volumetto") = {%d};\n', 2);
fprintf(fid, 'Physical Line("INTERFACEP") = {%d:%d};\n', 13, 16);
fprintf(fid, 'Physical Line("AIRFOILMOVING") = {%d:%d};\n', 17, 20);

fprintf(fid, '\nTransfinite Curve{13:16} = 101 Using Progression 1;');
% fprintf(fid, '\nTransfinite Curve{18,20} = 41 Using Progression 1;');
% fprintf(fid, '\nTransfinite Curve{17,19} = 101 Using Progression 1;\n');

fprintf(fid, '\n// 1: MeshAdapt, 2: Automatic, 3: Initial mesh only, 5: Delaunay, 6: Frontal-Delaunay, 7: BAMG, 8: Frontal-Delaunay for Quads, 9: Packing of Parallelograms\n');
fprintf(fid, 'Mesh.RandomFactor = 1e-9;\n');
fprintf(fid, 'Mesh.Algorithm = 2;\n');
fprintf(fid, '\nMesh.RandomFactor = 1e-09;');

% Chiudi il file
fclose(fid);

disp(['File salvato come ', filename1]);






% fid = fopen('config_super.cfg', 'r');
% if fid == -1
%     error('Impossibile aprire il config file!');
% end
% 
% % % Inizializza una cella per memorizzare tutte le righe
% lines = {};
% 
% while ~feof(fid)
%     lines{end+1} = fgetl(fid);  % Leggi una riga alla volta e salvala nella cella
% end
% 
% % Chiudi il file dopo la lettura
% fclose(fid);
% 
% 
% file_config_name = "config_super.cfg";
% percorso_completo3 = fullfile(nuovo_percorso, file_config_name);
% 
% 
% % Apri di nuovo il file per la scrittura
% fid = fopen(percorso_completo3, 'w');
% if fid == -1
%     error('Impossibile aprire il file per la scrittura!');
% end
% 
% % Scrivi tutte le righe nel file, comprese le modifiche
% for i = 1:length(lines)
%     fprintf(fid, '%s\n', lines{i});
% end
% 
% % Chiudi il file dopo la scrittura
% fclose(fid);
% 
% 
% 
% 
% 
% 
% fid = fopen('ins1.su2', 'r');
% if fid == -1
%     error('Impossibile aprire il config file!');
% end
% 
% % % Inizializza una cella per memorizzare tutte le righe
% lines = {};
% 
% while ~feof(fid)
%     lines{end+1} = fgetl(fid);  % Leggi una riga alla volta e salvala nella cella
% end
% 
% % Chiudi il file dopo la lettura
% fclose(fid);
% 
% 
% file_config_name = "ins1.su2";
% percorso_completo4 = fullfile(nuovo_percorso, file_config_name);
% 
% 
% % Apri di nuovo il file per la scrittura
% fid = fopen(percorso_completo4, 'w');
% if fid == -1
%     error('Impossibile aprire il file per la scrittura!');
% end
% 
% % Scrivi tutte le righe nel file, comprese le modifiche
% for i = 1:length(lines)
%     fprintf(fid, '%s\n', lines{i});
% end
% 
% % Chiudi il file dopo la scrittura
% fclose(fid);
% 
% 
% 
% fid = fopen('ins2.su2', 'r');
% if fid == -1
%     error('Impossibile aprire il config file!');
% end
% 
% % % Inizializza una cella per memorizzare tutte le righe
% lines = {};
% 
% while ~feof(fid)
%     lines{end+1} = fgetl(fid);  % Leggi una riga alla volta e salvala nella cella
% end
% 
% % Chiudi il file dopo la lettura
% fclose(fid);
% 
% 
% file_config_name = "ins2.su2";
% percorso_completo4 = fullfile(nuovo_percorso, file_config_name);
% 
% 
% % Apri di nuovo il file per la scrittura
% fid = fopen(percorso_completo4, 'w');
% if fid == -1
%     error('Impossibile aprire il file per la scrittura!');
% end
% 
% % Scrivi tutte le righe nel file, comprese le modifiche
% for i = 1:length(lines)
%     fprintf(fid, '%s\n', lines{i});
% end
% 
% % Chiudi il file dopo la scrittura
% fclose(fid);
% 
% 
% 
% 
% 
% fid = fopen('zone_1.cfg', 'r');
% if fid == -1
%     error('Impossibile aprire il config file!');
% end
% 
% % % Inizializza una cella per memorizzare tutte le righe
% lines = {};
% 
% while ~feof(fid)
%     lines{end+1} = fgetl(fid);  % Leggi una riga alla volta e salvala nella cella
% end
% 
% % Chiudi il file dopo la lettura
% fclose(fid);
% 
% 
% file_config_name = "zone_1.cfg";
% percorso_completo5 = fullfile(nuovo_percorso, file_config_name);
% 
% 
% % Apri di nuovo il file per la scrittura
% fid = fopen(percorso_completo5, 'w');
% if fid == -1
%     error('Impossibile aprire il file per la scrittura!');
% end
% 
% % Scrivi tutte le righe nel file, comprese le modifiche
% for i = 1:length(lines)
%     fprintf(fid, '%s\n', lines{i});
% end
% 
% % Chiudi il file dopo la scrittura
% fclose(fid);
% 
% 
% 
% 
% 
% fid = fopen('zone_2.cfg', 'r');
% if fid == -1
%     error('Impossibile aprire il config file!');
% end
% 
% % % Inizializza una cella per memorizzare tutte le righe
% lines = {};
% 
% while ~feof(fid)
%     lines{end+1} = fgetl(fid);  % Leggi una riga alla volta e salvala nella cella
% end
% 
% % Chiudi il file dopo la lettura
% fclose(fid);
% 
% 
% file_config_name = "zone_2.cfg";
% percorso_completo5 = fullfile(nuovo_percorso, file_config_name);
% 
% 
% % Apri di nuovo il file per la scrittura
% fid = fopen(percorso_completo5, 'w');
% if fid == -1
%     error('Impossibile aprire il file per la scrittura!');
% end
% 
% % Scrivi tutte le righe nel file, comprese le modifiche
% for i = 1:length(lines)
%     fprintf(fid, '%s\n', lines{i});
% end
% 
% % Chiudi il file dopo la scrittura
% fclose(fid);
