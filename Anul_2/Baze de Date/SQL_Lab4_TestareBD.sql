if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestRunTables_Tables]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [TestRunTables] DROP CONSTRAINT FK_TestRunTables_Tables
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestTables_Tables]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [TestTables] DROP CONSTRAINT FK_TestTables_Tables
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestRunTables_TestRuns]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [TestRunTables] DROP CONSTRAINT FK_TestRunTables_TestRuns
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestRunViews_TestRuns]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [TestRunViews] DROP CONSTRAINT FK_TestRunViews_TestRuns
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestTables_Tests]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [TestTables] DROP CONSTRAINT FK_TestTables_Tests
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestViews_Tests]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [TestViews] DROP CONSTRAINT FK_TestViews_Tests
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestRunViews_Views]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [TestRunViews] DROP CONSTRAINT FK_TestRunViews_Views
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestViews_Views]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [TestViews] DROP CONSTRAINT FK_TestViews_Views
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[Tables]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [Tables]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[TestRunTables]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [TestRunTables]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[TestRunViews]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [TestRunViews]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[TestRuns]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [TestRuns]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[TestTables]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [TestTables]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[TestViews]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [TestViews]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[Tests]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [Tests]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[Views]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [Views]
GO

CREATE TABLE [Tables] (
	[TableID] [int] IDENTITY (1, 1) NOT NULL ,
	[Name] [nvarchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [TestRunTables] (
	[TestRunID] [int] NOT NULL ,
	[TableID] [int] NOT NULL ,
	[StartAt] [datetime] NOT NULL ,
	[EndAt] [datetime] NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [TestRunViews] (
	[TestRunID] [int] NOT NULL ,
	[ViewID] [int] NOT NULL ,
	[StartAt] [datetime] NOT NULL ,
	[EndAt] [datetime] NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [TestRuns] (
	[TestRunID] [int] IDENTITY (1, 1) NOT NULL ,
	[Description] [nvarchar] (2000) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[StartAt] [datetime] NULL ,
	[EndAt] [datetime] NULL 
) ON [PRIMARY]
GO

CREATE TABLE [TestTables] (
	[TestID] [int] NOT NULL ,
	[TableID] [int] NOT NULL ,
	[NoOfRows] [int] NOT NULL ,
	[Position] [int] NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [TestViews] (
	[TestID] [int] NOT NULL ,
	[ViewID] [int] NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [Tests] (
	[TestID] [int] IDENTITY (1, 1) NOT NULL ,
	[Name] [nvarchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [Views] (
	[ViewID] [int] IDENTITY (1, 1) NOT NULL ,
	[Name] [nvarchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL 
) ON [PRIMARY]
GO

ALTER TABLE [Tables] WITH NOCHECK ADD 
	CONSTRAINT [PK_Tables] PRIMARY KEY  CLUSTERED 
	(
		[TableID]
	)  ON [PRIMARY] 
GO

ALTER TABLE [TestRunTables] WITH NOCHECK ADD 
	CONSTRAINT [PK_TestRunTables] PRIMARY KEY  CLUSTERED 
	(
		[TestRunID],
		[TableID]
	)  ON [PRIMARY] 
GO

ALTER TABLE [TestRunViews] WITH NOCHECK ADD 
	CONSTRAINT [PK_TestRunViews] PRIMARY KEY  CLUSTERED 
	(
		[TestRunID],
		[ViewID]
	)  ON [PRIMARY] 
GO

ALTER TABLE [TestRuns] WITH NOCHECK ADD 
	CONSTRAINT [PK_TestRuns] PRIMARY KEY  CLUSTERED 
	(
		[TestRunID]
	)  ON [PRIMARY] 
GO

ALTER TABLE [TestTables] WITH NOCHECK ADD 
	CONSTRAINT [PK_TestTables] PRIMARY KEY  CLUSTERED 
	(
		[TestID],
		[TableID]
	)  ON [PRIMARY] 
GO

ALTER TABLE [TestViews] WITH NOCHECK ADD 
	CONSTRAINT [PK_TestViews] PRIMARY KEY  CLUSTERED 
	(
		[TestID],
		[ViewID]
	)  ON [PRIMARY] 
GO

ALTER TABLE [Tests] WITH NOCHECK ADD 
	CONSTRAINT [PK_Tests] PRIMARY KEY  CLUSTERED 
	(
		[TestID]
	)  ON [PRIMARY] 
GO

ALTER TABLE [Views] WITH NOCHECK ADD 
	CONSTRAINT [PK_Views] PRIMARY KEY  CLUSTERED 
	(
		[ViewID]
	)  ON [PRIMARY] 
GO

ALTER TABLE [TestRunTables] ADD 
	CONSTRAINT [FK_TestRunTables_Tables] FOREIGN KEY 
	(
		[TableID]
	) REFERENCES [Tables] (
		[TableID]
	) ON DELETE CASCADE  ON UPDATE CASCADE ,
	CONSTRAINT [FK_TestRunTables_TestRuns] FOREIGN KEY 
	(
		[TestRunID]
	) REFERENCES [TestRuns] (
		[TestRunID]
	) ON DELETE CASCADE  ON UPDATE CASCADE 
GO

ALTER TABLE [TestRunViews] ADD 
	CONSTRAINT [FK_TestRunViews_TestRuns] FOREIGN KEY 
	(
		[TestRunID]
	) REFERENCES [TestRuns] (
		[TestRunID]
	) ON DELETE CASCADE  ON UPDATE CASCADE ,
	CONSTRAINT [FK_TestRunViews_Views] FOREIGN KEY 
	(
		[ViewID]
	) REFERENCES [Views] (
		[ViewID]
	) ON DELETE CASCADE  ON UPDATE CASCADE 
GO



ALTER TABLE [TestTables] ADD 
	CONSTRAINT [FK_TestTables_Tables] FOREIGN KEY 
	(
		[TableID]
	) REFERENCES [Tables] (
		[TableID]
	) ON DELETE CASCADE  ON UPDATE CASCADE ,
	CONSTRAINT [FK_TestTables_Tests] FOREIGN KEY 
	(
		[TestID]
	) REFERENCES [Tests] (
		[TestID]
	) ON DELETE CASCADE  ON UPDATE CASCADE 
GO

ALTER TABLE [TestViews] ADD 
	CONSTRAINT [FK_TestViews_Tests] FOREIGN KEY 
	(
		[TestID]
	) REFERENCES [Tests] (
		[TestID]
	),
	CONSTRAINT [FK_TestViews_Views] FOREIGN KEY 
	(
		[ViewID]
	) REFERENCES [Views] (
		[ViewID]
	)
GO


--------------------------------------------------------------------- TASK ---------------------------------------------------------------------------------

use LAB_1_store

CREATE OR ALTER VIEW viewOrar AS 
	SELECT * FROM Orar 
	WHERE Nr_ore >= 6
GO

CREATE OR ALTER VIEW viewAngajati AS
	
	--Afiseaza angajatii si orele lor de munca
	
	SELECT Angajati.Nume, Angajati.Prenume, Orar.Nr_ore 
	FROM Orar 
	INNER JOIN Angajati ON Orar.Oid = Angajati.Oid
GO

CREATE OR ALTER VIEW viewPromotii AS

	SELECT Produse.Denumire, SUM(Oferte.Nume_oferta) as a FROM Oferte
	INNER JOIN Promotii ON Oferte.Ofid = Promotii.Ofid
	INNER JOIN Produse ON Promotii.Pid = Produse.Pid
	GROUP BY Produse.Denumire

GO


-----------------------------
		--PROCEDURES

CREATE OR ALTER PROCEDURE insert_to_Oferte @numar_inregistrari INT AS
BEGIN

	WHILE @numar_inregistrari > 0
	BEGIN
		INSERT INTO Oferte(Nume_oferta) VALUES(69)
		set @numar_inregistrari = @numar_inregistrari - 1
	END

END
GO

CREATE OR ALTER PROCEDURE insert_to_Produse @numar_inregistrari INT AS
BEGIN

	WHILE @numar_inregistrari > 0
	BEGIN
		INSERT INTO Produse(Denumire, Descriere, Iid) VALUES('Produs smecher', 'Descriere smechera', 1)
		set @numar_inregistrari = @numar_inregistrari - 1
	END

END
GO

CREATE OR ALTER PROCEDURE insert_to_Promotii @numar_inregistrari INT AS
BEGIN

	INSERT INTO Promotii(Pid, Ofid)
	SELECT TOP (@numar_inregistrari) Produse.Pid, Oferte.Ofid FROM Oferte CROSS JOIN Produse

END
GO

CREATE OR ALTER PROCEDURE execute_view_viewOrar AS
BEGIN
	SELECT * FROM viewOrar
END
GO

CREATE OR ALTER PROCEDURE execute_view_viewAngajati AS
BEGIN
	SELECT * FROM viewAngajati
END
GO

CREATE OR ALTER PROCEDURE execute_view_viewPromotii AS
BEGIN
	SELECT * FROM viewPromotii
END
GO
-----------------------------


CREATE OR ALTER PROCEDURE test1 @nume_test varchar(50) AS
	BEGIN
		IF NOT EXISTS(SELECT TOP 1 TestID FROM Tests WHERE Name = @nume_test)
		BEGIN
			throw 50001, N'Invalid test name', 1;
		END

		DECLARE testTableCursor CURSOR SCROLL FOR
			SELECT TestTables.TableID, TestTables.NoOfRows
			FROM TestTables
			WHERE TestTables.TestID = (SELECT Tests.TestID FROM Tests WHERE Tests.Name = @nume_test)
			ORDER BY TestTables.Position ASC

		OPEN testTableCursor;

		DECLARE @test_id INT
		DECLARE @nr_inregistrari INT
		DECLARE @table_id INT
		DECLARE @table_name VARCHAR(50)
		DECLARE @procedure_name VARCHAR(50)
		DECLARE @test_start_time DATETIME
		DECLARE @test_finish_time DATETIME

		SET @test_start_time = GETDATE()

		INSERT INTO TestRuns(StartAt) VALUES (@test_start_time)
		SET @test_id = (SELECT TestRunID FROM TestRuns WHERE StartAt = @test_start_time)

		FETCH FIRST FROM testTableCursor INTO @table_id, @nr_inregistrari
		WHILE @@FETCH_STATUS = 0
		BEGIN
			
			DECLARE @command VARCHAR(50)
			SET @table_name = (SELECT TOP 1 Name FROM Tables WHERE Tables.TableID = @table_id)
			SET @command = 'DELETE FROM '+ @table_name
			EXEC (@command)

			FETCH NEXT FROM testTableCursor INTO @table_id, @nr_inregistrari

		END
		
		FETCH LAST FROM testTableCursor INTO @table_id, @nr_inregistrari
		WHILE @@FETCH_STATUS = 0
		BEGIN
			
			DECLARE @insertStartTime DATETIME
			DECLARE @insertFinishTime DATETIME

			SET @table_name = (SELECT TOP 1 Name FROM Tables WHERE Tables.TableID = @table_id)
			SET @procedure_name = 'insert_to_' + @table_name

			SET @insertStartTime = GETDATE()
			EXEC @procedure_name @nr_inregistrari
			SET @insertFinishTime = GETDATE()

			INSERT INTO TestRunTables(TestRunID, TableID, StartAt, EndAt)
			VALUES(@test_id, @table_id, @insertStartTime, @insertFinishTime)

			FETCH PRIOR FROM testTableCursor INTO @table_id, @nr_inregistrari

		END

		CLOSE testTableCursor;
		DEALLOCATE testTableCursor;

		DECLARE testViewCursor CURSOR FOR
			SELECT TestViews.ViewID FROM TestViews
			WHERE TestViews.TestID = (SELECT TestID FROM Tests WHERE Name = @nume_test)
		OPEN testViewCursor
		DECLARE @view_id INT
		DECLARE @view_nume VARCHAR(50)

		FETCH NEXT FROM testViewCursor INTO @view_id
		WHILE @@FETCH_STATUS = 0
		BEGIN
			
			DECLARE @insertStartTimeView DATETIME
			DECLARE @insertFinishTimeView DATETIME

			SET @view_nume = (SELECT TOP 1 Name FROM Views WHERE Views.ViewID = @view_id)
			SET @procedure_name = 'execute_view_' + @view_nume

			SET @insertStartTimeView = GETDATE()
			EXEC @procedure_name
			SET @insertFinishTimeView = GETDATE()

			INSERT INTO TestRunViews(TestRunID, ViewID, StartAt, EndAt)
			VALUES(@test_id, @view_id, @insertStartTimeView, @insertFinishTimeView)

			FETCH NEXT FROM testViewCursor INTO @view_id

		END

		CLOSE testViewCursor;
		DEALLOCATE testViewCursor;

		SET @test_finish_time = GETDATE()
		UPDATE TestRuns SET EndAt = @test_finish_time WHERE TestRunID = @test_id

	END
GO

EXEC test1 'test1'


DROP VIEW viewOrar
DROP VIEW viewAngajati
DROP VIEW viewPromotii
DROP PROCEDURE test1


SET NOCOUNT ON