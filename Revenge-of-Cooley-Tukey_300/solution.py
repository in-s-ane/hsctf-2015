# NOTE: This does not work because python's scipy library does not have normalization when using DCT/DST

from numpy import array
from scipy.fftpack import *

numbers = array([1653.0, 0.13916760914832338, 58.72838425104149, -29.800988375644806, -64.39696961966996, 77.24677622083472, 29.83855931789015, -62.70175235211251, 42.999999999999986, 65.1382409674247, -21.49541356738255, -67.39554551772046, 54.396969619669974, 40.92032042100787, -39.07153000154913, -15.546218972937488, 65.0])

result = idct(numbers)

print result

flag = ""
for i in result:
    flag += chr(int(i+.5) % 256)

print flag

