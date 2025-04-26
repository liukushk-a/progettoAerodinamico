'''Lo script deve essere eseguito nella stessa cartella in cui è presente il file .vsp3
Ricordarsi di cancellare la cartella di output di eventuali precedentia analisi'''

# Librerie necessarie
import sys
import openvsp_config
import openvsp as vsp
import os
import numpy as np
import time
from multiprocessing import Pool

# Directory in cui lo script è archiviato
script_dir = os.path.dirname(os.path.abspath(__file__))

# Rimuovi eventuali analisi precedenti
vsp.DeleteAllResults()

# Carico la geometria chiedendo all'utilizzatore il nome del file .vsp3
nomeGeometria = input("Inserisci il nome del file .vsp3 (senza .vsp3): ")
vsp.ReadVSPFile(os.path.join(script_dir, f"{nomeGeometria}.vsp3"))

# Definisco i parametri che rimangono fissi (modificabili su altri script)
Re_l = 180000
Machinf = 0.05
Vinf = 19.45
Sref = 0.41600
bref = 1.3
cref = 0.32
AoA = 0.0
NCPUs = 4

# L'utilizzatore sceglie i parametri
minSweep = float(input("Inserisci il valore minimo di angolo di sweep: "))
maxSweep = float(input("Inserisci il valore massimo di angolo di sweep: "))
spaceSweep = int(input("Inserisci il numero di punti di sweep (numero intero): "))

# Genero il linspace
sweeps = np.linspace(minSweep, maxSweep, spaceSweep)

# Specifico la cartella di output e creo suddetta cartella (dentro tale cartella, poi,
# creerò delle cartelle in modo ricorsivo, una per ogni angolo di sweep usato)
os.mkdir('./sweepAnalysisoutput')

# Mi sposto nella cartella che contiene i risultati delle analisi
os.chdir('./sweepAnalysisoutput')

# Do il nome all'analisi
analysis_name = "VSPAEROSweep" 
vsp.SetAnalysisInputDefaults(analysis_name)

# Trovo l'ID della geometria, perchè a quanto pare  OpenVSP funziona con l'ID
wing_id = vsp.FindGeom("WingGeom_main", 0)

for sweep in sweeps:
    # Genero la cartella apposita coi risultati
    os.mkdir(f'./sweep_{sweep}')

    # Mi sposto nella cartella dove stamperò i risultati
    os.chdir(f'./sweep_{sweep}')
    
    # Fisso il punto di partenza della simulazione
    vsp.SetAnalysisInputDefaults(analysis_name)
    
    # Imposto lo sweep
    vsp.SetParmVal(wing_id, "Sweep", "XSec_1", sweep)  # Sweep alla sezione 1

    # Aggiorno la geometria
    vsp.Update()

    # Imposto i parametri della simulazione
    vsp.SetDoubleAnalysisInput("VSPAEROSweep", "Re", [Re_l])
    vsp.SetDoubleAnalysisInput("VSPAEROSweep", "Mach", [Machinf])
    vsp.SetDoubleAnalysisInput("VSPAEROSweep", "Vinf", [Vinf])
    vsp.SetDoubleAnalysisInput("VSPAEROSweep", "Alpha", [AoA])
    vsp.SetIntAnalysisInput("VSPAEROSweep", "NCPUs", [NCPUs])
    vsp.SetIntAnalysisInput("VSPAEROSweep", "AnalysisMethod", [vsp.VORTEX_LATTICE])

    # Salvo la geometria corrente nella cartella dove salverò anche i risultati
    vsp.WriteVSPFile(vsp.GetVSPFileName())

    # Eseguo l'analisi
    res_id = vsp.ExecAnalysis(analysis_name)

    # Salvo i risultati
    vsp.WriteResultsCSVFile(res_id, "Results.csv")

    # Stampo una stringa di conferma di scrittura dei risultati
    print(f"Risultati salvati nela cartella sweep_{sweep}")

    # Torno alla cartella principale
    os.chdir('..')

