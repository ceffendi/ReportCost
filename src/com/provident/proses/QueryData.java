package com.provident.proses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import org.apache.log4j.Logger;

/**
 *
 * @author Christian
 */
public class QueryData {
    
    private Connection connection;
    private int periodeBulan;    
    private String inisialPerusahaan;
    private static Logger log = Logger.getLogger(QueryData.class);

    public QueryData(Connection connection, int periodeBulan, String inisialPerusahaan) {
        this.connection = connection;
        this.periodeBulan = periodeBulan;
        this.inisialPerusahaan = inisialPerusahaan;
    }
    
    public double getCostPanen(int tahun, boolean isPeriodic) {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        double costPanen = 0;
        String sql = null;

        try {
            if (isPeriodic) {
                sql = "SELECT COSTS_PANEN "
                        + "FROM MART_COSTS_PANEN "
                        + "WHERE TAHUN=? AND BULAN=? AND COMPANY=?";
            } else {
                sql = "SELECT SUM(COSTS_PANEN) "
                        + "FROM MART_COSTS_PANEN "
                        + "WHERE TAHUN =? AND BULAN <=? AND COMPANY=?";
            }

            psmt = connection.prepareStatement(sql);
            psmt.setInt(1, tahun);
            psmt.setString(2, putZero(periodeBulan));
            psmt.setString(3, inisialPerusahaan);

            rs = psmt.executeQuery();

            while (rs.next()) {
                costPanen = rs.getDouble(1);
            }

        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
        } finally {
            try {
                psmt.close();
                rs.close();
            } catch (SQLException ex) {
                log.error(ex.getMessage(), ex);
            }
            psmt = null;
            rs = null;
        }
        return decimalPoint(costPanen);
    }

    public double getCostTransportPanen(int tahun, boolean isPeriodic) {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        double costTransportPanen = 0;
        String sql = null;

        try {
            if (isPeriodic) {
                sql = "SELECT COSTS_TRANSPORTPANEN "
                        + "FROM MART_COSTS_TRANSPORTPANEN "
                        + "WHERE TAHUN=? AND BULAN=? AND COMPANY=?";
            } else {
                sql = "SELECT SUM(COSTS_TRANSPORTPANEN) "
                        + "FROM MART_COSTS_TRANSPORTPANEN "
                        + "WHERE TAHUN =? AND BULAN <=? AND COMPANY=?";
            }

            psmt = connection.prepareStatement(sql);
            psmt.setInt(1, tahun);
            psmt.setString(2, putZero(periodeBulan));
            psmt.setString(3, inisialPerusahaan);

            rs = psmt.executeQuery();

            while (rs.next()) {
                costTransportPanen = rs.getDouble(1);
            }

        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
        } finally {
            try {
                psmt.close();
                rs.close();
            } catch (SQLException ex) {
                log.error(ex.getMessage(), ex);
            }
            psmt = null;
            rs = null;
        }
        return decimalPoint(costTransportPanen);
    }
    
    public double getCostActPabrik(int tahun, boolean isPeriodic) {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        double nilai = 0;
        String sql = null;

        try {
            if (isPeriodic) {
                sql = "SELECT COSTS_ACTIVITYPKS "
                        + "FROM MART_COSTS_ACTIVITYPKS "
                        + "WHERE TAHUN=? AND BULAN=? AND COMPANY=?";
            } else {
                sql = "SELECT SUM(COSTS_ACTIVITYPKS) "
                        + "FROM MART_COSTS_ACTIVITYPKS "
                        + "WHERE TAHUN =? AND BULAN <=? AND COMPANY=?";
            }

            psmt = connection.prepareStatement(sql);
            psmt.setInt(1, tahun);
            psmt.setString(2, putZero(periodeBulan));
            psmt.setString(3, inisialPerusahaan);

            rs = psmt.executeQuery();

            while (rs.next()) {
                nilai = rs.getDouble(1);
            }

        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
        } finally {
            try {
                psmt.close();
                rs.close();
            } catch (SQLException ex) {
                log.error(ex.getMessage(), ex);
            }
            psmt = null;
            rs = null;
        }
        return decimalPoint(nilai);
    }
    
    public double getCostTenagaKerja(int tahun, boolean isPeriodic) {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        double nilai = 0;
        String sql = null;

        try {
            if (isPeriodic) {
                sql = "SELECT COSTS_RAWATTNMTMKERJA "
                        + "FROM MART_COSTS_RAWATTNMTMKERJA "
                        + "WHERE TAHUN=? AND BULAN=? AND COMPANY=?";
            } else {
                sql = "SELECT SUM(COSTS_RAWATTNMTMKERJA) "
                        + "FROM MART_COSTS_RAWATTNMTMKERJA "
                        + "WHERE TAHUN =? AND BULAN <=? AND COMPANY=?";
            }

            psmt = connection.prepareStatement(sql);
            psmt.setInt(1, tahun);
            psmt.setString(2, putZero(periodeBulan));
            psmt.setString(3, inisialPerusahaan);

            rs = psmt.executeQuery();

            while (rs.next()) {
                nilai = rs.getDouble(1);
            }

        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
        } finally {
            try {
                psmt.close();
                rs.close();
            } catch (SQLException ex) {
                log.error(ex.getMessage(), ex);
            }
            psmt = null;
            rs = null;
        }
        return decimalPoint(nilai);
    }
    
    public double getCostSisipSawit(int tahun, boolean isPeriodic) {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        double nilai = 0;
        String sql = null;

        try {
            if (isPeriodic) {
                sql = "SELECT COSTS_SISIPSAWITTM "
                        + "FROM MART_COSTS_SISIPSAWITTM "
                        + "WHERE TAHUN=? AND BULAN=? AND COMPANY=?";
            } else {
                sql = "SELECT SUM(COSTS_SISIPSAWITTM) "
                        + "FROM MART_COSTS_SISIPSAWITTM "
                        + "WHERE TAHUN =? AND BULAN <=? AND COMPANY=?";
            }

            psmt = connection.prepareStatement(sql);
            psmt.setInt(1, tahun);
            psmt.setString(2, putZero(periodeBulan));
            psmt.setString(3, inisialPerusahaan);

            rs = psmt.executeQuery();

            while (rs.next()) {
                nilai = rs.getDouble(1);
            }

        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
        } finally {
            try {
                psmt.close();
                rs.close();
            } catch (SQLException ex) {
                log.error(ex.getMessage(), ex);
            }
            psmt = null;
            rs = null;
        }
        return decimalPoint(nilai);
    }
    
    private String putZero(int periodeBulan){
        if(periodeBulan < 10 )
            return "0" + String.valueOf(periodeBulan);
        
        return String.valueOf(periodeBulan);
    }   
    
    private double decimalPoint(double nilai){
        nilai = nilai / 1000.000;
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(nilai));
    }    
}
