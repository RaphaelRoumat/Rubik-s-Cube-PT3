# -*- coding:utf-8 -*-
import cv2
import numpy as np
import sys
import math


#Preparation pour pool
kernel_15 = np.ones((15,15),np.uint8)
kernel_50 = np.ones((50,50),np.uint8)

#Analyser les images
def colorMatch(side):
    cube_rgb = cv2.imread( side + '.jpg')
    cube_gray = cv2.cvtColor(cube_rgb, cv2.COLOR_BGR2GRAY)# en gray
    cube_hsv = cv2.cvtColor(cube_rgb,cv2.COLOR_BGR2HSV)# en hsv
    cube_gray = cv2.adaptiveThreshold(cube_gray,255,cv2.ADAPTIVE_THRESH_GAUSSIAN_C,cv2.THRESH_BINARY,11,2)#自适应滤波
   
    # Blanc
    lower_white = np.array([0, 0, 201])
    upper_white = np.array([180, 50, 255])
    white_mask = cv2.inRange(cube_hsv, lower_white, upper_white)
    white_erosion = cv2.erode(white_mask, kernel_15, iterations = 1)
    white_res = cv2.bitwise_and(cube_rgb, cube_rgb, mask = white_erosion)

    #Red
    '''lower_red = np.array([0,50,50])
    upper_red = np.array([10,255,255])
    red_mask0 = cv2.inRange(cube_hsv, lower_red, upper_red)
    lower_red = np.array([172, 135, 150])
    upper_red = np.array([179, 240, 255])
    red_mask1 = cv2.inRange(cube_hsv, lower_red, upper_red)
    red_mask = red_mask0 + red_mask1
    '''
    lower_red = np.array([170, 110, 145])
    upper_red = np.array([182, 240, 255])
    red_mask = cv2.inRange(cube_hsv, lower_red, upper_red)
    red_erosion = cv2.erode(red_mask, kernel_15, iterations = 1)
    red_res = cv2.bitwise_and(cube_rgb, cube_rgb, mask = red_erosion)

    #Orange
    lower_orange = np.array([3, 115, 195])
    upper_orange = np.array([9, 190, 255])
    orange_mask = cv2.inRange(cube_hsv, lower_orange, upper_orange)
    orange_erosion = cv2.erode(orange_mask, kernel_15, iterations = 1)
    orange_res = cv2.bitwise_and(cube_rgb, cube_rgb, mask = orange_erosion)

    #Jaune
    lower_yellow = np.array([20, 125, 142])
    upper_yellow = np.array([34, 243, 255])
    yellow_mask = cv2.inRange(cube_hsv, lower_yellow, upper_yellow)
    yellow_erosion = cv2.erode(yellow_mask, kernel_15, iterations = 1)
    yellow_res = cv2.bitwise_and(cube_rgb, cube_rgb, mask = yellow_erosion)

    #Vert
    lower_green = np.array([68,140,120])
    upper_green = np.array([82,255,245])
    green_mask = cv2.inRange(cube_hsv, lower_green, upper_green)
    green_erosion = cv2.erode(green_mask, kernel_15, iterations = 1)
    green_res = cv2.bitwise_and(cube_rgb, cube_rgb, mask = green_erosion)

    #Blue
    lower_blue = np.array([95, 123, 109])
    upper_blue = np.array([124, 253, 240])
    blue_mask = cv2.inRange(cube_hsv, lower_blue, upper_blue)
    blue_erosion = cv2.erode(blue_mask, kernel_15, iterations = 1)
    blue_res = cv2.bitwise_and(cube_rgb, cube_rgb, mask = blue_erosion)

    #Les masque
    mask = red_erosion + green_erosion + yellow_erosion + blue_erosion + orange_erosion + white_erosion
    mask = cv2.morphologyEx(mask, cv2.MORPH_OPEN, kernel_50)
    mask = cv2.erode(mask, kernel_50, iterations = 1)
    res = cv2.bitwise_and(cube_hsv, cube_hsv, mask = mask)  
       
    edges = cv2.Canny(mask,50,80,apertureSize=5)
    points = cv2.findNonZero(edges)

    #Trouver des point max et min 
    min = np.amin(points, axis=0)#min，axis 0->colonne,1->ligne
    max = np.amax(points, axis=0)#max，axis 0->colonne,1->ligne

    #Trouver des pixel max et min 
    w, h = cube_gray.shape[::-1]
    x_max = max[0][0]
    y_max = max[0][1]
    x_min = min[0][0]
    y_min = min[0][1] 
    width = int(x_max - x_min)
    height = int(y_max - y_min)

    
    #le color de la case centre et sa coordonnee
    def midpoint(x1,y1,x2,y2):
        x_mid = int((x1 + x2)/2)
        y_mid = h - int(((y1 + y2)/2))
        color = res[y_mid, x_mid]
        return ([int(color[0]), int(color[1]), int(color[2])])

    # les 9 colors centre
    mid_1 = midpoint(x_min, y_max, (x_min + int(width/3)), (y_max - int(height/3)))
    mid_2 = midpoint((x_min + int(width/3)), y_max, (x_min + int(width*2/3)), (y_max - int(height/3)))
    mid_3 = midpoint((x_min + int(width*2/3)), y_max, x_max, (y_max - int(height/3)))
    mid_4 = midpoint(x_min, (y_max - int(height/3)), (x_min + int(width/3)), (y_max - int(height*2/3)))
    mid_5 = midpoint((x_min + int(width/3)), (y_max - int(height/3)), (x_min + int(width*2/3)), (y_max - int(height*2/3)))
    mid_6 = midpoint((x_min + int(width*2/3)), (y_max - int(height/3)), x_max, (y_max - int(height*2/3)))
    mid_7 = midpoint(x_min, (y_max - int(height*2/3)), (x_min + int(width/3)), y_min)
    mid_8 = midpoint(x_min + int(width/3), (y_max - int(height*2/3)), (x_min + int(width*2/3)), y_min)
    mid_9 = midpoint(x_min + int(width*2/3), (y_max - int(height*2/3)), x_max, y_min)
    mids = [mid_1, mid_2, mid_3, mid_4, mid_5, mid_6, mid_7, mid_8, mid_9]


    s=''
    for rgb in mids:#hsv
        if ((0<=rgb[0]<=180 ) and (0<=rgb[1]<=50 ) and( 201<=rgb[2]<=255)):#Blanc White
            s+='W '
        elif ((3<=rgb[0]<=9 )and (115<=rgb[1]<=190 )and (195<=rgb[2] <=255)):#Orange Orange
 	        s+='O '
        elif ((95<=rgb[0] <=124) and (123<=rgb[1]<=253) and (109<=rgb[2]<=240)):#Blue Blue
            s+='B '
       	elif( (68<=rgb[0]<=82) and (140<=rgb[1]<=255) and (120<=rgb[2]<=245)):#Vert Green
            s+='G '
        elif ((20<=rgb[0]<=34) and (125<=rgb[1]<=243) and(142 <=rgb[2]<=255)):#Jaune Yellow
            s+='Y '
        elif ((170<=rgb[0]<=182)and (110<=rgb[1] <=240 )and (145<=rgb[2]<=255 )):#Rouge Red
            s+='R '
    return(s)


if __name__ == '__main__':

    up=colorMatch('vert')
    get_str=up
    print('les colors de up:'+get_str)
     
    	
    
cv2.waitKey(0)
	
	





