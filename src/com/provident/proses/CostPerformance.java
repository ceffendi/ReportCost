package com.provident.proses;

import com.provident.model.Cost;
import com.provident.model.Organisasi;
import com.provident.model.Produksi;
import com.provident.model.biaya.BiayaProdLangsung;
import com.provident.model.biaya.BiayaProdTdkLangsung;
import com.provident.model.biaya.BiayaRawatTanam;
import com.provident.model.biaya.TotalBiayaAll;
import com.provident.model.biaya.TotalBiayaProd;
import com.provident.model.produksi.Cpo;
import com.provident.model.produksi.Kernel;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.joda.time.DateTime;

/**
 *
 * @author Christian
 */
public class CostPerformance {
    private static String templateFileName = "templates/cost_report_template.xls";
    public static String destFileName = "build/cost_report_output.xls";
    
    private boolean error = true;
    
    private static Logger log = Logger.getLogger(CostPerformance.class);
    
    private String perusahaan;
    private String periodeNmBulan;
    private int periodeBulan;
    private int periodeTahun;
    private String inisialPerusahaan;
    
    private QueryData query;

    public CostPerformance(Connection con, String perusahaan, int periodeBulan, int periodeTahun,String inisialPerusahan) {
//        this.con = con;
        this.perusahaan = perusahaan;
        this.periodeBulan = periodeBulan;
        this.periodeTahun = periodeTahun;
        this.inisialPerusahaan = inisialPerusahan;
        
        DateTime periode = new DateTime(periodeTahun, periodeBulan, 1, 0, 0);
        this.periodeNmBulan = periode.monthOfYear().getAsText();
        
        //Buat Object untuk Query Data
        query = new QueryData(con, periodeBulan, inisialPerusahaan);
        
    }
    
    // Proses Pembuatan Report jadi Object dan Outputnya dilakukan disni
    public void prosesOutputReport() throws ParsePropertyException, IOException, InvalidFormatException{
        // Setting Data Organisasi        
        Organisasi organisasi = new Organisasi(perusahaan.toUpperCase(), periodeNmBulan.toUpperCase(), periodeTahun);
        
        destFileName = "report_cost_" + organisasi.getOrgName() + "_" + periodeNmBulan.toUpperCase() + "_" + periodeTahun + ".xls";
           
        //Biaya Prod Langsung
        Cost biayaProdLangsung = getBiayaProduksiLangsung();
        
        //Biaya Prod Tdk Langsung
        Cost biayaProdTdkLangsung = getBiayaProduksiTidakLangsung();
        
        //Biaya Pembelian TBS Luar
        Cost biayaBeliTbsLuar = getBiayaPembelianTbsLuar();
        
        //Biaya Total Produksi
        Cost totalBiayaProd = new TotalBiayaProd(biayaProdLangsung, biayaProdTdkLangsung);
        
        //Biaya Total All
        Cost totalBiayaAll = new TotalBiayaAll(totalBiayaProd, biayaBeliTbsLuar);
        
        // =======================================================
        
        //Produksi Kebun CPO
        Cpo cpo = getQtyProduksiCpoHead();
        
        //Produksi Kebun PK (Kernel)
        Kernel kernel = getQtyPk();
        
        // Produksi TBS Terima
        Cost tbsTerima = getQtyProduksiTbsTerima();
        
               
        Map beans = new HashMap();

        // Buat Produksi Tonase Kebun
        beans.put("ptb", tbsTerima);
//        beans.put("hk", hektarTanam);
        beans.put("cpo", cpo);
        beans.put("pk", kernel);


        // Buat Biaya Produksi - mapping
        beans.put("pl", biayaProdLangsung);
        beans.put("ptl", biayaProdTdkLangsung);
        beans.put("tbs", biayaBeliTbsLuar);
        beans.put("totProd", totalBiayaProd);
        beans.put("totProdAndTbs", totalBiayaAll);

        //Organisasi
        beans.put("org", organisasi);


        XLSTransformer transformer = new XLSTransformer();
        transformer.transformXLS(templateFileName, beans, destFileName);
        error = false;
    }

