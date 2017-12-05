/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseproject;

/**
 *
 * @author hthoma
 */
public class Student {
    String FName, Mname, Lname, SID, RNum, Delegation, PhoneNum, Role, DName, Adv1, Adv2;
    Boolean PaidUp;

    public String getAdv1() {
        return Adv1;
    }

    public void setAdv1(String Adv1) {
        this.Adv1 = Adv1;
    }

    public String getAdv2() {
        return Adv2;
    }

    public void setAdv2(String Adv2) {
        this.Adv2 = Adv2;
    }

    public String getDName() {
        return DName;
    }

    public void setDName(String DName) {
        this.DName = DName;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public Boolean getPaidUp() {
        return PaidUp;
    }

    public void setPaidUp(Boolean PaidUp) {
        this.PaidUp = PaidUp;
    }
    
    
    public Student() {
    }



    public Student(String FName, String Mname, String Lname, String SID, String RNum, String Delegation, String PhoneNum) {
        this.FName = FName;
        this.Mname = Mname;
        this.Lname = Lname;
        this.SID = SID;
        this.RNum = RNum;
        this.Delegation = Delegation;
        this.PhoneNum = PhoneNum;
    }

    @Override
    public String toString() {
        return "Student{" + "FName=" + FName + ", Mname=" + Mname + ", Lname=" + Lname + ", SID=" + SID + ", RNum=" + RNum + ", Delegation=" + Delegation + ", PhoneNum=" + PhoneNum + ", Role=" + Role + ", DName=" + DName + ", Adv1=" + Adv1 + ", Adv2=" + Adv2 + ", PaidUp=" + PaidUp + '}';
    }

    

    public Student(String FName, String Lname) {
        this.FName = FName;
        this.Lname = Lname;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getMname() {
        return Mname;
    }

    public void setMname(String Mname) {
        this.Mname = Mname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String Lname) {
        this.Lname = Lname;
    }

    public String getSID() {
        return SID;
    }

    public void setSID(String SID) {
        this.SID = SID;
    }

    public String getRNum() {
        return RNum;
    }

    public void setRNum(String RNum) {
        this.RNum = RNum;
    }

    public String getDelegation() {
        return Delegation;
    }

    public void setDelegation(String Delegation) {
        this.Delegation = Delegation;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String PhoneNum) {
        this.PhoneNum = PhoneNum;
    }
    
}
