package com.provident.proses;

import com.provident.model.Cost;
import com.provident.model.Organisasi;
import com.provident.model.biaya.BiayaProdLangsung;
import com.provident.model.biaya.BiayaProdTdkLangsung;
import com.provident.model.biaya.BiayaRawatTanam;
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
               
        Map beans = new HashMap();

        // Buat Produksi Tonase Kebun
//        beans.put("ptb", prodTbs);
//        beans.put("hk", hektarTanam);
//        beans.put("cpo", cpo);
//        beans.put("pk", kernel);


        // Buat Biaya Produksi - mapping
        beans.put("pl", biayaProdLangsung);
        beans.put("ptl", biayaProdTdkLangsung);
//        beans.put("tbs", tbsLuar);
//        beans.put("totProd", totalBiayaProduksi);
//        beans.put("totProdAndTbs", totalBiayaAll);

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
        
        
        //Cost Biaya Prod Tdk Langsung
        Cost biayaProdTdkLangsung = new BiayaProdTdkLangsung(sisipSawit, new Cost(), rawatTanam, new Cost(), new Cost());
        
        return biayaProdTdkLangsung;
    }
    
    
    private Cost getBiayaRawatTanam(){
        
        //Cost Tenaga Kerja
        Cost tngKerja = new Cost();
        tngKerja.setPeriodActual(query.getCostTenagaKerja(periodeTahun, true));
        tngKerja.setPeriodPrvActual(query.getCostTenagaKerja(periodeTahun-1, true));        
        tngKerja.setYearActual(query.getCostTenagaKerja(periodeTahun, false));
        tngKerja.setYearPrvActual(query.getCostTenagaKerja(periodeTahun-1, false));
        
        //Cost Biaya Rawat Tanam TM
        Cost biayaRawatTanam = new BiayaRawatTanam(tngKerja, new Cost(), new Cost());
        
        return biayaRawatTanam;
    }
    
}
