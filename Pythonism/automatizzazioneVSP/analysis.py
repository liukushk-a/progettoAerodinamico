'''Lo script deve essere eseguito nella stessa cartella in cui è presente il file .vsp3
Ricordarsi di cancellare la cartella di output di eventuali precedenti analisi
In osstanza ciò che ho intenzione di fare adesso è estendere il codice dello script
sweep.py in modo tale che sia l'utilizzatore a scegliere che parametro cambiare tramite
un input. In questo modo, l'analisi non sarà più limitata solo all'angolo di sweep, ma
anche a tanto altro.
Ricordarsi di attivare l'ambiente su anaconda prima di eseguire lo script.
C'è una cosa carina quì: in sostanza esaminando il file .vsp3, ho visto che cambiando il
twist alla radice e all'estremità, si modificano due voci con due ID diversi, perciò in 
quella sezione del codice, io devo ricavarmi gli ID dei Twist, in modo da cambiarli tutti.'''

# Librerie necessarie
import sys
import openvsp_config
import openvsp as vsp
import os
import numpy as np
import time
from multiprocessing import Pool
#from openvsp import SPAN_WSECT_DRIVER, TAPER_WSECT_DRIVER, ROOTC_WSECT_DRIVER, AREA_WSECT_DRIVER, TIPC_WSECT_DRIVER # Prova del tizio sul blog

# Directory in cui lo script è archiviato
script_dir = os.path.dirname(os.path.abspath(__file__))

# Rimuovi eventuali analisi precedenti
vsp.DeleteAllResults()

# Carico la geometria chiedendo all'utilizzatore il nome del file .vsp3
nomeGeometria = input("Inserisci il nome del file .vsp3 (senza .vsp3): ")
vsp.ReadVSPFile(os.path.join(script_dir, f"{nomeGeometria}.vsp3"))

# Definisco i parametri che rimangono fissi (modificabili su altri script)
Re_l = 180000
Machinf = 0.06
Vinf = 19.45
Sref = 0.41600
bref = 1.3
cref = 0.32
AoA = 0
NCPUs = 8

# Faccio capire all'utilizzatore che numeri corrispondono ai parametri
print("1. Sweep main wing")
print("2. Dihedral main wing")
print("3. Twist main wing (totale, sia alla radice che all'estremità alare)")
print("4. Coordinate X secondary wing (di default si trova a +0.4 sull'asse X)")
print("5. Coordinate Z secondary wing (di default si trova a +0.35 sull'asse Z)")
print("6. Sweep secondary wing")
print("7. Dihedral secondary wing")
print("8. Twist secondary wing")

# L'utilizzatore sceglie il parametro da variare
parametro = int(input("Inserire il numero corrispondente al parametro da variare: "))

if parametro == 1:
    scelta = "Sweep_main_wing"
elif parametro == 2:
    scelta = "Dihedral_main_wing"
elif parametro == 3:
    scelta = "Twist_main_wing"
elif parametro == 4:
    scelta = "Coordinate_X_secondary_wing"
elif parametro == 5:
    scelta = "Coordinate_Z_secondary_wing"
elif parametro == 6:
    scelta = "Sweep_secondary_wing"
elif parametro == 7:
    scelta = "Dihedral_secondary_wing"
elif parametro == 8:
    scelta = "Twist_secondary_wing"
else:
    print("Scelta non valida. Seleziona una scelta valida.")

# L'utilizzatore sceglie i parametri
startValue = float(input(f"Inserisci il valore minimo di {scelta}: "))
endValue = float(input(f"Inserisci il valore massimo di {scelta}: "))
spacing = int(input(f"Inserisci il numero di punti di {scelta} (numero intero): "))

# Genero il linspace tra i valori
valori = np.linspace(startValue, endValue, spacing)

# Specifico la cartella di output e creo suddetta cartella (dentro tale cartella, poi,
# creerò delle cartelle in modo ricorsivo, una per ogni angolo di sweep usato)
os.mkdir(f'./analysisOutput{scelta}')

