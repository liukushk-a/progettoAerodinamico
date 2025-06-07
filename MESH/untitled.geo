// Importa i punti da un file .txt
Merge "GAW1_ruotato.txt";

// Associa i punti letti a entità Point
For i In {0:#Points[]-1}
  Point(i+1) = {Points[i][0], Points[i][1], Points[i][2], 1.0};
EndFor

// Crea una spline che collega i punti in ordine
Spline(1) = {1:#Points[]};

// (Opzionale) Chiudi la curva se è un profilo chiuso:
Line(2) = {#Points[], 1};   // collega ultimo punto al primo
Line Loop(1) = {1, 2};
Plane Surface(1) = {1};

