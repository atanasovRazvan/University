use Monezi

----------1----------

CREATE TABLE Monede(
	Mid INT PRIMARY KEY IDENTITY,
	Valoare INT,
	Denumire VARCHAR(50)
);

CREATE TABLE Persoane(
	Pid INT PRIMARY KEY IDENTITY,
	Nume VARCHAR (50),
	Gen VARCHAR(50),
	Data_nasterii DATETIME
);

CREATE TABLE Copii(
	Cid INT PRIMARY KEY IDENTITY,
	Nume VARCHAR(50),
	Varsta INT
);

CREATE TABLE Tipuri_de_Monede(
	Tid INT PRIMARY KEY IDENTITY,
	Tara VARCHAR(50)
);

ALTER TABLE Copii ADD Tid INT
ALTER TABLE Monede ADD Tid INT
ALTER TABLE Copii ADD FOREIGN KEY (Tid) REFERENCES Tipuri_de_Monede(Tid);
ALTER TABLE Monede ADD FOREIGN KEY (Tid) REFERENCES Tipuri_de_Monede(Tid);

CREATE TABLE Moneda_Persoana(
	Mid INT,
	Pid INT,
	Numar INT,
	Utilitate VARCHAR(50),
	CONSTRAINT PK_Moneda_Persoana PRIMARY KEY(Mid, Pid),
	FOREIGN KEY (Mid) REFERENCES Monede(Mid),
	FOREIGN KEY (Pid) REFERENCES Persoane(Pid)
)

-------------2--------------

CREATE OR ALTER PROCEDURE adauga_moneda @persoana_id INT, @moneda_id INT, @nr_monede INT, @utilitate VARCHAR(50) AS
BEGIN
	
	IF ((SELECT COUNT(*) FROM Moneda_Persoana WHERE Mid = @moneda_id and Pid = @persoana_id) > 0)
	BEGIN
		DELETE FROM Moneda_Persoana WHERE Moneda_Persoana.Mid = @moneda_id and Moneda_Persoana.Pid = @persoana_id
	END
	INSERT INTO Moneda_Persoana(Mid, Pid, Numar, Utilitate)
	VALUES(@moneda_id, @persoana_id, @nr_monede, @utilitate)

END
GO

EXEC adauga_moneda @persoana_id = 4, @moneda_id = 1, @nr_monede = 5, @utilitate = 'nimic special'

CREATE OR ALTER VIEW viewMonede AS
	SELECT Copii.Nume, Tipuri_de_Monede.Tid FROM Tipuri_de_Monede
	INNER JOIN Copii ON Tipuri_de_Monede.Tid = Copii.Tid
	WHERE Copii.Varsta>10
GO

CREATE OR ALTER FUNCTION afisare (@moneda_id INT)
RETURNS TABLE AS
	RETURN
		SELECT Persoane.Nume FROM Persoane WHERE Pid IN
		( SELECT Pid FROM Moneda_Persoana WHERE Mid = @moneda_id and Mid IN
			(	SELECT Mid FROM Moneda_Persoana WHERE Numar>3
			)
		)

SELECT * FROM afisare(1)
		   
