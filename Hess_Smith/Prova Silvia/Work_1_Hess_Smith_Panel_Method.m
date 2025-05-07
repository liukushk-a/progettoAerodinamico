clear
close all
clc

tic

U_inf_abs = 1;
chord = 1;

%% Wing Airfoil Generation - NACA 3310

alpha_w = 2.5*pi/180;
U_inf_w = [U_inf_abs*cos(alpha_w), U_inf_abs*sin(alpha_w)];
M_w = 3;
P_w = 3;
SS_w = 10;
n_panels_surf = 50;
n_panels_tot = n_panels_surf*2;

[x_us_w, y_us_w, x_ls_w, y_ls_w, x_ml_w, y_ml_w, x_cl_w, y_cl_w] = NACA_4d_gen (M_w, P_w, SS_w, n_panels_surf);

xc_us_w = zeros(n_panels_surf,1);
yc_us_w = zeros(n_panels_surf,1);
xc_ls_w = zeros(n_panels_surf,1);
yc_ls_w = zeros(n_panels_surf,1);
xc_w = [flipud(xc_ls_w); xc_us_w];
yc_w = [flipud(yc_ls_w); yc_us_w];

x_w = [flipud(x_ls_w(2:end)); x_us_w];
y_w = [flipud(y_ls_w(2:end)); y_us_w];

for i = 1:n_panels_tot
    xc_w(i) = 0.5*(x_w(i) + x_w(i+1));
    yc_w(i) = 0.5*(y_w(i) + y_w(i+1));
end

figure(1);

hold on;
box on;
axis on;
grid on;
plot(x_w, y_w ,'b')
plot(x_w, y_w ,'r*')
plot(x_ml_w, y_ml_w, 'k')
plot(x_cl_w, y_cl_w, 'k--')
% plot(xc_w,yc_w,'ys')
axis equal
legend('Profile','Panels Extrema','Mean Line', 'Chord Line')

%% Tail Airfoil Generation - NACA 0012

alpha_t = 0*pi/180;
U_inf_t = [U_inf_abs*cos(alpha_t), U_inf_abs*sin(alpha_t)];

M_t = 0;
P_t = 0;
SS_t = 12;

[x_us_t, y_us_t, x_ls_t, y_ls_t, x_ml_t, y_ml_t, x_cl_t, y_cl_t] = NACA_4d_gen (M_t, P_t, SS_t, n_panels_surf);

xc_us_t = zeros(n_panels_surf,1);
yc_us_t = zeros(n_panels_surf,1);
xc_ls_t = zeros(n_panels_surf,1);
yc_ls_t = zeros(n_panels_surf,1);
xc_t = [flipud(xc_ls_t); xc_us_t];
yc_t = [flipud(yc_ls_t); yc_us_t];

x_t = [flipud(x_ls_t(2:end)); x_us_t];
y_t = [flipud(y_ls_t(2:end)); y_us_t];

for i = 1:n_panels_tot
    xc_t(i) = 0.5*(x_t(i) + x_t(i+1));
    yc_t(i) = 0.5*(y_t(i) + y_t(i+1));
end

figure(2);

hold on;
box on;
axis on;
grid on;
plot(x_t, y_t ,'b')
plot(x_t, y_t ,'r*')
plot(x_ml_t, y_ml_t, 'k')
plot(x_cl_t, y_cl_t, 'k--')
% plot(xc_t,yc_t,'ys')
axis equal
legend('Profile','Panels Extrema','Mean Line', 'Chord Line')

%% Normal and tangent vectors - Wing

normals_us_w = zeros(n_panels_surf,2);
tangents_us_w = zeros(n_panels_surf,2);
normals_ls_w = zeros(n_panels_surf,2);
tangents_ls_w = zeros(n_panels_surf,2);
panel_length_us_w = zeros(n_panels_surf,1);
panel_length_ls_w = zeros(n_panels_surf,1);
beta_us_w = zeros(n_panels_surf,1);
beta_ls_w = zeros(n_panels_surf,1);

for i = 1:n_panels_surf
    panel_length_us_w(i) = sqrt((x_us_w(i+1)-x_us_w(i))^2 + (y_us_w(i+1)-y_us_w(i))^2);
    tangents_us_w(i,1) = (x_us_w(i+1)-x_us_w(i))/panel_length_us_w(i);
    tangents_us_w(i,2) = (y_us_w(i+1)-y_us_w(i))/panel_length_us_w(i);
    normals_us_w(i,1) = -((y_us_w(i+1)-y_us_w(i))/panel_length_us_w(i));
    normals_us_w(i,2) = (x_us_w(i+1)-x_us_w(i))/panel_length_us_w(i);
    beta_us_w(i) = asin(tangents_us_w(i,2));
