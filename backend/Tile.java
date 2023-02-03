package Minesweeper.backend;

public class Tile {
    public boolean mine;
    int adjacent;
    boolean discovered;

    public Tile() {
        this.mine = false;
        discovered = false;
    }

    public boolean discovered() {
        return discovered;
    }

    public void discover() {
        discovered = true;
    }

    public int adjacent() {
        return adjacent;
    }
}
