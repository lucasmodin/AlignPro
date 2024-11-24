/*this is to drop relation tables for projects */
DROP TABLE IF EXISTS SubTask_Employee;
DROP TABLE IF EXISTS Task_Employee;
DROP TABLE IF EXISTS Employee_Skill;
DROP TABLE IF EXISTS PMUser_Project;

/*this is to drop tables for projects */
DROP TABLE IF EXISTS SubTask;
DROP TABLE IF EXISTS Task;
DROP TABLE IF EXISTS SubProject;
DROP TABLE IF EXISTS Project;

/*this is to drop tables for Employees */
DROP TABLE IF EXISTS Employee;
DROP TABLE IF EXISTS Skills;

/*this is to drop tables for Employees */
DROP TABLE IF EXISTS PMUser;

CREATE TABLE Project (
	ProjectID INT AUTO_INCREMENT PRIMARY KEY,
    ProjectName VARCHAR(100) NOT NULL,
    StartDate DATE NOT NULL,
    Deadline DATE NOT NULL,
    TotalSumTime INT,
    ProjectDescription VARCHAR(100) NOT NULL
);

CREATE TABLE SubProject(
	SubProjectID INT AUTO_INCREMENT PRIMARY KEY,
    SubProjectName VARCHAR(100) NOT NULL,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    SumTime INT,
    SubProjectDescription VARCHAR(100) NOT NULL,
    ProjectID INT,
    CONSTRAINT fk_ProjectID FOREIGN KEY (ProjectID) REFERENCES Project(ProjectID) ON DELETE CASCADE
);

CREATE TABLE Task(
	TaskID INT AUTO_INCREMENT PRIMARY KEY,
	TaskName VARCHAR(100) NOT NULL,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    EstimatedTime INT NOT NULL,
    TaskDescription VARCHAR(100) NOT NULL,
    SkillRequirement VARCHAR(100) NOT NULL,
    SubProjectID INT,
    CONSTRAINT fk_SubProjectID FOREIGN KEY (SubProjectID) REFERENCES SubProject(SubProjectID) ON DELETE CASCADE
);

CREATE TABLE SubTask(
	SubTaskID INT AUTO_INCREMENT PRIMARY KEY,
    SubTaskName VARCHAR(100) NOT NULL,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    EstimatedTime INT NOT NULL,
    SubTaskDescription VARCHAR(100) NOT NULL,
    SkillRequirement VARCHAR(100) NOT NULL,
	TaskID INT,
    CONSTRAINT fk_TaskID FOREIGN KEY (TaskID) REFERENCES Task(TaskID) ON DELETE CASCADE
);

CREATE TABLE Employee(
	EmployeeID INT AUTO_INCREMENT PRIMARY KEY,
    EmployeeName VARCHAR(100) NOT NULL
);

CREATE TABLE Skills(
	SkillID INT AUTO_INCREMENT PRIMARY KEY,
    SkillName VARCHAR(100) NOT NULL
);

CREATE TABLE PMUser(
	PMUserID INT AUTO_INCREMENT PRIMARY KEY,
    FullName VARCHAR(100) NOT NULL,
    Mail VARCHAR(100) NOT NULL,
    PMPassword VARCHAR(100) NOT NULL
);

CREATE TABLE SubTask_Employee(
	SubTaskID INT,
    EmployeeID INT,
    FOREIGN KEY (SubTaskID) REFERENCES SubTask(SubTaskID) ON DELETE CASCADE,
    FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID) ON DELETE CASCADE
);

CREATE TABLE Task_Employee(
	TaskID INT,
    EmployeeID INT,
    FOREIGN KEY (TaskID) REFERENCES Task(TaskID) ON DELETE CASCADE,
    FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID) ON DELETE CASCADE
);

CREATE TABLE Employee_Skill(
    EmployeeID INT,
    SkillID INT,
    FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID) ON DELETE CASCADE,
    FOREIGN KEY (SkillID) REFERENCES Skills(SkillID) ON DELETE CASCADE
);

CREATE TABLE PMUser_Project(
	PMUserID INT,
    ProjectID INT,
    FOREIGN KEY (PMUserID) REFERENCES PMUser(PMUserID) ON DELETE CASCADE,
    FOREIGN KEY (ProjectID) REFERENCES Project(ProjectID) ON DELETE CASCADE
);

