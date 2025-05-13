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
    x_rot = x * np.cos(-angle) - y * np.sin(-angle)
    y_rot = x * np.sin(-angle) + y * np.cos(-angle)
    return x_rot, y_rot

x_rot, y_rot = rotate_airfoil(x, y, total_angle)

# Plot
plt.figure(figsize=(12, 8))
plt.plot(x, y, 'b-', label='Airfoil')


# Plot settings
plt.axis('equal')
plt.grid(True)
plt.legend()
plt.title('Airfoil Profile Plot')
plt.xlabel('x/c')
plt.ylabel('y/c')

# 4. Stampa l'angolo di deviazione totale
total_angle_deg = total_angle * 180 / np.pi
print(f'Total deviation angle: {total_angle_deg:.2f}°')

plt.show()

