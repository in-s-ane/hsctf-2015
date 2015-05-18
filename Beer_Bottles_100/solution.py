import math

LIMIT = 358000000 * 7000000000

def squareCheck(n):
    return math.sqrt(n) - int(math.sqrt(n)) == 0

lowerLimit = 2238749650

while not squareCheck((lowerLimit) * (lowerLimit + 1) / 2):
    lowerLimit = lowerLimit + 1

print (lowerLimit) * (lowerLimit + 1) / 2
