with open("generated_test.in", "w") as test_in:
    sccs = int(input("SCCS? "))

    test_in.write(str(sccs*3) + "\n")
    test_in.write(str(sccs*3) + "\n")

    j = 1
    result = 0
    for i in range(sccs):
        test_in.write(str(j) + " " + str(j+1) + " " + str((j*i + 3)*(j + 1)) + "\n")
        test_in.write(str(j) + " " + str(j+2) + " " + str((j*i + 3)*(j + 2)) + "\n")
        test_in.write(str(j+1) + " " + str(j+2) + " " + str((j*i + 3)*j) + "\n")
        result += (j*i + 3)*(j + 1) + (j*i + 3)*(j + 2)
        j += 3;

    with open("generated_test.out", "w") as test_out:
        test_out.write(str(result) + "\n");
