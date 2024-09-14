#SCRIPT 1#
#City position distribution analysis: You can extract coordinates (pos) for each city and create a graph or histogram to visualize the spatial distribution of cities.
 library(jsonlite)
# Path to JSON file
json_file <- "/Users/kevinfloride/Downloads/rep/repkevin.json"
# Upload JSON file
data <- fromJSON("/Users/kevinfloride/Downloads/rep/repkevin.json")
# Extract the positions of the cities
 positions <- sapply(data$cityList, function(x) x$pos)
# Create a position histogram
 hist(positions, main = "Distribution des positions des villes", xlab = "Position")
#Extract x and y coordinates from data object for regression
 x <- sapply(data$cityList, function(city) city$pos[1])
y <- sapply(data$cityList, function(city) city$pos[2])
#Perform linear regression using the lm() function to
#fit a regression line to
model <- lm(y ~ x)
#Plot the regression curve using the plot() function
#to show data points and
# the abline() function to draw the regression line
plot(x, y, pch = 16, xlab = "Coordonnée x", ylab = "Coordonnée y")
abline(model, col = "red")
#Access data
city_data <- data$cityList
#Calculate descriptive statistics
# Extract x and y coordinates
x <- sapply(city_data, function(x) x$pos[1])
y <- sapply(city_data, function(x) x$pos[2])
# Descriptive statistics for x coordinatesummary(x)
mean(x)
sd(x)
# Descriptive statistics for the y coordinate
summary(y)
mean(y)
sd(y)
#moustach bottles
positions <- data$cityList
df <- data.frame(x = sapply(positions, function(x) x$pos[1]), y = sapply(positions, function(x) x$pos[2]))

ggplot(df, aes(x = "", y = y)) +
  geom_boxplot() +
  labs(title = "Boîte à moustaches des positions des villes")

## work with CSV file
 filepath <- "/Users/kevinfloride/Downloads/rep/Statistical_Study.csv"
> data <- read.csv(filepath)
## Load the jsonlite library
library(jsonlite)
# Replace 'your_json_data' with the actual JSON data
your_json_data <- '[
  {
    "Number of Cities": "20",
    "Execution_Time (test 1)": "26.75",
    ...
    "Average": "6537.9"
  }
]'

#SCRIPT 2#
json_file <- "/Users/kevinfloride/Downloads/instance.json"
#After converting csv to json
# Convert the JSON data to a list
data <- jsonlite::fromJSON(your_json_data)
# Extract the "Average" values for each object in the list
averages <- sapply(data, function(x) as.numeric(x))
df <- as.data.frame(data)
numeric_columns <- c("Number of Cities", "Average", paste0("Execution_Time (test ", 1:25, ")"))
df[numeric_columns] <- lapply(df[numeric_columns], as.numeric)
summary(df[numeric_columns])
# Create the histogram
hist(averages, main = "Histogram of Average Execution Times", xlab = "Average Execution Time")
#regression courb
library(ggplot2)
library(farver)
ggplot(data = df, aes(x = as.numeric(`Number of Cities`), y = as.numeric(Average))) +
  geom_point() +  # Display observed data points

  geom_smooth(method = "lm", se = FALSE) + # Add the regression curve
  labs(x = "Number of Cities", y = "Average Execution Time")  # Define axis labels