    public boolean isError() {
        return error;
    }
    
    
    private Cost getBiayaProduksiLangsung(){
        
        //Cost Panen
        Cost biayaPanen = new Cost();
        biayaPanen.setPeriodActual(query.getCostPanen(periodeTahun, true));
        biayaPanen.setPeriodPrvActual(query.getCostPanen(periodeTahun - 1, true));
        biayaPanen.setYearActual(query.getCostPanen(periodeTahun, false));
        biayaPanen.setYearPrvActual(query.getCostPanen(periodeTahun - 1, false));

        //Cost Transport Panen
        Cost transportPanen = new Cost();
        transportPanen.setPeriodActual(query.getCostTransportPanen(periodeTahun, true));
        transportPanen.setPeriodPrvActual(query.getCostTransportPanen(periodeTahun - 1, true));
        transportPanen.setYearActual(query.getCostTransportPanen(periodeTahun, false));
        transportPanen.setYearPrvActual(query.getCostTransportPanen(periodeTahun - 1, false));

        //Cost Aktivitas Pabrik
        Cost actPabrik = new Cost();
        actPabrik.setPeriodActual(query.getCostActPabrik(periodeTahun, true));
        actPabrik.setPeriodPrvActual(query.getCostActPabrik(periodeTahun - 1, true));
        actPabrik.setYearActual(query.getCostActPabrik(periodeTahun, false));
        actPabrik.setYearPrvActual(query.getCostActPabrik(periodeTahun - 1, false));

        //Biaya Prod Langsung
        Cost biayaProdLangsung = new BiayaProdLangsung(biayaPanen, transportPanen, actPabrik);
        
        return biayaProdLangsung;
    }
    
    
    private Cost getBiayaProduksiTidakLangsung(){
        
        //Cost Sisip Sawit TM
        Cost sisipSawit = new Cost();
        sisipSawit.setPeriodActual(query.getCostSisipSawit(periodeTahun, true));
        sisipSawit.setPeriodPrvActual(query.getCostSisipSawit(periodeTahun-1, true));        
        sisipSawit.setYearActual(query.getCostSisipSawit(periodeTahun, false));
        sisipSawit.setYearPrvActual(query.getCostSisipSawit(periodeTahun-1, false));
        
        //Cost Rawat Tanam
        Cost rawatTanam = getBiayaRawatTanam();
        
        //Cost Infra
        Cost infra = new Cost();
        infra.setPeriodActual(query.getCostInfra(periodeTahun, true));
        infra.setPeriodPrvActual(query.getCostInfra(periodeTahun-1, true));        
        infra.setYearActual(query.getCostInfra(periodeTahun, false));
        infra.setYearPrvActual(query.getCostInfra(periodeTahun-1, false));
        
        //Cost Biaya Prod Tdk Langsung
        Cost biayaProdTdkLangsung = new BiayaProdTdkLangsung(sisipSawit, infra, rawatTanam, new Cost(), new Cost());
        
        return biayaProdTdkLangsung;
    }
    
