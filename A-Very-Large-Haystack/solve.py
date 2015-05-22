with open("text.txt") as f:
    data = f.read()

    every_other = data[::2] + data[1::2]

    with open("modified.txt", "w") as m:
        m.write(every_other)
