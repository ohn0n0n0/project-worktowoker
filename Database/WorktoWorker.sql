USE master
GO
---user


------
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
	username		nvarchar(30) PRIMARY KEY,
	[password]		nvarchar(30),
	isSystem		bit
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
	username		nvarchar(30) PRIMARY KEY,
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
	username		nvarchar(30),
	qualification	nvarchar(250),
	skillID			int,
	chargesHrs		money,
	chargesDaily	money,
	isDelete		bit
)
GO

CREATE TABLE [work]
(
	workID			int IDENTITY(1,1) PRIMARY KEY,
	username		nvarchar(30),
	skillID			int,
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
	workerID		int,
	ratingID		int,
	score int,
	isDelete		bit
	CONSTRAINT pk_rating PRIMARY KEY(workerID, ratingID)
)
GO

CREATE TABLE [group]
(
	groupID			int IDENTITY(1,1) PRIMARY KEY,
	username		nvarchar(30),
	groupName		nvarchar(250),
	isDelete		bit
)
GO

CREATE TABLE [groupDetails]
(
	groupID			int,
	workerID		int,
	isDelete		bit
	CONSTRAINT pk_groupDetails PRIMARY KEY(groupID, workerID)
)
GO

CREATE TABLE [workSelect]
(
	workID			int ,
	workerID		int,
	isDelete		bit,
	CONSTRAINT pk_workSelect PRIMARY KEY(workID, workerID)
)
GO
----- relationship
alter table information
add constraint FK_Username_Information foreign key (username) references [login](username)
go

alter table worker
add constraint FK_Username_Worker foreign key (username) references [login](username)
go

alter table work
add constraint FK_Username_Work foreign key (username) references [login](username)
go

alter table workSelect
add constraint FK_WorkID_WorkSelect foreign key (workID) references [work](workID),
constraint FK_WorkerID_WorkSelect foreign key (workerID) references [worker](workerID)
go
alter table groupDetails
add constraint FK_GroupID_GroupDetails foreign key (groupID) references [group](groupID),
constraint FK_WorkerID_GroupDetails foreign key (workerID) references [worker](workerID)
go

alter table [group]
add constraint FK_Username_Group foreign key (username) references [login](username)
go

alter table [rating]
add constraint FK_RatingID_Rating foreign key (ratingID) references [ratingType](ratingID)
go