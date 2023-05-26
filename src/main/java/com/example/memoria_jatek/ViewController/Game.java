package com.example.memoria_jatek.ViewController;

import com.example.memoria_jatek.Controller.Controller;
import com.example.memoria_jatek.Model.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import com.example.memoria_jatek.Model.MemoryCard;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/*
Ez az osztály tartalmazza a Játék logikáját.

Mezők amikre szükségünk lesz:

    - controller: Ez egy példány a Controller osztályból a megfelelő kommunikáció biztosítására a Controller osztállyal
    - cards: Ez egy MemoryCard tipusu lista a kártyák tárolására(8 pár van összesen így 8 pont szerezhető)
    - selectedcard: MemoryCard típusú objektum ami az aktuális kártyát reprezentálja
    - replayButton: ez egy gomb ami az újrajátszást biztosítja
    - Alafestő_zene: Ez egy Audioclip tipisu zene ami a játékközbeni zenét biztosítja
    - correct: Ez is egy hang ami akkor szólal meg ha a játékos eltalál egy párt és pontot szerez
    - fail: Ez double változó ami a hibapontokat tárolja a játékvégi statisztikáho és később ez lesz egyenlő a játékso hibapontjával

 */

public class Game {
    public static Controller controller;
    static List<MemoryCard>cards;
    static MemoryCard selectedCard;
    static Button replayButton;

    static AudioClip Alafesto_zene;

    static AudioClip correct;

    static double fail = 0;


    //EZ a metódus a játék szekvenciáját reprezentálja.Addig él amíg a játék megy.Végűl egy NULL-al tér vissza
    public static Game showView(Controller c, Stage stage) {

            //játékos példányosítása a név megadásához egy dialogban és a későbbi adatok letárolásához(Ha cancel-t nyomunk kilép)

             Player player = new Player();

            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Új Lakó a Máytás kiráy téren");
            dialog.setHeaderText("Lakó hozzáadása");
            dialog.setContentText("Kérem a nevet:");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                String p_name = result.get();
                player.setJatekos_nev(p_name);

            }

            else{
                System.exit(0);
            }

        // Miután a játkos beírta a nevét e játék elkezdődik és a zene is elindul.
        Alafesto_zene= new AudioClip(Menu.class.getResource("/sounds/BK_alafesto.mp3").toExternalForm());
        Alafesto_zene.setCycleCount(AudioClip.INDEFINITE);
        Alafesto_zene.play();
        correct = new AudioClip(Menu.class.getResource("/sounds/correct.mp3").toExternalForm());


        //controller példány belemásolása a c változóba
        controller=c;

        //új lista inicializálása a kártyák számára
        cards=new ArrayList<>();
        //A tábla meghatározása ahola kártyák lesznek
        GridPane rootSceen=new GridPane();
        VBox box=new VBox();


        /* A tábla feltöltése a kártyákkal(2x8).Mindegyik kártya 2 példánnyal kerül fel a tábára egy ciklus segyítségével.
            Miután a kártya létrehozásra került beállítódik a hossza és a szélessége.
            Ezután beállítódik az azonosítójuk is amely összehasonlításra kerül majd.
            Végül a kártyák a Collections osztály shuffle metódusával összekeverődnek
         */
        int inc=0;
        for(int i=0;i<=7;i++){
            MemoryCard card1=new MemoryCard(Game.class.getResource("/images/image"+String.valueOf(i)+".jpg").toExternalForm());

            card1.setFitWidth(170);
            card1.setFitHeight(120);
            card1.setPreserveRatio(true);
            MemoryCard card2=new MemoryCard(Game.class.getResource("/images/image"+String.valueOf(i)+".jpg").toExternalForm());
            card2.setFitWidth(170);
            card2.setFitHeight(120);
            card2.setPreserveRatio(true);

            card1.setId(String.valueOf(inc));
            card2.setId(String.valueOf(inc++));
            inc++;

            cards.add(card1);
            cards.add(card2);
        }
        Collections.shuffle(cards);

        /*
          Innentől kezdődik a játék.A játékos az egérrel egy eseménykezelőben kiválaszt 2 kártyát.Ezek
          összehasonlításra kerülnek.Ha a 2 kártya nem egyezik meg és az isFlipped() metródus sem igaz rá tehát fel van fordítva
          akkor a hide metódus alkalmazásra kerül így ha 2 különböző kártya kerül kiválasztásra akkor azok "visszabújnak" és a fail változó növelődik 1-el.
          Viszont ha az URL-jük alapján megegyezik a lista jelenlegi kártálya(card) és a választott kártya(selectedCard) és nem ugyan az a kártya
          akor mindkettő felfordítva marad a setFlipped metódussal és lejátszódik a correct.mp3 fájl
        */


