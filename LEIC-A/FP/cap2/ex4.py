x = eval(input("Escreva o numero de segundos "))

secs = x % 60
min = x // 60

hours = min // 60
min = min % 60

days = hours // 24
hours = hours % 24

print("dias:", days, "horas:", hours, "mins:", min, "segs:", secs)
