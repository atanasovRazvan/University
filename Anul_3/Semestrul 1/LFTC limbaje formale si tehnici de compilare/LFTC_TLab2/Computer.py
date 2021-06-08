
def validateSequence(sequence, af):
    # ------------------------------------------------------------------------------------
    # Functie care determina daca o secventa respecta regulile automatului finit
    # Parametri: sequence: sir de caractere, af: lista de siruri de caractere
    # Return: True daca sequence respecta regulile
    #         False, in caz contrar
    # ------------------------------------------------------------------------------------

    # ------------------------------------------------------------------------------------
    # Creem un dictionar care serveste ca matrice de adiacenta si il initializam cu NA
    # ------------------------------------------------------------------------------------

    adjacency = {}
    for state in af[0]:
        adjacency[state] = {}
        for innerState in af[0]:
            adjacency[state][innerState] = "NA"

    # ------------------------------------------------------------------------------------
    # Populam dictionarul cu valorile definite de tranzitii
    # ------------------------------------------------------------------------------------

    for transition in af[4:]:
        adjacency[transition[0]][transition[-1]] = transition[1:-1]

    currentState = af[2] # Starea initiala
    endStates = af[3] # Starile finale

    for ch in sequence:

        # Parcurgem fiecare caracter si presupunem ca nu este intre starea curenta si

        found = False
        for nextState in adjacency[currentState]:

            #   Daca gasim caracterul in matricea noastra cu indecsi [stare curenta] si [stare urmatoare],
            #   modificam starea curenta cu starea urmatoare si modificam presupunerea

            if ch in adjacency[currentState][nextState]:
                currentState = nextState
                found = True
                break

        # Daca caracterul nu se gaseste in tranzitiile pozitiei curente, returnam fals

        if not found:
            return False

    # Daca la final nu suntem intr-o stare finala, returnam fals

    if currentState not in endStates:
        return False

    # Returnam true la final

    return True

def readAndValidate(fname, sequence):
    af = []
    file = open(fname, "r")
    line = file.readline()
    while line:
        af.append(line)
        line = file.readline()
    af = map(lambda s: s.strip(), af)
    return validateSequence(sequence, list(af))

def longestPrefix(sequence, af):

    lastIndex = len(sequence)-1
    while lastIndex > 0:

        if validateSequence(sequence[:lastIndex], af):
            return "Cel mai lung prefix al secventei acceptat: " + sequence[:lastIndex]

        lastIndex = lastIndex - 1

    return "Nu exista niciun prefix care sa respecte automatul finit :((((("

