/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HastaneRandevu;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author AKM
 */
@Entity
@Table(name = "Randevu")
public class Randevu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "randevuID")
    private int randevuID;
    @Column(name = "hastaAdi")
    private String hastaAdi;
    @Column(name = "hastaSoyadi")
    private String hastaSoyadi;
    @Column(name = "tcKimlikNo")
    private String tcKimlikNo;
    @Column(name = "klinik")
    private String klinik;
    @Column(name = "tarih")
    private Date tarih;
    @Column(name = "saat")
    private String saat;

    public int getRandevuID() {
        return randevuID;
    }

    public void setRandevuID(int randevuID) {
        this.randevuID = randevuID;
    }

    public String getHastaAdi() {
        return hastaAdi;
    }

    public void setHastaAdi(String hastaAdi) {
        this.hastaAdi = hastaAdi;
    }

    public String getHastaSoyadi() {
        return hastaSoyadi;
    }

    public void setHastaSoyadi(String hastaSoyadi) {
        this.hastaSoyadi = hastaSoyadi;
    }

    public String getTcKimlikNo() {
        return tcKimlikNo;
    }

    public void setTcKimlikNo(String tcKimlikNo) {
        this.tcKimlikNo = tcKimlikNo;
    }

    public String getKlinik() {
        return klinik;
    }

    public void setKlinik(String klinik) {
        this.klinik = klinik;
    }

    public Date getTarih() {
        return tarih;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }
    
    
}
