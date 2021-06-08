USE LAB_1_store

-- Afiseaza clientul cu ID-ul 1
SELECT * FROM Client WHERE Cid=1
-- Afiseaza angajatii care au numele incepand cu litera B
SELECT * FROM Angajati WHERE Nume>='B'
-- Afiseaza ID-urile sediilor unde exista posturi libere de Software Engineer
SELECT Sid FROM PosturiLibere WHERE Denumire_post='Soft_ing'
-- Afiseaza orasele in care exista posturi libere de Software Engineer
SELECT Oras FROM Sedii WHERE Sid = (SELECT Sid FROM PosturiLibere WHERE Denumire_post = 'Soft_ing')
-- Afiseaza denumirea produselor care au o reducere de 50%
SELECT Denumire FROM Produse WHERE Pid = (SELECT Pid FROM Promotii WHERE Ofid = (SELECT Ofid FROM Oferte WHERE Nume_oferta = '50% reducere'))

-- Afiseaza ID-ul orarului si salariile insumate in functie de orele de lucru
SELECT Oid, SUM(Salariu) FROM Angajati GROUP BY Oid
-- Afiseaza ID-ul orarului si salariile insumate in functie de orele de lucru sub 6 ore.
SELECT Oid, SUM(Salariu) FROM Angajati WHERE Oid = (SELECT Oid FROM Orar WHERE Nr_ore < 6) GROUP BY Oid
-- Afiseaza data inventarului si numarul de produse pana in data de 2020.1.1
SELECT Data_Inventar, SUM(Nr_Produse) FROM Inventar WHERE Data_Inventar < '2020.1.1' GROUP BY Data_Inventar

-- Afiseaza salariile din Auchan
SELECT DISTINCT Salariu FROM Angajati
-- Afiseaza numarul de ore pe care sunt angajati repartizati
SELECT Nr_Ore FROM Orar WHERE Oid IN (SELECT DISTINCT Oid FROM Angajati)

-- Afiseaza ID-ul orarului si salariile insumate pentru mai multi angajati pe acelasi orar.
SELECT Oid, SUM(Salariu) FROM Angajati GROUP BY Oid HAVING COUNT(Oid)>1
-- Afiseaza data inventarului si numarul de produse pana in data de 2020.1.1 daca sunt mai mult de 1000.
SELECT Data_Inventar, SUM(Nr_Produse) FROM Inventar WHERE Data_Inventar < '2020.1.1' GROUP BY Data_Inventar HAVING SUM(Nr_Produse)>1000

--Afiseaza angajatii si orele lor de munca
SELECT Angajati.Nume, Angajati.Prenume, Orar.Nr_ore FROM Orar INNER JOIN Angajati ON Orar.Oid = Angajati.Oid
--Afiseaza angajatii si orele de munca, chiar daca nu exista angajati cu ture de 10 ore.
SELECT Angajati.Nume, Angajati.Prenume, Orar.Nr_ore FROM Orar LEFT OUTER JOIN Angajati ON Orar.Oid = Angajati.Oid
--Afiseaza angajatii si orele de munca, chiar daca nu exista angajati fara ture asignate.
SELECT Angajati.Nume, Angajati.Prenume, Orar.Nr_ore FROM Orar RIGHT OUTER JOIN Angajati ON Orar.Oid = Angajati.Oid
--Afiseaza data prezentarii si adresa clientilor.
SELECT Client.Data_prezentarii, Adresa.Strada, Adresa.Numar FROM Client INNER JOIN Adresa ON Client.Adresa = Adresa.Adresa
--Afiseaza numele angajatului care a distribuit anumite produse la inventarul din data afisata.
SELECT Angajati.Nume, Angajati.Prenume, Inventar.Data_inventar, Produse.Denumire FROM Inventar INNER JOIN Angajati ON Inventar.Aid = Angajati.Aid INNER JOIN Produse ON Produse.Iid = Inventar.Iid
--Afiseaza toate produsele cu reducerile aferente lor.
SELECT Produse.Denumire, Oferte.Nume_oferta FROM Oferte INNER JOIN Promotii ON Oferte.Ofid = Promotii.Ofid INNER JOIN Produse ON Promotii.Pid = Produse.Pid
--Afiseaza numele angajatilor care au avut inventare si au distribuit anumite produse chiar daca unii nu au avut produse de distribuit.
SELECT Angajati.Nume, Angajati.Prenume, Inventar.Data_inventar, Produse.Denumire FROM Inventar INNER JOIN Angajati ON Inventar.Aid = Angajati.Aid LEFT OUTER JOIN Produse ON Produse.Iid = Inventar.Iid

--Afiseaza ofertele la tigari.
SELECT Nume_oferta FROM Oferte WHERE Ofid IN (SELECT Ofid FROM Promotii WHERE Pid IN (SELECT Pid FROM Produse WHERE Denumire = 'Tigari'))
--Afiseaza produsele la care exista o reducere de 50%.
SELECT Denumire FROM Produse WHERE Pid = (SELECT Pid FROM Promotii WHERE Ofid = (SELECT Ofid FROM Oferte WHERE Nume_oferta = '50% reducere'))
