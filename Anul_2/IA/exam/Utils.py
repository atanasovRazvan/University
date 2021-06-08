import csv
from statistics import mean, stdev


def readData(fileName, inputCols, outputCols):
    data = []
    dataNames = []
    with open(fileName) as csv_file:
        csv_reader = csv.reader(csv_file, delimiter=',')
        line_count = 0
        for row in csv_reader:
            if line_count == 0:
                dataNames = row
            else:
                data.append(row)
            line_count += 1
            if line_count > 1055:
                break

    inputs = []

    for i in range(len(data)):
        line = []
        for column in inputCols:
            columnIndex = dataNames.index(column)
            if(data[i][columnIndex] == "NaN"): data[i][columnIndex] = 0.0
            line.append(float(data[i][columnIndex]))
        inputs.append(line)

    columnIndex = dataNames.index(outputCols)
    outputs = [float(data[i][columnIndex]) for i in range(len(data))]
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