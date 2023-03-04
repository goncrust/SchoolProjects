def generate(vertices):
    with open(str(vertices) + ".in", "w") as test:
        test.write(str(vertices) + "\n")
        test.write(str(vertices*(vertices-1)) + "\n")

        for i in range(vertices, 0, -1):
            for j in range(vertices, 0, -1):
                if (j != i):
                    test.write(str(i) + " " + str(j) + " " +
                               str(((i*j)//3)+1) + "\n")


for i in range(0, 50001, 1000):
    print(i)
    generate(i)
