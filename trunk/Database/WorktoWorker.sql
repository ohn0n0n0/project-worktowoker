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
	createDate		datetime,
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
	city			nvarchar(100),
	[country]		nvarchar(100),
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
	workCity		nvarchar(250),
	workCountry		nvarchar(250),
	startDate		datetime,
	endDate			datetime,
	startTime		time,
	endTime			time,
	[status]		nvarchar(30),
	feedback		nvarchar(4000),
	workDetails		nvarchar(4000),
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
	privateKey		nvarchar(50),
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


  --Insert data to table
  go
  insert into [admin] values('admin1', 'admin', 1),
						  ('admin2', 'admin', 0)
  go
  insert into [skills] values('Babysister','Baby or child caretaker',0),
							 ('Mason','A craftsman who builds in stone or brick otherwise known as masonry',0),
							 ('Carpenter','A person skilled at carpentry, the trade of cutting and joining timber in order to construct buildings or other structures.',0),
							 ('Gardener','Someone who is employed to cultivate or care for gardens.',0),
							 ('Painter','A worker who is employed to cover objects with paint',0),
							 ('Maid','A female servant or cleaner',0),
							 ('Driver','A person who drives a motorized vehicle, such as a car or a bus',0),
							 ('Fencer','A person who makes fences',0),
							 ('Mover','Someone that transports household or office goods from one location to another as an occupation',0),
							 ('Janitor','Someone who looks after the maintenance and cleaning of a public building',0),
							 ('Mechanic','A skilled worker capable of building or repairing machinery',0),
							 ('Waiter','A man whose occupation is to serve at table (as in a restaurant)',0),
							 ('Waitress','A woman who serves at a table, as in a restaurant',0),
							 ('Truck driver','Someone who drives a truck as an occupation',0),
							 ('Plumber','Someone that installs and repairs pipes and plumbing',0)
  --go
  --insert into [topic] values(1, '', '', '', 0),
  --                          (1, '', '', '', 0),
		--					(1, '', '', '', 0),
		--					(1, '', '', '', 0),
		--					(1, '', '', '', 0),
		--					(1, '', '', '', 0),
		--					(1, '', '', '', 0),
		--					(1, '', '', '', 0),
		--					(1, '', '', '', 0),
		--					(1, '', '', '', 0),
		--					(1, '', '', '', 0),
		--					(1, '', '', '', 0),
		--					(1, '', '', '', 0),
		--					(1, '', '', '', 0),
		--					(1, '', '', '', 0),
		--					(1, '', '', '', 0),
		--					(1, '', '', '', 0),
		--					(1, '', '', '', 0),
		--					(1, '', '', '', 0),
		--					(1, '', '', '', 0)
  go 
  insert into [login] values('f1sudoku', '123456', 'tannc2810@gmail.com', 'worker','09/07/2013', 0),
							('thanhshark', '123456', 'conthanh@gmail.com', 'customer','09/07/2013', 0),
							('kenshincho', '123456', 'kenshincho@gmail.com', 'general','09/07/2013', 0),
							('trongnhan', '123456', 'trongnhan@gmail.com', 'customer','09/07/2013', 0),
							('chieclacuoicung', '123456', 'vandang@gmail.com', 'worker','09/07/2013', 0 ),
							('codonmotvisao', '123456', 'myxuyen', 'worker','09/07/2013', 0 ),
							('choemmotconduong','123456', 'kimduyen@gmail.com', 'general','09/07/2013',0 ),
							('lenduongnhapngu', '123456', 'kiencuong@gmail.com', 'customer','09/07/2013', 0 ),
							('yeunuocaihonta', '123456', 'aiquoc@gmail.com', 'worker','09/07/2013', 0 ),
							('ronalut', '123456', 'vanut@gmail.com', 'worker','09/07/2013',0 ),
							('namvolam', '123456', 'hoangnam@gmail.com', 'general','09/07/2013',0 ),
							('truonghochuu', '123456', 'phuongdai@gmail.com', 'customer','09/07/2013',0 ),
							('lienhoa', '123456', 'bichtuyen@gmail.com', 'worker','09/07/2013',0 ),
							('thuykieu', '123456', 'thuykieu', 'worker','09/07/2013',0 ),
							('venmom', '123456', 'thiven@gmail.com', 'customer','09/07/2013', 0 ),
							('truongphi', '123456', 'nongtanh@gmail.com', 'worker','09/07/2013',0 ),
							('vinhlong', '123456', 'quangtan@gmail.com', 'general','09/07/2013',0 ),
							('chantinh', '123456', 'hoang@gmail.com', 'worker','09/07/2013', 0 ),
							('lamminh', '123456', 'lamminh@gmail.com', 'worker','09/07/2013', 0 ),
							('haivl', '123456', 'vanvui@gmail.com', 'worker','09/07/2013', 0 )

  go
  insert into [information] values('f1sudoku', 'Chi Tan', 'Nguyen', 1, '09/27/1989', '58 Hoa Vam, Thanh Yen A, U Minh Thuong District', 'Kieng Giang', 'Vietnam', '01666463664', '371462120', 'What is your name?', 'chitan', 'f1sudoku.jpg', 0),
								  ('kenshincho', 'Lam Son', 'Hong', 1, '05/01/1992', '123 No Trang Long street, Ward 14, District 1', 'Ho Chi Minh', 'Vietnam', '0938451245', '245145614', 'Who is my father?', 'chitan', 'kenshincho.jpg', 0),
								  ('thanhshark', 'Cong Thanh', 'Trinh', 1, '03/17/1988', '132 Tran Dai Nghia, Ward 6 Cam Ranh District', 'Nha Trang', 'Vietnam', '01221423215', '874452154', 'What is your name?', 'congthanh', 'thanhshark.jpg', 0 ),
								  ('trongnhan', 'Trong Nhan', 'Phan', 1, '12/02/1991', '135 Cach Mang Thang Tam, Ward 5, Ninh Kieu District', 'Can Tho', 'Vietnam', '0989303640', '321212546', 'How many people are there in your family?', 'conlaumoino', 'trongnhan.jpg', 0 ),
								  ('chieclacuoicung', 'Van Dang', 'Lam', 1, '08/25/1989', '12 Kenh 2, Trung Doan Ward U Minh Thuong District', 'Kien Giang', 'Vietnam', '0909691569', '365789149', 'What do you often do in your freetime?', 'listenning to the music', 'vandang.jpg', 0 ),
								  ('codonmotvisao', 'My Xuyen', 'Tran', 0, '03/15/1990', '15 Kenh 1, Trung Doan Ward U Minh Thuong District', 'Kien Giang', 'Vietnam', '01221423215', '874452154', 'What is your name?', 'myxuyen', 'myxuyen.jpg', 0 ),
								  ('choemmotconduong', 'Kim Duyen', 'Nguyen', 0, '09/15/1985', '58 Hoa Vam, Thanh Yen A, U Minh Thuong District', 'Kien Giang', 'Vietnam', '0939934044', '370145872', 'What is your name?', 'kimduyen', 'kimduyen.jpg', 0 ),
								  ('lenduongnhapngu', 'Kien Cuong', 'Chau', 1, '05/12/1990', '575 Cach Mang Thang Tam, Ward 14, District 10', 'Ho Chi Minh', 'Vietnam', '0922456654', '124215465', 'Who is the best in your mind?', 'it is mine!', 'kiencuong.jpg', 0 ),
								  ('yeunuocaihonta', 'Ai Quoc', 'Nguyen', 1, '03/15/1990', '124 Kha Van Can, Ward 6 Thu Duc District', 'Ho Chi Minh', 'Vietnam', '01234567896', '321654987', 'How can I Love you?', 'I do not know', 'aiquoc.jpg', 0 ),
								  ('ronalut', 'Van Ut', 'Trinh', 1, '07/12/1987', '456 Tran Van Dang, Ward 12, Binh Thanh District','Ho Chi Minh','Vietnam', '01688888021', '365842112', 'What is your favourite sport?', 'soccer', 'vanut.jpg', 0 ),
								  ('namvolam', 'Hoang Nam', 'Nguyen', 1, '02/20/1991', '789 Dao Duy Tu Ward 6 Tan Binh District', 'Ho Chi Minh', 'Vietnam', '0979797979', '371151618', 'Who is your girlfriend?', 'lam', 'hoangnam.jpg', 0 ),
								  ('truonghochuu', 'Phuong Dai', 'Tran', 1, '01/01/1987', '132 Hoang Hoa Tham, Tu Liem District', 'Ha Noi', 'Vietnam', '0979854457', '371885522', 'Are you gay?', 'Are you kidding me?', 'phuongdai.jpg', 0 ),
								  ('lienhoa', 'Bich Tuyen', 'Nguyen', 0, '05/05/1988', '30 Kenh 1, Trung Doan Ward U Minh Thuong District', 'Kien Giang', 'Vietnam', '0975411222', '371454412', 'What is your name?', 'bichtuyen', 'bichtuyen.jpg', 0 ),
								  ('thuykieu', 'Thuy Kieu', 'Le', 0, '12/02/1990', '987 Truong Cong Dinh, Ward 12 District 8', 'Ho Chi Minh', 'Vietnam', '0922123456', '213554787', 'when do you wake up in the morning?', 'six o`clock', 'thuykieu.jpg', 0 ),
								  ('venmom', 'Thi Ven', 'Le', 0, '03/17/1990', '145 Hoa Vam, Thanh Yen A, U Minh Thuong District', 'Kien Giang', 'Vietnam', '01234567895', '371474124', 'Are you OK?', 'OK thanks', 'thiven.jpg', 0 ),
								  ('truongphi', 'Nong Tanh', 'Tran', 1, '11/11/1991', '67 To Hien Thanh, Ward 14, District 10', 'Ho Chi Minh', 'Vietnam', '01234567894', '236985475', 'Will you married me?', 'never', 'nongtanh.jpg', 0 ),
								  ('vinhlong', 'Quang Tan', 'Le', 1, '04/25/1990', '140 Tran Dai Nghia, Ward 6 Cam Ranh District', 'Nha Trang', 'Vietnam', '0909222222', '745215457', 'How old are you?', 'do not dream so', 'quangtan.jpg', 0 ),
								  ('chantinh', 'Hoang', 'Ly', 1, '10/12/1986', '987 Cong Quynh, Pham Ngu Lao Ward, District 1', 'Ho Chi Minh', 'Vietnam', '01252132154', '625452125', 'What is your name?', 'congthanh', 'thanhshark.jpg', 0 ),
								  ('lamminh', 'Minh', 'Lam', 1, '03/03/1988', '54 Truong Chinh , Phan Van Tri Ward, Tan Binh District', 'Ho Chi Minh', 'Vietnam', '01221114445', '456123789', 'Do you know HKT band?', 'of course', 'lamminh.jpg', 0 ),
								  ('haivl', 'Van Vui', 'Le', 1, '10/23/1978', '32 Truong Chinh , Phan Van Tri Ward, Tan Binh District', 'Ho Chi Minh', 'Vietnam', '0989564524', '345621234', 'What is your name?', 'vanvui', 'vanvui.jpg', 0 )
  --go 
  --insert into [post] values(1, '', '', '', 1, 1, '', '', 0, 0),
		--				   (1, '', '', '', 1, 1, '', '', 0, 0),
		--				   (1, '', '', '', 1, 1, '', '', 0, 0),
		--				   (1, '', '', '', 1, 1, '', '', 0, 0),
		--				   (1, '', '', '', 1, 1, '', '', 0, 0),
		--				   (1, '', '', '', 1, 1, '', '', 0, 0),
		--				   (1, '', '', '', 1, 1, '', '', 0, 0),
		--				   (1, '', '', '', 1, 1, '', '', 0, 0),
		--				   (1, '', '', '', 1, 1, '', '', 0, 0),
		--				   (1, '', '', '', 1, 1, '', '', 0, 0),
		--				   (1, '', '', '', 1, 1, '', '', 0, 0),
		--				   (1, '', '', '', 1, 1, '', '', 0, 0),
		--				   (1, '', '', '', 1, 1, '', '', 0, 0),
		--				   (1, '', '', '', 1, 1, '', '', 0, 0),
		--				   (1, '', '', '', 1, 1, '', '', 0, 0),
		--				   (1, '', '', '', 1, 1, '', '', 0, 0),
		--				   (1, '', '', '', 1, 1, '', '', 0, 0),
		--				   (1, '', '', '', 1, 1, '', '', 0, 0),
		--				   (1, '', '', '', 1, 1, '', '', 0, 0),
		--				   (1, '', '', '', 1, 1, '', '', 0, 0)

  --go
  --insert into [attach] values(1, 1, '', 0),
		--					 (1, 1, '', 0),
		--					 (1, 1, '', 0),
		--					 (1, 1, '', 0),
		--					 (1, 1, '', 0),
		--					 (1, 1, '', 0),
		--					 (1, 1, '', 0),
		--					 (1, 1, '', 0),
		--					 (1, 1, '', 0),
		--					 (1, 1, '', 0),
		--					 (1, 1, '', 0),
		--					 (1, 1, '', 0),
		--					 (1, 1, '', 0),
		--					 (1, 1, '', 0),
		--					 (1, 1, '', 0),
		--					 (1, 1, '', 0),
		--					 (1, 1, '', 0),
		--					 (1, 1, '', 0),
		--					 (1, 1, '', 0),
		--					 (1, 1, '', 0) 
  
 go
 insert into [group] values ('thanhshark', 'Housework', 'The tasks, such as cleaning and cooking, that are performed in housekeeping', 0),
                            ('thanhshark', 'Outdoor Jobs', 'Working outdoor', 0),
							('thanhshark', 'Indoor Jobs', 'Working indoor', 0)

