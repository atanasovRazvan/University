
from AutomatFinit.Computer import *

def processAF(af):

    print() # newline
    while(True):
        print("")
        print("Alegeti operatiunea")
        print("1. Multimea starilor")
        print("2. Alfabetul")
        print("3. Tranzitii")
        print("4. Multimea starilor finale")
        print("5. Verifica daca secventa este acceptata")
        print("6. Cel mai lung prefix dintr-o secventa care este o secventa acceptata")

        cmd = input()
        if cmd in '123456':

            # ------------------------------------------------------------------------------------
            # Pentru multimea starilor, parcurgem primul element din AF in care se afla starile
            #   sub forma de caractere
            # ------------------------------------------------------------------------------------

            if cmd == "1":
                print("Stari = {", end="")
                for index in range(len(af[0])):
                    if index == len(af[0])-1:
                        print(af[0][index], end="")
                    else:
                        print(af[0][index] + ", ", end="")
                print("}")

            # ------------------------------------------------------------------------------------
            # Pentru alfabet, parcurgem al doilea element din AF in care se afla alfabetul
            #   sub forma de caractere
            # ------------------------------------------------------------------------------------

            if cmd == "2":
                print("Alfabet = {", end="")
                for index in range(len(af[1])):
                    if index == len(af[1]) - 1:
                        print(af[1][index], end="")
                    else:
                        print(af[1][index] + ", ", end="")
                print("}")

            # ------------------------------------------------------------------------------------
            # Pentru multimea tranzitiilor, parcurgem elementele de la 4 spre final, unde sunt
            #   tranzitiile sub forma de sir de caractere
            # ------------------------------------------------------------------------------------

            if cmd == "3":

                firstRow = ["delta"]
                for tr in af[1]:
                    firstRow.append(tr)
                firstRow = list(dict.fromkeys(firstRow))
                for el in firstRow:
                    print(el, end=" ")

                print()

                for state in af[0]:
                    row = [state]
                    for alf in firstRow[1:]:
                        found = False
                        for tr in af[4:]:
                            if tr[0] == state and alf in tr[1:-1]:
                                row.append(tr[-1])
                                found = True
                        if found == False:
                            row.append("-")
                    if state in af[3]:
                        row.append(1)
                    else:
                        row.append(0)
                    print("  " + row[0] + "  ", end=" ")
                    for el in row[1:]:
                        print(el, end=" ")
                    print()


                print()

            # ------------------------------------------------------------------------------------
            # Pentru multimea starilor finale, parcurgem elementele de la indexul 3, unde sunt
            #   starile finale sub forma de caractere
            # ------------------------------------------------------------------------------------

            if cmd == "4":
                print("Stari_finale = {", end="")
                for index in range(len(af[3])):
                    if index == len(af[3]) - 1:
                        print(af[3][index], end="")
                    else:
                        print(af[3][index] + ", ", end="")
                print("}")

            # ------------------------------------------------------------------------------------
            # Pentru verificarea unei secvente, introducem secventa si o trimitem functiei
            #   validateSequence din Computer.py pentru a o analiza
            # ------------------------------------------------------------------------------------

            if cmd == "5":
                print()
                sequence = input("Introduceti secventa")
                if validateSequence(sequence, af):
                    print(sequence + " este acceptata de AF :)")
                else:
                    print(sequence + " NU este acceptata de AF :(")

            # ------------------------------------------------------------------------------------
            # Pentru gasirea prefixului, introducem secventa si o trimitem functiei
            #   longestPrefix din Computer.py dupa validarea secventei introduse
            # ------------------------------------------------------------------------------------

            if cmd == "6":
                print()
                sequence = input("Introduceti secventa")
                print(longestPrefix(sequence, af))


        else:
            print("\nOperatiune invalida")

