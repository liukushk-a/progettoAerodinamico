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

# Trova il leading edge (min x)
idx_le = np.argmin(x)

# Ricostruisci la sequenza: TE -> dorso -> LE -> ventre -> TE
x_reordered = np.concatenate([x[:idx_le+1], x[idx_le+1:][::-1]])
y_reordered = np.concatenate([y[:idx_le+1], y[idx_le+1:][::-1]])

# Salva il nuovo file
outname = os.path.splitext(filename)[0] + '_riordinato.dat'
np.savetxt(outname, np.column_stack([x_reordered, y_reordered]), fmt='%.8e')
print(f"File riordinato salvato come '{outname}'")