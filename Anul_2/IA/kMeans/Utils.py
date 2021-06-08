import csv
import re

def readData():

    data = []
    with open("data.csv") as csv_file:
        csv_reader = csv.reader(csv_file, delimiter=',')
        line_count = 0
        for row in csv_reader:
            if line_count == 0:
                dataNames = row
            else:
                data.append(row)
            line_count += 1

    inputs = [data[i][0] for i in range(len(data))]
    outputs = [data[i][1] for i in range(len(data))]
    labelNames = list(set(outputs))
    return inputs, outputs, labelNames

# BAG OF WORDS

def word_extraction(sentences):
    allWords = []
    for sentence in sentences:
        words = re.split('[ \n?,.()-:!&]', sentence)
        cleaned_text = [w.lower() for w in words if w != '']
        for el in cleaned_text:
            allWords.append(el)
    return allWords

def get_vocabulary(sentences):
    allWords = word_extraction(sentences)
    dictionary = {}
    for word in allWords:
        if word not in dictionary.keys():
            dictionary[word] = 1
        else:
            dictionary[word] += 1
    return list(dictionary)

def vectorize_sentences(sentences, vocabulary):
    vocab = vocabulary
    vectorizedSentences = []
    for sentence in sentences:
        vectorizedSentence = [0] * len(vocab)
        for word in word_extraction([sentence]):
            for i,w in enumerate(vocab):
                if word == w:
                    vectorizedSentence[i]+=1
        vectorizedSentences.append(vectorizedSentence)
    return vectorizedSentences







