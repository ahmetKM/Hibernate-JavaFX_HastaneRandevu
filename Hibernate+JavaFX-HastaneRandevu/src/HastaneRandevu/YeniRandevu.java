/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HastaneRandevu;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author AKM
 */
public class YeniRandevu extends Application {

    Stage window;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage){
        window=primaryStage;
        window.setTitle("Hoşgeldiniz");
        
        GridPane grid=new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);   
        
        Label lbl1 =new Label("Hasta Adı: ");
        GridPane.setConstraints(lbl1, 0, 0);
        
        TextField tf1 =new TextField();
        GridPane.setConstraints(tf1, 1, 0);
        
        Label lbl2 =new Label("Hasta Soyadı: ");
        GridPane.setConstraints(lbl2, 0, 1);
        
        TextField tf2 =new TextField();
        GridPane.setConstraints(tf2, 1, 1);
        
        Label lbl3 =new Label("Tc Kimlik No: ");
        GridPane.setConstraints(lbl3, 0, 2);
        
        TextField tf3 =new TextField();
        GridPane.setConstraints(tf3, 1, 2);
        
        Label lbl4 =new Label("klinik: ");
        GridPane.setConstraints(lbl4, 0, 3);
        
        ComboBox<String> klinikler=new ComboBox<String>();
        klinikler.getItems().addAll("Ortopedi","Nöroloji","Kalp ve Damar",
                "Göğüs Hastalıkları","Kardiyokoji","Kulak Burun Boğaz");
        GridPane.setConstraints(klinikler, 1, 3);
        
        Label lbl5 =new Label("Tarih: ");
        GridPane.setConstraints(lbl5, 0, 4);
        
        DatePicker dP1 =new DatePicker();
        GridPane.setConstraints(dP1, 1, 4);
        
        Label lbl6 =new Label("Saat: ");
        GridPane.setConstraints(lbl6, 0, 5);
        
        ComboBox<String> saatler=new ComboBox<String>();
        saatler.getItems().addAll("08:00","09:00","10:00",
                "11:00","13:00","14:00","15:00","16:00");
        GridPane.setConstraints(saatler, 1, 5);
        
        Button randevuEkleBtn =new Button("Yeni Randevu Ekle");
        GridPane.setConstraints(randevuEkleBtn , 0, 6);
        
        Button geriBtn =new Button("Geri");
        GridPane.setConstraints(geriBtn , 1, 6);
        
        geriBtn.setOnAction(e->{
            SecimEkrani s=new SecimEkrani();
            s.start(primaryStage);
        });
        
        randevuEkleBtn.setOnAction(e->{
            String tarih=dP1.getValue().getDayOfMonth()+"/"+dP1.getValue().getMonthValue()+"/"+dP1.getValue().getYear();
            //Date tarih=new Date(dP1.getValue().getDayOfMonth()/dP1.getValue().getMonthValue()/dP1.getValue().getYear());
            
            System.out.println("tarih= "+tarih);
            int gun=dP1.getValue().getDayOfMonth(),ay=dP1.getValue().getMonthValue(),yil=dP1.getValue().getYear();
            System.out.println("gun= "+dP1.getValue().getDayOfMonth()+"ay= "+dP1.getValue().getMonthValue());
            
            
            Calendar c=Calendar.getInstance();
            c.set(yil, ay-1, gun);
            //c.toString()
            Date tarihDate=new Date(c.getTimeInMillis());

            System.out.println("date olan tarih="+tarihDate);
            boolean hataYok=inputKontrol(tf1.getText().toString(),tf2.getText().toString(),tf3.getText().toString(),klinikler.getValue().toString(),tarihDate,saatler.getValue().toString());
            if(hataYok==true){
                VeriTabaniIslemleri.kayitEkle(tf1.getText().toString(),tf2.getText().toString(),tf3.getText().toString(),klinikler.getValue().toString(),tarihDate,saatler.getValue().toString());
            }
        });
        
        grid.getChildren().addAll(lbl1,lbl2,lbl3,lbl4,lbl5,lbl6,tf1,tf2,tf3,randevuEkleBtn,geriBtn,klinikler,dP1,saatler);
        Scene scene=new Scene(grid,400 ,250);
        window.setScene(scene);
        window.show();
        
    }
    
    public static boolean inputKontrol(String adi,String soyadi,String tcKimlikNo,String klinik,Date tarih,String saat ){
        boolean hataYok=false;
        if(adi.isEmpty() || soyadi.isEmpty() || tcKimlikNo.isEmpty() || klinik.isEmpty() || saat.isEmpty()){
            Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Hata");
                String s="Tüm alanları doldurun";
                alert.setContentText(s);
                
                alert.showAndWait();
                
                hataYok=false;
        }
        if(tarih==null){
            Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Hata");
                String s="Tarih seçin";
                alert.setContentText(s);
                
                alert.showAndWait();
                
                hataYok=false;
        }
        else{
            hataYok=true;
        }
        return hataYok;
    }
}
