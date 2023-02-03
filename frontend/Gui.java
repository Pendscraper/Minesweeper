package Minesweeper.frontend;
import Minesweeper.backend.Board;
import Minesweeper.backend.Tile;

import java.util.Objects;
import java.util.Scanner;


public class Gui {
    private boolean flagDialogOpen = false;
    private Board board;

    public boolean cycle(Board board) {
        boardOut(board);
        this.board = board;
        boolean go = false;
        Tile tile;
        do {
            int x = getNum("Please enter the x coordinate: ", board.width);
            int y = getNum("Please enter the y coordinate: ", board.height);

            tile = board.getTile(x, y);
            if (tile.discovered())
                System.out.println("That one's already been discovered.");
            else if (tile.flagged)
                System.out.println("That one's flagged.");
            else
                go = true;
        } while (!go);

        tile.discover();
        if (tile.mine) {
            System.out.println("BOOOOOOOOM");
            board.discoverAll();
            boardOut(board);
            return true;
        }

        if (tile.adjacent() == 0) {
            board.uncoverAdjacentZeroes();
        }


        return false;
    }

    private int getNum(String input, int upperBound) {
        Scanner myS = new Scanner(System.in);
        boolean passes = false;
        int x = -1;
        do {
            String xString;
            do {
                System.out.print(input);
                xString = myS.nextLine();
            } while (!flagDialog(xString));

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

    private boolean flagDialog(String input) {
        if (!Objects.equals(input, "Flag"))
            return true;
        if (flagDialogOpen)
            return true;

        flagDialogOpen = true;
        int x = getNum("Please enter the x flag: ", board.width);
        int y = getNum("Please enter the y flag: ", board.height);
        flagDialogOpen = false;

        board.state[x][y].flagged = !board.state[x][y].flagged;
        boardOut();
        return false;
    }

    private void boardOut() {
        boardOut(board);
    }

    private void boardOut(Board board) {
        System.out.print("  ");
        for (int x = 0; x < board.width && x < 10; x++) {
            System.out.print(x);
            System.out.print(" ");
        }
        System.out.println();

        for (int y = 0; y < board.height; y++) {
            System.out.print(y);
            System.out.print(" ");
            for (int x = 0; x < board.width; x++) {
                Tile tile = board.getTile(x, y);
                if (tile.discovered()) {
                    if (tile.mine)
                        System.out.print('!');
                    else
                        System.out.print(tile.adjacent());
                } else if (tile.flagged)
                    System.out.print('X');
                else
                    System.out.print('-');

                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
