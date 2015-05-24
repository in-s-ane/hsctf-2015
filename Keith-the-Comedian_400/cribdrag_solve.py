# Modified version of the general purpose cribdrag.py
import sys

def letter_to_num(c):
    return ord(c)

def num_to_letter(n):
    return chr(n%128)

def generate_keys(system1_solution, system2_solution, system3_solution):
    keys = []
    for (a, b, c) in system1_solution:
        for (d, e, f) in system2_solution:
            for (g, h, i) in system3_solution:
                keys.append([[a, b, c], [d, e, f], [g, h, i]])
    return keys

def text_mat_to_str(m):
    text = ""
    for c in range(len(m[0])):
        for r in range(len(m)):
            text += num_to_letter(int(m[r][c]))
    return text

modulo = 128

print "Modulo: " + str(modulo)

KPT = "Knock, knock!"
ciphertext = "1 232 92 231 59 226 115 31 51 68 53 51 147 218 52 234 17 234 54 144 241 13 49 149 155 247 168 88 22 177 20 238 205 3 86 155 182 240 18 211 121 35 215 3 123 110 108 157 109 229 21 9 166 254 119 238 173 44 142 121 3 86 155 95 42 227 107 102 67 35 193 187 118 112 207 87 254 87 113 131 54 71 243 7 180 100 84 108 100 25 199 5 103 15 170 114 159 203 136 132 155 43 83 66 237 217 2 159 126 60 27 101 181 69 142 45 199 242 180 99 111 158 219 6 174 230 91 127 4 61 151 158 180 100 84 135 222 232 223 81 56 85 63 57 246 92 119 237 196 252 158 143 105 150 131 173 183 226 102 141 240 199 63 82 233"
ciphertext = ciphertext.split(' ')
ciphertext = [int(x) % modulo for x in ciphertext]
ciphertext_blocks = []
for x in xrange(0, len(ciphertext) - 2, 3):
    ciphertext_blocks.append([ciphertext[x], ciphertext[x+1], ciphertext[x+2]])
print ciphertext
print ciphertext_blocks

ciphertext_blocks_mat = Matrix(IntegerModRing(modulo), ciphertext_blocks).transpose()

for offset in range(len(ciphertext)-len(KPT)+1):
    mapping = []
    if offset % 3 == 0:
        mapping.append((letter_to_num(KPT[0]), ciphertext[offset]))
        mapping.append((letter_to_num(KPT[1]), ciphertext[offset+1]))
        mapping.append((letter_to_num(KPT[2]), ciphertext[offset+2]))
        mapping.append((letter_to_num(KPT[3]), ciphertext[offset+3]))
        mapping.append((letter_to_num(KPT[4]), ciphertext[offset+4]))
        mapping.append((letter_to_num(KPT[5]), ciphertext[offset+5]))
        mapping.append((letter_to_num(KPT[6]), ciphertext[offset+6]))
        mapping.append((letter_to_num(KPT[7]), ciphertext[offset+7]))
        mapping.append((letter_to_num(KPT[8]), ciphertext[offset+8]))
    elif offset % 3 == 2:
        mapping.append((letter_to_num(KPT[1]), ciphertext[offset+1]))
        mapping.append((letter_to_num(KPT[2]), ciphertext[offset+2]))
        mapping.append((letter_to_num(KPT[3]), ciphertext[offset+3]))
        mapping.append((letter_to_num(KPT[4]), ciphertext[offset+4]))
        mapping.append((letter_to_num(KPT[5]), ciphertext[offset+5]))
        mapping.append((letter_to_num(KPT[6]), ciphertext[offset+6]))
        mapping.append((letter_to_num(KPT[7]), ciphertext[offset+7]))
        mapping.append((letter_to_num(KPT[8]), ciphertext[offset+8]))
        mapping.append((letter_to_num(KPT[9]), ciphertext[offset+9]))
    else:
        mapping.append((letter_to_num(KPT[2]), ciphertext[offset+2]))
        mapping.append((letter_to_num(KPT[3]), ciphertext[offset+3]))
        mapping.append((letter_to_num(KPT[4]), ciphertext[offset+4]))
        mapping.append((letter_to_num(KPT[5]), ciphertext[offset+5]))
        mapping.append((letter_to_num(KPT[6]), ciphertext[offset+6]))
        mapping.append((letter_to_num(KPT[7]), ciphertext[offset+7]))
        mapping.append((letter_to_num(KPT[8]), ciphertext[offset+8]))
        mapping.append((letter_to_num(KPT[9]), ciphertext[offset+9]))
        mapping.append((letter_to_num(KPT[10]), ciphertext[offset+10]))

    var('a', 'b', 'c')
    system1_solution = solve_mod([
            mapping[0][0]*a + mapping[1][0]*b + mapping[2][0]*c == mapping[0][1],
            mapping[3][0]*a + mapping[4][0]*b + mapping[5][0]*c == mapping[3][1],
            mapping[6][0]*a + mapping[7][0]*b + mapping[8][0]*c == mapping[6][1],
            ], modulo)
    if len(system1_solution) == 0:
        continue
    var('d', 'e', 'f')
    system2_solution = solve_mod([
            mapping[0][0]*d + mapping[1][0]*e + mapping[2][0]*f == mapping[1][1],
            mapping[3][0]*d + mapping[4][0]*e + mapping[5][0]*f == mapping[4][1],
            mapping[6][0]*d + mapping[7][0]*e + mapping[8][0]*f == mapping[7][1],
            ], modulo)
    if len(system2_solution) == 0:
        continue
    var('g', 'h', 'i')
    system3_solution = solve_mod([
            mapping[0][0]*g + mapping[1][0]*h + mapping[2][0]*i == mapping[2][1],
            mapping[3][0]*g + mapping[4][0]*h + mapping[5][0]*i == mapping[5][1],
            mapping[6][0]*g + mapping[7][0]*h + mapping[8][0]*i == mapping[8][1],
            ], modulo)
    if len(system3_solution) == 0:
        continue
    print "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
    #print "Possible solutions: "
    #print "(a, b, c): " + str(system1_solution)
    #print "(d, e, f): " + str(system2_solution)
    #print "(g, h, i): " + str(system3_solution)
    print "Offset: " + str(offset)
    for key in generate_keys(system1_solution, system2_solution, system3_solution):
        try:
            key_mat = Matrix(IntegerModRing(modulo), key)
            inv_key = key_mat.inverse()
            #print "Key: "
            #print key_mat.numpy()
            #print "Key inverse: "
            #print inv_key.numpy()
            print text_mat_to_str((inv_key * ciphertext_blocks_mat).numpy())
        except ZeroDivisionError:
            #print "Key not invertible!"
            pass


# Knock, knock! Who's there? An Ant Hill. I don't have an Aunt Hill! Hahahah
# (Humor of the 2050s was weird). Well, anyway, the flag is: that_aunt_hill_joke_was_horrible
