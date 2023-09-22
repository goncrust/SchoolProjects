library(ggplot2)

set.seed(1636)
p <- 0.8
k <- 1000
confidence <- 0.91
sizes <- c(30, 50, 100, 200, 300, 500, 1000)

met_2 <- function(x) {
    n <- length(x)
    mx <- mean(x)
    return(qnorm(1 - confidence/2)*sqrt(mx*(1-mx)/n))    
}

met_1 <- function(x) {
    n <- length(x)
    mx <- mean(x)
    z <- qnorm((1 + confidence)/2)
    a <- 1 + (z^2)/n
    b <- -2*mx - (z^2)/n
    c <- mx^2
    root1 <- (-b - sqrt(b^2 - 4*a*c))/(2*a)
    root2 <- (-b + sqrt(b^2 - 4*a*c))/(2*a)
    return(abs(root1 - root2))
}

means <- c()
for (i in sizes) {
    dif <- 0
    for (j in 1:k) {
        x <- rbinom(i, 1, p)
        met_1_length <- met_1(x)
        met_2_length <- 2*met_2(x)
        dif <- dif + (met_1_length - met_2_length)
    }
    means <- c(means, dif/k)
}

ggplot() +
    geom_point(aes(x=sizes, y=means)) +
    geom_line(aes(x=sizes, y=means)) +
    labs(x='Tamanho da amostra', y='Diferença entre comprimentos dos intervalos de confiança')