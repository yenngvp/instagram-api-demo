package yen.nguyen.instagramapidemo.networking.common;

/**
 * Created by yen.nguyen on 3/30/17.
 */
public class AppException extends Exception {

    public ErrorType errorType;
    public String errorMessage;

    public AppException(ErrorType errorType) {
        this.errorType = errorType;
    }

    public AppException(ErrorType errorType, String errorMessage) {
        this.errorType = errorType;
        this.errorMessage = errorMessage;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    public String getErrorMessage() {
        return errorType.getErrorMessage();
    }
}
