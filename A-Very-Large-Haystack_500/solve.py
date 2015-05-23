f = open('text.txt', 'r')
data = f.read()
f.close()

'thesecretwordscanbeobtainedbyreadingeveryothercharacter'

step_1 = data[::2]
with open("modified1.txt", "w") as m:
    m.write(step_1)

'addtheseletterstotheotherhalfofthemessage' # Vignere Cipher

step_2 = [chr((ord(data[i])-ord('a') + ord(data[i+1])-ord('a'))%26 + ord('a')) for i in range(0, len(data), 2)]
step_2 = ''.join(step_2)
with open("modified2.txt", "w") as m:
    m.write(step_2)

'tofindthekeynowdothepreviousstepsanothertime'

step_3 = [chr((ord(step_2[i])-ord('a') + ord(step_2[i+1])-ord('a'))%26 + ord('a')) for i in range(0, len(step_2), 2)]
step_3 = ''.join(step_3)
with open("modified3.txt", "w") as m:
    m.write(step_3)

'nowdoallthatonemoretimetogettheanswer'

step_4 = [chr((ord(step_3[i])-ord('a') + ord(step_3[i+1])-ord('a'))%26 + ord('a')) for i in range(0, len(step_3)-1, 2)]
step_4 = ''.join(step_4)
with open("modified4.txt", "w") as m:
    m.write(step_4)

# This was added after I solved the problem... but I should've realized that I could search for terms from previous haystacks... like 'character'
with open("modified4.txt") as f:
    while True:
        text = f.read(68)
        if "character" in text:
            print text

# Flag: thesecharactersarethegatewaytokinglyglory
