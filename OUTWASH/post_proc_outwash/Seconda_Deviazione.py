import numpy as np
import matplotlib.pyplot as plt
import os

# --- Caricamento file profilo ---
profili_path = os.path.join(os.path.dirname(__file__), '..', 'profili')
if not os.path.exists(profili_path):
    os.makedirs(profili_path)
    print(f"Cartella 'profili' creata in: {profili_path}")
    print("Inserisci i tuoi file .dat nella cartella appena creata")
    exit()
dat_files = [f for f in os.listdir(profili_path) if f.endswith('.dat')]
if not dat_files:
    print("Nessun file .dat trovato nella cartella 'profili'")
    exit()
print("File disponibili:")
for i, file in enumerate(dat_files):
    print(f"{i+1}. {file}")
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

data = np.loadtxt(os.path.join(profili_path, selected_file))
x = data[:, 0]
y = data[:, 1]

# --- Divisione upper/lower e scaling ---
idx_min = np.argmin(x)
x_us = x[:idx_min]
y_us = y[:idx_min]
x_ls = x[idx_min:]
y_ls = y[idx_min:]

scale_factor = 0.7
x_us_scaled = x_us * scale_factor
y_us_scaled = y_us * scale_factor
x_ls_scaled = x_ls * scale_factor
y_ls_scaled = y_ls * scale_factor

# --- Funzioni di utilità ---
def calculate_tangent(x, y, idx, window=3):
    start_idx = max(0, idx - window)
    end_idx = min(len(x), idx + window + 1)
    coeffs = np.polyfit(x[start_idx:end_idx], y[start_idx:end_idx], 1)
    return coeffs[0]

def plot_tangent_line(x_point, y_point, slope, length=0.1):
    x_line = np.array([x_point - length/2, x_point + length/2])
    y_line = slope * (x_line - x_point) + y_point
    return x_line, y_line

def rotate_profile(x, y, angle_rad, center_x=0, center_y=0):
    x_translated = x - center_x
    y_translated = y - center_y
    cos_theta = np.cos(angle_rad)
    sin_theta = np.sin(angle_rad)
    x_rot = x_translated * cos_theta - y_translated * sin_theta
    y_rot = x_translated * sin_theta + y_translated * cos_theta
    return x_rot + center_x, y_rot + center_y

# --- Rotazione profilo originale ---
while True:
    try:
        alpha_deg = float(input("\nInserisci l'angolo di attacco del profilo originale (in gradi): "))
        alpha_rad = np.deg2rad(alpha_deg)
        break
    except ValueError:
        print("Inserisci un numero valido.")

x_rotated_orig, y_rotated_orig = rotate_profile(x, y, alpha_rad)
te_x = x_rotated_orig[0]
te_y = y_rotated_orig[0]

# --- Trova punto al 20% della corda ---
target_x = 0.2
idx_20 = np.argmin(np.abs(x_us - target_x))
x_20 = x_us[idx_20]
y_20 = y_us[idx_20]

target_x_scaled = 0.2 * scale_factor
idx_20_scaled = np.argmin(np.abs(x_us_scaled - target_x_scaled))
x_20_scaled = x_us_scaled[idx_20_scaled]
y_20_scaled = y_us_scaled[idx_20_scaled]

# --- Traslazione orizzontale fissa ---
x_us_shifted = x_us_scaled + te_x + 0.1
y_us_shifted = y_us_scaled + te_y
x_ls_shifted = x_ls_scaled + te_x + 0.1
y_ls_shifted = y_ls_scaled + te_y

x_20_shifted = x_20_scaled + te_x + 0.1
y_20_shifted = y_20_scaled + te_y

# --- Calcolo tangenti e rotazione profilo scalato ---
slope_original = calculate_tangent(x_rotated_orig, y_rotated_orig, 0)
slope_scaled = calculate_tangent(x_us_shifted, y_us_shifted, np.argmin(np.abs(x_us_shifted - x_20_shifted)))
tangent_angle_original = np.arctan(slope_original)
tangent_angle_scaled = np.arctan(slope_scaled)
rotation_angle_rad = tangent_angle_original - tangent_angle_scaled
rotation_angle_deg = np.rad2deg(rotation_angle_rad)

x_us_final, y_us_final = rotate_profile(x_us_shifted, y_us_shifted, rotation_angle_rad, center_x=x_20_shifted, center_y=y_20_shifted)
x_ls_final, y_ls_final = rotate_profile(x_ls_shifted, y_ls_shifted, rotation_angle_rad, center_x=x_20_shifted, center_y=y_20_shifted)

