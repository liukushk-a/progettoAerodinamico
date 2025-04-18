''' In sostanza se noi importiamo dei file txt per definire il profilo, in questo modo si
può ottenere un dataframe con le coordinate e non un file di testo, in modo che se le coordinate
siano scritte con misure troppo piccole, come decimi di millimetro, si possa operare un
riscalamento globale del profilo, per non avere problemi su VSP.
Il nuovo file .dat viene salvato nella stessa cartella in cui tu lanci lo script python. '''

import pandas as pd
import numpy as np

# Richiedi all'utilizzatore il percorso del file .txt
percorso = input("Inserire il percorso del file .txt, fino al punto esatto, " \
                    "includendo il file .txt: ")

# Leggi il file .txt e trasformalo in un DataFrame
DataFrame = pd.read_csv(percorso, delim_whitespace=True, header=None, names=["x", "y"])

# Chiedi all'utilizzatore di quanto vuole riscalare il profilo
riscalamento = float(input("Inserire riscalatura del profilo: "))

# Riscalamento effettivo
DataFrame = DataFrame*riscalamento

# Chiede all'utilizzatore di definire un nuovo nome per il file .dat e un titolo
# da mettere dentro il file .dat in modo tale che xfoil o openVSP lo leggano
# bene

nuovoNome = input("Inserire nuovo nome del file .dat, senza scrivere .dat," \
                    "solo il nome: ")
titolo = input("Inserire titolo per il file .dat (verrà messo nella prima riga): ")

# Riporta il file in .dat
DataFrame.to_csv(f"{nuovoNome}.dat", sep=" ", index=False, header=False)

# Aggiungi il titolo alla prima riga del file .dat
with open(f"{nuovoNome}.dat", "w") as f:
    f.write(titolo + "\n")
    DataFrame.to_csv(f, sep=" ", index=False, header=False)