end

for i = 1:n_panels_surf
    panel_length_ls_w(i) = sqrt((x_ls_w(i+1)-x_ls_w(i))^2 + (y_ls_w(i+1)-y_ls_w(i))^2);
    tangents_ls_w(i,1) = (x_ls_w(i+1)-x_ls_w(i))/panel_length_ls_w(i);
    tangents_ls_w(i,2) = (y_ls_w(i+1)-y_ls_w(i))/panel_length_ls_w(i);
    normals_ls_w(i,1) = -1* tangents_ls_w(i,2);
    normals_ls_w(i,2) = tangents_ls_w(i,1);
    beta_ls_w(i) = asin(tangents_ls_w(i,2));
end

normals_ls_w = -1.*normals_ls_w;
tangents_ls_w = -1.*tangents_ls_w;

%% Normal and tangent vectors - Tail

normals_us_t = zeros(n_panels_surf,2);
tangents_us_t = zeros(n_panels_surf,2);
normals_ls_t = zeros(n_panels_surf,2);
tangents_ls_t = zeros(n_panels_surf,2);
panel_length_us_t = zeros(n_panels_surf,1);
panel_length_ls_t = zeros(n_panels_surf,1);
beta_us_t = zeros(n_panels_surf,1);
beta_ls_t = zeros(n_panels_surf,1);

for i = 1:n_panels_surf

    panel_length_us_t(i) = sqrt((x_us_t(i+1)-x_us_t(i))^2 + (y_us_t(i+1)-y_us_t(i))^2);
    tangents_us_t(i,1) = (x_us_t(i+1)-x_us_t(i))/panel_length_us_t(i);
    tangents_us_t(i,2) = (y_us_t(i+1)-y_us_t(i))/panel_length_us_t(i);
    normals_us_t(i,1) = -1* tangents_us_t(i,2);
    normals_us_t(i,2) = tangents_us_t(i,1);
    beta_us_t(i) = asin(tangents_us_t(i,2));

end

for i = 1:n_panels_surf

    panel_length_ls_t(i) = sqrt((x_ls_t(i+1)-x_ls_t(i))^2 + (y_ls_t(i+1)-y_ls_t(i))^2);
    tangents_ls_t(i,1) = (x_ls_t(i+1)-x_ls_t(i))/panel_length_ls_t(i);
    tangents_ls_t(i,2) = (y_ls_t(i+1)-y_ls_t(i))/panel_length_ls_t(i);
    normals_ls_t(i,1) = -1* tangents_ls_t(i,2);
    normals_ls_t(i,2) = tangents_ls_t(i,1);
    beta_ls_t(i) = asin(tangents_ls_t(i,2));

end

normals_ls_t = -1.*normals_ls_t;
tangents_ls_t = -1.*tangents_ls_t;

%% System Building  - Wing

beta_w = [flipud(beta_ls_w); beta_us_w];
panel_length_w = [flipud(panel_length_ls_w); panel_length_us_w];
normals_w = [flipud(normals_ls_w); normals_us_w];
tangents_w = [flipud(tangents_ls_w); tangents_us_w];
A_w = zeros(n_panels_tot+1);
b_w = zeros(n_panels_tot+1,1);

for i = 1 : n_panels_tot
    for j = 1 : n_panels_tot
        [u_sou_w,v_sou_w] = source_gen(x_w(j), y_w(j), x_w(j+1), y_w(j+1), xc_w(i), yc_w(i));
        [u_vor_w,v_vor_w] = vortex_gen(x_w(j), y_w(j), x_w(j+1), y_w(j+1), xc_w(i), yc_w(i));
        A_w(i,j) = u_sou_w*normals_w(i,1) + v_sou_w*normals_w(i,2);
        A_w(i,n_panels_tot+1) = A_w(i,n_panels_tot+1) + u_vor_w*normals_w(i,1) + v_vor_w*normals_w(i,2);
    end
end

