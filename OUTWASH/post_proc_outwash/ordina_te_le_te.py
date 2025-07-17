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

# Trova la metà dei punti
n_points = len(x)
half_point = n_points // 2

# Prendi la prima metà e ribaltala
x_first_half = x[:half_point]
y_first_half = y[:half_point]

# Ribalta la prima metà
x_first_half_flipped = x_first_half[::-1]
y_first_half_flipped = y_first_half[::-1]

# Combina: prima metà ribaltata + seconda metà
x_new = np.concatenate([x_first_half_flipped, x[half_point:]])
y_new = np.concatenate([y_first_half_flipped, y[half_point:]])

# Salva il nuovo file
output_name = os.path.splitext(filename)[0] + '_ribaltato.dat'
np.savetxt(output_name, np.column_stack([x_new, y_new]), fmt='%.8f')

print(f"File salvato come: {output_name}")
print(f"Punti originali: {n_points}")
print(f"Prima metà ribaltata: {half_point} punti")
print(f"Seconda metà invariata: {n_points - half_point} punti")