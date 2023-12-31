package hu.nye.progtech.wumplusz.service.input;


import java.util.InputMismatchException;
import java.util.List;

import hu.nye.progtech.wumplusz.model.MapVO;
import hu.nye.progtech.wumplusz.model.enums.Entity;
import hu.nye.progtech.wumplusz.model.enums.GamePlayInstructions;
import hu.nye.progtech.wumplusz.model.enums.HeroDirection;
import hu.nye.progtech.wumplusz.service.throwable.ExitChoiceThrowable;
import hu.nye.progtech.wumplusz.service.util.InstructionOutputWriter;

/**
 * Komponens, ami kezeli a felhaszálói interakciót.
 */
public class UserInteractionHandler {

    private final InputReader inputReader;

    public UserInteractionHandler(InputReader inputReader) {
        this.inputReader = inputReader;
    }

    /**
     * Lekéri a felhasználó nevét.
     */
    public String getUsername() {
        System.out.print("Add meg a neved: ");
        return inputReader.readString();
    }

    /**
     * Lekéri a felhasználótól a menüpontot.
     */
    public Integer getMenuPoint() {
        InstructionOutputWriter.printMenu();
        return inputReader.readInteger();
    }

    /**
     * Lekéri az entitást a fasználótól.
     * Végigloopolva az entitás típusok között, ha nem találja az adott bekért entitás nevet,
     * vagyis nem létezik ilyen névvel, akkor bekéri újra.
     * Ezt addig teszi, amíg nem helyeset ad meg a felhasználó.
     */
    public Entity getChosenEntity(List<Entity> availableEntities) throws ExitChoiceThrowable {
        InstructionOutputWriter.printEntity(availableEntities);
        String entityChoice = inputReader.readString();
        if ("KILEPES".equals(entityChoice) || "K".equals(entityChoice)) {
            throw new ExitChoiceThrowable();
        }
        Entity result = null;
        for (Entity entity : Entity.values()) {
            if (entity.name().equals(entityChoice)) {
                result = Entity.valueOf(entityChoice);
            } else if (entityChoice.length() == 1 && entity.getLabel().equals(entityChoice.charAt(0))) {
                result = Entity.valueOfLabel(entityChoice.charAt(0));
            }
        }
        if (result == null) {
            System.out.println("Nem letezo entitas, probald ujra!");
            return getChosenEntity(availableEntities);
        }
        return result;
    }

    /**
     * Lekéri a koordinátát a felhasználótól.
     * Ha olyan koordinátát kap meg, ami a pályán kívülre esik, akkor újra bekéri
     * addig, amíg nem helyeset kap meg.
     */
    public Integer getCoordinate(MapVO mapVO, String coordinateType) {
        Integer coordinate;
        do {
            InstructionOutputWriter.printCoordinate(coordinateType);
            coordinate = inputReader.readInteger();
        } while (!mapVO.isInsideMap(coordinate));
        return coordinate;
    }

    /**
     * Bekéri a hős következő irányát.
     */
    public HeroDirection getHeroDirection() {
        InstructionOutputWriter.printGetHeroDirection();
        String input = inputReader.readString();
        HeroDirection heroDirection = null;
        for (HeroDirection hd : HeroDirection.values()) {
            if (hd.name().equals(input)) {
                heroDirection = hd;
            }
        }
        if (heroDirection == null) {
            return getHeroDirection();
        } else {
            return heroDirection;
        }
    }

    /**
     * Bekéri a következő játékbeli lépést.
     * Ha felad, akkor dob egy ExitChoiceThrowablet.
     * Ha nem létezik, jelzi azt.
     */
    public GamePlayInstructions getChosenGamePlayInstruction(List<GamePlayInstructions> gamePlayInstructions) throws ExitChoiceThrowable {
        InstructionOutputWriter.printGamePlayChoices(gamePlayInstructions);
        String gamePlayInstructionChoice = inputReader.readString();
        if ("FELAD".equals(gamePlayInstructionChoice) || "felad".equals(gamePlayInstructionChoice)) {
            throw new ExitChoiceThrowable();
        }
        GamePlayInstructions result = null;
        for (GamePlayInstructions gamePlayInstruction : GamePlayInstructions.values()) {
            if (gamePlayInstruction.name().equals(gamePlayInstructionChoice)) {
                result = GamePlayInstructions.valueOf(gamePlayInstructionChoice);
            } else if (gamePlayInstruction.getLabel().equals(gamePlayInstructionChoice)) {
                result = GamePlayInstructions.valueOfLabel(gamePlayInstructionChoice);
            }
        }
        if (result == null) {
            System.out.println("\nNem letezo instrukcio, probald ujra!");
            return getChosenGamePlayInstruction(gamePlayInstructions);
        }
        return result;
    }
}
