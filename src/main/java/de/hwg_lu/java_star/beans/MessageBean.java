package de.hwg_lu.java_star.beans;


public class MessageBean {

	String infoMessage;
	String actionMessage;
	
	public MessageBean() {
		this.setGeneralWelcome();
		//this.setRegistrationWelcome();
	}
	
	public void setGeneralWelcome(){
		this.setInfoMessage("Willkommen am BW4S-Portal");
		this.setActionMessage("bitte melden Sie sich an");
	}
	public void setRegistrationWelcome(){
		this.setInfoMessage("Willkommen am BW4S-Portal");
		this.setActionMessage("bitte registrieren Sie sich");
	}
	public void setLoginSuccessful(String userid){
		this.setInfoMessage("Willkommen am BW4S-Portal, " + userid);
		this.setActionMessage("bitte waehlen Sie eine Anwendung");
	}
	public void setLoginFailed(){
		this.setInfoMessage("Ihre Anmeldung ist fehlgeschlagen");
		this.setActionMessage("bitte versuchen Sie es noch einmal");
	}
	public void setLoggedOut(){
		this.setInfoMessage("Sie haben sich abgemeldet");
		this.setActionMessage("bitte melden Sie sich wieder an");
	}
	public void setNotLoggedIn(){
		this.setInfoMessage("Sie sind nicht angemeldet");
		this.setActionMessage("bitte melden Sie sich an");
	}
	public void setAccountAngelegt(String userid){
		this.setInfoMessage("Account " + userid + " angelegt");
		this.setActionMessage("Gehen Sie jetzt zur Anmeldung");
	}
	public void setAccountExists(String userid){
		this.setInfoMessage("Account " + userid + " existiert bereits");
		this.setActionMessage("Benutzen Sie bitte einen anderen Nickname");
	}
	public void setNoJDBCConnection(){
		this.setInfoMessage("keine Datenbankverbindung");
		this.setActionMessage("bitte kontaktieren Sie Ihren Administrator");
	}
	public void setDBError(){
		this.setInfoMessage("Datenbankfehler");
		this.setActionMessage("bitte kontaktieren Sie Ihren Administrator");
	}
	public void setAnyError(){
		this.setInfoMessage("Es ist ein Fehler aufgetreten");
		this.setActionMessage("bitte kontaktieren Sie Ihren Administrator");
	}
	public void setAttributeTooLong(
							String attributeName,
							String attributeValue,
							int maxLength,
							int realLength
				)
	{
		this.setInfoMessage("Der Wert f�r " + attributeName + " ist zu lang, erlaubt sind " + maxLength + " Zeichen");
		this.setActionMessage("Bitte passen Sie Ihre Werte an");
	}
	
	
	
	
	
	public String getInfoMessage() {
		return infoMessage;
	}
	public void setInfoMessage(String infoMessage) {
		this.infoMessage = infoMessage;
	}
	public String getActionMessage() {
		return actionMessage;
	}
	public void setActionMessage(String actionMessage) {
		this.actionMessage = actionMessage;
	}

}