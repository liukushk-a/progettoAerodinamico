import pandas as pd 
import matplotlib.pyplot as plt 
from tkinter import Tk, filedialog 
import numpy as np
plt.style.use('fivethirtyeight')

# selezione del file .csv 
Tk().withdraw()
df = filedialog.askopenfilename(title="Seleziona il CSV con cui lavorare", filetypes=[("CSV file", "*.csv")])

if not df:
    raise ValueError("\n Nessun file selezionato. Uscita.")

print(f"\n File selezionato: {df}")

dfRisultati = pd.read_csv(df, usecols=["Configurazione", "CL", "Cd"], index_col="Configurazione") #usiamo Configurazione come indice

#separiamo i casi con e senza naso 

filt_naso = dfRisultati.index.str.contains(r'(?:_si_naso|_naso)$', case=False) & \
            (~dfRisultati.index.str.contains(r'(?:_no_naso)$', case=False))

df_naso = dfRisultati.loc[filt_naso].copy()
df_nonaso = dfRisultati.loc[~filt_naso].copy()

#manipolazione dei dati: 
df_naso["CL"] = -df_naso["CL"]
df_naso = df_naso[df_naso["Cd"]>0]

df_nonaso["CL"] = -df_nonaso["CL"]
df_nonaso = df_nonaso[ (df_nonaso["Cd"]>0) & (df_nonaso["CL"]<2)] 
# nella configurazione senza naso usiamo un filtro più stringete per eliminare i dati spuri che alterano il risultato della funzione


print("\n anteprima del dataframe con il naso:")
print(df_naso.head())

print("\n anteprima del dataframe senza naso: ")
print(df_nonaso.head())

def epsilon_constraints(df, N_epsilon):
    '''
    questa funzione serve ad implementare il fronte di pareto usando la tecnica del epsilon constraints in modo da creare un fronte di pareto nel modo più generico possibile pocihè
    non si tiene conto della "convessità" dei dati. lo schema si basa sul fato che una delle funzioni obiettiva va minimizzata, mentre l'altra o le altre sono tenuti delimitate da 
    un valore epsilon. 
    '''
    epsilons = np.linspace(df["Cd"].min(), df["Cd"].max(), N_epsilon)
    pick = []

    for epsilon in epsilons:

        cd_prova = df[df["Cd"] >= epsilon]

        if cd_prova.empty:
            continue 
        best = cd_prova["CL"].idxmax()
        J = cd_prova.loc[best]
        pick.append((best, epsilon, J["Cd"], J["CL"]))

    Pareto = (pd.DataFrame(pick, columns=["Configurazione", "epsilon", "Cd", "CL"]).drop_duplicates(subset="Configurazione").set_index("Configurazione").sort_values(by=["Cd"], ascending=[True]))

    return Pareto

Fronte_naso = epsilon_constraints(df_naso, 100)
Fronte_nonaso = epsilon_constraints(df_nonaso, 100)

# plot naso

plt.figure()
plt.scatter(df_naso["Cd"], df_naso["CL"], alpha=0.3, label='tutte le configurazioni')
plt.plot(Fronte_naso["Cd"], Fronte_naso["CL"], 'o-', label='Fronte di pareto')
plt.xlabel('Cd')
plt.ylabel('Cl')
plt.title('Fronte di pareto con tecnica epsilon-constraints')
plt.gca().invert_xaxis()
plt.grid(True)
plt.legend()
plt.show()

# plot no naso

plt.figure()
plt.scatter(df_nonaso["Cd"], df_nonaso["CL"], alpha=0.3, label='tutte le configurazioni')
plt.plot(Fronte_nonaso["Cd"], Fronte_nonaso["CL"], 'o-', label='Fronte di pareto')
plt.xlabel('Cd')
plt.ylabel('Cl')
plt.title('Fronte di pareto con tecnica epsilon-constraints (No Naso)')
plt.gca().invert_xaxis()
plt.grid(True)
plt.legend()
plt.show()

#esportazione dei dati 
df_naso.to_csv("DataFrame_epsilon_Naso.csv")
df_nonaso.to_csv("DataFrame_epsilon_NoNaso.csv")
Fronte_naso.to_csv("Fronte_epsilon_naso.csv")
Fronte_nonaso.to_csv("Fronte_epsilon_nonaso.csv")



    

