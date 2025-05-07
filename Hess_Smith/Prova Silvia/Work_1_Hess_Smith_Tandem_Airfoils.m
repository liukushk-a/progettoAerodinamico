clear
close all
clc

tic

U_inf_abs = 1;
chord = 1;
dist_x = 1.2;
dist_y = 0.11;

n_airfoils = 2;

%% Airfoils Generation

% Wing

alpha_w = 2.5*pi/180;
U_inf = [U_inf_abs*cos(alpha_w), U_inf_abs*sin(alpha_w)];
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

for i = 1:n_panels_surf

    xc_us_w(i) = 0.5*(x_us_w(i) + x_us_w(i+1));
    yc_us_w(i) = 0.5*(y_us_w(i) + y_us_w(i+1));
    xc_ls_w(i) = 0.5*(x_ls_w(i) + x_ls_w(i+1));
    yc_ls_w(i) = 0.5*(y_ls_w(i) + y_ls_w(i+1));

end

xc_w = [flipud(xc_ls_w); xc_us_w];
yc_w = [flipud(yc_ls_w); yc_us_w];
x_w = [flipud(x_ls_w(2:end)); x_us_w];
y_w = [flipud(y_ls_w(2:end)); y_us_w];

% Tail

alpha_t = 4*pi/180;
d_alpha = alpha_t-alpha_w;

M_t = 0;
P_t = 0;
SS_t = 12;

[x_us_t, y_us_t, x_ls_t, y_ls_t, x_ml_t, y_ml_t, x_cl_t, y_cl_t] = NACA_4d_gen (M_t, P_t, SS_t, n_panels_surf);

x_t = [flipud(x_ls_t(2:end)); x_us_t];
y_t = [flipud(y_ls_t(2:end)); y_us_t];

x_t = (x_t - 0.5) * cos(d_alpha) + y_t * sin(d_alpha) + 0.5;
y_t = -(x_t - 0.5) * sin (d_alpha) + y_t * cos(d_alpha);
x_ml_t = (x_ml_t - 0.5) * cos(d_alpha) + y_ml_t * sin(d_alpha) + 0.5;
y_ml_t = -(x_ml_t - 0.5) * sin (d_alpha) + y_ml_t * cos(d_alpha);
x_cl_t = (x_cl_t - 0.5) * cos(d_alpha) + y_cl_t * sin(d_alpha) + 0.5;
y_cl_t = -(x_cl_t - 0.5) * sin (d_alpha) + y_cl_t * cos(d_alpha);

x_t = x_t + 1 + dist_x - x_t(ceil(n_panels_tot/2)+1);
y_t = y_t + dist_y;
x_ml_t = x_ml_t + 1 + dist_x - x_ml_t(1);
y_ml_t = y_ml_t + dist_y;
x_cl_t = x_cl_t + 1 + dist_x - x_cl_t(1);
y_cl_t = y_cl_t + dist_y;

xc_us_t = zeros(n_panels_surf,1);
yc_us_t = zeros(n_panels_surf,1);
xc_ls_t = zeros(n_panels_surf,1);
yc_ls_t = zeros(n_panels_surf,1);
xc_t = [flipud(xc_ls_t); xc_us_t];
yc_t = [flipud(yc_ls_t); yc_us_t];

for i = 1:n_panels_tot

    xc_t(i) = 0.5*(x_t(i) + x_t(i+1));
    yc_t(i) = 0.5*(y_t(i) + y_t(i+1));

end


figure(1)
hold on;
box on;
grid on;
axis on;
plot(x_w, y_w ,'b')
% plot(x_w, y_w ,'r*')
plot(x_ml_w, y_ml_w, 'k')
plot(x_cl_w, y_cl_w, 'k--')
% plot(xc_w,yc_w,'ys')
axis equal

plot(x_t, y_t ,'b')
% plot(x_t, y_t ,'r*')
plot(x_ml_t, y_ml_t, 'k')
plot(x_cl_t, y_cl_t, 'k--')
% plot(xc_t,yc_t,'ys')
axis([-0.2, 3.3, -1.5, 1.5])


%% Normals and Tangents

