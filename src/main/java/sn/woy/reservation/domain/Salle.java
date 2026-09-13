package sn.woy.reservation.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Salle extends AbstractEntity {

    private String nom;
    private String batiment;
    private int capacite;
    private boolean active;
    private TypeSalle type;
    private final List<Reservation> reservations;

    public Salle(Long id, String nom, String batiment, int capacite, TypeSalle type) {
        super(id);
        this.nom = nom;
        this.batiment = batiment;
        this.type = type;
        this.active = true;
        this.reservations = new ArrayList<>();
        changerCapacite(capacite);
    }

    
    public void changerCapacite(int nouvelleCapacite) {
        if (nouvelleCapacite <= 0) {
            throw new IllegalArgumentException("Capacité invalide pour la salle " + nom);
        }
        this.capacite = nouvelleCapacite;
    }

    public boolean peutAccueillir(int nombrePersonnes) {
        return active && nombrePersonnes <= capacite;
    }

    public void addReservations(Reservation reservation) {
        if (!reservations.contains(reservation)) {
            reservations.add(reservation);
        }
        if (reservation.getSalle() != this) {
            reservation.setSalle(this);
        }
    }

    public void removeReservations(Reservation reservation) {
        reservations.remove(reservation);
    }

    public List<Reservation> getAllReservations() {
        return Collections.unmodifiableList(reservations);
    }

    public int getNbReservations() {
        return reservations.size();
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getBatiment() {
        return batiment;
    }

    public void setBatiment(String batiment) {
        this.batiment = batiment;
    }

    public int getCapacite() {
        return capacite;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public TypeSalle getType() {
        return type;
    }

    public void setType(TypeSalle type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Salle{id=" + getId() + ", nom='" + nom + "', batiment='" + batiment
                + "', capacite=" + capacite + ", active=" + active + ", type=" + type
                + ", nbReservations=" + getNbReservations() + "}";
    }
}
