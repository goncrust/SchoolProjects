
def seq_racaman(num):

    res = [0]
    for n in range(1, num):
        
        if res[n-1] > n and not res[n-1] - n in res:
            res.append(res[n-1] - n)
        else:
            res.append(res[n-1] + n)

    return res

