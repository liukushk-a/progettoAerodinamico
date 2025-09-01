import os
import csv

# Nomi dei file di input
file_names = ['be092_075_ruotato.txt', 'S4180-098-84_ruotato.txt', 'coordinateNaso.txt']
output_txt = 'unione.txt'
output_csv = 'unione.csv'

def format_line(line, add_zero=False):
    # Formatta le prime due colonne con 8 cifre decimali, la terza con 1 cifra decimale
    items = line.strip().split()
    formatted = []
    for i, item in enumerate(items):
        try:
            num = float(item.replace(',', '.'))
            if i == 2:
                formatted.append(f"{num:.1f}")
            else:
                formatted.append(f"{num:.8f}")
        except ValueError:
            formatted.append(item)
    if add_zero:
        formatted.append("0.0")
    return ','.join(formatted)

# Unisci i file txt, separando i punti con una virgola e 8 decimali, senza spazi tra i file
with open(output_txt, 'w', encoding='utf-8') as outfile:
    for idx, fname in enumerate(file_names):
        with open(fname, 'r', encoding='utf-8') as infile:
            for line in infile:
                # Solo per il terzo file aggiungi una colonna di zeri (formattata con una cifra decimale)
                if idx == 2:
                    outfile.write(format_line(line, add_zero=True))
                else:
                    outfile.write(format_line(line))
                outfile.write('\n')

# Converti il file txt in csv (una colonna per riga)
with open(output_txt, 'r', encoding='utf-8') as infile, \
     open(output_csv, 'w', newline='', encoding='utf-8') as outfile:
    writer = csv.writer(outfile)
    for line in infile:
        writer.writerow([line.strip()])



print("File unione.txt e unione.csv creati con successo.")