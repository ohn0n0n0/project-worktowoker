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


CREATE LOGIN WorkToWorkerUser WITH PASSWORD='WorkToWorker', CHECK_EXPIRATION=OFF, CHECK_POLICY=ON
GO


USE WorkToWorker
GO


CREATE USER WorkToWorkerUser FOR LOGIN WorkToWorkerUser
GO


EXEC sp_addrolemember 'db_datawriter', 'WorkToWorkerUser'
GO
EXEC sp_addrolemember 'db_datareader', 'WorkToWorkerUser'
GO

------


CREATE TABLE [admin]
(
	username		nvarchar(30) PRIMARY KEY,
	[password]		nvarchar(30),
	isSystem		bit default (0)
)
GO

CREATE TABLE [login]
(
	username		nvarchar(30) PRIMARY KEY,
	[password]		nvarchar(30),
	email			nvarchar(250),
	permission		nvarchar(50),
	isDelete		bit default (0)
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
	[country]		nvarchar(100),
	city			nvarchar(100),
	[local]			nvarchar(100),
	phone			nvarchar(20),
	proofID			nvarchar(50),
	securityQuestion	nvarchar(250),
	securityAnswer	nvarchar(250),
	avatar			nvarchar(250),
	isDelete		bit default (0)
)
GO

CREATE TABLE [skills]
(
	skillID			int IDENTITY(1,1) PRIMARY KEY,
	skillName		nvarchar(250),
	[description]	nvarchar(max),
	isDelete		bit default (0)
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
	isDelete		bit default (0)
)
GO

CREATE TABLE [work]
(
	workID			int IDENTITY(1,1) PRIMARY KEY,
	username		nvarchar(30),
	skillName		nvarchar(50),
	workAddress		nvarchar(max),
	workCountry		nvarchar(250),
	workCity		nvarchar(250),
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
	isDelete		bit default (0)
)
GO

CREATE TABLE [ratingType]
(
	ratingID		int IDENTITY(1,1) PRIMARY KEY,
	ratingName		nvarchar(250),
	isDelete		bit default (0)
)
GO

CREATE TABLE [rating]
(
	workerID		int PRIMARY KEY,
	ratingID		int,
	score int,
	isDelete		bit default (0)
)
GO

CREATE TABLE [group]
(
	groupID			int IDENTITY(1,1) PRIMARY KEY,
	username		nvarchar(30),
	groupName		nvarchar(250),
	[description]	nvarchar(250),
	isDelete		bit default (0)
)
GO

CREATE TABLE [groupDetails]
(
	groupID			int,
	workerID		int,
	isDelete		bit default (0)
	CONSTRAINT pk_groupDetails PRIMARY KEY(groupID, workerID)
)
GO
CREATE TABLE [mediaWork]
(
	mediaID int IDENTITY(1,1) PRIMARY KEY,
	workID			int,
	url				nvarchar(250),
	isDelete		bit default (0),
)
GO
create table [workDaily]
(
	workID int ,
	workerID int ,
	startDate datetime,
	endDate datetime,
	Monday varchar(10),
	Tuesday varchar(10),
	Wednesday varchar(10),
	Thursday varchar(10),
	Friday varchar(10),
	Saturday varchar(10),
	Sunday varchar(10),
	isDelete		bit default (0)
CONSTRAINT PK_workDaily PRIMARY KEY (workId,workerId)
)
go
CREATE TABLE [topic]
(
	topicID			int IDENTITY(1,1) PRIMARY KEY,
	topicName		nvarchar(250),
	topicOrder		int,
	topicParent		int,
	isDelete		bit default (0)
)
GO
CREATE TABLE [post]
(
	postID			int IDENTITY(1,1) PRIMARY KEY,
	username		nvarchar(30),
	postTitle		nvarchar(250),
	postContent		text,
	topicID			int,
	postParent		int,
	postDateCreate	datetime,
	postDateEdit	datetime,
	[status]		bit,
	isDelete		bit default (0)
)
GO
CREATE TABLE [attach]
(
	attachID		int IDENTITY(1,1) PRIMARY KEY,
	postID			int,
	attachURL		nvarchar(250),
	isDelete		bit default (0)
)
GO
CREATE TABLE [report]
(
	reportID		int IDENTITY(1,1) PRIMARY KEY,
	postID			int,
	reportTypeID	int,
	content			nvarchar(250),
	isDelete		bit default (0)
)
GO
CREATE TABLE [reportType]
(
	reportTypeID	int IDENTITY(1,1) PRIMARY KEY,
	reportTypeName	nvarchar(250),
	isDelete		bit default (0)
)
GO
----- relationship
ALTER TABLE information
ADD CONSTRAINT FK_Username_Information FOREIGN KEY (username) REFERENCES [login](username)
GO

ALTER TABLE worker
ADD CONSTRAINT FK_Username_Worker FOREIGN KEY (username) REFERENCES [login](username)
GO

ALTER TABLE work
ADD CONSTRAINT FK_Username_Work FOREIGN KEY (username) REFERENCES [login](username)
GO
ALTER TABLE groupDetails
ADD CONSTRAINT FK_GroupID_GroupDetails FOREIGN KEY (groupID) REFERENCES [group](groupID),
CONSTRAINT FK_WorkerID_GroupDetails FOREIGN KEY (workerID) REFERENCES [worker](workerID)
GO

ALTER TABLE [group]
ADD CONSTRAINT FK_Username_Group FOREIGN KEY (username) REFERENCES [login](username)
GO

ALTER TABLE [rating]
ADD CONSTRAINT FK_RatingID_Rating FOREIGN KEY (ratingID) REFERENCES [ratingType](ratingID),
constraint FK_WokerID_Rating FOREIGN KEY (workerID) REFERENCES [worker](workerID)
GO
ALTER TABLE [mediaWork]
ADD CONSTRAINT FK_mediaid_mediaWork FOREIGN KEY (workID) REFERENCES [work](workID)
GO
ALTER TABLE [workDaily]
ADD CONSTRAINT FK_workID_workDaily FOREIGN KEY (workID) REFERENCES [work](workID),
CONSTRAINT FK_workerID_workDaily FOREIGN KEY (workerID) REFERENCES [worker](workerID)
GO
ALTER TABLE topic
ADD CONSTRAINT FK_TopicID_Topic FOREIGN KEY (topicParent) REFERENCES [topic](topicID)
GO
ALTER TABLE post
ADD CONSTRAINT FK_PostID_Post FOREIGN KEY (postParent) REFERENCES [post](postID),
CONSTRAINT FK_TopicID_Post FOREIGN KEY (topicID) REFERENCES [topic](topicID),
CONSTRAINT FK_Username_Post FOREIGN KEY (username) REFERENCES [login](username)
GO
ALTER TABLE report
ADD CONSTRAINT FK_ReportTypeID_Report FOREIGN KEY (reportTypeID) REFERENCES [reportType](reportTypeID)
GO

CREATE TRIGGER TableTopic_AfterInsert_TRG 
  ON topic 
AFTER INSERT
AS
  UPDATE topic
  SET topicParent = i.topicID
  FROM Inserted AS i
  WHERE topic.topicID = i.topicID;
  
GO
CREATE TRIGGER TablePost_AfterInsert_TRG 
  ON post
AFTER INSERT
AS
  UPDATE post
  SET postParent = i.postID
  FROM Inserted AS i
  WHERE post.postID = i.postID;