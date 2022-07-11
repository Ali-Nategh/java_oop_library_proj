package com.company;

class User {
    private String username;
    private String password;

    public User(String user, String pass) {
        this.username = user;
        this.password = pass;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
