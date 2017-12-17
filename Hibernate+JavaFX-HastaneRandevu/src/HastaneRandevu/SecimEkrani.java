/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HastaneRandevu;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author AKM
 */
public class SecimEkrani extends Application {

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
        
        Label lbl1 =new Label("Hoşgeldiniz");
        GridPane.setConstraints(lbl1, 0, 0);
        
        Button randevuEkleBtn =new Button("Yeni Randevu Ekle");
        GridPane.setConstraints(randevuEkleBtn , 0, 1);
        
        Button randevuListeleBtn =new Button("Randevuları Listele");
        GridPane.setConstraints(randevuListeleBtn , 1, 1);
        
        randevuEkleBtn.setOnAction(e->{
            YeniRandevu yr =new YeniRandevu();
            yr.start(primaryStage);
        });
        
        randevuListeleBtn.setOnAction(e->{
            RandevuListele rL =new RandevuListele();
            rL.start(primaryStage);
        });
        
        
        
        grid.getChildren().addAll(lbl1,randevuEkleBtn,randevuListeleBtn);
        Scene scene=new Scene(grid,250,150);
        window.setScene(scene);
        window.show();
        
    }
}
