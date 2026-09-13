# Diagramme de classes — Réservation de salles

**Statut : conçu par l'étudiant, en attente de validation par le formateur**
(voir section 17 du support de cours : le mini-diagramme doit être validé
avant que le code ne soit considéré comme définitif).

```
AbstractEntity
  # id
  + getId(): long
        △
        |  (héritage)
   ┌────┴────┐
Salle                          Reservation
- nom                          - demandeur
- batiment                     - date
- capacite                     - heureDebut
- active                       - heureFin
- type : TypeSalle              - statut : StatutReservation
- reservations                 - salle
- nbReservations
+ peutAccueillir(): bool       + confirmer()
+ changerCapacite()            + annuler()
+ get/setNom()                 + get/setSalle()
+ get/setBatiment()            + get/setDate()
+ getCapacite()                + get/setHeureDebut()
+ get/setActive()              + get/setHeureFin()
+ get/setType()                + get/setStatut()
+ getNbReservations()          + get/setDemandeur()
+ getAllReservations()         + toString()
+ addReservations()
+ removeReservations()
+ toString()
```

## Relations
- `AbstractEntity <|-- Salle` et `AbstractEntity <|-- Reservation` (héritage).
- `Salle "1" -- "0..*" Reservation` (association bidirectionnelle :
  `Salle.reservations` et `Reservation.salle`).
- `Salle ..> TypeSalle`, `Reservation ..> StatutReservation` (dépendance
  vers les enums).

Ce diagramme sert de base de travail pour le code des classes
`AbstractEntity`, `Salle` et `Reservation`, en attendant sa validation
officielle par le formateur. Note : `capacite` n'a volontairement qu'un
seul point d'entrée en écriture (`changerCapacite()`), pas de `setCapacite()`
séparé — pour éviter un setter brut qui contournerait la validation
(cf. section 14.4 du cours sur l'encapsulation).
