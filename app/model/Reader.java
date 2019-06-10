package model;

import io.ebean.Finder;
import io.ebean.Model;

public class Reader extends Model {
    private String ID;
    private String name;
    private String mobileNumber;
    private String email;

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static final Finder<Long, Reader> find = new Finder<>(Reader.class);
}
