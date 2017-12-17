/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HastaneRandevu;

import java.sql.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 *
 * @author AKM
 */
public class VeriTabaniIslemleri {

    public static boolean girisKontrol(String kullaniciAdi, String sifre) {

        boolean sifreDogruMu = false;

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("FROM Yonetici");
        List<Yonetici> sonuclar = query.list();

        for (int i = 0; i < sonuclar.size(); i++) {
            System.out.println(i + ". kullanıcı adı= " + sonuclar.get(i).getKullaniciAdi());
            System.out.println("girilen kullanıcı adı= " + kullaniciAdi);
            if ((sonuclar.get(i).getKullaniciAdi().equals(kullaniciAdi)) && (sonuclar.get(i).getSifre().equals(sifre))) {
                System.out.println("kayıt bulundu");
                sifreDogruMu = true;
                System.out.println("şifre doğru");
                break;
            } else {
                System.out.println("kayıt BULUNAMADI");
                sifreDogruMu = false; //kayıt bulunamadı
            }
        }
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
        return sifreDogruMu;

    }

    public static void kayitEkle(String hastaAdi, String hastaSoyadi, String tcKimlikNo, String klinik, Date tarih, String saat) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Randevu r = new Randevu();

        r.setRandevuID(2);
        r.setHastaAdi(hastaAdi);
        r.setHastaSoyadi(hastaSoyadi);
        r.setTcKimlikNo(tcKimlikNo);
        r.setKlinik(klinik);
        r.setTarih(tarih);
        r.setSaat(saat);

        session.beginTransaction();
        
        session.save(r);
        session.flush();
        session.close();
        sessionFactory.close();

    }

    //String eskiKlinik,Date eskiTarih,String eskiSaat,
    public static void guncelle(Randevu guncellenenRandevu,String tc, String yeniAd, String yeniSoyad, String yeniKlinik, Date yeniTarih, String yeniSaat) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        guncellenenRandevu.setHastaAdi(yeniAd);
        guncellenenRandevu.setHastaSoyadi(yeniSoyad);
        guncellenenRandevu.setKlinik(yeniKlinik);
        guncellenenRandevu.setTarih(yeniTarih);
        guncellenenRandevu.setSaat(yeniSaat);

        session.saveOrUpdate(guncellenenRandevu);
       session.flush();
        session.close();
        sessionFactory.close();
    }

    public static void randevuSil(String tc) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Randevu r = new Randevu();
        session.beginTransaction();
        
        List<Randevu> sonuclar = arama(tc);

        Randevu silinen = new Randevu();
        silinen = (Randevu) session.load(Randevu.class, sonuclar.get(0).getRandevuID());
        session.delete(silinen);
        
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    public void metot() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("FROM Yonetici");
        List<Yonetici> sonuclar = query.list();

        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    public void ekle(String kA, String sifre) {
        Yonetici y = new Yonetici();
        y.setKullaniciAdi(kA);
        y.setSifre(sifre);
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(y);
        session.close();
        sessionFactory.close();
    }

    public static List<Randevu> arama(String tc) {
        Randevu geciciNesne = new Randevu();

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("FROM Randevu WHERE tcKimlikNo='" + tc + "'");
        List<Randevu> sonuclar = query.list();

        session.getTransaction().commit();
        session.close();
        sessionFactory.close();

        return sonuclar;
    }

}
