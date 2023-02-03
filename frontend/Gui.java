package Minesweeper.frontend;
import Minesweeper.backend.Board;
import Minesweeper.backend.Tile;

import java.util.Scanner;


public class Gui {
    public boolean cycle(Board board) {
        boardOut(board);
        boolean go = false;
        Tile tile;
        do {
            int x = getNum("Please enter the x coordinate: ", board.width);
            int y = getNum("Please enter the y coordinate: ", board.height);

            tile = board.getTile(x, y);
            if (tile.discovered())
                System.out.println("That one's already been discovered.");
            else
                go = true;
        } while (!go);

        tile.discover();
        if (tile.mine) {
            System.out.println("BOOOOOOOOM");
            board.discoverAll();
            boardOut(board);
            return true;
        } else
            return false;
    }

    private int getNum(String input, int upperBound) {
        Scanner myS = new Scanner(System.in);
        boolean passes = false;
        int x = -1;
        do {
            System.out.print(input);
            String xString = myS.nextLine();
            try {
                x = Integer.parseInt(xString);
            } catch (NumberFormatException e) {
                System.out.println("not a number, dumbass");
                continue;
            }
            if (0 <= x && x < upperBound)
                passes = true;
            else
                System.out.println("number out of bounds");
        } while (!passes);

        return x;
    }

    private void boardOut(Board board) {
        for (Tile[] xArr : board.state) {
            System.out.print(" ");
            for (Tile tile : xArr) {
                if (tile.discovered()) {
                    if (tile.mine)
                        System.out.print('X');
                    else
                        System.out.print(tile.adjacent());
                } else
                    System.out.print('-');

                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