normals_w = zeros(n_panels_tot,2);
tangents_w = zeros(n_panels_tot,2);
panel_length_w = zeros(n_panels_tot,1);
beta_w = zeros(n_panels_tot,1);

for i = 1:n_panels_tot

    panel_length_w(i) = sqrt((x_w(i+1)-x_w(i))^2 + (y_w(i+1)-y_w(i))^2);
    tangents_w(i,1) = (x_w(i+1)-x_w(i))/panel_length_w(i);
    tangents_w(i,2) = (y_w(i+1)-y_w(i))/panel_length_w(i);
    normals_w(i,1) = -((y_w(i+1)-y_w(i))/panel_length_w(i));
    normals_w(i,2) = (x_w(i+1)-x_w(i))/panel_length_w(i);
    beta_w(i) = asin(tangents_w(i,2));

end

normals_t = zeros(n_panels_tot,2);
tangents_t = zeros(n_panels_tot,2);
panel_length_t = zeros(n_panels_tot,1);
beta_t = zeros(n_panels_tot,1);

for i = 1:n_panels_tot

    panel_length_t(i) = sqrt((x_t(i+1)-x_t(i))^2 + (y_t(i+1)-y_t(i))^2);
    tangents_t(i,1) = (x_t(i+1)-x_t(i))/panel_length_t(i);
    tangents_t(i,2) = (y_t(i+1)-y_t(i))/panel_length_t(i);
    normals_t(i,1) = -((y_t(i+1)-y_t(i))/panel_length_t(i));
    normals_t(i,2) = (x_t(i+1)-x_t(i))/panel_length_t(i);
    beta_t(i) = asin(tangents_t(i,2));

end

%% System Building

A = zeros(n_panels_tot*n_airfoils+1*n_airfoils);
A_w = zeros(n_panels_tot+1);
b_w = zeros(n_panels_tot+1,1);
A_t = zeros(n_panels_tot+1);
b_t = zeros(n_panels_tot+1,1);
A_12 = zeros(n_panels_tot+1);
A_21 = zeros(n_panels_tot+1);

% Wing by itself
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

A(1:n_panels_tot,1:n_panels_tot) = A_w(1:n_panels_tot,1:n_panels_tot);
A(1:n_panels_tot,n_panels_tot*n_airfoils+1) = A_w(1:n_panels_tot,n_panels_tot+1);
A(n_panels_tot*n_airfoils+1,1:n_panels_tot) = A_w(n_panels_tot+1,1:n_panels_tot);
A(n_panels_tot*n_airfoils+1,n_panels_tot*n_airfoils+1) = A_w(n_panels_tot+1,n_panels_tot+1);

% Tail by itself

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

A(n_panels_tot+1:n_panels_tot*n_airfoils, n_panels_tot+1:n_panels_tot*n_airfoils) = A_t(1:n_panels_tot,1:n_panels_tot);
A(n_panels_tot+1:n_panels_tot*n_airfoils, n_panels_tot*n_airfoils+2) = A_t(1:n_panels_tot,n_panels_tot+1);
A(n_panels_tot*n_airfoils+2, n_panels_tot+1:n_panels_tot*n_airfoils) = A_t(n_panels_tot+1,1:n_panels_tot);
A(n_panels_tot*n_airfoils+2, n_panels_tot*n_airfoils+2) = A_t(n_panels_tot+1,n_panels_tot+1);

% Filling Matrix 1-2

for i = 1 : n_panels_tot
    for j = 1 : n_panels_tot
        [u_sou_t,v_sou_t] = source_gen(x_t(j), y_t(j), x_t(j+1), y_t(j+1), xc_w(i), yc_w(i));
        [u_vor_t,v_vor_t] = vortex_gen(x_t(j), y_t(j), x_t(j+1), y_t(j+1), xc_w(i), yc_w(i));
        A_12(i,j) = u_sou_t*normals_w(i,1) + v_sou_t*normals_w(i,2);
        A_12(i,n_panels_tot+1) = A_12(i,n_panels_tot+1) + u_vor_t*normals_w(i,1) + v_vor_t*normals_w(i,2);
    end
end

