IF DB_ID('FeedTheMonster') IS NOT NULL DROP DATABASE FeedTheMonster;
GO
CREATE DATABASE FeedTheMonster;
GO
USE FeedTheMonster;
GO
EXEC sp_changedbowner 'sa';
GO
CREATE SCHEMA Entries;
GO
CREATE TABLE Entries.Monsters
(
	id			int				NOT NULL IDENTITY(1,1) PRIMARY KEY,
	name		NVARCHAR(20)	NOT NULL UNIQUE,
	level		int				NOT NULL DEFAULT 0,
	birthday	DATE			NOT NULL DEFAULT SYSDATETIME()
);
GO
CREATE PROC Entries.LevelIncrement
@name AS NVARCHAR(20)
AS
	UPDATE Entries.Monsters
	SET level += 1
	WHERE name = @name;
GO
CREATE PROC Entries.GetMonsters
AS
	SELECT id, name, level, birthday
	FROM Entries.Monsters
	ORDER BY name ASC;
GO
CREATE PROC Entries.GetMonster
@name AS NVARCHAR(20)
AS
	SELECT id, name, level, birthday
	FROM Entries.Monsters
	WHERE name = @name;
GO

INSERT INTO Entries.Monsters (name)
	VALUES (N'Monster1');
INSERT INTO Entries.Monsters (name)
	VALUES (N'Monster2');
INSERT INTO Entries.Monsters (name)
	VALUES (N'Monster3');