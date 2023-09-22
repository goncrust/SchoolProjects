# Projeto de PE: 7-10

amostras <- 1740
dim <- 15
set.seed(1805)

# sqsum é lista de soma dos quadrados dos valores observados das 1740 amostras
sqsum <- c()
for (i in 1:amostras){
  r <- rnorm(dim, 0, 1)
  r <- sum(r^2)
  sqsum <- c(sqsum, r)
}

# quantil 0.62 - quantil da dist. teórica

q <- quantile(sqsum, 0.62, type=2)
chi <- qchisq(0.62, df=dim)
q <- as.numeric(q)
ans <- round(q-chi, 4)
ans