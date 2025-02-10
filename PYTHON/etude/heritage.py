#coding:utf-8


#classe mere
class Vehicule:

    def __init__(self, nom_vehicule, qte_essence):
        self.nom = nom_vehicule
        self.essence = qte_essence
    def se_deplacer(self):
        print("vehicule {} est en marche...".format(self.nom))

#classe flle
class Voiture(Vehicule):
    def __init__(self, nom_voiture, essence, puissance):
        Vehicule.__init__(self, nom_voiture, essence)
        self.puissance = puissance
#main
voiture1 = Voiture("Toyota supra", 90, 430)
voiture1.se_deplacer()
print(voiture1.puissance,"ch")