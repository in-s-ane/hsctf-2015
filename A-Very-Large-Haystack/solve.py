with open("text.txt") as f:
    data = f.read()

    with open("modified.txt", "w") as m:
        for i in xrange(0, len(data), 2):
            m.write(data[i])

    with open("modified2.txt", "w") as n:
        for i in xrange(1, len(data), 2):
            n.write(data[i])
