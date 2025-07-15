import tkinter as Tk
from tkinter.filedialog import askopenfilename
import pandas as pd

# Disabilita la GUI di tkinter
Tk.Tk().withdraw()

# Selezione file
body = askopenfilename(title="Seleziona il file .txt del profilo", filetypes=[("Text files", "*.txt")])
df = pd.read_csv(body, delim_whitespace=True, header=None, names=["x", "y", "z"])

# Input dell’utente
int_value = int(input("\n Inserisci il primo valore (intero): "))
scale_value = float(input("\n Inserisci il secondo valore (scala): "))
name_value = input("\n Inserisci il nome dell'oggetto (es. airfoil): ")

# Determinazione automatica del punto di separazione
split_index = df['x'].idxmin()
print(f"\n Separazione automatica tra dorso e ventre all’indice: {split_index} (x = {df.loc[split_index, 'x']})")

# Costruzione della stringa
output_strings = []

for i, row in df.iterrows():
    target = 1 if i <= split_index else 0
    x_coord = round(row['x'], 5)
    entry = f"( {int_value}, {scale_value} | {name_value} | {target}, {x_coord} )"
    output_strings.append(entry)

# Generazione della stringa finale
final_string = "DEFINITION_DV= " + " ; ".join(output_strings)

# Stampa il risultato
print("\nStringa da incollare nel .cfg:\n")
print(final_string)

# (Facoltativo) salvataggio su file
with open("definition_dv_output.txt", "w") as f:
    f.write(final_string)

print("\n✅ Stringa salvata in 'definition_dv_output.txt'")
