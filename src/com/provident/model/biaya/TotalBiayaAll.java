package com.provident.model.biaya;

import com.provident.model.Cost;

/**
 *
 * @author Christian
 */
public class TotalBiayaAll extends Cost {
    
    private Cost prodAll;
    private Cost beliTbs;

    public TotalBiayaAll(Cost prodAll, Cost beliTbs) {
        this.prodAll = prodAll;
        this.beliTbs = beliTbs;
    }

    public TotalBiayaAll() {
    }

    public Cost getBeliTbs() {
        return beliTbs;
    }

    public void setBeliTbs(Cost beliTbs) {
        this.beliTbs = beliTbs;
    }

    public Cost getProdAll() {
        return prodAll;
    }

    public void setProdAll(Cost prodAll) {
        this.prodAll = prodAll;
    }
    
    @Override
    public double getMasterBudget() {
        return prodAll.getMasterBudget() + beliTbs.getMasterBudget();
    }

    @Override
    public double getMasterPrvActual() {
        return prodAll.getMasterPrvActual() + beliTbs.getMasterPrvActual();
    }

    @Override
    public double getPeriodActual() {
        return prodAll.getPeriodActual() + beliTbs.getPeriodActual();
    }

    @Override
    public double getPeriodBudget() {
        return prodAll.getPeriodBudget() + beliTbs.getPeriodBudget();
    }

    @Override
    public double getPeriodOutlook() {
        return prodAll.getPeriodOutlook() + beliTbs.getPeriodOutlook();
    }

    @Override
    public double getPeriodPrvActual() {
        return prodAll.getPeriodPrvActual() + beliTbs.getPeriodPrvActual();
    }

    @Override
    public double getYearActual() {
        return prodAll.getYearActual() + beliTbs.getYearActual();
    }

    @Override
    public double getYearBudget() {
        return prodAll.getYearBudget() + beliTbs.getYearBudget();
    }

    @Override
    public double getYearOutlook() {
        return prodAll.getYearOutlook() + beliTbs.getYearOutlook();
    }

    @Override
    public double getYearPrvActual() {
        return prodAll.getYearPrvActual() + beliTbs.getYearPrvActual();
    }
    
}
