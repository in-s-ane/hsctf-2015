#/usr/bin/python
# -*- coding: utf+8 -*-
from collections import Counter

f = open("markovhaystack.txt")
text = f.read()
f.close()

word_freqs = Counter(text.split())

print word_freqs
