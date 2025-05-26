import numpy as np
import os
import matplotlib.pyplot as plt

def calculate_tangent(x, y, idx, window=3):
    start_idx = max(0, idx - window)
    end_idx = min(len(x), idx + window + 1)
    coeffs = np.polyfit(x[start_idx:end_idx], y[start_idx:end_idx], 1)
    return coeffs[0]

def rotate_profile(x, y, angle_rad, center_x=0, center_y=0):
    x = np.asarray(x)
    y = np.asarray(y)
    x_translated = x - center_x
    y_translated = y - center_y
    cos_theta = np.cos(angle_rad)
    sin_theta = np.sin(angle_rad)
    x_rot = x_translated * cos_theta - y_translated * sin_theta
    y_rot = x_translated * sin_theta + y_translated * cos_theta
    return x_rot + center_x, y_rot + center_y

scale_factor = 0.7

profili_path = os.path.join(os.path.dirname(__file__), '..', 'profili')
dat_files = [f for f in os.listdir(profili_path) if f.endswith('.dat')]
if not dat_files:
    print("Nessun file .dat trovato nella cartella 'profili'")
    exit()

# 1. Selezione primo airfoil e angolo di attacco
print("Profili disponibili:")
for idx, fname in enumerate(dat_files):
    print(f"{idx}: {fname}")
idx1 = int(input("Seleziona il numero del primo profilo: "))
file1 = dat_files[idx1]
alpha_deg = float(input("Inserisci l'angolo di attacco (in gradi) per il primo profilo: "))
alpha_rad = np.deg2rad(alpha_deg)

data1 = np.loadtxt(os.path.join(profili_path, file1))
x, y = data1[:, 0], data1[:, 1]
idx_min = np.argmin(x)
x_us, y_us = x[:idx_min], y[:idx_min]
x_ls, y_ls = x[idx_min:], y[idx_min:]

x_rotated_orig, y_rotated_orig = rotate_profile(x, y, alpha_rad)
te_x, te_y = x_rotated_orig[0], y_rotated_orig[0]
x_us_rotated, y_us_rotated = rotate_profile(x_us, y_us, alpha_rad)
x_ls_rotated, y_ls_rotated = rotate_profile(x_ls, y_ls, alpha_rad)

results = []

# Crea la cartella "Accoppiamenti" se non esiste
output_dir = os.path.join(os.path.dirname(__file__), 'Accoppiamenti')
os.makedirs(output_dir, exist_ok=True)

# Crea una sottocartella per il primo flap selezionato
first_flap_name = os.path.splitext(file1)[0]
subfolder = os.path.join(output_dir, first_flap_name)
os.makedirs(subfolder, exist_ok=True)

# File di testo per i risultati numerici, chiamato come il primo flap
txt_path = os.path.join(subfolder, f"{first_flap_name}.txt")
# Svuota il file all'inizio della selezione
open(txt_path, 'w').close()

