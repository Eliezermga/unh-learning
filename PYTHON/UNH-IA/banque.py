from experta import *

class EvaluationPret(KnowledgeEngine):

    @DefFacts()
    def initier_faits(self):
        yield Fact(income=0)
        yield Fact(age=0)
        yield Fact(loan_duration=0)
        yield Fact(amount_requested=0)
        yield Fact(ratio_dette_income=0)

    @Rule(Fact(income=P(lambda r: r < 2000)))
    def refuser_pret_income(self):
        print("Prêt refusé : income mensuel insuffisant.")
        self.declare(Fact(eligibilite="refuse"))

    @Rule(Fact(income=P(lambda r: r >= 2000)), Fact(ratio_dette_income=P(lambda ratio: ratio > 0.4)))
    def refuser_pret_ratio(self):
        print("Prêt refusé : Ratio dette/income trop élevé.")
        self.declare(Fact(eligibilite="refuse"))

    @Rule(Fact(age=P(lambda age: age < 18)))
    def refuser_pret_age(self):
        print("Prêt refusé : Le client est mineur.")
        self.declare(Fact(eligibilite="refuse"))

    @Rule(Fact(amount_requested=P(lambda montant: montant > 50000)))
    def verification_approfondie(self):
        print("Vérification approfondie nécessaire : Montant demandé supérieur à 50 000€.")

    @Rule(Fact(loan_duration=P(lambda duree: duree > 10)), Fact(income=P(lambda r: r <= 5000)))
    def refuser_pret_duree(self):
        print("Prêt refusé : Durée du prêt trop longue avec un income insuffisant.")
        self.declare(Fact(eligibilite="refuse"))

    @Rule(Fact(income=P(lambda r: r >= 2000)),
          Fact(ratio_dette_income=P(lambda ratio: ratio <= 0.4)),
          Fact(age=P(lambda age: age >= 18)),
          Fact(loan_duration=P(lambda duree: duree <= 10) | Fact(income=P(lambda r: r > 5000))),
          NOT(Fact(eligibilite="refuse")))
    def accorder_pret(self):
        print("Prêt accordé.")
        self.declare(Fact(eligibilite="accorde"))

# Exemple d'utilisation
if __name__ == "__main__":
    moteur = EvaluationPret()
    moteur.reset()

    # Données du client
    moteur.declare(Fact(income=3000))
    moteur.declare(Fact(age=25))
    moteur.declare(Fact(loan_duration=8))
    moteur.declare(Fact(amount_requested=40000))
    moteur.declare(Fact(ratio_dette_income=0.3))

    moteur.run()
