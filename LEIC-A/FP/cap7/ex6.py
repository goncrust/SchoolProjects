
def func_g(n):
    if n == 0:
        print(f'func_g({n})')
        return 0
    else:
        print(f'func_g({n}), calling func_g(func_g({n - 1}))')
        return n - func_g(func_g(n - 1))