for j, file2 in enumerate(dat_files):

    data2 = np.loadtxt(os.path.join(profili_path, file2))
    x2, y2 = data2[:, 0], data2[:, 1]
    idx_min2 = np.argmin(x2)
    x_us2, y_us2 = x2[:idx_min2], y2[:idx_min2]
    x_ls2, y_ls2 = x2[idx_min2:], y2[idx_min2:]

    x_us_scaled = x_us2 * scale_factor
    y_us_scaled = y_us2 * scale_factor
    x_ls_scaled = x_ls2 * scale_factor
    y_ls_scaled = y_ls2 * scale_factor

    # Punto di riferimento a x = 20% della corda sul profilo scalato
    x_ref_point_scaled = 0.2 * scale_factor
    idx_us_ref_scaled = np.argmin(np.abs(x_us_scaled - x_ref_point_scaled))
    x_us_ref_scaled = x_us_scaled[idx_us_ref_scaled]
    y_us_ref_scaled = y_us_scaled[idx_us_ref_scaled]

    slope_at_us_ref_scaled = calculate_tangent(x_us_scaled, y_us_scaled, idx_us_ref_scaled)

    idx_te_us_rot = np.argmax(x_us_rotated)
    slope_at_us_te_rotated = calculate_tangent(x_us_rotated, y_us_rotated, idx_te_us_rot)
    x_te_us_rot = x_us_rotated[idx_te_us_rot]
    y_te_us_rot = y_us_rotated[idx_te_us_rot]

    angle_ref_scaled = np.arctan(slope_at_us_ref_scaled)
    angle_te_us_rotated = np.arctan(slope_at_us_te_rotated)
    angle_between = angle_te_us_rotated - angle_ref_scaled

    x_us_scaled_rot, y_us_scaled_rot = rotate_profile(x_us_scaled, y_us_scaled, angle_between)
    x_ls_scaled_rot, y_ls_scaled_rot = rotate_profile(x_ls_scaled, y_ls_scaled, angle_between)
    x_us_ref_scaled_rot, y_us_ref_scaled_rot = rotate_profile([x_us_ref_scaled], [y_us_ref_scaled], angle_between)

    y_translation = y_te_us_rot - y_us_ref_scaled_rot[0]
    y_us_scaled_rot_aligned = y_us_scaled_rot + y_translation
    y_ls_scaled_rot_aligned = y_ls_scaled_rot + y_translation
    x_us_scaled_rot_aligned = x_us_scaled_rot
    x_ls_scaled_rot_aligned = x_ls_scaled_rot
    x_us_ref_scaled_rot_aligned = x_us_ref_scaled_rot[0]

    delta_x = x_te_us_rot - x_us_ref_scaled_rot[0]
    x_us_scaled_rot_aligned = x_us_scaled_rot_aligned + delta_x
    x_ls_scaled_rot_aligned = x_ls_scaled_rot_aligned + delta_x
    x_us_ref_scaled_rot_aligned = x_us_ref_scaled_rot_aligned + delta_x

    offset_y = -0.03
    y_us_scaled_rot_aligned = y_us_scaled_rot_aligned + offset_y
    y_ls_scaled_rot_aligned = y_ls_scaled_rot_aligned + offset_y

    if len(x_us_scaled_rot_aligned) == len(x_ls_scaled_rot_aligned):
        x_camber = (x_us_scaled_rot_aligned + x_ls_scaled_rot_aligned[::-1]) / 2
        y_camber = (y_us_scaled_rot_aligned + y_ls_scaled_rot_aligned[::-1]) / 2
    else:
        from scipy.interpolate import interp1d
        f_x_ls = interp1d(np.linspace(0,1,len(x_ls_scaled_rot_aligned)), x_ls_scaled_rot_aligned, kind='linear')
        f_y_ls = interp1d(np.linspace(0,1,len(y_ls_scaled_rot_aligned)), y_ls_scaled_rot_aligned, kind='linear')
        s_us = np.linspace(0,1,len(x_us_scaled_rot_aligned))
        x_ls_interp = f_x_ls(s_us)
        y_ls_interp = f_y_ls(s_us)
        x_camber = (x_us_scaled_rot_aligned + x_ls_interp[::-1]) / 2
        y_camber = (y_us_scaled_rot_aligned + y_ls_interp[::-1]) / 2

    idx_te_camber = np.argmax(x_camber)
    if idx_te_camber == 0:
        idx_next = 1
    else:
        idx_next = idx_te_camber - 1

    dx = x_camber[idx_te_camber] - x_camber[idx_next]
    dy = y_camber[idx_te_camber] - y_camber[idx_next]
    slope_te_camber = dy / dx if dx != 0 else np.sign(dy) * 1e6

    angle_te_camber_rad = np.arctan(slope_te_camber)
    angle_te_camber_deg = np.degrees(angle_te_camber_rad)

    print(f"{file1} + {file2}: angolo deviazione = {angle_te_camber_deg:.2f}°, angolo attacco = {alpha_deg:.2f}°")

    # --- Salva il risultato numerico su file (CSV style) ---
    with open(txt_path, 'a') as ftxt:
        ftxt.write(f"{file1},{file2},{angle_te_camber_deg:.2f},{alpha_deg:.2f}\n")

    # --- Plot dei due profili e delle tangenti ---
    plt.figure(figsize=(10, 6))
    plt.plot(
        np.concatenate([x_us_rotated, x_ls_rotated]),
        np.concatenate([y_us_rotated, y_ls_rotated]),
        'b-', label='Primo flap'
    )
    plt.plot(
        np.concatenate([x_us_scaled_rot_aligned, x_ls_scaled_rot_aligned]),
        np.concatenate([y_us_scaled_rot_aligned, y_ls_scaled_rot_aligned]),
        'c-', label='Secondo Flap'
    )
    plt.plot(x_us_ref_scaled_rot_aligned, y_us_scaled_rot_aligned[idx_us_ref_scaled], 'mo', label='Punto a 20% corda')
    plt.plot(x_camber, y_camber, 'g-', label='Linea media flap 2')
    plt.axis('equal')
    plt.grid(True)
    plt.xlabel('x/c')
    plt.ylabel('y/c')
    plt.title(f'{file1} + {file2}\nAngolo deviazione: {angle_te_camber_deg:.2f}°, Angolo attacco: {alpha_deg:.2f}°')
    plt.legend(bbox_to_anchor=(1.05, 1), loc='upper left')
    plt.tight_layout()

    # Salva il plot nella sottocartella del primo flap
    plot_filename = f"{os.path.splitext(file1)[0]}_{os.path.splitext(file2)[0]}.png"
    plot_path = os.path.join(subfolder, plot_filename)
    plt.savefig(plot_path)
    plt.close()  # Chiude la figura per non saturare la memoria