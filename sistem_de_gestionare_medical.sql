CREATE DATABASE sistem_de_gestionare_medical;

drop database sistem_de_gestionare_medical;
drop table medic;
drop table pacient;
drop table programari;

CREATE TABLE medic (
    ID int(8) primary key,
    Nume varchar(40) not null,
    Prenume varchar(40) not null,
    Specializare varchar(30),
    Telefon varchar(13),
    Email varchar(30)
);

CREATE TABLE pacient (
    ID int(8) primary key,
    idnp varchar(13) not null,
    Nume varchar(40) not null,
    Prenume varchar(40) not null,
    DataNasterii date not null,
    Sex varchar(10) not null,
    Adresa text,
    Telefon varchar(13),
    GrupaDeSange varchar(10),
    Alergii text
);

CREATE TABLE programari (
    ID_Programare int(10) primary key,
    ID_Pacient int(8) not null,
    ID_Medic int(8) not null,
    DataOra datetime,
    Motivul text,
    Note text,
    FOREIGN KEY (ID_Pacient) REFERENCES pacient(ID),
    FOREIGN KEY (ID_Medic) REFERENCES medic(ID)
);

INSERT INTO pacient (ID, idnp, Nume, Prenume, DataNasterii, Sex, Adresa, Telefon, GrupaDeSange, Alergii)
VALUES
(1, '2002500876523', 'Arteni', 'Cristian', '2003-02-13', 'masculin', 'Str. Studentilor 7/2', '+373 60987263', 'AB-', 'Alergie alimentara: Nuci si seminte'),
(2, '2003500876525', 'Mocanu', 'Diana', '1998-04-26', 'feminin', 'Str. Luminii 5/1', '+373 60987264', 'B+', 'Alergie la polen'),
(3, '2004500876526', 'Enache', 'Ionut', '1990-12-15', 'masculin', 'Bdul. Victoriei 22/3', '+373 60987265', '0-', 'Alergie la praf'),
(4, '2005500876527', 'Constantin', 'Elena', '1985-07-30', 'feminin', 'Str. Pacii 89/10', '+373 60987266', 'A+', 'Alergie la latex'),
(5, '2006500876528', 'Tabacaru', 'Vlad', '1974-11-01', 'masculin', 'Str. Libertatii 67/8', '+373 60987267', 'A-', 'Alergie alimentara: Gluten'),
(6, '2001500234567', 'Ionescu', 'Ion', '1986-07-15', 'masculin', 'Str. Libertatii 12/1', '+373 69123456', 'A+'),
(7, '2007450987654', 'Marin', 'Ana', '1975-03-21', 'feminin', 'Bdul. Unirii 4/7', '+373 69234567', 'B-'),
(8, '1999050765432', 'Vasile', 'Mihai', '1999-05-07', 'masculin', 'Str. Florilor 8', '+373 69345678', 'AB+'),
(9, '1986120954321', 'Aleandrescu', 'Elena', '1986-12-09', 'feminin', 'Aleea Rozelor 5/6', '+373 69456789', '0-'),
(10, '2001120765432', 'Pop', 'Andreea', '2001-12-07', 'feminin', 'Str. Mihai Eminescu 9/2', '+373 69567890', 'A-'),
(11, '2006500876524', 'Popescu', 'Alexandra', '1994-11-12', 'feminin', 'Str. Trandafirilor 40/3', '+373 69099001', '0+');
INSERT INTO medic (ID, Nume, Prenume, Specializare, Telefon, Email)
VALUES
(1, 'Andries', 'Ion', 'Chirurgie', '+373 68928734', 'andriesion@gmail.com'),
(2, 'Baciu', 'Alina', 'Cardiologie', '+373 68928735', 'baciu.alina@gmail.com'),
(3, 'Cojocaru', 'Bogdan', 'Dermatologie', '+373 68928736', 'cojocaru.bogdan@yahoo.com'),
(4, 'Dumitrescu', 'Carmen', 'Pediatrie', '+373 68928737', 'dumitrescu.carmen@outlook.com'),
(5, 'Ene', 'Vasile', 'Neurologie', '+373 68928738', 'ene.vasile@hotmail.com'),
(6, 'Florescu', 'Laura', 'Ginecologie', '+373 68928739', 'florescu.laura@clinic.com');
INSERT INTO programari (ID_Programare, ID_Pacient, ID_Medic, DataOra, Motivul, Note)
VALUES
(1092837433, 1, 1, '2023-11-22 14:30:00', 'Dureri de inima', 'Dureri periodice de inima'),
(1092837423, 2, 1, '2023-11-23 11:30:00', 'Recrutare Voicomat', 'Procesul de verificare pentru recrutarea la voicomat'),
(1092837413, 2, 6, '2023-11-21 10:25:00', 'Cosuri multe', 'Aparitia deasa a cosurilor pe fata'),
(1192837443, 3, 5, '2023-11-24 14:15:00', 'Dureri de cap', 'Dureri frecvente'),
(1192837453, 11, 4, '2023-11-24 16:20:00', 'Verificare oftalmologica', 'Probleme de vedere'),
(1192837463, 11, 4, '2023-11-25 12:00:00', 'Control cardiologic', 'Palpitatii'),
(1192837473, 10, 4, '2023-11-25 08:45:00', 'Teste alergii', 'Reactii alergice recente'),
(1192837483, 9, 3, '2023-11-26 15:30:00', 'Examen dermatologic', 'Eruptii cutanate'),
(1192837493, 8, 2, '2023-11-26 10:00:00', 'Consult gastroenterologic', 'Disconfort abdominal'),
(1192837503, 8, 3, '2023-11-26 11:00:00', 'Control ORL', 'Dificultati de auz'),
(1192837513, 7, 1, '2023-11-27 09:30:00', 'Examinare neurologica', 'Amorteala in membre'),
(1192837523, 6, 5, '2023-11-27 14:00:00', 'Consultatie psihiatrica', 'Anxietate crescuta');


