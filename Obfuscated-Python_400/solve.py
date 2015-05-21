x = ''
n = 6321187

for i in open('obfuscated_python.pyc').read().split('lol')[3]:
    n **= 2
    n %= 256
    
    v = n ^ ord(i)
    v %= 256

    x += chr(v)

print x
