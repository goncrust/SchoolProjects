##############################################
################ Exercício 4 #################
##############################################
############## João Silveira #################
############## Tomás Garrido #################
############# Guilherme Lopes ################
##############################################

# Dados do problema
semente <- 4267
lambda <- 7.5
k <- 2072

# Fixar a semente
set.seed(semente)

# Gerar a amostra de dimensão k proveniente de uma distribuição exponencial
amostras <- rexp(k, rate = lambda)

# Calcular o somatório do início até à n-ésima posição para todas as posições
somatorios <- cumsum(amostras)

# Determinar o valor T (ceil arredonda para cima)
T <- ceiling(somatorios[length(somatorios)])

# Dividir o intervalo ]0, T] em intervalos de amplitude unitária
intervalos <- 0:T

# Contabilizar o número de acontecimentos em cada subintervalo
histograma <- hist(somatorios, breaks = intervalos, plot = FALSE)$counts

# Calcular a média do número de acontecimentos por subintervalo
media_acontecimentos <- mean(histograma)

# Calcular o desvio absoluto entre a média e o valor esperado
# Nota: o valor teórico é o próprio lambda
desvio_absoluto <- abs(media_acontecimentos - lambda)

# Arredondar a 4 casas decimais
desvio_arredondado <- round(desvio_absoluto, 4)

RESPOSTA <- desvio_arredondado
RESPOSTA