select * from pacient;
select * from pacient where GrupaDeSange = '0-';
select * from pacient where Sex = 'masculin';
select idnp, nume, prenume, telefon from pacient where Sex = 'feminin';

select * from programari;
select ID_Pacient, ID_Medic, Motivul, DataOra from programari where DataOra like '2023-11-26%';
select ID_Pacient, ID_Medic, Motivul, DataOra from programari where Motivul like '%alergii%';

select * from medic;
select * from medic where Specializare = 'Chirurgie';
select Telefon, Email from medic where Nume = 'Baciu' and Prenume = 'Alina';

update pacient
    set Alergii = 'Alergie la miere'
where id = 9;

update pacient
    set Telefon = '+373 67888999', Adresa = 'Str. Studentilor 7/3'
where Nume = 'Constantin' and Prenume = 'Elena';

update programari
    set DataOra = '2023-11-23 11:00:00'
where ID_Programare = 1;

update programari
    set Motivul = 'Teste alergii', Note = 'Reactii alergice la castraveti si rosii'
where ID_Programare = 5;

update medic
    set id = 3
where Nume = 'Dima' and prenume = 'Frimu';

delete from programari where DataOra like '%11-23%';
delete from programari where ID_Programare = 1192837483;
delete from pacient where GrupaDeSange = 'AB-' or GrupaDeSange = 'AB+';
delete from pacient where Adresa like '%Libertatii%';
delete from medic where id = 12333454;
delete from medic where Specializare = 'Ginecologie';

-- Toti pacientii care au programari
select pacient.Nume, pacient.Prenume, programari.DataOra, programari.Motivul
from pacient
inner join programari on pacient.ID = programari.ID_Medic;

-- Toti pacientii care au programari la data de26 luna a 11-a
select pacient.Nume, pacient.Prenume, programari.DataOra, programari.Motivul
from pacient
inner join programari on pacient.ID = programari.ID_Pacient where DataOra like '%11-26%';

-- Toti medicii si programarile acestora (in caz ca nu sunt, sunt setate cu null)
select medic.Nume, medic.Prenume, programari.DataOra, programari.Motivul
from medic
left join programari ON medic.ID = programari.ID_Medic;

-- Toti medicii chirurgi si pediatri si programarile acestora (in caz ca nu sunt, sunt setate cu null)
select medic.Nume, medic.Prenume, programari.DataOra, programari.Motivul
from medic
left join programari on medic.ID = programari.ID_Pacient where Specializare = 'Chirurgie' or Specializare = 'Pediatrie';

-- va selecta toate programările din tabelul programari, indiferent dacă pacientul asociat cu fiecare programare există sau nu in pacient table
select pacient.Nume, pacient.Prenume, programari.DataOra, programari.Motivul
from pacient
right join programari on pacient.ID = programari.ID_Pacient;

-- va selecta toate programările din tabelul programari, indiferent dacă medicul asociat cu fiecare programare există sau nu in medic table
select medic.Nume, medic.Prenume, programari.DataOra, programari.Motivul
from medic
right join programari on medic.ID = programari.ID_Pacient;

-- Combina fiecare medic cu fiecare pacient posibil
select medic.Nume, medic.Prenume,
       pacient.Nume, pacient.Prenume
from medic
cross join pacient;

-- Combina ficare medic cu fiecare programare posibila
select medic.Nume, medic.Prenume,
       programari.DataOra, programari.Motivul
from medic
cross join programari;

USE sistem_de_gestionare_medical;
SHOW TABLES;
