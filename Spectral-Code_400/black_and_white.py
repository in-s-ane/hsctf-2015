#!/usr/bin/python

import sys
from PIL import Image

WHITE = (255, 255, 255)
BLACK = (0, 0, 0)

def read(img):
    image = Image.open(img)
    image_pixels = image.load()
    bw = Image.new('RGB', image.size, (255, 255, 255))
    bw_pixels = bw.load()

    height, width = image.size
    for r in range(height):
        for c in range(width):
            p = image_pixels[r,c]
            k = BLACK
            diff = ((p[0]-k[0])**2 + (p[1]-k[1])**2 + (p[2]-k[2])**2) ** .5
            bw_pixels[r,c] = BLACK if diff < .5 else WHITE

    bw.save(img + "-BW.png")

if len(sys.argv) > 1:
    read(sys.argv[1])
else:
    print "Please specify a picture to read!\n> python black_and_white.py [FILE]"
