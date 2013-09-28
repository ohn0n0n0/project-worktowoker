USE master
GO

IF  EXISTS (
	SELECT name 
		FROM sys.databases 
		WHERE name = N'WorkToWorker'
)
DROP DATABASE [WorkToWorker]
GO

CREATE DATABASE [WorkToWorker]
GO

USE [WorkToWorker]
GO

CREATE TABLE [admin]
(
	username		nvarchar(30),
	[password]		nvarchar(30),
)
GO

CREATE TABLE [login]
(
	username		nvarchar(30) PRIMARY KEY,
	[password]		nvarchar(30),
	email			nvarchar(250),
	isDelete		bit
)
GO

CREATE TABLE [information]
(
	username		nvarchar(30) FOREIGN KEY REFERENCES [login](username) PRIMARY KEY,
	firstName		nvarchar(30),
	lastName		nvarchar(30),
	gender			bit,
	dob				datetime,
	[address]		nvarchar(max),
	city			nvarchar(100),
	[state]			nvarchar(100),
	[local]			nvarchar(100),
	phone			nvarchar(20),
	proofID			nvarchar(50),
	securityQuestion	nvarchar(250),
	securityAnswer	nvarchar(250),
	isDelete		bit
)
GO

CREATE TABLE [skills]
(
	skillID			int IDENTITY(1,1) PRIMARY KEY,
	skillName		nvarchar(250),
	[description]	nvarchar(max),
	isDelete		bit
)
GO

CREATE TABLE [worker]
(
	workerID		int IDENTITY(1,1) PRIMARY KEY,
	username		nvarchar(30) FOREIGN KEY REFERENCES [login](username),
	qualification	nvarchar(250),
	skillID			int FOREIGN KEY REFERENCES [skills](skillID),
	chargesHrs		money,
	chargesDaily	money,
	isDelete		bit
)
GO

CREATE TABLE [work]
(
	workID			int IDENTITY(1,1) PRIMARY KEY,
	username		nvarchar(30) FOREIGN KEY REFERENCES [login](username),
	skillID			int FOREIGN KEY REFERENCES [skills](skillID),
	workAddress		nvarchar(max),
	workCity		nvarchar(250),
	workState		nvarchar(250),
	workLoc			nvarchar(250),
	startDate		datetime,
	endDate			datetime,
	startTime		time,
	endTime			time,
	[status]		int,
	feedback		nvarchar(50),
	workDetails		nvarchar(50),
	chargesHrs		money,
	chargesDaily	money,
	isDelete		bit
)
GO

CREATE TABLE [ratingType]
(
	ratingID		int IDENTITY(1,1) PRIMARY KEY,
	ratingName		nvarchar(250),
	isDelete		bit
)
GO

CREATE TABLE [rating]
(
	workerID		int FOREIGN KEY REFERENCES [worker](workerID),
	ratingID		int FOREIGN KEY REFERENCES [ratingType](ratingID),
	score int,
	isDelete		bit
	CONSTRAINT pk_rating PRIMARY KEY(workerID, ratingID)
)
GO

CREATE TABLE [workerDaily]
(
	dateID			int IDENTITY(1,1) PRIMARY KEY,
	workerID		int FOREIGN KEY REFERENCES [worker](workerID),
	[date]			datetime,
	isDelete		bit
)
GO

CREATE TABLE [workerHours]
(
	workerID		int FOREIGN KEY REFERENCES [worker](workerID),
	dateID			int FOREIGN KEY REFERENCES [workerDaily](dateID),
	[time]			time,
	isDelete		bit
)
GO

CREATE TABLE [group]
(
	username		nvarchar(30) FOREIGN KEY REFERENCES [login](username),
	groupID			int IDENTITY(1,1) PRIMARY KEY,
	groupName		nvarchar(250),
	isDelete		bit
)
GO

CREATE TABLE [groupDetails]
(
	groupID			int FOREIGN KEY REFERENCES [group](groupID),
	workerID		int FOREIGN KEY REFERENCES [worker](workerID),
	isDelete		bit
	CONSTRAINT pk_groupDetails PRIMARY KEY(groupID, workerID)
)
GO

CREATE TABLE [workSelect]
(
	workID			int FOREIGN KEY REFERENCES [work](workID) PRIMARY KEY,
	workerID		int FOREIGN KEY REFERENCES [worker](workerID),
	groupID			int FOREIGN KEY REFERENCES [group](groupID),
	isDelete		bit
)
GO