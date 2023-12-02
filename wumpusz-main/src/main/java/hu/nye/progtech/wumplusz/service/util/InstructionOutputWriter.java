package hu.nye.progtech.wumplusz.service.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import hu.nye.progtech.wumplusz.model.UserData;
import hu.nye.progtech.wumplusz.model.enums.Entity;
import hu.nye.progtech.wumplusz.model.enums.GamePlayInstructions;

/**
 * Util osztály, amely az interakció előtti utasítások kiíratásáért felelős.
 */
public class InstructionOutputWriter {

    public static final String X_COORDINATE = "X";

    public static final String Y_COORDINATE = "Y";

    /**
     * Kiírja a kiválasztható menüpontokat.
     */
    public static void printMenu() {
        System.out.println("Valassz a menupontokbol:");
        System.out.println("1. Palyaszerkesztes");
        System.out.println("2. Beolvasas fajlbol");
        System.out.println("3. Betoltes adatbazisbol");
        System.out.println("4. Mentes adatbazisbol");
        System.out.println("5. Jatek");
        System.out.println("6. Toplista tablazat");
        System.out.println("7. Kilepes");
    }

    /**
     * Kiírja a választható entitásokat, amik a listában megtalálhatóak.
     */
    public static void printEntity(List<Entity> availableEntities) {
        System.out.println("Valassz egy entitas a lehelyezeshez. Kilepeshez: 'KILEPES'");
        for (Entity entity : availableEntities) {
            System.out.print(entity.name() + " (" + entity.getLabel() + ") ");
        }
        System.out.println();
    }

    /**
     * Kiírja, hogy milyen koordinátát írjon be a felhasználó.
     */
    public static void printCoordinate(String coordinateType) {
        System.out.printf("Add meg a %s koordinatat: ", coordinateType);
    }

    /**
     * Kiírja, hogy milyen hős irányt választhatunk.
     */
    public static void printGetHeroDirection() {
        System.out.println("Adja meg a hos kezdezi iranyat (N/W/S/E)");
    }

    /**
     * Kiírja a lehetséges akciókat.
     */
    public static void printGamePlayChoices(List<GamePlayInstructions> gamePlayInstructionsList) {
        System.out.println("Valasz egy akciot:");
        for (GamePlayInstructions gamePlayInstructions : gamePlayInstructionsList) {
            System.out.print(gamePlayInstructions + " ");
        }
    }

    /**
     * Kiírja a highscore-t.
     */
    public static void printHighScore(List<UserData> userDatas) {
        System.out.println("TOPSCORE: ");
        List<UserData> sorted =
                userDatas.stream().sorted(Comparator.comparing(UserData::getWins)).collect(Collectors.toList());
        for (int i = sorted.size() - 1; i >= 0; i--) {
            System.out.println(sorted.get(i).getUsername() + ": " + sorted.get(i).getWins() + " nyeres");
        }
        System.out.println();
    }
}
