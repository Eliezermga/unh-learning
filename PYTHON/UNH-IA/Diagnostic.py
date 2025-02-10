from experta import *
from random import choice

class DiagnosticMedical(KnowledgeEngine):
    
    def initier_facts(self):
        yield Fact(fievre=False)
        yield Fact(toux=False)
        yield Fact(mal_de_gorge=False)
        yield Fact(essoufflement=False)
        yield Fact(douleur_thoracique=False)
        
    @Rule(Fact(fievre=True), Fact(toux=True))
    def diagnostic_grippe(self):
        print("Il se pourrait que vous souffriez de la Grippe")
        self.declare(Fact(maladie='Grippe'))
        
    @Rule(Fact(fievre=True), Fact(mal_de_gorge=True))
    def diagnostic_rhume(self):
        print("Il se pourrait que vous souffriez du Rhume")
        self.declare(Fact(maladie='Rhume'))
        
    @Rule(Fact(essoufflement=True), Fact(toux=True))
    def diagnostic_covid19(self):
        print("Il se pourrait que vous souffriez du COVID-19")
        self.declare(Fact(maladie='COVID-19'))
        
    @Rule(Fact(douleur_thoracique=True), Fact(maladie='Grippe'))
    def diagnostic_pneumonie_grippe(self):
        print("Il se pourrait que vous souffriez de la Pneumonie")
        self.declare(Fact(maladie='Pneumonie'))
        
    @Rule(Fact(douleur_thoracique=True), Fact(maladie='COVID-19'))
    def diagnostic_pneumonie_covid19(self):
        print("Il se pourrait que vous souffriez de la Pneumonie")
        self.declare(Fact(maladie='Pneumonie'))
        
    @Rule(Fact(maladie=MATCH.maladie))
    def afficher_diagnostic(self, maladie):
        print(f"Maladie confirmée est : {maladie}")

    

if __name__=='__main__':
    medical = DiagnosticMedical()
    medical.reset()
    medical.declare(Fact(maladie='Grippe', douleur_thoracique=True))
    medical.run()