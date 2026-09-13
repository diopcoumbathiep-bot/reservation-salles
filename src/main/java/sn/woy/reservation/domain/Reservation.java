package sn.woy.reservation.domain;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Une réservation concerne une seule salle (référence directe vers
 * Salle - association bidirectionnelle avec Salle.reservations).
 * Déclarée final : aucun sous-type n'est demandé par le domaine.
 */
public final class Reservation extends AbstractEntity {

    private String demandeur;
    private LocalDate date;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private StatutReservation statut;
    private Salle salle;

    public Reservation(Long id, Salle salle, String demandeur,
                        LocalDate date, LocalTime heureDebut, LocalTime heureFin) {
        super(id);
        this.demandeur = demandeur;
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.statut = StatutReservation.EN_ATTENTE;
        setSalle(salle);
    }

    public void confirmer() {
        this.statut = StatutReservation.CONFIRMEE;
    }

    public void annuler() {
        this.statut = StatutReservation.ANNULEE;
    }

    // --- Accesseurs ---

    public Salle getSalle() {
        return salle;
    }

    /** Maintient la cohérence de l'association bidirectionnelle avec Salle. */
    public void setSalle(Salle salle) {
        this.salle = salle;
        if (salle != null && !salle.getAllReservations().contains(this)) {
            salle.addReservations(this);
        }
    }

    public String getDemandeur() {
        return demandeur;
    }

    public void setDemandeur(String demandeur) {
        this.demandeur = demandeur;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }

    public LocalTime getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(LocalTime heureFin) {
        this.heureFin = heureFin;
    }

    public StatutReservation getStatut() {
        return statut;
    }

    public void setStatut(StatutReservation statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Reservation{id=" + getId() + ", salle=" + (salle != null ? salle.getNom() : "null")
                + ", demandeur='" + demandeur + "', date=" + date
                + ", heureDebut=" + heureDebut + ", heureFin=" + heureFin
                + ", statut=" + statut + "}";
    }
}
