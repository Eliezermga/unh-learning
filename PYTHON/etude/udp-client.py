# udp-client.py

from socket import *
serverName = "127.1.1.0"
serverPort = 12000
clientSocket = socket(AF_INET,SOCK_DGRAM)
message = input("Input lowercase sentence:")
clientSocket.sendto(message.encode(),(serverName, serverPort))
modifiedMessage, serverAddress = clientSocket.recvfrom(2048)
print (modifiedMessage.decode())
clientSocket.close()
