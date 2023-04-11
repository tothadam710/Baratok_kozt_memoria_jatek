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

/*
Ez az osztály a Játék menüének a vezérlésért felelős.A kinézetét az FXML fájl biztosítja.
Egy példány itt is létrehozódik a Controller osztályból.
Egy Menu tipusu függvényt is tartalmaz az osztály(showView)
Először példányosítja a controllert majd betölti a "menu_view.fxml" fájlt hogy összekapcsolja a kinézettel
Közben a játék kezdőzenéje is meghatározásra kerül a sounds mappából(Sajnos csak windowson működik.Az Ubuntu gépemen nem tudja betölteni
a media player-t mert hiányzik egy media codec)
 */

    public static Controller controller;




    public static AudioClip Kezdozene;
    public static Menu showView(Controller c, Stage stage) {
        controller=c;
        URL location=Menu.class.getResource("/menu_view.fxml");
        FXMLLoader fxmlLoader=new FXMLLoader(location);
        Parent root=null;

        //Kezdőzene lejátszása
        Kezdozene= new AudioClip(Menu.class.getResource("/sounds/BK_eleje.mp3").toExternalForm());
        Kezdozene.setCycleCount(AudioClip.INDEFINITE);
        Kezdozene.play();

        //kivételkezelés ha véletlen probléma lenne az FXML fájlal
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


    //A menüből elérhető 2 gomb amire ha rákattintunk lefut Controller osztályból a megfelelő metódus(újrajátszás vagy kilépés)
    //Ha új játékot kezdünk a kezdőzene megáll és ha átkerűl a vezérlés a Game osztáylhoz ami a játék logikáját vezérli elindul egy másik zene.
    public void goToPlay(MouseEvent mouseEvent) {
        Kezdozene.stop();
        controller.goToPlay();
    }

    public void kilep(MouseEvent mouseEvent) {
        controller.kilep();
    }
}
