# Combinazioni profili da provare

## Flap (ok se nella cartella /MESH/PROFILI/FLAP/)

Selig S1210 (ok)

Be 092-075 (ok)

EPPLER 61 (ok)

Wortmann FX 082-512 (Per il meme) (ok)

Be 123-076 (ok)

GOE 431 (ok)

## Indicazioni

Main:

- traslazione in x: 0.160
- traslazione in y: 0.075
- riscalatura: 0.32
- angolo: 3
- polo: 0.08

Flap:

- traslazione in x: 0.451
- traslazione in y: 0.117
- riscalatura: 0.13
- angolo: 20
- polo: 0.0325

All'inizio si usa interpola_200.py, lo script di Giorgio per aumentare il numero di punti, di seguito il fromTXTtoDataFrame.py, per le rotazioni e traslazioni del caso.

Dopodichè c'è il geowriting.py, che prende in input sia i due profili che il naso. Per le simulazioni senza naso, farò un'altra funzione a parte.

Per la mesh, come cl__1 mettiamo 0.006 e le altre misure sono scritte in automatico nel geowriting.py.

Ognuno ha dentro CFD/incomprimibile una cartella RESULTS con il suo nome. Dentro questa cartella mettiamo tante cartelle coi nomi delle combinazioni, tipo wing_flap. Dentro ognuna di queste cartelle tu fai le simulazioni e devi tenere solo il file .cfg, il .geo e il history.csv e un txt coi risultati.

Ogni simulazione va fatta per due volte: con e senza naso. Il file txt del naso si chiama coordinateNaso.txt.

Sono Luca: finora ho fatto simulazioni con 100 iterazioni interne e massimo CFL 400. Funziona abbastanza bene e nonostante durante il processo di aumento del CFl i residui aumentino un po' e anche dopo aver raggiunto il CFL massimo debba passare un po' di tempo prima di vedere stabilità, dopo si stabilizza bene e i residui scendono stabili e un bel po'. Può capitare, però, che i residui col CFL di 400 salgano inspiegabilmente certe volte, perciò credo proprio che il setting migliore per il nostro caso sia tenere 100 iterazioni interne, ma abbassare il CFL max a 350: in questo modo si verificano meno questi episodi, anche nelle fasi finali della simulazione, raggiungendo secondo me il miglior tempo di esecuzione.

## (ok se messo nella cartella /MESH/PROFILI/MAIN/)
## Malloreddus man 

Main:

- EPPLER 398 (ok)
- Be 183-056 (ok)
- NACA 6412 (ok)

## Uomo orso

Main:

- Be 183-105
- EPPLER 396 (ok)
- Be 153-055


## Pirletta

Main:

- Selig S1223 (ok)
- NACA 4412 (ok)
- Be 153-076

