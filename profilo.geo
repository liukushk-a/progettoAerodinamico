// Gmsh project created on Fri May 30 12:16:39 2025
SetFactory("OpenCASCADE");

// 1. Carica STL
Merge "profilo.stp"; // metti qui il nome reale del tuo file

// 2. Ricostruisci un solido da mesh STL triangolata
//    Questo comando cerca di convertire le superfici mesh in superfici CAD
//    se il tuo STL è chiuso e manifolde, funziona bene
//    (se non è chiuso, ti dirò come chiuderlo)
Surface Loop(100) = Surface In BoundingBox{-1e22, -1e22, -1e22, 1e22, 1e22, 1e22};
Volume(100) = {100};

// 3. Crea la box esterna
Box(200) = {-10, -10, -10, 20, 20, 20};

// 4. Differenza booleana: fluido = box - oggetto
BooleanDifference{ Volume{200}; Delete; }{ Volume{100}; Delete; }

// 5. Physical group
Physical Volume("fluid") = {1};
