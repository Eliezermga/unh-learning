import cv2 as cv
import numpy as np
import matplotlib.pyplot as plt
 
img = cv.imread('img_j.png', cv.IMREAD_GRAYSCALE)
background = cv.imread('noisy_background.png', cv.IMREAD_GRAYSCALE)
foreground = cv.imread('noisy_foreground.png', cv.IMREAD_GRAYSCALE)

kernel = np.ones((5,5),np.uint8)
erosion = cv.erode(img,kernel,iterations = 1)
dilatation = cv.dilate(img,kernel,iterations = 1)
ouverture = cv.morphologyEx(background, cv.MORPH_OPEN, kernel)
fermeture = cv.morphologyEx(foreground, cv.MORPH_CLOSE, kernel)

plt.figure(figsize=(10, 5))

plt.subplot(4, 2, 1)
plt.imshow(img, cmap='gray')
plt.title('Original')
plt.axis('off')

# Eroded image
plt.subplot(4, 2, 2)
plt.imshow(erosion, cmap='gray')
plt.title('Erosion')
plt.axis('off')


plt.subplot(4, 2, 3)
plt.imshow(img, cmap='gray')
plt.title('Original')
plt.axis('off')
# Dilated image
plt.subplot(4, 2, 4)
plt.imshow(dilatation, cmap='gray')
plt.title('Dilatation')
plt.axis('off')

plt.subplot(4, 2, 5)
plt.imshow(background, cmap='gray')
plt.title('Original')
plt.axis('off')
# Dilated image
plt.subplot(4, 2, 6)
plt.imshow(ouverture, cmap='gray')
plt.title('Ouverture')
plt.axis('off')

plt.subplot(4, 2, 7)
plt.imshow(foreground, cmap='gray')
plt.title('Original')
plt.axis('off')
# Dilated image
plt.subplot(4, 2, 8)
plt.imshow(fermeture, cmap='gray')
plt.title('Fermeture')
plt.axis('off')


# Show the plot
plt.tight_layout()
plt.show()