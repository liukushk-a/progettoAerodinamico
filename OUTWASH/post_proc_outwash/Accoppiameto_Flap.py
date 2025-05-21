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

# Caricamento dati Cp
try:
    cp_data = np.loadtxt('cp_naca5406.txt', skiprows=6)  # Skip the first 6 rows
    x_cp = cp_data[:, 0][::-1]  # Prima colonna, invertita per avere x da 0 a 1
    cp = cp_data[:, 2][::-1]    # Terza colonna (Cp)
    
    # Trova il punto di minimo Cp
    idx_min_cp = np.argmin(cp)
    x_min_cp = x_cp[idx_min_cp]
    cp_min = cp[idx_min_cp]
    
    print(f"\nPunto di minimo Cp trovato:")
    print(f"x = {x_min_cp:.4f}")
    print(f"Cp = {cp_min:.4f}")
     # Plot del coefficiente di pressione
    plt.figure(figsize=(10, 6))
    plt.plot(x_cp, cp, 'b-', label='Cp')
    plt.plot(x_min_cp, cp_min, 'ro', label='Cp minimo')
    plt.grid(True)
    plt.xlabel('x/c')
    plt.ylabel('Cp')
    plt.title('Distribuzione del coefficiente di pressione')
    plt.legend()
    plt.gca().invert_yaxis()  # Inverte l'asse y per la convenzione del Cp
    plt.show()

except FileNotFoundError:
    print("File cp_naca5406.txt non trovato nella directory corrente")
    x_min_cp = None
    cp_min = None

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
    """
    Ruota il profilo attorno a un punto centrale.
    Funziona sia per angoli positivi che negativi.
    """
    # Converti gli input in array numpy se non lo sono già
    x = np.asarray(x)
    y = np.asarray(y)
    
    # Traslazione all'origine
    x_translated = x - center_x
    y_translated = y - center_y
    
    # Matrice di rotazione
    cos_theta = np.cos(angle_rad)
    sin_theta = np.sin(angle_rad)
    
    # Applica la rotazione
    x_rot = x_translated * cos_theta - y_translated * sin_theta
    y_rot = x_translated * sin_theta + y_translated * cos_theta
    
    # Ritraslazione al punto originale
    x_final = x_rot + center_x
    y_final = y_rot + center_y
    
    return x_final, y_final


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

# --- Trova punto di minimo Cp sul profilo ---
if x_min_cp is not None:
    target_x = x_min_cp
    idx_cp = np.argmin(np.abs(x_us - target_x))
    x_cp = x_us[idx_cp]
    y_cp = y_us[idx_cp]

    target_x_scaled = x_min_cp * scale_factor
    idx_cp_scaled = np.argmin(np.abs(x_us_scaled - target_x_scaled))
    x_cp_scaled = x_us_scaled[idx_cp_scaled]
    y_cp_scaled = y_us_scaled[idx_cp_scaled]
else:
    print("Impossibile trovare il punto di minimo Cp. File cp_naca5406.txt non trovato.")
    x_cp = y_cp = x_cp_scaled = y_cp_scaled = None

# 1. Trova il punto sul profilo scalato corrispondente a x_min_cp
if x_cp_scaled is not None:
    # 2. Calcola la tangente nel punto di minimo Cp sul profilo scalato
    slope_at_cp = calculate_tangent(x_us_scaled, y_us_scaled, idx_cp_scaled)
    
    # Calcola la linea tangente per il plot
    x_tangent, y_tangent = plot_tangent_line(x_cp_scaled, y_cp_scaled, slope_at_cp, length=0.2)


