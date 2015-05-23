f = open("input.txt")
data = f.read().split('\n')[1:]
f.close()

def appears_in(trusty_set, complete_sets):
    for complete_set in complete_sets:
        already_there = True
        for person in complete_set:
            if person not in trusty_set:
                already_there = False
                break
        if already_there:
            return True
    return False

complete_sets = []
for trusty_set in data[::-1]:
    trusty_set = trusty_set.strip()
    trusty_set = trusty_set.split(" ")[1:]
    trusty_set = [int(person) for person in trusty_set]
    print trusty_set
    if not appears_in(trusty_set, complete_sets):
        complete_sets.append(trusty_set)

#print complete_sets
print len(complete_sets)
