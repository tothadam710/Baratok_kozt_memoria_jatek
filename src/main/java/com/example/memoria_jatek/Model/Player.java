package com.example.memoria_jatek.Model;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/*
EZ az osztály a játkos adatok kezeléésért felel és azért hogy az eredmények elmentődjenek
és Json fájlba kerüljenek a játékos nevével

Mezők:
        -játékos_név: A játékos neve ami majd a Json file neve is lesz.
        -találati arány: Ez tárolja azt ha  a játékos talált párjainak arányát(megoszlik a hibbaránnyal, és 1 vagy annál kisebb lehet csak)
        -hiba_arány: Ez tárolja azt ha  a játékos nem talált párjainak aránynát(megoszlik a találati aránnyal, és 1 vagy annál kisebb lehet csak)
        -hibapont: Ez tárolja a hibapontokat
        -összes_kattintás: Ez tárolja az összes kattintást ami a játékos által inputra kerül.
        -idő: Ez tárolja a játékidőt amíg a játékos megtalálja a párokat millisekundumban


 */


public class Player {

    private String jatekos_nev;
    private double talalati_arany;
    private double hiba_arany;

    private int hibapont;

    private int osszes_kattintas;

    private long ido;

    //getterek és setterek
    public String getJatekos_nev() {
        return jatekos_nev;
    }

    public void setJatekos_nev(String jatekos_nev) {
        this.jatekos_nev = jatekos_nev;
    }

    public double getTalalati_arany() {
        return talalati_arany;
    }

    public void setTalalati_arany(double talalati_arany) {
        this.talalati_arany = talalati_arany;
    }

    public double getHiba_arany() {
        return hiba_arany;
    }

    public void setHiba_arany(double hiba_arany) {
        this.hiba_arany = hiba_arany;
    }

    public int getHibapont() {
        return hibapont;
    }

    public void setHibapont(int hibapont) {
        this.hibapont = hibapont;
    }

    public int getOsszes_kattintas() {
        return osszes_kattintas;
    }

    public void setOsszes_kattintas(int osszes_kattintas) {
        this.osszes_kattintas = osszes_kattintas;
    }

    public long getIdo() {
        return ido;
    }

    public void setIdo(long ido) {
        this.ido = ido;
    }

    //default konstruktor
    public Player() {
    }


    //EZ a metódus az Objectmappert veszi segítségűl hogy kiírja az eredményeket a játékosnévvel ellátot JSON file-ba.
    public  static void WriteToJson(Player p) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String filePath = "src/main/resources/results/"+p.getJatekos_nev()+".json";
        mapper.writeValue(new File(new File(filePath).getAbsolutePath()), p);

    }


}
