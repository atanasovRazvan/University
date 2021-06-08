
import time

def nodigits(config, grammar):

    index = -1
    nonTerminal = config["stiva"][-1][:index]
    while nonTerminal not in grammar["NonTerminals"]:
        index = index - 1
        nonTerminal = config["stiva"][-1][:index]
    return index

def secondPart(grammar):

    # Verificare recursivitate la stanga
    for x in grammar["Productions"]:
        if x[0] == x[1]:
            print("Gramatica este recursiva la stanga!\n")
            return

    secventa = input("Introduceti secventa care doriti sa fie analizata: ").split(' ')

    config = {"stare": 'q', "pozitie": 1, "stiva": [], "banda": [grammar["NonTerminals"][0]]}

    print("\nConfiguratie: ", config)
    print()

    while config["stare"] != 'e' and config["stare"] != 't':

        # STARE: q
        if config["stare"] == 'q':

            # POZITIA este n+1 si banda e goala => SUCCES
            if config["banda"] == [] and config["pozitie"] == len(secventa) + 1:
                config["stare"] = 't'
                print("SUCCES")

            # BANDA incepe cu nonterminal => EXPANDARE
            elif config["banda"] != [] and config["banda"][0] in grammar["NonTerminals"]:
                config["stiva"].append(config["banda"][0] + '1')
                productions = [prod for prod in grammar["Productions"] if prod[0] == config["banda"][0]]
                config["banda"] = productions[0][1:] + config["banda"][1:]
                print("EXPANDARE")

            # BANDA incepe cu terminal care e acelasi cu pozitia i din secventa => AVANS
            elif config["pozitie"] <= len(secventa) and config["banda"] != [] and config["banda"][0] == secventa[config["pozitie"] - 1]:
                config["pozitie"] = config["pozitie"] + 1
                config["stiva"].append(config["banda"][0])
                config["banda"] = config["banda"][1:]
                print("AVANS")

            # BANDA incepe cu terminal care nu e pe aceeasi pozitie => INSUCCES DE MOMENT
            else:
                config["stare"] = 'r'
                print("INSUCCES DE MOMENT")

        # STARE: r
        elif config["stare"] == 'r':

            # STIVA are terminal pe ultima pozitie => REVENIRE
            if config["stiva"][-1] in grammar["Terminals"]:
                config["pozitie"] = config["pozitie"] - 1
                config["banda"] = [config["stiva"][-1]] + config["banda"]
                config["stiva"] = config["stiva"][:-1]
                print("REVENIRE")

            # Altfel => ALTA INCERCARE
            else:
                productions = [prod for prod in grammar["Productions"] if prod[0] == config["stiva"][-1][:nodigits(config, grammar)]]
                j = int(config["stiva"][-1][nodigits(config, grammar):])
                starter = productions[j-1][1:]

                # Daca inceputul benzii este productia finalului stivei, mergem pe prima ramura
                if not [config["banda"][i] == starter[i] for i in range(len(starter))].__contains__(False):

                    # Daca exista un j+1 pt care productia ultimului nonterminal din stiva se regaseste la inceputul benzii, mergem pe primul caz
                    if j < len(productions):
                        print("primul caz")
                        config["banda"] = productions[j][1:] + config["banda"][len(starter):]
                        config["stiva"][-1] = productions[j][0] + str(j + 1)
                        config["stare"] = 'q'

                    # Daca pozitia este 1 si banda este productia stivei mergem pe a doua ramura => EROARE
                    elif config["pozitie"] == 1 and j == len(productions):
                        print("al doilea caz")
                        config["stare"] = 'e'
                        config["banda"] = config["stiva"][-1][0]
                        config["stiva"] = config["stiva"][:-1]
                        print("EROARE")

                    else:
                        print("ultimul caz")
                        config["stare"] = 'r'
                        config["banda"][:(len(productions[j-1]) - 1)] = [config["stiva"][-1][:nodigits(config, grammar)]]
                        config["stiva"] = config["stiva"][:-1]

                    print("ALTA INCERCARE")

        print("Configuratie: ", config)
        print()


    if config["stare"] == 'e':
        print("EROARE: Secventa nu este corespunzatoare gramaticii")
    else:
        print("SUCCES: Secventa este corespunzatoare gramaticii")
    print()