import os
import time

def matrix_beep():
    for _ in range(5):
        os.system("echo -e '\a'")
        time.sleep(0.5)

matrix_beep()


def colorful_text():
    os.system("echo -e '\033[1;32mHello, colored world!\033[0m'")

colorful_text()

def user_dialog():
    os.system("dialog --msgbox 'Ceci est une boîte de dialogue simple en utilisant Dialog' 10 30")

user_dialog()
