#coding:utf-8

"""
Opérateurs de comparaison : == (égal à)
                            !=(différent de de)
                            < (inferieur)
                            > (superieur)
conditions multiples : and (et)
                       or (ou)
                       in / nor in (Dans / pas dans)
"""

"""
identiifiant = "Eliezer"
mot_de_passe = "password123"

print('interface de connexion')

user_id = input('entrez votre identifiant : ')
user_password = input("Entrez votre mot de passe : ")

if user_id == identiifiant and user_password == mot_de_passe:
    print("Vous êtes conectés, bienvenue", user_id)
else:
    print("imbecille")

lettre_hasrd = "b"

if lettre_hasrd in "aeiouy":
    print("C'est une voyelle")
else:
    print("c'est une consonne")


compteur = 0
while compteur < 5:
    print("je suis une phrase")
    compteur +=1

def dire_bonjour(nom_personne, message_personne):
    print("{} : {}".format(nom_personne, message_personne))
dire_bonjour("ELiezer", "bonjour à tous")
dire_bonjour("Sam", "salut les gars")

ttc= lambda prixHT : prixHT + (prixHT * 16 / 100)

print(ttc(200))
"""
from math import *

resultat = sqrt(25)
print(resultat)

sinus = sin(45)
print(sinus)


A=12
b=34
c=A+b
if A>b:
    print("ok")
else:
    print(A)