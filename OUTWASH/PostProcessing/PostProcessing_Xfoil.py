import numpy as np
import os

def load_bl_file(filename):
    """
    Carica i dati dal file .bl e li separa in diverse variabili
    """
    # Percorso del file
    data_path = os.path.join('..', 'Datas', filename)
    
    # Lettura del file
    with open(data_path, 'r') as file:
        lines = file.readlines()
    
    # Inizializzazione liste per i dati
    x = []          # Posizione x lungo il profilo
    H = []          # Fattore di forma
    theta = []      # Spessore di momento
    cf = []         # Coefficiente di attrito
    
    # Salta l'intestazione (prima riga)
    for line in lines[1:]:
        values = line.strip().split()
        if len(values) >= 4:  # Verifica che ci siano abbastanza valori
            x.append(float(values[0]))
            H.append(float(values[1]))
            theta.append(float(values[2]))
            cf.append(float(values[3]))
    
    # Converti in array numpy
    return {
        'x': np.array(x),
        'H': np.array(H),
        'theta': np.array(theta),
        'cf': np.array(cf)
    }

# Esempio di utilizzo
bl_data = load_bl_file('naca63012.bl')

# Accesso ai dati separati
x = bl_data['x']
H = bl_data['H']
theta = bl_data['theta']
cf = bl_data['cf']