go
insert into [worker] values('f1sudoku', 'Unskilled worker', 'Waiter', 15000, 120000, 0),
						   ('chieclacuoicung', 'Bachelor of Engineering', 'Mechanic', 25000, 200000, 0),
						   ('yeunuocaihonta', 'Unskilled worker', 'Driver', 20000, 160000, 0),
						   ('ronalut', 'Unskilled worker', 'Painter', 20000, 160000, 0),
						   ('lienhoa', 'Unskilled worker', 'Waitress', 15000, 120000, 0),
						   ('thuykieu', 'Unskilled worker', 'Babysister', 20000, 160000, 0),
						   ('chantinh', 'Unskilled worker', 'Gardener', 15000, 120000, 0),
						   ('lamminh', 'Unskilled worker', 'Janitor', 20000, 160000, 0)
go
insert into [groupDetails] values(1, 6, 0),
								 (2, 7, 0),
                                 (2, 3, 0),
								 (3, 4, 0),
								 (3, 1, 0),
								 (3, 5, 0),
								 (3, 2, 0)
go
insert into [work] values('thanhshark', 'Waiter', '30 To Hien Thanh Street, Ward 14, District 10', 'Ho Chi Minh', 'Vietnam', '10/07/2013', '10/07/2014', '08:00', '16:00', 'openning', 'Go to work on time everyday', 'Order food or drink for customer', 15000, 120000, 0),	
				         ('kenshincho', 'Mechanic', '367 Bac Hai Street, Ward 6, Tan Binh District', 'Ho Chi Minh', 'Vietnam', '10/07/2013', '10/07/2015', '09:00', '17:00', 'openning', 'Go to work on time, hard working, easy going', 'fixing the card', 25000, 200000, 0),
						 ('trongnhan', 'Waitress', '75 Hai Ba Trung Street, Ward 7, Dong Da District', 'Ha Noi', 'Vietnam','10/07/2013', '10/07/2014', '14:00', '22:00', 'openning', 'Go to work on time', 'Order food or drink for customer', 15000, 120000, 0),
						 ('choemmotconduong', 'Driver', '50 Ba Thang Hai, An Khanh Ward, Ninh Kieu District','Can Tho' ,'Vietnam', '10/07/2013', '10/07/2013', '16:00', '00:00', 'openning', 'Go to work on time, no drink alcohol when driving', 'Drive a bus', 20000, 160000, 0)
					
   go
   insert into [workDaily] values(1, 1, '10/07/2013', '10/07/2014', '8H-16H', '8H-16H', '8H-16H', '8H-16H', '8H-16H', '8H-16H', '8H-16H', 0),
								 (2, 2, '10/07/2013', '10/07/2014', '9H-17H', '9H-17H', '9H-17H', '9H-17H', '9H-17H', '9H-17H', '9H-17H', 0),
								 (3, 5, '10/07/2013', '10/07/2014', '14H-22H', '14H-22H', '14H-22H', '14H-22H', '14H-22H', '14H-22H', '14H-22H', 0),
								 (4, 3, '10/07/2013', '10/07/2014', '16H:00H', '16H:00H', '16H:00H', '16H:00H', '16H:00H', '16H:00H', '16H:00H', 0)
   go 
   insert into [mediaWork] values (1, 'waiter.jpg', 0),
								  (1, 'mechanic.jpg', 0),
								  (1, 'waitress.jpg', 0),
								  (1, 'driver.jpg', 0)
