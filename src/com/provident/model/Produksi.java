package com.provident.model;

/**
 *
 * @author Christian
 */
public class Produksi extends Cost{
    
    private Cost plasma;
    private Cost pihak3;
    private Cost inti;

    public Produksi(Cost plasma, Cost pihak3, Cost inti) {
        this.plasma = plasma;
        this.pihak3 = pihak3;
        this.inti = inti;
    }

    public Produksi() {
    }

    public Cost getInti() {
        return inti;
    }

    public void setInti(Cost inti) {
        this.inti = inti;
    }

    public Cost getPihak3() {
        return pihak3;
    }

    public void setPihak3(Cost pihak3) {
        this.pihak3 = pihak3;
    }

    public Cost getPlasma() {
        return plasma;
    }

    public void setPlasma(Cost plasma) {
        this.plasma = plasma;
    }
        
    @Override
    public double getMasterBudget() {
        return plasma.getMasterBudget() + pihak3.getMasterBudget() + inti.getMasterBudget();
    }

    @Override
    public double getMasterPrvActual() {
        return plasma.getMasterPrvActual() + pihak3.getMasterPrvActual() + inti.getMasterPrvActual();
    }

    @Override
    public double getPeriodActual() {
        return plasma.getPeriodActual() + pihak3.getPeriodActual() + inti.getPeriodActual();
    }

    @Override
    public double getPeriodBudget() {
        return plasma.getPeriodBudget() + pihak3.getPeriodBudget() + inti.getPeriodBudget();
    }

    @Override
    public double getPeriodOutlook() {
        return plasma.getPeriodOutlook() + pihak3.getPeriodOutlook() + inti.getPeriodOutlook();
    }

    @Override
    public double getPeriodPrvActual() {
        return plasma.getPeriodPrvActual() + pihak3.getPeriodPrvActual() + inti.getPeriodPrvActual();
    }

    @Override
    public double getYearActual() {
        return plasma.getYearActual() + pihak3.getYearActual() + inti.getYearActual();
    }

    @Override
    public double getYearBudget() {
        return plasma.getYearBudget() + pihak3.getYearBudget() + inti.getYearBudget();
    }

    @Override
    public double getYearOutlook() {
        return plasma.getYearOutlook() + pihak3.getYearOutlook() + inti.getYearOutlook();
    }

    @Override
    public double getYearPrvActual() {
        return plasma.getYearPrvActual() + pihak3.getYearPrvActual() + inti.getYearPrvActual();
    }
    
    
    
}
