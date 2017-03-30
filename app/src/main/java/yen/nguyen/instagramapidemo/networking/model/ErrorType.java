package yen.nguyen.instagramapidemo.networking.model;

import java.text.MessageFormat;

import yen.nguyen.instagramapidemo.utils.InstagramApplication;

/**
 * Created by yen.nguyen on 3/30/17.
 */
public enum ErrorType {
    UNEXPECTED("UNEXPECTED", "UNEXPECTED"),
    NO_NETWORK("NO_NETWORK", "NO_NETWORK");

    private final int messageResourceId;
    private final String message;
    private final String code;

    ErrorType(String code, int messageResourceId) {
        this.code = code;
        this.messageResourceId = messageResourceId;
        this.message = null;
    }

    ErrorType(String code, String message) {
        this.code = code;
        this.message = message;
        this.messageResourceId = 0;
    }

    public static ErrorType fromErrorCode(String errorCode) {
        for (ErrorType ErrorTypeType : ErrorType.values()){
            if (ErrorTypeType.getCode().equals(errorCode)){
                return ErrorTypeType;
            }
        }

        return UNEXPECTED;
    }

    public String getCode() {
        return code;
    }

    public String getErrorMessage(){
        if (message != null) {
            return message;
        } else {
            return InstagramApplication.getInstance().getApplicationContext().getString(messageResourceId);
        }
    }

    @Override
    public String toString() {
        return MessageFormat.format("ACError [{0}:{1}]", getCode(), getErrorMessage());
    }
}