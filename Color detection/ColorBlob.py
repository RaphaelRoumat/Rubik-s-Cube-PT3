from __future__ import annotations
from dataclasses import dataclass
from typing import List


@dataclass
class ColorBlob:
    """
    Groupement d'une couleur et d'une liste d'index
    
    # Attributs
    
    ### `color`
    
    Couleur du blob
    
    ### `indexlist`
    
    List des index composant le blob
    
    """
    color: List[int]
    indexlist: List[List[int]]
    
    def fuse(self, other: ColorBlob) -> ColorBlob:
        """
        Renvoit la fusion des deux blobs
        La nouvelle couleur est la moyenne de la couleur des deux blobs
        L'indexlist est la somme des deux indexlist
        """
        average_color = []
        average_color.append((self.color[0] + other.color[0]) / 2)
        average_color.append((self.color[1] + other.color[1]) / 2)
        average_color.append((self.color[2] + other.color[2]) / 2)
        
        fused_indexlist = []
        for index in self.indexlist:
            fused_indexlist.append(index)
        for index in other.indexlist:
            fused_indexlist.append(index)

        return ColorBlob(average_color, fused_indexlist)
