USE Store;

CREATE TABLE Client(

	client_id INT PRIMARY KEY IDENTITY,
	nume VARCHAR(50),
	prenume VARCHAR(50),
	varsta INT

);

CREATE TABLE Produs(

	produs_id INT PRIMARY KEY IDENTITY,
	denumire VARCHAR(50),
	greutate INT

);

CREATE TABLE Comanda(

	client_id INT,
	produs_id INT,
	durata INT,
	AWB VARCHAR(50),
	PRIMARY KEY (client_id, produs_id),
	FOREIGN KEY (client_id) REFERENCES Client(client_id),
	FOREIGN KEY (produs_id) REFERENCES Produs(produs_id)

);

-- Verifica daca numele/prenumele/denumirea incepe cu litera mare sau nu este vid
CREATE FUNCTION valNume(@nume VARCHAR(50)) RETURNS INT AS
BEGIN
	DECLARE @return INT
	SET @return = 1 
	IF(@nume = null OR @nume != upper(left(@nume, 1)) + right(@nume, len(@nume) - 1)) 
		SET @return = 0 
	RETURN @return
END

-- Verifica ca varsta sa fie cel putin 18
CREATE FUNCTION valVarsta(@varsta INT) RETURNS INT AS
BEGIN
	DECLARE @return INT
	SET @return = 1
	IF(@varsta < 18)
		SET @return = 0
	RETURN @return
END

CREATE PROCEDURE AddClientProdusSingle @nume varchar(50), @prenume varchar(50), @varsta int, @denumire varchar(50),
	@greutate int, @durata int, @AWB varchar(50) AS 
BEGIN

	BEGIN TRAN
		BEGIN TRY
			IF(dbo.valNume(@nume) = 0) 
				BEGIN RAISERROR('Numele trebuie sa inceapa cu litera mare!',14,1)
				END
			IF(dbo.valNume(@prenume) = 0)
				BEGIN RAISERROR('Prenumele trebuie sa inceapa cu litera mare!',14,1);
				END
			IF(dbo.valVarsta(@varsta) = 0)
				BEGIN RAISERROR('Varsta trebuie sa fie >18!',14,1);
				END
			IF(dbo.valNume(@denumire) = 0)
				BEGIN RAISERROR('Denumirea produsului trebuie sa inceapa cu litera mare!',14,1);
				END
			IF(dbo.valNume(@AWB) = 0)
				BEGIN RAISERROR('AWB-ul trebuie sa inceapa cu litera mare si sa nu fie nul!',14,1);
				END

			INSERT INTO Client(nume, prenume, varsta) VALUES (@nume, @prenume, @varsta);
			DECLARE  @clientid INT = SCOPE_IDENTITY();
			INSERT INTO Produs (denumire, greutate) VALUES (@denumire, @greutate)
			DECLARE @produsid INT = SCOPE_IDENTITY();
			INSERT INTO Comanda(client_id, produs_id, durata, AWB) VALUES (@clientid, @produsid, @durata, @AWB);

			COMMIT TRAN
			SELECT 'Transaction committed'
		END TRY
		BEGIN CATCH
			ROLLBACK TRAN
			SELECT 'Transaction rollbacked'
		END CATCH

END

CREATE PROCEDURE AddClientProdus @nume varchar(50), @prenume varchar(50), @varsta int, @denumire varchar(50),
	@greutate int, @durata int, @AWB varchar(50) AS 
