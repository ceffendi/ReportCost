package com.provident.model.biaya;

import com.provident.model.Cost;

/**
 *
 * @author Christian
 */
public class BiayaProdLangsung extends Cost{
    
    private Cost panen;
    private Cost transPanen;
    private Cost actPabrik;

    public BiayaProdLangsung(Cost panen, Cost transPanen, Cost actPabrik) {
        this.panen = panen;
        this.transPanen = transPanen;
        this.actPabrik = actPabrik;
    }

    public BiayaProdLangsung() {
    }

    public Cost getActPabrik() {
        return actPabrik;
    }

    public void setActPabrik(Cost actPabrik) {
        this.actPabrik = actPabrik;
    }

    public Cost getPanen() {
        return panen;
    }

    public void setPanen(Cost panen) {
        this.panen = panen;
    }

    public Cost getTransPanen() {
        return transPanen;
    }

    public void setTransPanen(Cost transPanen) {
        this.transPanen = transPanen;
    }

    @Override
    public double getMasterBudget() {
        return panen.getMasterBudget() + transPanen.getMasterBudget() + actPabrik.getMasterBudget();
    }

    @Override
    public double getMasterPrvActual() {
        return panen.getMasterPrvActual() + transPanen.getMasterPrvActual() + actPabrik.getMasterPrvActual();
    }

    @Override
    public double getPeriodActual() {
        return panen.getPeriodActual() + transPanen.getPeriodActual() + actPabrik.getPeriodActual();
    }

    @Override
    public double getPeriodBudget() {
        return panen.getPeriodBudget() + transPanen.getPeriodBudget() + actPabrik.getPeriodBudget();
    }

    @Override
    public double getPeriodOutlook() {
        return panen.getPeriodOutlook() + transPanen.getPeriodOutlook() + actPabrik.getPeriodOutlook();
    }

    @Override
    public double getPeriodPrvActual() {
        return panen.getPeriodPrvActual() + transPanen.getPeriodPrvActual() + actPabrik.getPeriodPrvActual();
    }

    @Override
    public double getYearActual() {
        return panen.getYearActual() + transPanen.getYearActual() + actPabrik.getYearActual();
    }

    @Override
    public double getYearBudget() {
        return panen.getYearBudget() + transPanen.getYearBudget() + actPabrik.getYearBudget();
    }

    @Override
    public double getYearOutlook() {
        return panen.getYearOutlook() + transPanen.getYearOutlook() + actPabrik.getYearOutlook();
    }

    @Override
    public double getYearPrvActual() {
        return panen.getYearPrvActual() + transPanen.getYearPrvActual() + actPabrik.getYearPrvActual();
    }
    
    
    
}
