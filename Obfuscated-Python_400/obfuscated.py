# py_compile.compile('obfuscated.py');file=open('obfuscated.pyc');file.read(8);dis.dis(marshal.load(file))

x = ''
n = 6321187

for i in open(__file__).read().split('lol')[3]:
    n **= 2
    n %= 256
    
    v = n ^ ord(i)
    v %= 256

    x += chr(v)

exec(x)
print "Done"
