import os
import pandas as pd
import numpy as np

def scegli_cartella(percorso):
    cartelle = [d for d in os.listdir(percorso) if os.path.isdir(os.path.join(percorso, d))]
    if not cartelle:
        print("Nessuna cartella trovata.")
        return None
    print("Cartelle disponibili:")
    for i, nome in enumerate(cartelle):
        print(f"{i+1}: {nome}")
    scelta = input("Seleziona il numero della cartella da analizzare: ")
    try:
        idx = int(scelta) - 1
        if 0 <= idx < len(cartelle):
            return cartelle[idx]
    except ValueError:
        pass
    print("Scelta non valida.")
    return None

def analizza_plot(cartella_base):
    plot_path = os.path.join(cartella_base, "Plot")
    x_csv = os.path.join(plot_path, "X_Direction.csv")
    y_csv = os.path.join(plot_path, "Y_Direction.csv")
    if os.path.isfile(x_csv) and os.path.isfile(y_csv):
        try:
            x_df = pd.read_csv(x_csv)
            y_df = pd.read_csv(y_csv)
            y_pos = x_df.iloc[:, 0]
            vx = x_df.iloc[:, 1]
            vy = y_df.iloc[:, 1]
            theta = np.degrees(np.arctan2(vy, vx))
            min_idx = np.argmin(theta)
            min_theta = theta[min_idx]
            y_min = y_pos.iloc[min_idx]
            print(f"\nRisultati per la cartella '{os.path.basename(cartella_base)}':")
            print(f"  Minimo angolo di deflessione: {min_theta:.2f}°")
            print(f"  Posizione verticale corrispondente: {y_min}\n")
        except Exception as e:
            print(f"Errore durante la lettura dei file: {e}")
    else:
        print("File X_Direction.csv o Y_Direction.csv non trovati nella sottocartella 'Plot'.")

if __name__ == "__main__":
    percorso = os.path.dirname(os.path.abspath(__file__))
    scelta = scegli_cartella(percorso)
    if scelta:
        analizza_plot(os.path.join(percorso, scelta))