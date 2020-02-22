package com.cpy.saas_test.element;

public class User {
    private String username;
    private String password;
    private String phone;
    private String URL;
    private String Id;
    private String Email;
    private String Introg;  //备注

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", URL='" + URL + '\'' +
                ", Id='" + Id + '\'' +
                ", Email='" + Email + '\'' +
                ", Introg='" + Introg + '\'' +
                '}';
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getIntrog() {
        return Introg;
    }

    public void setIntrog(String introg) {
        Introg = introg;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }





}
