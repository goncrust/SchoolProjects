
def soma_mat(m1, m2):

    for l in range(len(m1)):
        for c in range(len(m1[l])):
            m1[l][c] += m2[l][c]

    return m1
            
