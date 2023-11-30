package Client_Side.User_Display.Config;

public enum InfoBlock {
    SERVER_UNAVAILABLE(new String[] {
            "Connection to the server failed",
            "* Check of the correctness of the data",
            "* Or try again later"
    });

    String[] exceptionLines;

    InfoBlock(String[] exception) {
        this.exceptionLines = exception;
    }

    public String[] getExceptionLines() {
        return exceptionLines;
    }
}
