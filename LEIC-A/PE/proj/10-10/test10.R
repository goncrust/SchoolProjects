sigma <- 2  # raiz do valor do enunciado
h0 <- 37.5  #H0:μ

alpha <- 0.02 # α
semente <- 131
m <- 300  # Number of samples
n <- 45   # Dimension of each sample
mu <- 38.7  # μ Assumed value of mean




set.seed(semente)  # Setting the seed

teste <- function (n, h0, alpha, mu, sigma){
  sample<-rnorm(n, mu, sigma)
  media<-mean(sample)
  t0<-(media-h0)/(sigma/sqrt(n))
  a<-qnorm(1-alpha/2)
  return(abs(t0)<=a)
  
}

results<-replicate(m, teste(n, h0, alpha, mu, sigma))
prob<-mean(results)
prob