BEGIN  
	BEGIN TRAN 
		BEGIN TRY 
			IF(dbo.valNume(@nume) = 0) 
				BEGIN RAISERROR('Numele trebuie sa inceapa cu litera mare!',14,1)
				END
			IF(dbo.valNume(@prenume) = 0)
				BEGIN RAISERROR('Prenumele trebuie sa inceapa cu litera mare!',14,1);
				END
			IF(dbo.valVarsta(@varsta) = 0)
				BEGIN RAISERROR('Varsta trebuie sa fie >18!',14,1);
				END

			INSERT INTO Client(nume, prenume, varsta) VALUES (@nume, @prenume, @varsta);
			DECLARE  @clientid INT = SCOPE_IDENTITY();
			COMMIT TRAN
			SELECT 'Transaction committed'

			BEGIN TRAN
				BEGIN TRY
					IF(dbo.valNume(@denumire) = 0)
						BEGIN RAISERROR('Denumirea produsului trebuie sa inceapa cu litera mare!',14,1);
						END

					INSERT INTO Produs (denumire, greutate) VALUES (@denumire, @greutate)
					DECLARE @produsid INT = SCOPE_IDENTITY();
					COMMIT TRAN
					SELECT 'Transaction committed'

					BEGIN TRAN
						BEGIN TRY
							IF(dbo.valNume(@AWB) = 0)
								BEGIN RAISERROR('AWB-ul trebuie sa inceapa cu litera mare si sa nu fie nul!',14,1);
								END
							INSERT INTO Comanda(client_id, produs_id, durata, AWB) VALUES (@clientid, @produsid, @durata, @AWB);
							COMMIT TRAN
							SELECT 'Transaction committed'
						END TRY
						BEGIN CATCH
							ROLLBACK TRAN
							SELECT 'Transaction rollbacked'
						END CATCH

				END TRY
				BEGIN CATCH
					ROLLBACK TRAN
					SELECT 'Transaction rollbacked'
				END CATCH

		END TRY
		
		BEGIN CATCH
			ROLLBACK TRAN
			SELECT 'Transaction rollbacked'
		END CATCH
END

	
DROP TABLE Comanda;
DROP TABLE Client;
DROP TABLE Produs;
DROP FUNCTION dbo.valNume;
DROP FUNCTION dbo.valVarsta;

SELECT * FROM Client;
SELECT * FROM Produs;
SELECT * FROM Comanda;
EXEC AddClientProdusSingle @nume = 'Andrei', @prenume = 'Mihai', @varsta = 21, @denumire = 'Laptop', @greutate = 3, @durata = 5, @AWB = 'AWB12944683';
SELECT * FROM Client;
SELECT * FROM Produs;
SELECT * FROM Comanda;

SELECT * FROM Client;
SELECT * FROM Produs;
SELECT * FROM Comanda;
EXEC AddClientProdusSingle @nume = 'Andrei', @prenume = 'Mihai', @varsta = 21, @denumire = 'Laptop', @greutate = 3, @durata = 5, @AWB = '';
SELECT * FROM Client;
SELECT * FROM Produs;
SELECT * FROM Comanda;

SELECT * FROM Client;
SELECT * FROM Produs;
SELECT * FROM Comanda;
EXEC AddClientProdus @nume = 'Andrei', @prenume = 'Mihai', @varsta = 21, @denumire = 'Laptop', @greutate = 3, @durata = 5, @AWB = 'AWB12944683';
SELECT * FROM Client;
SELECT * FROM Produs;
SELECT * FROM Comanda;

SELECT * FROM Client;
SELECT * FROM Produs;
SELECT * FROM Comanda;
EXEC AddClientProdus @nume = '', @prenume = 'Mihai', @varsta = 21, @denumire = 'Laptop', @greutate = 3, @durata = 5, @AWB = 'AWB12944683';
SELECT * FROM Client;
SELECT * FROM Produs;
SELECT * FROM Comanda;

SELECT * FROM Client;
SELECT * FROM Produs;
SELECT * FROM Comanda;
EXEC AddClientProdus @nume = 'Andrei', @prenume = 'Mihai', @varsta = 21, @denumire = '', @greutate = 3, @durata = 5, @AWB = 'AWB12944683';
SELECT * FROM Client;
SELECT * FROM Produs;
SELECT * FROM Comanda;

SELECT * FROM Client;
SELECT * FROM Produs;
SELECT * FROM Comanda;
EXEC AddClientProdus @nume = 'Andrei', @prenume = 'Mihai', @varsta = 21, @denumire = 'Laptop', @greutate = 3, @durata = 5, @AWB = '';
SELECT * FROM Client;
SELECT * FROM Produs;
SELECT * FROM Comanda;