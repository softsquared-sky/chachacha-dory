package com.example.chachacha_dory;

public class UserClass {
    private String userId, userPw, userPw2, userName, userEmail;
    private int userAge, userGender;

    public UserClass(String userId, String userPw){
        this.userId = userId;
        this.userPw = userPw;
    }
    public UserClass(String userId, String userPw, String userPw2, String userName, String userEmail, int userAge, int userGender){
        this.userId = userId;
        this.userPw = userPw;
        this.userPw2 = userPw2;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userAge = userAge;
        this.userGender = userGender;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public int getUserGender() {
        return userGender;
    }

    public void setUserGender(int userGender) {
        this.userGender = userGender;
    }
}
