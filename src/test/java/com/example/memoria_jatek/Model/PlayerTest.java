package com.example.memoria_jatek.Model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @TempDir
    Path tempDir;

    @Test
    void testWriteToJson() throws IOException {
        // Adatokkal inicializált játékos létrehozása
        Player player = new Player();
        player.setJatekos_nev("Teszt Játékos");
        player.setTalalati_arany(0.75);
        player.setHiba_arany(0.25);
        player.setHibapont(3);
        player.setOsszes_kattintas(12);
        player.setIdo(60000);

        // Teszt json fájl létrehozása az ideiglenes könyvtárban
        String tempJsonFilePath = tempDir.resolve("Teszt_Jatekos.json").toString();
        File tempJsonFile = new File(tempJsonFilePath);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(tempJsonFile, player);

        // Ellenőrizzük, hogy a fájl létezik-e és olvasható-e
        assertTrue(tempJsonFile.exists());
        assertTrue(tempJsonFile.canRead());

        // A fájl tartalmának összehasonlítása a játékos adataival
        Player readPlayer = mapper.readValue(tempJsonFile, Player.class);
        assertEquals(player.getJatekos_nev(), readPlayer.getJatekos_nev());
        assertEquals(player.getTalalati_arany(), readPlayer.getTalalati_arany());
        assertEquals(player.getHiba_arany(), readPlayer.getHiba_arany());
        assertEquals(player.getHibapont(), readPlayer.getHibapont());
        assertEquals(player.getOsszes_kattintas(), readPlayer.getOsszes_kattintas());
        assertEquals(player.getIdo(), readPlayer.getIdo());
    }
}
