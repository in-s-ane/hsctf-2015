from PIL import Image

WHITE = (255, 255, 255)
BLACK = (0, 0, 0)

logo = Image.open("hsctf-logo.png")
logo_pixels = logo.load()
keith = Image.open("logo.jpg")
keith_pixels = keith.load()
difference = Image.new('RGB', logo.size, (255, 255, 255))
difference_pixels = difference.load()

height, width = logo.size
for r in range(height):
    for c in range(width):
        p = logo_pixels[r,c]
        k = keith_pixels[r,c]
        diff = ((p[0]-k[0])**2 + (p[1]-k[1])**2 + (p[2]-k[2])**2) ** .5
        difference_pixels[r,c] = BLACK if diff > 1.8 else WHITE
        '''
        difference_pixels[r,c] = (0 if abs(p[0]-k[0]) > 2 else 255,
                                  0 if abs(p[1]-k[1]) > 2 else 255,
                                  0 if abs(p[2]-k[2]) > 2 else 255)
        '''

difference.save("difference.png")
