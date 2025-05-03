''' È ancora uno script molto semplice, ma con script così mi piacerebbe partire dalla
cartella generale e navigare in tutte le cartelle dentro la cartella di analisysOutput, per
estrarre i risultati da ogni analisi, in ogni cartella e compararli tra di loro, in modo
da saper subito quale configurazione ci dà i risultati migliori.
Affinchè questo script funzioni, deve essere runnato NELLA STESSA CARTELLA DI analysis.py.

Note tecniche: non so come mai ma ho dovuto usare il metodo os.path.join per entrare
nelle varie cartelle, per motivi sconosciuti al genere umano, se uso os.chidir, è come
se python non riconoscesse che io intendo entrare solo nelle cartelle, perchè è chiaro
che non posso entrare nei file .csv, ma solo nelle cartelle che contengono i file .csv, 
ma in ogni caso non vuole funzionare, quindi ho dovuto fare sta modifica.

Ho capito una cosa importante creo: os.getcwd è una funzione puttana, perchè se la uso, 
concettualmente pensavo che mi dicesse dove sono virtualmente nell'andamento del codice,
ma tutto ciò che può dirmi è dove il codice è stato lanciato, è come se il codice anche
se stia andando anche in sottocartelle, ma in realtà python questo non lo sa, perciò ti
restituisce sempre la cartella dove il codice è stato lanciato, non la sottocartella dove
il codice in questo momento sta operando.
Un problema simile l'ho riscontrato usando la funzione open, è come se si ostinasse a non
riconoscere a che punto siamo col codice, ma cercasse di aprire file sempre nella cartella
da cui lo script è stato lanciato, quindi bisogna dargli il percorso completo del file.'''

import re
import pandas as pd
import os

# Creo un dataframe vuoto per i risultati
df = pd.DataFrame()
df['Cartella'] = []
df['CDtot'] = []
df['CLtot'] = []
df['Funzione obiettivo'] = []

# Inizializzo le grandezze d'interesse
CDtot = 0
CL = 0
funzioneObiettivo = 0

# Rendo il codice adatto per navigare da solo in tutte le cartelle di analysisOutput
# e analizzare i risultati di ogni cartella
# Inizio ottenendo la directory di partenza
directoryDiPartenza = os.getcwd()

# Parto ad analizzare dentro ogni directory che ho dentro quella di partenza
for folder in os.listdir(directoryDiPartenza):

    # Mi sposto nella cartella analysisOutput... del caso
    folder_path = os.path.join(directoryDiPartenza, folder)

    # Se sono effettivamente dentro una cartella, il ciclo continua
    if os.path.isdir(folder_path):

        # Navigo nei subfolder di analysisOutput
        for subfolder in os.listdir(folder_path):

            # Navigo dentro ogni subfolder
            folder_path1 = os.path.join(folder_path, subfolder)

            # Controllo ancora di essere in una cartella
            if os.path.isdir(folder_path1):

                # Se trovo dentro i vari subfolder un file che finisce per .csv, lo analizzo
                for file in os.listdir(folder_path1):
                    if file.endswith(".csv"):

                        # Mi porto nel path del file
                        file_path = os.path.join(folder_path1, file)

                        # Apro il file di risultati e leggo le righe
                        with open(f"{folder_path1}/{file}", "r") as result:
                            lines = result.readlines()

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

                                # Creo nuove righe per il dataframe. Aggiungo il sort_values per
                                # ordinare il dataframe in base alla funzione obiettivo
                                newRow = pd.DataFrame({f'Cartella': [subfolder], 
                                                       'CDtot': [CDtot],
                                                       'CLtot': [CL],
                                                       'Funzione obiettivo': [funzioneObiettivo]})

                                # Aggiungo la nuova riga al dataframe
                                df = pd.concat([df, newRow], ignore_index=True)

# Stampo il dataframe finale
# print(df)

# Trovo il valore massimo della funzione obiettivo
bestResult = df['Funzione obiettivo'].max()
bestResultIndex = df['Funzione obiettivo'].idxmax()

# Trovo con quale configurazione ho ottenuto il valore massimo della funzione obiettivo
bestConfiguration = df.iloc[bestResultIndex]['Cartella']

# Stampo a schermo il risultato migliore
print(f"La configurazione migliore è {bestConfiguration}, con un valore di funzione obiettivo pari a {bestResult}")