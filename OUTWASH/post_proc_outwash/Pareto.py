import os
from pathlib import Path

def select_profile_folder():
    # Percorso della cartella profili
    profiles_path = Path("../profili")
    
    # Verifica che la cartella esista
    if not profiles_path.exists():
        raise FileNotFoundError("La cartella 'profili' non esiste!")
    
    # Lista tutte le cartelle in profili
    folders = [f for f in profiles_path.iterdir() if f.is_dir()]
    
    if not folders:
        print("Non ci sono cartelle nella directory 'profili'")
        return None
    
    # Mostra le opzioni disponibili
    print("\nCartelle disponibili:")
    for i, folder in enumerate(folders, 1):
        print(f"{i}. {folder.name}")
    
    # Chiedi all'utente di selezionare una cartella
    while True:
        try:
            choice = int(input("\nSeleziona il numero della cartella desiderata: "))
            if 1 <= choice <= len(folders):
                selected_folder = folders[choice-1]
                print(f"\nHai selezionato: {selected_folder.name}")
                return selected_folder
            else:
                print("Scelta non valida. Riprova.")
        except ValueError:
            print("Inserisci un numero valido.")

if __name__ == "__main__":
    try:
        selected_folder = select_profile_folder()
    except Exception as e:
        print(f"Errore: {e}")