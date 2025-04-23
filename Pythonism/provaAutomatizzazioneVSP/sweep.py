'''Lo script deve essere eseguito nella stessa cartella in cui è presente il file .vsp3'''

# Mi assicuro che Python riconosca il modulo openvsp nella cartella giusta
import sys
sys.path.append("/home/liukushka/OpenVSP/build/python_api")

# Librerie necessarie
import vsp
import os
import numpy as np
import time
from multiprocessing import Pool

# Directory in cui lo script è archiviato
script_dir = os.path.dirname(os.path.abspath(__file__))

# Carico la geometria
vsp.ReadVSPFile(os.path.join(script_dir, "{}.vsp3"))

# Rimuovi eventuali analisi precedenti
vsp.DeleteAllResults()

# L'utilizzatore sceglie i parametri
minSweep = input("Inserisci il valore minimo di angolo di sweep: ")
maxSweep = input("Inserisci il valore massimo di angolo di sweep: ")
spaceSweep = input("Inserisci il numero di punti di sweep (numero intero): ")

# Genero il linspace
sweeps = np.linspace(minSweep, maxSweep, spaceSweep)

# Specifico la cartella di output
output_dir = "sweepAnalysisoutput"
os.makedirs(output_dir, exist_ok=True)


def run_vspaero(params):
    span, root_chord, sweep, dihedral, iteration = params

    # Ensure a clean slate for OpenVSP in the child process
    vsp.VSPRenew()
    vsp.ClearVSPModel()

    # Add wing geometry
    wing_id = vsp.AddGeom("WING")
    vsp.SetParmVal(wing_id, "TotalSpan", "WingGeom", span)
    vsp.SetParmVal(wing_id, "TotalChord", "WingGeom", root_chord)
    vsp.SetParmVal(wing_id, "Sweep", "XSec_1", sweep)  # Sweep at first section
    vsp.SetParmVal(wing_id, "Dihedral", "XSec_1", dihedral)  # Dihedral at first section
    vsp.Update()

    # Save VSP model
    vsp_file = os.path.join(output_dir, f"aircraft_model_{iteration}.vsp3")
    vsp.WriteVSPFile(vsp_file)

    # Run aerodynamic analysis using VSPAERO
    vsp.ComputeCompGeom( vsp.SET_ALL, False, 0 )
    
def main():
    iteration = 0
    tasks = []

    # Generate parameter combinations
    for span in SPANS:
        for chord in chords:
            for sweep in sweeps:
                for dihedral in dihedrals:
                    tasks.append((span, chord, sweep, dihedral, iteration))
                    iteration += 1

    # Measure execution time
    start_time = time.time()

    # Use multiprocessing to parallelize work
    with Pool(processes=4) as pool:
        pool.map(run_vspaero, tasks)

    end_time = time.time()
    elapsed_time = end_time - start_time

    print(f"Benchmark completed for {iteration} iterations.")
    print(f"Total execution time: {elapsed_time:.2f} seconds.")


if __name__ == "__main__":
    main()