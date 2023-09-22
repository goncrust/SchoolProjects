# Projeto de PE 3/10

library(dplyr)
library(ggplot2)
library(data.table)

# Lê os dados do ficheiro .txt
data <- fread("GENDER_EMP_19032023152556091.txt")

# Filtra as colunas e linhas indesejadas
data <- filter(data, grepl('ESP', COU))
data <- filter(data, grepl('EMP5', IND))
data <- filter(data, grepl('2015', TIME))
colnames(data)[8] <- "Age"
colnames(data)[17] <- "Percentage"

data <- select(data, c(SEX, Age,  Percentage))

# Altera legendas para facilitar leitura do gráfico
data <- mutate(data, SEX = recode(SEX, ALL_PERSONS = "All", MEN = "M",
                                  WOMEN = "W"),
                                  Age = recode(Age, Total = "All"))

# Cria um gráfico de barras
ggplot(data, aes(SEX, Percentage, fill=Age)) +
  geom_bar(stat = "identity", position = position_dodge2(1)) +
  scale_y_continuous(limits = c(0,100)) +
  labs(title = "Percentage of people employed in part-time employment",
       subtitle = "By Sex and Age Group in Spain, 2015",
       caption = "Source: OCDE") +
  facet_grid(cols = vars(Age))