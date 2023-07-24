package com.example.demo;

public class User {
    private String username;
    private String email;
    private String storeName;

    private String password;

    public User() {

    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }

    public void setStoreName(String store)
    {
        this.storeName = store;
    }

    public String getStoreName()
    {
        return storeName;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }

}
