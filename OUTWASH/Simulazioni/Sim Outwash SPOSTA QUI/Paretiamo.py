import pandas as pd
import matplotlib.pyplot as plt
import os

# Percorso del file CSV (nella stessa cartella di questo script)
csv_path = os.path.join(os.path.dirname(__file__), "risultati_deviazione.csv")

# Leggi il CSV
df = pd.read_csv(csv_path)

# Controlla che le colonne esistano
if "Cd.csv" not in df.columns or "min_theta" not in df.columns or "cartella" not in df.columns:
    raise ValueError("Il file CSV deve contenere le colonne 'Cd.csv', 'min_theta' e 'cartella'.")

# Plot
plt.figure(figsize=(8, 6))
plt.scatter(df["Cd.csv"], df["min_theta"], color='b', marker='o')
for i, row in df.iterrows():
    plt.annotate(
        row["cartella"],
        (row["Cd.csv"], row["min_theta"]),
        textcoords="offset points",
        xytext=(5, 2),
        ha='left',
        fontsize=8,
        color='gray'
    )
plt.xlabel("Cd.csv")
plt.ylabel("min_theta")
plt.title("min_theta vs Cd.csv")
plt.grid(True)
plt.tight_layout()
plt.show()