package org.example.anaMaGF;

import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Tache t1 = new Tache("Coder", "Tache en Java", LocalDate.of(2025, 8, 30), Tache.Statut.TERMINE);
        Tache t2 = new Tache( "Coder la classe", "Créer la classe Tache en Java", LocalDate.of(2025, 8, 30), Tache.Statut.A_FAIRE);
        System.out.println(t1);
        //t1.setStatut(Tache.Statut.EN_COURS);
        System.out.println(t1);
        System.out.println("Est en retard ? " + t1.estEnRetard());
        System.out.println("Statut tache : " + t1.getStatut());
        System.out.println(t1.getTitre());
        Project p1 = new Project(1, "p1", "Coder le projet",LocalDate.of(2025, 7 ,30), LocalDate.of(2025, 10, 30), Project.StatutProjet.EN_ATTENTE);
        p1.ajouterTache(t1);
        p1.ajouterTache(t2);
        System.out.println(p1.listerTachesParStatut(Tache.Statut.TERMINE));
        p1.supprimerTacheParId(1);
        System.out.println(p1.listerTachesParStatut(Tache.Statut.A_FAIRE));

    }
}