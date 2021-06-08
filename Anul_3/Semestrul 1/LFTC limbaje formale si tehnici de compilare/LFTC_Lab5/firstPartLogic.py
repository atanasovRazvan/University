
def firstPart(grammar):

    while(True):

        print("1. Multimea neterminalelor")
        print("2. Multimea terminalelor")
        print("3. Multimea regulilor de productie")
        print("4. Multimea regulilor de productie ce au un anumit neterminal in membrul stang")
        print("5. EXIT")

        cmd = input("\nAlegeti varianta dorita: ")

        if cmd == "1":
            print("Nonterminale:", end=" ")
            for x in grammar["NonTerminals"]:
                print(x, end=" ")
            print()

        if cmd == "2":
            print("Terminale:", end=" ")
            for x in grammar["Terminals"]:
                print(x, end=" ")
            print()

        if cmd == "3":
            print("Reguli de productie:")
            for x in grammar["Productions"]:
                print(x[0] + ' ->', end=" ")
                for el in x[1:]:
                    print(el, end=" ")
                print()

        if cmd == "4":
            neTerminal = input("\nIntruduceti neterminalul: ")
            print("Reguli de productie:")
            for x in grammar["Productions"]:
                if x[0] == neTerminal:
                    print(x[0] + ' ->', end=" ")
                    for el in x[1:]:
                        print(el, end=" ")
                    print()

        if cmd == "5":
            break