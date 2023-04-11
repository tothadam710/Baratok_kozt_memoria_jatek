package com.example.memoria_jatek.Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/*
Ez az osztály a memóriajkártyák megjelenéséért és viselkedéséért felel.
Az osztály kiterjeszti az ImageView osztályt amely a képek megjelenését segíti a játékban.

Mezők:

    unknown - Ez egy final string amely a getResource segítségével egyből a resources mappából importálja be a képet
    így nincs szükség abszolút elérési útra.
    Ez a kép a kártyák hátoldalát reprezentálja.(Mivel ez mindig ugyanaz ezért lehet final)

    url - a képek elérési útja mej majd a konstruktorban is szerepel és majd a Game osztályban lesz meghívva konstruktor hívás álat
    és itt lesz átadva a kártyák elérése

    flipped - ez egy logikai érték ami azt tárolja hogy a kártya felvan-e forddítva vagy sem.

 */

public class MemoryCard extends ImageView {

    private static final String unknown=MemoryCard.class.getResource("/images/unknown.gif").toExternalForm();

    private String url;
    private boolean flipped;

    //Konstruktor amely tartalmazza az unknown illetve url mezoket a konstruktor hivasnal. a kártya hátoldala "bele van égetve" mert az mindig uyganaz
    public MemoryCard(String url) {
        super(unknown);
        this.url=url;
    }

    //getterek és setterek
    public static String getunknown() {
        return unknown;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isFlipped() {
        return flipped;
    }

    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }


    // az equals és hashCode metódusok túlterhelése azzal a céllal hogy az equals az URL-juk es ID-juk alapján összehasonlítsa a kártáykat és hashCode amely az URL hash alapján hasonlít
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemoryCard card = (MemoryCard) o;
        if(!((MemoryCard) o).getId().equals(this.getId())){
            return false;
        }
        return Objects.equals(url, card.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }

    //EZ a metódus a elrejti a kártyát
    public void hide(){
        this.setImage(new Image(unknown));
    }

    //Ez a metódus felfedi a kártyát
    public void show(){
        this.setImage(new Image(url));

    }


}
