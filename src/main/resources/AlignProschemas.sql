CREATE DATABASE IF NOT EXISTS AlignProDB;
USE AlignProDB;

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