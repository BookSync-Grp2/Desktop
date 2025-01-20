package poo.booksync.model;
public enum View {
    HOME("home"),
    LOANS("loans"),
    CLIENT("client");

    private final String value;

    View(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}