# --- Analisi profilo originale e scalato ---
# Se x_cp è stato trovato, procedi con l'analisi
if x_cp is not None:
    # Trova il punto sul ventre che corrisponde a x_TE - x_min_cp
    x_target_ls = (te_x-x_min_cp)
    idx_ls = np.argmin(np.abs(x_ls - x_target_ls))
    x_ls_point = x_ls[idx_ls]
    y_ls_point = y_ls[idx_ls]
    
    # Calcola le tangenti
    slope_at_us = calculate_tangent(x_us, y_us, idx_cp)
    slope_at_ls = calculate_tangent(x_ls, y_ls, idx_ls)
    x_tangent_us, y_tangent_us = plot_tangent_line(x_cp, y_cp, slope_at_us, length=0.2)
    x_tangent_ls, y_tangent_ls = plot_tangent_line(x_ls_point, y_ls_point, slope_at_ls, length=0.2)
    
    # Calcola la traslazione per il profilo scalato
    x_translation = te_x - x_min_cp * scale_factor  # Allineamento x rimane lo stesso
    
    # Prima ruota il profilo originale
    x_ls_rotated, y_ls_rotated = rotate_profile(x_ls, y_ls, alpha_rad)
    x_ls_point_rotated, y_ls_point_rotated = rotate_profile([x_ls_point], [y_ls_point], alpha_rad)
    
    # Calcola la tangente al profilo ruotato nel punto corrispondente
    slope_at_ls_rotated = calculate_tangent(x_ls_rotated, y_ls_rotated, idx_ls)
    
    # Calcola l'offset della tangente
    offset = -0.15
    # Calcola il punto sulla tangente del profilo originale
    x_ref = x_ls_point_rotated[0]
    y_ref = y_ls_point_rotated[0]
    slope_ls = slope_at_ls_rotated
    
    # Calcola il punto parallelo sulla tangente con offset
    # Usa la normale alla tangente (perpendicolare) per calcolare l'offset
    normal_vector = np.array([-slope_ls, 1]) / np.sqrt(1 + slope_ls**2)
    y_translation = y_ref + offset * normal_vector[1]
    
    # Applica le traslazioni
    x_us_scaled_translated = x_us_scaled + x_translation
    y_us_scaled_translated = y_us_scaled + y_translation
    x_ls_scaled_translated = x_ls_scaled + x_translation
    y_ls_scaled_translated = y_ls_scaled + y_translation
    
    # Trasla anche il punto di minimo Cp e la sua tangente
    x_cp_scaled_translated = x_cp_scaled + x_translation
    y_cp_scaled_translated = y_cp_scaled + y_translation
    x_tangent_translated = x_tangent + x_translation
    y_tangent_translated = y_tangent + y_translation
    
    
    # Ruota le tangenti del profilo originale
    x_tangent_us_rotated, y_tangent_us_rotated = rotate_profile(x_tangent_us, y_tangent_us, alpha_rad)
    x_tangent_ls_rotated, y_tangent_ls_rotated = rotate_profile(x_tangent_ls, y_tangent_ls, alpha_rad)

    # Modifica il plot finale per usare il profilo scalato ruotato
    plt.figure(figsize=(12, 8))
    
    # 1. Ruota il profilo originale dell'angolo di attacco
    x_us_rotated, y_us_rotated = rotate_profile(x_us, y_us, alpha_rad)
    x_ls_rotated, y_ls_rotated = rotate_profile(x_ls, y_ls, alpha_rad)
    x_cp_rotated, y_cp_rotated = rotate_profile([x_cp], [y_cp], alpha_rad)
    x_ls_point_rotated, y_ls_point_rotated = rotate_profile([x_ls_point], [y_ls_point], alpha_rad)
    
    # Ruota le tangenti del profilo originale
    x_tangent_us_rotated, y_tangent_us_rotated = rotate_profile(x_tangent_us, y_tangent_us, alpha_rad)
    x_tangent_ls_rotated, y_tangent_ls_rotated = rotate_profile(x_tangent_ls, y_tangent_ls, alpha_rad)

    
    
    # Calcola le pendenze delle tangenti delle curve
    slope_at_ls_rotated = calculate_tangent(x_ls_rotated, y_ls_rotated, idx_ls)  # tangente al ventre originale
    slope_at_cp_translated = calculate_tangent(x_us_scaled_translated, y_us_scaled_translated, idx_cp_scaled)  # tangente al cp scalato