for j = 1 : n_panels_tot
    [u_sou_w,v_sou_w] = source_gen(x_w(j), y_w(j), x_w(j+1), y_w(j+1), xc_w(1), yc_w(1));
    [u_vor_w,v_vor_w] = vortex_gen(x_w(j), y_w(j), x_w(j+1), y_w(j+1), xc_w(1), yc_w(1));
    A_w(n_panels_tot+1,j) = u_sou_w*tangents_w(1,1) + v_sou_w*tangents_w(1,2);
    A_w(n_panels_tot+1,n_panels_tot+1) = A_w(n_panels_tot+1,n_panels_tot+1) + u_vor_w*tangents_w(1,1) + v_vor_w*tangents_w(1,2);
    [u_sou_w,v_sou_w] = source_gen(x_w(j), y_w(j), x_w(j+1), y_w(j+1), xc_w(n_panels_tot), yc_w(n_panels_tot));
    [u_vor_w,v_vor_w] = vortex_gen(x_w(j), y_w(j), x_w(j+1), y_w(j+1), xc_w(n_panels_tot), yc_w(n_panels_tot));
    A_w(n_panels_tot+1,j) = A_w(n_panels_tot+1,j) + u_sou_w*tangents_w(n_panels_tot,1) + v_sou_w*tangents_w(n_panels_tot,2);
    A_w(n_panels_tot+1,n_panels_tot+1) = A_w(n_panels_tot+1,n_panels_tot+1) + u_vor_w*tangents_w(n_panels_tot,1) + v_vor_w*tangents_w(n_panels_tot,2);
end

for i = 1 : n_panels_tot
    b_w(i) = -U_inf_w*(normals_w(i,:))';
end

b_w(n_panels_tot+1) = -U_inf_w*(tangents_w(1,:) + tangents_w(n_panels_tot,:))';

solution_w = A_w \ b_w;
q_w = solution_w(1:n_panels_tot);
gamma_w = solution_w(n_panels_tot+1);

%% System Building  - Tail

beta_t = [flipud(beta_ls_t); beta_us_t];
panel_length_t = [flipud(panel_length_ls_t); panel_length_us_t];
normals_t = [flipud(normals_ls_t); normals_us_t];
tangents_t = [flipud(tangents_ls_t); tangents_us_t];
A_t = zeros(n_panels_tot+1);
b_t = zeros(n_panels_tot+1,1);

for i = 1 : n_panels_tot
    for j = 1 : n_panels_tot
        [u_sou_t,v_sou_t] = source_gen(x_t(j), y_t(j), x_t(j+1), y_t(j+1), xc_t(i), yc_t(i));
        [u_vor_t,v_vor_t] = vortex_gen(x_t(j), y_t(j), x_t(j+1), y_t(j+1), xc_t(i), yc_t(i));
        A_t(i,j) = u_sou_t*normals_t(i,1) + v_sou_t*normals_t(i,2);
        A_t(i,n_panels_tot+1) = A_t(i,n_panels_tot+1) + u_vor_t*normals_t(i,1) + v_vor_t*normals_t(i,2);
    end
end

for j = 1 : n_panels_tot
    [u_sou_t,v_sou_t] = source_gen(x_t(j), y_t(j), x_t(j+1), y_t(j+1), xc_t(1), yc_t(1));
    [u_vor_t,v_vor_t] = vortex_gen(x_t(j), y_t(j), x_t(j+1), y_t(j+1), xc_t(1), yc_t(1));
    A_t(n_panels_tot+1,j) = u_sou_t*tangents_t(1,1) + v_sou_t*tangents_t(1,2);
    A_t(n_panels_tot+1,n_panels_tot+1) = A_t(n_panels_tot+1,n_panels_tot+1) + u_vor_t*tangents_t(1,1) + v_vor_t*tangents_t(1,2);
    [u_sou_t,v_sou_t] = source_gen(x_t(j), y_t(j), x_t(j+1), y_t(j+1), xc_t(n_panels_tot), yc_t(n_panels_tot));
    [u_vor_t,v_vor_t] = vortex_gen(x_t(j), y_t(j), x_t(j+1), y_t(j+1), xc_t(n_panels_tot), yc_t(n_panels_tot));
    A_t(n_panels_tot+1,j) = A_t(n_panels_tot+1,j) + u_sou_t*tangents_t(n_panels_tot,1) + v_sou_t*tangents_t(n_panels_tot,2);
    A_t(n_panels_tot+1,n_panels_tot+1) = A_t(n_panels_tot+1,n_panels_tot+1) + u_vor_t*tangents_t(n_panels_tot,1) + v_vor_t*tangents_t(n_panels_tot,2);
