with open("code.befunge") as f:
    code = f.read()
    count = 0
    for ch in code:
        if ch != ' ' and ch != '\n':
            count += 1
    print count
