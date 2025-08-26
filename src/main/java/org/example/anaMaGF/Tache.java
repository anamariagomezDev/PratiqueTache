package org.example.anaMaGF;

import java.time.LocalDate;

public class Tache {

    private int id;
    private String titre;
    private String description;
    private LocalDate dateEcheance;
    public enum Statut {A_FAIRE, EN_COURS, TERMINE}
    private Statut statut;

    public Tache(int id, String titre, String description, LocalDate dateEcheance, Statut statut) {
        if(titre == null || titre.isBlank()) throw new IllegalArgumentException("titre obligatoire");
        if(statut == null) throw new IllegalArgumentException("statut obligatoire");
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.dateEcheance = dateEcheance;
        this.statut = statut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    //Retourne true si la tâche n'est pas terminée et que l'échéance est dépassée
    public boolean estEnRetard(){
        return (this.statut != Statut.TERMINE)
                && this.dateEcheance != null
                && (this.dateEcheance.isBefore(LocalDate.now()));
    }

    @Override
    public String toString() {
        return "Tache{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", dateEcheance=" + dateEcheance +
                ", statut=" + statut +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tache tache = (Tache) o;
        return id == tache.id;
    }
    @Override
    public int hashCode() {
        return id;
    }


}
