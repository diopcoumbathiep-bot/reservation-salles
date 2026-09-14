# DevLog — Réservation de salles universitaires (fil rouge)

## Ce que j'ai compris de Git et GitHub
Git suit localement l'historique du projet (commits, branches, tags),
même sans connexion Internet. GitHub héberge ce dépôt à distance
(https://github.com/diopcoumbathiep-bot/reservation-salles) et permet
de le partager avec le formateur via `git push`.

## Branches créées et leur rôle
- `main` : versions stables uniquement, aucun développement direct dessus.
- `develop` : branche d'intégration des trois incréments.
- `feature/00-tooling` : mise en place Git + Maven + README (fusionnée puis supprimée).
- `feature/01-domain` : AbstractEntity, Salle, Reservation, enums (fusionnée puis supprimée).
- `feature/02-memory-basics` : données de démonstration en mémoire (fusionnée puis supprimée).

## Tags posés
- `v0.1.0` — outillage Git et Maven initialisé.
- `v0.2.0` — modèle objet du domaine terminé.
- `v0.3.0` — premières données en mémoire disponibles.

## Ce que Maven apporte au projet
Structure standard du projet, compilation (`mvn compile`), packaging,
et gestion de la version Java cible (21) directement dans `pom.xml`,
sans avoir à construire le classpath à la main.

## Diagramme de classes
Voir `docs/diagramme.md` — conçu par moi-même, encore en attente de
validation par le formateur au moment de la rédaction de ce DevLog.

## Pourquoi AbstractEntity est abstraite
Elle ne représente aucun concept métier autonome à elle seule (une
« entité » sans plus de précision n'a pas de sens) ; elle sert
uniquement de socle pour factoriser l'identifiant, commun à Salle et
Reservation.

## Pourquoi Salle et Reservation sont final
Le domaine ne demande aucun sous-type de ces classes ; les déclarer
`final` empêche un héritage accidentel qui n'aurait pas de justification
métier.

## Relation Salle / Reservation et cardinalité
`Salle 1 -------- 0..* Reservation` : une salle peut recevoir plusieurs
réservations, une réservation concerne une seule salle. L'association
est bidirectionnelle : `Reservation` porte une référence vers `Salle`,
et `Salle` maintient la liste de ses réservations.

## Difficulté rencontrée et sa résolution
En créant les branches `main` et `develop` avant tout premier commit
(exactement comme indiqué dans le support de cours), `git switch develop`
échouait avec `fatal: invalid reference: develop`. La raison : en Git,
une branche ne devient une référence réelle qu'à partir de son premier
commit — comme `develop` et `main` n'avaient jamais reçu de commit
directement, elles n'existaient plus une fois qu'on les avait quittées.
J'ai résolu ça en créant `develop` et `main` une fois les premiers
commits faits sur `feature/00-tooling` (`git branch develop` /
`git branch main`), ce qui a fait que la toute première fusion
(Incrément 1) s'est faite en fast-forward plutôt qu'avec un commit de
fusion visible — les fusions suivantes (Incréments 2 et 3), elles,
ont bien produit un commit de merge explicite puisque `develop` avait
entre-temps son propre historique.

Deuxième petite difficulté : lors de l'Incrément 2, j'ai regroupé par
erreur `Application.java` avec `Salle.java`/`Reservation.java` dans le
commit `feat: modeliser Salle et Reservation`, alors qu'il aurait dû
apparaître seulement à l'Incrément 3. Plutôt que de réécrire l'historique
déjà fusionné et taggé (`v0.2.0`), j'ai ajouté du contenu réellement
nouveau à l'Incrément 3 (une troisième réservation, annulée) pour que
le commit `feat: ajouter les salles de demonstration` corresponde à un
vrai changement de code, cohérent avec son message.

---

## Stratégie Git réellement appliquée (historique complet)

Voir le dépôt public :
https://github.com/diopcoumbathiep-bot/reservation-salles

```
* Merge branch 'feature/02-memory-basics' into develop   (tag: v0.3.0)
|\
| * refactor: organiser l initialisation des donnees
| * docs: documenter le modele objet
| * feat: ajouter les salles de demonstration
|/
*   Merge branch 'feature/01-domain' into develop        (tag: v0.2.0)
|\
| * docs: documenter les relations du domaine
| * feat: ajouter les enums du domaine
| * feat: modeliser Salle et Reservation
| * feat: ajouter AbstractEntity
|/
* docs: ajouter le README initial                        (tag: v0.1.0, main)
* chore: initialiser le projet Maven
* chore: initialiser le depot Git
```

## Démarche de modélisation guidée (section 17 du support de cours)

1. **Deux entités principales :** Salle et Reservation.
2. **Informations d'identification :** chacune identifiée par son `id`
   (hérité d'AbstractEntity) ; Salle aussi par nom+batiment, Reservation
   par la combinaison salle/date/horaire.
3. **Attributs de Salle :** nom, batiment, capacite, active, type
   (TypeSalle), plus la liste `reservations` (association).
4. **Attributs de Reservation :** demandeur, date, heureDebut, heureFin,
   statut (StatutReservation), plus la référence `salle` (association).
5. **Relation Salle/Reservation :** association bidirectionnelle —
   Reservation connaît sa Salle, Salle connaît la liste de ses
   réservations.
6. **Cardinalité :** Salle 1 -------- 0..* Reservation.
7. **Relation « est un » ?** Non — Reservation n'est pas une Salle,
   donc pas d'`extends` entre les deux, seulement une association.
8. **Éléments communs pour AbstractEntity :** uniquement l'identifiant
   (`id`) et `getId()`.
9. **Classes final :** Salle et Reservation — aucun sous-type demandé
   par le domaine, `final` évite un héritage sans justification métier.