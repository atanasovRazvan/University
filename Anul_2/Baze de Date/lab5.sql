go
use LAB_1_store
go

create or alter function ValidateNumeOferta(@nume int)
returns varchar(50)
as
begin
	if (@nume > 99 or @nume < 1)
		return 'invalid';
	return 'valid';
end
go

create or alter procedure CreateOferta(@id_oferta int, @nume int)
as
begin
	if ((select count(Ofid) from Oferte where Ofid = @id_oferta) = 0 and [dbo].ValidateNumeOferta(@nume) = 'valid')
	begin
		insert into Oferte(Ofid, Nume_oferta) values (@id_oferta, @nume);
	end
	else
		print 'Nu s-a putut insera';
end
go

create or alter procedure ReadOferta(@id_oferta int)
as
begin
	select Ofid, nume_oferta from Oferte where @id_oferta = Ofid;
end
go

create or alter procedure UpdateOferta(@id_oferta int, @nume int)
as
begin
	if((select count(Ofid) from Oferte where Ofid = @id_oferta) > 0 and [dbo].ValidateNumeOferta(@nume) = 'valid')
		update Oferte set Nume_oferta=@nume where @id_oferta = Ofid;
	else
		print 'Nu se poate face update';
end
go

create or alter procedure DeleteOferta(@id_oferta int)
as
begin
	delete from Oferte where Ofid = @id_oferta;
end
go



create or alter function ValidateProduse(@denumire varchar(50), @descriere varchar(50), @Iid int)
returns varchar(50)
as
begin
	if (@denumire = '' or @descriere = '' or @Iid = 0)
		return 'invalid';
	return 'valid';
end
go

create or alter procedure CreateProdus(@pid int, @iid int, @denumire varchar(50), @descriere varchar(50))
as
begin
	if((select count(Pid) from Produse where Pid = @Pid) = 0 and [dbo].ValidateProduse(@denumire, @descriere, @Iid) = 'valid')
	begin
		insert into Produse (Pid, Iid, Descriere, Denumire) values (@pid, @iid, @descriere, @denumire);
	end
	else
		print 'Nu s-a putut insera';
end
go

create or alter procedure ReadProdus(@pid int)
as
begin
	select Pid, Denumire, Descriere, Iid from Produse where @pid = Pid;
end
go

create or alter procedure UpdateProdus(@pid int, @denumire varchar(50), @descriere varchar(50), @iid int)
as
begin
	if((select count(Pid) from Produse where Pid = @pid) > 0 and [dbo].ValidateProduse(@denumire, @descriere, @iid)='valid')
		update Produse set Descriere = @descriere, Denumire = @denumire, Iid = @iid where Pid = @pid;
	else
		print 'Nu se poate updata';
end
go

create or alter procedure DeleteProdus(@pid int)
as
begin
	delete from Produse where @pid = Pid;
end
go


create or alter function ValidatePromotie(@Ofid int, @Pid int)
returns varchar(50)
as
begin
	if (@Ofid = 0 or @Pid = 0)
		return 'invalid';
	return 'valid';
end
go

create or alter procedure CreatePromotie(@Ofid int, @Pid int)
as
begin
	if ((select count(Ofid) from Promotii where Ofid = @Ofid and Pid = @Pid) = 0 and [dbo].ValidatePromotie(@Ofid, @Pid) = 'valid')
	begin
		insert into Promotii(Ofid, Pid) values (@Ofid, @Pid);
	end
	else
		print 'Nu s-a putut insera';
end
go

create or alter procedure ReadPromotie(@Ofid int, @Pid int)
as
begin
	select Ofid, Pid from Promotii where Ofid = @Ofid and Pid = @Pid;
end
go

create or alter procedure UpdatePromotie(@Ofid int, @OldOfid int, @Pid int)
as
begin
	if((select count(Ofid) from Promotii where Ofid = @Ofid and Pid = @Pid) = 0 and [dbo].ValidatePromotie(@Ofid, @Pid)='valid')
		update Promotii set Ofid = @Ofid where Ofid = @OldOfid and Pid = @Pid;
	else
		print 'Nu se poate updata';
end
go

create or alter procedure DeletePromotie(@Pid int, @Ofid int)
as
begin
	delete from Promotii where Ofid = @Ofid and Pid = @Pid;
end
go



create or alter function ValidateInventar(@nrprod int, @aid int)
returns varchar(50)
as
begin
	if (@nrprod = 0 or @aid = 0)
		return 'invalid';
	return 'valid';
end
go

create or alter procedure CreateInventar(@Iid int, @nrProd int, @Aid int)
as
begin
	if ((select count(Iid) from Inventar where Iid = @Iid) = 0 and [dbo].ValidateInventar(@nrProd, @Aid) = 'valid')
	begin
		insert into Inventar(Nr_produse, Data_inventar, Aid) values (@nrProd, GETDATE(), @Aid);
	end
	else
		print 'Nu s-a putut insera';
