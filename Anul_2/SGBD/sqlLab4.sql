USE Store

CREATE TABLE TransactionHistory(
    type VARCHAR(150),
    time TIME,
    message VARCHAR(150)
)

--DIRTY READS
BEGIN
    --WRONG
    BEGIN
    BEGIN TRANSACTION
        INSERT INTO Orar(nr_ore) VALUES (13);
        WAITFOR DELAY '00:00:10'
        ROLLBACK TRANSACTION
        INSERT INTO TransactionHistory(type, time, message) VALUES('DR Rollback', CONVERT(TIME, GETDATE()), 'Rollback successfully')
    END

    BEGIN
    SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED
    BEGIN TRANSACTION
        SELECT * FROM Orar;
        INSERT INTO TransactionHistory(type, time, message) VALUES('Orar select', CONVERT(TIME, GETDATE()), 'Selected all from Orar after insert')
        WAITFOR DELAY '00:00:15'
        SELECT * FROM Orar;
        INSERT INTO TransactionHistory(type, time, message) VALUES('Orar select', CONVERT(TIME, GETDATE()), 'Selected all from Orar after rollback')
    COMMIT TRANSACTION
    END

    --SOLUTION
    BEGIN
    SET TRANSACTION ISOLATION LEVEL READ COMMITTED
    BEGIN TRANSACTION
        SELECT * FROM Orar;
        INSERT INTO TransactionHistory(type, time, message) VALUES('Orar select', CONVERT(TIME, GETDATE()), 'Selected all from Orar after insert')
        WAITFOR DELAY '00:00:15'
        SELECT * FROM Orar;
        INSERT INTO TransactionHistory(type, time, message) VALUES('Orar select', CONVERT(TIME, GETDATE()), 'Selected all from Orar after rollback')
    COMMIT TRANSACTION
    END
END


--NON-REPEATABLE READS
BEGIN
    --WRONG
    BEGIN
    BEGIN TRANSACTION
        INSERT INTO Client(nume, prenume, varsta) VALUES ('ClientX', 'ClientX', 15);
        INSERT INTO TransactionHistory(type, time, message) VALUES('X Insert', CONVERT(TIME, GETDATE()), 'Delay for X insert started')
        WAITFOR DELAY '00:00:05';
        UPDATE Client SET nume = nume + ' TRANSACTION' WHERE nume = 'ClientX';
        INSERT INTO TransactionHistory(type, time, message) VALUES('X Insert', CONVERT(TIME, GETDATE()), 'Inserted')
        COMMIT TRANSACTION
        INSERT INTO TransactionHistory(type, time, message) VALUES('X Insert', CONVERT(TIME, GETDATE()), 'Insert rollback ended')
    END

    BEGIN
    SET TRANSACTION ISOLATION LEVEL READ COMMITTED
    BEGIN TRANSACTION
        SELECT * FROM Client;
        WAITFOR DELAY '00:00:10'
        SELECT * FROM Client;
    COMMIT TRANSACTION
    END

    --SOLUTION
    BEGIN
    SET TRANSACTION ISOLATION LEVEL REPEATABLE READ
    BEGIN TRANSACTION
        SELECT * FROM Client;
        WAITFOR DELAY '00:00:10'
        SELECT * FROM Client;
    COMMIT TRANSACTION
    END
END

--PHANTOM READS
BEGIN
    --WRONG
     BEGIN
    BEGIN TRANSACTION
        INSERT INTO TransactionHistory(type, time, message) VALUES('PR Insert', CONVERT(TIME, GETDATE()), 'Delay for NRR insert started')
        WAITFOR DELAY '00:00:05'
        INSERT INTO Client(nume, prenume, varsta) VALUES ('ClientPR', 'ClientPR', 50);
        INSERT INTO TransactionHistory(type, time, message) VALUES('PR Insert', CONVERT(TIME, GETDATE()), 'Inserted')
        COMMIT TRANSACTION
        INSERT INTO TransactionHistory(type, time, message) VALUES('PR Insert', CONVERT(TIME, GETDATE()), 'Insert rollback ended')
    END

    BEGIN
    SET TRANSACTION ISOLATION LEVEL REPEATABLE READ
    BEGIN TRANSACTION
        SELECT * FROM Client
        WAITFOR DELAY '00:00:10'
        SELECT * FROM Client
    COMMIT TRANSACTION
    END

    --SOLUTION
    BEGIN
    SET TRANSACTION ISOLATION LEVEL SERIALIZABLE
    BEGIN TRANSACTION
        SELECT * FROM Client
        WAITFOR DELAY '00:00:10'
        SELECT * FROM Client
    COMMIT TRANSACTION
    INSERT INTO TransactionHistory(type, time, message) VALUES('Phantom reads', CONVERT(TIME, GETDATE()), 'PR succeded')
    END
