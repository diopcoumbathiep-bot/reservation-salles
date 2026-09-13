# Gestion des réservations de salles universitaires (fil rouge — Séance 1)

Application Java console (sans framework, sans base de données à ce
stade) permettant de représenter les salles de l'université et leurs
réservations. Données créées en mémoire dans `Application`.

- **Technologie :** Java 21, Maven
- **Travail :** individuel
- **Version courante :** v0.3.0

## Domaine

```
AbstractEntity
 |
 +---- Salle
 |
 +---- Reservation
```

`Salle 1 -------- 0..* Reservation` : une salle peut recevoir plusieurs
réservations ; une réservation concerne une seule salle. `Reservation`
porte une référence directe vers `Salle`.

`Salle` et `Reservation` sont déclarées `final` : le domaine ne demande
aucun sous-type de ces entités.

## Lancer le projet

```bash
mvn clean compile
mvn clean package
java -jar target/reservation-salles.jar
```

## Structure

```
reservation-salles/
├── pom.xml
├── docs/
│   ├── diagramme.md
│   └── devlog.md
└── src/main/java/sn/woy/reservation/
    ├── Application.java
    └── domain/
        ├── AbstractEntity.java
        ├── Salle.java
        ├── Reservation.java
        ├── TypeSalle.java
        └── StatutReservation.java
```

## Stratégie Git

Voir `docs/devlog.md` — incréments 1 (`v0.1.0`), 2 (`v0.2.0`) et
3 (`v0.3.0`) tels que décrits en section 18 du support de cours.
