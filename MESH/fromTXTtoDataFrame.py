''' In sostanza se noi importiamo dei file txt per definire il profilo, in questo modo si
può ottenere un dataframe con le coordinate e non un file di testo, in modo che se le coordinate
siano scritte con misure troppo piccole, come decimi di millimetro, si possa operare un
riscalamento globale del profilo, per non avere problemi su VSP.
Il nuovo file .dat viene salvato nella stessa cartella in cui tu lanci lo script python.
Il codice, tra le altre cose, prende anche i file .txt e li trasforma in .dat, quindi può
essere usato anche per quello, se non si vuole riscalare, si mette solo 1
È importante come il file che venga dato in input abbia soltanto numeri dentro, colonne coi
dati e non titoli o altre cose strane dentro, quelli vanno cancellati a priori'''

import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

# Richiedi all'utilizzatore il percorso del file .txt
percorso = input("Inserire il percorso del file .txt, fino al punto esatto, " \
                    "includendo il file .txt: ")

# Leggi il file .txt e trasformalo in un df
df = pd.read_csv(percorso, delim_whitespace=True, header=None, names=["x", "y", "z"])

# Elimino eventuali colonne di coordinate z che non servono
del df["z"]

# Chiedi all'utilizzatore di quanto vuole riscalare il profilo
riscalamento = float(input("Inserire riscalatura del profilo: "))

# Riscalamento effettivo
df = df*riscalamento

# Chiede all'utilizzatore se intende capovolgere il profilo
capovolgimento = input ("Vuoi capovolgere il profilo? (Y/n): ")
if capovolgimento == "Y" or capovolgimento == "y":
    df["y"] = df["y"]*(-1)

# Verifica la lunghezza del file: deve essere pari
if len(df) % 2 != 0:
    raise ValueError("Il numero di righe nel file non è pari: controlla il file!")

# Suddividi il dataframe in due metà: dorso (superiore) e ventre (inferiore)
n = len(df) // 2
dorso = df.iloc[:n].reset_index(drop=True)
ventre = df.iloc[n:].reset_index(drop=True)

# Calcolo della linea media
x_medio = dorso["x"]  # Le x sono le stesse per dorso e ventre
y_medio = (dorso["y"] + ventre["y"]) / 2

# Costruzione del dataframe della linea media
df_linea_media = pd.DataFrame({
    "x": x_medio,
    "y_medio": y_medio
})

x_camber = x_medio
y_camber = y_medio

# Parametri di rotazione
x0 = float(input("Inserisci x del polo di rotazione: "))
y0 = float(input("Inserisci y del polo di rotazione: "))
theta_deg = float(input("Inserisci angolo di rotazione in gradi (positivo = antiorario): "))
theta_rad = np.radians(theta_deg)

# Applica la rotazione a tutti i punti
x_shifted = df["x"] - x0
y_shifted = df["y"] - y0

x_c_shifted = x_camber - x0
y_c_shifted = y_camber - y0

x_c_rot = x0 + x_c_shifted * np.cos(theta_rad) - y_c_shifted * np.sin(theta_rad)
y_c_rot = y0 + x_c_shifted * np.sin(theta_rad) + y_c_shifted * np.cos(theta_rad)

x_rot = x0 + x_shifted * np.cos(theta_rad) - y_shifted * np.sin(theta_rad)
y_rot = y0 + x_shifted * np.sin(theta_rad) + y_shifted * np.cos(theta_rad)

# Faccio in modo che le coordinate dell'ultimo punto del dorso e del ventre dopo la
# rotazione siano uguali, in modo tale che il profilo sia chiuso
if y_rot.iloc[-1] != y_rot.iloc[n-1]:
    y_rot.iloc[-1] = y_rot.iloc[n-1]

# Aggiorna il dataframe
df["x"] = x_rot
df["y"] = y_rot


# Divide in due metà
n = len(df) // 2
dorso = df.iloc[:n].copy()
#ventre = ventre.iloc[::-1].reset_index(drop=True)
ventre = df.iloc[n:].iloc[::-1].copy()  # inversione del ventre

# Ricombina in un unico dataframe
df_flipped = pd.concat([dorso, ventre], ignore_index=True)

# Chiede all'utilizzatore se intende effettuare una traslazione nello spazio del profilo
traslazione = input("Vuoi traslare il profilo nello spazio? (Y/n): ")
if traslazione == "Y" or traslazione == "y":
    
    traslazione_X = float(input("Inserire la traslazione in x in metri (I.E. 160 mm = 0.160): "))
    traslazione_Y = float(input("Inserire la traslazione in y in metri: "))

    df_flipped["x"] = df_flipped["x"] + traslazione_X;
    df_flipped["y"] = df_flipped["y"] + traslazione_Y;

if traslazione == "n" or traslazione == "N":

    traslazione_X = 0
    traslazione_Y = 0

plt.plot(df_flipped["x"], df_flipped["y"], 'b-', label='Punti Originali')
plt.axis('equal')
plt.show()

# Chiede all'utilizzatore di definire un nuovo nome per il file .dat che 
# funge anche da titolo da mettere nel file .dat in modo tale che xfoil o
# openVSP lo leggano bene
nuovoNome = input("Inserire nuovo nome del file .dat, senza scrivere .dat, " \
                    "solo il nome: ")
# Riporta il file in .dat
df_flipped.to_csv(f"{nuovoNome}_ruotato.txt", sep=" ", index=False, header=False)