END

--DEADLOCK
BEGIN
    --TRANSACTION 1
    BEGIN
    SET TRANSACTION ISOLATION LEVEL SERIALIZABLE
    BEGIN TRY
    BEGIN TRANSACTION
        UPDATE Grupa SET sectie='Matematica' WHERE grupaID=1;
        WAITFOR DELAY '00:00:10'
        UPDATE Student SET nume = nume + ' mate' WHERE grupaID=1;
    COMMIT TRANSACTION
    END TRY
    begin catch
        ROLLBACK TRANSACTION
        PRINT 'VICTIM1'
        INSERT INTO TransactionHistory(type, time, message) VALUES('Deadlock', CONVERT(TIME, GETDATE()), 'Trans 1 is the victim')
    end catch
    END

    --TRANSACTION 2
        BEGIN
    SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
    BEGIN TRY
    BEGIN TRANSACTION
        UPDATE Student SET nume = nume + ' info' WHERE grupaID=4;
        WAITFOR DELAY '00:00:10'
        UPDATE Grupa SET sectie='Informatica' WHERE grupaID = 4;
    COMMIT TRANSACTION
    INSERT INTO TransactionHistory(type, time, message) VALUES('Deadlock', CONVERT(TIME, GETDATE()), 'Trans 2 succeeded')
    END TRY
    begin catch
        ROLLBACK TRANSACTION
        PRINT 'VICTIM2'
        INSERT INTO TransactionHistory(type, time, message) VALUES('Deadlock', CONVERT(TIME, GETDATE()), 'Trans 2 is the victim')
    end catch
    END

     --TRANSACTION 2 SOLUTION
        BEGIN
    SET DEADLOCK_PRIORITY HIGH;
    BEGIN TRY
    BEGIN TRANSACTION
        UPDATE Student SET nume = nume + ' info' WHERE grupaID=4;
        WAITFOR DELAY '00:00:10'
        UPDATE Grupa SET sectie='Informatica' WHERE grupaID = 4;
    COMMIT TRANSACTION
    INSERT INTO TransactionHistory(type, time, message) VALUES('Deadlock', CONVERT(TIME, GETDATE()), 'Trans 2 succeeded')
    END TRY
    begin catch
        ROLLBACK TRANSACTION
        PRINT 'VICTIM2'
        INSERT INTO TransactionHistory(type, time, message) VALUES('Deadlock', CONVERT(TIME, GETDATE()), 'Trans 2 is the victim')
    end catch
    END
end

----------------C#---------------------------

go
CREATE PROCEDURE usp_run_thread1
AS
    BEGIN
        SET TRANSACTION ISOLATION LEVEL SERIALIZABLE
        BEGIN TRANSACTION
        UPDATE Grupa SET sectie='Matematica' WHERE grupaID=1;
        WAITFOR DELAY '00:00:10'
        UPDATE Student SET nume = nume + ' mate' WHERE grupaID=1;
        COMMIT TRANSACTION
    END

go
CREATE PROCEDURE usp_run_thread2
AS
    BEGIN
        SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
        BEGIN TRANSACTION
        UPDATE Student SET nume = nume + ' info' WHERE grupaID=4;
        WAITFOR DELAY '00:00:10'
        UPDATE Grupa SET sectie='Informatica' WHERE grupaID = 4;
        COMMIT TRANSACTION
    END