package org.itstep;

import java.io.*;

public class User implements Serializable, Externalizable {
    private String login;
    private String password;
    private String fullName;

    public User() {

    }

    User(String login, String password, String fullName) {
        this.login = login;
        this.password = password;
        this.fullName = fullName;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "User{" +
                "fullName: " + fullName +
                ", login: " + login +
                ", password: " + password +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == (User) o) return true;
        if (o == null || getClass() != o.getClass()) return false;
//        User user = (User) o;
        return this.login.equals(((User) o).getLogin()) && this.password.equals(((User) o).getPassword());
//                Objects.equals(login, users.login) && Objects.equals(password, users.password) && Objects.equals(firstName, users.firstName) && Objects.equals(lastName, users.lastName);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(login);
        out.writeObject(password);
        out.writeObject(fullName);
//        out.writeObject(lastName);
//        out.writeUTF(login);
//        out.writeObject(password);
//        out.writeUTF(firstName);
//        out.writeObject(lastName);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        login = (String) in.readObject();
        password = (String) in.readObject();
        fullName = (String) in.readObject();
    }
}
