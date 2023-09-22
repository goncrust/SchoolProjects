# Projeto de PE: 5-10

# Constantes
param_geom <- 0.3
size <- 1026

# Obter valores simulados
simulados <- c()
set.seed(1306)
while (length(simulados) != size) {
  u <- runif(1, 0, 1)
  x <- 0
  
  while(u > pgeom(x, param_geom)) {x <- x + 1}
  if (pgeom(x-1, param_geom) < u & u <= pgeom(x, param_geom)) {
    simulados <- c(simulados, x)
  }
}

# Obter média e stdev amostrais
mean <- mean(simulados)
stdev <- sd(simulados)

# Calcular a proporção 
divisor <- length(simulados[simulados > mean])
dividendo <- length(simulados[simulados > mean + stdev] )

ans <- round(dividendo/divisor, 4)
ans