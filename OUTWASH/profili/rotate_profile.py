import numpy as np 
import matplotlib.pyplot as plt 
import numpy.polynomial.polynomial as Polynomial 
plt.style.use('fivethirtyeight')

def find_rot_location(rot_location,x_camber): 
    x = x_camber
    cord = x.max() - x.min() #find the length of the profile
    x_loc = x.min()+rot_location*cord
    index_loc = np.argmin(np.abs(x-x_loc))
    return index_loc,x_loc

def camber_line(data):

    #find the lenght of the .dat 
    Np = data.shape[0]

    # find the middle point
    mp = int(Np/2)

    #find the coordinates
    x_upper = data[0:mp+1,0]
    x_lower = data[mp:,0]
    y_upper = data[0:mp+1,1]
    y_lower = data[mp:,1]

    #min_len = min(len(x_upper), len(x_lower))
    x_camber = x_upper  # si assume che upper e lower abbiano stessi x
    y_camber = (y_upper + np.flip(y_lower)) / 2
    t = np.abs(y_upper - np.flip(y_lower))
    
    plt.plot(x_camber,y_camber, label = 'camber line')
    plt.plot(x_upper,y_upper,color = 'black',label='upper surface')
    plt.plot(x_lower,y_lower,color='magenta',label='lower surface')
    plt.grid(True)
    plt.legend()
    plt.show()
    plt.xlabel('x')
    plt.ylabel('y')
    plt.title('Camber line of the profile')
    plt.axis('equal')
    return x_camber, y_camber,t

def rotation(polygrade,rot_location,x_camber,y_camber):

    idx,x_rot = find_rot_location(rot_location,x_camber)

    # now I need to build the curve that interpolates the point from the first to x_rot

    # idx_lex = np.argmin(np.abs(x_camber-0))
    # idx_ley = np.argmin(np.abs(y_camber-0))

    x_sub = x_camber[-(idx+1):]
    y_sub = y_camber[-(idx+1):]

    if len(x_sub) < polygrade + 1:
        raise ValueError("Troppi pochi punto per il polinomio")

    a = np.polyfit(x_sub,y_sub,polygrade)
    p = np.poly1d(a) # this function generate a polynom that is zero in every point of a

    dp = p.deriv()
    dy_dx = dp(x_rot)
    theta = np.arctan(dy_dx)
    norm_x = -dy_dx / np.sqrt(1 + dy_dx**2)
    norm_y = 1 / np.sqrt(1 + dy_dx**2)

    cos_theta = np.cos(-theta)
    sin_theta = np.sin(-theta)

    R = np.array([[cos_theta,-sin_theta],[sin_theta,cos_theta]])

    pivot = np.array([x_camber[idx],y_camber[idx]])

    dat = np.column_stack((x_camber,y_camber))

    rotated = (dat-pivot)@R.T +pivot

    x_upper = rotated[:,0] + norm_x * t / 2
    y_upper = rotated[:,1] + norm_y * t / 2

    x_lower = rotated[:,0]- norm_x * t / 2
    y_lower = rotated[:,1] - norm_y * t / 2   

    return rotated, theta, pivot, p, x_rot,x_upper,x_lower,y_upper,y_lower

## -----------------------------------------------------------------------------------------

file_path = "/Users/danielegalluzzo/progettoAerodinamico/OUTWASH/profili/naca6408.dat" 

data = np.loadtxt(file_path)

x_c,y_c,t = camber_line(data)

rot_location = 0.25

rotated_data, theta, pivot, p, x_rot,x_up,x_lo,y_up,y_lo = rotation(3,rot_location,x_c,y_c)

print(f"Rotation Angle: {-np.degrees(theta):.2f}°")
print(f"Pivot Point): {pivot}")

# === Plot ===
plt.figure(figsize=(12, 6))

# 1. Profilo originale vs ruotato
plt.subplot(1, 3, 1)
plt.plot(data[:, 0], data[:, 1], label="Original")
plt.axis("equal")
plt.grid(True)
plt.legend()
plt.xlabel('x')
plt.ylabel('y')
plt.title('Original Profile')

# 2. Profilo ruotato
plt.subplot(1,3,2)
plt.plot(rotated_data[:, 0], rotated_data[:, 1], '--', label="Rotated")
plt.scatter(*pivot, color='red', label= f"{rot_location}% cord")
plt.plot(x_up,y_up, lw = 2, label = 'upper surface')
plt.plot(x_lo,y_lo, lw = 2, label = 'lower surface')
plt.legend()
plt.axis('equal')
plt.xlabel('x')
plt.ylabel('y')
plt.grid(True)
plt.title(f"Rotated profile of {-np.degrees(theta):.2f}°")

# 3. Fit polinomiale
plt.subplot(1, 3, 3)
x_fit = np.linspace(data[:, 0].min(), x_rot, 200)
plt.plot(x_fit, p(x_fit), label=f" grade {p.order}")
plt.scatter(data[:, 0], data[:, 1], s=10, alpha=0.5, label="original data")
plt.axvline(x=x_rot, color='red', linestyle='--', label="25% cord")
plt.title("Fit")
plt.grid(True)
plt.legend()

plt.tight_layout()
plt.show()
























