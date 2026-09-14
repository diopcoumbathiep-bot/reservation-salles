# Questions de compréhension (section 20 du support de cours)

1. **Quelle différence existe entre Git et GitHub ?**
   Git est l'outil local qui gère commits/branches/tags, même sans
   Internet. GitHub est une plateforme distante qui héberge ces dépôts
   et ajoute des fonctionnalités de collaboration (Pull Requests, Issues).

2. **À quoi sert `git add` ?**
   À placer les modifications sélectionnées dans la staging area, pour
   préparer le prochain commit.

3. **Qu'est-ce qu'un commit ?**
   Un instantané cohérent du projet, correspondant à une intention
   claire (ex : `feat: ...`).

4. **Pourquoi utiliser une branche feature ?**
   Pour isoler le développement d'une fonctionnalité sans toucher à
   `develop`/`main` tant qu'elle n'est pas terminée.

5. **Quelle différence entre merge et tag ?**
   Le merge fusionne l'historique d'une branche dans une autre ; le tag
   marque un commit précis comme jalon, sans rien fusionner.

6. **Que signifie v0.2.0 ?**
   Modèle objet du domaine terminé (MINOR : nouvelle capacité
   compatible avec la version précédente).

7. **Pourquoi main ne doit-elle contenir que des versions stables ?**
   Pour garantir qu'à tout moment le code sur `main` compile et
   fonctionne, sans y mélanger du travail en cours.

8. **Quel est l'intérêt de Maven ?**
   Standardiser la structure du projet, automatiser compilation, tests
   et packaging, gérer le classpath.

9. **Quel fichier décrit un projet Maven ?**
   `pom.xml`.

10. **Quelle différence entre un attribut et une méthode ?**
    L'attribut décrit l'état d'un objet ; la méthode représente un
    comportement/une opération sur cet état.

11. **Qu'est-ce que l'encapsulation ?**
    Protéger l'état interne d'un objet (attributs `private`/`protected`)
    et contrôler sa modification via des méthodes qui préservent la
    cohérence.

12. **Quelle différence entre static et instance ?**
    Un membre static appartient à la classe (partagé, accessible via
    `NomClasse.membre`) ; un membre d'instance appartient à un objet
    particulier.

13. **Dans quel cas l'héritage est-il pertinent ?**
    Quand une vraie relation « est un » existe, stable et justifiable
    par le domaine, et que le comportement commun a du sens dans la
    classe parent.

14. **Quel risque présente une mauvaise hiérarchie d'héritage ?**
    Couplage fort, modification du parent qui casse les sous-classes,
    héritage utilisé juste pour éviter de dupliquer du code sans vraie
    relation « est un ».

15. **Pourquoi AbstractEntity ne peut-elle pas être instanciée ?**
    C'est une classe `abstract`, incomplète par nature — elle ne
    représente pas un concept métier autonome, seulement un socle
    commun.

16. **Quelle différence entre surcharge et redéfinition ?**
    La surcharge (overloading) définit plusieurs méthodes de même nom
    avec des paramètres différents (choix à la compilation) ; la
    redéfinition (overriding) fournit dans une sous-classe une nouvelle
    implémentation d'une méthode héritée avec la même signature.

17. **Quelle relation existe entre Salle et Reservation ?**
    `Salle 1 -------- 0..* Reservation` — une salle peut avoir
    plusieurs réservations, une réservation concerne une seule salle ;
    association bidirectionnelle, pas d'héritage.