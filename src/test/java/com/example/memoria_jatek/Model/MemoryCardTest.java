package com.example.memoria_jatek.Model;

import com.example.memoria_jatek.ViewController.Game;
import javafx.application.Platform;
import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemoryCardTest {

    private MemoryCard card1;


    @BeforeAll
    static void setUpClass() {
        // Inicializálja a JavaFX platformot
        Platform.startup(() -> {});
    }

    @BeforeEach
    void setUp() {
        //Létrehozunk egy példányt az egyik képből
        card1 = new MemoryCard(Game.class.getResource("/images/image0.jpg").toExternalForm());

    }


    @Test
        //Összehasonlítjuk a leforddított és nem lefordított kép URL-jét hogy ugyan az-e
    void testHide() {
        card1.show();
        card1.hide();
        Image hiddenImage = new Image(MemoryCard.getunknown());
        assertEquals(hiddenImage.getUrl(), card1.getImage().getUrl());
    }

    @Test
        //Itt a két felfordított lap URL-jét hasonlítjuk
    void testShow() {
        card1.show();
        Image shownImage = new Image(card1.getUrl());
        assertEquals(shownImage.getUrl(), card1.getImage().getUrl());
    }
}
