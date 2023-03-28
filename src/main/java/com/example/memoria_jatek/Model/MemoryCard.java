package com.example.memoria_jatek.Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class MemoryCard extends ImageView {

    private static final String unknown=MemoryCard.class.getResource("/images/unknown.gif").toExternalForm();

    private String url;
    private boolean flipped;


    public MemoryCard(String url) {
        super(unknown);
        this.url=url;
    }

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

    public void hide(){
        this.setImage(new Image(unknown));
    }

    public void show(){
        this.setImage(new Image(url));

    }







}