    private Cost getBiayaPembelianTbsLuar(){
        
        //Cost Plasma
        Cost plasma = new Cost();
        plasma.setPeriodActual(query.getCostTbsLuarPlasma(periodeTahun, true));
        plasma.setPeriodPrvActual(query.getCostTbsLuarPlasma(periodeTahun - 1, true));
        plasma.setYearActual(query.getCostTbsLuarPlasma(periodeTahun, false));
        plasma.setYearPrvActual(query.getCostTbsLuarPlasma(periodeTahun - 1, false));

        //Cost Pihak 3
        Cost pihak3 = new Cost();
        pihak3.setPeriodActual(query.getCostTbsLuarPihak3(periodeTahun, true));
        pihak3.setPeriodPrvActual(query.getCostTbsLuarPihak3(periodeTahun - 1, true));
        pihak3.setYearActual(query.getCostTbsLuarPihak3(periodeTahun, false));
        pihak3.setYearPrvActual(query.getCostTbsLuarPihak3(periodeTahun - 1, false));
        
        //Cost Biaya Pembelian TBS Luar
        Cost biayaTbsLuar = new Produksi(plasma, pihak3);
        
        return biayaTbsLuar;
    }
    
    
    private Cost getBiayaRawatTanam(){
        
        //Cost Tenaga Kerja
        Cost tngKerja = new Cost();
        tngKerja.setPeriodActual(query.getCostTenagaKerja(periodeTahun, true));
        tngKerja.setPeriodPrvActual(query.getCostTenagaKerja(periodeTahun-1, true));        
        tngKerja.setYearActual(query.getCostTenagaKerja(periodeTahun, false));
        tngKerja.setYearPrvActual(query.getCostTenagaKerja(periodeTahun-1, false));
        
        //Cost Pupuk
        Cost pupuk = new Cost();
        pupuk.setPeriodActual(query.getCostPupuk(periodeTahun, true));
        pupuk.setPeriodPrvActual(query.getCostPupuk(periodeTahun-1, true));        
        pupuk.setYearActual(query.getCostPupuk(periodeTahun, false));
        pupuk.setYearPrvActual(query.getCostPupuk(periodeTahun-1, false));
        
        
        //Cost Material & Biaya Lain
        Cost biayaLain = new Cost();
        biayaLain.setPeriodActual(query.getCostTanamLain(periodeTahun, true));
        biayaLain.setPeriodPrvActual(query.getCostTanamLain(periodeTahun-1, true));        
        biayaLain.setYearActual(query.getCostTanamLain(periodeTahun, false));
        biayaLain.setYearPrvActual(query.getCostTanamLain(periodeTahun-1, false));
        
        
        //Cost Biaya Rawat Tanam TM
        Cost biayaRawatTanam = new BiayaRawatTanam(tngKerja, pupuk, biayaLain);
        
        return biayaRawatTanam;
    }
    
    
    private Cpo getQtyProduksiCpoHead(){
        
        //QTy TbsOlah
        Cost tbsOlah = getQtyTbsOlah();
        
        //Qty Restan Olah      
        Cost restan = new Cost();
        restan.setPeriodActual(query.getTonaseRestanOlah(periodeTahun, true));
        restan.setPeriodPrvActual(query.getTonaseRestanOlah(periodeTahun-1, true));        
        restan.setYearActual(query.getTonaseRestanOlah(periodeTahun, false));
        restan.setYearPrvActual(query.getTonaseRestanOlah(periodeTahun-1, false));
        
        //Oty Produksi CPO
        Cost prodCpo = getQtyProduksiCpoTon();
        
        //Qty Rendemen CPO      
        Cost rendemenCpo = new Cost();
        rendemenCpo.setPeriodActual(query.getTonaseRendemenCpo(periodeTahun, true));
        rendemenCpo.setPeriodPrvActual(query.getTonaseRendemenCpo(periodeTahun-1, true));        
        rendemenCpo.setYearActual(query.getTonaseRendemenCpo(periodeTahun, false));
        rendemenCpo.setYearPrvActual(query.getTonaseRendemenCpo(periodeTahun-1, false));
        
        //Qty Produksi CPO
        Cpo produksiCpo = new Cpo(tbsOlah, restan, prodCpo, rendemenCpo);
        
        return produksiCpo;
         
    }
    
    private Cost getQtyTbsOlah(){
        
        //Produksi CPO TBS Olah
        Produksi cpoTbsOlah = new Produksi();
        
         //Cost Inti
         Cost inti = new Cost();
         inti.setPeriodActual(query.getTonaseCpoTbsOlahInti(periodeTahun, true));
         inti.setPeriodPrvActual(query.getTonaseCpoTbsOlahInti(periodeTahun-1, true));        
         inti.setYearActual(query.getTonaseCpoTbsOlahInti(periodeTahun, false));
         inti.setYearPrvActual(query.getTonaseCpoTbsOlahInti(periodeTahun-1, false));
         
         //Cost Plasma
         Cost plasma = new Cost();
         plasma.setPeriodActual(query.getTonaseCpoTbsOlahPlasma(periodeTahun, true));
         plasma.setPeriodPrvActual(query.getTonaseCpoTbsOlahPlasma(periodeTahun-1, true));        
         plasma.setYearActual(query.getTonaseCpoTbsOlahPlasma(periodeTahun, false));
         plasma.setYearPrvActual(query.getTonaseCpoTbsOlahPlasma(periodeTahun-1, false));
         
         
       cpoTbsOlah.setInti(inti);
       cpoTbsOlah.setPlasma(plasma);
       cpoTbsOlah.setPihak3(new Cost());
       
       return cpoTbsOlah;
    }
    
