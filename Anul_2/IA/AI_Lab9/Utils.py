from statistics import mean, stdev

def readData():

    inputs = []
    outputs = []

    # Inputs: column 1 and 3

    f=open("Data/iris.data", "r")
    for line in f:

        x1, aux1, x2, aux2, output = line.split(',')
        inputs.append([float(x1), float(x2)])
        outputs.append(output[0:-1])

    return inputs, outputs


# statistical normalisation (centered around meand and standardisation)
def statisticalNormalisation(features):
    # meanValue = sum(features) / len(features)
    meanValue = mean(features)
    # stdDevValue = (1 / len(features) * sum([ (feat - meanValue) ** 2 for feat in features])) ** 0.5
    stdDevValue = stdev(features)
    normalisedFeatures = [(feat - meanValue) / stdDevValue for feat in features]
    return meanValue, stdDevValue, normalisedFeatures

def normaliseTestData(features, meanValue, stdDevValue):
    return [(feat-meanValue) / stdDevValue for feat in features]

def computeError(computedOutputs, testOutputs):
    error = 0
    for t1, t2 in zip(computedOutputs, testOutputs):
        error += (t1 - t2) ** 2
    return error / len(testOutputs)
