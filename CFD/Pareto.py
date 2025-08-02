import pandas as pd 
from tkinter import Tk, filedialog
import matplotlib.pyplot as plt
#=================== Funzioni =======================================================

def calcola_pareto_efficienza(df):
    df_ordinato = df.sort_values(by=["Cd", "CL"], ascending=[True, False])
    pareto = []
    best_eff = -float("inf")

    for config, row in df_ordinato.iterrows():
        eff = row["Efficienza"]
        if eff > best_eff:
            pareto.append((config, row["CL"], row["Cd"], eff))
            best_eff = eff

    return pd.DataFrame(pareto, columns=["Configurazione", "CL", "Cd", "Efficienza"]).set_index("Configurazione")
 
#==================== Codice ========================================================
#selezionare il csv con cui lavorare:

Tk().withdraw()

df = filedialog.askopenfilename(title = "selezione il CSv con cui lavorare", filetypes=[("CSV file","*.csv")])

if not df: 
    raise ValueError("\n Nessun file selezionato. Uscita ")

print(f"\n file selezionato è : {df}")

dfRisultati = pd.read_csv(df, usecols=["Configurazione", "CL", "Cd"], index_col="Configurazione")

print("\n Preview del dataframe caricato: \n")
print(dfRisultati.head())

# Divisione dei dati delle simlazioni con e senza naso.
filt_naso = (
    dfRisultati.index.str.contains(r'(_si_naso|_naso)$', case=False) &
    ~dfRisultati.index.str.contains(r'_no_naso$', case=False)
)
dfNaso = dfRisultati.loc[filt_naso]
dfNonaso = dfRisultati.loc[~filt_naso]

#Calcolo dell'efficienza aerodinamica: 
# dfRisultati["Efficienza"] = dfRisultati["CL"]/dfRisultati["Cd"]
dfNaso["Efficienza"] = dfNaso["CL"]/dfNaso["Cd"]
dfNonaso["Efficienza"] = dfNonaso["CL"]/dfNonaso["Cd"]

#fronte di pareto per massimizzare efficienza: 

Pareto_Naso = calcola_pareto_efficienza(dfNaso)
Pareto_Nonaso = calcola_pareto_efficienza(dfNonaso)

# === Funzione obiettivo: CL - alpha·Cd ===

alpha = 15  # Peso dato alla resistenza

# Calcolo per Naso
dfNaso["Funzione_Obiettivo"] = dfNaso["CL"] - alpha * dfNaso["Cd"]
best_obj_config_naso = dfNaso["Funzione_Obiettivo"].idxmax()
best_obj_row_naso = dfNaso.loc[best_obj_config_naso]

# Calcolo per No Naso
dfNonaso["Funzione_Obiettivo"] = dfNonaso["CL"] - alpha * dfNonaso["Cd"]
best_obj_config_nonaso = dfNonaso["Funzione_Obiettivo"].idxmax()
best_obj_row_nonaso = dfNonaso.loc[best_obj_config_nonaso]

# Figura 1
plt.figure(figsize=(10, 6))
plt.scatter(dfNaso["Cd"], dfNaso["CL"], alpha=0.3, label="Tutte (Naso)")
plt.plot(Pareto_Naso["Cd"], Pareto_Naso["CL"], 'o-', color='red', label="Pareto Naso")
plt.xlabel("Cd (Resistenza)")
plt.ylabel("CL (Deportanza)")
plt.title("Fronte di Pareto – Massimizzazione Efficienza (Naso)")
plt.gca().invert_xaxis()
plt.grid(True)
plt.legend()
plt.tight_layout()
plt.show()

#Figura 2
plt.figure(figsize=(10, 6))
plt.scatter(dfNonaso["Cd"], dfNonaso["CL"], alpha=0.3, label="Tutte (No Naso)")
plt.plot(Pareto_Nonaso["Cd"], Pareto_Nonaso["CL"], 'o-', color='blue', label="Pareto No Naso")
plt.xlabel("Cd (Resistenza)")
plt.ylabel("CL (Deportanza)")
plt.title("Fronte di Pareto – Massimizzazione Efficienza (No Naso)")
plt.gca().invert_xaxis()
plt.grid(True)
plt.legend()
plt.tight_layout()
plt.show()

# Figura 3
plt.figure(figsize=(10, 6))
plt.scatter(dfNaso["Cd"], dfNaso["CL"], alpha=0.3, label="Tutte (Naso)")
plt.scatter(best_obj_row_naso["Cd"], best_obj_row_naso["CL"],
            color='green', edgecolors='black', s=100,
            label=f"Ottimo F.O. (Naso: {best_obj_config_naso})")
plt.xlabel("Cd (Resistenza)")
plt.ylabel("CL (Deportanza)")
plt.title(f"Funzione Obiettivo: CL - {alpha}·Cd (Naso)")
plt.gca().invert_xaxis()
plt.grid(True)
plt.legend()
plt.tight_layout()
plt.show()

#Figura 4
plt.figure(figsize=(10, 6))
plt.scatter(dfNonaso["Cd"], dfNonaso["CL"], alpha=0.3, label="Tutte (No Naso)")
plt.scatter(best_obj_row_nonaso["Cd"], best_obj_row_nonaso["CL"],
            color='orange', edgecolors='black', s=100,
            label=f"Ottimo F.O. (No Naso: {best_obj_config_nonaso})")
plt.xlabel("Cd (Resistenza)")
plt.ylabel("CL (Deportanza)")
plt.title(f"Funzione Obiettivo: CL - {alpha}·Cd (No Naso)")
plt.gca().invert_xaxis()
plt.grid(True)
plt.legend()
plt.tight_layout()
plt.show()

# Esportazione dei fronti Pareto
Pareto_Naso.to_csv("fronte_pareto_efficienza_naso.csv")
Pareto_Nonaso.to_csv("fronte_pareto_efficienza_nonaso.csv")

print("\n📁 Fronti di Pareto esportati:")
print("- fronte_pareto_efficienza_naso.csv")
print("- fronte_pareto_efficienza_nonaso.csv")

# Stampa riepilogo configurazioni ottimali
print("\n🏆 Configurazione ottima (con naso):")
print("---------------------------------------")
print(f"Configurazione : {best_obj_config_naso}")
print(f"CL             : {best_obj_row_naso['CL']:.4f}")
print(f"Cd             : {best_obj_row_naso['Cd']:.4f}")
print(f"Efficienza     : {best_obj_row_naso['Efficienza']:.4f}")
print(f"F.O. (CL - {alpha}·Cd): {best_obj_row_naso['Funzione_Obiettivo']:.4f}")

print("\n🏆 Configurazione ottima (senza naso):")
print("---------------------------------------")
print(f"Configurazione : {best_obj_config_nonaso}")
print(f"CL             : {best_obj_row_nonaso['CL']:.4f}")
print(f"Cd             : {best_obj_row_nonaso['Cd']:.4f}")
print(f"Efficienza     : {best_obj_row_nonaso['Efficienza']:.4f}")
print(f"F.O. (CL - {alpha}·Cd): {best_obj_row_nonaso['Funzione_Obiettivo']:.4f}")

