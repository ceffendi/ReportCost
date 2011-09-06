package com.provident.model.produksi;

import com.provident.model.Cost;

/**
 *
 * @author Christian
 */
public class Cpo {
    
    private Cost tbsOlah; // masuknya di Produksi
    private Cost restan;
    private Cost pCpo; // Produksi
    private Cost rendemenCpo;

    /**
     * 
     * @param tbsOlah - Class Produksi
     * @param restan
     * @param pCpo - Class Produksi
     * @param rendemenCpo 
     */
    public Cpo(Cost tbsOlah, Cost restan, Cost pCpo, Cost rendemenCpo) {
        this.tbsOlah = tbsOlah;
        this.restan = restan;
        this.pCpo = pCpo;
        this.rendemenCpo = rendemenCpo;
    }

    public Cpo() {
    }

    public Cost getpCpo() {
        return pCpo;
    }

    public void setpCpo(Cost pCpo) {
        this.pCpo = pCpo;
    }

    public Cost getRendemenCpo() {
        return rendemenCpo;
    }

    public void setRendemenCpo(Cost rendemenCpo) {
        this.rendemenCpo = rendemenCpo;
    }

    public Cost getRestan() {
        return restan;
    }

    public void setRestan(Cost restan) {
        this.restan = restan;
    }

    public Cost getTbsOlah() {
        return tbsOlah;
    }

    public void setTbsOlah(Cost tbsOlah) {
        this.tbsOlah = tbsOlah;
    }
    
    
    
    
}
