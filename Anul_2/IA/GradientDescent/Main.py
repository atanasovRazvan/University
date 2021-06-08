from math import sqrt

import numpy as np

from Utils import readData
from GDRegression import GDRegression

# ---------------------------------
# getting data
# ---------------------------------
input, output = readData("world-happiness-report-2017.csv", ["Economy..GDP.per.Capita.", "Freedom"], "Happiness.Score")

indexes = [i for i in range(len(input))]
trainIndex = np.random.choice(indexes, int(0.8 * len(input)))
testIndex = [i for i in indexes if i not in trainIndex]

trainInput = [input[i] for i in trainIndex]
trainOutput = [output[i] for i in trainIndex]

testInput = [input[i] for i in testIndex]
testOutput = [output[i] for i in testIndex]

regression = GDRegression()
regression.fit(trainInput, trainOutput)

computedOutput = regression.predict(testInput)
error = 0
for i in range(len(testOutput)):
    error += sqrt ((testOutput[i] - computedOutput[i]) ** 2)

print(error / len(testOutput))
