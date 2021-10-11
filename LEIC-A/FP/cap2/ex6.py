a = eval(input("a: "))
b = eval(input("b: "))
c = eval(input("c: "))

if a > b:
    if a > c:
        print(a)
    else:
        print(c)
else:
    if b > c:
        print(b)
    else:
        print(c)

