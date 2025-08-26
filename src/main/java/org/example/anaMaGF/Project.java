package org.example.anaMaGF;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Project {

    public enum StatutProjet {EN_COURS, TERMINE, EN_ATTENTE}

    ;

    private int id;
    private String nom;
    private String description;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private StatutProjet statut;

    private final List<Tache> taches = new ArrayList<>();

    public Project(int id, String nom, String description,
                   LocalDate dateDebut, LocalDate dateFin, StatutProjet statut) {
        if (dateDebut != null && dateFin != null && dateFin.isBefore(dateDebut)) {
            throw new IllegalArgumentException("dateFin ne peut pas être avant dateDebut");
        }
        this.id = id;
        this.nom = Objects.requireNonNull(nom);
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statut = Objects.requireNonNullElse(statut, StatutProjet.EN_ATTENTE);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return taches;
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
                 .toList(); // en Java 16+ ; sinon .collect(Collectors.toList())
}*/


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
