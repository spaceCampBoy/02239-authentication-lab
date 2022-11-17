# 02239-authentication-lab

Java Authentication Lab for 02239 Data Security course. <br/>
This project is client/server application using Java RMI + implemented authentication mechanism (login with credentials, session token, storing hashed passwords in public file)

<br />
<br />
THIS BRANCH HAS RBAC IMPLEMENTED (see below)

<br />
<br />

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

RBAC:
rbac-descr.txt contains roles and operations available to a role "$role:$operation1,operation2"
user-roles.txt contains usernames and assigned roles "$username:$role". Only one role for one username is possible

user credentials for test ($username : $password) : <br />
Alice : alice <br />
Bob : bob <br />
Cecilia: cecilia <br />
David : david <br />
Erica : erica <br />
Fred : fred <br />
George : george <br />

printer names: <br />
printer1 <br />
printer2 <br />
 <br />
Printing service methods are accessible only for authenticated users
