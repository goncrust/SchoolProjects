from main import *

def teste121():
    
    print(corrigir_palavra('abBAx'))
    # x

    print(corrigir_palavra('cCdatabasacCADde'))
    # database

    return

teste121()

def teste122():

    print(eh_anagrama('caso', 'SaCo'))
    # True

    print(eh_anagrama('caso', 'casos'))
    # False

    return

teste122()

def teste123():

    #print(corrigir_doc('???'))
    # corrigir_doc: argumento invalido

    doc = 'BuAaXOoxiIKoOkggyrFfhHXxR duJjUTtaCcmMtaAGga eEMmtxXOjUuJQqQHhqoada JlLjbaoOsuUeYy cChgGvValLCwMmWBbclLsNn LyYlMmwmMrRrongTtoOkyYcCK daRfFKkLlhHrtZKqQkkvVKza'
    print(corrigir_doc(doc))
    # Buggy data base has wrong data

    return

teste123()

def teste221():

    print(obter_posicao('C', 5))
    # 2

    return

teste221()

def teste222():

    print(obter_digito('CEE', 5))
    # 1

    return

teste222()

def teste223():

    #print(obter_pin(()))
    # obter_pin: argumento invalido

    t = ('CEE', 'DDBBB', 'ECDBE', 'CCCCB')
    print(obter_pin(t))
    # (1, 9, 8, 5)

    return

teste223()

def teste321():

    print(eh_entrada(('a-b-c-d-e-f-g-h', '[abcd]', (950, 300))))
    # False

    print(eh_entrada(('a-b-c-d-e-f-g-h-2', '[abcde]', (950, 300))))
    # False

    print(eh_entrada(('a-b-c-d-e-f-g-h', '[xxxxx]', (950, 300))))
    # True

    return

teste321()

def teste322():

    print(validar_cifra('a-b-c-d-e-f-g-h', '[xxxxx]'))
    # False

    print(validar_cifra('a-b-c-d-e-f-g-h', '[abcde]'))
    # True

    return

teste322()

def teste323():

    #print(filtrar_bdb([]))
    # filtrar_bdb: argumento invalido

    bdb = [('aaaaa-bbb-zx-yz-xy', '[abxyz]', (950, 300)), ('a-b-c-d-e-f-g-h', '[abcde]', (124, 325, 7)), ('entrada-muito-errada', '[abcde]', (50, 404))]
    print(filtrar_bdb(bdb))
    # [('entrada-muito-errada', '[abcde]', (50, 404))]

    return

teste323()

def teste421():

    print(eh_entrada(('qgfo-qutdo-s-egoes-wzegsnfmjqz', '[abcde]', (2223,424,1316,99))))
    # True

    return

teste421()

def teste422():

    print(obter_num_seguranca((2223,424,1316,99)))
    # 325

    return

teste422()

def teste423():

    print(decifrar_texto('qgfo-qutdo-s-egoes-wzegsnfmjqz', 325))
    # esta cifra e quase inquebravel

    return

teste423()

def teste424():

    #print(decifrar_bdb([('nothing')]))
    # decifrar_bdb: argumento invalido

    bdb = [('qgfo-qutdo-s-egoes-wzegsnfmjqz', '[abcde]', (2223,424,1316,99)), ('lctlgukvzwy-ji-xxwmzgugkgw', '[abxyz]', (2388, 367, 5999)), ('nyccjoj-vfrex-ncalml', '[xxxxx]', (50, 404))]
    print(decifrar_bdb(bdb))
    # ['esta cifra e quase inquebravel', 'fundamentos da programacao', 'entrada muito errada']

    return

teste424()

def teste521():

    print(eh_utilizador({'name': 'john.doe', 'pass': 'aabcde', 'rule': {'vals': (1, 3), 'char': 'a'}}))
    # True

    print(eh_utilizador({'name': 'john.doe', 'pass': 'aabcde', 'rule': {'vals': 1, 'char': 'a'}}))
    # False

    return

teste521()

def teste522():

    print(eh_senha_valida('aabcde', {'vals': (1, 3), 'char': 'a'}))
    # True

    print(eh_senha_valida('cdefgh', {'vals': (1, 3), 'char': 'b'}))
    # False

    return

teste522()

def teste523():

    #print(filtrar_senhas([]))
    # filtrar_senhas: argumento invalido

    bdb = [{'name': 'john.doe', 'pass': 'aabcde', 'rule': {'vals': (1, 3), 'char': 'a'}}, {'name': 'jane.doe', 'pass': 'cdefgh', 'rule': {'vals': (1, 3), 'char': 'b'}}, {'name': 'jack.doe', 'pass': 'cccccc', 'rule': {'vals': (2, 9), 'char': 'c'}}]
    print(filtrar_senhas(bdb))
    # ['jack.doe', 'jane.doe']

    return

teste523()
