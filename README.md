# USC Missed Connections Deployment Documentation
By Eric Cheng, Raymond Kuan, Tim Lin, Cate Jung, Melanie Toh, and Ronak Pai

### USC Missed Connections Project
Students often meet people they find interesting but lack the courage to spark a longer conversation that might blossom into a genuine friendship. Our app aims to give students a chance to find connections they might have otherwise missed. Our anonymous message board allows students to post safely about people they wish they could connect to. There's comment systems, likes, and a daily recommended feed, all designed to maximize chances students have to connect.

# Setup the Database locally:

Open MySQLWorkbench

Click Server -> Data Import.   
In Import from Disk tab,  
 - select import from Self-Contained File
 - find the file ServerSetup.sql
 - Select option at the bottom: Dump Structure Only
 - Click start import
 - Close the import tab on MySQLWorkbench

Click Server -> Data Import.   
In Import from Disk tab,  
 - select import from Self-Contained File
 - find file SampleDataPopulate.sql
 - Select option at the bottom: Dump Data Only
 - Select default target schema: uscmissed (NEED TO CLOSE TAB AND SELECT DATA IMPORT AGAIN)
 - Click start import



# Running the Webpage:
- After the setup of the database, the homepage can be accessed at http://localhost:8079/A2/homepage.jsp
- Apache Tomcat Servers must be attached prior to website execution. This project uses Apache Tomcat 9.0 
- Jar files must be attached (should be already attached when project is downloaded). Ensure both javax.servlets.jar and well as the mysql_connector.jar are both attached.
- Run the homepage via the Tomcat Server.


## Multithreading:
- Connection to remote mySql servlet is required.    
- To do so, go to the data package and the Database.java file. Replace lines 31 to 35 with the following
```java
public static String DRIVER = "com.mysql.cj.jdbc.Driver";
public static String ADDRESS = "jdbc:mysql://remotemysql.com:3306/iMErdiDE0y";
public static String USER = "iMErdiDE0y";
public static String PASSWORD = "kILr3e0bxa";
 ```
