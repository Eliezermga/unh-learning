import tkinter as tk
from tkinter import messagebox
from tkcalendar import DateEntry
import json

# Liste pour stocker les produits
produits = []

# Liste pour stocker les ventes
ventes = []

# Fonction pour ajouter un produit à la liste
def ajouter_produit(nom, prix, quantite):
    try:
        # Convertir le prix en float et la quantité en int
        prix = float(prix)
        quantite = int(quantite)
        # Ajouter le produit à la liste des produits
        produits.append({"nom": nom, "prix": prix, "quantite": quantite})
        # Afficher un message de succès
        messagebox.showinfo("Succès", f"Produit {nom} ajouté avec succès.")
    except ValueError:
        # Afficher un message d'erreur si la conversion échoue
        messagebox.showerror("Erreur", "Veuillez entrer un prix et une quantité valides.")

# Fonction pour afficher tous les produits
def afficher_produits():
    if not produits:
        # Afficher un message si aucun produit n'est ajouté
        messagebox.showinfo("Produits", "Aucun produit n'a été ajouté.")
    else:
        # Créer une chaîne de caractères contenant les informations de tous les produits
        produits_text = "\n".join([f"Nom: {produit['nom']}, Prix: {produit['prix']}, Quantité: {produit['quantite']}" for produit in produits])
        # Afficher les informations des produits
        messagebox.showinfo("Produits", produits_text)

# Fonction pour enregistrer une vente
def enregistrer_vente(client, produit, quantite, date):
    try:
        # Convertir la quantité en int
        quantite = int(quantite)
        # Vérifier si le stock est suffisant pour la vente
        if verifier_stock(produit, quantite):
            # Ajouter la vente à la liste des ventes
            ventes.append({"client": client, "produit": produit, "quantite": quantite, "date": date})
            # Afficher un message de succès
            messagebox.showinfo("Succès", f"Vente à {client} enregistrée avec succès.")
            # Mettre à jour le stock du produit
            mettre_a_jour_stock(produit, quantite)
        else:
            # Afficher un message d'erreur si le stock est insuffisant
            messagebox.showerror("Erreur", f"Quantité insuffisante pour {produit}.")
    except ValueError:
        # Afficher un message d'erreur si la conversion échoue
        messagebox.showerror("Erreur", "Veuillez entrer une quantité valide.")

# Fonction pour afficher toutes les ventes
def afficher_ventes():
    if not ventes:
        # Afficher un message si aucune vente n'est enregistrée
        messagebox.showinfo("Ventes", "Aucune vente n'a été enregistrée.")
    else:
        # Créer une chaîne de caractères contenant les informations de toutes les ventes
        ventes_text = "\n".join([f"Client: {vente['client']}, Produit: {vente['produit']}, Quantité: {vente['quantite']}, Date: {vente['date']}" for vente in ventes])
        # Afficher les informations des ventes
        messagebox.showinfo("Ventes", ventes_text)

# Fonction pour mettre à jour le stock d'un produit après une vente
def mettre_a_jour_stock(nom_produit, quantite_vendue):
    for produit in produits:
        if produit['nom'] == nom_produit:
            # Réduire la quantité du produit vendu
            produit['quantite'] -= quantite_vendue
            return

# Fonction pour vérifier si le stock est suffisant pour une vente
def verifier_stock(nom_produit, quantite):
    for produit in produits:
        if produit['nom'] == nom_produit:
            # Vérifier si la quantité demandée est disponible
            return produit['quantite'] >= quantite
    return False

# Fonction pour charger les données des produits et des ventes depuis un fichier JSON
def charger_donnees():
    global produits, ventes
    try:
        with open('donnees.json', 'r') as f:
            data = f.read()
            if data.strip():  # Vérifier si le fichier n'est pas vide
                data = json.loads(data)
                # Charger les produits et les ventes depuis le fichier
                produits = data.get('produits', [])
                ventes = data.get('ventes', [])
    except FileNotFoundError:
        pass
    except json.JSONDecodeError:
        # Afficher un message d'erreur si le fichier JSON n'est pas valide
        messagebox.showerror("Erreur", "Le fichier JSON est vide ou n'est pas au format valide.")

# Fonction pour sauvegarder les données des produits et des ventes dans un fichier JSON
def sauvegarder_donnees():
    with open('donnees.json', 'w') as f:
        json.dump({'produits': produits, 'ventes': ventes}, f)

