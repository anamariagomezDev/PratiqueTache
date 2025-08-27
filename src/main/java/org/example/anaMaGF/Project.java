package org.example.anaMaGF;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Project {

    public enum StatutProjet {EN_COURS, TERMINE, EN_ATTENTE}
    private static final AtomicInteger COMPTEUR_ID = new AtomicInteger(0);


   // private static int compteurId = 0;
    private final int id;
    private String nom;
    private String description;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private StatutProjet statut;

    private final List<Tache> taches = new ArrayList<>();

    public Project(String nom, String description,
                   LocalDate dateDebut, LocalDate dateFin, StatutProjet statut) {
        if (dateDebut != null && dateFin != null && dateFin.isBefore(dateDebut)) {
            throw new IllegalArgumentException("dateFin ne peut pas être avant dateDebut");
        }
        this.id  = COMPTEUR_ID.incrementAndGet();
        /* this.id = ++compteurId; */
        this.nom = Objects.requireNonNull(nom);
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statut = Objects.requireNonNullElse(statut, StatutProjet.EN_ATTENTE);
    }

    public int getId() {
        return id;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        if (dateDebut != null && dateFin != null && dateFin.isBefore(dateDebut)) {
            throw new IllegalArgumentException("dateFin ne peut pas être avant dateDebut");
        }
        this.dateFin = dateFin;
    }

    public StatutProjet getStatut() {
        return statut;
    }

    public void setStatut(StatutProjet statut) {
        this.statut = statut;
    }

    public List<Tache> getTaches() {
        //Retourne une copie non modifiable pour protéger l'état interne.
        return Collections.unmodifiableList(taches);
    }

    public void ajouterTache(Tache t) {
        if(t != null) taches.add(t);
    }

    public boolean supprimerTacheParId(int id){
        return taches.removeIf(t -> t.getId() == id);
    }

    public List<Tache> listerTachesParStatut(Tache.Statut statut) {
        List<Tache> res = new ArrayList<>();
        for (Tache t : taches) {
            if (t.getStatut() == statut) {
                res.add(t);
            }
        }
        return res;
    }

           /*public List<Tache> listerTachesParStatut(Tache.Statut statut) {
    return taches.stream()
                 .filter(t -> t.getStatut() == statut)
                 .toList(); // en Java 16+ ; sinon .collect(Collectors.toList())}*/


    /* Pourcentage [0.0, 1.0] de tâches terminées. */
    public double progression(){
        if(taches.isEmpty()) return 0.0;
        long done = taches.stream()
                         .filter(t -> t.getStatut() == Tache.Statut.TERMINE)
                         .count();
        return (double) done / taches.size();
    }
    public void cloreSiTermine() {
            if (!taches.isEmpty() && taches.stream().allMatch(t -> t.getStatut() == Tache.Statut.TERMINE)) {
                this.statut = StatutProjet.TERMINE;
        }
    }


    /*Un project est en retard si non terminé et que la dateFin est passée*/
    public boolean estEnRetard(){
        return (this.statut != StatutProjet.TERMINE)
                && this.dateFin != null
                && (this.dateFin.isBefore(LocalDate.now()));
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id == project.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
