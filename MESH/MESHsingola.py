import gmsh
import numpy as np
from tkinter import Tk
from tkinter.filedialog import askopenfilename
import matplotlib.pyplot as plt

# === Caricamento file ===
Tk().withdraw()
file_path1 = askopenfilename(title="Seleziona il primo file .txt", filetypes=[("Text files", "*.txt")])

data1 = np.loadtxt(file_path1)

# Visualizzazione (opzionale)
plt.plot(data1[:, 0], data1[:, 1], label='Profilo 1')
plt.legend()
plt.axis('equal')
plt.show()

# === Inizializzazione Gmsh ===
gmsh.initialize()
gmsh.model.add("HybridMesh")

point_tags_1 = []
point_tags_2 = []

for x, y, z in data1:
    tag = gmsh.model.geo.addPoint(x, y, z,1e-2)
    point_tags_1.append(tag)

# === Curve (Polyline invece di Spline) ===
line_tags_1 = gmsh.model.geo.addPolyline(point_tags_1 + [point_tags_1[0]])

loop_1 = gmsh.model.geo.addCurveLoop([line_tags_1])

# --- Physical group dei profili ---
gmsh.model.addPhysicalGroup(1, [line_tags_1], tag=1)
gmsh.model.setPhysicalName(1, 1, "Airfoil1")

# --- Farfield geometry ---
Lx, Ly = 5, 1.5
x0, y0 = -1, 0.25
cl_far = 0.05

p0 = gmsh.model.geo.addPoint(x0, y0, 0, cl_far, 1000)
p1 = gmsh.model.geo.addPoint(x0 + Lx, y0, 0, cl_far, 1001)
p2 = gmsh.model.geo.addPoint(x0 + Lx, y0 + Ly, 0, cl_far, 1002)
p3 = gmsh.model.geo.addPoint(x0, y0 + Ly, 0, cl_far, 1003)

l1 = gmsh.model.geo.addLine(1000, 1001)
l2 = gmsh.model.geo.addLine(1001, 1002)
l3 = gmsh.model.geo.addLine(1002, 1003)
l4 = gmsh.model.geo.addLine(1003, 1000)

farfield_loop = gmsh.model.geo.addCurveLoop([l1, l2, l3, l4])

# --- Superficie con fori dei due profili ---
surface = gmsh.model.geo.addPlaneSurface([farfield_loop, loop_1])

gmsh.model.addPhysicalGroup(1, [l4], tag=10)
gmsh.model.setPhysicalName(1, 10, "inlet")
gmsh.model.addPhysicalGroup(1, [l2], tag=11)
gmsh.model.setPhysicalName(1, 11, "outlet")
gmsh.model.addPhysicalGroup(1, [l1], tag=12)
gmsh.model.setPhysicalName(1, 12, "bottom")
gmsh.model.addPhysicalGroup(1, [l3], tag=13)
gmsh.model.setPhysicalName(1, 13, "top")

gmsh.model.geo.synchronize()

dist = gmsh.model.mesh.field.add("Distance")
gmsh.model.mesh.field.setNumbers(dist, "CurvesList", [line_tags_1])

thresh = gmsh.model.mesh.field.add("Threshold")
gmsh.model.mesh.field.setNumber(thresh, "InField", dist)
gmsh.model.mesh.field.setNumber(thresh, "SizeMin", 0.1)
gmsh.model.mesh.field.setNumber(thresh, "SizeMax", 0.3)
gmsh.model.mesh.field.setNumber(thresh, "DistMin", 0.001)
gmsh.model.mesh.field.setNumber(thresh, "DistMax", 0.01)

# Campo 2: refinement intermedio (box grande)
box_big = gmsh.model.mesh.field.add("Box")
gmsh.model.mesh.field.setNumber(box_big, "VIn", 0.01)
gmsh.model.mesh.field.setNumber(box_big, "VOut", 0.1)
gmsh.model.mesh.field.setNumber(box_big, "XMin", 0.13)
gmsh.model.mesh.field.setNumber(box_big, "XMax", 2.5)
gmsh.model.mesh.field.setNumber(box_big, "YMin", 0.18)
gmsh.model.mesh.field.setNumber(box_big, "YMax", 0.45)
gmsh.model.mesh.field.setNumber(box_big, "ZMin", -1)
gmsh.model.mesh.field.setNumber(box_big, "ZMax", 1)

gmsh.model.mesh.field.setAsBackgroundMesh(box_big)

gmsh.fltk.run()

# --- Genera mesh 2D ---
gmsh.model.mesh.generate(2)

# --- Visualizza mesh finale ---
gmsh.fltk.run()

format = ".su2"
name = "mesh"+format
gmsh.write(name)

gmsh.finalize()