# --- Allineamento verticale delle tangenti ---
y_tangente_orig_su_20 = slope_original * (x_20_shifted - te_x) + te_y
idx_20_final = np.argmin(np.abs(x_us_final - x_20_shifted))
y_20_final = y_us_final[idx_20_final]
delta_y = y_tangente_orig_su_20 - y_20_final
y_us_final = y_us_final + delta_y
y_ls_final = y_ls_final + delta_y

# --- Distanza obliqua fissa tra TE originale e 20% profilo scalato ---
d_obliqua = -0.2 * scale_factor  # 0.3 della corda del secondo profilo

# --- Calcola la posizione iniziale del punto 20% profilo scalato rispetto al TE originale ---
# (prima di traslare)
vec_x = x_20_scaled - te_x
vec_y = y_20_scaled - te_y
vec_norm = np.sqrt(vec_x**2 + vec_y**2)

# --- Calcola il vettore di traslazione necessario ---
if vec_norm == 0:
    unit_x, unit_y = 1, 0  # fallback
else:
    unit_x = vec_x / vec_norm
    unit_y = vec_y / vec_norm

# Offset da applicare per ottenere la distanza desiderata
offset_x = te_x + unit_x * d_obliqua - x_20_scaled
offset_y = te_y + unit_y * d_obliqua - y_20_scaled

# Applica la traslazione a tutto il profilo scalato
x_us_shifted = x_us_scaled + offset_x
y_us_shifted = y_us_scaled + offset_y
x_ls_shifted = x_ls_scaled + offset_x
y_ls_shifted = y_ls_scaled + offset_y

# Trova il nuovo punto al 20% dopo la traslazione
x_20_shifted = x_20_scaled + offset_x
y_20_shifted = y_20_scaled + offset_y

# --- Calcolo tangenti e rotazione profilo scalato (dopo traslazione obliqua) ---
slope_original = calculate_tangent(x_rotated_orig, y_rotated_orig, 0)
slope_scaled = calculate_tangent(x_us_shifted, y_us_shifted, np.argmin(np.abs(x_us_shifted - x_20_shifted)))
tangent_angle_original = np.arctan(slope_original)
tangent_angle_scaled = np.arctan(slope_scaled)
rotation_angle_rad = tangent_angle_original - tangent_angle_scaled
rotation_angle_deg = np.rad2deg(rotation_angle_rad)

x_us_final, y_us_final = rotate_profile(x_us_shifted, y_us_shifted, rotation_angle_rad, center_x=x_20_shifted, center_y=y_20_shifted)
x_ls_final, y_ls_final = rotate_profile(x_ls_shifted, y_ls_shifted, rotation_angle_rad, center_x=x_20_shifted, center_y=y_20_shifted)

# --- Allineamento verticale delle tangenti (dopo traslazione obliqua) ---
y_tangente_orig_su_20 = slope_original * (x_20_shifted - te_x) + te_y
idx_20_final = np.argmin(np.abs(x_us_final - x_20_shifted))
y_20_final = y_us_final[idx_20_final]
delta_y = y_tangente_orig_su_20 - y_20_final
y_us_final = y_us_final + delta_y
y_ls_final = y_ls_final + delta_y

# --- Plot finale ---
plt.figure(figsize=(12, 8))
plt.plot(x_rotated_orig, y_rotated_orig, 'b-', label=f'Profilo originale (α={alpha_deg}°)')
plt.plot(x_us_final, y_us_final, 'r--', label=f'Profilo scalato e ruotato')
plt.plot(x_ls_final, y_ls_final, 'r--')

x_tangent_orig, y_tangent_orig = plot_tangent_line(te_x, te_y, slope_original, length=0.2)
x_tangent_scaled, y_tangent_scaled = plot_tangent_line(x_20_shifted, y_tangente_orig_su_20, slope_original, length=0.2)
plt.plot(x_tangent_orig, y_tangent_orig, 'g-', label='Tangente TE originale')
plt.plot(x_tangent_scaled, y_tangent_scaled, 'm-', label='Tangente 20% scalato')
plt.plot(x_20_shifted, y_tangente_orig_su_20, 'ko', label='Punto di rotazione (0.2c)')

plt.axis('equal')
plt.grid(True)
plt.legend()
plt.title('Configurazione dei profili')
plt.xlabel('x/c')
plt.ylabel('y/c')
plt.show()

