## Résolution générale du Rubik's Cube

Légendes pour les rotations :

* Rotation de devant horaire : F
* Rotation de devant anti-horaire : F'
* Rotation de derrière horaire : B
* Rotation de derrière anti-horaire : B'
* Rotation gauche horaire : L
* Rotation gauche anti-horaire : L'
* Rotation droite horaire : R
* Rotation droite anti-horaire : R'
* Rotation du dessus horaire : U
* Rotation du dessus anti-horaire : U'
* Rotation du dessous horaire : D
* Rotation du dessous anti-horaire : D'

#### La 1ère étape est de réaliser la croix blanche :
* Placez uniquement la croix blanche au dessus sans vous soucier des arêtes
* Placez deux pièces de même couleur sur deux faces consécutives sous la croix blanche comme sur l'image
* Placez vous sur la face de droite non réalisée avec la face blanche au dessus
* Faites **R U R' U R 2U R'**

Vous arriverez à ce résultat:

![Croix Blanche](croix_blanche.png)


#### Le 2ème objectif est le placement et l'orientation des coins blancs :
Les coins blancs restants appartiennent forcément à un coin (en bas ou en haut), il en reste trois différents, un par couleur, chacun doit être placé sur sa couleur correspondante
* Mettez un des coins blancs non placés en dessous de son emplacement et regardez le.
* Puis faites **R'D' R D** jusqu'à ce qu'il soit bien placé.
* Refaites ça pour chaque coins.

Résultat voulu

![Coins Blancs](coins_blanc.png)

#### Le 3ème aspect du cube à faire est la deuxième ligne :
Vous avez donc la face blanche et la 1ère ligne voire plus avec de la chance
* Maintenant regardez les arêtes pour complétez la 2 lignes le but va de les placer
* placez l'arête voulu sur sa face correspondante vous aurez alors un T, la face de droite doit être l'autre couleur de l'arête, vous voulez donc lever en haut à droit cette arête.
* Regardez la face de gauche avec le T et la face blanche au dessus
* Faites **D' R' D R D' F D' F'**

Si jamais la couleur ne peut être placer sur la face de gauche placer la à droite

faites **D L D' L' D' F' D F**

Résultat attendu

![2ème ligne](ligne_2.png)

#### La 4ème phase est la croix jaune qui doit être réalisé en deux étapes :
1ère objectif, croix jaunes
* Placez la face jaune en haut
* Trois cas possibles :
    * Vous n'avez aucune figure visible 
        * Faites **F R U R' U' F'** 3 fois
    * Vous avez une sorte de L que vous placez en haut en gauche de la face jaune
        * Faites **F R U R' U' F'** 2 fois
    * Vous avez une ligne droite que vous mettez à l'horizontale sur la face jaune
        * Faites **F R U R' U' F'** 2 fois
* Vous avez la crois jaune sans ces arêtes
* N'en placez qu'une sur sa couleur en tournant la face jaune et placez vous devant cette face
* Faites **R 2U R' U' R U' R'** jusqu'à ce que la face jaune ai ses bonnes arêtes

Au final, on obtiendra

![Croix Jaunes](croix_jaune.png)

#### La 5ème étape est de bien placer les coins jaunes
Le principe est de trouver au moins un coin bien placé mais mal positionné (ou bien positionné)

* Suite à ça placez ce coin dans l'angle inférieur droit ou à droite si il y en a 2
* Faites **L' U R U' L U R' U'** jusqu'à ce qu'ils soient tous bien placés l'orientation viendra après

![Coins Jaune](coins_jaunes.png)

#### L'orientation des coins afin de compléter le Rubik's Cube
On va finir le cube sur cette étape
* Placez vous face à un coin mal orienté
* Faites **R'D' R D** jusqu'à que ce coin soit bien orienté
* Puis tourner la face au dessus (la jaune) pour placer au même endroit qu'avant un autre coin mal orienté
* Refaites l'algorithme jusqu'à qu'ils soient tous bien orientés 
* puis tourner les U ou D pour finir le cube

![Rubik's Cube](Rubik's_Cube.png)

**FIN**