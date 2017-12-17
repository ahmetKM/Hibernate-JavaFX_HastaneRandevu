/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HastaneRandevu;

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
@Table(name = "Yonetici")
public class Yonetici {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "yoneticiID")
    private int yoneticiID;
    @Column(name = "kullaniciAdi")
    private String kullaniciAdi;
    @Column(name = "sifre")
    private String sifre;

    public int getYoneticiID() {
        return yoneticiID;
    }

    public void setYoneticiID(int yoneticiID) {
        this.yoneticiID = yoneticiID;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }
}
