package hu.nye.progtech.wumplusz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import hu.nye.progtech.wumplusz.model.GameStore;
import hu.nye.progtech.wumplusz.repository.impl.JdbcGameRepository;
import hu.nye.progtech.wumplusz.repository.impl.TxtGameRepository;
import hu.nye.progtech.wumplusz.service.game.GameController;
import hu.nye.progtech.wumplusz.service.game.GamePlayController;
import hu.nye.progtech.wumplusz.service.game.GamePlayStepController;
import hu.nye.progtech.wumplusz.service.input.InputReader;
import hu.nye.progtech.wumplusz.service.input.UserInteractionHandler;
import hu.nye.progtech.wumplusz.service.map.MapEditor;
import hu.nye.progtech.wumplusz.service.util.FileUtil;

/**
 * Belepési pont a Wumplusz jatekba.
 */
public class Main {

    /**
     * Belepesi pont.
     *
     * @param args parancssori argumentumok
     */
    public static void main(String[] args) {
        InputReader inputReader = new InputReader(new Scanner(System.in));
        UserInteractionHandler interactionHandler = new UserInteractionHandler(inputReader);
        GameStore gameStore = new GameStore();
        MapEditor mapEditor = new MapEditor(inputReader, gameStore, interactionHandler);
        FileUtil fileUtil = new FileUtil();
        TxtGameRepository txtGameRepository = new TxtGameRepository(fileUtil);
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        } catch (SQLException e) {
            System.out.println("Hiba az adatbazis kapcsolat nyitasanal: " + e);
            System.exit(1);
        }

        JdbcGameRepository jdbcGameRepository = new JdbcGameRepository(connection, gameStore);
        GamePlayStepController gamePlayStepController = new GamePlayStepController();
        GamePlayController gamePlayController = new GamePlayController(gameStore, interactionHandler, gamePlayStepController);
        GameController gameController = new GameController(interactionHandler, gameStore, mapEditor,
                txtGameRepository, jdbcGameRepository, gamePlayController);
        gameController.handlePreStart();
        gameController.handleMenu();
    }
}
