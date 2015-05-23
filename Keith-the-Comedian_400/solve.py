import sys
a = "1 232 92 231 59 226 115 31 51 68 53 51 147 218 52 234 17 234 54 144 241 13 49 149 155 247 168 88 22 177 20 238 205 3 86 155 182 240 18 211 121 35 215 3 123 110 108 157 109 229 21 9 166 254 119 238 173 44 142 121 3 86 155 95 42 227 107 102 67 35 193 187 118 112 207 87 254 87 113 131 54 71 243 7 180 100 84 108 100 25 199 5 103 15 170 114 159 203 136 132 155 43 83 66 237 217 2 159 126 60 27 101 181 69 142 45 199 242 180 99 111 158 219 6 174 230 91 127 4 61 151 158 180 100 84 135 222 232 223 81 56 85 63 57 246 92 119 237 196 252 158 143 105 150 131 173 183 226 102 141 240 199 63 82 233"
a = a.split(' ')
a = [int(x) % 26 for x in a]
#c = ''.join([chr(x+65) for x in a])
#print c
b = []
for x in xrange(0, len(a) - 2, 3):
    b.append([a[x], a[x+1], a[x+2]])
#for y in xrange(55):
#    b.append([a[y], a[1*55+y], a[2*55+y]])
print b
sys.exit(0)

full_ciphertext = Matrix(b).transpose()
print "Ciphertext: "
print full_ciphertext.str()

# [10, 13, 14] -> [1, 24, 14]
# [2, 10, 10] -> [23, 7, 18]
# [13, 14, 2] -> [11, 5, 25]
#http://www.nku.edu/~christensen/092mat483%20known%20plaintext%20attack%20of%20Hill%20cipher.pdf
#http://facultyfp.salisbury.edu/despickler/personal/Resources/LinearAlgebra/HillCipherHandoutLA.pdf

def text_mat_to_str(m):
    text = ""
    for c in range(len(m[0])):
        for r in range(len(m)):
            text += chr(65 + int(m[r][c]))
    return text


iter_count = 0

#f = open("output.txt", "w")

#ciphertext = Matrix(IntegerModRing(26), [[1, 23, 11], [24, 7, 5], [14, 18, 25]])
#ciphertext = Matrix(IntegerModRing(26), [[1, 24, 14], [4, 17, 18], [1, 23, 25]])
#for a in range(26):
#    for b in range(26):
#        for c in range(26):
#            for d in range(26):
#                iter_count += 1
#                if iter_count % 10000 == 0:
#                    print(str(iter_count))
#                try:
#                    plaintext = Matrix(IntegerModRing(26), [[10, 2, a], [13, 10, b], [14, c, d]])
#                    inv_plaintext = plaintext.inverse()
#                except ZeroDivisionError:
#                    #print "Got non-invertible plaintext matrix"
#                    continue
#                key = ciphertext * inv_plaintext
#                new_ciphertext = key * plaintext
#                if new_ciphertext == ciphertext:
#                    try:
#                        inv_key = key.inverse()
#                        #print "Possible key:"
#                        #print key
#                        #print "Key inverse:"
#                        #print key.inverse()
#                    except ZeroDivisionError:
#                        #print "Got non-invertible matrix"
#                        continue
#                    new_plaintext = (key.inverse() * full_ciphertext).numpy()
#                    if new_plaintext[0][0] == 10 and \
#                       new_plaintext[0][1] == 2 and \
#                       new_plaintext[1][0] == 13 and \
#                       new_plaintext[1][1] == 10 and \
#                       new_plaintext[2][0] == 14:
#                       decoded = text_mat_to_str(new_plaintext)
#                       if "FLAG" in decoded or "KNOCKKNOCK" in decoded or\
#                        "CIPHER" in decoded or "HILL" in decoded:
#                            print decoded
#                       #print "Possible answer at: " + str(iter_count)
#                       #f.write(decoded + '\n')
#
