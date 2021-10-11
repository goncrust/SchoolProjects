num = 0
res = 0
i = 1

while i <= 9:
    num *= 10
    num += i

    res = num * 8 + i

    print(num, "x 8 +", i, "=", res)

    i += 1
