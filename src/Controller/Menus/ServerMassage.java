package Controller.Menus;

import javax.lang.model.type.ErrorType;

public class ServerMassage {
    public enum Type{
        Error, Accept;
    }
    public enum ErrorType{
        LogInFailed, InvalidPasswordForSignUp, InvalidUsernameForSignUp;
    }

    public ServerMassage(Type type, ErrorType errorType){
        this.type = type;
        this.errorType = errorType;
    }

    private Type type;
    private ErrorType errorType;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }
}
