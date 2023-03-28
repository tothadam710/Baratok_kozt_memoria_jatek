package com.example.memoria_jatek.Model;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;


public class Player {

    private String jatekos_nev;
    private double talalati_arany;
    private double hiba_arany;


    public String getJatekos_nev() {
        return jatekos_nev;
    }

    public void setJatekos_nev(String jatekos_nev) {
        this.jatekos_nev = jatekos_nev;
    }

    private int hibapont;

    private int osszes_kattintas;

    private long ido;

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

    public Player() {
    }


    public  static void WriteToJson(Player p) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String filePath = "src/main/resources/results/"+p.getJatekos_nev()+".json";
        mapper.writeValue(new File(new File(filePath).getAbsolutePath()), p);

    }


}
