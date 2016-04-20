package hu.codingmentor.mobile.webshop.dto;

public class ExceptionDTO {

    private String exceptionType;

    private String errorMessage;

    public ExceptionDTO() {
        //default constructor
    }

    public ExceptionDTO(String exceptionType, String errorMessage) {
        this.exceptionType = exceptionType;
        this.errorMessage = errorMessage;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
