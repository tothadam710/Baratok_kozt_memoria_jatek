package com.example.memoria_jatek.Controller;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main  extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller=new Controller(primaryStage); //Controller osztaly peldanyositasa a fopőrogramban
    }


    public static void main(String[]args){
        launch();
    }
}
