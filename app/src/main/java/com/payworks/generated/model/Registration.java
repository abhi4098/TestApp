package com.payworks.generated.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Abhinandan on 8/12/17.
 */

public class Registration {
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("fname")
    @Expose
    private String fname;
    @SerializedName("lname")
    @Expose
    private String lname;
    @SerializedName("pass")
    @Expose
    private String pass;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("companybox")
    @Expose
    private String companybox;

    @SerializedName("companyname")
    @Expose
    private String companyname;

    @SerializedName("address")
    @Expose
    private String address;


    public Registration(String email, String fname,String lname,String pass,String country,String type,String token,String phone,String companybox,String companyname,String address) {
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.pass = pass;
        this.country = country;
        this.type = type;
        this.token = token;
        this.phone = phone;
        this.companybox = companybox;
        this.companyname = companyname;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
