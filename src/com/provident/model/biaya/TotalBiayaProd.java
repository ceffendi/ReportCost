package com.provident.model.biaya;

import com.provident.model.Cost;

/**
 *
 * @author Christian
 */
public class TotalBiayaProd extends Cost {
    
    private Cost prodLangsung;
    private Cost prodTdkLangsung;

    public TotalBiayaProd(Cost prodLangsung, Cost prodTdkLangsung) {
        this.prodLangsung = prodLangsung;
        this.prodTdkLangsung = prodTdkLangsung;
    }

    public TotalBiayaProd() {
    }

    public Cost getProdLangsung() {
        return prodLangsung;
    }

    public void setProdLangsung(Cost prodLangsung) {
        this.prodLangsung = prodLangsung;
    }

    public Cost getProdTdkLangsung() {
        return prodTdkLangsung;
    }

    public void setProdTdkLangsung(Cost prodTdkLangsung) {
        this.prodTdkLangsung = prodTdkLangsung;
    }
    
    @Override
    public double getMasterBudget() {
        return prodLangsung.getMasterBudget() + prodTdkLangsung.getMasterBudget();
    }

    @Override
    public double getMasterPrvActual() {
        return prodLangsung.getMasterPrvActual() + prodTdkLangsung.getMasterPrvActual();
    }

    @Override
    public double getPeriodActual() {
        return prodLangsung.getPeriodActual() + prodTdkLangsung.getPeriodActual();
    }

    @Override
    public double getPeriodBudget() {
        return prodLangsung.getPeriodBudget() + prodTdkLangsung.getPeriodBudget();
    }

    @Override
    public double getPeriodOutlook() {
        return prodLangsung.getPeriodOutlook() + prodTdkLangsung.getPeriodOutlook();
    }

    @Override
    public double getPeriodPrvActual() {
        return prodLangsung.getPeriodPrvActual() + prodTdkLangsung.getPeriodPrvActual();
    }

    @Override
    public double getYearActual() {
        return prodLangsung.getYearActual() + prodTdkLangsung.getYearActual();
    }

    @Override
    public double getYearBudget() {
        return prodLangsung.getYearBudget() + prodTdkLangsung.getYearBudget();
    }

    @Override
    public double getYearOutlook() {
        return prodLangsung.getYearOutlook() + prodTdkLangsung.getYearOutlook();
    }

    @Override
    public double getYearPrvActual() {
        return prodLangsung.getYearPrvActual() + prodTdkLangsung.getYearPrvActual();
    }
    
}
