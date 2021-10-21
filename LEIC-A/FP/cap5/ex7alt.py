
def multiplica_mat(m1, m2):

    if len(m1[0]) != len(m2):
        raise ValueError("colunas m1 tem de ser igual a linhas de m2")

    k = len(m2)
    colunas_res = len(m2[0])
    linhas_res = len(m1)

    m_res = []
    for l in range(linhas_res):
        linha = []

        for c in range(colunas_res):
            produto_vetorial = 0
            for z in range(k):
                produto_vetorial += m1[l][z] * m2[z][c]
            linha.append(produto_vetorial)

        m_res.append(linha)

    return m_res
                
# test
#import ex5
#a = [[2, 3, 1], [-1, 0, 2]]
#b = [[1, -2], [0, 5], [4, 1]]
#ex5.escreve_matriz(multiplica_mat(a, b))
