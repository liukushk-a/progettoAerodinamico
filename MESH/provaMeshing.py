import gmsh
import numpy as np
from tkinter import Tk
from tkinter.filedialog import askopenfilename

# Caricamento dei due file .txt
Tk().withdraw()
file_path1 = askopenfilename(title="Seleziona il primo file .txt", filetypes=[("Text files", "*.txt")])
file_path2 = askopenfilename(title="Seleziona il secondo file .txt", filetypes=[("Text files", "*.txt")])

if not file_path1 or not file_path2:
    raise FileNotFoundError("Entrambi i file devono essere selezionati")

data1 = np.loadtxt(file_path1)
data2 = np.loadtxt(file_path2)

# Inizializzazione Gmsh
gmsh.initialize()
gmsh.model.add("HybridMesh")

point_tags_1 = []
point_tags_2 = []

# Aggiunge i punti del primo profilo
for x, y, z in data1:
    tag = gmsh.model.geo.addPoint(x, y, 0, meshSize=0.01)
    point_tags_1.append(tag)

# Aggiunge i punti del secondo profilo
for x, y, z in data2:
    tag = gmsh.model.geo.addPoint(x, y, 0, meshSize=0.01)
    point_tags_2.append(tag)

# Crea una spline chiusa per ogni profilo
line_loop_1 = gmsh.model.geo.addSpline(point_tags_1 + [point_tags_1[0]])
line_loop_2 = gmsh.model.geo.addSpline(point_tags_2 + [point_tags_2[0]])

# Crea curve loop per i profili
curve_loop_1 = gmsh.model.geo.addCurveLoop([line_loop_1])
curve_loop_2 = gmsh.model.geo.addCurveLoop([line_loop_2])

# Crea superfici dei profili
surface_1 = gmsh.model.geo.addPlaneSurface([curve_loop_1])
surface_2 = gmsh.model.geo.addPlaneSurface([curve_loop_2])

# Sincronizzazione e mesh
gmsh.model.geo.synchronize()

# Scrivo nel .geo_unrolled
gmsh.write("geometria.geo_unrolled")

#gmsh.model.mesh.setRecombine(2, fluid_domain)  # Mesh quad-dominant

# gmsh.model.mesh.generate(2)
gmsh.fltk.run()

gmsh.finalize()
