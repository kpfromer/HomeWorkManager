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