--go
--insert into [ratingType] values(1, '', 0),
--							   (1, '', 0),
--							   (1, '', 0),
--							   (1, '', 0),
--							   (1, '', 0)

--go
--insert into [rating] values(1, 1, '', 0),
--						   (1, 1, '', 0),
--						   (1, 1, '', 0),
--						   (1, 1, '', 0),
--						   (1, 1, '', 0),
--						   (1, 1, '', 0),
--						   (1, 1, '', 0),
--						   (1, 1, '', 0),
--						   (1, 1, '', 0),
--						   (1, 1, '', 0),
--						   (1, 1, '', 0),
--						   (1, 1, '', 0),
--						   (1, 1, '', 0),
--						   (1, 1, '', 0),
--						   (1, 1, '', 0),
--						   (1, 1, '', 0),
--						   (1, 1, '', 0),
--						   (1, 1, '', 0),
--						   (1, 1, '', 0),
--						   (1, 1, '', 0)			

--go
--insert into [reportType] values(1, '', 0),
--							   (1, '', 0),
--							   (1, '', 0),
--							   (1, '', 0),
--							   (1, '', 0)

--go
--insert into [report] values(1, 1, 1, '', 0),
--						   (1, 1, 1, '', 0),
--						   (1, 1, 1, '', 0),
--						   (1, 1, 1, '', 0),
--						   (1, 1, 1, '', 0),
--						   (1, 1, 1, '', 0),
--						   (1, 1, 1, '', 0),
--						   (1, 1, 1, '', 0),
--						   (1, 1, 1, '', 0),
--						   (1, 1, 1, '', 0)