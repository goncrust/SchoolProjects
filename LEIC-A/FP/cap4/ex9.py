
def reconhece(frase):

    in_letras = True

    for c in frase:
        if c == 'A' or c == 'B' or c == 'C' or c == 'D':
            if not in_letras:
                return False
        elif c == '1' or c == '2' or c == '3' or c == '4':
            if in_letras:
                in_letras = False
        else:
            return False

    return True
