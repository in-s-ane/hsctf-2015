GLOBAL_ACCUMULATOR = 0

def shell():
    # Initialization
    accumulator = 0

    # Main program loop
    while True:
        # Get user input
        cmd = raw_input('>> ')
        if accumulator == 256 or accumulator == -1:
            # Overflow, reset accumulator
            accumulator = 0
        # Process input
        if cmd == 'i':
            accumulator += 1 # Increment
        elif cmd == 'd':
            accumulator += -1 # Decrement
        elif cmd == 'o':
            print accumulator # Output
        elif cmd == 's':
            accumulator *= accumulator # Square
        else:
            print 'Unrecognized command.'

def process(c , accumulator):
    if accumulator == 256 or accumulator == -1:
        # Overflow, reset accumulator
        accumulator = 0
    # Process input
    if len(c) > 1:
        x = accumulator
        for char in c:
            x = process(char , x)
        return x
    elif c == 'i':
        accumulator += 1 # Increment
    elif c == 'd':
        accumulator += -1 # Decrement
    elif c == 'o':
        print accumulator # Output
    elif c == 's':
        accumulator *= accumulator # Square
    else:
       print 'Unrecognized command D:'
    return accumulator

def numfish():
    return process("iiss" , 0)

currFish = numfish()
print currFish
process("siisio" , currFish)
