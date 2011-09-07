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
import com.provident.model.produksi.HektarTanam;
import com.provident.model.produksi.Kernel;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author Christian
 */
public class CostPerformance {
    private static String templateFileName = "templates/cost_report_template.xls";
    public static String destFileName = "build/cost_report_output.xls";
    
    private boolean error = true;
    
    private Connection con;
    private String perusahaan;
    private String periodeNmBulan;
    private int periodeBulan;
    private int periodeTahun;

    public CostPerformance(Connection con, String perusahaan, int periodeBulan, int periodeTahun) {
        this.con = con;
        this.perusahaan = perusahaan;
        this.periodeBulan = periodeBulan;
        this.periodeTahun = periodeTahun;
    }

    public CostPerformance(Connection con, String perusahaan, String periodeNmBulan, int periodeTahun) {
        this.con = con;
        this.perusahaan = perusahaan;
        this.periodeNmBulan = periodeNmBulan;
        this.periodeTahun = periodeTahun;
    }    
    
    //Proses-Proses Query SQL dilakukan disni
    
    // Proses Pembuatan Report jadi Object dan Outputnya dilakukan disni
    public void prosesOutputReport() throws ParsePropertyException, IOException, InvalidFormatException{
        // Setting Data Organisasi
//        DateTime periode = new DateTime(periodeTahun, periodeBulan, 1, 0, 0);
        Organisasi organisasi = new Organisasi(perusahaan.toUpperCase(), periodeNmBulan.toUpperCase(), periodeTahun);
        
        destFileName = "report_cost_" + organisasi.getOrgName() + "_" + periodeNmBulan.toUpperCase() + "_" + periodeTahun + ".xls";
        
        Cost nilaiSama = new Cost();
        nilaiSama.setPeriodActual(1000.32);
        nilaiSama.setPeriodBudget(500.32);
        nilaiSama.setPeriodOutlook(500.32);
        nilaiSama.setPeriodPrvActual(1000.32);

        nilaiSama.setYearActual(1000.32);
        nilaiSama.setYearBudget(500.32);
        nilaiSama.setYearOutlook(500.32);
        nilaiSama.setYearPrvActual(1000.32);

        nilaiSama.setMasterBudget(2000.56);
        nilaiSama.setMasterPrvActual(2000.56);


        Cost biayaProdLangsung = new BiayaProdLangsung(nilaiSama, nilaiSama, nilaiSama);
        Cost biayaRwatTanam = new BiayaRawatTanam(nilaiSama, nilaiSama, nilaiSama);
        Cost biayaProdTakLgsung = new BiayaProdTdkLangsung(nilaiSama, nilaiSama, biayaRwatTanam, nilaiSama, nilaiSama);
        Cost tbsLuar = new Produksi(nilaiSama, nilaiSama);

        // Biaya Total
        Cost totalBiayaProduksi = new TotalBiayaProd(biayaProdLangsung, biayaProdTakLgsung);
        Cost totalBiayaAll = new TotalBiayaAll(totalBiayaProduksi, tbsLuar);


        //Produksi TBS (Ton)
        Cost prodTbs = new Produksi(nilaiSama, nilaiSama, nilaiSama);
        //hektar tanam
        Cost hektarTanam = new HektarTanam(nilaiSama, nilaiSama);

        //CPO
        Cpo cpo = new Cpo(new Produksi(nilaiSama, nilaiSama, nilaiSama), nilaiSama, new Produksi(nilaiSama, nilaiSama, nilaiSama), nilaiSama);

        //Kernel
        Kernel kernel = new Kernel(tbsLuar, nilaiSama);

        Map beans = new HashMap();

        // Buat Produksi Tonase Kebun
        beans.put("ptb", prodTbs);
        beans.put("hk", hektarTanam);
        beans.put("cpo", cpo);
        beans.put("pk", kernel);


        // Buat Biaya Produksi - mapping
        beans.put("pl", biayaProdLangsung);
        beans.put("ptl", biayaProdTakLgsung);
        beans.put("tbs", tbsLuar);
        beans.put("totProd", totalBiayaProduksi);
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
    
    
    
    
}
