import time
import subprocess

# Durée du bip (en secondes)
duration = 0.01

# Durée de la pause (en secondes)
pause = 0.01

# Frequence de la trame 5Vbipe (en Hz)
trame_frequency = 2000

# Nombre de trames à envoyer
n_trames = 5

# Convertir la durée en secondes en nombre de cycles de la trame
cycle_duration = duration / (1 / trame_frequency)

# Nombre de cycles '0' et '1' dans une trame
cycles_zero = int(cycle_duration / 2)
cycles_one = int(cycle_duration / 2)

# Seulement les nombres pairs de cycles '0' et '1' sont nécessaires
if cycles_zero % 2 != 0:
    cycles_zero += 1
if cycles_one % 2 != 0:
    cycles_one += 1

# Seulement la moitié du nombre de cycles '0' et '1' sont nécessaires
cycles_zero = int(cycles_zero / 2)
cycles_one = int(cycles_one / 2)

# Generer le son 5Vbipe
def generate_sound(frequency, duration):
    command = f'mode con: cols=1 lines=1 && echo @echo off > temp.bat && echo mode con: cols=80 lines=50 >> temp.bat && echo fsutil file setvaliddata temp.wav > nul >> temp.bat && echo echo off >> temp.bat && echo for /l %%a in (1,1,%d) do ( >> temp.bat && echo echo set /a "x=!random! %% 32768, y=!random! %% 32768, z=x*x*1.0+y*y*1.0+2000.0*x*y*1.0/(x*x*1.0+y*y*1.0+1.0e4)" >> temp.bat && echo if !z! <= 10000 ( >> temp.bat && echo echo set freq=!random! %% %d >> temp.bat && echo echo echo t = !time! >> temp.bat && echo echo ping -n 2 127.0.0.1 > nul >> temp.bat && echo echo mode con: cols=1 lines=1 >> temp.bat && echo echo set /a "freq=freq+1, t=1000000*(%d-%time%), t=t*8388608/t, freq=freq*t/1000000+256>> temp.bat && echo echo freq=!freq! >> temp.bat && echo echo echo ping -n 2 127.0.0.1 > nul >> temp.bat && echo echo mode con: cols=80 lines=50 >> temp.bat && echo fsutil file seteof temp.wav %d >> temp.bat && echo del temp.bat >> temp.bat && start /min temp.bat)' % (duration * 10, frequency, frequency, frequency)
    subprocess.run(command, shell=True)

# Envoyer une trame 5Vbipe
def send_trame():
    for i in range(cycles_zero):
        generate_sound(0, pause)
        time.sleep(pause)

    for i in range(cycles_one):
        generate_sound(trame_frequency, pause)
        time.sleep(pause)

# Emitter le son 5Vbipe
generate_sound(0, duration)

# Envoyer les trames 5Vbipe
for i in range(n_trames):
    send_trame()
    time.sleep(duration)