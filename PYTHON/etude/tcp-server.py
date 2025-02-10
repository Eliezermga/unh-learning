# tcp-server.py

from socket import *
 créer une socket
# AF_INET == ipv4
# AF_INET == ipv6# SOCK_STREAM ==
serverPort = 12000
serverSocket = socket(AF_INET, SOCK_STREAM)
# lier une socket à un port sur le serveur
serverSocket.bind(("", serverPort))
serverSocket.listen(5)
print("Le serveur est prêt à recevoir")
while True:
    connectionSocket, addr = serverSocket.accept()
    while True:
        sentence = connectionSocket.recv(1024).decode()
        if not sentence:
            break
        capitalizedSentence = sentence.upper()
        connectionSocket.send(capitalizedSentence.encode())
    connectionSocket.close()