# Interface pour ajouter un produit
def interface_ajout_produit():
    def ajouter():
        # Ajouter le produit avec les informations saisies
        ajouter_produit(entry_nom.get(), entry_prix.get(), entry_quantite.get())
        # Effacer les champs de saisie
        entry_nom.delete(0, tk.END)
        entry_prix.delete(0, tk.END)
        entry_quantite.delete(0, tk.END)

    # Créer une nouvelle fenêtre pour ajouter un produit
    fenetre_ajout = tk.Toplevel()
    fenetre_ajout.title("Ajouter Produit")
    fenetre_ajout.geometry("300x200")

    # Créer et positionner les labels et champs de saisie
    tk.Label(fenetre_ajout, text="Nom").grid(row=0, column=0, padx=10, pady=10)
    tk.Label(fenetre_ajout, text="Prix").grid(row=1, column=0, padx=10, pady=10)
    tk.Label(fenetre_ajout, text="Quantité").grid(row=2, column=0, padx=10, pady=10)

    entry_nom = tk.Entry(fenetre_ajout)
    entry_prix = tk.Entry(fenetre_ajout)
    entry_quantite = tk.Entry(fenetre_ajout)

    entry_nom.grid(row=0, column=1, padx=10, pady=10)
    entry_prix.grid(row=1, column=1, padx=10, pady=10)
    entry_quantite.grid(row=2, column=1, padx=10, pady=10)

    # Créer et positionner le bouton pour ajouter le produit
    tk.Button(fenetre_ajout, text='Ajouter', command=ajouter).grid(row=3, column=1, padx=10, pady=10, sticky=tk.W)

# Interface pour enregistrer une vente
def interface_enregistrement_vente():
    def enregistrer():
        if not entry_client.get() or not entry_produit.get() or not entry_quantite.get() or not entry_date.get():
            # Afficher un message d'erreur si des champs sont vides
            messagebox.showerror("Erreur", "Tous les champs doivent être remplis.")
            return
        # Enregistrer la vente avec les informations saisies
        enregistrer_vente(entry_client.get(), entry_produit.get(), entry_quantite.get(), entry_date.get())
        # Effacer les champs de saisie
        entry_client.delete(0, tk.END)
        entry_produit.delete(0, tk.END)
        entry_quantite.delete(0, tk.END)
        entry_date.delete(0, tk.END)

    # Créer une nouvelle fenêtre pour enregistrer une vente
    fenetre_enregistrement = tk.Toplevel()
    fenetre_enregistrement.title("Enregistrer Vente")
    fenetre_enregistrement.geometry("300x250")

    # Créer et positionner les labels et champs de saisie
    tk.Label(fenetre_enregistrement, text="Client").grid(row=0, column=0, padx=10, pady=10)
    tk.Label(fenetre_enregistrement, text="Produit").grid(row=1, column=0, padx=10, pady=10)
    tk.Label(fenetre_enregistrement, text="Quantité").grid(row=2, column=0, padx=10, pady=10)
    tk.Label(fenetre_enregistrement, text="Date").grid(row=3, column=0, padx=10, pady=10)

    entry_client = tk.Entry(fenetre_enregistrement)
    entry_produit = tk.Entry(fenetre_enregistrement)
    entry_quantite = tk.Entry(fenetre_enregistrement)
    entry_date = DateEntry(fenetre_enregistrement, date_pattern='y-mm-dd')

    entry_client.grid(row=0, column=1, padx=10, pady=10)
    entry_produit.grid(row=1, column=1, padx=10, pady=10)
    entry_quantite.grid(row=2, column=1, padx=10, pady=10)
    entry_date.grid(row=3, column=1, padx=10, pady=10)

    # Créer et positionner le bouton pour enregistrer la vente
    tk.Button(fenetre_enregistrement, text='Enregistrer', command=enregistrer).grid(row=4, column=1, padx=10, pady=10, sticky=tk.W)

# Interface pour afficher les produits
def interface_affichage_produits():
    afficher_produits()

# Interface pour afficher les ventes
def interface_affichage_ventes():
    afficher_ventes()

# Fonction pour rechercher un produit par son nom
def rechercher_produit(nom):
    for produit in produits:
        if produit['nom'].lower() == nom.lower():
            return produit
    return None

