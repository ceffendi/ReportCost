package com.provident.model.produksi;

import com.provident.model.Cost;

/**
 *
 * @author Christian
 */
public class HektarTanam extends Cost{
    
    private Cost tm;
    private Cost tbm;

    public HektarTanam(Cost tm, Cost tbm) {
        this.tm = tm;
        this.tbm = tbm;
    }

    public HektarTanam() {
    }

    public Cost getTbm() {
        return tbm;
    }

    public void setTbm(Cost tbm) {
        this.tbm = tbm;
    }

    public Cost getTm() {
        return tm;
    }

    public void setTm(Cost tm) {
        this.tm = tm;
    }

    @Override
    public double getMasterBudget() {
        return tm.getMasterBudget() + tbm.getMasterBudget();
    }

    @Override
    public double getMasterPrvActual() {
        return tm.getMasterPrvActual() + tbm.getMasterPrvActual();
    }

    @Override
    public double getPeriodActual() {
        return tm.getPeriodActual() + tbm.getPeriodActual();
    }

    @Override
    public double getPeriodBudget() {
        return tm.getPeriodBudget() + tbm.getPeriodBudget();
    }

    @Override
    public double getPeriodOutlook() {
        return tm.getPeriodOutlook() + tbm.getPeriodOutlook();
    }

    @Override
    public double getPeriodPrvActual() {
        return tm.getPeriodPrvActual() + tbm.getPeriodPrvActual();
    }

    @Override
    public double getYearActual() {
        return tm.getYearActual() + tbm.getYearActual();
    }

    @Override
    public double getYearBudget() {
        return tm.getYearBudget() + tbm.getYearBudget();
    }

    @Override
    public double getYearOutlook() {
        return tm.getYearOutlook() + tbm.getYearOutlook();
    }

    @Override
    public double getYearPrvActual() {
        return tm.getYearPrvActual() + tbm.getYearPrvActual();
    }
    
    
    
    
}
