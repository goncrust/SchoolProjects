# Setup
set.seed(131)
test_count <- 300

# Teste de hipóteses para distribuição normal com valor esperado desconhecido
var <- 4
miu0 <- 37.5
alpha <- 0.02
sample_size <- 45
gamma <- 38.7

var_fulcral <- function(x) {
  return((mean(x) - miu0)/(sqrt(var)/sqrt(length(x))))
}

# Teste
rejeicoes <- 0
for (i in 1:test_count) {
  sample <- rnorm(sample_size, gamma, sqrt(var))
  quantil <- qnorm(1-(alpha/2))
  stat_teste <- var_fulcral(sample)
  if (stat_teste < -quantil || stat_teste > quantil) {
    rejeicoes <- rejeicoes + 1
  }
}

# Resultado
ans <- 1 - round(rejeicoes/test_count, 3)
ans