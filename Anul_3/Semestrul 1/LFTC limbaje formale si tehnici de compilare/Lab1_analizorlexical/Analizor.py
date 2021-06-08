import re

# ---------------------------------------------------------------------------------------------------
# DEFINIM TABELA DE SIMBOLURI
# ---------------------------------------------------------------------------------------------------

tabelaSimboluri = {"ID": 0, "CONST": 1, "int": 2, "double": 3, "cin>>": 4, "cout<<": 5,
    "if": 6, "else": 7, "for": 8, "main": 9, "and": 10, "=": 11, "==": 12, "<": 13, ">": 14,
    "<=": 15, ">=": 16, "*": 17, "/": 18, "%": 19, "+": 20, "-": 21, "(": 22, ")": 23,
    "{": 24, "}": 25, ";": 26}

# ---------------------------------------------------------------------------------------------------
# In reserved pastram toate keys care nu sunt ID sau CONST
# ---------------------------------------------------------------------------------------------------

reserved = list(dict.fromkeys(tabelaSimboluri))[2:]

# ---------------------------------------------------------------------------------------------------
# Se alege numele fisierului pana se introduce x/X pentru oprirea executiei
# ---------------------------------------------------------------------------------------------------

while(True):

    # ---------------------------------------------------------------------------------------------------
    # Se citeste numele fisierului
    # ---------------------------------------------------------------------------------------------------

    inputFile = input("Choose file (X to exit): ")
    if(inputFile in {'x', 'X'}):
        break

    # ---------------------------------------------------------------------------------------------------
    # Se deschide fisierul introdus
    # ---------------------------------------------------------------------------------------------------

    inputFile = open(inputFile, "r")

    # ---------------------------------------------------------------------------------------------------
    # Citim prima linie
    # ---------------------------------------------------------------------------------------------------

    line = inputFile.readline()
    formattedFile = []

    # ---------------------------------------------------------------------------------------------------
    # Definim separatorii
    # ---------------------------------------------------------------------------------------------------

    separators = [' ', ';', '{', '}', ',', ')', '(', '*', '+', '%']

    # ---------------------------------------------------------------------------------------------------
    # Ne folosim de fisierul intermediateProcessing pentru a usura procesul
    # In fisierul respectiv adaugam acelasi program, doar cu newline inainte si dupa separatori
    # ---------------------------------------------------------------------------------------------------

    while(line):

        formattedFile.append(['\n'+i+'\n' if i in separators else i for i in line])

        line=inputFile.readline()

    formattedFile = [j for i in formattedFile for j in i]

    intermediateFile = open("intermediateProcessing", "w")
    for i in formattedFile:
        intermediateFile.write(i)
    intermediateFile.close()

    # ---------------------------------------------------------------------------------------------------
    # Se citesc pe rand liniile din intermediateProcessing si se separa atomii
    # ---------------------------------------------------------------------------------------------------

    atoms = []

    intermediateFile = open("intermediateProcessing", "r")
    line = intermediateFile.readline()

    while(line):

        atoms.append(re.split('\n', line))
        line = intermediateFile.readline()

    # ---------------------------------------------------------------------------------------------------
    # Se unesc listele imbricate intr-una singura
    # ---------------------------------------------------------------------------------------------------

    atoms = [j for i in atoms for j in i]

    # ---------------------------------------------------------------------------------------------------
    # Se separa atomii de separatorii ramasi: cin>>, cout<<, ==, >=, <=,  <, >, =
    # ---------------------------------------------------------------------------------------------------

    for i in range(len(atoms)):

        if "cin>>" in atoms[i]:
            atoms[i] = atoms[i].split('cin>>')
            atoms[i].append(atoms[i][1])
            atoms[i][1] = 'cin>>'
        else:
            if "cout<<" in atoms[i]:
                atoms[i] = atoms[i].split('cout<<')
                atoms[i].append(atoms[i][1])
                atoms[i][1] = 'cout<<'
            else:
                if "==" in atoms[i]:
                    atoms[i] = atoms[i].split('==')
                    atoms[i].append(atoms[i][1])
                    atoms[i][1] = '=='
                else:
                    if "<=" in atoms[i]:
                        atoms[i] = atoms[i].split('<=')
                        atoms[i].append(atoms[i][1])
                        atoms[i][1] = '<='
                    else:
                        if ">=" in atoms[i]:
                            atoms[i] = atoms[i].split('>=')
                            atoms[i].append(atoms[i][1])
                            atoms[i][1] = '>='
                        else:
                            if "=" in atoms[i]:
                                atoms[i] = atoms[i].split('=')
                                atoms[i].append(atoms[i][1])
                                atoms[i][1] = '='
                            else:
                                if ">" in atoms[i]:
                                    atoms[i] = atoms[i].split('>')
                                    atoms[i].append(atoms[i][1])
                                    atoms[i][1] = '>'
                                else:
                                    if "<" in atoms[i]:
                                        atoms[i] = atoms[i].split('<')
                                        atoms[i].append(atoms[i][1])
                                        atoms[i][1] = '<'

    inputFile.close()

    # ---------------------------------------------------------------------------------------------------
    # Se unesc listele imbricate intr-una singura
    # ---------------------------------------------------------------------------------------------------

    newAtoms = []
    for el in atoms:
        if isinstance(el, list):
            for i in el:
                newAtoms.append(i)
        else: newAtoms.append(el)

    atoms = newAtoms

    # ---------------------------------------------------------------------------------------------------
    # Se sterg spatiile, newline-urile si caracterele nule
    # ---------------------------------------------------------------------------------------------------

    atoms = [i for i in atoms if i != '' and i != ' ' and i != '\n']

    # ---------------------------------------------------------------------------------------------------
    # Retinem forma programului scrisa sub forma de atomi
    # ---------------------------------------------------------------------------------------------------

    fipAtoms = atoms

    # ---------------------------------------------------------------------------------------------------
    # Se sterg duplicatele
    # ---------------------------------------------------------------------------------------------------

    atoms = list(dict.fromkeys(atoms))

    # ---------------------------------------------------------------------------------------------------
    # Se afiseaza elementele listei in fisierul atoms
    # ---------------------------------------------------------------------------------------------------

    outputFile = open("atoms", "w")

    outputFile.write("Number of atoms: " + str(len(atoms)) + '\n\n')
    for atom in atoms:
        if atom == '\n':
            outputFile.write("\\n" + "\n")
        else:
            outputFile.write("'" + atom + "'" + "\n")
    outputFile.close()

    # ---------------------------------------------------------------------------------------------------
    # Calculam si afisam FIP-ul
    # ---------------------------------------------------------------------------------------------------

    fip = []

    for i in range(len(fipAtoms)):

        if fipAtoms[i] in reserved:
            fip.append(tabelaSimboluri.get(fipAtoms[i]))
        else:
            if fipAtoms[i-1] == "int":
                fip.append(tabelaSimboluri.get("ID"))
            else:
                if fipAtoms[i][0].isdigit() or fipAtoms[i][0] == '"':
                    fip.append(tabelaSimboluri.get("CONST"))
                else:
                    fip.append(tabelaSimboluri.get("ID"))

    for el in fip: print(el, end=" ")

    # ---------------------------------------------------------------------------------------------------
    # Calculam si afisam tabela generala de simboluri
    # ---------------------------------------------------------------------------------------------------

    print('\n')
    tskeys = list(tabelaSimboluri.keys())
    tsvalues = list(tabelaSimboluri.values())
    pairs = []
    for nr in range(27):
        if nr in fip:
            pairs.append((tskeys[nr], tsvalues[nr]))

    for pair in pairs:
        print("{: >20} {: >20}".format(*pair))

    # ---------------------------------------------------------------------------------------------------
    # Cautam erorile lexicale
    # ---------------------------------------------------------------------------------------------------

    errors = {}

    for i in range(len(fip)):

        from AutomatFinit.Computer import readAndValidate

        if fip[i] == 0:

            # ---------------------------------------------------------------------------------------------------
            # Verificam lungimea identificatorului <= 8
            # ---------------------------------------------------------------------------------------------------
            if len(fipAtoms[i]) > 8:
                errors["at " + str(i)] = 'Lenght of ID "'+fipAtoms[i]+'" too big'

            # ---------------------------------------------------------------------------------------------------
            # Verificam ca identificatorul sa fie adecvat automatului finit
            # ---------------------------------------------------------------------------------------------------

            if not readAndValidate("AutomatFinit/AFTesting.txt", fipAtoms[i]):
                errors["at " + str(i)] = 'IDENTIFIER "' + fipAtoms[i] + '" is not available acording to MLP'

        if fip[i] == 1:
            # ---------------------------------------------------------------------------------------------------
            # Verificam ca constanta sa contina doar cifre si .
            # ---------------------------------------------------------------------------------------------------
            if not fipAtoms[i][0] == '"' and not readAndValidate("AutomatFinit/AFinput.txt", fipAtoms[i]):
                    errors["at " + str(i)] = 'CONST "' + fipAtoms[i] + '" is not available acording to MLP'

    # ---------------------------------------------------------------------------------------------------
    # Afisam erorile lexicale
    # ---------------------------------------------------------------------------------------------------

    for error_key in errors:
        print("Error found " + error_key + ": " + errors[error_key])

    if len(errors) == 0:
        # ---------------------------------------------------------------------------------------------------
        # Calculam si afisam tabela de simboluri lexicografic daca nu exista erori
        # ---------------------------------------------------------------------------------------------------

        identifiers = []
        constants = []

        for i in range(len(fip)):
            if fip[i] == 0:
                # ---------------------------------------------------------------------------------------------------
                # Identificator
                # ---------------------------------------------------------------------------------------------------
                identifiers.append(fipAtoms[i])
            if fip[i] == 1:
                # ---------------------------------------------------------------------------------------------------
                # Constant
                # ---------------------------------------------------------------------------------------------------
                constants.append(fipAtoms[i])

        # ---------------------------------------------------------------------------------------------------
        # Stergem duplicatele
        # ---------------------------------------------------------------------------------------------------

        identifiers = list(dict.fromkeys(identifiers))
        constants = list(dict.fromkeys(constants))

        # ---------------------------------------------------------------------------------------------------
        # Sortam lexicografic atomii
        # ---------------------------------------------------------------------------------------------------
        identifiers.sort()
        constants.sort()

        # ---------------------------------------------------------------------------------------------------
        # Creem un dictionar corespunzator tabelei de simboluri
        # ---------------------------------------------------------------------------------------------------
        tsDict = {}
        count = 0

        for id in identifiers:
            tsDict[id] = count
            count = count+1

        count = 0
        for const in constants:
            tsDict[const] = count
            count = count+1

        # ---------------------------------------------------------------------------------------------------
        # Afisam tabela de simboluri in ordinea in care apare in FIP
        # ---------------------------------------------------------------------------------------------------

        print("\n------------------------------------- TS -----------------------------------------")
        for atom in fipAtoms:
            if atom in tsDict:
                print("{: >20} {: >20}".format(atom, tsDict[atom]))
