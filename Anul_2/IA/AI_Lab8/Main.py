import numpy as np
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_squared_error

from Regressor import Regressor
from Utils import readData, plotTrain, plotTest

# ---------------------------------
# getting data
# ---------------------------------
input, output = readData("data/2017.csv", ["Economy..GDP.per.Capita.", "Freedom"], "Happiness.Score")

indexes = [i for i in range(len(input))]
trainIndex = np.random.choice(indexes, int(0.8 * len(input)))
testIndex = [i for i in indexes if i not in trainIndex]

trainInput = [([1] + input[i]) for i in trainIndex]
trainOutput = [[output[i]] for i in trainIndex]

testInput = [input[i] for i in testIndex]
testOutput = [output[i] for i in testIndex]

# ------------------------
# TOOL regression
# ------------------------
regressor = LinearRegression()
regressor.fit(trainInput, trainOutput)

testInputs = [[regressor.intercept_] + testInput[i] for i in range(len(testInput))]
computedDataOutput = regressor.predict(testInputs)

error = mean_squared_error(testOutput, computedDataOutput)
print("Tool error: ", error)

# ----------------------------
# MANUAL regression
# ----------------------------
regressor = Regressor()

regressor.fit(trainInput, trainOutput)

computedOutput = [regressor.predict(data) for data in testInput]

error = 0
for t1, t2 in zip(computedOutput, testOutput):
    error += (t1 - t2) ** 2
error = error / len(testOutput)
print("Manual error: ", error)

# ----------------------
# plot
# ----------------------
plotTrain(regressor, trainInput, trainOutput)
plotTest(regressor, trainInput, trainOutput, testInput, computedOutput)
