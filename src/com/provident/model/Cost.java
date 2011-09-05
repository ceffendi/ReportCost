package com.provident.model;

/**
 *
 * @author Christian
 */
public class Cost {
    
    protected double periodActual;
    protected double periodOutlook;
    protected double periodBudget;
    protected double periodPrvActual;
    
    protected double yearActual;
    protected double yearOutlook;
    protected double yearBudget;
    protected double yearPrvActual;
    
    protected double masterBudget;
    protected double masterPrvActual;

    public Cost() {
    }

    public Cost(double periodActual, double periodOutlook, double periodBudget, double periodPrvActual, double yearActual, double yearOutlook, double yearBudget, double yearPrvActual, double masterBudget, double masterPrvActual) {
        this.periodActual = periodActual;
        this.periodOutlook = periodOutlook;
        this.periodBudget = periodBudget;
        this.periodPrvActual = periodPrvActual;
        this.yearActual = yearActual;
        this.yearOutlook = yearOutlook;
        this.yearBudget = yearBudget;
        this.yearPrvActual = yearPrvActual;
        this.masterBudget = masterBudget;
        this.masterPrvActual = masterPrvActual;
    }

    public double getMasterBudget() {
        return masterBudget;
    }

    public void setMasterBudget(double masterBudget) {
        this.masterBudget = masterBudget;
    }

    public double getMasterPrvActual() {
        return masterPrvActual;
    }

    public void setMasterPrvActual(double masterPrvActual) {
        this.masterPrvActual = masterPrvActual;
    }

    public double getPeriodActual() {
        return periodActual;
    }

    public void setPeriodActual(double periodActual) {
        this.periodActual = periodActual;
    }

    public double getPeriodBudget() {
        return periodBudget;
    }

    public void setPeriodBudget(double periodBudget) {
        this.periodBudget = periodBudget;
    }

    public double getPeriodOutlook() {
        return periodOutlook;
    }

    public void setPeriodOutlook(double periodOutlook) {
        this.periodOutlook = periodOutlook;
    }

    public double getPeriodPrvActual() {
        return periodPrvActual;
    }

    public void setPeriodPrvActual(double periodPrvActual) {
        this.periodPrvActual = periodPrvActual;
    }

    public double getYearActual() {
        return yearActual;
    }

    public void setYearActual(double yearActual) {
        this.yearActual = yearActual;
    }

    public double getYearBudget() {
        return yearBudget;
    }

    public void setYearBudget(double yearBudget) {
        this.yearBudget = yearBudget;
    }

    public double getYearOutlook() {
        return yearOutlook;
    }

    public void setYearOutlook(double yearOutlook) {
        this.yearOutlook = yearOutlook;
    }

    public double getYearPrvActual() {
        return yearPrvActual;
    }

    public void setYearPrvActual(double yearPrvActual) {
        this.yearPrvActual = yearPrvActual;
    }

    @Override
    public String toString() {
        return "Cost{" + "periodActual=" + periodActual + ", periodOutlook=" + periodOutlook + ", periodBudget=" + periodBudget + ", periodPrvActual=" + periodPrvActual + ", yearActual=" + yearActual + ", yearOutlook=" + yearOutlook + ", yearBudget=" + yearBudget + ", yearPrvActual=" + yearPrvActual + ", masterBudget=" + masterBudget + ", masterPrvActual=" + masterPrvActual + '}';
    }
    
    
    
    
    
}