# Calcola l'angolo tra le tangenti usando arctan
    angle_ls = np.arctan(slope_at_ls_rotated)  # angolo tangente ventre originale
    angle_cp_translated = np.arctan(slope_at_cp_translated)  # angolo tangente cp scalato traslato
    angle_between = angle_ls - angle_cp_translated  # Angolo di rotazione per allineare le tangenti
    
    print(f"\nDebug angoli:")
    print(f"Angolo tangente ventre originale: {np.rad2deg(angle_ls):.2f}°")
    print(f"Angolo tangente cp scalato: {np.rad2deg(angle_cp_translated):.2f}°")
    print(f"Angolo di rotazione: {np.rad2deg(angle_between):.2f}°")
    
    # Ruota il profilo scalato dell'angolo calcolato
    x_us_scaled_rot, y_us_scaled_rot = rotate_profile(x_us_scaled_translated, y_us_scaled_translated, 
                                                angle_between, 
                                                center_x=x_cp_scaled_translated, 
                                                center_y=y_cp_scaled_translated)
    x_ls_scaled_rot, y_ls_scaled_rot = rotate_profile(x_ls_scaled_translated, y_ls_scaled_translated, 
                                                angle_between,
                                                center_x=x_cp_scaled_translated, 
                                                center_y=y_cp_scaled_translated)
    
    plt.plot(np.concatenate([x_us_rotated, x_ls_rotated]), 
             np.concatenate([y_us_rotated, y_ls_rotated]), 
             'b-', label='Primo flap')
    plt.plot(x_tangent_ls_rotated, y_tangent_ls_rotated, 'r--', 
             label=f'Tangente al ventre')
    
    # Ruota il punto di minimo Cp del profilo scalato
    x_cp_scaled_final, y_cp_scaled_final = rotate_profile([x_cp_scaled_translated], [y_cp_scaled_translated], 
                                                        angle_between,
                                                        center_x=x_cp_scaled_translated, 
                                                        center_y=y_cp_scaled_translated)
    
    # Profilo scalato allineato
    plt.plot(np.concatenate([x_us_scaled_rot, x_ls_scaled_rot]), 
             np.concatenate([y_us_scaled_rot, y_ls_scaled_rot]), 
             'c-', label='Secondo Flap')
    plt.plot(x_cp_scaled_final[0], y_cp_scaled_final[0], 'mo', label='Punto minimo Cp')
    
    # Calcola e plotta la tangente del profilo scalato ruotato
    x_tangent_scaled_rot, y_tangent_scaled_rot = rotate_profile(x_tangent_translated, y_tangent_translated,
                                                              angle_between,
                                                              center_x=x_cp_scaled_translated,
                                                              center_y=y_cp_scaled_translated)
    plt.plot(x_tangent_scaled_rot, y_tangent_scaled_rot, 'm--',
             label=f'Tangente al dorso')
    
    plt.axis('equal')
    plt.grid(True)
    plt.xlabel('x/c')
    plt.ylabel('y/c')
    plt.title(f'Accoppiamento Flap')
    plt.legend(bbox_to_anchor=(1.05, 1), loc='upper left')
    plt.tight_layout()
    plt.show()
    
    # Calcola l'angolo di deviazione totale al trailing edge
    # Trova i punti vicini al trailing edge per il profilo scalato ruotato
    idx_te_us = np.argmax(x_us_scaled_rot)  # indice del punto più a destra sul dorso
    idx_te_ls = np.argmax(x_ls_scaled_rot)  # indice del punto più a destra sul ventre
    
    # Calcola le pendenze al trailing edge
    slope_te_us = calculate_tangent(x_us_scaled_rot, y_us_scaled_rot, idx_te_us)
    slope_te_ls = calculate_tangent(x_ls_scaled_rot, y_ls_scaled_rot, idx_te_ls)
    
    # Calcola gli angoli rispetto all'orizzontale in gradi
    angle_te_us = np.rad2deg(np.arctan(slope_te_us))
    angle_te_ls = np.rad2deg(np.arctan(slope_te_ls))
    
    # Calcola l'angolo di deviazione totale
    total_deviation = angle_te_us - angle_te_ls
    
    print(f"\nAngoli al trailing edge del profilo scalato:")
    print(f"Angolo dorso: {angle_te_us:.2f}°")
    print(f"Angolo ventre: {angle_te_ls:.2f}°")
    print(f"Angolo di deviazione totale del flusso: {total_deviation:.2f}°")
    
