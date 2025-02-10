from experta import *

class GestionEnergie(KnowledgeEngine):
    @DefFacts()
    def initier_faits(self):
        yield Fact(temperature=15)
        yield Fact(humidite=20)
        yield Fact(heure=8)

    @Rule(Fact(temperature=P(lambda t: t < 18)), 
          NOT(Fact(chauffage='actif')), 
          NOT(Fact(temperature=P(lambda t: t > 22))))
    def activer_chauffage(self):
        print("Activer le chauffage")
        self.declare(Fact(chauffage='actif'))

    @Rule(Fact(temperature=P(lambda t: t > 25)), NOT(Fact(climatisation='actif')))
    def activer_climatisation(self):
        print("Activer la climatisation")
        self.declare(Fact(climatisation='actif'))

    @Rule(Fact(humidite=P(lambda h: h > 70)), NOT(Fact(humidificateur='actif')))
    def activer_humidificateur(self):
        print("Activer l'humidificateur")
        self.declare(Fact(humidificateur='actif'))

    @Rule(Fact(heure=P(lambda he: he >= 22 or he <= 6)), NOT(Fact(eclairage='actif')))
    def activer_eclairage(self):
        print("Réduction de l'éclairage")
        self.declare(Fact(eclairage='actif'))

    @Rule(Fact(temperature=P(lambda t: t > 22)), Fact(chauffage='actif'))
    def desactiver_chauffage(self):
        print("Désactiver le chauffage")
        self.declare(Fact(chauffage='inactif'))


if __name__ == "__main__":
    moteur = GestionEnergie()
    moteur.reset()
    moteur.declare(Fact(temperature=28))
    moteur.run()
    moteur.reset()
    moteur.declare(Fact(humidite=80))
    moteur.run()
    moteur.reset()
    moteur.declare(Fact(heure=3))
    moteur.run()