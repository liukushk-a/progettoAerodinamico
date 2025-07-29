import pandas as pd 
from tkinter import Tk, filedialog
import matplotlib.pyplot as plt

#selezionare il csv con cui lavorare: 

Tk().withdraw()

df = filedialog.askopenfilename(title = "selezione il CSv con cui lavorare", filetypes=[("CSV file","*.csv")])

if not df: 
    raise ValueError("\n Nessun file selezionato. Uscita ")

print(f"\n file selezionato è : {df}")

dfRisultati = pd.read_csv(df, usecols=["Configurazione", "CL", "Cd"], index_col="Configurazione")

print("\n Preview del dataframe caricato: \n")
print(dfRisultati.head())

#Calcolo dell'efficienza aerodinamica: 
dfRisultati["Efficienza"] = dfRisultati["CL"]/dfRisultati["Cd"]

#fronte di pareto per massimizzare efficienza: 

dfRisultati_ordinato = dfRisultati.sort_values(by=["Cd","CL"], ascending=[True, False])
Pareto_eff = []
best_E = -float("inf")

for config, row in dfRisultati_ordinato.iterrows():
    E = row["Efficienza"]
    if E > best_E:
        Pareto_eff.append((config, row["CL"], row["Cd"], E))
        best_E = E

Pareto_E = pd.DataFrame(Pareto_eff, columns=["Configurazione", "CL", "Cd", "Efficienza"]).set_index("Configurazione")

# Pareto con funzione obiettivo

alpha = 15
dfRisultati["Funzione_Obiettivo"] = dfRisultati["CL"] - alpha*dfRisultati["Cd"]
best_obj_config = dfRisultati["Funzione_Obiettivo"].idxmax()
best_obj_row = dfRisultati.loc[best_obj_config]

# Plot 1: Fronte di Pareto (Efficienza)
plt.figure(figsize=(10, 6))
plt.scatter(dfRisultati["Cd"], dfRisultati["CL"], label="Tutte le configurazioni")
plt.plot(Pareto_E["Cd"], Pareto_E["CL"], marker='o', color='red', label="Fronte di Pareto (Efficienza)")

plt.xlabel("Cd (Resistenza)")
plt.ylabel("CL (Deportanza)")
plt.title("Fronte di Pareto – Massimizzazione Efficienza")
plt.gca().invert_xaxis()
plt.grid(True)
plt.legend()
plt.tight_layout()
plt.show()

#Plot 2: Ottimo secondo Funzione Obiettivo 
plt.figure(figsize=(10, 6))
plt.scatter(dfRisultati["Cd"], dfRisultati["CL"], label="Tutte le configurazioni")
plt.scatter(
    best_obj_row["Cd"], best_obj_row["CL"],
    color='green', edgecolors='black', s=100,
    label=f"Ottimo F.O. ({best_obj_config})"
)

plt.xlabel("Cd (Resistenza)")
plt.ylabel("CL (Deportanza)")
plt.title(f"Funzione Obiettivo: CL - {alpha}·Cd")
plt.gca().invert_xaxis()
plt.grid(True)
plt.legend()
plt.tight_layout()
plt.show()

Pareto_E.to_csv("fronte_pareto_efficienza.csv")
print("\n Fronte di Pareto esportato in 'fronte_pareto_efficienza.csv'")
best_obj_row = dfRisultati.loc[best_obj_config]
if isinstance(best_obj_row, pd.DataFrame):
    best_obj_row = best_obj_row.iloc[0]

print("\n Configurazione ottima secondo la funzione obiettivo:")
print("--------------------------------------------------------")
print(f"Configurazione : {best_obj_config}")
print(f"CL             : {best_obj_row['CL']:.4f}")
print(f"Cd             : {best_obj_row['Cd']:.4f}")
print(f"Efficienza     : {best_obj_row['Efficienza']:.4f}")
print(f"Funzione Obiettivo (CL - {alpha}·Cd) : {best_obj_row['Funzione_Obiettivo']:.4f}")

