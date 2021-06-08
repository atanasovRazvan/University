import random

from Utils import readData, statisticalNormalisation, computeError, normaliseTestData
from Regression import MyLogisticRegression

nonShuffledInputs, nonShuffledOutputs = readData()
indexes = random.sample(range(len(nonShuffledInputs)), len(nonShuffledInputs))

inputs = [nonShuffledInputs[i] for i in indexes]
outputs = [nonShuffledOutputs[i] for i in indexes]

lenght = len(outputs)

start = 0
finish = 0

TrainInputs = []
TestInputs = []
TrainOutputs = []
TestOutputs = []
RLForOne = None
RLForTwo = None
RLForThree = None
sum = 99999

# validarea încrucișată
for i in range(5):

    finish = start + lenght / 5

    nonNormalisedTestInputs = inputs[int(start):int(finish)]
    nonNormalisedTrainInputs = inputs[:int(start)] + inputs[int(finish):]

    testOutputs = outputs[int(start):int(finish)]
    trainOutputs = outputs[:int(start)] + outputs[int(finish):]

    mean1, stDev1, features1 = statisticalNormalisation([el[0] for el in nonNormalisedTrainInputs])
    mean2, stDev2, features2 = statisticalNormalisation([el[1] for el in nonNormalisedTrainInputs])
    trainInputs = [[feat1, feat2] for feat1, feat2 in zip(features1, features2)]

    testFeatures1 = normaliseTestData([el[0] for el in nonNormalisedTestInputs], mean1, stDev1)
    testFeatures2 = normaliseTestData([el[1] for el in nonNormalisedTestInputs], mean2, stDev2)
    testInputs = [[feat1, feat2] for feat1, feat2 in zip(testFeatures1, testFeatures2)]

    regressionOne = MyLogisticRegression()
    regressionTwo = MyLogisticRegression()
    regressionThree = MyLogisticRegression()

    trainOutputsForOne = [1 if el == "Iris-setosa" else 0 for el in trainOutputs]
    trainOutputsForTwo = [1 if el == "Iris-versicolor" else 0 for el in trainOutputs]
    trainOutputsForThree = [1 if el == "Iris-virginica" else 0 for el in trainOutputs]

    testOutputsForOne = [1 if el == "Iris-setosa" else 0 for el in testOutputs]
    testOutputsForTwo = [1 if el == "Iris-versicolor" else 0 for el in testOutputs]
    testOutputsForThree = [1 if el == "Iris-virginica" else 0 for el in testOutputs]

    regressionOne.fit(trainInputs, trainOutputsForOne)
    regressionTwo.fit(trainInputs, trainOutputsForTwo)
    regressionThree.fit(trainInputs, trainOutputsForThree)

    computedOutputsForOne = regressionOne.predict(testInputs)
    computedOutputsForTwo = regressionTwo.predict(testInputs)
    computedOutputsForThree = regressionThree.predict(testInputs)

    errorForOne = computeError(computedOutputsForOne, testOutputsForOne)
    errorForTwo = computeError(computedOutputsForTwo, testOutputsForTwo)
    errorForThree = computeError(computedOutputsForThree, testOutputsForThree)

    if sum > errorForOne + errorForTwo + errorForThree:
        sum = errorForOne + errorForTwo + errorForThree
        TrainInputs = trainInputs
        TestInputs = testInputs
        TrainOutputs = trainOutputs
        TestOutputs = testOutputs
        RLForOne = regressionOne
        RLForTwo = regressionTwo
        RLForThree = regressionThree

    start = finish

import matplotlib.pyplot as plt

def plotTrain():
    plt.xlabel("sepal length")
    plt.ylabel("petal length")
    plt.title("normalised train data")
    for i in range(len(TrainOutputs)):
        if TrainOutputs[i] == "Iris-setosa":
            plt.plot([TrainInputs[i][0]], [TrainInputs[i][1]], "ro")
        if TrainOutputs[i] == "Iris-versicolor":
            plt.plot([TrainInputs[i][0]], [TrainInputs[i][1]], "go")
        if TrainOutputs[i] == "Iris-virginica":
            plt.plot([TrainInputs[i][0]], [TrainInputs[i][1]], "bo")
    plt.show()

# daca maximu dintre (RLForOne, RLForTwo, RLForThree).predictOneSampleValue este
# cel dar de TestOutputs[i], atunci e predictie corecta.

def plotTest():
    plt.xlabel("sepal length")
    plt.ylabel("petal length")
    plt.title("normalised test data")
    for i in range(len(TestOutputs)):

        firstPred = RLForOne.predictOneSampleValue(TestInputs[i])
        secondPred = RLForTwo.predictOneSampleValue(TestInputs[i])
        thirdPred = RLForThree.predictOneSampleValue(TestInputs[i])

        if TestOutputs[i] == "Iris-setosa":
            if(firstPred > secondPred and firstPred > thirdPred):
                plt.plot([TestInputs[i][0]], [TestInputs[i][1]], "r*")
            else:
                plt.plot([TestInputs[i][0]], [TestInputs[i][1]], "ro")

        if TestOutputs[i] == "Iris-versicolor":
            if (secondPred > firstPred and secondPred > thirdPred):
                plt.plot([TestInputs[i][0]], [TestInputs[i][1]], "g*")
            else:
                plt.plot([TestInputs[i][0]], [TestInputs[i][1]], "go")

        if TestOutputs[i] == "Iris-virginica":
            if (thirdPred > secondPred and thirdPred > firstPred):
                plt.plot([TestInputs[i][0]], [TestInputs[i][1]], "b*")
            else:
                plt.plot([TestInputs[i][0]], [TestInputs[i][1]], "bo")

    plt.show()


plotTrain()
plotTest()






