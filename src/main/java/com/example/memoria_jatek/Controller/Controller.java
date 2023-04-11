package com.example.memoria_jatek.Controller;

import com.example.memoria_jatek.ViewController.Game;
import com.example.memoria_jatek.ViewController.Menu;
import javafx.stage.Stage;


//Ez az oszály azért felel hogy a vezérlés a Menütől a játék főképernyójéig megfelelően működjön.




public class Controller {

    private Stage stage;

    //példányosít a Menü osztályból és meghívja a showView metódust ami a Menü kinézetéért felel
    public Controller(Stage stage) {
        this.stage = stage;
        Menu menu=Menu.showView(this,stage);
    }

    //A Game osztáylból(ami a játék üzleti logikájáért felel) pédányosít és meghívja a Game osztályban a showView metódust ami a játék vizuális megjelenésért felel.
    public void goToPlay()   {
        Game game = Game.showView(this,stage);

    }

    //Erre azért van szükség hogyha a játékos újra akarja játszani a játlkot.(újra meghívja a játékot)
    public void goToPlayAgain() {

        Game game =Game.showView(this,stage);

    }

    //Kilép a játékból
    public void kilep() {

        System.exit(0);


    }


}

