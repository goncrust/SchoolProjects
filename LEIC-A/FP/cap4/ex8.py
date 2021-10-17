
def junta_ordenados(digs1, digs2):

    i_1 = 0
    i_2 = 0

    res = ()
    while i_1 < len(digs1) and i_2 < len(digs2):
        
        if digs1[i_1] < digs2[i_2]:
            res += (digs1[i_1],)
            i_1 += 1
        elif digs1[i_1] > digs2[i_2]:
            res += (digs2[i_2],)
            i_2 += 1
        else:
            res += (digs1[i_1])
            i_1 += 1
            i_2 += 1

    if i_1 < len(digs1):
        res += digs1[i_1:] 
    elif e_2 < len(digs2):
        res += digs2[i_2:]

    return res

        