for j = 1 : n_panels_tot
    [u_sou_t,v_sou_t] = source_gen(x_t(j), y_t(j), x_t(j+1), y_t(j+1), xc_w(1), yc_w(1));
    [u_vor_t,v_vor_t] = vortex_gen(x_t(j), y_t(j), x_t(j+1), y_t(j+1), xc_w(1), yc_w(1));
    A_12(n_panels_tot+1,j) = u_sou_t*tangents_w(1,1) + v_sou_t*tangents_w(1,2);
    A_12(n_panels_tot+1,n_panels_tot+1) = A_12(n_panels_tot+1,n_panels_tot+1) + u_vor_t*tangents_w(1,1) + v_vor_t*tangents_w(1,2);
    [u_sou_t,v_sou_t] = source_gen(x_t(j), y_t(j), x_t(j+1), y_t(j+1), xc_w(n_panels_tot), yc_w(n_panels_tot));
    [u_vor_t,v_vor_t] = vortex_gen(x_t(j), y_t(j), x_t(j+1), y_t(j+1), xc_w(n_panels_tot), yc_w(n_panels_tot));
    A_12(n_panels_tot+1,j) = A_12(n_panels_tot+1,j) + u_sou_t*tangents_w(n_panels_tot,1) + v_sou_t*tangents_w(n_panels_tot,2);
    A_12(n_panels_tot+1,n_panels_tot+1) = A_12(n_panels_tot+1,n_panels_tot+1) + u_vor_t*tangents_w(n_panels_tot,1) + v_vor_t*tangents_w(n_panels_tot,2);
end

A(1:n_panels_tot, n_panels_tot+1:n_panels_tot*n_airfoils) = A_12(1:n_panels_tot,1:n_panels_tot);
A(1:n_panels_tot, n_panels_tot*n_airfoils+2) = A_12(1:n_panels_tot,n_panels_tot+1);
A(n_panels_tot*n_airfoils+1, n_panels_tot+1:n_panels_tot*n_airfoils) = A_12(n_panels_tot+1,1:n_panels_tot);
A(n_panels_tot*n_airfoils+1, n_panels_tot*n_airfoils+2) = A_12(n_panels_tot+1,n_panels_tot+1);

% Filling Matrix 2-1

for i = 1 : n_panels_tot
    for j = 1 : n_panels_tot
        [u_sou_w,v_sou_w] = source_gen(x_w(j), y_w(j), x_w(j+1), y_w(j+1), xc_t(i), yc_t(i));
        [u_vor_w,v_vor_w] = vortex_gen(x_w(j), y_w(j), x_w(j+1), y_w(j+1), xc_t(i), yc_t(i));
        A_21(i,j) = u_sou_w*normals_t(i,1) + v_sou_w*normals_t(i,2);
        A_21(i,n_panels_tot+1) = A_21(i,n_panels_tot+1) + u_vor_w*normals_t(i,1) + v_vor_w*normals_t(i,2);
    end
end

for j = 1 : n_panels_tot
    [u_sou_w,v_sou_w] = source_gen(x_w(j), y_w(j), x_w(j+1), y_w(j+1), xc_t(1), yc_t(1));
    [u_vor_w,v_vor_w] = vortex_gen(x_w(j), y_w(j), x_w(j+1), y_w(j+1), xc_t(1), yc_t(1));
    A_21(n_panels_tot+1,j) = u_sou_w*tangents_t(1,1) + v_sou_w*tangents_t(1,2);
    A_21(n_panels_tot+1,n_panels_tot+1) = A_21(n_panels_tot+1,n_panels_tot+1) + u_vor_w*tangents_t(1,1) + v_vor_w*tangents_t(1,2);
    [u_sou_w,v_sou_w] = source_gen(x_w(j), y_w(j), x_w(j+1), y_w(j+1), xc_t(n_panels_tot), yc_t(n_panels_tot));
    [u_vor_w,v_vor_w] = vortex_gen(x_w(j), y_w(j), x_w(j+1), y_w(j+1), xc_t(n_panels_tot), yc_t(n_panels_tot));
    A_21(n_panels_tot+1,j) = A_21(n_panels_tot+1,j) + u_sou_w*tangents_t(n_panels_tot,1) + v_sou_w*tangents_t(n_panels_tot,2);
    A_21(n_panels_tot+1,n_panels_tot+1) = A_21(n_panels_tot+1,n_panels_tot+1) + u_vor_w*tangents_t(n_panels_tot,1) + v_vor_w*tangents_t(n_panels_tot,2);
