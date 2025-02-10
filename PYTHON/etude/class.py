class Humain:
    """
    clase des ètres humains
    """
    humains_crees = 0

    def __init__(self, c_prenom, c_age):
        print("création d'un Humain...")
        self.prenom = c_prenom
        self.age = c_age
        Humain.humains_crees +=1

print("Lancement du programme...")

h1 = Humain("jojo", 34)
print("prenom de h1 :{}".format(h1.prenom))
print("age de h1 :{}".format(h1.age))

h2 = Humain("albert", 14)
print("prenom de h2 :{}".format(h2.prenom))
print("age de h2 :{}".format(h2.age))
print("Humains existants : {}".format(Humain.humains_crees))

h3 = Humain("Eliezer",20)
print("Humains existants : {}".format(Humain.humains_crees))