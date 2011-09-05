package com.provident.model.biaya;

import com.provident.model.Cost;

/**
 *
 * @author Christian
 */
public class BiayaRawatTanam extends Cost{
    
    private Cost tngKerja;
    private Cost pupuk;
    private Cost lain2;

    public BiayaRawatTanam(Cost tngKerja, Cost pupuk, Cost lain2) {
        this.tngKerja = tngKerja;
        this.pupuk = pupuk;
        this.lain2 = lain2;
    }

    public BiayaRawatTanam() {
    }

    public Cost getLain2() {
        return lain2;
    }

    public void setLain2(Cost lain2) {
        this.lain2 = lain2;
    }

    public Cost getPupuk() {
        return pupuk;
    }

    public void setPupuk(Cost pupuk) {
        this.pupuk = pupuk;
    }

    public Cost getTngKerja() {
        return tngKerja;
    }

    public void setTngKerja(Cost tngKerja) {
        this.tngKerja = tngKerja;
    }

    @Override
    public double getMasterBudget() {
        return tngKerja.getMasterBudget() + pupuk.getMasterBudget() + lain2.getMasterBudget();
    }

    @Override
    public double getMasterPrvActual() {
        return tngKerja.getMasterPrvActual() + pupuk.getMasterPrvActual() + lain2.getMasterPrvActual();
    }

    @Override
    public double getPeriodActual() {
        return tngKerja.getPeriodActual() + pupuk.getPeriodActual() + lain2.getPeriodActual();
    }

    @Override
    public double getPeriodBudget() {
        return tngKerja.getPeriodBudget() + pupuk.getPeriodBudget() + lain2.getPeriodBudget();
    }

    @Override
    public double getPeriodOutlook() {
        return tngKerja.getPeriodOutlook() + pupuk.getPeriodOutlook() + lain2.getPeriodOutlook();
    }

    @Override
    public double getPeriodPrvActual() {
        return tngKerja.getPeriodPrvActual() + pupuk.getPeriodPrvActual() + lain2.getPeriodPrvActual();
    }

    @Override
    public double getYearActual() {
        return tngKerja.getYearActual() + pupuk.getYearActual() + lain2.getYearActual();
    }

    @Override
    public double getYearBudget() {
        return tngKerja.getYearBudget() + pupuk.getYearBudget() + lain2.getYearBudget();
    }

    @Override
    public double getYearOutlook() {
        return tngKerja.getYearOutlook() + pupuk.getYearOutlook() + lain2.getYearOutlook();
    }

    @Override
    public double getYearPrvActual() {
        return tngKerja.getYearPrvActual() + pupuk.getYearPrvActual() + lain2.getYearPrvActual();
    }
    
    
    
}
