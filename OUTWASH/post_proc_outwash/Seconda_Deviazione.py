import numpy as np
import matplotlib.pyplot as plt
import os

# Modifica il percorso per puntare alla cartella profili nel livello superiore
profili_path = os.path.join(os.path.dirname(__file__), '..', 'profili')

# Crea la cartella se non esiste
if not os.path.exists(profili_path):
    os.makedirs(profili_path)
    print(f"Cartella 'profili' creata in: {profili_path}")
    print("Inserisci i tuoi file .dat nella cartella appena creata")
    exit()

# Lista tutti i file .dat nella cartella profili
dat_files = [f for f in os.listdir(profili_path) if f.endswith('.dat')]

if not dat_files:
    print("Nessun file .dat trovato nella cartella 'profili'")
    exit()

# Puoi scegliere il file da analizzare
print("File disponibili:")
for i, file in enumerate(dat_files):
    print(f"{i+1}. {file}")

# Chiedi all'utente quale file vuole analizzare
while True:
    try:
        selection = int(input("\nSeleziona il numero del file da analizzare: ")) - 1
        if 0 <= selection < len(dat_files):
            selected_file = dat_files[selection]
            break
        else:
            print("Selezione non valida. Riprova.")
    except ValueError:
        print("Inserisci un numero valido.")

# Carica il profilo selezionato
data = np.loadtxt(os.path.join(profili_path, selected_file))

# 1. Carica il profilo e dividilo in upper e lower
x = data[:, 0]
y = data[:, 1]

idx_min = np.argmin(x)
x_us = x[:idx_min]
y_us = y[:idx_min]
x_ls = x[idx_min:]
y_ls = y[idx_min:]

# 2. Scala il profilo del 70%
scale_factor = 0.7
x_us_scaled = x_us * scale_factor
y_us_scaled = y_us * scale_factor
x_ls_scaled = x_ls * scale_factor
y_ls_scaled = y_ls * scale_factor

# 3. Trova il punto al 20% della corda sul dorso
target_x = 0.2  # 20% della corda
idx_20 = np.argmin(np.abs(x_us - target_x))
x_20 = x_us[idx_20]
y_20 = y_us[idx_20]

# 3. Trova il punto al 20% della corda sul dorso del profilo scalato
target_x_scaled = 0.2 * scale_factor  # 20% della corda scalata
idx_20_scaled = np.argmin(np.abs(x_us_scaled - target_x_scaled))
x_20_scaled = x_us_scaled[idx_20_scaled]
y_20_scaled = y_us_scaled[idx_20_scaled]

# Calcola le tangenti nei punti al 20%
def calculate_tangent(x, y, idx, window=3):
    # Prendi alcuni punti intorno al punto di interesse
    start_idx = max(0, idx - window)
    end_idx = min(len(x), idx + window + 1)
    
    # Calcola la pendenza usando polyfit
    coeffs = np.polyfit(x[start_idx:end_idx], y[start_idx:end_idx], 1)
    slope = coeffs[0]
    
    return slope

# Calcola le pendenze
slope_original = calculate_tangent(x_us, y_us, idx_20)
slope_scaled = calculate_tangent(x_us_scaled, y_us_scaled, idx_20_scaled)

# Crea punti per disegnare le linee tangenti
def plot_tangent_line(x_point, y_point, slope, length=0.1):
    x_line = np.array([x_point - length/2, x_point + length/2])
    y_line = slope * (x_line - x_point) + y_point
    return x_line, y_line

# Crea le linee tangenti
x_tangent_orig, y_tangent_orig = plot_tangent_line(x_20, y_20, slope_original)
x_tangent_scaled, y_tangent_scaled = plot_tangent_line(x_20_scaled, y_20_scaled, slope_scaled)

# Chiedi l'angolo di rotazione
rotation_angle_deg = float(input("\nInserisci l'angolo di rotazione (in gradi): "))
rotation_angle_rad = np.deg2rad(rotation_angle_deg)

# Funzione per ruotare il profilo
def rotate_profile(x, y, angle_rad):
    # Matrice di rotazione
    cos_theta = np.cos(angle_rad)
    sin_theta = np.sin(angle_rad)
    
    # Applica la rotazione
    x_rot = x * cos_theta - y * sin_theta
    y_rot = x * sin_theta + y * cos_theta
    
    return x_rot, y_rot

# Ruota il profilo scalato
x_us_rotated, y_us_rotated = rotate_profile(x_us_scaled, y_us_scaled, rotation_angle_rad)
x_ls_rotated, y_ls_rotated = rotate_profile(x_ls_scaled, y_ls_scaled, rotation_angle_rad)

# Trova il punto al 20% della corda sul profilo ruotato
target_x_rotated = target_x_scaled * np.cos(rotation_angle_rad)
idx_20_rotated = np.argmin(np.abs(x_us_rotated - target_x_rotated))
x_20_rotated = x_us_rotated[idx_20_rotated]
y_20_rotated = y_us_rotated[idx_20_rotated]

# Calcola la tangente nel punto ruotato
slope_rotated = calculate_tangent(x_us_rotated, y_us_rotated, idx_20_rotated)
x_tangent_rot, y_tangent_rot = plot_tangent_line(x_20_rotated, y_20_rotated, slope_rotated)

# Aggiorna il plot per includere il profilo ruotato
plt.figure(figsize=(10, 6))
plt.plot(x_us, y_us, 'b-', label='Profilo originale')
plt.plot(x_ls, y_ls, 'b-')
plt.plot(x_us_scaled, y_us_scaled, 'r--', label='Profilo scalato')
plt.plot(x_ls_scaled, y_ls_scaled, 'r--')
plt.plot(x_us_rotated, y_us_rotated, 'g--', label='Profilo ruotato')
plt.plot(x_ls_rotated, y_ls_rotated, 'g--')
plt.plot(x_20, y_20, 'bo', label='Punto 20% originale', markersize=10)
plt.plot(x_20_scaled, y_20_scaled, 'ro', label='Punto 20% scalato', markersize=10)
plt.plot(x_20_rotated, y_20_rotated, 'go', label='Punto 20% ruotato', markersize=10)
plt.plot(x_tangent_orig, y_tangent_orig, 'b-', linewidth=2, label='Tangente originale')
plt.plot(x_tangent_scaled, y_tangent_scaled, 'r-', linewidth=2, label='Tangente scalata')
plt.plot(x_tangent_rot, y_tangent_rot, 'g-', linewidth=2, label='Tangente ruotata')

plt.axis('equal')
plt.grid(True)
plt.legend()
plt.title(f'Confronto profili - {selected_file}')

# Stampa le coordinate del punto
print(f"\nPunto al 20% della corda:")
print(f"x = {x_20:.4f}")
print(f"y = {y_20:.4f}")

# Stampa le coordinate del punto scalato
print(f"\nPunto al 20% della corda del profilo scalato:")
print(f"x = {x_20_scaled:.4f}")
print(f"y = {y_20_scaled:.4f}")

# Stampa le pendenze delle tangenti
print(f"\nPendenza della tangente nel punto originale: {slope_original:.4f}")
print(f"Pendenza della tangente nel punto scalato: {slope_scaled:.4f}")

# Stampa le pendenze
print(f"\nPendenza della tangente nel punto ruotato: {slope_rotated:.4f}")
print(f"Angolo di rotazione applicato: {rotation_angle_deg:.2f} gradi")

plt.show()

