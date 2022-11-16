# 02239-authentication-lab

Java Authentication Lab for 02239 Data Security course. <br/>
This project is client/server application using Java RMI + implemented authentication mechanism (login with credentials, session token, storing hashed passwords in public file)


To run the project
1) Run Server.java
2) Run Client.java
3) From clients terminal following commands are available:<br />
  - login 	&lt;username&gt; &lt;password&gt;        // login to system <br />
  - print &lt;filename&gt; &lt;printerName&gt;        // prints file filename on the specified printer <br />
  - queue &lt;printerName&gt;                    // lists the print queue for a given printer <br />
  - topQueue &lt;printerName&gt; &lt;jobNumber&gt;         // moves job to the top of the queue <br />
  - start                                    // starts the print server <br />
  - stop                            // stops the print server <br />
  - restart                          // stops the print server, clears the print queue and starts the print server again <br />
  - status &lt;printerName&gt;                // prints status of printer on the user's display <br />
  - setConfig &lt;parameterName&gt; &lt;value&gt;         // sets the parameter to value <br />
  - readConfig &lt;parameterName&gt;           // prints the value of the parameter on the user's display <br />
 
 
 <br />
 <br />

user credentials for test: <br />
admin : admin <br />
user1 : user1 <br />

printer names: <br />
printer1 <br />
printer2 <br />
 <br />
Printing service methods are accessible only for authenticated users
