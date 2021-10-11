valor = eval(input("Euros: "))
valor = int(valor * 100)

notas_50 = valor // 5000
valor %= 5000
print("Notas 50:", notas_50)

notas_20 = valor // 2000
valor %= 2000
print("Notas 20:", notas_20)

notas_10 = valor // 1000
valor %= 1000
print("Notas 10:", notas_10)

notas_5 = valor // 500
valor %= 500
print("Notas 5:", notas_5)

moedas_2 = valor // 200
valor %= 200
print("Moedas 2:", moedas_2)

moedas_1 = valor // 100
valor %= 100
print("Moedas 1:", moedas_1)

moedas_50cent = valor // 50
valor %= 50
print("Moedas 0.50:", moedas_50cent)

moedas_20cent = valor // 20
valor %= 20
print("Moedas 0.20:", moedas_20cent)

moedas_10cent = valor // 10
valor %= 10
print("Moedas 0.10:", moedas_10cent)

moedas_5cent = valor // 5
valor %= 5
print("Moedas 0.05:", moedas_5cent)

moedas_2cent = valor // 2
valor %= 2
print("Moedas 0.02:", moedas_2cent)

moedas_1cent = valor // 1
print("Moedas 0.01:", moedas_1cent)

