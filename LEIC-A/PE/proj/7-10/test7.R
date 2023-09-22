
# Dados do problema
semente <- 1805
m <- 1740
n <- 15
probabilidade <- 0.62


# Fixando a semente
set.seed(semente)

# Simulação das amostras de uma população normal
samples <- matrix(rnorm(m * n), nrow = m, byrow = TRUE)

# Cálculo da soma dos quadrados dos valores observados para cada amostra
soma_quadrados <- rowSums(samples^2)

# Calculando o quantil
quantil_amostra <- quantile(soma_quadrados, probs = probabilidade, type = 2)

# Calculando o quantil teorico
quantile_theoretical <- qchisq(probabilidade, df = n - 1)

# Calculando a diferença em valor absoluto entre os quantis
diferenca <- abs(quantil_amostra - quantile_theoretical)
RESPOSTA=unname(diferenca <- round(diferenca,4))
RESPOSTA
