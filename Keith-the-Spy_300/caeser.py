text = "WOOD the FREUD and the PECAN at DODO PUNT's, then CAP the HEWN on a SAX ECO to FOE your BIAS. - PIERRE"

for shift in range(26):
    caesar = ""
    for ch in text:
        if ord('Z') >= ord(ch) >= ord('A'):
            caesar += chr(((ord(ch) - ord('A') + shift) % 26) + ord('A'))
        else:
            caesar += ch

    print caesar

'''
First we do a caesar shift on the entire sentence using shifts from 0-25.
Then we can manually select the words that are actually english.

MEET the COBRA and the TIGER at PAPA JOHN's, then GET the LIAR on a WEB CAM to END your TASK. - LEANNA
'''
