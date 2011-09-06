
import com.provident.model.Cost;
import com.provident.model.Produksi;
import com.provident.model.biaya.BiayaProdLangsung;
import com.provident.model.biaya.BiayaProdTdkLangsung;
import com.provident.model.biaya.BiayaRawatTanam;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author Christian
 */
public class TestCostReport {
    private static String templateFileName = "templates/cost_report_template.xls";
    private static String destFileName = "build/cost_report_output.xls";
    
    public static void main(String [] args) throws ParsePropertyException, IOException, InvalidFormatException{
        
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
        
        
        Map beans = new HashMap();
        beans.put("pl", biayaProdLangsung);
        beans.put("ptl", biayaProdTakLgsung);
        beans.put("tbs", tbsLuar);
        
        XLSTransformer transformer = new XLSTransformer();       
        transformer.transformXLS(templateFileName, beans, destFileName);
        
        
    }
    
}
