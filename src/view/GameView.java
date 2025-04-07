package src.view;

import javax.swing.JPanel;
import src.model.*;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;

public class GameView extends JPanel {
    private GameModel model;
    private Image backgroundImg;
    private Image birdImg;
    private Image bottomPipeImg;
    private Image topPipeImg;

    public GameView(GameModel model) {
        this.model = model;
        setFocusable(true);

        backgroundImg = new ImageIcon("flappybirdbg.png").getImage();
        birdImg = new ImageIcon("flappybird.png").getImage();
        bottomPipeImg = new ImageIcon("bottompipe.png").getImage();
        topPipeImg = new ImageIcon("toppipe.png").getImage();

        setPreferredSize(new java.awt.Dimension(model.boardWidth, model.boardHeight));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImg, 0, 0, model.boardWidth, model.boardHeight, null);
        g.drawImage(birdImg, model.bird.x, model.bird.y, model.bird.width, model.bird.height, null);

        for (Pipe pipe : model.pipes) {
            if (pipe.y < model.boardHeight / 2) {
                g.drawImage(topPipeImg, pipe.x, pipe.y, pipe.width, pipe.height, null);
            } else {
                g.drawImage(bottomPipeImg, pipe.x, pipe.y, pipe.width, pipe.height, null);
            }
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("", Font.PLAIN, 32));
        if (model.gameOver) {
            g.drawString("Game Over " + (int) model.score + "record :"+String.valueOf(HighScoreManager.readHighScore()), 10, 35);
        } else {
            g.drawString(String.valueOf((int) model.score), 10, 35);
        }
    }
}
