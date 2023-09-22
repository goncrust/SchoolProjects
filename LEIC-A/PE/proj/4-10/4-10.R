# Projeto de PE: 4-10

# Setup
set.seed(4267)
k <- 2072
lambda <- 7.5

# Gen sample
sample <- rexp(k, lambda)

# Calculating constants
time <- cumsum(sample)
T <- ceiling(time[[k]])

# Getting the amount of events per second
events <- rep(0, T)
for (t in time) events[[ceiling(t)]] <- events[[ceiling(t)]] + 1

# Getting the expected values and the mean
mean <- mean(events)
expected <- lambda

# Calculating the answer
ans <- abs(mean - expected)
ans <- round(ans, 4)
print(ans)