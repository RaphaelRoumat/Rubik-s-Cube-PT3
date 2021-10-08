[Retour à l'index](../../readme.md)

## Résolution algorithmique du Rubik's Cube

_Cette documentation fait suite à celle sur la [résolution générale du Rubik's Cube](../solve/solve.md), il est important de l'avoir lue pour passer à la résolution algorithmique et la partie code._

## Sommaire

- [Objectifs de cette étape](#objectifs-de-cette-étape)
- [Choix du langage](#choix-du-langage)
- [Principes généraux](#principes-généraux)
- [Résolution](#résolution)
- [Conclusion](#conclusion)

## Objectifs de cette étape

Cette étape consiste en la recherche d'une suite algorithmique de mouvements sur le Rubik's Cube menant à sa résolution systématique, à partir d'une configuration possible déterminée à partir d'un [dispositif de détection des couleurs](../color_detection/color_detection.md).

Il est crucial que les résultats obtenus soient valables dans 100% des cas, la suite de mouvement obtenue devra ensuite être exécutée par le robot et ses pinces mécaniques afin de résoudre le Rubik's Cube physiquement.

## Choix du langage

Le langage choisi pour la partie algorithmique est Java natif, les packages utilisés sont ceux de base (notamment ceux provenant de java.util).
Ce choix est une question d'affinité/simplicité, car l'approche relève plus de l'algorithmie brute que du code en lui-même, Java est seulement le support utilisé et pas l'objet déterminant pour cette partie.

## Principes généraux

La structure du programme est définie comme tel:

- On part d'une représentation virtuelle du cube, sous forme textuelle dans un fichier créé par la partie détection des couleurs qu'on va placer dans une variable pour pouvoir l'altérer plus facilement
- On possède plusieurs méthodes correspondants aux rotations qui modifient la représentation virtuelle en direct (passée en argument et en retour)
- On va analyser la situation du cube après chaque étape en regardant la simulation
- En fonction de cette analyse, on définit la marche à suivre pour compléter l'étape (à travers une batterie de conditions)
- On peut potentiellement utiliser des méthodes de vérification pour chaque sous-étape, afin d'optimiser les résultats
- Une étape est terminée quand elle est validée arbitrairement par une méthode
- Chaque mouvement effectué dans l'étape validée est ajouté à une liste
- Cette liste est passée dans une méthode de refactoring qui va tenter de la réduire en simplifiant les mouvements inutiles par exemple
- Le fichier textuel contenant la représentation initiale est mis à jour pour y ajouter la suite de mouvements obtenus

### Représentation virtuelle du cube

L'émulation du cube doit être facilement manipulable par le programme, c'est pourquoi nous avons fait le choix d'une matrice bidimensionnelle, la première dimension représentant les faces du cube, et la seconde les carrés composant chaque face.

Le type utilisé est l'Array native d'Integer, sous cette forme: `int[][]`

Première dimension (faces): **Taille de 6**

Seconde dimension (carrés de couleur): **Taille de 9**

Le code couleur utilisé est le suivant:

- ![#00ff00](https://via.placeholder.com/30/00ff00/000000?text=1)
- ![#ff7f00](https://via.placeholder.com/30/ff7f00/000000?text=2)
- ![#0000ff](https://via.placeholder.com/30/0000ff/000000?text=3)
- ![#f03c15](https://via.placeholder.com/30/f03c15/000000?text=4)
- ![#ffff00](https://via.placeholder.com/30/ffff00/000000?text=5)
- ![#ffffff](https://via.placeholder.com/30/ffffff/000000?text=6)

Donc par exemple, la configuration par défaut (cube résolu) sera celle-ci:

```java
int[][] cubeResolu = new int[][]{
    {1,1,1,1,1,1,1,1,1}, // face verte
    {2,2,2,2,2,2,2,2,2}, // face orange
    {3,3,3,3,3,3,3,3,3}, // face bleue
    {4,4,4,4,4,4,4,4,4}, // face rouge
    {5,5,5,5,5,5,5,5,5}, // face jaune
    {6,6,6,6,6,6,6,6,6} // face blanche
};
```

_N.B: En Java les index commencent à zéro, donc si par exemple on veut appeler la première face de notre cube, elle sera notée `cube[0]` et non pas `cube[1]`, contrairement au color code qui lui commence à 1 car il ne s'agit pas d'un index, par exemple:_

```java
int carre = cube[0][4]; // Comme on appelle un index correspondant à une face, on commence par 0

boolean isVert = cube[0][4] == 1 // En revanche ici on veut tester un color code, donc on commence par 1
```

### Emulation des mouvements

La notation conventionnelle relative au Rubik's Cube est la suivante:

Un mouvement est défini par une lettre (représentant une face), par défaut il en existe 6 (un par face).
La rotation d'une face est effectuée dans le sens horaire, dans le cas contraire on ajoute un `'` (prime) à la lettre correspondant à la face.

Les mouvements possibles et qui seront représentés dans le code sont les suivants:

- U (et U'): UP
- D (et D'): DOWN
- F (et F'): FRONT
- B (et B'): BACK
- L (et L'): LEFT
- R (et R'): RIGHT

**!! DANS NOTRE CONTEXTE LES MOUVEMENTS SONT ABSOLUS ET NON PAS RELATIFS !!**

Le placement absolu est le suivant:

- Face verte visible devant
- Face blanche en haut
- Face jaune en bas
- Face orange à gauche
- Face rouge à droite
- Face bleue derrière

Pour émuler chaque mouvement possible, nous allons devoir altérer la matrice de manière très précise, il s'agira ici d'utiliser un cache dont on se servira pour inverser des positions dans la matrice, nous prendrons en exemple le mouvement UP ici:

## Résolution

(...)

### Croix blanche et ses parités

(...)

### Couronne blanche

(...)

### Couronne médiane

(...)

### Croix jaune

(...)

### Couronne jaune

(...)

## Conclusion

(...)
