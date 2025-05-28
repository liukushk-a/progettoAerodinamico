import numpy as np
import os
import csv

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

# --- Caricamento file profilo ---
profili_path = os.path.join(os.path.dirname(__file__), '..', 'profili')
dat_files = [f for f in os.listdir(profili_path) if f.endswith('.dat')]
if not dat_files:
    print("Nessun file .dat trovato nella cartella 'profili'")
    exit()

# Angoli di attacco per i profili "davanti" (in ordine, in gradi)
alpha_deg_list = [-7.86, -6.05, -9.22, -4.96, -5.87, -6.68, -1.13, -2.9, -2.96, -2.07, -1.96]

results = []

for i, file1 in enumerate(dat_files):
    data1 = np.loadtxt(os.path.join(profili_path, file1))
    x, y = data1[:, 0], data1[:, 1]
    idx_min = np.argmin(x)
    x_us, y_us = x[:idx_min], y[:idx_min]
    x_ls, y_ls = x[idx_min:], y[idx_min:]

    # Usa l'angolo di attacco specifico per questo profilo
    if i < len(alpha_deg_list):
        alpha_deg = alpha_deg_list[i]
    else:
        alpha_deg = 0.0  # fallback se ci sono più file che angoli
    alpha_rad = np.deg2rad(alpha_deg)

    for j, file2 in enumerate(dat_files):
        data2 = np.loadtxt(os.path.join(profili_path, file2))
        x2, y2 = data2[:, 0], data2[:, 1]
        idx_min2 = np.argmin(x2)
        x_us2, y_us2 = x2[:idx_min2], y2[:idx_min2]
        x_ls2, y_ls2 = x2[idx_min2:], y2[idx_min2:]

        # Profilo 2 scalato
        x_us_scaled = x_us2 * scale_factor
        y_us_scaled = y_us2 * scale_factor
        x_ls_scaled = x_ls2 * scale_factor
        y_ls_scaled = y_ls2 * scale_factor

        # Rotazione profilo originale
        x_rotated_orig, y_rotated_orig = rotate_profile(x, y, alpha_rad)
        te_x, te_y = x_rotated_orig[0], y_rotated_orig[0]
        x_us_rotated, y_us_rotated = rotate_profile(x_us, y_us, alpha_rad)
        x_ls_rotated, y_ls_rotated = rotate_profile(x_ls, y_ls, alpha_rad)

        # Punto di riferimento a x = 20% della corda sul profilo scalato
        x_ref_point_scaled = 0.2 * scale_factor
        idx_us_ref_scaled = np.argmin(np.abs(x_us_scaled - x_ref_point_scaled))
        x_us_ref_scaled = x_us_scaled[idx_us_ref_scaled]
        y_us_ref_scaled = y_us_scaled[idx_us_ref_scaled]

        # Tangente nel punto di riferimento sul profilo scalato
        slope_at_us_ref_scaled = calculate_tangent(x_us_scaled, y_us_scaled, idx_us_ref_scaled)

        # Tangente al dorso al TE del profilo originale ruotato
        idx_te_us_rot = np.argmax(x_us_rotated)
        slope_at_us_te_rotated = calculate_tangent(x_us_rotated, y_us_rotated, idx_te_us_rot)
        x_te_us_rot = x_us_rotated[idx_te_us_rot]
        y_te_us_rot = y_us_rotated[idx_te_us_rot]

        # Rendi le tangenti parallele
        angle_ref_scaled = np.arctan(slope_at_us_ref_scaled)
        angle_te_us_rotated = np.arctan(slope_at_us_te_rotated)
        angle_between = angle_te_us_rotated - angle_ref_scaled

        # Ruota tutto il profilo scalato
        x_us_scaled_rot, y_us_scaled_rot = rotate_profile(x_us_scaled, y_us_scaled, angle_between)
        x_ls_scaled_rot, y_ls_scaled_rot = rotate_profile(x_ls_scaled, y_ls_scaled, angle_between)
        x_us_ref_scaled_rot, y_us_ref_scaled_rot = rotate_profile([x_us_ref_scaled], [y_us_ref_scaled], angle_between)

        y_translation = y_te_us_rot - y_us_ref_scaled_rot[0]
        y_us_scaled_rot_aligned = y_us_scaled_rot + y_translation
        y_ls_scaled_rot_aligned = y_ls_scaled_rot + y_translation
        x_us_scaled_rot_aligned = x_us_scaled_rot
        x_ls_scaled_rot_aligned = x_ls_scaled_rot
        x_us_ref_scaled_rot_aligned = x_us_ref_scaled_rot[0]

        # Allineamento orizzontale
        delta_x = x_te_us_rot - x_us_ref_scaled_rot[0]
        x_us_scaled_rot_aligned = x_us_scaled_rot_aligned + delta_x
        x_ls_scaled_rot_aligned = x_ls_scaled_rot_aligned + delta_x
        x_us_ref_scaled_rot_aligned = x_us_ref_scaled_rot_aligned + delta_x

        # Allineamento verticale per il secondo profilo
        offset_y = -0.03
        y_us_scaled_rot_aligned = y_us_scaled_rot_aligned + offset_y
        y_ls_scaled_rot_aligned = y_ls_scaled_rot_aligned + offset_y

        # Calcolo linea media del flap scalato, ruotato e traslato
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

        # Trova l'indice della x massima (TE) sulla linea media
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

        # Salva risultato (aggiungi anche l'angolo di attacco)
        results.append([file1, file2, angle_te_camber_deg, alpha_deg])

# Trova la combinazione con l'angolo di deflessione massimo
if results:
    max_result = max(results, key=lambda r: abs(r[2]))  # usa abs se vuoi il valore assoluto
    print(f"Angolo di deflessione massimo: {max_result[2]:.2f}° tra {max_result[0]} e {max_result[1]} (alpha = {max_result[3]:.2f}°)")

    # Ordina tutte le combinazioni per angolo di deflessione (valore assoluto, decrescente)
    results_sorted = sorted(results, key=lambda r: abs(r[2]), reverse=True)
    print("\nClassifica combinazioni per angolo di deflessione (dal più alto al più basso):")
    for r in results_sorted:
        print(f"{r[0]} + {r[1]}: {r[2]:.2f}° (alpha = {r[3]:.2f}°)")

# Salva su file CSV
output_path = os.path.join(os.path.dirname(__file__), 'combinazioni_flap_angle.csv')
with open(output_path, 'w', newline='') as f:
    writer = csv.writer(f)
    writer.writerow(['Profilo_1', 'Profilo_2', 'Angolo_deviazione_deg', 'Angolo_attacco_deg'])
    writer.writerows(results)

print(f"Risultati salvati in {output_path}")




