import os
import tkinter as tk
from tkinter import filedialog
import numpy as np
import matplotlib.pyplot as plt

def select_folder():
    root = tk.Tk()
    root.withdraw()  # Nascondi la finestra principale
    folder_path = filedialog.askdirectory(title="Seleziona la cartella con i file .txt")
    return folder_path

def load_txt_files(folder_path):
    # Lista tutti i file .txt nella cartella
    txt_files = [f for f in os.listdir(folder_path) if f.endswith('.txt')]
    
    if not txt_files:
        print("Nessun file .txt trovato nella cartella selezionata")
        return None
    
    # Mostra i file trovati
    print("\nFile trovati:")
    for i, file in enumerate(txt_files):
        print(f"{i+1}. {file}")
    
    # Dizionario per salvare i dati e gli header
    data_dict = {}
    headers_dict = {}
    
    # Carica ogni file
    for file in txt_files:
        file_path = os.path.join(folder_path, file)
        try:
            # Leggi la prima riga come header
            with open(file_path, 'r') as f:
                header = f.readline().strip()
                
            # Carica i dati saltando la prima riga
            data = np.loadtxt(file_path, skiprows=1)
            
            # Salva sia i dati che l'header
            data_dict[file] = {
                'header': header,
                'data': data
            }
            print(f"\nCaricato con successo: {file}")
            print(f"Header: {header}")
        except Exception as e:
            print(f"Errore nel caricamento di {file}: {str(e)}")
    
    return data_dict

def plot_data(data_dict, folder_path):
    # Create Results directory in profili folder
    results_path = os.path.join(os.path.dirname(folder_path), 'results')
    if not os.path.exists(results_path):
        os.makedirs(results_path)
    
    # Get folder name from path
    folder_name = os.path.basename(folder_path)
    
    # Dizionari per salvare i dati separati
    alphas = {}
    y_values = {}
    alpha_30_percent = {}  # Nuovo dizionario per salvare gli alpha al 30%
    
    # Estrai e salva i dati prima di plottare
    for filename, data in data_dict.items():
        alphas[filename] = data['data'][:, 0]  # Prima colonna (alpha)
        y_values[filename] = data['data'][:, 1]  # Seconda colonna
        
        # Trova il punto al 30% dal massimo
        max_xtr = np.max(y_values[filename])
        target_xtr = max_xtr * 0.7  # 30% dal massimo
        
        # Trova l'indice del punto più vicino al target
        idx = np.argmin(np.abs(y_values[filename] - target_xtr))
        alpha_30_percent[filename] = alphas[filename][idx]
        
        print(f"\nPer {filename}:")
        print(f"Massimo xtr_top: {max_xtr:.4f}")
        print(f"Valore al 30%: {target_xtr:.4f}")
        print(f"Alpha corrispondente: {alpha_30_percent[filename]:.2f} gradi")
    
    # Crea i subplots con dimensione fissa
    fig, axs = plt.subplots(2, 2, figsize=(16, 9))
    fig.suptitle('Analisi dei dati', fontsize=16)
    
    # Converti axs in array 1D per facilità d'uso
    axs = axs.flatten()
    
    # Plotta i dati salvati
    for i, filename in enumerate(data_dict.keys()):
        if i < 4:  # Limita a 4 grafici
            axs[i].plot(alphas[filename], y_values[filename], 'b-', marker='o')
            axs[i].set_xlabel('Alpha [deg]')
            axs[i].set_ylabel(filename.replace('.txt', ''))
            axs[i].grid(True)
            axs[i].set_title(f'{filename.replace(".txt", "")}')

    # Aggiusta il layout
    plt.tight_layout()
    
    # Save the plot in results folder
    plot_filename = os.path.join(results_path, f'analysis_{folder_name}.png')
    plt.savefig(plot_filename, dpi=300, bbox_inches='tight')
    print(f"\nPlot saved as: {plot_filename}")
    
    plt.close()  # Chiude la figura senza mostrarla
    
    return alphas, y_values, alpha_30_percent

def main():
    print("Seleziona la cartella contenente i file .txt")
    folder_path = select_folder()
    
    if not folder_path:
        print("Nessuna cartella selezionata")
        return None, None, None, None
    
    data_dict = load_txt_files(folder_path)
    
    if data_dict:
        print(f"\nCaricati {len(data_dict)} file con successo")
        # Plot dei dati
        alphas, y_values, alpha_30_percent = plot_data(data_dict, folder_path)
        return data_dict, alphas, y_values, alpha_30_percent
    else:
        return None, None, None, None

if __name__ == "__main__":
    try:
        data, alphas, y_values, alpha_30_percent = main()
        if data is not None:
            print("\nAnalisi completata con successo!")
        else:
            print("\nNessun dato da analizzare.")
    except Exception as e:
        print(f"\nErrore durante l'esecuzione: {str(e)}")