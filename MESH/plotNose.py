import gmsh
import numpy as np
from tkinter import Tk
from tkinter.filedialog import askopenfilename
import matplotlib.pyplot as plt

# === Funzione per rimuovere punti troppo ravvicinati ===
def filter_close_points(points, tol=1e-6):
    filtered = [points[0]]
    for p in points[1:]:
        if np.linalg.norm(np.array(p) - np.array(filtered[-1])) > tol:
            filtered.append(p)
    return np.array(filtered)

# === Caricamento file ===
Tk().withdraw()
file_path1 = askopenfilename(title="Seleziona il primo file .txt", filetypes=[("Text files", "*.txt")])

if not file_path1:
    raise FileNotFoundError("Entrambi i file devono essere selezionati")

data1 = np.loadtxt(file_path1)

# Rimozione punti duplicati o troppo vicini
data1 = filter_close_points(data1)

# Visualizzazione (opzionale)
plt.plot(data1[:, 0], data1[:, 1], label='Naso')
plt.legend()
plt.axis('equal')
plt.show()

