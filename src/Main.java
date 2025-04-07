package src;

import javax.swing.JFrame;
import src.model.GameModel;
import src.view.GameView;
import src.controller.GameController;

public class Main {
    public static void main(String[] args) {
        int boardWidth = 360;
        int boardHeight = 640;
        JFrame frame = new JFrame("FlappyBird MVC");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        GameModel model = new GameModel(boardWidth, boardHeight);
        GameView view = new GameView(model);
        new GameController(model, view);

        frame.add(view);
        frame.pack();
        frame.setVisible(true);
        view.requestFocusInWindow();

    }
}
