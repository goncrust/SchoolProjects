# Projeto de PE: 6-10

# distribuição
benford_dist <- function(x) {
  return(log10(1 + 1/x))
}

# 1
b1 <- benford_dist(1)
b6 <- benford_dist(6)
b <- b1 + b6

# 2
fr6.2 <- 5/11

# 3
ans <- round(abs(b - fr6.2), 4)
ans