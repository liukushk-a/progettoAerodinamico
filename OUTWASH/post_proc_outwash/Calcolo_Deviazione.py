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

# 2. Calcola l'angolo di deviazione dalla linea media
def calculate_camber_deviation(x_us, y_us, x_ls, y_ls, alpha_deg, num_points=5):
    # Interpola i punti del dorso e ventre per avere x comuni
    x_common = np.linspace(0, 1, 200)
    y_us_interp = np.interp(x_common, x_us, y_us)
    y_ls_interp = np.interp(x_common, x_ls, y_ls)
    
    # Calcola la linea media
    camber_line = (y_us_interp + y_ls_interp) / 2
    
    # Calcola le tangenti alla linea media
    camber_fit = np.polyfit(x_common[-num_points:], camber_line[-num_points:], 1)
    
    # Calcola l'angolo della linea media
    camber_angle = np.arctan(camber_fit[0])
    
    # Angolo totale = alpha + angolo della linea media
    alpha_rad = alpha_deg * np.pi / 180
    total_deviation = alpha_rad + camber_angle
    
    return total_deviation, camber_fit

# Chiedi all'utente l'angolo di attacco
while True:
    try:
        alpha_deg = float(input("\nInserisci l'angolo di attacco (in gradi): "))
        break
    except ValueError:
        print("Inserisci un numero valido.")

# Calcola l'angolo totale di deviazione
total_angle, camber_fit = calculate_camber_deviation(x_us, y_us, x_ls, y_ls, alpha_deg)

# 3. Ruota il profilo
def rotate_airfoil(x, y, angle):
    # Translate to make LE the origin
    x_translated = x - x[0]
    y_translated = y - y[0]
    
    # Rotate around LE (note: angle is positive when LE goes up)
    x_rot = x_translated * np.cos(angle) - y_translated * np.sin(angle)
    y_rot = x_translated * np.sin(angle) + y_translated * np.cos(angle)
    
    # Translate back
    x_rot = x_rot + x[0]
    y_rot = y_rot + y[0]
    
    return x_rot, y_rot

x_rot, y_rot = rotate_airfoil(x, y, total_angle)

# 4. Stampa l'angolo di deviazione totale
total_angle_deg = total_angle * 180 / np.pi
print(f'Total deviation angle: {total_angle_deg:.2f}°')

# Plot dei profili
plt.figure(figsize=(10, 6))
plt.plot(x, y, 'b-', label='Profilo originale')
plt.plot(x_rot, y_rot, 'r--', label=f'Profilo ruotato ({total_angle_deg:.1f}°)')
plt.axis('equal')
plt.grid(True)
plt.legend()
plt.title('Profilo originale e ruotato')
plt.xlabel('x')
plt.ylabel('y')
plt.show()

