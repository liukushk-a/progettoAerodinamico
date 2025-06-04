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

# Richiedi all'utilizzatore il percorso del file .txt
percorso = input("Inserire il percorso del file .txt, fino al punto esatto, " \
                    "includendo il file .txt: ")

# Leggi il file .txt e trasformalo in un DataFrame
DataFrame = pd.read_csv(percorso, delim_whitespace=True, header=None, names=["x", "y", "z"])

# Elimino eventuali colonne di coordinate z che non servono
del DataFrame["z"]

# Chiedi all'utilizzatore di quanto vuole riscalare il profilo
riscalamento = float(input("Inserire riscalatura del profilo: "))

# Riscalamento effettivo
DataFrame = DataFrame*riscalamento

# Chiede all'utilizzatore se intende capovolgere il profilo
capovolgimento = input ("Vuoi capovolgere il profilo? (Y/n): ")
if capovolgimento == "Y" or capovolgimento == "y":
    DataFrame["y"] = DataFrame["y"]*(-1)

# Chiede all'utilizzatore se intende effettuare una traslazione nello spazio del profilo
traslazione = input("Vuoi traslare il profilo nello spazio? (Y/n): ")
if traslazione == "Y" or traslazione == "y":
    
    traslazione_X = float(input("Inserire la traslazione in x in metri (I.E. 160 mm = 0.160): "))
    traslazione_Y = float(input("Inserire la traslazione in y in metri: "))

    DataFrame["x"] = DataFrame["x"] + traslazione_X;
    DataFrame["y"] = DataFrame["y"] + traslazione_Y;

# È necessario fare in modo che, dato che airfoiltools non chiude i profili al bordo d'uscita,
# faccio un controllo sul numero in coordinata x, in modo tale che se noto che il numero
# corrisponde perfettamente al valore dato dal riscalamento della corda + la traslazione, ho
# che la coordinata sull'asse y deve andare non a zero, ma al valore imposto dalla traslazione.
for i in range(len(DataFrame)):
    if DataFrame["x"][i] == riscalamento + traslazione_X:
        DataFrame["y"][i] = traslazione_Y

# Chiede all'utilizzatore di definire un nuovo nome per il file .dat che 
# funge anche da titolo da mettere nel file .dat in modo tale che xfoil o
# openVSP lo leggano bene
nuovoNome = input("Inserire nuovo nome del file .dat, senza scrivere .dat, " \
                    "solo il nome: ")
# Riporta il file in .dat
DataFrame.to_csv(f"{nuovoNome}.dat", sep=" ", index=False, header=False)

# Aggiungi il titolo alla prima riga del file .dat
with open(f"{nuovoNome}.dat", "w") as f:
    f.write(nuovoNome + "\n")
    DataFrame.to_csv(f, sep=" ", index=False, header=False)


