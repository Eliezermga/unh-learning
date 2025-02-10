#coding:utf-8

class Humain:
    """classe qui definit un humain"""

    lieu_habitation = "Terre"

    def __init__(self, nom, age):
        self.nom = nom
        self.age = age
    def parler(self, message):
        print("{} a dit : {}".format(self.nom, message))#self = methode standard

    def changer_planete(cls, nouvelle_planete):#cls = methode de classe
        Humain.lieu_habitation = nouvelle_planete
    
    changer_planete = classmethod(changer_planete)

    def definition():
        print("l'humain est mauvais")
    definition = staticmethod(definition)

#programme principal
h1 = Humain("Jason", 25)

h1.parler("bonjour à tous")
print("Planete actuelle : {}".format(Humain.lieu_habitation))

Humain.changer_planete("MArs")

print("Planete actuelle : {}".format(Humain.lieu_habitation))
Humain.definition()