end

for i = 1 : n_panels_tot
    b_t(i) = -U_inf_t*(normals_t(i,:))';
end

b_t(n_panels_tot+1) = -U_inf_t*(tangents_t(1,:))' + -U_inf_t*(tangents_t(n_panels_tot,:))';

solution_t = A_t \ b_t;
q_t = solution_t(1:n_panels_tot);
gamma_t = solution_t(n_panels_tot+1);

%% Compute Velocity, Cp and Cl - Wing

U_w = zeros(n_panels_tot,2);

for i = 1 : n_panels_tot
    U_w(i,:) = U_inf_w;
    for j = 1 : n_panels_tot
        [u_sou_w,v_sou_w] = source_gen(x_w(j), y_w(j), x_w(j+1), y_w(j+1), xc_w(i), yc_w(i));
        [u_vor_w,v_vor_w] = vortex_gen(x_w(j), y_w(j), x_w(j+1), y_w(j+1), xc_w(i), yc_w(i));
        U_w(i,1) = U_w(i,1) + u_sou_w*q_w(j) + gamma_w*u_vor_w;
        U_w(i,2) = U_w(i,2) + v_sou_w*q_w(j) + gamma_w*v_vor_w;
    end
end

circulation_w = 0;

for i = 1 : n_panels_tot
    circulation_w = circulation_w + panel_length_w(i)*gamma_w;
end

Cp_w = zeros (n_panels_tot,1);
Vt_w = zeros (n_panels_tot,1);

for i = 1 : n_panels_tot
    Vt_w(i) = (U_w(i,1)*tangents_w(i,1) + (U_w(i,2)*tangents_w(i,2)));
end

Vt_w = Vt_w';

for i = 1 : n_panels_tot
    Cp_w(i) = 1 - (Vt_w(i)^2)/(abs(U_inf_abs))^2;
end

Cl_w = 0;

for i = 1 : n_panels_tot
    Cl_w = Cl_w + Cp_w(i)*panel_length_w(i)*normals_w(i,2);
end

Cl_w = -1 * Cl_w;
Cl_walt = 2*circulation_w/U_inf_abs;

figure (3)
plot(xc_w,-Cp_w,'r')
xlabel('x')
ylabel('-Cp')

% Calcolo dell'angolo di incidenza indotta per l'ala
alpha_ind_w = atan(U_w(:,2) ./ U_inf_w(1)); % Angolo in radianti

%% Compute Velocity, Cp and Cl - Tail

U_t = zeros(n_panels_tot,2);

for i = 1 : n_panels_tot
    U_t(i,:) = U_inf_t;
    for j = 1 : n_panels_tot
        [u_sou_t,v_sou_t] = source_gen(x_t(j), y_t(j), x_t(j+1), y_t(j+1), xc_t(i), yc_t(i));
        [u_vor_t,v_vor_t] = vortex_gen(x_t(j), y_t(j), x_t(j+1), y_t(j+1), xc_t(i), yc_t(i));
        U_t(i,1) = U_t(i,1) + u_sou_t*q_t(j) + gamma_t*u_vor_t;
        U_t(i,2) = U_t(i,2) + v_sou_t*q_t(j) + gamma_t*v_vor_t;
    end
end

circulation_t = 0;

for i = 1 : n_panels_tot
    circulation_t = circulation_t + panel_length_t(i)*gamma_t;
end

Cp_t = zeros (n_panels_tot,1);
Vt_t = zeros (n_panels_tot,1);

for i = 1 : n_panels_tot
    Vt_t(i) = (U_t(i,1)*tangents_t(i,1) + (U_t(i,2)*tangents_t(i,2)));
end

Vt_t = Vt_t';

for i = 1 : n_panels_tot
    Cp_t(i) = 1 - (Vt_t(i)^2)/(abs(U_inf_abs))^2;
end

Cl_t = 0;

for i = 1 : n_panels_tot
    Cl_t = Cl_t + Cp_t(i)*panel_length_t(i)*normals_t(i,2);
end

Cl_t = -1 * Cl_t;
Cl_talt = 2*circulation_t/U_inf_abs;

figure (4)
plot(xc_t,-Cp_t,'b')
xlabel('x')
ylabel('-Cp')

