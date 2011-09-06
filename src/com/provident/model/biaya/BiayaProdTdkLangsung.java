package com.provident.model.biaya;

import com.provident.model.Cost;

/**
 *
 * @author Christian
 */
public class BiayaProdTdkLangsung extends Cost{
    
    private Cost sisip;
    private Cost infra;
    private Cost rawat;
    private Cost site;
    private Cost ho;

    public BiayaProdTdkLangsung(Cost sisip, Cost infra, Cost rawat, Cost site, Cost ho) {
        this.sisip = sisip;
        this.infra = infra;
        this.rawat = rawat;
        this.site = site;
        this.ho = ho;
    }

    public BiayaProdTdkLangsung() {
    }

    public Cost getHo() {
        return ho;
    }

    public void setHo(Cost ho) {
        this.ho = ho;
    }

    public Cost getInfra() {
        return infra;
    }

    public void setInfra(Cost infra) {
        this.infra = infra;
    }

    public Cost getRawat() {
        return rawat;
    }

    public void setRawat(Cost rawat) {
        this.rawat = rawat;
    }

    public Cost getSisip() {
        return sisip;
    }

    public void setSisip(Cost sisip) {
        this.sisip = sisip;
    }

    public Cost getSite() {
        return site;
    }

    public void setSite(Cost site) {
        this.site = site;
    }

    @Override
    public double getMasterBudget() {
        return sisip.getMasterBudget() + infra.getMasterBudget() + site.getMasterBudget() + rawat.getMasterBudget() + ho.getMasterBudget();
    }

    @Override
    public double getMasterPrvActual() {
        return sisip.getMasterPrvActual() + infra.getMasterPrvActual() + site.getMasterPrvActual() + rawat.getMasterPrvActual() + ho.getMasterPrvActual();
    }

    @Override
    public double getPeriodActual() {
        return sisip.getPeriodActual() + infra.getPeriodActual() + site.getPeriodActual() + rawat.getPeriodActual() + ho.getPeriodActual();
    }

    @Override
    public double getPeriodBudget() {
        return sisip.getPeriodBudget() + infra.getPeriodBudget() + site.getPeriodBudget() + rawat.getPeriodBudget() + ho.getPeriodBudget();
    }

    @Override
    public double getPeriodOutlook() {
        return sisip.getPeriodOutlook() + infra.getPeriodOutlook() + site.getPeriodOutlook() + rawat.getPeriodOutlook() + ho.getPeriodOutlook();
    }

    @Override
    public double getPeriodPrvActual() {
        return sisip.getPeriodPrvActual() + infra.getPeriodPrvActual() + site.getPeriodPrvActual() + rawat.getPeriodPrvActual() + ho.getPeriodPrvActual();
    }

    @Override
    public double getYearActual() {
        return sisip.getYearActual() + infra.getYearActual() + site.getYearActual() + rawat.getYearActual() + ho.getYearActual();
    }

    @Override
    public double getYearBudget() {
        return sisip.getYearBudget() + infra.getYearBudget() + site.getYearBudget() + + rawat.getYearBudget() + ho.getYearBudget();
    }

    @Override
    public double getYearOutlook() {
        return sisip.getYearOutlook() + infra.getYearOutlook() + site.getYearOutlook() + rawat.getYearOutlook() + ho.getYearOutlook();
    }

    @Override
    public double getYearPrvActual() {
        return sisip.getYearPrvActual() + infra.getYearPrvActual() + site.getYearPrvActual() + rawat.getYearPrvActual() + ho.getYearPrvActual();
    }
    
    
    
}
