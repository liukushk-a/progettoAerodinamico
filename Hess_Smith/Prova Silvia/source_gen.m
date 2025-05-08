function [u, v] = source_gen(x1, y1, x2, y2, xc, yc)
    % Calcola la velocità (u,v) indotta da un pannello sorgente di intensità unitaria
    % che va da (x1,y1) a (x2,y2), osservata nel punto (xc,yc)
    
    % Lunghezza del pannello
    l = sqrt((x2 - x1)^2 + (y2 - y1)^2);
    
    % Trasformazione nel sistema locale del pannello
    dx = x2 - x1;
    dy = y2 - y1;
    
    % Coordinate del punto di controllo nel sistema locale
    x_local = ( (xc - x1)*dx + (yc - y1)*dy ) / l;
    y_local = (-(xc - x1)*dy + (yc - y1)*dx ) / l;
    
    % Coordinate dell'estremità destra del pannello nel sistema locale
    x2_local = l;
    y2_local = 0;
    
    % Distanze e angoli relativi
    r1 = sqrt(x_local^2 + y_local^2);
    r2 = sqrt((x_local - x2_local)^2 + y_local^2);
    
    theta1 = atan2(y_local, x_local);
    theta2 = atan2(y_local, x_local - x2_local);
    
    % Correzione per salti di π quando si attraversano discontinuità numeriche
    if abs(theta1) < 1e-12 && abs(theta2) > 3
    theta1 = 0;
    theta2 = pi;
    elseif abs(theta2) < 1e-12 && abs(theta1) > 3
    theta2 = 0;
    theta1 = -pi;
    end
    
    % Velocità nel sistema locale (dati dalla teoria della sorgente lineare)
    ul = (1 / (2*pi)) * log(r1 / r2);
    vl = (1 / (2*pi)) * (theta2 - theta1);
    
    % Rotazione nel sistema globale
    u = ul * (dx / l) - vl * (dy / l);
    v = ul * (dy / l) + vl * (dx / l);
    
 end