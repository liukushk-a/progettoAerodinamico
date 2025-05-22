import numpy as np
import tkinter as tk
from tkinter import filedialog
from scipy.interpolate import splprep, splev
import os
import matplotlib.pyplot as plt

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

# Parametrizza i punti per la spline
tck, u = splprep([x, y], s=0, per=0)

# Genera 200 punti equispaziati lungo la curva
u_new = np.linspace(0, 1, 200)
x_new, y_new = splev(u_new, tck)

# Salva il nuovo file
outname = os.path.splitext(filename)[0] + '_interp200.dat'
np.savetxt(outname, np.column_stack([x_new, y_new]), fmt='%.8e')
print(f"File interpolato salvato come '{outname}'")

# Plot confronto e differenza
plt.figure(figsize=(10, 5))
plt.subplot(1, 2, 1)
plt.plot(x, y, 'o-', label='Originale')
plt.plot(x_new, y_new, '.-', label='Interpolato (200)')
plt.legend()
plt.title('Confronto profilo')
plt.axis('equal')

plt.subplot(1, 2, 2)
# Interpola i punti originali sugli x_new per confronto (solo se monotono)
if np.all(np.diff(x) > 0) or np.all(np.diff(x) < 0):
    y_orig_interp = np.interp(x_new, x, y)
    diff = y_new - y_orig_interp
    plt.plot(x_new, diff, label='Differenza y')
    plt.title('Differenza interpolato-originale')
    plt.xlabel('x')
    plt.ylabel('y_interp - y_orig')
    plt.legend()
else:
    plt.text(0.5, 0.5, 'x non monotono,\ndifferenza non plottata', ha='center', va='center')
plt.tight_layout()
plt.show()