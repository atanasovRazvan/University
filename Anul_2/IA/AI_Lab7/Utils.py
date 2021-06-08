import csv
import matplotlib.pyplot as plt
import numpy as np


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

    inputs = []

    for i in range(len(data)):
        line = []
        for column in inputCols:
            columnIndex = dataNames.index(column)
            line.append(float(data[i][columnIndex]))
        inputs.append(line)

    columnIndex = dataNames.index(outputCols)
    outputs = [float(data[i][columnIndex]) for i in range(len(data))]
    return inputs, outputs

def plotTrain(regressor, trainInput, trainOutput):
    fig = plt.figure()
    ax = fig.add_subplot(111, projection='3d')
    ax.scatter([trainInput[i][1] for i in range(len(trainInput))], [trainInput[i][2] for i in range(len(trainInput))],
               trainOutput, marker='.', color='red')
    plotArea(regressor, trainInput, trainOutput, ax)

def plotTest(regressor, trainInput, trainOutput, testInput, testOutput):
    fig = plt.figure()
    ax = fig.add_subplot(111, projection='3d')
    ax.scatter([testInput[i][0] for i in range(len(testInput))], [testInput[i][1] for i in range(len(testInput))],
               testOutput, marker='^', color='green')
    plotArea(regressor, trainInput, trainOutput, ax)

def plotArea(regressor, trainInput, trainOutput, ax):

    ax.set_xlabel("GDP")
    ax.set_ylabel("Freedom")
    ax.set_zlabel("Happiness")

    x = np.arange(min([trainInput[i][1] for i in range(len(trainInput))]),
                  max([trainInput[i][1] for i in range(len(trainInput))]), 0.01)

    y = np.arange(min([trainInput[i][2] for i in range(len(trainInput))]),
                  max([trainInput[i][2] for i in range(len(trainInput))]), 0.1)
    x, y = np.meshgrid(x, y)

    z = [regressor.predict([d1, d2]) for d1, d2 in zip(x,y)]
    z = np.array(z)

    ax.plot_surface(x, y, z.reshape(x.shape), alpha=0.7)
    plt.show()