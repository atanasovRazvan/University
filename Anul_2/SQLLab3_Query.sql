USE LAB_1_store
GO

CREATE TABLE Version (currentV INT PRIMARY KEY, Descriere VARCHAR(50));
INSERT INTO Version VALUES(1, 7);

GO

CREATE PROCEDURE V1 AS
BEGIN
	
	ALTER TABLE Version ALTER COLUMN Descriere VARCHAR(10);
	UPDATE Version SET currentV = 2;

END
GO

CREATE PROCEDURE iV1 AS
BEGIN

	ALTER TABLE Version ALTER COLUMN Descriere INT;
	UPDATE Version SET currentV = 1;

END
GO

CREATE PROCEDURE V2 AS
BEGIN

	ALTER TABLE Sedii ADD CONSTRAINT def_Oras DEFAULT 'Cluj-Napoca' FOR Oras;
	UPDATE Version SET currentV = 3;
	


END
GO

CREATE PROCEDURE iV2 AS
BEGIN
	
	ALTER TABLE Sedii DROP CONSTRAINT def_Oras;
	UPDATE VERSION SET currentV = 2;

END
GO

CREATE PROCEDURE V3 AS
BEGIN

	CREATE TABLE Cel_Mai_Bun_Magazin(
		StoreID INT PRIMARY KEY,
		Nume VARCHAR(20),
		LOCATIE Varchar(20));
	UPDATE VERSION SET currentV = 4;


END
GO

CREATE PROCEDURE iV3 AS
BEGIN

	DROP TABLE Cel_Mai_Bun_Magazin;
	UPDATE VERSION SET currentV = 3;

END
GO

CREATE PROCEDURE V4 AS
BEGIN

	ALTER TABLE Angajati ADD Observatii varchar(50);
	UPDATE VERSION SET currentV = 5;

END
GO

CREATE PROCEDURE iV4 AS
BEGIN

	ALTER TABLE Angajati DROP COLUMN Observatii;
	UPDATE VERSION SET currentV = 4;

END
GO

CREATE PROCEDURE V5 AS
BEGIN

	ALTER TABLE Sedii ADD CONSTRAINT FK_idk FOREIGN KEY (Sid) REFERENCES Cel_Mai_Bun_Magazin(StoreID);
	UPDATE VERSION SET currentV = 6;

END
GO

CREATE PROCEDURE iV5 AS
BEGIN

	ALTER TABLE Angajati DROP CONSTRAINT FK_idk;
	UPDATE VERSION SET currentV = 5;

END
GO

CREATE PROCEDURE Main(@version INT) AS
BEGIN

	DECLARE @currentVersion INT;
	DECLARE @sql VARCHAR(15);
	SET @currentVersion = (SELECT currentV FROM Version);

	IF @version < 1 OR @version > 5 THROW 50001, 'Versiune incorecta', 1;

	WHILE @version < @currentVersion
		BEGIN
			
			SET @sql = 'EXEC iV' + CONVERT(VARCHAR(15),@currentVersion-1);
			EXEC(@sql);
			SET @currentVersion = (SELECT currentV FROM Version);

		END

	WHILE @version > @currentVersion
		BEGIN
			
			SET @sql = 'EXEC V' + CONVERT(VARCHAR(15),@currentVersion);
			EXEC(@sql);
			SET @currentVersion = (SELECT currentV FROM Version);

		END

END
GO

EXEC Main 1;
EXEC Main 2;
EXEC Main 3;
EXEC Main 4;
EXEC Main 1;
EXEC Main 10;

drop Table Version;
drop procedure V1;
drop procedure V2;
drop procedure V3;
drop procedure V4;
drop procedure V5;
drop procedure iV1;
drop procedure iV2;
drop procedure iV3;
drop procedure iV4;
drop procedure iV5;
drop procedure Main;
