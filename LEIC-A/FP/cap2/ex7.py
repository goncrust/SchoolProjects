hours = eval(input("Horas: "))
salary = eval(input("Salario/hora: "))

total = 0

if hours > 40:
    total = 40 * salary
    total += (hours - 40) * salary * 2
else:
    total = hours * salary

print("Ordenado semanal:", total)