% Calcolo dell'angolo di incidenza indotta per la coda
alpha_ind_t = atan(U_t(:,2) ./ U_inf_t(1)); % Angolo in radianti

% Converti gli angoli di incidenza indotta in gradi
alpha_ind_w_deg = rad2deg(alpha_ind_w); % Ala
alpha_ind_t_deg = rad2deg(alpha_ind_t); % Coda

% Stampa i risultati
fprintf('Angolo di incidenza indotta medio ala: %.4f gradi\n', mean(alpha_ind_w_deg));
fprintf('Angolo di incidenza indotta medio per la coda: %.4f gradi\n', mean(alpha_ind_t_deg));


%% Streamlines Wing

ifield = 500;
jfield = 500;
[xfield,yfield] = meshgrid(linspace(-1,2,500),linspace(-0.75,0.75,500));
ufield = zeros(ifield,jfield);
vfield = zeros(ifield,jfield);
vmag = zeros(ifield,jfield);


for jj= 1 : jfield
    for ii= 1 : ifield

        xi = xfield(ii,jj);
        yi = yfield(ii,jj);
        ufield(ii,jj) = 0.0;
        vfield(ii,jj) = 0.0;

        for j=1:n_panels_tot
            [us, vs] = source_gen(x_w(j), y_w(j), x_w(j+1), y_w(j+1), xi, yi);
            [uv, vv] = vortex_gen(x_w(j), y_w(j), x_w(j+1), y_w(j+1), xi, yi);
            ufield(ii,jj) = ufield(ii,jj) + us*q_w(j) + uv*gamma_w; % superimposes velocities for sources and vortex
            vfield(ii,jj) = vfield(ii,jj) + vs*q_w(j) + vv*gamma_w;
        end

        ufield(ii,jj) = ufield(ii,jj) + U_inf_w(1); % add free stream
        vfield(ii,jj) = vfield(ii,jj) + U_inf_w(2);
        vmag(ii,jj) = sqrt(ufield(ii,jj)^2+vfield(ii,jj)^2);

    end
end

figure(5)
hold on;
box on;
axis on;
plot(x_w, y_w ,'k')
% plot(x_w, y_w ,'r*')
plot(x_ml_w, y_ml_w, 'k')
plot(x_cl_w, y_cl_w, 'k--')
% plot(xc_w,yc_w,'ys')
axis equal

starty = -.75 : 0.05 : .75; %define where to start streamlines
startx = -ones(size(starty));
streamline(xfield,yfield,ufield, vfield,startx,starty);
axis([-1,2, -0.6, 0.7])

%% Streamlines Tail

ifield = 500;
jfield = 500;
[xfield,yfield] = meshgrid(linspace(-1,2,500),linspace(-0.75,0.75,500));


for jj= 1 : jfield % loop over field points -
    for ii= 1 : ifield

        xi = xfield(ii,jj);
        yi = yfield(ii,jj);

        ufield(ii,jj) = 0.0; % initializes field velocity data
        vfield(ii,jj) = 0.0;

        for j=1:n_panels_tot
            [us, vs] = source_gen(x_t(j), y_t(j), x_t(j+1), y_t(j+1), xi, yi);
            [uv, vv] = vortex_gen(x_t(j), y_t(j), x_t(j+1), y_t(j+1), xi, yi);
            ufield(ii,jj) = ufield(ii,jj) + us*q_t(j) + uv*gamma_t; % superimposes velocities for sources and vortex
            vfield(ii,jj) = vfield(ii,jj) + vs*q_t(j) + vv*gamma_t;
        end

        ufield(ii,jj) = ufield(ii,jj) + U_inf_t(1); % add free stream
        vfield(ii,jj) = vfield(ii,jj) + U_inf_t(2);
        vmag(ii,jj) = sqrt(ufield(ii,jj)^2+vfield(ii,jj)^2);

    end
end

figure(6)

hold on;
box on;
axis on;
plot(x_t, y_t ,'k')
% plot(x_t, y_t ,'r*')
plot(x_ml_t, y_ml_t, 'k')
plot(x_cl_t, y_cl_t, 'k--')
% plot(xc_t,yc_t,'ys')
axis equal

starty = -.75 : 0.05 : .75; %define where to start streamlines
startx = -ones(size(starty));
streamline(xfield,yfield,ufield, vfield,startx,starty)
axis([-1,2, -0.6, 0.7])

toc