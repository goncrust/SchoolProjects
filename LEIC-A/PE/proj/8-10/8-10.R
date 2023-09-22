# Projeto de PE: 8-10
library(ggplot2)

# Constantes
loc <- 1
scale <- 1.8
tamanho <- 107

# Setup
set.seed(2009)
values <- sort(rcauchy(tamanho, loc, scale))

quantil <- c()
for (i in 1:tamanho) {
  quantil <- c(quantil, i/(tamanho+1))
}

# Junta os dois conjuntos numa dataframe só
df <- as.data.frame(quantil)
df$values <- values

# Adiciona a coluna da distribuição normal
df$values2 <- sort(rnorm(tamanho, -2.8, 1.183))

# Desenha o gráfico dos dois conjuntos
ggplot(df, aes(x=values, y=quantil)) + geom_line(linewidth = 0.5) +
  geom_line(aes(x=values2, y=quantil), colour = 'red') +
  geom_segment(aes(x=0, y=0, xend=1, yend=1), colour = 'blue', linetype = 'dashed')
  
