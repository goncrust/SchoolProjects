
def conta_linhas(ficheiro):

    with open(ficheiro, 'r') as f:
        lines = f.readlines()
        return len(lines)
    
