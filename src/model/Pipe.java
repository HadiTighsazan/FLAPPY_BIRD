package src.model;

public class Pipe {
    public int x, y, width, height;
    public boolean passed;

    public Pipe(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.passed = false;
    }
}
