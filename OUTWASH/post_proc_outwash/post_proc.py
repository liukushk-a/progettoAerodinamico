import numpy as np
import matplotlib.pyplot as plt

def load_data(file_path):
    # Inizializza liste per x e Cf
    x_values = []
    cf_values = []
    
    # Leggi il file riga per riga
    with open(file_path, 'r') as file:
        for line in file:
            # Salta righe vuote o di commento
            if line.strip() == "" or line.startswith("#"):
                continue
            
            # Dividi la riga in colonne
            columns = line.split()
            
            # Estrai x e Cf
            x = float(columns[1])
            cf = float(columns[6])
            
            # Aggiungi i valori a x e Cf
            x_values.append(x)
            cf_values.append(cf)
    
    return np.array(x_values), np.array(cf_values)

def load_airfoil(file_path):
    # Inizializza liste per x e y
    x_values = []
    y_values = []
    
    # Leggi il file riga per riga
    with open(file_path, 'r') as file:
        lines = file.readlines()
        for line in lines[1:]:  # Ignora la prima riga (titolo dell'airfoil)
            # Salta righe vuote
            if line.strip() == "":
                continue
            
            # Dividi la riga in colonne
            columns = line.split()
            
            # Estrai x e y
            x = float(columns[0])
            y = float(columns[1])
            
            # Aggiungi i valori a x e y
            x_values.append(x)
            y_values.append(y)
    
    return np.array(x_values), np.array(y_values)

def rotate_airfoil(x, y, alpha):
    # Converte l'angolo da gradi a radianti
    alpha_rad = np.radians(alpha)
    
    # Matrice di rotazione
    rotation_matrix = np.array([
        [np.cos(alpha_rad),  np.sin(alpha_rad)],
        [-np.sin(alpha_rad),  np.cos(alpha_rad)]
    ])
    
    # Combina x e y in un array 2D
    points = np.vstack((x, y))
    
    # Applica la rotazione
    rotated_points = rotation_matrix @ points
    
    # Restituisce i punti ruotati
    return rotated_points[0, :], rotated_points[1, :]

# Percorso dei file
file_path = "d:/Scuola/Uni Magi Secondo Semestre Quinto Anno/Progetto Aerodinamico/XFOIL/XFOIL6.99/naca45008.bl"
airfoil_path = "d:/Scuola/Uni Magi Secondo Semestre Quinto Anno/Progetto Aerodinamico/XFOIL/XFOIL6.99/naca45008.dat"

# Carica i dati
x, cf = load_data(file_path)

# Carica il profilo dell'airfoil
x_airfoil, y_airfoil = load_airfoil(airfoil_path)

# Ruota l'airfoil di un angolo alpha rispetto al leading edge
alpha = 2  # Angolo di rotazione in gradi (modifica questo valore come necessario)
x_airfoil_rotated, y_airfoil_rotated = rotate_airfoil(x_airfoil, y_airfoil, alpha)

# Considera solo i primi 300 punti
x = x[:300]
cf = cf[:300]

# Dividi i dati in due gruppi da 150 punti ciascuno
x_first_half = x[:150]
cf_first_half = cf[:150]

x_second_half = x[150:]
cf_second_half = cf[150:]

# Crea i grafici
fig, axs = plt.subplots(2, 1, figsize=(8, 14))  # Aumenta l'altezza totale del grafico

# Primo grafico: Cf in funzione di x
axs[0].plot(x_first_half, cf_first_half, label="Primi 150 punti", color="blue")
axs[0].plot(x_second_half, cf_second_half, label="Secondi 150 punti", color="red", linestyle="--")
axs[0].set_xlabel("x")
axs[0].set_ylabel("Cf")
axs[0].set_title("Cf - x")
axs[0].legend()
axs[0].grid(True)

# Secondo grafico: Profilo dell'airfoil ruotato
axs[1].plot(x_airfoil_rotated, y_airfoil_rotated, label=f"Airfoil (rotato di {alpha}°)", color="green")
axs[1].set_xlabel("x")
axs[1].set_ylabel("y")
axs[1].legend()
axs[1].grid(True)

# Imposta la stessa scala per x e y
axs[1].set_aspect('equal', adjustable='datalim')

# Aggiusta gli spazi tra i grafici
plt.subplots_adjust(hspace=0.5)  # Aumenta lo spazio verticale tra i grafici

# Mostra i grafici
plt.show()




