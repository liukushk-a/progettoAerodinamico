import tkinter as Tk
from tkinter.filedialog import askopenfilename
import pandas as pd

# Disabilita la GUI di tkinter
Tk.Tk().withdraw()

# Selezione file
body = askopenfilename(title="Seleziona il file .txt del profilo", filetypes=[("Text files", "*.txt")])
df = pd.read_csv(body, delim_whitespace=True, header=None, names=["x", "y", "z"])

# Trova il punto di separazione (bordo d’attacco)
split_index = df['x'].idxmin()
print(f"\nSeparazione automatica tra dorso e ventre all’indice: {split_index} (x = {df.loc[split_index, 'x']})")

# Inverti solo la prima metà (dorso)
first_half = df.loc[:split_index].iloc[::-1].reset_index(drop=True)
second_half = df.loc[split_index+1:].reset_index(drop=True)

# Combina le due metà
df = pd.concat([first_half, second_half], ignore_index=True)

# Input dell’utente
int_value = int(input("\nInserisci il primo valore (intero): "))
scale_value = float(input("\nInserisci il secondo valore (scala): "))
name_value = input("\nInserisci il nome dell'oggetto (es. airfoil): ")

# Costruzione della stringa
output_strings = []

for i, row in df.iterrows():
    target = 0 if i < len(first_half) else 1
    x_coord = round(row['x'], 5)
    entry = f"( {int_value}, {scale_value} | {name_value} | {target}, {x_coord} )"
    output_strings.append(entry)

# Generazione stringa finale
final_string = "DEFINITION_DV= " + " ; ".join(output_strings)

# Output
print("\nStringa da incollare nel .cfg:\n")
print(final_string)

# Salvataggio su file
with open("definition_dv_output.txt", "w") as f:
    f.write(final_string)

print("\n✅ Stringa salvata in 'definition_dv_output.txt'")
