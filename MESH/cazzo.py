import matplotlib.pyplot as plt
from tkinter.filedialog import askopenfilename
coordinateCorrette = askopenfilename(title="Seleziona il file .txt del main", filetypes=[("Text files", "*.txt")])
plt.figure(figsize=(10, 5))
plt.plot(coordinateCorrette, marker='o', linestyle='-', color='r', label='Coordinate corrette')
plt.show()
