/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HastaneRandevu;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author AKM
 */
public class RandevuListele extends Application {

    Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Hoşgeldiniz");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label lbl1 = new Label("Tc Kimlik No giriniz");
        GridPane.setConstraints(lbl1, 0, 0);

        TextField tf1 = new TextField();
        GridPane.setConstraints(tf1, 0, 1);

        Button btn1 = new Button("Ara");
        GridPane.setConstraints(btn1, 0, 2);

        TableView table = new TableView();
        GridPane.setConstraints(table, 0, 3);

        table.setEditable(true);
        // table.setSelectionModel(tvsm);

        TableColumn ad = new TableColumn("Hasta Adı");
        ad.setCellValueFactory(new PropertyValueFactory<Randevu, String>("hastaAdi"));

        TableColumn soyad = new TableColumn("Hasta Soyadı");
        soyad.setCellValueFactory(new PropertyValueFactory<Randevu, String>("hastaSoyadi"));

        TableColumn tcKimlikNo = new TableColumn("TC Kimlik No");
        tcKimlikNo.setCellValueFactory(new PropertyValueFactory<Randevu, String>("tcKimlikNo"));

        TableColumn klinik = new TableColumn("Klinik");
        klinik.setCellValueFactory(new PropertyValueFactory<Randevu, String>("klinik"));

        TableColumn tarihKolonu = new TableColumn("Tarih");
        tarihKolonu.setCellValueFactory(new PropertyValueFactory<Randevu, Date>("tarih"));

        TableColumn saat = new TableColumn("Saat");
        saat.setCellValueFactory(new PropertyValueFactory<Randevu, String>("saat"));

        table.getColumns().addAll(ad, soyad, tcKimlikNo, klinik, tarihKolonu, saat);

        Label lbl2 = new Label("Hasta Adı: ");
        GridPane.setConstraints(lbl2, 0, 4);

        TextField tf2 = new TextField();
        GridPane.setConstraints(tf2, 0, 5);

        Label lbl3 = new Label("Hasta Soyadı: ");
        GridPane.setConstraints(lbl3, 0, 6);

        TextField tf3 = new TextField();
        GridPane.setConstraints(tf3, 0, 7);

        Label lbl4 = new Label("klinik: ");
        GridPane.setConstraints(lbl4, 0, 8);

        ComboBox<String> klinikler = new ComboBox<String>();
        klinikler.getItems().addAll("Ortopedi", "Nöroloji", "Kalp ve Damar",
                "Göğüs Hastalıkları", "Kardiyokoji", "Kulak Burun Boğaz");
        GridPane.setConstraints(klinikler, 0, 9);

        Label lbl5 = new Label("Tarih: ");
        GridPane.setConstraints(lbl5, 0, 10);

        DatePicker dP1 = new DatePicker();
        GridPane.setConstraints(dP1, 0, 11);

        Label lbl6 = new Label("Saat: ");
        GridPane.setConstraints(lbl6, 0, 12);

        ComboBox<String> saatler = new ComboBox<String>();
        saatler.getItems().addAll("08:00", "09:00", "10:00",
                "11:00", "13:00", "14:00", "15:00", "16:00");
        GridPane.setConstraints(saatler, 0, 13);

        Button randevuSilBtn = new Button("Randevuyu Sil");
        GridPane.setConstraints(randevuSilBtn, 0, 14);

        Button randevuDuzenleBtn = new Button("Randevuyu Duzenle");
        GridPane.setConstraints(randevuDuzenleBtn, 0, 15);

        Button anaMenuBtn = new Button("Ana Menüye git");
        GridPane.setConstraints(anaMenuBtn, 0, 16);

        btn1.setOnAction(e -> {

            if (tf1.getText().toString().isEmpty()) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Hata");
                String s = "Tc Kimlik No boş";
                a.setContentText(s);

                a.showAndWait();
            } else {
                List<Randevu> rList = VeriTabaniIslemleri.arama(tf1.getText().toString());
                ObservableList<Randevu> data = FXCollections.observableArrayList(
                        rList
                );
                table.setItems(data);

            }
        });

        randevuDuzenleBtn.setOnAction(e -> {
            String tc = tf1.getText().toString();
            String tarih = dP1.getValue().getDayOfMonth() + "/" + dP1.getValue().getMonthValue() + "/" + dP1.getValue().getYear();
            //Date tarih=new Date(dP1.getValue().getDayOfMonth()/dP1.getValue().getMonthValue()/dP1.getValue().getYear());
            Randevu secilenRandevu = (Randevu) table.getSelectionModel().getSelectedItem();

            //Seçilen randevunun tarih ve saat bilgileri alınıyor...
            Date secilenTarih = secilenRandevu.getTarih();
            String secilenSaat = secilenRandevu.getSaat();

            //alınan tarih bilgileri,date formatına çevriliyor
            int gun = dP1.getValue().getDayOfMonth(), ay = dP1.getValue().getMonthValue(), yil = dP1.getValue().getYear();
            System.out.println("gun= " + dP1.getValue().getDayOfMonth() + "ay= " + dP1.getValue().getMonthValue());

            Calendar c = Calendar.getInstance();
            c.set(yil, ay - 1, gun);
            //alınan tarih bilgileri,Date türüne çevrildi...
            Date tarihDate = new Date(c.getTimeInMillis());

            Randevu guncellenecekRandevu = new Randevu();
            //seçilen randevunun id si olmadığı için TcNo ile arama yapılıp, donen randevu objesi kullanılacak
            //tarih kolonu her kayda özgü olduğu için,eşleşme kontrolü bu şekilde yapılacak
            List<Randevu> tcEslesenRandevular = VeriTabaniIslemleri.arama(tc);
            for (int i = 0; i < tcEslesenRandevular.size(); i++) {
                if (tcEslesenRandevular.get(i).getTarih().equals(secilenTarih)
                        && tcEslesenRandevular.get(i).getSaat().equals(secilenSaat)) {
                    guncellenecekRandevu = tcEslesenRandevular.get(i);
                }
            }

            VeriTabaniIslemleri.guncelle(guncellenecekRandevu, tc, tf2.getText().toString(), tf3.getText().toString(), klinikler.getValue().toString(), tarihDate,
                    saatler.getValue().toString());
        });

        anaMenuBtn.setOnAction(e -> {
            SecimEkrani s = new SecimEkrani();
            s.start(primaryStage);
        });

        randevuSilBtn.setOnAction(e -> {
            VeriTabaniIslemleri.randevuSil(tf1.getText());
        });

        grid.getChildren().addAll(lbl1, randevuDuzenleBtn, randevuSilBtn, table, tf1, btn1, lbl2, tf2, lbl3, tf3, lbl4, lbl5, lbl6, klinikler, dP1, saatler, anaMenuBtn);
        Scene scene = new Scene(grid, 500, 600);
        window.setScene(scene);
        window.show();

    }
}
