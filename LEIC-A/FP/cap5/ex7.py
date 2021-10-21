
def produto_vetorial(v1, v2):
    soma = 0
    for i in range(len(v1)):
        soma += v1[i] * v2[i]

    return soma

def multiplica_mat(m1, m2):

    m2_colunas = []
    for c in range(len(m2[0])):
        m2_colunas.append([])
        for l in m2:
            m2_colunas[c].append(l[c])

    m_res = []
    for l in range(len(m2_colunas)):
        m_res.append([])
        for c in m1:
            m_res[l].append(produto_vetorial(m2_colunas[l], c))


    return m_res

# test
#import ex5
#a = [[2, 3, 1], [-1, 0, 2]]
#b = [[1, -2], [0, 5], [4, 1]]
#ex5.escreve_matriz(multiplica_mat(a, b))

