
def maior_inteiro(limite):
    
    def aux(limite, n, total):
        if total > limite:
            return n - 1
        else:
            return aux(limite, n+1, total+n+1)

    return aux(limite, 1, 1)

