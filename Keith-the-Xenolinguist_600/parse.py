from collections import Counter

alphabet = {
    'a': 0,
    'b': 1,
    'c': 0,
    'd': 0,
    'e': 0,
    'g': 6,
    'h': 7,
    'i': 0,
    'j': 9,
    'k': 10,
    'l': 0,
    'm': 0,
    'n': 0,
    'o': 0,
    'p': 0,
    'r': 17,
    's': 0,
    't': 0
}
base = len(alphabet)

lines = []
with open("xenolinguist.txt", "r") as f:
    data = f.read()
    #alphabet = Counter(data.replace("okshnoddle", "").replace("fwooosh", "").replace("flordoralscorp", "").replace(" ", "").replace("\n", ""))
    #print sorted(alphabet.keys())
    lines = data.split("\n")

def get_position(ch):
    return alphabet[ch]

def get_value(word):
    index = 0
    value = 0
    for ch in word[::-1]:
        value += (get_position(ch)) * (base**index)
        index += 1
    return value

class Stack:
    def __init__(self):
        self.__storage = []
    def isEmpty(self):
        return len(self.__storage) == 0
    def push(self,p):
        self.__storage.append(p)
    def pop(self):
        return self.__storage.pop()

def main():
    for line in lines:
        print line
        line = line.split()
        stack = Stack()
        result = ""
        for term in line:
            if result != "":
                print result == get_value(term)
            if term == "orkshnoddle":
                result = stack.pop()
            elif term == "fwooosh":
                s1 = stack.pop()
                s2 = stack.pop()
                stack.push(abs(s1 - s2))
            elif term == "flordoralscorp":
                pass
            else:
                stack.push(get_value(term))

if __name__ == "__main__":
    main()
