USE AlignProDB;

/* Create 3 project*/
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
(SubTaskName, StartDate, EndDate, EstimatedTime, SubTaskDescription, SkillRequirement, TaskID) VALUES ('Build a gun', '2024-11-23', '2024-11-24', 43, 'Follow the attached instructions on gun building', 'Developer',1);
INSERT INTO SubTask
(SubTaskName, StartDate, EndDate, EstimatedTime, SubTaskDescription, SkillRequirement, TaskID) VALUES ('Finish SQL Scripts', '2024-11-23', '2024-11-23', 600, 'Before we can write Crud codes, we need SQL database set-up for testing', 'Developer',1);

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

