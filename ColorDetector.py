from typing import Tuple
import cv2
import numpy as np
import matplotlib.pyplot as plt
import sys


Image = np.ndarray
Square = np.ndarray


class ColorDetector:
    """
    Détecteur des carrés des faces d'un Rubik's Cube.

    Les carrés détectés sont ensuites utilisés pour former des groupes de couleurs.

    # Paramètres

    ### paths:
    chemins d'accès vers les images à analyser. S'il n'y a pas 6 images la détection des groupes
    de couleurs seras impossible.

    ### show_images: 
    option debug pour afficher les carrés détectés sur les images.

    ### show_rgb: 
    option debug pour afficher la représentation 3D des couleurs détectées avant groupage
    """
    paths: list
    images = []
    colors = []
    groups: list[list[int]]
    sharping_kernel = np.array([[0, -1, 0], [-1, 5, -1], [0, -1, 0]])
    morphic_kernel = np.ones((5, 5), np.uint8)

    show_images: bool
    show_rgb: bool

    def __init__(self, paths: list[str], show_images: bool = False, show_rgb: bool = False) -> None:
        """
        Création de l'objet ColorDetector.

        # Paramètres

        ### paths:
        chemins d'accès vers les images à analyser. S'il n'y a pas 6 images la détection des groupes
        de couleurs seras impossible.

        ### show_images: 
        option debug pour afficher les carrés détectés sur les images.

        ### show_rgb: 
        option debug pour afficher la représentation 3D des couleurs détectées avant groupage
        """
        self.show_images = show_images
        self.show_rgb = show_rgb
        self.paths = paths.copy()
        for k in range(len(paths)):
            self.images.append(cv2.imread(self.paths[k]))

    # Affiche les chemins d'accès vers les images

    def printPaths(self) -> None:
        """
        Affiche les chemins d'accés actuellement utilisés par le détecteur
        """
        print(self.paths)

    # Affiche les couleurs extraites

    def printColors(self) -> None:
        """
        Affiche les couleurs extraites des carrés détectés
        """
        for k in range(0, len(self.colors)):
            print(f"face {k + 1}:")
            for color in self.colors[k]:
                print(color)
    def printGroupsToFile(self, filename: str) -> None:
        """
        Transfer les groupes de couleurs vers un fichier.
        
        # Paramètres
        ### filename:
        nom du fichier où stocker les groupes de couleurs.
        """
        original_stdout = sys.stdout    
 
        with open(f'{filename}', 'w') as f:
            sys.stdout = f 
            
            for face in self.groups:
                print(f"{face[0]} {face[1]} {face[2]} {face[3]} {face[4]} {face[5]} {face[6]} {face[7]} {face[8]}")
            # Reset the standard output
            sys.stdout = original_stdout

        
        
    # Fonction de debbugage pour afficher le résultats des carrés sur une image
    def showSquares(self, image: Image, squares: list[Square]) -> None:
        """
        Dessine les carrés sur l'image correspondante et l'affiche à l'écran.

        Les carrés sont numérotés dans l'ordre d'apparition de la liste.

        Les coins des carrés sont numérotés dans l'ordre des coordonnées fournies.

        # Paramètres

        ### image:
        image où les carrés doivent être dessinés.

        ### squares:
        liste des carrés à afficher sur l'image provenant de la détection des carrés via detectSquares()
        """
        final = image.copy()
        cv2.drawContours(final, squares, -1, (0, 255, 0), 3)
        for square in squares:
            i = 0
            for coord in square:
                i = i + 1
                final = cv2.circle(final, (coord[0], coord[1]), radius=10, color=(
                    255, 255, 255), thickness=-1)
                cv2.putText(final, "{}".format(
                    i), (coord[0], coord[1]), cv2.FONT_HERSHEY_SIMPLEX, 0.5, (0, 0, 0), 2)

        number = 0
        for square in squares:
            x, y, _w, _h = cv2.boundingRect(square)
            cv2.putText(final, "#{}".format(number + 1), (x, y - 5),
                        cv2.FONT_HERSHEY_SIMPLEX, 0.7, (0, 255, 0), 2)
            number += 1

        cv2.imshow('detected squares', final)
        cv2.waitKey(0)
        cv2.destroyAllWindows()

        # Remplie self.colors avec les couleurs des faces

    def showColors(self, rgb_colors: list[list[int]]) -> None:
        """
        Affiche dans un graph 3D les couleurs détectées 
        sur les différentes faces du Rubik's Cube.

        # Paramètres

        ### rgb_colors:
        Liste de couleurs rgb.

        exemple: [[255, 0, 234], [123, 43, 234]]
        """
        fig = plt.figure(figsize=(4, 4))

        ax = fig.add_subplot(projection='3d')

        for color in rgb_colors:
            ax.scatter(color[0], color[1], color[2], color=[
                       color[0]/255., color[1]/255., color[2]/255.])
        plt.show()

    def showGroups(self) -> None:
        """
        Affiche les groupes de couleurs après analyse des images.

        Ne pas appeler avant analyse des images.
        """
        for k in range(0, len(self.groups)):
            group = self.groups[k]
            print(f"Face {k + 1}")
            print(f"{group[0]} | {group[1]} | {group[2]} ")
            print("_________")
            print(f"{group[3]} | {group[4]} | {group[5]} ")
            print("_________")
            print(f"{group[6]} | {group[7]} | {group[8]} ")

    def processImages(self) -> None:
        """
        Analyse les images de la détection des carrés à la création des groupes de couleurs.

        Cette fonction utilise les chemins d'accès fournis à la création et ne créer les groupes
        que si les conditions de création sont remplis: nombre d'images correct (6) et nombre de carrés
        détectés par face correcte (9).
        """
        for k in range(0, len(self.images)):
            print(f"Image n°{k + 1}")
            image = self.images[k]

            prepared, resized = self.prepareImage(image=image)
            squares = self.detectSquares(prepared_image=prepared)
            squares = self.sortSquaresPoints(squares=squares)
            squares = self.removeInclosedSquares(squares=squares)
            if len(squares) == 9:
                print(f"Image n°{k + 1} a 9 carrés")

                squares = self.correctSquaresCoords(squares=squares)
                squares = self.sortSquares(squares=squares)

                if self.show_images:
                    self.showSquares(resized, squares)

                self.colors.append(self.extractColors(
                    squares=squares, image=resized))

            else:
                print(
                    f"Erreur de détection de carrés sur l'image n°{k+1} path:{self.paths[k]}, carrés détectés {len(squares)}/9")

        if len(self.colors) == 6:
            print("Création des groupes")
            self.groups = self.createColorGroups()
        else:
            print(
                "Pas assez d'image pour créer les groupes de couleurs, veuillez fournir 6 images")

    def prepareImage(self, image: Image) -> Tuple[Image, Image]:
        """
        Prépare l'image à la détection de formes.

        Cette fonction utilise la réduction de bruit, augmentation de netteté
        et détection de bord.

        # Paramètres

        ### image:
        image a préparer.

        # Renvoie

        une image de taille réduite et l'image utilisable pour la détection de formes.
        """
        scale_percent = 30  # percent of original size
        width = int(image.shape[1] * scale_percent / 100)
        height = int(image.shape[0] * scale_percent / 100)
        dim = (width, height)
        image = cv2.resize(image, dim, interpolation=cv2.INTER_AREA)
        # supression du bruit

        denoised = cv2.medianBlur(image, 5)
        sharpen = cv2.filter2D(denoised, -1, self.sharping_kernel)

        # detection des bords
        edge = cv2.Canny(sharpen, 75, 150)

        # dilatation des bords
        dilated_edges = cv2.dilate(edge, self.morphic_kernel, iterations=3)

        return dilated_edges, sharpen

    def detectSquares(self, prepared_image: Image) -> list[Square]:
        """
        Détect les carrés sur l'image fournie.

        # Paramètres

        ### prepared_image:
        image prête pour la détection de forme, obtenable par la fonction prepareImage()

        # Renvoie
        Liste de carrés detectés de la forme:

        [[[x1,y1],[x2,y2],[x3,y3],[x4,y4]] * nombre de carrés détéctés]
        """
        # détection et filtration des mauvais résultats
        contours, _hierarchy = cv2.findContours(
            prepared_image, cv2.RETR_LIST, cv2.CHAIN_APPROX_SIMPLE)
        squares = []

        for cnt in contours:
            cnt_len = cv2.arcLength(cnt, True)
            cnt = cv2.approxPolyDP(cnt, 0.02*cnt_len, True)
            if len(cnt) == 4 and cv2.contourArea(cnt) > 1000 and cv2.isContourConvex(cnt):
                cnt = cnt.reshape(-1, 2)
                # remplacer par une version avec la racine carré pour calculer la distance entre les points
                sides_lenghts = [np.sqrt((cnt[i][0] - cnt[(i + 1) % 4][0])**2+(
                    cnt[i][1] - cnt[(i + 1) % 4][1])**2) for i in range(4)]
                max_diff = np.max(sides_lenghts) - np.min(sides_lenghts)

                if np.max(sides_lenghts) < prepared_image.shape[0]*0.4 and max_diff < np.min(sides_lenghts) * 0.20:
                    squares.append(cnt)
        return squares

    def removeInclosedSquares(self, squares: list[Square]) -> list[Square]:
        """
        Supprime les carrés qui ont un ou plusieurs carrés à l'intérieur d'eux.

        Cette fonction doit être utilisée uniquement sur des carrés dont les points ont été
        triés par la fonction sortSquaresPoints().

        # Paramètres

        ### squares:
        Liste de carrés de la forme:
        [[[x1,y1],[x2,y2],[x3,y3],[x4,y4]]

        # Renvoie

        Une liste de carrés.
        """
        indices_to_remove = []
        for k in range(0, len(squares)):
            for t in range(0, len(squares)):
                if t != k:
                    if (squares[k][0][0] >= squares[t][0][0] and squares[k][0][1] >= squares[t][0][1]) and (squares[k][1][0] <= squares[t][1][0] and squares[k][1][1] >= squares[t][1][1]) and (squares[k][2][0] <= squares[t][2][0] and squares[k][2][1] <= squares[t][2][1]) and (squares[k][3][0] >= squares[t][3][0] and squares[k][3][1] <= squares[t][3][1]):
                        indices_to_remove.append(k)

        new_squares = []
        for k in range(0, len(squares)):
            if k not in indices_to_remove:
                new_squares.append(squares[k])

        return new_squares
  
    def sortSquaresPoints(self, squares: list[Square]) -> list[Square]:
        """
        Trie les points des carrés dans l'ordre de priorité haut-bas droite-gauche.

        L'ordre droite-gauche est utilisé pour permettre un affichage par OpenCV plus simple en cas de debug

        # Paramètres

        ### squares:
        Liste de carrés.

        # Renvoie
        Une liste de carrés avec chaque carrés ayant des points dans l'orde:

        |1| |2|

        |4| |3|
        """
        i = 0
        for square in squares:

            indices = np.argsort(square, axis=0)
            x_sorted_square = np.array(
                [square[indices[0][0]], square[indices[1][0]], square[indices[2][0]], square[indices[3][0]]])

            square = x_sorted_square

            y_sorted_square = np.array([[0, 0], [0, 0], [0, 0], [0, 0]])

            if square[0][1] > square[1][1]:
                y_sorted_square[0] = square[1]
                y_sorted_square[3] = square[0]
            else:
                y_sorted_square[0] = square[0]
                y_sorted_square[3] = square[1]

            if square[2][1] > square[3][1]:
                y_sorted_square[1] = square[3]
                y_sorted_square[2] = square[2]
            else:
                y_sorted_square[1] = square[2]
                y_sorted_square[2] = square[3]

            square = y_sorted_square

            squares[i] = square
            i += 1

        return squares

    def correctSquaresCoords(self, squares: list[Square]) -> list[Square]:
        """
        Correction des coordonnées des carrés.

        Utilisé pour pouvoir numéroter les carrés d'une face plus simplement

        Cette fonction doit être uniquement utilisé sur des carrés dont les points
        ont été triés par la fonction sortSquaresPoints().

        # Paramètres

        ### squares:
        Liste de carrés.

        # Renvoie

        Liste de carrés dont les coordonnées ont été corrigés par sélection du carré intérieur parfait.

        Si un bord n'est par droit la plus petite coordonnée du bord est prise et appliqué à la deuxième.
        """
        for i in range(0, len(squares)):
            square = squares[i]

            corrected_coord = max(square[0][1], square[1][1])
            square[0][1] = corrected_coord
            square[1][1] = corrected_coord

            corrected_coord = min(square[2][1], square[3][1])
            square[2][1] = corrected_coord
            square[3][1] = corrected_coord

            corrected_coord = max(square[0][0], square[3][0])
            square[0][0] = corrected_coord
            square[3][0] = corrected_coord

            corrected_coord = min(square[1][0], square[2][0])
            square[1][0] = corrected_coord
            square[2][0] = corrected_coord

        return squares

    def sortSquares(self, squares: list[Square]) -> list[Square]:
        """
        Trie les carrés d'une face dans l'ordre haut-bas gauche-droite.

        Cette fonction ne doit être utilisée que sur une face dont les carrés des carrés dont les points ont été triés par
        sortSquaresPoints().

        # Paramètres

        ### squares:
        Liste de carrés.

        # Renvoie
        Une liste de carrés numérotés dans l'ordre:

        |1| |2| |3|

        |4| |5| |6|

        |7| |8| |9|
        """
        tabx = []
        for square in squares:
            tabx.append(square[0][0])

        indices = np.argsort(tabx)
        squares = np.array([squares[indices[0]], squares[indices[1]], squares[indices[2]],
                            squares[indices[3]], squares[indices[4]
                                                         ], squares[indices[5]],
                            squares[indices[6]], squares[indices[7]], squares[indices[8]]])

        taby = []
        for k in range(0, 3):
            taby.append(squares[k][0][1])
        indices1 = np.argsort(taby)

        taby = []
        for k in range(3, 6):
            taby.append(squares[k][0][1])
        indices2 = np.argsort(taby)

        taby = []
        for k in range(6, 9):
            taby.append(squares[k][0][1])
        indices3 = np.argsort(taby)

        squares = np.array([squares[indices1[0]], squares[indices2[0] + 3], squares[indices3[0] + 6],
                            squares[indices1[1]], squares[indices2[1] +
                                                          3], squares[indices3[1] + 6],
                            squares[indices1[2]], squares[indices2[2] + 3], squares[indices3[2] + 6]])

        return squares

    def extractColors(self, squares: list[Square], image: Image) -> list[list[int]]:
        """
        Extrait la couleur moyenne de chaque carré.

        Cette fonction ne doit être utilisée que sur des carrés dont les points ont été triés par
        sortSquarePoints() et donc les coordonnées ont été corrigés par correctSquaresCoords().

        # Paramètres

        ### squares:
        Liste de carrés

        ### image:
        image dont il faut extraire les couleurs.

        L'utilisation de l'image redimensionnné par prepareImage() est recommandée.

        # Renvoie

        Une liste de couleurs de la forme:

        [[255, 0, 234], [123, 43, 234]]
        """
        color_array = []
        for square in squares:
            b = 0
            g = 0
            r = 0
            count = (square[1][0] - square[0][0]) * \
                (square[3][1] - square[0][1])
            for x in range(square[0][0], square[1][0]):
                for y in range(square[0][1], square[3][1]):
                    b += image[y][x][0]
                    g += image[y][x][1]
                    r += image[y][x][2]

            color_array.append([int(r/count), int(g/count), int(b/count)])

        return color_array

    def createColorGroups(self) -> list[list[int]]:
        """
        Créer les groupes de couleurs en fonction de leur proximité dans l'espace RGB.
        Cette fonction utilise les couleurs générées par processImages().
        Le résultat est visualisable par l'appel de showGroups().
        """

        if self.show_rgb:
            rgb_colors = []
            for color_cluster in self.colors:
                for color in color_cluster:
                    rgb_colors.append([color[0], color[1], color[2]])
            self.showColors(rgb_colors)

        # Création du groupe de rang 1: 54 groupes de 1
        groups = []
        for x in range(0, 6):
            for y in range(0, 9):
                color = {
                    "color": [self.colors[x][y][0], self.colors[x][y][1], self.colors[x][y][2]],
                    "indexlist": [[x, y]]
                }

                groups.append(color)

        # Création des groupes de taille 9
        final_groups = []
        while len(groups) > 0:
            current = groups[0]
            min_dist = np.Infinity
            min_index = 0

            for k in range(1, len(groups)):
                other = groups[k]
                distance = (current["color"][0] - other["color"][0])**2 + (current["color"][1] -
                                                                           other["color"][1])**2 + (current["color"][2] - other["color"][2])**2
                if distance < min_dist:
                    min_dist = distance
                    min_index = k

            if min_index == 0:
                print("Erreur de détection du plus proche")
                SystemExit

            closest = groups[min_index]

            fused_group = {
                "color": [(current["color"][0] + closest["color"][0])/2, (current["color"][1] + closest["color"][1])/2, (current["color"][2] + closest["color"][2])/2],
                "indexlist": []
            }

            for index in current["indexlist"]:
                fused_group["indexlist"].append(index)
            for index in closest["indexlist"]:
                fused_group["indexlist"].append(index)

            if len(fused_group["indexlist"]) == 9:
                final_groups.append(fused_group)
                groups.pop(min_index)
                groups.pop(0)
            else:
                groups.pop(min_index)
                groups[0] = fused_group

        rubiks_groups = []
        for k in range(0, 6):
            rubiks_groups.append([0, 0, 0, 0, 0, 0, 0, 0, 0])

        group_number = 0
        for group in final_groups:
            group_number += 1
            for element in group["indexlist"]:
                rubiks_groups[element[0]][element[1]] = group_number

        return rubiks_groups