INSERT INTO Project
(ProjectName, StartDate, Deadline, TotalSumTime, ProjectDescription) VALUES ('Project Kea', '2024-11-20', '2024-12-18', 0 ,'This is a project for testing');
INSERT INTO Project
(ProjectName, StartDate, Deadline, TotalSumTime, ProjectDescription) VALUES ('Project test2', '2023-09-01', '2025-11-08', 0 ,'Another empty test project');
INSERT INTO Project
(ProjectName, StartDate, Deadline, TotalSumTime, ProjectDescription) VALUES ('SQL Script Project', '2024-11-22', '2024-11-23',240 ,'Project about writing SQL Scripts');

/* Create 2 PM Managers*/
INSERT INTO PMUser
(FullName, Mail, PMPassword) VALUES ('Lucas Modin', 'Lucas@Kea.dk','Lucas1234');
INSERT INTO PMUser
(FullName, Mail, PMPassword) VALUES ('Rasmus CSS', 'Rasmus@Kea.dk','Rasmus1234');

/* Links 2 PM Managers to 3 projects*/
INSERT INTO PMUser_Project (PMUserID, ProjectID) VALUES (1,1), (1,3), (2,2);

/* Creats 3 sub_projects for project 1 and 3*/
INSERT INTO SubProject
(SubProjectName, StartDate, EndDate, SumTime, SubProjectDescription, ProjectID) VALUES ('Workd day and night','2024-11-23', '2024-11-24', 0, 'This should be linked to project Kea', 1);
INSERT INTO SubProject
(SubProjectName, StartDate, EndDate, SumTime, SubProjectDescription, ProjectID)  VALUES ('Project SQL KEA', '2024-12-01', '2024-12-03', 0, 'Conencted to project Kea', 1);
INSERT INTO SubProject
(SubProjectName, StartDate, EndDate, SumTime, SubProjectDescription, ProjectID)  VALUES ('Scipting all the time', '2024-12-01', '2024-12-03', 0, 'Conencted to project SQL Scripts', 3);

/* Creats 2 task for sub_project 1 and 1*/
INSERT INTO Task
(TaskName, StartDate, EndDate, EstimatedTime, TaskDescription, SkillRequirement, SubProjectID) VALUES ('RandomTask 42', '2024-11-23', '2024-11-24', 37 ,'This task have an estimate and a skill requirement', 'Developer', 1);
INSERT INTO Task
(TaskName, StartDate, EndDate, EstimatedTime, TaskDescription, SkillRequirement, SubProjectID) VALUES ('Order 66', '2009-01-01', '2024-12-18', 500 ,'From star wars we have to execute order 66', 'Cost Controller', 1);

/* Create 3 Subtask for Task 1 and 1 and 1 */
INSERT INTO SubTask
(SubTaskName, StartDate, EndDate, EstimatedTime, SubTaskDescription, SkillRequirement) VALUES ('Build a gun', '2024-11-23', '2024-11-24', 43, 'Follow the attached instructions on gun building', 'Developer');
INSERT INTO SubTask
(SubTaskName, StartDate, EndDate, EstimatedTime, SubTaskDescription, SkillRequirement) VALUES ('Finish SQL Scripts', '2024-11-23', '2024-11-23', 600, 'Before we can write Crud codes, we need SQL database set-up for testing', 'Developer');

/* Creat list of Skills */
INSERT INTO Skills
(SkillName) VALUES ('Developer'), ('Cost Controller'), ('Project Manager');

/* Creat 2 Employees */
INSERT INTO Employee
(EmployeeName) VALUES ('Lars Larsen'), ('Kim Møller'), ('Frederik Rosenborg');

/* Give my 3 Employees some skills */
INSERT INTO Employee_Skill
(EmployeeID, SkillID) VALUES (1,1), (1,3), (2,1), (2,2), (2,3),(3,1);

/* Link them to a SubTask */
INSERT INTO SubTask_Employee
(SubTaskID, EmployeeID) VALUES (1,1), (2,3);

/* Add Kim Møller til Task */
INSERT INTO Task_Employee
(TaskID, EmployeeID) VALUES (2,1), (2,2);
