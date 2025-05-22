import numpy as np
import tkinter as tk
from tkinter import filedialog
import os

# Apri finestra di selezione file
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

# Inverti tutti i punti
x_reordered = x[::-1]
y_reordered = y[::-1]

# Salva il nuovo file
outname = os.path.splitext(filename)[0] + '_riordinato.dat'
np.savetxt(outname, np.column_stack([x_reordered, y_reordered]), fmt='%.8e')
print(f"File riordinato salvato come '{outname}'")