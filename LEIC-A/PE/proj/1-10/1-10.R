# Projeto de PE: 1/10

library(readxl)
library(dplyr)
library(ggplot2)

# Lê o ficheiro dado, filtra o ano, mantem as colunas que interessam
data <- read_excel('econ.xlsx')
data <- subset(data, select = c(tempo, ndesemp, pop))
data <- filter(data, substr(tempo,1,4) >= 1981)

# Unifica a escala dos dados
avg_pop <- mean(data$pop)
avg_ndesemp <- mean(data$ndesemp)

stdev_op <- sd(data$pop)
stdev_ndesemp <- sd(data$ndesemp)

transformed <- data

transformed$pop <- (transformed$pop - avg_pop)/stdev_op
transformed$ndesemp <- (transformed$ndesemp - avg_ndesemp)/stdev_ndesemp

# Cria um gráfico onde se pode analisar a evolução dos dados ao longo do tempo
ggplot(transformed, aes(tempo)) +
  geom_line(data=transformed, aes(y=pop), linewidth = 0.5) +
  geom_line(data=transformed, aes(y=ndesemp), colour = 'red', linewidth = 0.5) +
  scale_y_continuous(name = "População",
                     sec.axis = sec_axis(~.,name = "Desemprego Absoluto")) +
  labs(title = "Evolução Populacional e do Desemprego Absoluto",
       caption = "Source: FRED") +
  theme(axis.text.y = element_blank(),
    axis.title.y.right = element_text(color = 'red', size = 13))