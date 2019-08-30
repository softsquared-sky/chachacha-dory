package com.example.chachacha_dory;

import com.google.gson.annotations.SerializedName;

public class DefaultResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("result")
    Result result;

    public static class Result{
        @SerializedName("jwt") String jwt;

        @SerializedName("name") String name;
        @SerializedName("writing") String writing;
        @SerializedName("email") String email;
        @SerializedName("phone") String phone;
        @SerializedName("signuptime") String signuptime;

        public String getJwt(){ return jwt; }

        public String getName(){return name;}
        public String getWriting(){return writing;}
        public String getEmail(){return email;}
        public String getPhone(){return phone;}
        public String getSignuptime(){return signuptime;}

        public void setName(String name) {
            this.name = name;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setSignuptime(String signuptime) {
            this.signuptime = signuptime;
        }

        public void setWriting(String writing) {
            this.writing = writing;
        }
    }

    public Result getResult(){  return result;  }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

}