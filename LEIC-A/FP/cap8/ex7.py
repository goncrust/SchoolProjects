
def primeiro_no_alfabeto(l1, l2):

    l1 = l1.lower()
    l2 = l2.lower()

    if l1 == l2:
        return False

    indice = -1
    if l1 <= l2:
        for i in range(len(l1)):
            if l1[i] != l2[i]:
                indice = i
                break

        if indice == -1:
            return True
    else:
        for i in range(len(l2)):
            if l1[i] != l2[i]:
                indice = i
                break

        if indice == -1:
            return False

    alfabeto = ('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z')

    for l in alfabeto:
        if l == l1[indice]:
            return True
        elif l == l2[indice]:
            return False

        
def mostra_ordenado(d):

    l = list(d.items())
    case_found = True

    for i in range(len(l) - 1, -1, -1):
        case_found = False
        for k in range(i):
            if primeiro_no_alfabeto(l[k+1][0], l[k][0]):
                l[k+1], l[k] = l[k], l[k+1]
                case_foud = True

        if not case_found:
            break

    for e in l:
        print(e[0], e[1])

