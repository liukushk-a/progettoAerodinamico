import numpy as np
import tkinter as tk
from tkinter import filedialog
import os

# Seleziona file tramite GUI
root = tk.Tk()
root.withdraw()
filename = filedialog.askopenfilename(
    title="Seleziona un file .dat",
    filetypes=[("DAT files", "*.dat")]
)
if not filename:
    print("Nessun file selezionato.")
    exit()

# Carica i dati
data = np.loadtxt(filename)
x = data[:, 0]
y = data[:, 1]

# Specchia rispetto all'asse x (inverte il segno delle y)
y_mirrored = -y

# Salva il nuovo file
outname = os.path.splitext(filename)[0] + '_specchiatoX.dat'
np.savetxt(outname, np.column_stack([x, y_mirrored]), fmt='%.8e')
print(f"File specchiato salvato come '{outname}'")