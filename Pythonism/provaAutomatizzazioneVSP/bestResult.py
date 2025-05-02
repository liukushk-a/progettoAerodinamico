''' È ancora uno script molto semplice, ma con script così mi piacerebbe partire dalla
cartella generale e navigare in tutte le cartelle dentro la cartella di analisysOutput, per
estrarre i risultati da ogni analisi, in ogni cartella e compararli tra di loro, in modo
da saper subito quale configurazione ci dà i risultati migliori.
Affinchè questo script funzioni, deve essere runnato NELLA STESSA CARTELLA DI analysis.py.

Note tecniche: non so come mai ma ho dovuto usare il metodo os.path.join per entrare
nelle varie cartelle, per motivi sconosciuti al genere umano, se uso os.chidir, è come
se python non riconoscesse che io intendo entrare solo nelle cartelle, perchè è chiaro
che non posso entrare nei file .csv, ma solo nelle cartelle che contengono i file .csv, 
ma in ogni caso non vuole funzionare, quindi ho dovuto fare sta modifica.'''

import re
import pandas as pd
import os

# Creo un dataframe vuoto per i risultati
df = pd.DataFrame()
df['Cartella'] = []
df['CDtot'] = []
df['CLtot'] = []
df['Funzione obiettivo'] = []

# Rendo il codice adatto per navigare da solo in tutte le cartelle di analysisOutput
# e analizzare i risultati di ogni cartella
# Inizio ottenendo la directory di partenza
directoryDiPartenza = os.getcwd()

# Parto ad analizzare dentro ogni directory che ho dentro quella di partenza
for folder in os.listdir(directoryDiPartenza):

    # Mi sposto nella cartella analysisOutput... del caso
    folder_path = os.path.join(directoryDiPartenza, folder)

    # Ottengo dove mi trovo
    directoryAnalysisOutput = os.getcwd()

    # Navigo nei subfolder di analysisOutput
    for subfolder in os.listdir(directoryAnalysisOutput):

        # Navigo dentro ogni subfolder
        folder_path = os.path.join(directoryAnalysisOutput, subfolder)

        # Ottengo dove mi trovo
        directorySubfolder = os.getcwd()

        # Se trovo dentro i vari subfolder un file che finisce per .csv, lo analizzo
        for file in os.listdir(directorySubfolder):
            if file.endswith(".csv"):
                
                # Apro il file di risultati e leggo le righe
                with open("Results.csv", "r") as file:
                    lines = file.readlines()

                # Ricerco la combinazione di lettere, caratteri speciali e numeri che mi interessa
                # per i coefficienti di portanza e resistenza
                # Curiosità: il $ alla fine della stringa indica che stai cercando la fine della riga
                for line in lines:
                    coeffResistenzaTotale = re.search(r"CDtot,(\d+\.\d+e-\d+)$", line)
                    if coeffResistenzaTotale is not None:
                        CDtot = float(coeffResistenzaTotale.group(1))

                    coeffPortanzaTotale = re.search(r"CL,(-\d+\.\d+e-\d+)$", line)
                    if coeffPortanzaTotale is not None:
                        CL = float(coeffPortanzaTotale.group(1))

                    # Creo un esempio di funzione obiettivo che voglio ottimizzare
                    funzioneObiettivo = abs(CL)*0.5 + CDtot

                    # NOTA BENE: ci troviamo ancora in uno dei vari subfolder

                    # Creo nuove righe per il dataframe
                    newRow = pd.DataFrame({f'Cartella': [subfolder], 
                                           'CDtot': [CDtot],
                                           'CLtot': [CL],
                                           'Funzione obiettivo': [funzioneObiettivo]})
                    
                    # Aggiungo la nuova riga al dataframe
                    df = pd.concat([df, newRow], ignore_index=True)

                    print(df)

# Stampo il dataframe finale
print(df)









## Questo è il codice ancora non adatto all'automazione, ancora con la necessità
## che tu dica che cartella vuoi analizzare. È funzionante, ma non mi posso 
## accontentare
## Apro il file e leggo le righe
#with open("./analysisOutput/Sweep_main_wing_0.0/Results.txt", "r") as file:
#    lines = file.readlines()
#
## Ricerco la combinazione di lettere, caratteri speciali e numeri che mi interessa
## Curiosità: il $ alla fine della stringa indica che stai cercando la fine della riga
#for line in lines:
#    coeffResistenzaTotale = re.search(r"CDtot,(\d+\.\d+e-\d+)$", line)
#    if coeffResistenzaTotale is not None:
#        CDtot = float(coeffResistenzaTotale.group(1))
#
#    coeffPortanzaTotale = re.search(r"CL,(-\d+\.\d+e-\d+)$", line)
#    if coeffPortanzaTotale is not None:
#        CL = float(coeffPortanzaTotale.group(1))
#
#print("CDtot: ", CDtot)
#print("CLtot: ", CL)
#
#liftToDragRatio = CL/CDtot
#
## Creo un esempio di funzione obiettivo che voglio ottimizzare
#funzioneObiettivo = abs(CL)*0.5 + CDtot