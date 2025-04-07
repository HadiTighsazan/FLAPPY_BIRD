package src.model;

import java.util.ArrayList;

public class GameModel {
    public int boardWidth;
    public int boardHeight;

    public boolean gameOver = false;
    public double score = 0;

    public int gravity = 1;
    public int velocityX = -4;
    public int velocityY = -1;

    public Bird bird;
    public ArrayList<Pipe> pipes;

    public GameModel(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        bird = new Bird(boardWidth / 8, boardHeight / 2, 34, 24);
        pipes = new ArrayList<>();
    }

    public void reset() {
        HighScoreManager.updateHighScore((int) score);
        gameOver = false;
        score = 0;
        velocityY = -1;
        bird.x = boardWidth / 8;
        bird.y = boardHeight / 2;
        pipes.clear();
    }

    public void update() {
        velocityY += gravity;
        bird.y += velocityY;
        if (bird.y < 0) {
            bird.y = 0;
        }

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.x += velocityX;

            if (pipe.x + pipe.width < 0) {
                pipes.remove(i);
                i--;
            } else {
                if (!pipe.passed && bird.x > pipe.x + pipe.width) {
                    pipe.passed = true;
                    score += 0.5;
                }
                if (collision(bird, pipe)) {
                    gameOver = true;
                }
            }
        }

        if (bird.y > boardHeight) {
            gameOver = true;
        }
    }

    public void addPipes() {
        int pipeWidth = 64;
        int pipeHeight = 512;
        int pipeX = boardWidth;
        int pipeY = 0;
        int openingSpace = boardHeight / 4;

        int randomPipeY = (int) (pipeY - (pipeWidth / 4) - Math.random() * (pipeHeight / 2));
        Pipe topPipe = new Pipe(pipeX, randomPipeY, pipeWidth, pipeHeight);
        Pipe bottomPipe = new Pipe(pipeX, topPipe.y + pipeHeight + openingSpace, pipeWidth, pipeHeight);
        pipes.add(topPipe);
        pipes.add(bottomPipe);
    }

    private boolean collision(Bird a, Pipe b) {
        return a.x < b.x + b.width && a.x + a.width > b.x &&
                a.y < b.y + b.height && a.y + a.height > b.y;
    }
}