# Interface pour rechercher un produit
def interface_recherche_produit():
    def rechercher():
        nom_produit = entry_recherche.get()
        produit = rechercher_produit(nom_produit)
        if produit:
            interface_modification_produit(produit)
        else:
            messagebox.showinfo("Recherche", f"Produit {nom_produit} non trouvé.")

    # Créer une nouvelle fenêtre pour la recherche de produit
    fenetre_recherche = tk.Toplevel()
    fenetre_recherche.title("Rechercher Produit")
    fenetre_recherche.geometry("300x150")

    # Créer et positionner les labels et champs de saisie
    tk.Label(fenetre_recherche, text="Nom du produit").grid(row=0, column=0, padx=10, pady=10)
    entry_recherche = tk.Entry(fenetre_recherche)
    entry_recherche.grid(row=0, column=1, padx=10, pady=10)

    # Créer et positionner le bouton pour rechercher le produit
    tk.Button(fenetre_recherche, text='Rechercher', command=rechercher).grid(row=1, column=1, padx=10, pady=10, sticky=tk.W)

# Interface pour modifier ou supprimer un produit
def interface_modification_produit(produit):
    def modifier():
        if messagebox.askyesno("Confirmation", "Voulez-vous vraiment modifier ce produit?"):
            produit['nom'] = entry_nom.get()
            produit['prix'] = float(entry_prix.get())
            produit['quantite'] = int(entry_quantite.get())
            messagebox.showinfo("Succès", "Produit modifié avec succès.")
            fenetre_modification.destroy()

    def supprimer():
        if messagebox.askyesno("Confirmation", "Voulez-vous vraiment supprimer ce produit?"):
            produits.remove(produit)
            messagebox.showinfo("Succès", "Produit supprimé avec succès.")
            fenetre_modification.destroy()

    # Créer une nouvelle fenêtre pour la modification ou suppression de produit
    fenetre_modification = tk.Toplevel()
    fenetre_modification.title("Modifier/Supprimer Produit")
    fenetre_modification.geometry("320x270")

    # Créer et positionner les labels et champs de saisie
    tk.Label(fenetre_modification, text="Nom").grid(row=0, column=0, padx=10, pady=10)
    tk.Label(fenetre_modification, text="Prix").grid(row=1, column=0, padx=10, pady=10)
    tk.Label(fenetre_modification, text="Quantité").grid(row=2, column=0, padx=10, pady=10)

    entry_nom = tk.Entry(fenetre_modification)
    entry_nom.insert(0, produit['nom'])
    entry_prix = tk.Entry(fenetre_modification)
    entry_prix.insert(0, produit['prix'])
    entry_quantite = tk.Entry(fenetre_modification)
    entry_quantite.insert(0, produit['quantite'])

    entry_nom.grid(row=0, column=1, padx=10, pady=10)
    entry_prix.grid(row=1, column=1, padx=10, pady=10)
    entry_quantite.grid(row=2, column=1, padx=10, pady=10)

    # Créer et positionner les boutons pour modifier et supprimer le produit
    tk.Button(fenetre_modification, text='Modifier', command=modifier).grid(row=3, column=1, padx=10, pady=10, sticky=tk.W)
    tk.Button(fenetre_modification, text='Supprimer', command=supprimer).grid(row=4, column=1, padx=10, pady=10, sticky=tk.W)

# Fonction principale
def main():
    charger_donnees()

    # Créer la fenêtre principale
    root = tk.Tk()
    root.title("Gestion de Magasin")
    root.geometry("400x300")

    # Créer un cadre pour les boutons
    frame = tk.Frame(root)
    frame.pack(pady=20)

    # Créer et positionner les boutons
    tk.Button(frame, text='Ajouter Produit', command=interface_ajout_produit, width=20).grid(row=0, column=0, padx=10, pady=10)
    tk.Button(frame, text='Enregistrer Vente', command=interface_enregistrement_vente, width=20).grid(row=1, column=0, padx=10, pady=10)
    tk.Button(frame, text='Afficher Produits', command=interface_affichage_produits, width=20).grid(row=2, column=0, padx=10, pady=10)
    tk.Button(frame, text='Afficher Ventes', command=interface_affichage_ventes, width=20).grid(row=3, column=0, padx=10, pady=10)
    tk.Button(frame, text='Rechercher Produit', command=interface_recherche_produit, width=20).grid(row=4, column=0, padx=10, pady=10)

    # Sauvegarder les données et fermer l'application en cas de fermeture de la fenêtre
    root.protocol("WM_DELETE_WINDOW", lambda: (sauvegarder_donnees(), root.destroy()))
    root.mainloop()

# Exécuter la fonction principale si le script est exécuté directement
if __name__ == "__main__":
    main()