        inc=0;
        for(int i=0;i<=3;i++){
            long start = System.currentTimeMillis();
            HBox hBox=new HBox();
            for(int j=0;j<=3;j++){
                MemoryCard card=cards.get(inc);
                card.setOnMouseExited(x ->{
                    if( card != selectedCard && !card.isFlipped()){
                        card.hide();
                    }
                });

                card.setOnMouseClicked(x->{
                    if(card.isFlipped()){
                        return ;
                    }
                    if(selectedCard!=null && !selectedCard.isFlipped()){
                        if(selectedCard!=card && card.getUrl().equals(selectedCard.getUrl())){
                            selectedCard.show();
                            card.show();
                            selectedCard.setFlipped(true);
                            card.setFlipped(true);
                            correct.play();
                        }

                        else{
                            card.show();
                            selectedCard.hide();
                            selectedCard=null;
                            fail++; // hibas probalkozas
                        }
                    }
                    else{
                        selectedCard=card;
                        selectedCard.show();

                    }

                    //EZ az if ág megviszgálja hogy a játékos megtalálta-e a párokat(4pár) és az if ág blokkjában a következők futnak le:

                        /*
                        -leáll a számláló ész E_time-ba elmentődik az idő ami alatt végzett a játékos
                        -Megáll a játék zenéje és elindul a végzene
                        -Előugrik egy alert ablak ami a "Gratulálunk" címmel a játékos elért pontjait mutatja(Egy kis számolás után)

                         */


                    if(checkWon(cards)){
                        long end = System.currentTimeMillis();
                        long E_time = end - start;
                        Alafesto_zene.stop();
                        AudioClip Vegzene= new AudioClip(Menu.class.getResource("/sounds/BK_vege.mp3").toExternalForm());
                        Vegzene.play();
                        Alert alert=new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Gratulálunk");

                        double  tal_ar = Math.round(8.0/(fail+8.0)*100.0)/100.0;
                        double hib_ar =  Math.round(fail/(fail+8.0)*100.0)/100.0;
                        int hib_pont = (int)fail;
                        int ossz = (int)(fail + 8);

                        //Ez az alertbox-ra lesz kiiratva
                        alert.setContentText("Megtaláltad az összes párt és ezzel feltámasztottad Magdi anyust!\n" +
                                "\njatekos neve: "+player.getJatekos_nev() +
                                "\nTalálati arányod: "+tal_ar + " %"+
                                "\nHiba arányod: "+hib_ar+ " %"+
                                "\nHibapont: "+hib_pont +
                                "\nÖsszes kattintás: "+ossz +
                                "\nIdő : "+E_time + " ms");

                        alert.show();
                        //újrajátszás gomb megjelenik
                        replayButton.setVisible(true);


                        // a player objektumba elmentődne a játékos adatai
                       player.setTalalati_arany(tal_ar);
                        player.setHiba_arany(hib_ar);
                        player.setHibapont(hib_pont);
                        player.setOsszes_kattintas(ossz);
                        player.setIdo(E_time);

                        //Majd egy kivételkezelés keretében elmentődnek az adatok egy JSON file-ba
                        try {
                            Player.WriteToJson(player);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }


                    }

                });

                //A tábla és a kártyák megjelenésének beállításai(térköz,pozicionálás stb)
                card.setId(String.valueOf(inc));
                hBox.setPadding(new Insets(10,10,1,40));
                hBox.setAlignment(Pos.CENTER);
                hBox.setSpacing(10);
                hBox.getChildren().add(card);
                inc++;
            }
            box.getChildren().add(hBox);

        }

        //újrajátszógomb megjelenésének beállításai
        replayButton=new Button();
        replayButton.setText("Játszd újra");
        replayButton.setPadding(new Insets(10,10,10,10));
        replayButton.setVisible(false);

        // egy lambda kifejezésben megadjuk hogy hívja meg a goToPlayAgain() metódust a replaygomb(de dönthetünk a kilépésnél is)
        replayButton.setOnMouseClicked(x->{
            controller.goToPlayAgain();
        });
        box.getChildren().add(replayButton);

        box.setAlignment(Pos.CENTER);


        // A fő játékablak megjelenítése
        rootSceen.add(box,0,0);

        stage.setTitle("Barátok közt memória játék");
        stage.setHeight(600);
        stage.setWidth(600);
        stage.setScene(new Scene(rootSceen));
        stage.show();

        // Ez fontos h a metódus végén 0 legyen hiszen új játék kezdődik
        fail = 0;

        return null; // null-al tér vissza a metódus

    }

    // Ez a függvény egy logikai tipust ad vissza arra vonatkozóan hogy megtaláltuk-e az összes párt azaz nyertünk-e vagy sem(végigiterál a kártyapárokon és megvizsgálja hogy mind felfordított-e)
    private static boolean checkWon(List<MemoryCard> cards) {
        for(MemoryCard card:cards){
            if(card.isFlipped()==false){
                return false;
            }
        }
        return true;
    }




}
