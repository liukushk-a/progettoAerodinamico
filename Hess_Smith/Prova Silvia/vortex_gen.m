function [u,v] = vortex_gen( x1, y1, x2, y2, xc, yc)

l=sqrt((x2-x1)^2+(y2-y1)^2);
xl2 = l; 
yl2 = 0;

xl=(xc-x1)*(x2-x1)/l+(yc-y1)*(y2-y1)/l; 
yl=-(xc-x1)*(y2-y1)/l+(yc-y1)*(x2-x1)/l;
r1=sqrt(xl^2+yl^2); r2=sqrt((xl-xl2)^2+(yl-yl2)^2);
theta1=atan2(yl,xl); 
theta2=atan2(yl-yl2,xl-xl2);

if (abs(theta1)<10^(-12) && abs(theta2)>3) 
    theta1=0; 
    theta2=pi; 
end

if (abs(theta2)<10^(-12) && abs(theta1)>3)
    theta2=0; 
    theta1=-pi; 
end

ul=1/(2*pi)*(theta2-theta1); 
vl=-1/(2*pi)*log(r1/r2);
u=ul*(x2-x1)/l-vl*(y2-y1)/l; 
v=ul*(y2-y1)/l+vl*(x2-x1)/l;
end