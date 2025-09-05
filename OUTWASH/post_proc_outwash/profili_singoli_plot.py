
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

# Selezione del file profilo
dat_files = [f for f in os.listdir(profili_path) if f.endswith('.dat')]
if not dat_files:
	print("Nessun file .dat trovato nella cartella 'profili'")
	exit()

print("File disponibili:")
for i, file in enumerate(dat_files):
	print(f"{i+1}. {file}")

# Input selezione file
while True:
	try:
		selection = int(input("\nSeleziona il numero del file da analizzare: ")) - 1
		if 0 <= selection < len(dat_files):
			selected_file = dat_files[selection]
			break
		print("Selezione non valida. Riprova.")
	except ValueError:
		print("Inserisci un numero valido.")

# Caricamento dati profilo
data = np.loadtxt(os.path.join(profili_path, selected_file))
x, y = data[:, 0], data[:, 1]


# --- Plot profilo ---
plt.figure(figsize=(12, 3))
plt.plot(x, y, 'b-', label='Profilo')
plt.axis('equal')
#plt.xlim(-0.1, 1.1)
#plt.ylim(-0.10, 0.25)
plt.grid(True)
plt.xlabel('x/c')
plt.ylabel('y/c')
#plt.title(f'Profilo {selected_file}')
plt.tight_layout()

# Salva immagine nella cartella dei profili con lo stesso nome del file dat (senza estensione)
output_img = os.path.join(profili_path, os.path.splitext(selected_file)[0] + ".png")
plt.savefig(output_img, dpi=300)
plt.show()
