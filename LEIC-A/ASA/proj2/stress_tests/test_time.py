import os

os.system("echo > results.txt")
os.system("echo > count.txt")
files = os.listdir(".")
selected = []
for i in range(len(files)):
    if ".in" in files[i]:
        files[i] = files[i][:-3]
        selected.append(files[i])
selected.sort(key=int)
for file in selected:
    file = file + ".in"
    print(file)
    os.system("echo " + file + ">> results.txt")
    os.system("echo " + file + ">> count.txt")
    os.system("{ time ./../bin/p2 < " + file +
              " >> count.txt ; } 2>> results.txt")
    os.system("echo >> results.txt")
    os.system("echo >> count.txt")
