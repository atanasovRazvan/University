
def obtainGrammar():
    file = open("./data/gramaticamlp.txt")

    grammarArray = []

    line = file.readline()
    while line:
        grammarArray.append(line.strip('\n'))
        line = file.readline()

    grammar = {"NonTerminals": grammarArray[0].split(" "), "Terminals": grammarArray[1].split(" ")}
    grammar["Productions"] = []
    k = 0
    for element in grammarArray[2:]:
        grammar["Productions"].append([])
        grammar["Productions"][k].append(element.split('->')[0])
        for x in element.split('->')[1].split(' '):
            grammar["Productions"][k].append(x)
        k = k + 1

    return grammar
