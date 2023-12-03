package Client_Side.Display.Config;

public enum Blocks_Exception {
    SERVER_UNAVAILABLE(new String[] {
            "Connection to the server failed",
            "* Check of the correctness of the data",
            "* Or try again later"
    });

    String[] exceptionLines;

    Blocks_Exception(String[] exception) {
        this.exceptionLines = exception;
    }

    public String[] getExceptionLines() {
        return exceptionLines;
    }
}
