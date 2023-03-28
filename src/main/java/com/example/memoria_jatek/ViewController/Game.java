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

public class Game {
    public static Controller controller;
    static List<MemoryCard>cards;
    static MemoryCard selectedCard;
    static Button replayButton;

    static AudioClip Alafesto_zene;

    static AudioClip correct;

    static double fail = 0;











    public static Game showView(Controller c, Stage stage) {

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


        Alafesto_zene= new AudioClip(Menu.class.getResource("/sounds/BK_alafesto.mp3").toExternalForm());
        Alafesto_zene.setCycleCount(AudioClip.INDEFINITE);
        Alafesto_zene.play();
        correct = new AudioClip(Menu.class.getResource("/sounds/correct.mp3").toExternalForm());



        controller=c;

        cards=new ArrayList<>();
        GridPane rootSceen=new GridPane();
        VBox box=new VBox();


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


        inc=0;
        for(int i=0;i<=3;i++){ //4 paronkent a kartyak osszehasonlitasa
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



                        }else{
                            card.show();
                            selectedCard.hide();
                            selectedCard=null;
                            fail++; // hibas probalkozas
                        }
                    }else{
                        selectedCard=card;
                        selectedCard.show();

                    }

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


                        alert.setContentText("Megtaláltad az összes párt és ezzel feltámasztottad Magdi anyust!\n" +
                                "\njatekos neve: "+player.getJatekos_nev() +
                                "\nTalálati arányod: "+tal_ar + " %"+
                                "\nHiba arányod: "+hib_ar+ " %"+
                                "\nHibapont: "+hib_pont +
                                "\nÖsszes kattintás: "+ossz +
                                "\nIdő : "+E_time + " ms");

                        alert.show();

                        replayButton.setVisible(true);



                       player.setTalalati_arany(tal_ar);
                        player.setHiba_arany(hib_ar);
                        player.setHibapont(hib_pont);
                        player.setOsszes_kattintas(ossz);
                        player.setIdo(E_time);


                        try {
                            Player.WriteToJson(player);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }


                    }

                });

                card.setId(String.valueOf(inc));
                hBox.setPadding(new Insets(10,10,1,40));
                hBox.setAlignment(Pos.CENTER);
                hBox.setSpacing(10);
                hBox.getChildren().add(card);
                inc++;
            }
            box.getChildren().add(hBox);

        }

        replayButton=new Button();
        replayButton.setText("Játszd újra");
        replayButton.setPadding(new Insets(10,10,10,10));
        replayButton.setVisible(false);
        replayButton.setOnMouseClicked(x->{
            controller.goToPlayAgain();
        });
        box.getChildren().add(replayButton);

        box.setAlignment(Pos.CENTER);






        rootSceen.add(box,0,0);

        stage.setTitle("Barátok közt memória játék");
        stage.setHeight(600);
        stage.setWidth(600);
        stage.setScene(new Scene(rootSceen));
        stage.show();

        fail = 0;

        return null;






    }

    private static boolean checkWon(List<MemoryCard> cards) {
        for(MemoryCard card:cards){
            if(card.isFlipped()==false){
                return false;
            }
        }
        return true;
    }




}
