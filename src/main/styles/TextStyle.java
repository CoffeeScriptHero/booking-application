package main.styles;

public enum TextStyle {
    RESET("\033[0m"),
    RED("\u001B[31m"),
    GREEN("\033[0;32m"),
    BLACK_BOLD("\033[1;30m");

    private final String style;

    TextStyle(String style) {
        this.style = style;
    }

    public String getStyle() {
        return this.style;
    }
}
