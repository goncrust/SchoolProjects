from main import *


def prado_tests():
    dim = cria_posicao(11, 4)
    obs = (cria_posicao(4,2), cria_posicao(5,2))
    an1 = tuple(cria_animal('rabbit', 5, 0) for i in range(3))
    an2 = (cria_animal('lynx', 20, 15),)
    pos = tuple(cria_posicao(p[0],p[1]) for p in ((5,1),(7,2),(10,1),(6,1)))
    prado = cria_prado(dim, obs, an1+an2, pos)
    print(obter_tamanho_x(prado), obter_tamanho_y(prado))
    print(prado_para_str(prado))
    p1 = cria_posicao(7,2)
    p2 = cria_posicao(9,3)
    prado = mover_animal(prado, p1, p2)
    print(prado_para_str(prado))
    print(obter_valor_numerico(prado, cria_posicao(9,3)))
    print(posicao_para_str(obter_movimento(prado, cria_posicao(5,1))))
    print(posicao_para_str(obter_movimento(prado, cria_posicao(6,1))))
    print(posicao_para_str(obter_movimento(prado, cria_posicao(10,1))))


def geracao_tests():
    dim = cria_posicao(11, 4)
    obs = (cria_posicao(4,2), cria_posicao(5,2))
    an1 = tuple(cria_animal('sheep', 2, 0) for i in range(3))
    an2 = (cria_animal('wolf', 10, 3),)
    pos = tuple(cria_posicao(p[0],p[1]) for p in ((2,2),(4,3),(10,2),(3,2)))
    prado = cria_prado(dim, obs, an1+an2, pos)
    print(prado_para_str(prado))
    print(prado_para_str(geracao(prado)))
    print(prado_para_str(geracao(prado)))
    print(prado_para_str(geracao(prado)))


def simulacao_tests():
    simula_ecossistema('config.txt', 200, False)
    simula_ecossistema('config.txt', 200, True)

simulacao_tests()