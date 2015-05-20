import collections

counter = collections.Counter()
with open("text.txt") as f:
    data = f.read()
    for c in data:
        counter[c] += 1

with open("frequency.txt", "w") as f:
    for c in counter:
        f.write(c + ": " + str(counter[c]) + "\n")
