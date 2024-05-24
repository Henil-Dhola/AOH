package com.example.application_animation;

public class User {
    public String fullname,mobile,email;
    public User(){

    }

    public User(String fullname, String mobile, String email) {
        this.fullname = fullname;
        this.mobile = mobile;
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
