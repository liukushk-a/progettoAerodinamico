function [x_us, y_us, x_ls, y_ls, x_ml, y_ml, x_cl, y_cl] = NACA_4d_gen (M, P, SS, n)

% M  : ordinata massima della linea media             (una cifra)
%
% P  : posizione sulla corda dell'ordinata massima M  (una cifra)
%
% SS : spessore massimo del profilo simmetrico        (due cifre)
%
% n  : numero di punti lungo la corda distribuiti uniformemente,
%      inclusi i punti del bordo di attacco e del bordo di uscita,  
%      in corrispondenza dei quali si calcolano i punti sul dorso
%      e sul ventre
% 
%
% M, P e SS possono essere forniti come NUMERI INTERI, compresi, 
%      rispettivamente,
%
%      M  fra 1 e 9,  in  %  della corda      
%      P  fra 1 e 9   in 1/10  della corda   
%      SS fra 1 e 99  in  %  della corda
%
%      oppure, se si preferisce, come VALORI REALI  < 1  
%      frazione della corda

%Input check

if M >= 1
   m = M/100; 
else  
   m = M;
end

if P >= 1
   p = P/10;  % in 1/10 
else  
   p = P;
end

if SS >= 1
   s = SS/100; 
else  
   s = SS;
end

%Coordinates vectors initialisation

x_p = zeros(n+1,1);

k = n + 1;

for i = 1 : k
    x_p(i) = 1 - (0.5 + 0.5 * cos((i-1)*pi/n)); % "uno meno serve per metterli in ordine
end

% 
% k = n;
% i = [1:k];
% x_p = 1-0.5*(1+cos(((i-1)*pi)/n));
% x_p = x_p(1:ceil(length(x_p)/2))/0.5;

% Upper Surface points
x_us = x_p;
y_us = zeros(n+1,1); 

% Lower Surface points
x_ls = x_p;
y_ls = zeros(n+1,1);

y_th = zeros(n+1,1);

% Mean Line points
x_ml = x_p;
y_ml = zeros(n+1,1);
Dy_ml = zeros(n+1,1);
theta = zeros(n+1,1);

%Chord Line points
x_cl = x_p;
y_cl = zeros(n+1,1);

% Mean line ordinates

for i = 1 : (n+1)
    if x_ml(i) < p
        y_ml(i) = m/(p^2)*(2*p*x_ml(i) - x_ml(i)^2);
        Dy_ml(i) = (m/p^2) * 2*(p - x_ml(i));
        theta(i) = atan (Dy_ml(i));
    else
        y_ml(i) = m/((1-p)^2)*((1-2*p) + 2*p*x_ml(i) - x_ml(i)^2);
        Dy_ml(i) = (m/(1-p)^2) * 2*(p - x_ml(i));
        theta(i) = atan (Dy_ml(i));
    end
end

% Upper and Lower surface points

for i = 1 : (n+1)

    y_th(i) = s/0.20 * (0.29690 * sqrt(x_p(i))  -  0.12600 * x_p(i)  -  0.35160 * x_p(i)^2 + 0.28430 * x_p(i)^3  -  0.10360 * x_p(i)^4);
    
    x_us(i) = x_ml(i)  -  y_th(i) * sin(theta(i)); 
    y_us(i) = y_ml(i)  +  y_th(i) * cos(theta(i));

    x_ls(i) = x_ml(i)  +  y_th(i) * sin(theta(i));
    y_ls(i) = y_ml(i)  -  y_th(i) * cos(theta(i));
    
end















end

