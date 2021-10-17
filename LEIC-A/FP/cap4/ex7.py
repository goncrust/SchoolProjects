
def amigas(str1, str2):

    if not (isinstance(str1, str) and isinstance(str1, str)):
        raise ValueError("amigas: argumento nao string")

    if len(str1) != len(str2):
        raise ValueError("amigas: strings de diferentes comprimentos")

    diferentes = 0
    for i in range(len(str1)):
        if str1[i] != str2[i]:
            diferentes += 1
        
    percentagem = diferentes / len(str1)

    return percentagem < 0.1


