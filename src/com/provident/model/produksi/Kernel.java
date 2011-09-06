package com.provident.model.produksi;

import com.provident.model.Cost;

/**
 *
 * @author Christian
 */
public class Kernel {
    
    private Cost pk; // masuknya di Produksi
    private Cost rendemenPk;

    public Kernel(Cost pk, Cost rendemenPk) {
        this.pk = pk;
        this.rendemenPk = rendemenPk;
    }

    public Kernel() {
    }

    public Cost getPk() {
        return pk;
    }

    public void setPk(Cost pk) {
        this.pk = pk;
    }

    public Cost getRendemenPk() {
        return rendemenPk;
    }

    public void setRendemenPk(Cost rendemenPk) {
        this.rendemenPk = rendemenPk;
    }       
    
}
