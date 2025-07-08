import numpy as np
import matplotlib.pyplot as plt
import os
import csv

# Carica Be_183-125
file_path_be = os.path.join('..', 'profili', 'Be_183-125.dat')
data_be = np.loadtxt(file_path_be)
x_be = data_be[:, 0]
y_be = data_be[:, 1]

# Carica Clark Y
file_path_clark = os.path.join('..', 'profili', 'Clark-Y.dat')
data_clark = np.loadtxt(file_path_clark)
N = len(data_clark) // 2
x_clark_dorso = data_clark[:N, 0]
y_clark_dorso = data_clark[:N, 1]
x_clark_ventre = data_clark[N:, 0][::-1]  # inverti il ventre
y_clark_ventre = data_clark[N:, 1][::-1]

# Unisci dorso e ventre per chiudere il profilo
x_clark = np.concatenate([x_clark_dorso, x_clark_ventre])
y_clark = np.concatenate([y_clark_dorso, y_clark_ventre])

# Ruota di 180° attorno all'origine
x_be_rot = -x_be
y_be_rot = -y_be
x_clark_rot = -x_clark
y_clark_rot = -y_clark

# Trova l'indice del LE (x minimo) e riordina i punti da lì
def porta_le_a_sinistra(x, y):
    idx_le = np.argmin(x)
    x_new = np.concatenate([x[idx_le:], x[:idx_le]])
    y_new = np.concatenate([y[idx_le:], y[:idx_le]])
    return x_new, y_new

x_be_rot, y_be_rot = porta_le_a_sinistra(x_be_rot, y_be_rot)
x_clark_rot, y_clark_rot = porta_le_a_sinistra(x_clark_rot, y_clark_rot)

# Chiudi il profilo aggiungendo il primo punto alla fine
x_be_rot = np.append(x_be_rot, x_be_rot[0])
y_be_rot = np.append(y_be_rot, y_be_rot[0])
x_clark_rot = np.append(x_clark_rot, x_clark_rot[0])
y_clark_rot = np.append(y_clark_rot, y_clark_rot[0])

# Ruota di 3 gradi verso l'alto il Clark Y (antiorario)
theta = -np.deg2rad(3)  # negativo per senso orario
x_c = x_clark_rot * np.cos(theta) - y_clark_rot * np.sin(theta)
y_c = x_clark_rot * np.sin(theta) + y_clark_rot * np.cos(theta)

# Scala il Be_183-125 del 70%
x_be_rot_scaled = 0.4 * x_be_rot
y_be_rot_scaled = 0.4* y_be_rot

# Inclina il Be_183-125 di 12 gradi in senso orario
theta_incl = -np.deg2rad(12)  # negativo per senso orario
x_be_rot_scaled_incl = x_be_rot_scaled * np.cos(theta_incl) - y_be_rot_scaled * np.sin(theta_incl)
y_be_rot_scaled_incl = x_be_rot_scaled * np.sin(theta_incl) + y_be_rot_scaled * np.cos(theta_incl)

# Trova quanto traslare per portare il LE in x = 0.7
shift_x = 0.9375
x_be_rot_scaled_incl_shifted = -x_be_rot_scaled_incl + shift_x

# Trova quanto traslare per portare il LE in y = 1
shift_y = 0.20- y_be_rot_scaled_incl[0]  # 0.2 è il valore di y del LE del Clark Y
y_be_rot_scaled_incl_shifted = y_be_rot_scaled_incl + shift_y

# Plot
plt.figure(figsize=(10, 4))
plt.plot(x_be_rot_scaled_incl_shifted, y_be_rot_scaled_incl_shifted, '-', label='Be_183-125 ruotato, scalato, inclinato, traslato')
plt.plot(-x_c, y_c, '-', label='Clark Y ruotato + 3°')
plt.title('Be_183-125 ruotato, scalato 70%, inclinato 12°, traslato in x=0.7, y=1 vs Clark Y')
plt.xlabel('x')
plt.ylabel('y')
plt.axis('equal')
plt.grid(True)
plt.legend()
plt.show()

# --- Definizione variabili per salvataggio profili accoppiati ---

# Percorso e nomi file (adatta se necessario)
profili_path = os.path.abspath(os.path.join('..', 'profili'))
selected_file = 'Clark-Y.dat'
selected_file2 = 'Be_183-125.dat'

# Clark Y (profilo principale, ruotato di 180° e 3°)
# Separazione extradosso/intradosso
N = len(x_clark_rot) // 2
x_us_rotated = -x_c[:N]   # upper surface Clark Y
y_us_rotated = y_c[:N]
x_ls_rotated = -x_c[N:]   # lower surface Clark Y
y_ls_rotated = y_c[N:]

