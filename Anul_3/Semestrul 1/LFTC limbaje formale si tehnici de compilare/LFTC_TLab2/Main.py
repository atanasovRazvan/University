from ProcessAF import processAF

# ------------------------------------------------------------------------------------
#   Conținutul fișierului trebuie să fie de următoarea formă
#   De asemenea, inputul consolei trebuie sa respecte aceleasi reguli cu conditia de
#       final (introducerea valorii 0)
#
#   CONSTANTE
#   <fisier> ::= <stari><alfa><stare_initiala><stari_finale><tranzitii>
#   <stari> ::= a|p|m|z|s
#   <alfa> ::= +|-|0|1|2||3|4|5|6|7|8|9
#   <stare_initiala> ::= a
#   <stari_finale> ::= z|s
#   <tranzitii> ::= a0z|a-m|a+p|m123456789s|p123456789s|a123456789s|s0123456789s
#
#   IDENTIFICATORI
#   <fisier> ::= <stari><alfa><stare_initiala><stari_finale><tranzitii>
#   <stari> ::= s|f
#   <alfa> ::= _|a|b|c|d|e|f|g|h|i|j|k|l|m|n|o|p|q|r|s|t|u|v|w|x|y|z|A|B|C|D|E|F|G|H|I|J|K|L|M|N|O|P|Q|R|S|T|U|V|W|X|Y|Z|0|1|2|3|4|5|6|7|8|9
#   <stare_initiala> ::= s
#   <stare_finala> ::= f
#   <tranzitii> ::= s_abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUWXYZf|f_abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUWXYZ0123456789f
#
# ------------------------------------------------------------------------------------

while(True):

    # ------------------------------------------------------------------------------------
    # Meniul din care alegem tipul de input
    # ------------------------------------------------------------------------------------

    #fname = "AFinput.txt"
    fname = "AFTesting.txt"

    print("Alegeti locul din care se vor citi datele")
    print("1. Fisierul " + fname)
    print("2. Consola")
    cmd = input()

    # ------------------------------------------------------------------------------------
    # Comanda valida
    # ------------------------------------------------------------------------------------
    if cmd == "1" or cmd == "2":

        af = []

        # ------------------------------------------------------------------------------------
        # Fisier
        # Citim linie cu linie din fisier si adaugam in elementele automatului finit af
        # ------------------------------------------------------------------------------------
        if cmd == "1":
            file = open(fname, "r")
            line = file.readline()
            while line:
                af.append(line)
                line = file.readline()

        # ------------------------------------------------------------------------------------
        # Consola
        # Citim linie cu linie din consola pana cand se introduce 0 si adaugam in elementele
        #   automatului finit af
        # ------------------------------------------------------------------------------------
        if cmd == "2":
            print()
            print("Introduceti 0 pentru a indica finalizarea AF-ului")
            afElement = input()
            while afElement != "0":
                af.append(afElement)
                afElement = input()

        # ------------------------------------------------------------------------------------
        # Eliminam toate newline-urile si ramanem cu elementele stricte pe care le trimitem
        #   spre procesare
        # ------------------------------------------------------------------------------------
        af = map(lambda s: s.strip(), af)
        processAF(list(af))

    # ------------------------------------------------------------------------------------
    # Comanda invalida - se repeta procesul si se afiseaza "Invalid choice"
    # ------------------------------------------------------------------------------------
    else:
        print("\nInvalid choice\n")

