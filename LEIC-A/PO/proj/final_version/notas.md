O padrao visitor pode ser usado para criar filtros pa pesquisa (por ex quando queremos ver so clientes com dividas)

Ainda nao percebi como implementar o visitor nas notificacoes

O plano tarifario esta implementado da melhor forma?

BasePlan - O base plan devia ter um atributo client ou clientType, como so precisa de saber o client
type faria sentido guardar enquanto atributo so o client type. Se guardar o client type como atributo aquilo
fica tipo ponteiro para o atributo do Client e quando o atualizar aquilo tambem muda só? Caso não, e preciso guardar
o client todo (acho)

Na entrega final trocar a utilizacao do .toString() nas classes para uma aplicacao do visitor

Trocar Notification pra ser um record (verificar compatibilidade)

Tirar os enums (sqe)

Rever a maneira como se regista o terminal