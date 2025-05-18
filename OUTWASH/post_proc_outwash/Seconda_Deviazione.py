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

# 3. Trova il punto al 40% della corda sul dorso
target_x = 0.4  # 40% della corda
idx_40 = np.argmin(np.abs(x_us - target_x))
x_40 = x_us[idx_40]
y_40 = y_us[idx_40]

# 3. Trova il punto al 40% della corda sul dorso del profilo scalato
target_x_scaled = 0.4 * scale_factor  # 40% della corda scalata
idx_40_scaled = np.argmin(np.abs(x_us_scaled - target_x_scaled))
x_40_scaled = x_us_scaled[idx_40_scaled]
y_40_scaled = y_us_scaled[idx_40_scaled]

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
slope_original = calculate_tangent(x_us, y_us, idx_40)
slope_scaled = calculate_tangent(x_us_scaled, y_us_scaled, idx_40_scaled)

# Crea punti per disegnare le linee tangenti
def plot_tangent_line(x_point, y_point, slope, length=0.1):
    x_line = np.array([x_point - length/2, x_point + length/2])
    y_line = slope * (x_line - x_point) + y_point
    return x_line, y_line

# Crea le linee tangenti
x_tangent_orig, y_tangent_orig = plot_tangent_line(x_40, y_40, slope_original)
x_tangent_scaled, y_tangent_scaled = plot_tangent_line(x_40_scaled, y_40_scaled, slope_scaled)

# Calcola l'angolo tra le tangenti (in radianti)
tangent_angle_original = np.arctan(slope_original)
tangent_angle_scaled = np.arctan(slope_scaled)
# L'angolo di rotazione necessario è la differenza tra gli angoli delle tangenti
rotation_angle_rad = tangent_angle_original - tangent_angle_scaled
rotation_angle_deg = np.rad2deg(rotation_angle_rad)

# Rimuovi l'input dell'utente per l'angolo di rotazione
# rotation_angle_deg = float(input("\nInserisci l'angolo di rotazione del profilo scalato (in gradi): "))
# rotation_angle_rad = np.deg2rad(rotation_angle_deg)

# Funzione per ruotare il profilo
def rotate_profile(x, y, angle_rad, center_x=0, center_y=0):
    # Trasla al centro di rotazione
    x_translated = x - center_x
    y_translated = y - center_y
    
    # Matrice di rotazione
    cos_theta = np.cos(angle_rad)
    sin_theta = np.sin(angle_rad)
    
    # Applica la rotazione
    x_rot = x_translated * cos_theta - y_translated * sin_theta
    y_rot = x_translated * sin_theta + y_translated * cos_theta
    
    # Trasla indietro
    x_rot = x_rot + center_x
    y_rot = y_rot + center_y
    
    return x_rot, y_rot

# Chiedi all'utente l'angolo di attacco per il profilo originale
while True:
    try:
        alpha_deg = float(input("\nInserisci l'angolo di attacco del profilo originale (in gradi): "))
        alpha_rad = np.deg2rad(alpha_deg)
        break
    except ValueError:
        print("Inserisci un numero valido.")

# Chiedi all'utente l'angolo di deflessione per il profilo scalato
while True:
    try:
        rotation_angle_deg = float(input("\nInserisci l'angolo di deflessione del profilo scalato (in gradi): "))
        rotation_angle_rad = np.deg2rad(rotation_angle_deg)
        break
    except ValueError:
        print("Inserisci un numero valido.")

# Ruota il profilo originale
x_rotated_orig, y_rotated_orig = rotate_profile(x, y, alpha_rad)

# Trova il trailing edge del profilo originale ruotato
te_x = x_rotated_orig[0]  
te_y = y_rotated_orig[0]

# Trova il punto al 20% della corda sul dorso
target_x = 0.2  # 20% della corda
idx_20 = np.argmin(np.abs(x_us - target_x))
x_20 = x_us[idx_20]
y_20 = y_us[idx_20]

# Trova il punto al 20% della corda sul dorso del profilo scalato
target_x_scaled = 0.2 * scale_factor  # 20% della corda scalata
idx_20_scaled = np.argmin(np.abs(x_us_scaled - target_x_scaled))
x_20_scaled = x_us_scaled[idx_20_scaled]
y_20_scaled = y_us_scaled[idx_20_scaled]

# Posiziona il profilo scalato
x_us_shifted = x_us_scaled + te_x + 0.1
y_us_shifted = y_us_scaled + te_y
x_ls_shifted = x_ls_scaled + te_x + 0.1
y_ls_shifted = y_ls_scaled + te_y

# Trova il punto al 20% del profilo scalato traslato
x_20_shifted = x_20_scaled + te_x + 0.1
y_20_shifted = y_20_scaled + te_y

# Calcola la tangente al trailing edge del profilo originale RUOTATO
idx_te = 0  
slope_original = calculate_tangent(x_rotated_orig, y_rotated_orig, idx_te)

# Calcola la tangente al 20% del profilo scalato traslato
idx_20_shifted = np.argmin(np.abs(x_us_shifted - x_20_shifted))
slope_scaled = calculate_tangent(x_us_shifted, y_us_shifted, idx_20_shifted)

