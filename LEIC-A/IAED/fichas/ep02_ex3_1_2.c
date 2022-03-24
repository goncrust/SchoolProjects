
typedef struct {
    int dia; 
    int mes;
    int ano;
} Data;

typedef struct {
    Data data;
    int duracao;
    int num_destino;
} Chamada;

typedef struct {
    int num;
    char codigo[51];
    float preco;
    int num_chamadas;
    Chamada chamadas[100];
} Telefone;

typedef struct {
    char num[16];
    char nome[101];
    char morada[101];
    Data nascimento;
    int num_telefones;
    Telefone telefones[10];
} Cliente;

float valorTotal(Telefone t[], int n) {
    float total = 0;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < t[i].num_chamadas; j++) {
            total += t[i].preco * ((float) t[i].chamadas[j].duracao / 60);
        }
    }

    return total;
}
