# AuruProject
Please follow the instruction to run the project in your machine
1) Download and Import project in your workspace
2) In your machine ,Need to run MySql server running
3) Run the below create script to create the table in SQL server
          Sript : create table AURUEVENT
          (
          	EVENTID INTEGER primary key,
              EVENTNAME NVARCHAR(50),
              EVENTDATE DATE,
              EVENTTYPE NVARCHAR(20),
              BASE64FILE LONGTEXT,
              WEBLINK NVARCHAR(500)
          );
4) Run the project Run-> Java Application
5) Open the any brower Chrome or Edge and enter "http://localhost:8080/swagger-ui.html"
6) Open the swagger -> controller -> addEvent
7) Paste the given JSON into the request {
  "eventDate": "2024-09-20",
  "eventId": 0,
  "eventName": "ColdPlay",
  "eventType": "COncert",
  "fileLocation": "C:\\Users\\pawar\\Desktop\\GL_Issue.png",
  "weblink": "http:\\hello.html"
}
8) After clicking on execute Message will come Data saved succesfully
