package hu.nye.progtech.wumplusz.model.enums;

/**
 * Játékbeli lépés típus.
 */
public enum GamePlayInstructions {
    LEP("lép"),
    FORDUL_JOBBRA("jobb"),
    FORDUL_BALRA("bal"),
    LO("lő"),
    ARANYAT_FELSZED("felszed"),
    FELAD("felad"),
    HALASZTAS("halaszt");

    private final String label;

    private GamePlayInstructions(String label) {
        this.label = label;
    }

    /**
     * Visszaadja az instrukció címkéjét.
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * Megkeresi az adott típust a megfelelő label alapján.
     */
    public static GamePlayInstructions valueOfLabel(String label) {
        for (GamePlayInstructions g : values()) {
            if (g.label.equals(label)) {
                return g;
            }
        }
        return null;
    }
}
