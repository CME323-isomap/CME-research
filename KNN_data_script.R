# Import libraries
library(readr)
library(ggplot2)
library(dplyr)
library(mvtnorm)

# Parms
n <- 100 # number data points per centroid
sd <- 1 # standard deviation for each centroid

# Define our four centroids
centroid_1 <- c(0, 0)
centroid_2 <- c(10, 10)
centroid_3 <- c(0, 10)
centroid_4 <- c(10, 0)

# Make covariance matrix
sd_mat <- matrix(0, 2, 2)
sd_mat[1, 1] <- sd
sd_mat[2, 2] <- sd

# Generate 100 data points per centroid. Bivariate Normal, sd = 1
data_1 <- rmvnorm(n = n, mean = centroid_1, sigma = sd_mat)
data_2 <- rmvnorm(n = n, mean = centroid_2, sigma = sd_mat)
data_3 <- rmvnorm(n = n, mean = centroid_3, sigma = sd_mat)
data_4 <- rmvnorm(n = n, mean = centroid_4, sigma = sd_mat)

# Classify our data into 4 classes
data_1 <- cbind(data_1, rep(1, 100))
data_2 <- cbind(data_2, rep(2, 100))
data_3 <- cbind(data_3, rep(3, 100))
data_4 <- cbind(data_4, rep(4, 100))

# Bind our data together
sep_data <- as.data.frame(rbind(data_1, data_2, data_3, data_4))

sep_data %>% ggplot() +
  geom_point(mapping = aes(x = V1, y = V2, color = V3))

# Write out data
write_csv(sep_data, path = "/Users/henryneeb/CME-research/sep_data.txt")
