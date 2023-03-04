with open("generated_test.in", "w") as test:
    vertices = int(input("Vertices? "))

    test.write(str(vertices) + "\n")
    test.write(str(vertices*(vertices-1)) + "\n")

    for i in range(vertices, 0, -1):
        for j in range(vertices, 0, -1):
            if (j != i):
                test.write(str(i) + " " + str(j) + " " + str(((i*j)//3)+1) + "\n")
    
