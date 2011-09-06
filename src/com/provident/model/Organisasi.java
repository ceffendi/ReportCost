package com.provident.model;

import org.joda.time.DateTime;

/**
 *
 * @author Christian
 */
public class Organisasi {
    private String orgName;
    private String periode;
    private DateTime periodeDate;
    private int now;
    private int prv;

    public Organisasi() {
    }

    public Organisasi(String orgName, String periode, int now) {
        this.orgName = orgName;
        this.periode = periode;
        this.now = now;
        this.prv = now - 1;
    }  

    public Organisasi(String orgName, DateTime periodeDate) {
        this.orgName = orgName;
        this.periodeDate = periodeDate;
        
        initialize();
    }
    
    private void initialize(){
        this.periode = periodeDate.monthOfYear().getAsText() + " " + periodeDate.year().getAsText();
        this.now = periodeDate.getYear();
        this.prv = periodeDate.getYear() - 1;
    }

    public int getNow() {
        return now;
    }    

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getPeriode() {
        return periode.toUpperCase();
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public DateTime getPeriodeDate() {
        return periodeDate;
    }

    public void setPeriodeDate(DateTime periodeDate) {
        this.periodeDate = periodeDate;
    }

    public int getPrv() {
        return prv;
    }
    
//    public static void main(String [] args){
//        Organisasi data = new Organisasi("Test", new DateTime());
//        
//        System.out.println(data.getPeriode());
//        System.out.println(data.getNow());
//        System.out.println(data.getPrv());
//        
//    }
}