# Mi sposto nella cartella che contiene i risultati delle analisi
os.chdir(f'./analysisOutput{scelta}')

# Trovo l'ID delle geometrie
mainWing_id = vsp.FindGeom("WingGeom_main", 0)
secWing_id = vsp.FindGeom("WingGeom_sec", 0)

for valore in valori:
    # Genero la cartella apposita coi risultati
    os.mkdir(f'./{scelta}_{valore}')

    # Mi sposto nella cartella dove stamperò i risultati
    os.chdir(f'./{scelta}_{valore}')
    
    # Quì parte la distinzione tra le varie scelte
    if parametro == 1:

        # Roba del tizio sul blog per permettermi i cambi in radice (prova)
        #vsp.SetDriverGroup(mainWing_id, 1, SPAN_WSECT_DRIVER, ROOTC_WSECT_DRIVER, TAPER_WSECT_DRIVER)

        # Imposto lo sweep
        vsp.SetParmVal(mainWing_id, "Sweep", "XSec_1", valore)  # Sweep alla sezione 1

        # Aggiorno la geometria
        vsp.Update()

        # Salvo la geometria corrente nella cartella dove salverò anche i risultati
        vsp.WriteVSPFile(f"geometry_{scelta}_{valore}.vsp3")

        # Analisi della geometria per ottenere i file necessari
        vsp.SetAnalysisInputDefaults("VSPAERODegenGeom")
        vsp.ExecAnalysis("VSPAERODegenGeom")

        # Setto l'analisi aerodinamica
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "ReCref", [Re_l])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "MachRef", [Machinf])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "Vinf", [Vinf])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "VRef", [Vinf])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "Sref", [Sref])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "bref", [bref])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "cref", [cref])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "AlphaStart", [0.0])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "AlphaEnd", [0.0])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "AlphaNpts", [1])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "GroundEffect", [0.03])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "NCPUs", [NCPUs])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "AnalysisMethod", [vsp.VORTEX_LATTICE])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "MaxIter", [250])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "WakeNumIter", [20])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "NumWakeNodes", [64])

        # Eseguo l'analisi aerodinamica
        res_id = vsp.ExecAnalysis("VSPAEROSweep")

        # Salvo i risultati
        vsp.WriteResultsCSVFile(res_id, "Results.csv")

        # Stampo una stringa di conferma di scrittura dei risultati
        print(f"Risultati salvati nela cartella {scelta}_{valore}")

        # Torno alla cartella principale
        os.chdir('..')

    elif parametro == 2:

       # Roba del tizio sul blog per permettermi i cambi in radice (prova)
        #vsp.SetDriverGroup(mainWing_id, 1, SPAN_WSECT_DRIVER, ROOTC_WSECT_DRIVER, TAPER_WSECT_DRIVER)

        # Imposto il diedro
        vsp.SetParmVal(mainWing_id, "Dihedral", "XSec_1", valore)  # Diedro alla sezione 1

        # Aggiorno la geometria
        vsp.Update()

        # Salvo la geometria corrente nella cartella dove salverò anche i risultati
        vsp.WriteVSPFile(f"geometry_{scelta}_{valore}.vsp3")

        # Analisi della geometria per ottenere i file necessari
        vsp.SetAnalysisInputDefaults("VSPAERODegenGeom")
        vsp.ExecAnalysis("VSPAERODegenGeom")

        # Setto l'analisi aerodinamica
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "ReCref", [Re_l])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "MachRef", [Machinf])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "Vinf", [Vinf])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "VRef", [Vinf])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "Sref", [Sref])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "bref", [bref])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "cref", [cref])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "AlphaStart", [0.0])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "AlphaEnd", [0.0])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "AlphaNpts", [1])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "GroundEffect", [0.03])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "NCPUs", [NCPUs])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "AnalysisMethod", [vsp.VORTEX_LATTICE])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "MaxIter", [250])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "WakeNumIter", [20])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "NumWakeNodes", [64])

        # Eseguo l'analisi aerodinamica
        res_id = vsp.ExecAnalysis("VSPAEROSweep")

        # Salvo i risultati
        vsp.WriteResultsCSVFile(res_id, "Results.csv")

        # Stampo una stringa di conferma di scrittura dei risultati
        print(f"Risultati salvati nela cartella {scelta}_{valore}")

        # Torno alla cartella principale
        os.chdir('..')
   
    elif parametro == 3:

       # Roba del tizio sul blog per permettermi i cambi in radice (prova)
        #vsp.SetDriverGroup(mainWing_id, 1, AREA_WSECT_DRIVER, ROOTC_WSECT_DRIVER, TIPC_WSECT_DRIVER );

        # Imposto il twist sia alla radice che all'estremità alare
        vsp.SetParmVal(mainWing_id, "Twist", "XSec_0", valore) # Twist alla radice
        vsp.SetParmVal(mainWing_id, "Twist", "XSec_1", valore)  # Twist alla sezione 1

        # Aggiorno la geometria
        vsp.Update()

        # Salvo la geometria corrente nella cartella dove salverò anche i risultati
        vsp.WriteVSPFile(f"geometry_{scelta}_{valore}.vsp3")

        # Analisi della geometria per ottenere i file necessari
        vsp.SetAnalysisInputDefaults("VSPAERODegenGeom")
        vsp.ExecAnalysis("VSPAERODegenGeom")

        # Setto l'analisi aerodinamica
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "ReCref", [Re_l])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "MachRef", [Machinf])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "Vinf", [Vinf])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "VRef", [Vinf])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "Sref", [Sref])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "bref", [bref])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "cref", [cref])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "AlphaStart", [0.0])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "AlphaEnd", [0.0])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "AlphaNpts", [1])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "GroundEffect", [0.03])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "NCPUs", [NCPUs])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "AnalysisMethod", [vsp.VORTEX_LATTICE])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "MaxIter", [250])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "WakeNumIter", [20])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "NumWakeNodes", [64])

        # Eseguo l'analisi aerodinamica
        res_id = vsp.ExecAnalysis("VSPAEROSweep")

        # Salvo i risultati
        vsp.WriteResultsCSVFile(res_id, "Results.csv")

        # Stampo una stringa di conferma di scrittura dei risultati
        print(f"Risultati salvati nela cartella {scelta}_{valore}")

        # Torno alla cartella principale
        os.chdir('..')
    
    elif parametro == 4:

       # Roba del tizio sul blog per permettermi i cambi in radice (prova)
        #vsp.SetDriverGroup(mainWing_id, 1, AREA_WSECT_DRIVER, ROOTC_WSECT_DRIVER, TIPC_WSECT_DRIVER );

        # Imposto la coordinata X della wing secondaria
        vsp.SetParmVal(secWing_id, "X_Rel_Location", "XForm", 0.4 + valore) # Notare che per le posizioni usi XForm

        # Aggiorno la geometria
        vsp.Update()

        # Salvo la geometria corrente nella cartella dove salverò anche i risultati
        vsp.WriteVSPFile(f"geometry_{scelta}_{valore}.vsp3")

        # Analisi della geometria per ottenere i file necessari
        vsp.SetAnalysisInputDefaults("VSPAERODegenGeom")
        vsp.ExecAnalysis("VSPAERODegenGeom")

        # Setto l'analisi aerodinamica
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "ReCref", [Re_l])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "MachRef", [Machinf])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "Vinf", [Vinf])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "VRef", [Vinf])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "Sref", [Sref])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "bref", [bref])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "cref", [cref])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "AlphaStart", [0.0])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "AlphaEnd", [0.0])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "AlphaNpts", [1])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "GroundEffect", [0.03])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "NCPUs", [NCPUs])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "AnalysisMethod", [vsp.VORTEX_LATTICE])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "MaxIter", [250])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "WakeNumIter", [20])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "NumWakeNodes", [64])

        # Eseguo l'analisi aerodinamica
        res_id = vsp.ExecAnalysis("VSPAEROSweep")

        # Salvo i risultati
        vsp.WriteResultsCSVFile(res_id, "Results.csv")

        # Stampo una stringa di conferma di scrittura dei risultati
        print(f"Risultati salvati nela cartella {scelta}_{valore}")

        # Torno alla cartella principale
        os.chdir('..')

    elif parametro == 5:

       # Roba del tizio sul blog per permettermi i cambi in radice (prova)
        #vsp.SetDriverGroup(mainWing_id, 1, AREA_WSECT_DRIVER, ROOTC_WSECT_DRIVER, TIPC_WSECT_DRIVER );

        # Imposto la coordinata X della wing secondaria
        vsp.SetParmVal(secWing_id, "Z_Rel_Location", "XForm", 0.35 + valore) # Notare che per le posizioni usi XForm

        # Aggiorno la geometria
        vsp.Update()

        # Salvo la geometria corrente nella cartella dove salverò anche i risultati
        vsp.WriteVSPFile(f"geometry_{scelta}_{valore}.vsp3")

        # Analisi della geometria per ottenere i file necessari
        vsp.SetAnalysisInputDefaults("VSPAERODegenGeom")
        vsp.ExecAnalysis("VSPAERODegenGeom")

        # Setto l'analisi aerodinamica
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "ReCref", [Re_l])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "MachRef", [Machinf])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "Vinf", [Vinf])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "VRef", [Vinf])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "Sref", [Sref])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "bref", [bref])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "cref", [cref])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "AlphaStart", [0.0])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "AlphaEnd", [0.0])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "AlphaNpts", [1])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "GroundEffect", [0.03])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "NCPUs", [NCPUs])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "AnalysisMethod", [vsp.VORTEX_LATTICE])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "MaxIter", [250])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "WakeNumIter", [20])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "NumWakeNodes", [64])

        # Eseguo l'analisi aerodinamica
        res_id = vsp.ExecAnalysis("VSPAEROSweep")

        # Salvo i risultati
        vsp.WriteResultsCSVFile(res_id, "Results.csv")

        # Stampo una stringa di conferma di scrittura dei risultati
        print(f"Risultati salvati nela cartella {scelta}_{valore}")

        # Torno alla cartella principale
        os.chdir('..')

    if parametro == 6:

        # Roba del tizio sul blog per permettermi i cambi in radice (prova)
        #vsp.SetDriverGroup(mainWing_id, 1, SPAN_WSECT_DRIVER, ROOTC_WSECT_DRIVER, TAPER_WSECT_DRIVER)

        # Imposto lo sweep
        vsp.SetParmVal(secWing_id, "Sweep", "XSec_1", valore)  # Sweep alla sezione 1

        # Aggiorno la geometria
        vsp.Update()

        # Salvo la geometria corrente nella cartella dove salverò anche i risultati
        vsp.WriteVSPFile(f"geometry_{scelta}_{valore}.vsp3")

        # Analisi della geometria per ottenere i file necessari
        vsp.SetAnalysisInputDefaults("VSPAERODegenGeom")
        vsp.ExecAnalysis("VSPAERODegenGeom")

        # Setto l'analisi aerodinamica
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "ReCref", [Re_l])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "MachRef", [Machinf])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "Vinf", [Vinf])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "VRef", [Vinf])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "Sref", [Sref])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "bref", [bref])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "cref", [cref])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "AlphaStart", [0.0])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "AlphaEnd", [0.0])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "AlphaNpts", [1])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "GroundEffect", [0.03])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "NCPUs", [NCPUs])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "AnalysisMethod", [vsp.VORTEX_LATTICE])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "MaxIter", [250])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "WakeNumIter", [20])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "NumWakeNodes", [64])

        # Eseguo l'analisi aerodinamica
        res_id = vsp.ExecAnalysis("VSPAEROSweep")

        # Salvo i risultati
        vsp.WriteResultsCSVFile(res_id, "Results.csv")

        # Stampo una stringa di conferma di scrittura dei risultati
        print(f"Risultati salvati nela cartella {scelta}_{valore}")

        # Torno alla cartella principale
        os.chdir('..')

    elif parametro == 7:

       # Roba del tizio sul blog per permettermi i cambi in radice (prova)
        #vsp.SetDriverGroup(mainWing_id, 1, SPAN_WSECT_DRIVER, ROOTC_WSECT_DRIVER, TAPER_WSECT_DRIVER)

        # Imposto il diedro
        vsp.SetParmVal(secWing_id, "Dihedral", "XSec_1", valore)  # Diedro alla sezione 1

        # Aggiorno la geometria
        vsp.Update()

        # Salvo la geometria corrente nella cartella dove salverò anche i risultati
        vsp.WriteVSPFile(f"geometry_{scelta}_{valore}.vsp3")

        # Analisi della geometria per ottenere i file necessari
        vsp.SetAnalysisInputDefaults("VSPAERODegenGeom")
        vsp.ExecAnalysis("VSPAERODegenGeom")

        # Setto l'analisi aerodinamica
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "ReCref", [Re_l])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "MachRef", [Machinf])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "Vinf", [Vinf])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "VRef", [Vinf])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "Sref", [Sref])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "bref", [bref])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "cref", [cref])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "AlphaStart", [0.0])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "AlphaEnd", [0.0])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "AlphaNpts", [1])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "GroundEffect", [0.03])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "NCPUs", [NCPUs])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "AnalysisMethod", [vsp.VORTEX_LATTICE])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "MaxIter", [250])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "WakeNumIter", [20])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "NumWakeNodes", [64])

        # Eseguo l'analisi aerodinamica
        res_id = vsp.ExecAnalysis("VSPAEROSweep")

        # Salvo i risultati
        vsp.WriteResultsCSVFile(res_id, "Results.csv")

        # Stampo una stringa di conferma di scrittura dei risultati
        print(f"Risultati salvati nela cartella {scelta}_{valore}")

        # Torno alla cartella principale
        os.chdir('..')
   
    elif parametro == 8:

       # Roba del tizio sul blog per permettermi i cambi in radice (prova)
        #vsp.SetDriverGroup(mainWing_id, 1, AREA_WSECT_DRIVER, ROOTC_WSECT_DRIVER, TIPC_WSECT_DRIVER );

        # Imposto il twist sia alla radice che all'estremità alare
        vsp.SetParmVal(secWing_id, "Twist", "XSec_0", valore) # Twist alla radice
        vsp.SetParmVal(secWing_id, "Twist", "XSec_1", valore)  # Twist alla sezione 1

        # Aggiorno la geometria
        vsp.Update()

        # Salvo la geometria corrente nella cartella dove salverò anche i risultati
        vsp.WriteVSPFile(f"geometry_{scelta}_{valore}.vsp3")

        # Analisi della geometria per ottenere i file necessari
        vsp.SetAnalysisInputDefaults("VSPAERODegenGeom")
        vsp.ExecAnalysis("VSPAERODegenGeom")

        # Setto l'analisi aerodinamica
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "ReCref", [Re_l])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "MachRef", [Machinf])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "Vinf", [Vinf])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "VRef", [Vinf])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "Sref", [Sref])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "bref", [bref])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "cref", [cref])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "AlphaStart", [0.0])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "AlphaEnd", [0.0])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "AlphaNpts", [1])
        vsp.SetDoubleAnalysisInput("VSPAEROSweep", "GroundEffect", [0.03])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "NCPUs", [NCPUs])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "AnalysisMethod", [vsp.VORTEX_LATTICE])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "MaxIter", [250])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "WakeNumIter", [20])
        vsp.SetIntAnalysisInput("VSPAEROSweep", "NumWakeNodes", [64])

        # Eseguo l'analisi aerodinamica
        res_id = vsp.ExecAnalysis("VSPAEROSweep")

        # Salvo i risultati
        vsp.WriteResultsCSVFile(res_id, "Results.csv")

        # Stampo una stringa di conferma di scrittura dei risultati
        print(f"Risultati salvati nela cartella {scelta}_{valore}")

        # Torno alla cartella principale
        os.chdir('..')
