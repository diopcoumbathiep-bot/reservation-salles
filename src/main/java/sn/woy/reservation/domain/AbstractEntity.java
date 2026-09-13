package sn.woy.reservation.domain;

/**
 * Classe abstraite de base pour toutes les entités du domaine
 * (Salle, Reservation). Porte uniquement l'identifiant, seule
 * caractéristique réellement commune. Ne peut pas être instanciée
 * directement.
 */
public abstract class AbstractEntity {

    protected final Long id;

    protected AbstractEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
