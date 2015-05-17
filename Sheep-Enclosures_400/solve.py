from scipy.spatial import ConvexHull # http://en.wikipedia.org/wiki/Graham_scan

# Calculate algebraic area of a polygon using shoelace formula
def area(vertices):
    n = len(vertices)
    area = 0.0
    for i in range(n):
        j = (i + 1) % n
        area += vertices[i][0] * vertices[j][1]
        area -= vertices[j][0] * vertices[i][1]
    area /= 2.0
    return area

points = []
with open("input.txt", "r") as f:
    data = f.read().split('\n')
    for point in data:
        point = point.split(" ")
        points.append((float(point[0]), float(point[1])))

hull = ConvexHull(points)
vertices = []
for i in hull.vertices:
    vertices.append(points[i])

print "Fence area:", area(points)
print "Rubber band area:", area(vertices)
print "Difference:", area(vertices) - area(points)
