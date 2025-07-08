# import numpy as np
# from tkinter import Tk
# from tkinter.filedialog import askopenfilename
# import matplotlib.pyplot as plt
# import pandas as pd
#
# # === Caricamento file ===
# Tk().withdraw()
# nose = askopenfilename(title="Seleziona il primo file .txt", filetypes=[("Text files", "*.txt")])
#
# # Carico il dataframe
# data1 = pd.read_csv(nose, delim_whitespace=True, header=None, names=["x", "z", "y"])
#
# # Filtro i punti con y vicino a 0
# data2 = data1[np.abs(data1["y"]) < 1e-10]
#
# # Scrivo su file le coordinate selezionate
# with open("coordinateNaso.txt", 'w') as file:
#     for i in range(len(data2)):
#         file.write(f"{data2.iloc[i]['x']} {data2.iloc[i]['z']}\n")
#
# # Riordino le cooordinate del dataframe dalla più bassa alla più alta
# data2 = data2.sort_values(by="z").reset_index(drop=True)
#
# # Plot di prova
# plt.figure(figsize=(10, 5))
# plt.plot(data2["x"], data2["z"], marker='o', linestyle='-', color='b')
# plt.show()
#
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from tkinter import Tk
from tkinter.filedialog import askopenfilename

# === Selezione file ===
Tk().withdraw()
nose = askopenfilename(title="Seleziona il primo file .txt", filetypes=[("Text files", "*.txt")])

# === Caricamento dati ===
data1 = pd.read_csv(nose, delim_whitespace=True, header=None, names=["x", "z", "y"])

# === Filtro: prendo solo i punti con y ≈ 0 ===
data2 = data1[np.abs(data1["y"]) < 1e-10].reset_index(drop=True)

# === Ordinamento: nearest neighbor ===
def ordina_per_distanza(data):
    coords = data[["x", "z"]].to_numpy()
    n = len(coords)
    visited = np.zeros(n, dtype=bool)
    ordered_indices = [0]
    visited[0] = True

    for _ in range(1, n):
        last = coords[ordered_indices[-1]]
        dists = np.linalg.norm(coords - last, axis=1)
        dists[visited] = np.inf
        next_idx = np.argmin(dists)
        ordered_indices.append(next_idx)
        visited[next_idx] = True

    return data.iloc[ordered_indices].reset_index(drop=True)

data2 = ordina_per_distanza(data2)

# === (Facoltativo) Chiudo la curva ===
data2.loc[len(data2)] = data2.loc[0]

# === Scrivo su file le coordinate ordinate ===
with open("coordinateNaso.txt", 'w') as file:
    for i in range(len(data2)):
        file.write(f"{data2.loc[i, 'x']} {data2.loc[i, 'z']}\n")

# === Plot finale ===
plt.figure(figsize=(10, 5))
plt.plot(data2["x"], data2["z"], marker='o', linestyle='-', color='b')
plt.title("Profilo ordinato")
plt.axis('equal')
plt.grid(True)
plt.show()

# coordinateCorrette = askopenfilename(title="Seleziona il file .txt del main", filetypes=[("Text files", "*.txt")])
#
# plt.figure(figsize=(10, 5))
# plt.plot(coordinateCorrette, marker='o', linestyle='-', color='r', label='Coordinate corrette')
# plt.show()
