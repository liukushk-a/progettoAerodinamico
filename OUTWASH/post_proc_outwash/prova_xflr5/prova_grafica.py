import matplotlib.pyplot as plt

def parse_alpha_block(file_path):
    profiles_data = {}

    with open(file_path, 'r') as f:
        lines = f.readlines()

    # Prima riga: intestazioni
    headers = lines[0].split()
    profile_names = headers[1::2]  # Ogni secondo valore è il nome del profilo

    # Inizializza dizionario per ogni profilo
    for name in profile_names:
        profiles_data[name] = {'alpha': [], 'xtr': []}

    # Righe successive: dati
    for line in lines[1:]:
        if not line.strip():
            continue  # salta righe vuote
        tokens = line.split()
        try:
            n = len(tokens) // 2  # ogni coppia è (alpha, xtr)
            for i in range(n):
                alpha = float(tokens[2 * i])
                xtr = float(tokens[2 * i + 1])
                profile = profile_names[i]
                profiles_data[profile]['alpha'].append(alpha)
                profiles_data[profile]['xtr'].append(xtr)
        except (IndexError, ValueError):
            continue  # salta righe mal formattate

    return profiles_data

# Esempio di utilizzo
file_path = "d:/Scuola/Uni Magi Secondo Semestre Quinto Anno/Progetto Aerodinamico/PROG/progettoAerodinamico/OUTWASH/post_proc_outwash/prova_xflr5/xtr_top_alfa.txt"
profiles_data = parse_alpha_block(file_path)

# === PLOT ===
plt.figure(figsize=(10, 6))
for profile_name, data in profiles_data.items():
    plt.plot(data['alpha'], data['xtr'], label=profile_name, marker='o')

plt.xlabel('Angolo di attacco (°)')
plt.ylabel('Posizione transizione (x/c)')
plt.title('Transizione laminare-turbolento vs Angolo di attacco')
plt.legend()
plt.grid(True)
plt.tight_layout()
plt.savefig('transizione_vs_alpha.png', dpi=300)
plt.show()

#xtr bottom

file_path = "d:/Scuola/Uni Magi Secondo Semestre Quinto Anno/Progetto Aerodinamico/PROG/progettoAerodinamico/OUTWASH/post_proc_outwash/prova_xflr5/xtr_bottom_alfa.txt"
profiles_data = parse_alpha_block(file_path)

# === PLOT ===
plt.figure(figsize=(10, 6))
for profile_name, data in profiles_data.items():
    plt.plot(data['alpha'], data['xtr'], label=profile_name, marker='o')

plt.xlabel('Angolo di attacco (°)')
plt.ylabel('Posizione transizione (x/c)')
plt.title('Transizione laminare-turbolento vs Angolo di attacco')
plt.legend()
plt.grid(True)
plt.tight_layout()
plt.savefig('transizione_vs_alpha.png', dpi=300)
plt.show()

#cl alfa

file_path = "d:/Scuola/Uni Magi Secondo Semestre Quinto Anno/Progetto Aerodinamico/PROG/progettoAerodinamico/OUTWASH/post_proc_outwash/prova_xflr5/cl_alfa.txt"
profiles_data = parse_alpha_block(file_path)

# === PLOT ===
plt.figure(figsize=(10, 6))
for profile_name, data in profiles_data.items():
    plt.plot(data['alpha'], data['xtr'], label=profile_name, marker='o')

plt.xlabel('Angolo di attacco (°)')
plt.ylabel('cl ')
plt.title('Cl vs Angolo di attacco')
plt.legend()
plt.grid(True)
plt.tight_layout()
plt.savefig('cl_vs_alpha.png', dpi=300)
plt.show()

#cd alfa

file_path = "d:/Scuola/Uni Magi Secondo Semestre Quinto Anno/Progetto Aerodinamico/PROG/progettoAerodinamico/OUTWASH/post_proc_outwash/prova_xflr5/cd_alfa.txt"
profiles_data = parse_alpha_block(file_path)

# === PLOT ===
plt.figure(figsize=(10, 6))
for profile_name, data in profiles_data.items():
    plt.plot(data['alpha'], data['xtr'], label=profile_name, marker='o')

plt.xlabel('Angolo di attacco (°)')
plt.ylabel('cd')
plt.title('Cd vs Angolo di attacco')
plt.legend()
plt.grid(True)
plt.tight_layout()
plt.savefig('cd_vs_alpha.png', dpi=300)
plt.show()


