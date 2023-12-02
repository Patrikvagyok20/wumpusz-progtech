package hu.nye.progtech.wumplusz.model.enums;

/**
 * A hős irányának típusa.
 */
public enum HeroDirection {
    N("eszak"),
    W("nyugat"),
    S("del"),
    E("kelet");

    private final String label;

    private HeroDirection(String label) {
        this.label = label;
    }

    /**
     * Visszaadja az hős irány címkéjét.
     */
    public String getLabel() {
        return this.label;
    }
}
