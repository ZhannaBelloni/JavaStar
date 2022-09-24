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
	/JavaStarWebsite/src/main/java/de/hwg_lu/java_star/admin/AdminDB.java <path/to/data/directory>
to add the needed data in database.


-------------------
| RUN TOMCAT       |
--------------------

Run tom-cat:
If class not found exception for org.postgres.drivers is thrown, 
then add the postgres-driver jar file in lib/ directory of tom-cat
