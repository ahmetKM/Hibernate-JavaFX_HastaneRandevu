/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HastaneRandevu;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author AKM
 */
public class HastaneRandevu extends Application {

    Stage window;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage){
        window=primaryStage;
        window.setTitle("Hastane Otomasyon");
        
        GridPane grid=new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);
        
        Label kullaniciAdiLbl=new Label("Kullanıcı adı: ");
        GridPane.setConstraints(kullaniciAdiLbl, 0, 0);
        
        TextField kullaniciAdiTF=new TextField();
        GridPane.setConstraints(kullaniciAdiTF, 1, 0);
        
        Label sifreLbl=new Label("Şifre: ");
        GridPane.setConstraints(sifreLbl, 0, 1);
        
        TextField sifreTF=new TextField();
        GridPane.setConstraints(sifreTF, 1, 1);
        
        Button girisBtn =new Button("Giriş");
        GridPane.setConstraints(girisBtn, 0, 2);

        girisBtn.setOnAction(e-> {
            
            String kullaniciAdi=kullaniciAdiTF.getText().trim();
            
            boolean valid=true;
            
            if( (kullaniciAdi.isEmpty())){
                
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("mesaj");
                String s="Kullanıcı adını boş bıraktınız. "+
                        "Geçerli bir kullanıcı adı girin. ";
                alert.setContentText(s);               
                alert.showAndWait();
            }
            else {
                boolean sonuc=VeriTabaniIslemleri.girisKontrol(kullaniciAdiTF.getText().toString(),sifreTF.getText().toString());
                if(sonuc==true){
                    System.out.println("giriş başarılı");
                    SecimEkrani s =new SecimEkrani();
                    s.start(primaryStage);
                }
                else if(sonuc==false){
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("mesaj");
                    String s="Kullanıcı adı veya şifre yanlış";
                    alert.setContentText(s);
                
                    alert.showAndWait();
                }
            }
            
        });

        grid.getChildren().addAll(kullaniciAdiLbl,kullaniciAdiTF,sifreLbl,sifreTF,girisBtn);
        Scene scene=new Scene(grid,250,150);
        window.setScene(scene);
        window.show();
    }
    
}
