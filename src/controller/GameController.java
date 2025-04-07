package src.controller;

import src.model.GameModel;
import src.view.GameView;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class GameController implements ActionListener, KeyListener {
    private GameModel model;
    private GameView view;
    private Timer gameLoop;
    private Timer pipeTimer;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        view.addKeyListener(this);

        gameLoop = new Timer(1000 / 60, this);
        pipeTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.addPipes();
            }
        });
        gameLoop.start();
        pipeTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!model.gameOver) {
            model.update();
            view.repaint();
        } else {
            pipeTimer.stop();
            gameLoop.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            model.velocityY = -9;
            if (model.gameOver) {
                model.reset();
                gameLoop.start();
                pipeTimer.start();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }
}
