package org.main;

import org.main.biblo.Livre;
import org.main.humain.Auteur;
import org.main.service.AuteurManagerService;
import org.main.service.LivreManagerService;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        AuteurManagerService service1 = new AuteurManagerService();
        Auteur a= new Auteur();
        //a.setNom("ss");
        //a.setEmail("rebe@gmail.com");
       // service1.ajoutAuteur(a);

        a.setId(6);
        service1.supprimerAuteur(a);
        System.out.printf(".");
//
//        livremanagerImpl service = new livremanagerImpl();
//       livres l = new livres();
//      //  l.setTitre("the black man");
//        //l.setDate_publication(new Date(2023,03,23));
//        //l.setNbres_pages(23);
//        //service.ajoutLivres(l);
//       // TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
//         //to see how IntelliJ IDEA suggests fixing it.
//      //  System.out.println(".");
//       l.setId(40);
//       service.deleteLivres(l);
//        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
//        // to see how IntelliJ IDEA suggests fixing it.
//       // System.out.println(".");




        LivreManagerService service2 = new LivreManagerService();
        Livre p = new Livre();
//        p.setId(33);
//        p.setTitre("the hug man");
//        p.setDate_publication(new Date(2023,03,23));
//        p.setNbres_pages(23);
     //   service2.updateLivres(p);
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println(".");


        List<Livre> livre = service2.getLivres();

        for (Livre livres : livre ) {
       // TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
        //for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
        System.out.println(livres.getId()+" "+ livres.getTitre()+" "+ livres.getDatePublication()+" "+ livres.getNombrePages());
        }

        AuteurManagerService service3 = new AuteurManagerService();
        List<Auteur> auteure = service3.getAuteurs();
        for (Auteur auteur : auteure ) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            //for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
          //  System.out.println(Auteur.getId() + " " + Auteur.getNom() + " " + Auteur.getEmail() + " ");
        }
          //  publicationManagerImpl service8 = new publicationManagerImpl();
           // Publication k= new Publication();
//            k.setIdAuteur(5);
//            k.setIdLivre(42);
          //  service8.savePublication(k);
            //System.out.println(".");

    }
}