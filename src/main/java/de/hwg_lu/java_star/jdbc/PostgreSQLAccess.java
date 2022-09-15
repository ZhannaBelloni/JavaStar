package de.hwg_lu.java_star.jdbc;

public class PostgreSQLAccess extends JDBCAccess {

	public PostgreSQLAccess() throws NoConnectionException {
		super();
	}
	public void setDBParms(){
		dbDrivername = "org.postgresql.Driver";
		// dbURL        = "jdbc:postgresql://143.93.200.243:5432/BWUEBDB";
		// dbUser       = "user1";
		// dbPassword   = "pgusers";
		dbURL        = "jdbc:postgresql://localhost:5432/BWUEBDB";
    	dbUser       = "postgres";
		dbPassword   = "belloni";
		dbSchema     = "bwi420_6xxxxx"; // hier Matrikelnummer eintragen
		
	}
	
	public String getSchema() {
		return this.dbSchema;
	}
	
	public static void main(String[] args) throws NoConnectionException { 
		new PostgreSQLAccess().getConnection();
	}
}
