# ================================================= #
#                    SETUP                          #
# ================================================= #

-------------------
| JDBC CONNECTION  |
--------------------
change connection parameter in 
     /JavaStarWebsite/src/main/java/de/hwg_lu/java_star/jdbc/PostgreSQLAccess.java

-------------------
| PREPARE DATA     |
--------------------

Prepare Database:

Run: 
    /JavaStarWebsite/src/main/java/de/hwg_lu/java_star/admin/AdminDBCleaner.java
to delete all data in database

Run: 
	/JavaStarWebsite/src/main/java/de/hwg_lu/java_star/admin/AdminDB.java
to add the needed data in database.

You need to modify the value in AdminDB.java of the variable pathToData to point to
the directory /JavaStarWebsite/data.


-------------------
| RUN TOMCAT       |
--------------------

Run tom-cat:
If class not found exception for org.postgres.drivers is thrown, 
then add the postgres-driver jar file in lib/ directory of tom-cat

 
-------------------
| TODOs            |
--------------------

[] improve graphics
[] use dbSchema SCHEMA instead of its value in some queries
[] add more exercise 
[] add statistics for user
[] add page with all users and they rating
[] add theory part of the course  
[] add home button at all pages
[] add lateral navigation
[] improve error message by checking exercises and escape html code.

