package models;

public class User {
    private final long id;
    private String name;
    private String login;
    private String password;
    private boolean isAdmin;

    public User(long ID, String Name, String Login, String Password, boolean IsAdmin) {
        this(ID, Name, Login, Password);
        this.isAdmin = IsAdmin;
    }

    public User(long ID, String Name, String Login, String Password) {
        this.id = ID;
        this.name = Name;
        this.login = Login;
        this.password = Password;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