end
go

create or alter procedure ReadInventar(@Iid int)
as
begin
	select Iid, Nr_Produse, Data_inventar, Aid from Inventar where @Iid = Iid;
end
go

create or alter procedure UpdateInventar(@Iid int, @nrProd int, @Aid int)
as
begin
	if((select count(Iid) from Inventar where Iid = @Iid) > 0 and [dbo].ValidateInventar(@nrProd, @Aid)='valid')
		update Inventar set Nr_produse = @nrProd, Aid = @Aid, Data_inventar = GETDATE() where Iid = @Iid;
	else
		print 'Nu se poate updata';
end
go

create or alter procedure DeleteInventar(@Iid int)
as
begin
	delete from Inventar where Iid = @Iid;
end
go

/*  */

create or alter function ValidateAngajati(@nume varchar(50), @prenume varchar(50), @salariu int, @oid int)
returns varchar(50)
as
begin
	if (@nume != '' and @prenume != '' and @salariu >1152 and @oid > 0)
		return 'valid';
	return 'invalid';
end
go

create or alter procedure CreateAngajati(@Aid int, @nume varchar(50), @prenume varchar(50), @salariu int, @oid int)
as
begin
	if((select count(Aid) from Angajati where Aid = @Aid) > 0)
	begin
		insert into Angajati(Nume, Prenume, Salariu, Oid) values (@nume, @prenume, @salariu, @oid);
	end
	else
		print 'Nu s-a putut insera';
end
go

create or alter procedure ReadAngajati(@Aid int)
as
begin
	select * from Angajati where Aid = @Aid;
end
go

create or alter procedure UpdateAngajati(@Aid int, @Nume varchar(50), @Prenume varchar(50), @Salariu int, @Oid int)
as
begin
	if((select Aid from Angajati where Aid = @Aid) > 0)
		update Angajati set Nume = @Nume, Prenume = @Prenume, Salariu = @Salariu, Oid = @Oid where Aid = @Aid;
	else
		print 'Nu se poate updata';
end
go

create or alter procedure DeleteAngajati(@Aid int)
as
begin
	delete from Angajati where Aid = @Aid;
end
go


create or alter view View1 as
--Afiseaza numele angajatului care a distribuit anumite produse la inventarul din data afisata.
SELECT Angajati.Nume, Angajati.Prenume, Inventar.Data_inventar, Produse.Denumire FROM Inventar 
	INNER JOIN Angajati ON Inventar.Aid = Angajati.Aid 
	INNER JOIN Produse ON Produse.Iid = Inventar.Iid
go

create or alter view View2 as
--Afiseaza toate produsele cu reducerile aferente lor.
SELECT Produse.Denumire, Oferte.Nume_oferta FROM Oferte 
	INNER JOIN Promotii ON Oferte.Ofid = Promotii.Ofid 
	INNER JOIN Produse ON Promotii.Pid = Produse.Pid
go

if exists(select name from sys.indexes where name=N'N_idx_inventar')
	drop index N_idx_salariu on Angajati;
go

create nonclustered index N_idx_inventar on Angajati(Nume);
go

if exists(select name from sys.indexes where name=N'N_idx_reduceri')
	drop index N_idx_echipa on Produse;
go

create nonclustered index N_idx_reduceri on Promotii(Pid);
go

EXEC ReadOferta @id_oferta = 1
EXEC CreateOferta @id_oferta = 1, @nume = 50
EXEC ReadOferta @id_oferta = 1
EXEC UpdateOferta @id_oferta = 1, @nume = 10
EXEC ReadOferta @id_oferta = 1
EXEC DeleteOferta @id_oferta = 1
EXEC ReadOferta @id_oferta = 1

EXEC ReadProdus @pid = 2
EXEC CreateProdus @pid = 2, @iid = 1, @denumire = 'a', @descriere = 'a'
EXEC ReadProdus @pid = 2
EXEC UpdateProdus @pid = 2, @denumire = 'b', @descriere = 'b', @iid = 2
EXEC ReadProdus @pid = 2
EXEC DeleteProdus @pid = 2
EXEC ReadProdus @pid = 2

EXEC CreateOferta @id_oferta = 1, @nume = 17
EXEC CreateProdus @pid = 1, @iid = 1, @denumire = 'asdf', @descriere = 'asdf'
EXEC CreateOferta @id_oferta = 2, @nume = 83

EXEC ReadPromotie @ofid = 1, @pid = 1
EXEC CreatePromotie @ofid = 1, @pid = 1
EXEC ReadPromotie @ofid = 1, @pid = 1
EXEC UpdatePromotie @ofid = 2, @OldOfid = 1, @pid = 1
EXEC ReadPromotie @ofid = 2, @pid = 1
EXEC DeletePromotie @pid = 1, @ofid = 2
EXEC ReadPromotie @ofid = 2, @pid = 1
