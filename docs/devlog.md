# DevLog — Réservation de salles universitaires (fil rouge)

## Ce que j'ai compris de Git et GitHub
Git suit localement l'historique (commits, branches, tags) ; GitHub
héberge ce dépôt à distance pour le partager avec le formateur.

## Branches créées et leur rôle
- `main` : versions stables uniquement, aucun développement direct.
- `develop` : intégration des trois incréments.
- `feature/00-tooling` : Git + Maven + README.
- `feature/01-domain` : AbstractEntity, Salle, Reservation, enums.
- `feature/02-memory-basics` : données en mémoire.

## Tags posés
- `v0.1.0` — outillage Git et Maven initialisé.
- `v0.2.0` — modèle objet du domaine terminé.
- `v0.3.0` — premières données en mémoire disponibles.

## Ce que Maven apporte au projet
Structure standard, `mvn compile`/`mvn test`/`mvn package`, gestion de
la version Java cible dans `pom.xml`, sans classpath manuel.

## Diagramme de classes
Voir `docs/diagramme.md`.

## Pourquoi AbstractEntity est abstraite
Elle ne porte que l'identifiant, un socle technique commun à Salle et
Reservation, sans exister en tant que concept métier autonome.

## Pourquoi Salle et Reservation sont final
Le domaine ne demande aucun sous-type de ces entités ; `final` évite un
héritage accidentel sans justification métier.

## Relation Salle / Reservation et cardinalité
`Salle 1 -------- 0..* Reservation` : une salle peut recevoir plusieurs
réservations ; une réservation concerne une seule salle. `Reservation`
porte une référence directe vers `Salle`.

## Difficulté rencontrée
(à compléter selon votre propre expérience : par exemple la détection
des chevauchements de créneaux horaires sur une même salle.)

---

## Stratégie Git appliquée (incréments 1, 2, 3 — section 18 du cours)

### Incrément 1 — Initialiser l'environnement de travail

```bash
mkdir reservation-salles
cd reservation-salles
git init
git branch -M main
git switch -c develop
git switch -c feature/00-tooling

# après création du .gitignore
git add .gitignore
git commit -m "chore: initialiser le depot Git"

# après création/configuration du projet Maven
git add pom.xml src/
git commit -m "chore: initialiser le projet Maven"

# après création du README
git add README.md
git commit -m "docs: ajouter le README initial"

mvn clean compile
git status

git switch develop
git merge --no-ff feature/00-tooling
git branch -d feature/00-tooling
git tag -a v0.1.0 -m "Outillage Git et Maven initialise"
```

### Incrément 2 — Modéliser le domaine

```bash
git switch develop
git switch -c feature/01-domain

git add src/main/java
git commit -m "feat: ajouter AbstractEntity"

git add src/main/java
git commit -m "feat: modeliser Salle et Reservation"

git add src/main/java
git commit -m "feat: ajouter les enums du domaine"

git add README.md docs/
git commit -m "docs: documenter les relations du domaine"

mvn clean compile
git switch develop
git merge --no-ff feature/01-domain
git branch -d feature/01-domain
git tag -a v0.2.0 -m "Modele objet du domaine termine"
```

### Incrément 3 — Ajouter les premières données en mémoire

```bash
git switch develop
git switch -c feature/02-memory-basics

git add src/main/java
git commit -m "feat: ajouter les salles de demonstration"

# si le code d'initialisation est réorganisé sans changer le besoin
git add src/main/java
git commit -m "refactor: organiser l initialisation des donnees"

git add README.md docs/
git commit -m "docs: documenter le modele objet"

mvn clean compile
git switch develop
git merge --no-ff feature/02-memory-basics
git branch -d feature/02-memory-basics
git tag -a v0.3.0 -m "Premieres donnees en memoire disponibles"
```

### Publication sur GitHub

```bash
git remote add origin <URL_GITHUB>
git push -u origin main
git push -u origin develop
git push origin --tags
```

### Contrôle final de l'historique

```bash
git status
git log --oneline --graph --decorate --all
git tag
```
