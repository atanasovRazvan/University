from Utils import readData, statisticalNormalisation, normaliseTestData
from Regression import GDRegression

input, output = readData("homedatanew.csv", ["bedrooms", "bathrooms", "sqft_living", "sqft_basement"], "price")
input2, output2 = readData("homedatanew.csv", ["bedrooms", "bathrooms", "sqft_living", "sqft_basement"], "price")

nonNormalisedTrainInput = input[:1000]
trainOutput = output[:1000]
nonNormalisedTestInput = input[1000:1050]
testOutput = output[1000:1050]

mean1, stDev1, features1 = statisticalNormalisation([el[0] for el in nonNormalisedTrainInput])
mean2, stDev2, features2 = statisticalNormalisation([el[1] for el in nonNormalisedTrainInput])
mean3, stDev3, features3 = statisticalNormalisation([el[2] for el in nonNormalisedTrainInput])
mean4, stDev4, features4 = statisticalNormalisation([el[3] for el in nonNormalisedTrainInput])

trainInput = [[feat1, feat2, feat3, feat4] for feat1, feat2, feat3, feat4 in zip(features1, features2, features3, features4)]

testFeatures1 = normaliseTestData([el[0] for el in nonNormalisedTestInput], mean1, stDev1)
testFeatures2 = normaliseTestData([el[1] for el in nonNormalisedTestInput], mean2, stDev2)
testFeatures3 = normaliseTestData([el[2] for el in nonNormalisedTestInput], mean3, stDev3)
testFeatures4 = normaliseTestData([el[3] for el in nonNormalisedTestInput], mean4, stDev4)

testInput = [[feat1, feat2, feat3, feat4] for feat1, feat2, feat3, feat4 in zip(testFeatures1, testFeatures2, testFeatures3, testFeatures4)]

gd = GDRegression()
gd.fit(trainInput, trainOutput)
computedOutput = gd.predict(testInput)

mse = 0

for i in range(len(computedOutput)):
    mse += (computedOutput[i] - testOutput[i]) ** 2

print("MSE: ", mse / len(computedOutput))

nonNormalisedTestInputNew = input2[1050:1100]

testFeatures1New = normaliseTestData([el[0] for el in nonNormalisedTestInputNew], mean1, stDev1)
testFeatures2New = normaliseTestData([el[1] for el in nonNormalisedTestInputNew], mean2, stDev2)
testFeatures3New = normaliseTestData([el[2] for el in nonNormalisedTestInputNew], mean3, stDev3)
testFeatures4New = normaliseTestData([el[3] for el in nonNormalisedTestInputNew], mean4, stDev4)

testInputNew = [[feat1, feat2, feat3, feat4] for feat1, feat2, feat3, feat4 in zip(testFeatures1New, testFeatures2New, testFeatures3New, testFeatures4New)]
testOutputNew = output2[1050:1100]
computedOutputNew = gd.predict(testInputNew)

mse = 0
for i in range(len(computedOutputNew)):
    mse += (computedOutputNew[i] - testOutputNew[i]) ** 2

print("MSE for new data: ", mse / len(computedOutputNew))



