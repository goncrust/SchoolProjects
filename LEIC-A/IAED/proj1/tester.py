"""
Script de testagem automática do primeiro projeto de IAED.

Utilização:

1 - Colocar o script na pasta onde se encontra o vosso projeto compilado.
2 - Colocar a pasta de testes publicos que transferem do fénix na pasta do vosso projeto,
    garantindo que o nome é exatamente "tests" e que o único conteúdo são os ficheiros .in e .out.
3 - Abrir o terminal.
4 - $ sudo python3 tester.py ./{proj}, 
    onde {proj} é o nome do ficheiro binário proveniente da compilação.


Output:
Quando o vosso programa faz o output correto, 
verão uma mensagem que confirma que passaram no teste.

Caso haja alguma diferença entre o output correto e o output do vosso programa, 
o script imprimirá um erro.

Convém sempre verificar manualmente as diferenças, e comparar o input com o output do teste, 
visto que a ferramenta que o script utiliza (diff), não é perfeita, e retorna um output difícil
de interpretar.

"""

import subprocess
import sys
import os
from pathlib import Path

VERSION = 1.1

if len(sys.argv) < 2 or len(sys.argv[1]) <= 2:
    print(
        """
Utilização:

$ sudo python3 tester.py ./{PROJ}
    """
    )
    exit()

print(f"tester.py - versão {VERSION}\n")

project_name = sys.argv[1][2:]
project_path = Path(__file__).absolute().parent
test_folder = str(project_path) + '/tests/'

files_in = []
files_out = []

for f in os.listdir(test_folder):
    if os.path.isfile(os.path.join(test_folder, f)):
        if str(f)[-2:] == 'in':
            files_in.append(f)
        elif str(f)[-3:] == 'out':
            files_out.append(f)

files_in = sorted(files_in)
files_out = sorted(files_out)

if len(files_out) != len(files_in):
    raise RuntimeError("ERRO - Testes inválidos")

TEST_NR = len(files_in)

passes = 0
fails = 0

for i in range(len(files_in)):
    test_path = test_folder + files_in[i]
    check_path = test_folder + files_out[i]

    output_path = test_folder + f'output{(i+1):02d}.result'

    input = open(test_path)
    output = open(output_path, 'w')

    exec = subprocess.run(
        [f"./{project_name}"], stdin=input, stdout=output)
    diff = subprocess.run(
        ["diff", check_path, output_path], stdout=subprocess.PIPE)

    if diff.returncode != 0:
        print(f"\nFAILED - Teste {(i+1):02d}\n")
        print(diff.stdout.decode("utf-8"))
        fails += 1
    else:
        print(f"OK - Teste {(i+1):02d}")
        passes += 1


print(f"""
RESULTADOS:

Testes executados: {TEST_NR}

Passou: {passes}
Falhou: {fails}
""")
