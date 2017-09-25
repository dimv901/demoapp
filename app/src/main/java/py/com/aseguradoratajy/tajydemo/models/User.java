package py.com.aseguradoratajy.tajydemo.models;

/**
 * Created by Diego on 9/20/2017.
 */

public class User {

    private String name;
    private String lastName;
    private String document;
    private String password;

    public static User getInstance() {
        return new User("DIEGO", "MALDONADO", "3336430", "1234");
    }

    public User(String name, String lastName, String document, String password) {
        this.name = name;
        this.lastName = lastName;
        this.document = document;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
