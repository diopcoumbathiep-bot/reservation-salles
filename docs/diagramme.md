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

## Choix de collection pour les données en mémoire (Incrément 3)

`Application.java` utilise une `ArrayList<Salle>` et une
`ArrayList<Reservation>` pour stocker les objets créés en mémoire.
Une `ArrayList` a été choisie plutôt qu'une autre collection Java
parce que :
- l'ordre d'insertion doit être conservé (les salles/réservations
  s'affichent dans l'ordre où elles ont été créées) ;
- l'accès par index (`get(i)`) est utile pour désigner une salle ou
  une réservation précise dans la démonstration ;
- aucune contrainte d'unicité ni de tri n'est demandée à ce stade
  (pas besoin d'un `Set` ou d'une `Map`).