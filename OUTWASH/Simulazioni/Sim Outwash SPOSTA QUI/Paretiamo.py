import pandas as pd
import matplotlib.pyplot as plt
import os

# Percorso dei file
base_path = os.path.dirname(__file__)
csv_path = os.path.join(base_path, "risultati_deviazione.csv")
stallo_path = os.path.join(base_path, "Stallo.txt")

# Leggi i CSV
df = pd.read_csv(csv_path)
df_stallo = pd.read_csv(stallo_path, sep="\t|\s+", engine="python", names=["cartella", "stallo"], header=None)

# Unisci i due dataframe sulla colonna 'cartella'
df_merged = pd.merge(df, df_stallo, on="cartella", how="inner")


# Filtra solo dove stallo == "no" (case insensitive)
df_plot = df_merged[df_merged["stallo"].str.lower() == "no"]

# Controlla che le colonne esistano
if "Cd.csv" not in df_merged.columns or "min_theta" not in df_merged.columns or "cartella" not in df_merged.columns:
    raise ValueError("Il file CSV deve contenere le colonne 'Cd.csv', 'min_theta' e 'cartella'.")

# Normalizza rispetto al massimo di Cd.csv e al valore assoluto del minimo di min_theta (sul dataframe completo)
cd_max_all = df_merged["Cd.csv"].max()
theta_min_abs_all = abs(df_merged["min_theta"].min())
df_merged["Cd_norm"] = df_merged["Cd.csv"] / cd_max_all if cd_max_all != 0 else df_merged["Cd.csv"]
df_merged["min_theta_norm"] = df_merged["min_theta"] / theta_min_abs_all if theta_min_abs_all != 0 else df_merged["min_theta"]

# Plot TUTTI I DATI (senza filtro stallo)
fig, ax = plt.subplots(figsize=(12, 6))
ax.scatter(df_merged["Cd_norm"], df_merged["min_theta_norm"], color='orange', marker='o')

# Numerazione punti
num_labels = list(range(1, len(df_merged) + 1))
for i, (idx, row) in enumerate(df_merged.iterrows()):
    ax.text(row["Cd_norm"], row["min_theta_norm"], str(num_labels[i]), fontsize=8, color='black', ha='center', va='center')

ax.set_xlabel("Cd / Cd_max (tutti)")
ax.set_ylabel("min_theta / abs(min(min_theta)) (tutti)")
ax.set_title("min_theta normalizzato vs Cd normalizzato (TUTTI I DATI)")
ax.grid(True)

# Legenda numerica a lato in più colonne per compattezza
labels_data = [(num_labels[i], row['cartella']) for i, (idx, row) in enumerate(df_merged.iterrows())]
# Dividi in 3 colonne
n_cols = 3
n_rows = len(labels_data) // n_cols + (1 if len(labels_data) % n_cols > 0 else 0)
legend_lines = []
for row in range(n_rows):
    line_parts = []
    for col in range(n_cols):
        idx = row * n_cols + col
        if idx < len(labels_data):
            line_parts.append(f"{labels_data[idx][0]}: {labels_data[idx][1]}")
        else:
            line_parts.append("")  # Riempi celle vuote
    legend_lines.append("  |  ".join(line_parts))

legend_text = "\n".join(legend_lines)
ax.text(1.05, 0.5, legend_text, transform=ax.transAxes, fontsize=7, verticalalignment='center', 
        bbox=dict(boxstyle="round,pad=0.3", facecolor="lightgray"), family='monospace')

plt.tight_layout()
plt.show()

# Normalizza e calcola funzione obiettivo solo per i non stallo (come prima)
cd_max = df_plot["Cd.csv"].max()
theta_min_abs = abs(df_plot["min_theta"].min())
df_plot["Cd_norm"] = df_plot["Cd.csv"] / cd_max if cd_max != 0 else df_plot["Cd.csv"]
df_plot["min_theta_norm"] = df_plot["min_theta"] / theta_min_abs if theta_min_abs != 0 else df_plot["min_theta"]

# Definisci i pesi (modifica a piacere)
w_theta = -0.6
w_cd = -0.4

# Calcola la funzione obiettivo come somma pesata
df_plot["obiettivo"] = w_theta * df_plot["min_theta_norm"] + w_cd * df_plot["Cd_norm"]

# (Opzionale) Ordina per funzione obiettivo crescente
df_plot = df_plot.sort_values("obiettivo")

# Plot SOLO NON STALLO
plt.figure(figsize=(8, 6))
plt.scatter(df_plot["Cd_norm"], df_plot["min_theta_norm"], color='b', marker='o')
for i, row in df_plot.iterrows():
    plt.annotate(
        row["cartella"],
        (row["Cd_norm"], row["min_theta_norm"]),
        textcoords="offset points",
        xytext=(5, 2),
        ha='left',
        fontsize=8,
        color='gray'
    )
plt.xlabel("Cd / Cd_max")
plt.ylabel("min_theta / abs(min(min_theta))")
plt.title("min_theta normalizzato vs Cd normalizzato (solo non stallo)")
plt.grid(True)
plt.tight_layout()
plt.show()

# Plot funzione obiettivo
plt.figure(figsize=(8, 6))
plt.scatter(df_plot["Cd_norm"], df_plot["min_theta_norm"], c=df_plot["obiettivo"], cmap="viridis", marker='o')
for i, row in df_plot.iterrows():
    plt.annotate(
        row["cartella"],
        (row["Cd_norm"], row["min_theta_norm"]),
        textcoords="offset points",
        xytext=(5, 2),
        ha='left',
        fontsize=8,
        color='gray'
    )
plt.xlabel("Cd / Cd_max")
plt.ylabel("min_theta / abs(min(min_theta))")
plt.title("Funzione obiettivo pesata (colore)")
plt.colorbar(label="Funzione obiettivo")
plt.grid(True)
plt.tight_layout()
plt.show()

# Stampa i cinque migliori risultati della funzione obiettivo
print("Cinque peggiori risultati (funzione obiettivo più alta):")
print(df_plot[["cartella", "Cd.csv", "min_theta", "obiettivo"]].tail(5).to_string(index=False))