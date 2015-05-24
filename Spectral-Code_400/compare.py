from PIL import Image

WHITE = (255, 255, 255)
BLACK = (0, 0, 0)

image = Image.open("946px-Emission_spectrum-Fe.svg.png-BW.png")
image_pixels = image.load()
spectro = Image.open("spectralcode-cropped.png")
spectro_pixels = spectro.load()
difference = Image.new('RGB', image.size, (255, 255, 255))
difference_pixels = difference.load()

height, width = image.size
for r in range(height):
    for c in range(width):
        p = image_pixels[r,c]
        k = spectro_pixels[r,c]
        diff = ((p[0]-k[0])**2 + (p[0]-k[0])**2 + (p[0]-k[0])**2) ** .5
        difference_pixels[r,c] = BLACK if diff > 441 else WHITE
        '''
        difference_pixels[r,c] = (0 if abs(p[0]-k[0]) > 2 else 255,
                                  0 if abs(p[1]-k[1]) > 2 else 255,
                                  0 if abs(p[2]-k[2]) > 2 else 255)
        '''

difference.save("difference.png")
