import gmsh
import numpy as np
import matplotlib.pyplot as plt
from tkinter import Tk
from tkinter.filedialog import askopenfilename
import os

def datTogeo(datas):
    Np = datas.shape[0]
    mp = int(Np / 2)

    x_upper = datas[0:mp + 1, 0]
    y_upper = datas[0:mp + 1, 1]
    x_lower = datas[mp:, 0]
    y_lower = datas[mp:, 1]

    min_len = min(len(y_upper), len(y_lower))
    y_upper = y_upper[:min_len]
    y_lower = y_lower[:min_len]
    x_upper = x_upper[:min_len]

    x = np.concatenate((x_upper, x_lower))
    y = np.concatenate((y_upper, y_lower))
    x_camber = x_upper
    y_camber = (y_upper + np.flip(y_lower)) / 2
    t = np.abs(y_upper - np.flip(y_lower))

    return x, x_camber, y, y_camber, t

def changeOrientation(x0, y0, x_camber, y_camber, t, rot_percent, angle_deg):
    chord_length = x_camber.max() - x_camber.min()
    x_rot = x_camber.min() + (rot_percent / 100.0) * chord_length
    idx = np.argmin(np.abs(x_camber - x_rot))
    pivot = np.array([x_camber[idx], y_camber[idx]])

    dx = np.gradient(x_camber)
    dy = np.gradient(y_camber)
    mag = np.sqrt(dx ** 2 + dy ** 2)
    nx = -dy / mag
    ny = dx / mag

    angle_rad = np.deg2rad(angle_deg)
    R = np.array([[np.cos(angle_rad), -np.sin(angle_rad)], [np.sin(angle_rad), np.cos(angle_rad)]])

    camber_coords = np.column_stack((x_camber, y_camber))
    camber_rotated = (camber_coords - pivot) @ R.T + pivot
    x_c_rot = camber_rotated[:, 0]
    y_c_rot = camber_rotated[:, 1]

    normals = np.column_stack((nx, ny))
    normals_rotated = normals @ R.T
    nx_rot = normals_rotated[:, 0]
    ny_rot = normals_rotated[:, 1]

    x_upper = x_c_rot + (t / 2) * nx_rot + x0
    y_upper = y_c_rot + (t / 2) * ny_rot + y0
    x_lower = x_c_rot - (t / 2) * nx_rot + x0
    y_lower = y_c_rot - (t / 2) * ny_rot + y0

    return x_upper, y_upper, x_lower, y_lower

def remove_close_points(x, y, tol=1e-6):
    filtered_x = [x[0]]
    filtered_y = [y[0]]
    for i in range(1, len(x)):
        dx = x[i] - filtered_x[-1]
        dy = y[i] - filtered_y[-1]
        if dx ** 2 + dy ** 2 > tol ** 2:
            filtered_x.append(x[i])
            filtered_y.append(y[i])
    return np.array(filtered_x), np.array(filtered_y)

def ensure_closed(x, y, tol=1e-6):
    if np.hypot(x[0] - x[-1], y[0] - y[-1]) > tol:
        x = np.append(x, x[0])
        y = np.append(y, y[0])
    return x, y

# Lettura file .dat per 2 profili
Tk().withdraw()
file_path1 = askopenfilename(title="Seleziona il primo file .dat", filetypes=[("DAT files", "*.dat")])
file_path2 = askopenfilename(title="Seleziona il secondo file .dat", filetypes=[("DAT files", "*.dat")])

if not file_path1 or not file_path2:
    raise FileNotFoundError("Entrambi i file devono essere selezionati")

data1 = np.loadtxt(file_path1)
data2 = np.loadtxt(file_path2)

x1, x_c1, y1, y_c1, t1 = datTogeo(data1)
x2, x_c2, y2, y_c2, t2 = datTogeo(data2)

x_up1, y_up1, x_lo1, y_lo1 = changeOrientation(0, 0, x_c1, y_c1, t1, 25, 0)
x_up2, y_up2, x_lo2, y_lo2 = changeOrientation(1.5, 0, x_c2, y_c2, t2, 25, 0)

# # Pulizia e chiusura contorni
# x_up1, y_up1 = remove_close_points(*ensure_closed(x_up1, y_up1))
# x_lo1, y_lo1 = remove_close_points(*ensure_closed(x_lo1, y_lo1))
# x_up2, y_up2 = remove_close_points(*ensure_closed(x_up2, y_up2))
# x_lo2, y_lo2 = remove_close_points(*ensure_closed(x_lo2, y_lo2))

# # Avvio GMSH
# gmsh.initialize()
# gmsh.model.add("HybridMesh")
# gmsh.option.setNumber("Geometry.Tolerance", 1e-5)

# # Funzione per aggiungere contorno in GMSH
# def add_airfoil(x, y, tag_offset):
#     points = []
#     for i in range(len(x)):
#         points.append(gmsh.model.geo.addPoint(x[i], y[i], 0, 0.001, tag=tag_offset + i))
#     lines = []
#     for i in range(len(points) - 1):
#         lines.append(gmsh.model.geo.addLine(points[i], points[i + 1]))
#     lines.append(gmsh.model.geo.addLine(points[-1], points[0]))
#     cl = gmsh.model.geo.addCurveLoop(lines)
#     return cl

# loop1 = add_airfoil(np.concatenate((x_up1, x_lo1)), np.concatenate((y_up1, y_lo1)), 1)
# loop2 = add_airfoil(np.concatenate((x_up2, x_lo2)), np.concatenate((y_up2, y_lo2)), 1000)

# # Crea bounding box esterno
# dom_pts = [
#     gmsh.model.geo.addPoint(-1, -1, 0, 0.1),
#     gmsh.model.geo.addPoint(3, -1, 0, 0.1),
#     gmsh.model.geo.addPoint(3, 1, 0, 0.1),
#     gmsh.model.geo.addPoint(-1, 1, 0, 0.1)
# ]
# dom_lines = [
#     gmsh.model.geo.addLine(dom_pts[0], dom_pts[1]),
#     gmsh.model.geo.addLine(dom_pts[1], dom_pts[2]),
#     gmsh.model.geo.addLine(dom_pts[2], dom_pts[3]),
#     gmsh.model.geo.addLine(dom_pts[3], dom_pts[0])
# ]
# dom_loop = gmsh.model.geo.addCurveLoop(dom_lines)

# # Crea superficie con buchi
# surf = gmsh.model.geo.addPlaneSurface([dom_loop, loop1, loop2])

# gmsh.model.geo.synchronize()

# # Algoritmo ibrido: frontal vicino al bordo, Delaunay altrove
# gmsh.model.mesh.setAlgorithm(2, surf, 6)  # Surface ID 2 -> Frontal-Delaunay
# gmsh.option.setNumber("General.NumThreads",4)
# gmsh.option.setNumber("Mesh.Optimize",1)

# # Mesh
# gmsh.model.mesh.generate(2)
# gmsh.write("hybrid_mesh.msh")

# gmsh.fltk.run()
# gmsh.finalize()
