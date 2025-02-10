# tcp-client.py

from socket import *
serverName = "127.1.1.0"
serverPort = 12000
clientSocket = socket(AF_INET, SOCK_STREAM)
clientSocket.connect((serverName,serverPort))
sentence = input("Input lowercase sentence:")
print (sentence)
#text = raw_input("Enter a Letter: ").lower() print text
clientSocket.send(sentence.encode())
modifiedSentence = clientSocket.recv(1024)
print ("From Server:", modifiedSentence.decode())
clientSocket.close()