end

A(n_panels_tot+1:n_panels_tot*n_airfoils, 1:n_panels_tot) = A_21(1:n_panels_tot,1:n_panels_tot);
A(n_panels_tot+1:n_panels_tot*n_airfoils, n_panels_tot*n_airfoils+1) = A_21(1:n_panels_tot,n_panels_tot+1);
A(n_panels_tot*n_airfoils+2, 1:n_panels_tot) = A_21(n_panels_tot+1,1:n_panels_tot);
A(n_panels_tot*n_airfoils+2, n_panels_tot*n_airfoils+1) = A_21(n_panels_tot+1,n_panels_tot+1);

for i = 1 : n_panels_tot
    b_w(i) = -U_inf*(normals_w(i,:))';
end

b_w(n_panels_tot+1) = -U_inf*(tangents_w(1,:) + tangents_w(n_panels_tot,:))';

for i = 1 : n_panels_tot
    b_t(i) = -U_inf*(normals_t(i,:))';
end

b_t(n_panels_tot+1) = -U_inf*(tangents_t(1,:))' + -U_inf*(tangents_t(n_panels_tot,:))';

b = [b_w(1:n_panels_tot); b_t(1:n_panels_tot); b_w(n_panels_tot+1); b_t(n_panels_tot+1)];

solution = A \ b;
q_w = solution(1:n_panels_tot);
q_t = solution(n_panels_tot+1:n_panels_tot*n_airfoils);
gamma_w = solution(n_panels_tot*n_airfoils+1);
gamma_t = solution(n_panels_tot*n_airfoils+2);

%% Compute Velocity, Cp and Cl

U_w = zeros(n_panels_tot,2);

for i = 1 : n_panels_tot
    U_w(i,:) = U_inf;
    for j = 1 : n_panels_tot
        [u_sou_w,v_sou_w] = source_gen(x_w(j), y_w(j), x_w(j+1), y_w(j+1), xc_w(i), yc_w(i));
        [u_vor_w,v_vor_w] = vortex_gen(x_w(j), y_w(j), x_w(j+1), y_w(j+1), xc_w(i), yc_w(i));
        U_w(i,1) = U_w(i,1) + u_sou_w*q_w(j) + gamma_w*u_vor_w;
        U_w(i,2) = U_w(i,2) + v_sou_w*q_w(j) + gamma_w*v_vor_w;
    end
    for j = 1 : n_panels_tot
        [u_sou_w,v_sou_w] = source_gen(x_t(j), y_t(j), x_t(j+1), y_t(j+1), xc_w(i), yc_w(i));
        [u_vor_w,v_vor_w] = vortex_gen(x_t(j), y_t(j), x_t(j+1), y_t(j+1), xc_w(i), yc_w(i));
        U_w(i,1) = U_w(i,1) + u_sou_w*q_t(j) + gamma_t*u_vor_w;
        U_w(i,2) = U_w(i,2) + v_sou_w*q_t(j) + gamma_t*v_vor_w;
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

Cm_le_w = 0;
z = [0 0 1]';
r_w = [xc_w yc_w zeros(n_panels_tot,1)];
n_w = [normals_w zeros(n_panels_tot,1)];

for i = 1 : n_panels_tot
    Cm_le_w = Cm_le_w + Cp_w(i)*panel_length_w(i)*(cross(r_w(i,:) , n_w(i,:)))*z;
end

Cm_le_w = -1 * Cm_le_w;


U_t = zeros(n_panels_tot,2);

