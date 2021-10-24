def teste121():
    total_score = 0
    fun_name = corrigir_palavra_public

    corrigir_palavra('abBAx')
    # x

    corrigir_palavra('cCdatabasacCADde')
    # database

    return


def teste122():
    total_score = 0
    fun_name = eh_anagrama_public

    eh_anagrama('caso', 'SaCo')
    # True

    eh_anagrama('caso', 'casos')
    # False

    return


def teste123():
    total_score = 0
    fun_name = corrigir_doc_public

    corrigir_doc('???')
    # corrigir_doc: argumento invalido

    doc = 'BuAaXOoxiIKoOkggyrFfhHXxR duJjUTtaCcmMtaAGga eEMmtxXOjUuJQqQHhqoada JlLjbaoOsuUeYy cChgGvValLCwMmWBbclLsNn LyYlMmwmMrRrongTtoOkyYcCK daRfFKkLlhHrtZKqQkkvVKza'
    corrigir_doc(doc)
    # Buggy data base has wrong data

    return


def teste221():
    total_score = 0
    fun_name = obter_posicao_public

    obter_posicao('C', 5)
    # 2

    return


def teste222():
    total_score = 0
    fun_name = obter_digito_public

    obter_digito('CEE', 5)
    # 1

    return


def teste223():
    total_score = 0
    fun_name = obter_pin_public

    obter_pin(())
    # obter_pin: argumento invalido

    t = ('CEE', 'DDBBB', 'ECDBE', 'CCCCB')
    obter_pin(t)
    # (1, 9, 8, 5)

    return


def teste321():

    total_score = 0
    fun_name = eh_entrada_public

    eh_entrada(('a-b-c-d-e-f-g-h', '[abcd]', (950, 300)))
    # False

    eh_entrada(('a-b-c-d-e-f-g-h-2', '[abcde]', (950, 300)))
    # False

    eh_entrada(('a-b-c-d-e-f-g-h', '[xxxxx]', (950, 300)))
    # True

    return

def teste322():
    total_score = 0
    fun_name = validar_cifra_public

    validar_cifra('a-b-c-d-e-f-g-h', '[xxxxx]')
    # False

    validar_cifra('a-b-c-d-e-f-g-h', '[abcde]')
    # True

    return


def teste323():
    total_score = 0
    fun_name = filtrar_bdb_public


    filtrar_bdb([])
    # filtrar_bdb: argumento invalido

    bdb = [('aaaaa-bbb-zx-yz-xy', '[abxyz]', (950, 300)), ('a-b-c-d-e-f-g-h', '[abcde]', (124, 325, 7)), ('entrada-muito-errada', '[abcde]', (50, 404))]
    filtrar_bdb(bdb)
    # [('entrada-muito-errada', '[abcde]', (50, 404))]

    return


def teste421():
    total_score = 0
    fun_name = eh_entrada_public

    eh_entrada(('qgfo-qutdo-s-egoes-wzegsnfmjqz', '[abcde]', (2223,424,1316,99)))
    # True

    return


def teste422():
    total_score = 0
    fun_name = obter_num_seguranca_public

    obter_num_seguranca((2223,424,1316,99))
    # 325

    return


def teste423():
    total_score = 0
    fun_name = decifrar_texto_public

    decifrar_texto('qgfo-qutdo-s-egoes-wzegsnfmjqz', 325)
    # esta cifra e quase inquebravel

    return


def teste424():
    total_score = 0
    fun_name = decifrar_bdb_public

    decifrar_bdb([('nothing')])
    # decifrar_bdb: argumento invalido

    bdb = [('qgfo-qutdo-s-egoes-wzegsnfmjqz', '[abcde]', (2223,424,1316,99)), ('lctlgukvzwy-ji-xxwmzgugkgw', '[abxyz]', (2388, 367, 5999)), ('nyccjoj-vfrex-ncalml', '[xxxxx]', (50, 404))]
    decifrar_bdb(bdb)
    # ['esta cifra e quase inquebravel', 'fundamentos da programacao', 'entrada muito errada']

    return


def teste521():
    total_score = 0
    fun_name = eh_utilizador_public

    eh_utilizador({'name': 'john.doe', 'pass': 'aabcde', 'rule': {'vals': (1, 3), 'char': 'a'}})
    # True

    eh_utilizador({'name': 'john.doe', 'pass': 'aabcde', 'rule': {'vals': 1, 'char': 'a'}})
    # False

    return


def teste522():
    total_score = 0
    fun_name = eh_senha_valida_public

    eh_senha_valida('aabcde', {'vals': (1, 3), 'char': 'a'})
    # True

    eh_senha_valida('cdefgh', {'vals': (1, 3), 'char': 'b'})
    # False

    return


def teste523():
    total_score = 0
    fun_name = filtrar_senhas_public

    filtrar_senhas([])
    # filtrar_senhas: argumento invalido

    bdb = [{'name': 'john.doe', 'pass': 'aabcde', 'rule': {'vals': (1, 3), 'char': 'a'}}, {'name': 'jane.doe', 'pass': 'cdefgh', 'rule': {'vals': (1, 3), 'char': 'b'}}, {'name': 'jack.doe', 'pass': 'cccccc', 'rule': {'vals': (2, 9), 'char': 'c'}}]
    filtrar_senhas(bdb)
    # ['jack.doe', 'jane.doe']

    return
