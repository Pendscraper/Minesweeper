package Minesweeper.backend;
import Minesweeper.frontend.Gui;

public class Main {

    public static void main(String[] args) {
	    Board board = new Board();
        Gui gui = new Gui();

        boolean exploded;
        do {
            exploded = gui.cycle(board);
        } while (!board.solved() && !exploded);
    }
}
