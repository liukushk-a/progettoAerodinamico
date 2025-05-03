''' Script di prova con cui posso verificare come funzionano i comandi che chiami su OpenVSP '''

import os
import openvsp_config
import openvsp as vsp

script_dir = os.path.dirname(os.path.abspath(__file__))

nomeGeometria = input("Inserisci il nome del file .vsp3 (senza .vsp3): ")
vsp.ReadVSPFile(os.path.join(script_dir, f"{nomeGeometria}.vsp3"))

mainWing_id = vsp.FindGeom("WingGeom_main", 0)

xsec_surf = vsp.GetXSecSurf(mainWing_id, 0)

print(xsec_surf)
