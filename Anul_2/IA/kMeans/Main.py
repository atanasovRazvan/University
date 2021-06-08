from Utils import readData, vectorize_sentences, get_vocabulary

while(True):

    inputs, outputs, labelNames = readData()

    import numpy as np
    from random import randint

    np.random.seed(5)
    noSamples = len(inputs)
    indexes = [i for i in range(noSamples)]
    trainSample = np.random.choice(indexes, int(0.8 * noSamples), replace = False)
    testSample = [i for i in indexes  if not i in trainSample]

    trainInputs = [inputs[i] for i in trainSample]
    trainOutputs = [outputs[i] for i in trainSample]
    testInputs = [inputs[i] for i in testSample]
    testOutputs = [outputs[i] for i in testSample]

    vocab = get_vocabulary(trainInputs)
    trainFeatures = vectorize_sentences(trainInputs, vocab)
    testFeatures = vectorize_sentences(testInputs, vocab)

    # k-Means
    # k = 2

    positiveIndex = randint(0, len(trainOutputs)-1)
    while trainOutputs[positiveIndex] == 'negative':
        positiveIndex = randint(0, len(trainOutputs)-1)
    centroid1 = trainFeatures[positiveIndex]

    negativeIndex = randint(0, len(trainOutputs)-1)
    while trainOutputs[negativeIndex] != 'negative':
        negativeIndex = randint(0, len(trainOutputs)-1)
    centroid2 = trainFeatures[negativeIndex]

    noMoved = 50
    iterations = 0

    while iterations < 25:

        noMoved = 0
        bin1 = [0] * len(centroid1)
        bin2 = [0] * len(centroid2)
        candidatesInBin1 = 0
        candidatesInBin2 = 0

        for sentence,type in zip(trainFeatures, trainOutputs):

            distFromCentroid1 = 0
            distFromCentroid2 = 0

            for i in range(len(sentence)):
                distFromCentroid1 += (sentence[i] - centroid1[i]) * (sentence[i] - centroid1[i])
                distFromCentroid2 += (sentence[i] - centroid2[i]) * (sentence[i] - centroid2[i])

            if (distFromCentroid1 < distFromCentroid2 and type == 'negative') or \
                    (distFromCentroid2 <= distFromCentroid1 and type == 'positive'):
                noMoved += 1

            if distFromCentroid1 < distFromCentroid2:
                for i in range(len(bin1)):
                    bin1[i] += sentence[i]
                candidatesInBin1 += 1
            else:
                for i in range(len(bin2)):
                    bin2[i] += sentence[i]
                candidatesInBin2 += 1

        for i in range(len(centroid1)):
            centroid1[i] = bin1[i] / candidatesInBin1

        for i in range(len(centroid2)):
            centroid2[i] = bin2[i] / candidatesInBin2

        iterations += 1


    # centroid1 positive
    # centroid2 negative

    print("--------------------Accuracy--------------------")
    correctPredictions = 0

    for sentence,type in zip(testFeatures,testOutputs):

        distFromCentroid1 = 0
        distFromCentroid2 = 0
        for i in range(len(sentence)-1):
            distFromCentroid1 += (sentence[i] - centroid1[i]) * (sentence[i] - centroid1[i])
            distFromCentroid2 += (sentence[i] - centroid2[i]) * (sentence[i] - centroid2[i])

        if (distFromCentroid1 < distFromCentroid2 and type == 'positive') or \
                (distFromCentroid2 <= distFromCentroid1 and type == 'negative'):
            correctPredictions += 1

    print(correctPredictions / len(testOutputs))



