package exceptions;

public enum Message {
    INVALID_AGE("wrong age input!"),
    INVALID_NATIONAL_CODE("wrong national code!"),
    INVALID_PASSWORD("wrong password!"),
    INVALID_AMOUNT(" not enough cash!"),
    INVALID_USERNAME("wrong username!"),
    INVALID_USERNAME_OR_PASS("wrong username or password!");

    private String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
