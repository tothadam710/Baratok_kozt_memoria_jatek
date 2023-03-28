package com.example.memoria_jatek.ViewController;


import com.example.memoria_jatek.Controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Menu {



    public static Controller controller;




    public static AudioClip Kezdozene;
    public static Menu showView(Controller c, Stage stage) {
        controller=c;
        URL location=Menu.class.getResource("/menu_view.fxml");
        FXMLLoader fxmlLoader=new FXMLLoader(location);
        Parent root=null;

        Kezdozene= new AudioClip(Menu.class.getResource("/sounds/BK_eleje.mp3").toExternalForm());
        Kezdozene.setCycleCount(AudioClip.INDEFINITE);
        Kezdozene.play();

        try {
            root=fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Menu vue=fxmlLoader.getController();
        stage.setTitle("Barátok közt memória játék");
        stage.setScene(new Scene(root,750,400));
        stage.show();
        return vue;

    }

    public void goToPlay(MouseEvent mouseEvent) {
        Kezdozene.stop();
        controller.goToPlay();
    }

    public void kilep(MouseEvent mouseEvent) {
        controller.kilep();
    }
}