# Be_183-125 (flap, ruotato, scalato, inclinato, traslato)
# Qui si assume che il profilo sia già chiuso e ordinato come Clark Y
N_be = len(x_be_rot_scaled_incl_shifted) // 2
x_us_scaled_rot_aligned = x_be_rot_scaled_incl_shifted[:N_be]
y_us_scaled_rot_aligned = y_be_rot_scaled_incl_shifted[:N_be]
x_ls_scaled_rot_aligned = x_be_rot_scaled_incl_shifted[N_be:]
y_ls_scaled_rot_aligned = y_be_rot_scaled_incl_shifted[N_be:]

# --- Salvataggio profili accoppiati ---

simulazioni_path = os.path.join(os.path.dirname(profili_path), 'Simulazioni')
csv_sim_folder = os.path.join(simulazioni_path, "CSV per simulazioni")
os.makedirs(csv_sim_folder, exist_ok=True)

base1 = os.path.splitext(selected_file)[0]
base2 = os.path.splitext(selected_file2)[0]
output_folder = os.path.join(csv_sim_folder, f"{base1}__{base2}")
os.makedirs(output_folder, exist_ok=True)

output_file = os.path.join(output_folder, "profili_accoppiati.txt")
output_csv = os.path.join(output_folder, "profili_accoppiati.csv")

points = []
points_set = set()

# Primo profilo
for xi, yi in zip(np.concatenate([x_us_rotated, x_ls_rotated]), np.concatenate([y_us_rotated, y_ls_rotated])):
    key = (round(xi, 8), round(yi, 8))
    if key not in points_set:
        points.append(f"{xi:.8f},{yi:.8f},0.0")
        points_set.add(key)

# Secondo profilo
for xi, yi in zip(np.concatenate([x_us_scaled_rot_aligned, x_ls_scaled_rot_aligned]), np.concatenate([y_us_scaled_rot_aligned, y_ls_scaled_rot_aligned])):
    key = (round(xi, 8), round(yi, 8))
    if key not in points_set:
        points.append(f"{xi:.8f},{yi:.8f},0.0")
        points_set.add(key)

with open(output_file, "w") as f:
    for line in points:
        f.write(line + "\n")

with open(output_csv, "w", newline='') as f_csv:
    writer = csv.writer(f_csv)
    for line in points:
        x_str, y_str, z_str = line.split(",")
        writer.writerow([x_str, y_str, z_str])

# Cartella di output
simulazioni_path = os.path.join(os.path.dirname(profili_path), 'Simulazioni')
csv_sim_folder = os.path.join(simulazioni_path, "CSV per simulazioni")
os.makedirs(csv_sim_folder, exist_ok=True)

base1 = os.path.splitext(selected_file)[0]
base2 = os.path.splitext(selected_file2)[0]
output_folder = os.path.join(csv_sim_folder, f"{base1}__{base2}")
os.makedirs(output_folder, exist_ok=True)

# --- Esporta solo i punti del Clark Y ---
clark_csv = os.path.join(output_folder, f"{base1}.csv")
clark_points_set = set()
with open(clark_csv, "w", newline='') as f_csv:
    writer = csv.writer(f_csv)
    for xi, yi in zip(np.concatenate([x_us_rotated, x_ls_rotated]), np.concatenate([y_us_rotated, y_ls_rotated])):
        key = (round(xi, 8), round(yi, 8))
        if key not in clark_points_set:
            writer.writerow([f"{xi:.8f}", f"{yi:.8f}", "0.0"])
            clark_points_set.add(key)

# --- Esporta solo i punti del Be_183-125 flap ---
be_csv = os.path.join(output_folder, f"{base2}.csv")
be_points_set = set()
with open(be_csv, "w", newline='') as f_csv:
    writer = csv.writer(f_csv)
    for xi, yi in zip(np.concatenate([x_us_scaled_rot_aligned, x_ls_scaled_rot_aligned]), np.concatenate([y_us_scaled_rot_aligned, y_ls_scaled_rot_aligned])):
        key = (round(xi, 8), round(yi, 8))
        if key not in be_points_set:
            writer.writerow([f"{xi:.8f}", f"{yi:.8f}", "0.0"])
            be_points_set.add(key)

print(f"Punti Clark Y salvati in: {clark_csv}")
print(f"Punti Be_183-125 salvati in: {be_csv}")