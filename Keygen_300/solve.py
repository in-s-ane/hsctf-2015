offsets = [0]*36
differences = [-3, 0, 4, -8, 20, -13, 3, -2, 6, 6, -19, 13, -17, 17, -13, -1, 10, -5, 7, 8, -10, -10, 20, -26, 10, -6, 3, 19,-4, 2, -11, 0, 3, -5, 7] # Let's extract the differences

for i, diff in enumerate(differences):
    offsets[i+1] = offsets[i] - diff # By calculating the offsets relative to the first number, we can then get the global offsets

total = sum(offsets)
add_to_each = (3842 - total) / 36 # We can then find how much to add to each number to achieve the desired sum of numbers

flag = ""
for o in offsets:
    flag += chr(o + add_to_each)

print flag
