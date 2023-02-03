package Minesweeper.backend;

public class Tile {
    public boolean mine;
    int adjacent;
    boolean discovered;
    public boolean flagged;

    public Tile() {
        mine = false;
        discovered = false;
        flagged = false;
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
