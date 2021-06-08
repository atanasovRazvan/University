Todo

Pentru gestiunea task-urilor curente o firma s-a gandit la o aplicatie mobila
si la un server care ofera servicii REST despre resursele:
  - task - reprezinta informatii despre un task
    - id - numar intreg
    - tag - sir de caractere
    - text - sir de caractere
    - version - numar intreg reprezentand de cate ori a fost actualizat task-ul

Aplicatia mobila ofera urmatoarele:

1. Primul ecran prezinta informatii despre task-uri, cu operatii disponibile atat in modul
online cat si in modul offline. La inceput, acest ecran prezinta lista tuturor tag-urilor 
pe care le au task-urile (ex. tag0, tag1, tag2). In dreptul fiecarui tag (ex tag0), aplicatia
prezinta numarul task-urilor necitite de utilizator (ex. tag0 [10]).
Task-urile sunt aduse de pe server via GET /task, care returneaza o lista [{ id, tag, text, version }].
  
2. Utilizatorul poate face click pe un tag pentru a vedea task-urile marcate cu acel tag.
Lista de tag-uri din primul ecran va fi inlocuita cu lista task-urilor care au acel tag,
permitand utilizatorului sa revina la lista tag-urilor printr-un buton 'return'.
In urma acestei operatii aplicatia marcheaza local ca toate acele task-uri prezentate au fost citite.

3. Utilizatorul poate face click pe un task din lista pentru a deschide al doilea ecran
ce permite editarea task-ului (doar textul este afisat, editabil). Utilizatorul poate declansa
un buton save pentru salvarea modificarilor. Task-ul este trimis pe server prin http PUT /task,
incapsuland in corpul cererii http reprezentarea JSON a acelui task.
Trimiterea pe server se face in background, aplicatia revenind imediat la primul ecran.

4. In timpul efectuarii operatiei PUT pentru un task, lista de task-uri din primul ecran
prezinta textul 'Sending...' in dreptul acelui task.

5. Operatia PUT poate esua (409 Version conflict) daca altcineva a modificat intre timp
acel task. Aplicatia va afisa in lista de task-uri in dreptul task-ului respectiv 'Version conflict'.
De asemenea, operatia poate esua daca serverul nu este accesibil. Aplicatia va afisa in
lista de task-uri in dreptul task-ului respectiv 'Not sent'.

6. Daca exista task-uri modificate local si netrimise la server, utilizatorul poate relua
trimiterea task-ului facand click pe 'Not sent'.

7. Daca exista task-uri modificate local pentru care trimiterea a esuat din cauza unui conflict,
utilizatorul poate edita din nou task-ul facand click pe 'Version conflict'. In acest caz al doilea ecran
prezinta utilizatorului si textul modificat pe server de catre alt utilizator. Task-ul de pe server
pot fi adus via GET /task/id.

8. Serverul emite notificari prin ws pe localhost:3000. O notificare contine un task modificat
{ id, text, version, tag }. Aplicatia client foloseste aceste notificari pentru a actaliza datele
aduse initial. Task-urile modificate sunt considerate necitite.

9. In timpul descarcarii listei de taskuri de pe server (1) sau a descarcarii unui task (7),
aplicatia va afisa un progress indicator. Daca aceste operatii esueaza cu o eroare, utilizatorul va
fi notificat si i se va permite sa reia (retry) operatia care a esuat. 
