package com.evoteam.evolist;

/**
 * Created by user on 7/15/2017.
 */

public class User {

    String firstName, familyName, cityName, emailAddress, username, password;
    boolean licenseAgreement;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        if(emailAddress.length() > 3 && emailAddress.contains("@"))
            this.emailAddress = emailAddress;
        else
            this.emailAddress = " ";
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        if(familyName.length() > 3)
            this.familyName = familyName;
        else
            this.familyName = " ";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(firstName.length() > 3)
            this.firstName = firstName;
        else
            this.firstName = " ";
    }

    public boolean isLicenseAgreement() {
        return licenseAgreement;
    }

    public void setLicenseAgreement(boolean licenseAgreement) {
        this.licenseAgreement = licenseAgreement;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password.length() > 3)
            this.password = password;
        else
            this.password = " ";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if(username.length() > 3)
            this.username = username;
        else
            this.username = " ";
    }
}
