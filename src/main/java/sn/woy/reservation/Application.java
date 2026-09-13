package sn.woy.reservation;

import sn.woy.reservation.domain.Reservation;
import sn.woy.reservation.domain.Salle;
import sn.woy.reservation.domain.TypeSalle;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de démonstration (Incrément 3) : crée des salles et des
 * réservations en mémoire. Aucun Stream, aucune base de données.
 */
public class Application {

    public static void main(String[] args) {

        List<Salle> salles = new ArrayList<>();
        salles.add(new Salle(1L, "A101", "Bâtiment A", 40, TypeSalle.SALLE_COURS));
        salles.add(new Salle(2L, "A102", "Bâtiment A", 25, TypeSalle.SALLE_TP));
        salles.add(new Salle(3L, "B201", "Bâtiment B", 15, TypeSalle.SALLE_REUNION));
        salles.add(new Salle(4L, "Amphi 1", "Bâtiment C", 200, TypeSalle.AMPHITHEATRE));
        salles.add(new Salle(5L, "B205", "Bâtiment B", 10, TypeSalle.SALLE_SOUTENANCE));

        System.out.println("=== Salles ===");
        for (Salle salle : salles) {
            System.out.println(salle);
        }

        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation(1L, salles.get(0), "Awa Diop",
                LocalDate.of(2026, 9, 15), LocalTime.of(8, 0), LocalTime.of(10, 0)));
        reservations.add(new Reservation(2L, salles.get(3), "Ibrahima Fall",
                LocalDate.of(2026, 9, 16), LocalTime.of(14, 0), LocalTime.of(16, 0)));
        reservations.get(0).confirmer();

        System.out.println("\n=== Réservations ===");
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }

        System.out.println("\n=== Association bidirectionnelle ===");
        Salle amphi = salles.get(3);
        System.out.println(amphi.getNom() + " a " + amphi.getNbReservations() + " reservation(s).");
    }
}
