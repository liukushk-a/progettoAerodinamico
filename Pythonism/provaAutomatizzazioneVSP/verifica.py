import sys
sys.path.append("/home/liukushka/OpenVSP/build/python_api")   # o dov’È vsp.py
import openvsp as vsp

print("Analyses disponibili:")
for name in vsp.ListAnalysis():
    print("  •", name)
