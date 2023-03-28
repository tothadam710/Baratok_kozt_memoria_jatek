package com.example.memoria_jatek.Controller;

import com.example.memoria_jatek.ViewController.Game;
import com.example.memoria_jatek.ViewController.Menu;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller {

    private Stage stage;

    public Controller(Stage stage) {
        this.stage = stage;
        Menu menu=Menu.showView(this,stage);
    }


    public Boolean goToPlay()   {
        Game game = Game.showView(this,stage);
        return true;
    }


    public Boolean goToPlayAgain() {

        Game game =Game.showView(this,stage);
        return true;
    }

    public boolean kilep() {

        System.exit(0);

        return true;
    }


}

