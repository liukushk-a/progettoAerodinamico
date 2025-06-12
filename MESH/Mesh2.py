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
    tag = gmsh.model.geo.addPoint(x, y, z, meshSize=0.01)
    point_tags_1.append(tag)

# Aggiunge i punti del secondo profilo
for x, y, z  in data2:
    tag = gmsh.model.geo.addPoint(x, y, z, meshSize=0.01)
    point_tags_2.append(tag)

# Creo le spline 
line_tags_1 = gmsh.model.geo.addSpline(point_tags_1 + [point_tags_1[0]])
line_tags_2 = gmsh.model.geo.addSpline(point_tags_2 + [point_tags_2[0]])

# Creazione della curve loop

curve_tags_1 = gmsh.model.geo.addCurveLoop([line_tags_1])
curve_tags_2 = gmsh.model.geo.addCurveLoop([line_tags_2])

#definizione dei pyhsical group:
tag1 = gmsh.model.addPhysicalGroup(1, [line_tags_1])
gmsh.model.setPhysicalName(1, tag1, "Airfoil1")

tag2 = gmsh.model.addPhysicalGroup(1, [line_tags_2])
gmsh.model.setPhysicalName(1, tag2, "Airfoil2")

# Campo distanza da entrambi i profili
dist = gmsh.model.mesh.field.add("Distance")
gmsh.model.mesh.field.setNumbers(dist, "CurvesList", [line_tags_1, line_tags_2])

# Refinamento in prossimità
thresh = gmsh.model.mesh.field.add("Threshold")
gmsh.model.mesh.field.setNumber(thresh, "InField", dist)
gmsh.model.mesh.field.setNumber(thresh, "SizeMin", 0.001)  # mesh più fine
gmsh.model.mesh.field.setNumber(thresh, "SizeMax", 0.01)
gmsh.model.mesh.field.setNumber(thresh, "DistMin", 0.001)
gmsh.model.mesh.field.setNumber(thresh, "DistMax", 0.01)

# Applica come background
gmsh.model.mesh.field.setAsBackgroundMesh(thresh)


# Creo il campo Boundary Layer
bl_field = gmsh.model.mesh.field.add("BoundaryLayer")

# Parametri del boundary layer
gmsh.model.mesh.field.setNumbers(bl_field, "CurvesList", [line_tags_1, line_tags_2])
gmsh.model.mesh.field.setNumber(bl_field, "hwall_n", 0.002)  # spessore della prima cella
gmsh.model.mesh.field.setNumber(bl_field, "thickness", 0.02) # spessore totale dello strato limite
gmsh.model.mesh.field.setNumber(bl_field, "ratio", 1.2)      # fattore di crescita
gmsh.model.mesh.field.setNumber(bl_field, "Quads", 1)        # elementi quadrilateri nello strato limite

# Attivo il campo
gmsh.model.mesh.field.setAsBoundaryLayer(bl_field)

## creazione della refinement box: 

x_min, x_max = 0.13, 2.5
y_min, y_max = 0.18, 0.45
cl_box = 0.002  # cell size dentro il refinement box

p1 = gmsh.model.geo.addPoint(x_min, y_min, 0, cl_box)
p2 = gmsh.model.geo.addPoint(x_max, y_min, 0, cl_box)
p3 = gmsh.model.geo.addPoint(x_max, y_max, 0, cl_box)
p4 = gmsh.model.geo.addPoint(x_min, y_max, 0, cl_box)

l6 = gmsh.model.geo.addLine(p1, p2)
l7 = gmsh.model.geo.addLine(p2, p3)
l8 = gmsh.model.geo.addLine(p3, p4)
l9 = gmsh.model.geo.addLine(p4, p1)

box_field = gmsh.model.mesh.field.add("Box")
gmsh.model.mesh.field.setNumber(box_field, "VIn", 0.002)
gmsh.model.mesh.field.setNumber(box_field, "VOut", 0.05)
gmsh.model.mesh.field.setNumber(box_field, "XMin", x_min)
gmsh.model.mesh.field.setNumber(box_field, "XMax", x_max)
gmsh.model.mesh.field.setNumber(box_field, "YMin", y_min)
gmsh.model.mesh.field.setNumber(box_field, "YMax", y_max)
gmsh.model.mesh.field.setNumber(box_field, "ZMin", -1)
gmsh.model.mesh.field.setNumber(box_field, "ZMax", 1)
gmsh.model.mesh.field.setAsBackgroundMesh(box_field)

# Parametri farfield 
Lx = 5
Ly = 0.5 
x0 = -1
y0 = 0.25
cl_far = 0.05

gmsh.model.geo.addPoint(x0,y0,0,cl_far,1000)
gmsh.model.geo.addPoint(x0+Lx,y0,0,cl_far,1001)
gmsh.model.geo.addPoint(x0+Lx,y0+Ly,0,cl_far,1002)
gmsh.model.geo.addPoint(x0,y0+Ly,0,cl_far,1003)

l1 = gmsh.model.geo.addLine(1000,1001)
l2 = gmsh.model.geo.addLine(1001,1002)
l3 = gmsh.model.geo.addLine(1002,1003)
l4 = gmsh.model.geo.addLine(1003,1000)

farfield = gmsh.model.geo.addCurveLoop([l1,l2,l3,l4])

# Definizione dei Physical Groups per i bordi esterni
inlet = gmsh.model.addPhysicalGroup(1, [l4])
gmsh.model.setPhysicalName(1, inlet, "inlet")

outlet = gmsh.model.addPhysicalGroup(1, [l2])
gmsh.model.setPhysicalName(1, outlet, "outlet")

wall_bottom = gmsh.model.addPhysicalGroup(1, [l1])
gmsh.model.setPhysicalName(1, wall_bottom, "bottom")

#Una sola superficie totale con tutti i buchi
gmsh.model.geo.addPlaneSurface([farfield, curve_tags_1, curve_tags_2])

# Sincronizzazione e mesh
gmsh.model.geo.synchronize()
gmsh.model.mesh.generate(2)
gmsh.fltk.run()
gmsh.finalize()
