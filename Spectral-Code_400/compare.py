dependencies_loaded = False
try:
    from PIL import Image
    import zbar
    '''
    If the above segfaults, which it will most likely do on OSX, install python zbar like this:
    > pip install git+https://github.com/npinchot/zbar.git --upgrade
    Credeits to http://stackoverflow.com/questions/21612908/zbar-python-crashes-on-import-osx-10-9-1
    '''
    dependencies_loaded = True
except:
    dependencies_loaded = False

image = Image.open("946px-Emission_spectrum-Fe.svg-cropped.png")
image_pixels = image.load()
spectro = Image.open("spectralcode.png")
spectro_pixels = spectro.load()
difference = Image.new('RGBA', image.size, (255, 255, 255, 255))
difference_pixels = difference.load()

height, width = image.size
for r in range(height):
    for c in range(width):
        k = image_pixels[r,c]
        p = spectro_pixels[r,c]
        difference_pixels[r,c] = (abs(p[0]-k[0]), abs(p[1]-k[1]), abs(p[2]-k[2]), abs(p[3]-k[3]))
        print k, p, difference_pixels[r,c]

difference.save("difference.png")

if dependencies_loaded:
    scanner = zbar.ImageScanner()
    scanner.parse_config('enable')
    image = Image.open("difference.png").convert('L')
    width, height = image.size
    raw = image.tostring()
    barcode = zbar.Image(width, height, 'Y800', raw)
    scanner.scan(barcode)
    for symbol in barcode:
        print 'Decoded', symbol.type, 'symbol:', '"%s"' % symbol.data
    del(image)
