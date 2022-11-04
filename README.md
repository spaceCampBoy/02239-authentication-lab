# 02239-authentication-lab

Java Authentication Lab for 02239 Data Security course
This project is client/server application using Java RMI and implemented authentication mechanism (login with credentials, session token, storing hashed passwords in public file)


To run the project
1) Run Server.java
2) Run Client.java
3) From clients terminal following commands are available:
  login <username> <password>           // login to system
  print <filename> <printerName>        // prints file filename on the specified printer
  queue <printerName>                   // lists the print queue for a given printer
  topQueue<printerName> <jobNumber>     // moves job to the top of the queue
  start                                 // starts the print server
  stop                                  // stops the print server
  restart                               // stops the print server, clears the print queue and starts the print server again
  status <printerName>                  // prints status of printer on the user's display
  setConfig <parameterName> <value>     // sets the parameter to value
  readConfig <parameterName>            // prints the value of the parameter on the user's display





user credentials for test:
admin : admin
user1 : user1

printer names:
- printer1
- printer2

Printing service methods are accessible only for authenticated users
