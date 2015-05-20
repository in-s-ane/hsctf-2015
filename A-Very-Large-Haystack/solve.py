with open("text.txt") as f:
    data = f.read()

    with open("modified2-1.txt", "w") as m:
        for i in xrange(0, len(data), 2):
            m.write(data[i])

    with open("modified2-2.txt", "w") as m:
        for i in xrange(1, len(data), 2):
            m.write(data[i])

    with open("modified3-1.txt", "w") as m:
        for i in xrange(1, len(data), 3):
            m.write(data[i])

    with open("modified4-1.txt", "w") as m:
        for i in xrange(1, len(data), 4):
            m.write(data[i])