    private Cost getQtyProduksiCpoTon(){
        
        //Produksi CPO (Ton)
        Produksi cpoTon = new Produksi();
        
         //Cost Inti
         Cost inti = new Cost();
         inti.setPeriodActual(query.getTonaseCpoInti(periodeTahun, true));
         inti.setPeriodPrvActual(query.getTonaseCpoInti(periodeTahun-1, true));        
         inti.setYearActual(query.getTonaseCpoInti(periodeTahun, false));
         inti.setYearPrvActual(query.getTonaseCpoInti(periodeTahun-1, false));
         
         //Cost Plasma
         Cost plasma = new Cost();
         plasma.setPeriodActual(query.getTonaseCpoPlasma(periodeTahun, true));
         plasma.setPeriodPrvActual(query.getTonaseCpoPlasma(periodeTahun-1, true));        
         plasma.setYearActual(query.getTonaseCpoPlasma(periodeTahun, false));
         plasma.setYearPrvActual(query.getTonaseCpoPlasma(periodeTahun-1, false));
         
         
       cpoTon.setInti(inti);
       cpoTon.setPlasma(plasma);
       cpoTon.setPihak3(new Cost());
       
       return cpoTon;
    }
    
    private Kernel getQtyPk(){
        
        //QTy TbsOlah
        Cost prodPk = getQtyProduksiPk();        
          
        //Qty Rendemen PK      
        Cost rendemenPk = new Cost();
        rendemenPk.setPeriodActual(query.getTonaseRendemenPk(periodeTahun, true));
        rendemenPk.setPeriodPrvActual(query.getTonaseRendemenPk(periodeTahun-1, true));        
        rendemenPk.setYearActual(query.getTonaseRendemenPk(periodeTahun, false));
        rendemenPk.setYearPrvActual(query.getTonaseRendemenPk(periodeTahun-1, false));
        
        //Qty Produksi CPO
        Kernel pk = new Kernel(prodPk, rendemenPk);
        
        return pk;
         
    }
    
    private Cost getQtyProduksiPk(){
        
        //Produksi CPO (Ton)
        Produksi prodPk = new Produksi();
        
         //Cost Inti
         Cost inti = new Cost();
         inti.setPeriodActual(query.getTonasePkTbsInti(periodeTahun, true));
         inti.setPeriodPrvActual(query.getTonasePkTbsInti(periodeTahun-1, true));        
         inti.setYearActual(query.getTonasePkTbsInti(periodeTahun, false));
         inti.setYearPrvActual(query.getTonasePkTbsInti(periodeTahun-1, false));
         
         //Cost Plasma
         Cost plasma = new Cost();
         plasma.setPeriodActual(query.getTonasePkTbsPlasma(periodeTahun, true));
         plasma.setPeriodPrvActual(query.getTonasePkTbsPlasma(periodeTahun-1, true));        
         plasma.setYearActual(query.getTonasePkTbsPlasma(periodeTahun, false));
         plasma.setYearPrvActual(query.getTonasePkTbsPlasma(periodeTahun-1, false));
         
         
       prodPk.setInti(inti);
       prodPk.setPlasma(plasma);
       prodPk.setPihak3(new Cost());
       
       return prodPk;
    }
    
    private Cost getQtyProduksiTbsTerima(){
        
        //Produksi CPO (Ton)
        Produksi tbsTerima = new Produksi();
        
         //Cost Inti
         Cost inti = new Cost();
         inti.setPeriodActual(query.getTonaseTbsTerimaInti(periodeTahun, true));
         inti.setPeriodPrvActual(query.getTonaseTbsTerimaInti(periodeTahun-1, true));        
         inti.setYearActual(query.getTonaseTbsTerimaInti(periodeTahun, false));
         inti.setYearPrvActual(query.getTonaseTbsTerimaInti(periodeTahun-1, false));
         
         //Cost Plasma
         Cost plasma = new Cost();
         plasma.setPeriodActual(query.getTonaseTbsTerimaPlasma(periodeTahun, true));
         plasma.setPeriodPrvActual(query.getTonaseTbsTerimaPlasma(periodeTahun-1, true));        
         plasma.setYearActual(query.getTonaseTbsTerimaPlasma(periodeTahun, false));
         plasma.setYearPrvActual(query.getTonaseTbsTerimaPlasma(periodeTahun-1, false));
         
         
       tbsTerima.setInti(inti);
       tbsTerima.setPlasma(plasma);
       tbsTerima.setPihak3(new Cost());
       
       return tbsTerima;
    }
    
}
