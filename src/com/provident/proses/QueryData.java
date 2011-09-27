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
    
    public double getCostPupuk(int tahun, boolean isPeriodic) {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        double nilai = 0;
        String sql = null;

        try {
            if (isPeriodic) {
                sql = "SELECT COSTS_RAWATTNMTMPUPUK "
                        + "FROM MART_COSTS_RAWATTNMTMPUPUK "
                        + "WHERE TAHUN=? AND BULAN=? AND COMPANY=?";
            } else {
                sql = "SELECT SUM(COSTS_RAWATTNMTMPUPUK) "
                        + "FROM MART_COSTS_RAWATTNMTMPUPUK "
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
    
    public double getCostTanamLain(int tahun, boolean isPeriodic) {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        double nilai = 0;
        String sql = null;

        try {
            if (isPeriodic) {
                sql = "SELECT COSTS_RAWATTNMTMLAIN "
                        + "FROM MART_COSTS_RAWATTNMTMLAIN "
                        + "WHERE TAHUN=? AND BULAN=? AND COMPANY=?";
            } else {
                sql = "SELECT SUM(COSTS_RAWATTNMTMLAIN) "
                        + "FROM MART_COSTS_RAWATTNMTMLAIN "
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
    
    public double getCostInfra(int tahun, boolean isPeriodic) {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        double nilai = 0;
        String sql = null;

        try {
            if (isPeriodic) {
                sql = "SELECT COSTS_RAWATINFRAS "
                        + "FROM MART_COSTS_RAWATINFRAS "
                        + "WHERE TAHUN=? AND BULAN=? AND COMPANY=?";
            } else {
                sql = "SELECT SUM(COSTS_RAWATINFRAS) "
                        + "FROM MART_COSTS_RAWATINFRAS "
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
    
    public double getCostTbsLuarPlasma(int tahun, boolean isPeriodic) {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        double nilai = 0;
        String sql = null;

        try {
            if (isPeriodic) {
                sql = "SELECT COSTS_BELITBSPLASMA "
                        + "FROM MART_COSTS_BELITBSPLASMA "
                        + "WHERE TAHUN=? AND BULAN=? AND COMPANY=?";
            } else {
                sql = "SELECT SUM(COSTS_BELITBSPLASMA) "
                        + "FROM MART_COSTS_BELITBSPLASMA "
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
    
    public double getCostTbsLuarPihak3(int tahun, boolean isPeriodic) {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        double nilai = 0;
        String sql = null;

        try {
            if (isPeriodic) {
                sql = "SELECT COSTS_BELITBSPIHAK3 "
                        + "FROM MART_COSTS_BELITBSPIHAK3 "
                        + "WHERE TAHUN=? AND BULAN=? AND COMPANY=?";
            } else {
                sql = "SELECT SUM(COSTS_BELITBSPIHAK3) "
                        + "FROM MART_COSTS_BELITBSPIHAK3 "
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
    
    
    public double getTonaseCpoTbsOlahInti(int tahun, boolean isPeriodic) {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        double nilai = 0;
        String sql = null;

        try {
            if (isPeriodic) {
                sql = "SELECT QTY_TBS_OLAHINTI "
                        + "FROM MART_QTY_TBS_OLAHINTI "
                        + "WHERE TAHUN=? AND BULAN=? AND COMPANY=?";
            } else {
                sql = "SELECT SUM(QTY_TBS_OLAHINTI) "
                        + "FROM MART_QTY_TBS_OLAHINTI "
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
        return decimalPoint2(nilai);
    }
    
    public double getTonaseCpoTbsOlahPlasma(int tahun, boolean isPeriodic) {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        double nilai = 0;
        String sql = null;

        try {
            if (isPeriodic) {
                sql = "SELECT QTY_TBS_OLAHPLASMA "
                        + "FROM MART_QTY_TBS_OLAHPLASMA "
                        + "WHERE TAHUN=? AND BULAN=? AND COMPANY=?";
            } else {
                sql = "SELECT SUM(QTY_TBS_OLAHPLASMA) "
                        + "FROM MART_QTY_TBS_OLAHPLASMA "
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
        return decimalPoint2(nilai);
    }
    
    public double getTonaseRestanOlah(int tahun, boolean isPeriodic) {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        double nilai = 0;
        String sql = null;

        try {
            if (isPeriodic) {
                sql = "SELECT QTY_RESTANOLAH "
                        + "FROM MART_QTY_RESTANOLAH "
                        + "WHERE TAHUN=? AND BULAN=? AND COMPANY=?";
            } else {
                sql = "SELECT SUM(QTY_RESTANOLAH) "
                        + "FROM MART_QTY_RESTANOLAH "
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
        return decimalPoint2(nilai);
    }
    
    public double getTonaseCpoInti(int tahun, boolean isPeriodic) {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        double nilai = 0;
        String sql = null;

        try {
            if (isPeriodic) {
                sql = "SELECT QTY_CPO_TBSINTI "
                        + "FROM MART_QTY_CPO_TBSINTI "
                        + "WHERE TAHUN=? AND BULAN=? AND COMPANY=?";
            } else {
                sql = "SELECT SUM(QTY_CPO_TBSINTI) "
                        + "FROM MART_QTY_CPO_TBSINTI "
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
        return decimalPoint2(nilai);
    }
    
    public double getTonaseCpoPlasma(int tahun, boolean isPeriodic) {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        double nilai = 0;
        String sql = null;

        try {
            if (isPeriodic) {
                sql = "SELECT QTY_CPO_TBSPLASMA "
                        + "FROM MART_QTY_CPO_TBSPLASMA "
                        + "WHERE TAHUN=? AND BULAN=? AND COMPANY=?";
            } else {
                sql = "SELECT SUM(QTY_CPO_TBSPLASMA) "
                        + "FROM MART_QTY_CPO_TBSPLASMA "
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
        return decimalPoint2(nilai);
    }
    
    public double getTonaseRendemenCpo(int tahun, boolean isPeriodic) {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        double nilai = 0;
        String sql = null;

        try {
            if (isPeriodic) {
                sql = "SELECT QTY_RENDEMENCPO "
                        + "FROM MART_QTY_RENDEMENCPO "
                        + "WHERE TAHUN=? AND BULAN=? AND COMPANY=?";
            } else {
                sql = "SELECT SUM(QTY_RENDEMENCPO) "
                        + "FROM MART_QTY_RENDEMENCPO "
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
        return decimalPoint2(nilai);
    }
    
    public double getTonasePkTbsInti(int tahun, boolean isPeriodic) {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        double nilai = 0;
        String sql = null;

        try {
            if (isPeriodic) {
                sql = "SELECT QTY_PK_TBSINTI "
                        + "FROM MART_QTY_PK_TBSINTI "
                        + "WHERE TAHUN=? AND BULAN=? AND COMPANY=?";
            } else {
                sql = "SELECT SUM(QTY_PK_TBSINTI) "
                        + "FROM MART_QTY_PK_TBSINTI "
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
        return decimalPoint2(nilai);
    }    
    
    public double getTonasePkTbsPlasma(int tahun, boolean isPeriodic) {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        double nilai = 0;
        String sql = null;

        try {
            if (isPeriodic) {
                sql = "SELECT QTY_PK_TBSPLASMA "
                        + "FROM MART_QTY_PK_TBSPLASMA "
                        + "WHERE TAHUN=? AND BULAN=? AND COMPANY=?";
            } else {
                sql = "SELECT SUM(QTY_PK_TBSPLASMA) "
                        + "FROM MART_QTY_PK_TBSPLASMA "
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
        return decimalPoint2(nilai);
    }    
    
    public double getTonaseRendemenPk(int tahun, boolean isPeriodic) {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        double nilai = 0;
        String sql = null;

        try {
            if (isPeriodic) {
                sql = "SELECT QTY_RENDEMENPK "
                        + "FROM MART_QTY_RENDEMENPK "
                        + "WHERE TAHUN=? AND BULAN=? AND COMPANY=?";
            } else {
                sql = "SELECT SUM(QTY_RENDEMENPK) "
                        + "FROM MART_QTY_RENDEMENPK "
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
        return decimalPoint2(nilai);
    }
    
    public double getTonaseTbsTerimaInti(int tahun, boolean isPeriodic) {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        double nilai = 0;
        String sql = null;

        try {
            if (isPeriodic) {
                sql = "SELECT QTY_TBSTERIMAINTI "
                        + "FROM MART_QTY_TBSTERIMAINTI "
                        + "WHERE TAHUN=? AND BULAN=? AND COMPANY=?";
            } else {
                sql = "SELECT SUM(QTY_TBSTERIMAINTI) "
                        + "FROM MART_QTY_TBSTERIMAINTI "
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
        return decimalPoint2(nilai);
    }
    
    
    public double getTonaseTbsTerimaPlasma(int tahun, boolean isPeriodic) {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        double nilai = 0;
        String sql = null;

        try {
            if (isPeriodic) {
                sql = "SELECT QTY_TBSTERIMAPLASMA "
                        + "FROM MART_QTY_TBSTERIMAPLASMA "
                        + "WHERE TAHUN=? AND BULAN=? AND COMPANY=?";
            } else {
                sql = "SELECT SUM(QTY_TBSTERIMAPLASMA) "
                        + "FROM MART_QTY_TBSTERIMAPLASMA "
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
        return decimalPoint2(nilai);
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
    
    private double decimalPoint2(double nilai){        
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(nilai));
    }
}
