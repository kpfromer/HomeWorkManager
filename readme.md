#Homework Manager
This a website that acts as a todo list site and sends you reminds of due homework.
I used a mysql database and a tomcat server. 

Create file /opt/config/settings.properties
with 
```properties
#database
url=#url
login=#login
password=#password
#Secrity
hash=#hash
```
Edit /var/lib/tomcat8/conf/server.xml and add underneath
WARNING editing server.xml is not recommended
```xml
<Context path="" docBase="HomeWorkManager-1.0-SNAPSHOT" debug="0" reloadable="true"/>
```

Create a table called ListItems
```sql
CREATE TABLE ListItems
(
    ListItemID INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    ListID INT(11) NOT NULL,
    ListText VARCHAR(150) NOT NULL,
    ListItemDone INT(11) NOT NULL,
    ListItemPosition INT(11) NOT NULL
);
```
Create another new table called users
```sql
CREATE TABLE users
(
    ID INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    Username VARCHAR(255) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Email VARCHAR(255) NOT NULL
);
```