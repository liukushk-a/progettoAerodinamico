import os
import csv

def scegli_cartella():
    # Percorso assoluto della cartella target
    percorso = os.path.dirname(os.path.abspath(__file__))
    cartelle = [d for d in os.listdir(percorso) if os.path.isdir(os.path.join(percorso, d))]
    if not cartelle:
        print("Nessuna cartella trovata.")
        return None
    for i, nome in enumerate(cartelle):
        print(f"{i}: {nome}")
    scelta = input("Inserisci il numero della cartella da selezionare: ")
    try:
        scelta = int(scelta)
        if 0 <= scelta < len(cartelle):
            print(f"Hai selezionato: {cartelle[scelta]}")
            return os.path.join(percorso, cartelle[scelta])
        else:
            print("Numero non valido.")
    except ValueError:
        print("Input non valido.")
    return None

def estrai_ultimi_valori(cartella_base):
    plot_path = os.path.join(cartella_base, "Plot")
    if not os.path.isdir(plot_path):
        print(f"Nessuna cartella 'Plot' trovata in {cartella_base}")
        return
    risultati = []
    for nome_file in os.listdir(plot_path):
        if nome_file.endswith(".csv"):
            file_path = os.path.join(plot_path, nome_file)
            try:
                with open(file_path, newline='') as csvfile:
                    reader = list(csv.reader(csvfile))
                    if reader:
                        ultimo_valore = reader[-1][-1]  # Ultima riga, ultima colonna
                        risultati.append(f"{nome_file}: {ultimo_valore}")
            except Exception as e:
                print(f"Errore con il file {nome_file}: {e}")
    # Salva i risultati in un file txt
    output_path = os.path.join(plot_path, "Reports.txt")
    with open(output_path, "w") as f:
        for riga in risultati:
            f.write(riga + "\n")
    print(f"Risultati salvati in {output_path}")

if __name__ == "__main__":
    cartella = scegli_cartella()
    if cartella:
        estrai_ultimi_valori(cartella)