# Calcola l'angolo tra le tangenti
tangent_angle_original = np.arctan(slope_original)
tangent_angle_scaled = np.arctan(slope_scaled)
rotation_angle_rad = tangent_angle_original - tangent_angle_scaled

# Ruota il profilo scalato attorno al punto al 20%
x_us_final, y_us_final = rotate_profile(x_us_shifted, y_us_shifted, rotation_angle_rad,
                                      center_x=x_20_shifted, center_y=y_20_shifted)
x_ls_final, y_ls_final = rotate_profile(x_ls_shifted, y_ls_shifted, rotation_angle_rad,
                                      center_x=x_20_shifted, center_y=y_20_shifted)

# Trova il punto al 95% della corda sul profilo scalato e traslato
target_x_rot = 0.95 * scale_factor  # 95% della corda scalata
idx_95_scaled = np.argmin(np.abs(x_us_scaled - target_x_rot))
x_95_scaled = x_us_scaled[idx_95_scaled] + te_x + 0.1  # Use te_x + 0.1 instead of offset_x
y_95_scaled = y_us_scaled[idx_95_scaled] + te_y        # Use te_y instead of offset_y

# Ruota il profilo scalato e traslato attorno al punto al 95%
x_us_final, y_us_final = rotate_profile(x_us_shifted, y_us_shifted, rotation_angle_rad, 
                                      center_x=x_95_scaled, center_y=y_95_scaled)
x_ls_final, y_ls_final = rotate_profile(x_ls_shifted, y_ls_shifted, rotation_angle_rad,
                                      center_x=x_95_scaled, center_y=y_95_scaled)

# Trova il punto al 20% della corda sul dorso
target_x = 0.2  # 20% della corda
idx_20 = np.argmin(np.abs(x_us - target_x))
x_20 = x_us[idx_20]
y_20 = y_us[idx_20]

# Trova il punto al 20% della corda sul dorso del profilo scalato
target_x_scaled = 0.2 * scale_factor  # 20% della corda scalata
idx_20_scaled = np.argmin(np.abs(x_us_scaled - target_x_scaled))
x_20_scaled = x_us_scaled[idx_20_scaled]
y_20_scaled = y_us_scaled[idx_20_scaled]

# Calcola la tangente al trailing edge del profilo originale
idx_te = 0  # Il TE è il primo punto
slope_original = calculate_tangent(x_rotated_orig, y_rotated_orig, idx_te)

# Calcola la tangente al 20% del profilo scalato
slope_scaled = calculate_tangent(x_us_scaled, y_us_scaled, idx_20_scaled)

# Calcola l'angolo tra le tangenti
tangent_angle_original = np.arctan(slope_original)
tangent_angle_scaled = np.arctan(slope_scaled)
rotation_angle_rad = tangent_angle_original - tangent_angle_scaled

# Posiziona il profilo scalato
x_us_shifted = x_us_scaled + te_x + 0.1
y_us_shifted = y_us_scaled + te_y
x_ls_shifted = x_ls_scaled + te_x + 0.1
y_ls_shifted = y_ls_scaled + te_y

# Trova il punto al 20% del profilo scalato traslato
x_20_shifted = x_20_scaled + te_x + 0.1
y_20_shifted = y_20_scaled + te_y

# Ruota il profilo scalato attorno al punto al 20%
x_us_final, y_us_final = rotate_profile(x_us_shifted, y_us_shifted, rotation_angle_rad,
                                      center_x=x_20_shifted, center_y=y_20_shifted)
x_ls_final, y_ls_final = rotate_profile(x_ls_shifted, y_ls_shifted, rotation_angle_rad,
                                      center_x=x_20_shifted, center_y=y_20_shifted)

# Calcola la tangente al 20% del profilo scalato dopo la rotazione
idx_20_final = np.argmin(np.abs(x_us_final - x_20_shifted))
slope_scaled = calculate_tangent(x_us_final, y_us_final, idx_20_final)

# Plot con tangenti
plt.figure(figsize=(12, 8))
plt.plot(x_rotated_orig, y_rotated_orig, 'b-', label=f'Profilo originale (α={alpha_deg}°)')
plt.plot(x_us_final, y_us_final, 'r--', label=f'Profilo scalato e ruotato ({rotation_angle_deg:.1f}°)')
plt.plot(x_ls_final, y_ls_final, 'r--')

# Calcola le tangenti per il plot
slope_original = calculate_tangent(x_rotated_orig, y_rotated_orig, 0)  # tangente al TE
x_tangent_orig, y_tangent_orig = plot_tangent_line(te_x, te_y, slope_original, length=0.2)

# Usa la stessa pendenza per la tangente del profilo scalato
x_tangent_scaled, y_tangent_scaled = plot_tangent_line(x_20_shifted, y_20_shifted, slope_original, length=0.2)

# Plot delle tangenti
plt.plot(x_tangent_orig, y_tangent_orig, 'g-', label='Tangente TE originale')
plt.plot(x_tangent_scaled, y_tangent_scaled, 'm-', label='Tangente 20% scalato')
plt.plot(x_20_shifted, y_20_shifted, 'ko', label='Punto di rotazione (0.2c)')

plt.axis('equal')
plt.grid(True)
plt.legend()
plt.title('Configurazione dei profili')
plt.xlabel('x/c')
plt.ylabel('y/c')



plt.show()