for i = 1 : n_panels_tot
    U_t(i,:) = U_inf;
    for j = 1 : n_panels_tot
        [u_sou_t,v_sou_t] = source_gen(x_t(j), y_t(j), x_t(j+1), y_t(j+1), xc_t(i), yc_t(i));
        [u_vor_t,v_vor_t] = vortex_gen(x_t(j), y_t(j), x_t(j+1), y_t(j+1), xc_t(i), yc_t(i));
        U_t(i,1) = U_t(i,1) + u_sou_t*q_t(j) + gamma_t*u_vor_t;
        U_t(i,2) = U_t(i,2) + v_sou_t*q_t(j) + gamma_t*v_vor_t;
    end
    for j = 1 : n_panels_tot
        [u_sou_t,v_sou_t] = source_gen(x_w(j), y_w(j), x_w(j+1), y_w(j+1), xc_t(i), yc_t(i));
        [u_vor_t,v_vor_t] = vortex_gen(x_w(j), y_w(j), x_w(j+1), y_w(j+1), xc_t(i), yc_t(i));
        U_t(i,1) = U_t(i,1) + u_sou_t*q_w(j) + gamma_w*u_vor_t;
        U_t(i,2) = U_t(i,2) + v_sou_t*q_w(j) + gamma_w*v_vor_t;
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

Cm_le_t = 0;
r_t = [xc_t yc_t zeros(n_panels_tot,1)];
n_t = [normals_t zeros(n_panels_tot,1)];

for i = 1 : n_panels_tot
    Cm_le_t = Cm_le_t + Cp_t(i)*panel_length_t(i)*(cross(r_t(i,:) , n_t(i,:)))*z;
end

Cm_le_t = -1 * Cm_le_t;

figure (2)
hold on
plot(xc_w,-Cp_w,'r')
plot(xc_w,-Cp_t,'b')
box on
legend('Wing','Tail')
xlabel('x')
ylabel('-Cp')

%% Streamlines

ifield = 500;
jfield = 500;
[xfield,yfield] = meshgrid(linspace(-1,4,500),linspace(-0.75,2,500));
ufield = zeros(ifield,jfield);
vfield = zeros(ifield,jfield);
vmag = zeros(ifield,jfield);


for jj= 1 : jfield % loop over field points -
    for ii= 1 : ifield

        xi = xfield(ii,jj);
        yi = yfield(ii,jj);
        ufield(ii,jj) = 0.0; % initializes field velocity data
        vfield(ii,jj) = 0.0;

        for j=1:n_panels_tot
            [us_w, vs_w] = source_gen(x_w(j), y_w(j), x_w(j+1), y_w(j+1), xi, yi);
            [uv_w, vv_w] = vortex_gen(x_w(j), y_w(j), x_w(j+1), y_w(j+1), xi, yi);
            [us_t, vs_t] = source_gen(x_t(j), y_t(j), x_t(j+1), y_t(j+1), xi, yi);
            [uv_t, vv_t] = vortex_gen(x_t(j), y_t(j), x_t(j+1), y_t(j+1), xi, yi);
            ufield(ii,jj) = ufield(ii,jj) + us_w*q_w(j) + uv_w*gamma_w + us_t*q_t(j) + uv_t*gamma_t; % superimposes velocities for sources and vortex
            vfield(ii,jj) = vfield(ii,jj) + vs_w*q_w(j) + vv_w*gamma_w + vs_t*q_t(j) + vv_t*gamma_t;
        end

        ufield(ii,jj) = ufield(ii,jj) + U_inf(1); % add free stream
        vfield(ii,jj) = vfield(ii,jj) + U_inf(2);
        vmag(ii,jj) = sqrt(ufield(ii,jj)^2+vfield(ii,jj)^2);

    end
end

figure(5)
hold on;
box on;
grid on;
axis on;
plot(x_w, y_w ,'k')
% plot(x_w, y_w ,'r*')
plot(x_ml_w, y_ml_w, 'k')
plot(x_cl_w, y_cl_w, 'k--')
% plot(xc_w,yc_w,'ys')
axis equal

plot(x_t, y_t ,'k')
% plot(x_t, y_t ,'r*')
plot(x_ml_t, y_ml_t, 'k')
plot(x_cl_t, y_cl_t, 'k--')
% plot(xc_t,yc_t,'ys')

starty = -.75 : 0.05 : .75; %define where to start streamlines
startx = -ones(size(starty));
streamline(xfield,yfield,ufield, vfield,startx,starty)
axis([-1, 4, -0.6, 0.7])

toc