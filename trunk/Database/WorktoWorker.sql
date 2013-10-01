USE master
GO
---user
-- create database with default parameters 
IF  EXISTS (
	SELECT name 
		FROM sys.databases 
		WHERE name = N'WorkToWorker'
)
DROP DATABASE [WorkToWorker]
GO

CREATE DATABASE [WorkToWorker]
GO

-- create a login called 'JitbitHelpDeskUser' 
-- remember to change the password

--/*exec sp_addlogin 'JitbitHelpDeskUser', 'HDPassword1', 'JitbitHelpDesk'*/
-- "sp_addlogin" is deprecated so lets use CREATE LOGIN INSTEAD
CREATE LOGIN WorkToWorkerUser WITH PASSWORD='WorkToWorker', CHECK_EXPIRATION=OFF, CHECK_POLICY=ON
GO

-- switch to the context of the new database 
USE WorkToWorker
GO

-- add JitbitHelpDeskUser as a user in this database 
--/*exec sp_adduser 'JitbitHelpDeskUser', 'JitbitHelpDeskUser', 'db_datareader'*/
-- "sp_adduser" is depracated
CREATE USER WorkToWorkerUser FOR LOGIN WorkToWorkerUser
GO

-- add the new user to the roles
EXEC sp_addrolemember 'db_datawriter', 'WorkToWorkerUser'
GO
EXEC sp_addrolemember 'db_datareader', 'WorkToWorkerUser'
GO

------


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
	avata			nvarchar(250),
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
	skillName		nvarchar(30),
	chargesHrs		money,
	chargesDaily	money,
	isDelete		bit
)
GO

CREATE TABLE [work]
(
	workID			int IDENTITY(1,1) PRIMARY KEY,
	username		nvarchar(30),
	skillName		nvarchar(50),
	workAddress		nvarchar(max),
	workCity		nvarchar(250),
	workState		nvarchar(250),
	workLoc			nvarchar(250),
	startDate		datetime,
	endDate			datetime,
	startTime		time,
	endTime			time,
	[status]		int,
	feedback		nvarchar(4000),
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
	workerID		int PRIMARY KEY,
	ratingID		int,
	score int,
	isDelete		bit
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
CREATE TABLE [mediaWork]
(
	mediaID int IDENTITY(1,1) PRIMARY KEY,
	workID			int,
	isDelete		bit,
)
GO
CREATE TABLE [box]
(
	boxID			int IDENTITY(1,1) PRIMARY KEY,
	boxName			nvarchar(250),
	boxOrder		int,
	boxParent		int,
	isDelete		bit
)
GO
CREATE TABLE [post]
(
	postID			int IDENTITY(1,1) PRIMARY KEY,
	username		nvarchar(30),
	postTitle		nvarchar(250),
	postContent		text,
	boxID			int,
	postParent		int,
	postDateCreate	datetime,
	postDateEdit	datetime,
	[status]		bit,
	isDelete		bit
)
GO
CREATE TABLE [attach]
(
	attachID		int IDENTITY(1,1) PRIMARY KEY,
	postID			int,
	attachURL		nvarchar(250),
	isDelete		bit
)
GO
CREATE TABLE [report]
(
	reportID		int IDENTITY(1,1) PRIMARY KEY,
	postID			int,
	reportTypeID		int,
	content			nvarchar(250)
)
GO
CREATE TABLE [reportType]
(
	reportTypeID	int IDENTITY(1,1) PRIMARY KEY,
	reportTypeName	nvarchar(250)
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
add constraint FK_RatingID_Rating foreign key (ratingID) references [ratingType](ratingID),
constraint FK_WokerID_Rating foreign key (workerID) references [worker](workerID)
go
alter table [mediaWork]
add constraint FK_mediaid_mediaWork foreign key (mediaID) references [work](workID)
go
alter table box
add constraint FK_BoxID_Box foreign key (boxParent) references [box](boxID)
go
alter table post
add constraint FK_PostID_Post foreign key (postParent) references [post](postID),
constraint FK_BoxID_Post foreign key (boxID) references [box](boxID),
constraint FK_Username_Post foreign key (username) references [login](username)
go
alter table report
add constraint FK_ReportTypeID_Report foreign key (reportTypeID) references [reportType](reportTypeID)
go