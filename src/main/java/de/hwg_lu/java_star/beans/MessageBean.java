package de.hwg_lu.java_star.beans;

public class MessageBean {

    String infoMessage;
    String actionMessage;
    boolean isWithError = false;

    public MessageBean() {}

    public void setLoginFailed() {
        setWithError(true);
        this.setInfoMessage("Username or password are incorrect");
        this.setActionMessage("try again or register");

    }
    
    public void setLoginSuccessful(String userid) {
        setWithError(false);
        this.setInfoMessage("Login succesful for " + userid);
        this.setActionMessage("have fun");
    }

    public void setLoginOnFailed() {
        setWithError(true);
        this.setInfoMessage("Username or email exsist already");
        this.setActionMessage("try again or register with another username and password");

    }

    public void setNoJDBCConnection() {
        this.setWithError(true);
        this.setInfoMessage("Problem connecting to the database");
        this.setActionMessage("Contact the administartor");
    }

    public void setDBError() {
        this.setWithError(true);
        this.setInfoMessage("Problem connecting to the database");
        this.setActionMessage("Contact the administartor");
    }

    public void setCommentIsTooLong() {
        this.setWithError(true);
        this.setInfoMessage("The comment is too long");
        this.setActionMessage("thanks for your comment, please try to be more syntetic!");
    }

    public void setCommentIsEmpty() {
        this.setWithError(true);
        this.setInfoMessage("The comment is empty");
        this.setActionMessage("thanks for your comment, please try to be more esplicit!");
    }

    public void setErrorInsertingComment() {
        this.setWithError(true);
        this.setInfoMessage("there was a problem to insert your comment ");
        this.setActionMessage("contact the administrator.");
    }

    public void setRepeatedPasswordMismatch() {
        this.setWithError(true);
        this.setInfoMessage("passwords mismatch");
        this.setActionMessage("try again");
    }

    public void setFieldIsEmpty(String field) {
        this.setWithError(true);
        this.setInfoMessage("field " + field + " cannot be empty.");
        this.setActionMessage("try again");
    }

    public void emailIsInvalid() {
        this.setWithError(true);
        this.setInfoMessage("email is not valid");
        this.setActionMessage("try again");
    }

    public void setAttributeTooLong(String attributeName, int maxLength) {
        this.setWithError(true);
        this.setInfoMessage("The field " + attributeName + " can be only " + maxLength + " characters long");
        this.setActionMessage("use shorter inputs, please");
    }

    public boolean isWithError() {
        return isWithError;
    }

    public void setWithError(boolean isWithError) {
        this.isWithError = isWithError;
        this.infoMessage = "";
        this.actionMessage = "";
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