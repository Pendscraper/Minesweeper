package Minesweeper.backend;
import java.util.Random;
import java.util.PriorityQueue;

public class Board {
    public Tile[][] state;
    public final int width;
    public final int height;

    public Board (int width, int height, int mineCount) {
        this.width = width;
        this.height = height;
        state = new Tile[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                state[x][y] = new Tile();
            }
        }

        setMines(mineCount);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                state[x][y].adjacent = getNeighborCount(x, y);
            }
        }
    }

    private int getNeighborCount(int x, int y) {
        int count = 0;
        if (state[x][y].mine)
            count++;

        int negX = x == 0 ? 0 : -1;
        int posX = x == width - 1 ? 0 : 1;
        int negY = y == 0 ? 0 : -1;
        int posY = y == height - 1 ? 0 : 1;

        for (int a = negX; a <= posX; a++) {
            for (int b = negY; b <= posY; b++) {
                if (state[x + a][y + b].mine)
                    count++;
            }
        }
        return count;
    }

    private void setMines(int mineCount) {
        assert(mineCount <= width * height - 1);

        PriorityQueue<Integer> minePos = new PriorityQueue<>();
        Random numgen = new Random();
        for (int i = width * height; i > width * height - mineCount; --i) {
            int curPos = numgen.nextInt(i);
            for (Integer num : minePos) {
                if (curPos >= num)
                    curPos++;
                else
                    break;
            }
            state[curPos / width][curPos % width].mine = true;
            minePos.add(curPos);
        }
    }

    public Board (int width, int height) {
        this(width, height, (width * height) / 4);
    }
    public Board () {
        this(10, 10);
    }

    public boolean move(int x, int y) {
        assert(0<=x&&x<width);
        assert(0<=y&&y<height);

        state[x][y].discovered = true;
        return state[x][y].mine;
    }

    public Tile getTile(int x, int y) {
        assert(0<=x&&x<width);
        assert(0<=y&&y<height);

        return state[x][y];
    }

    public boolean solved() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (!state[x][y].mine)
                    return false;
            }
        }
        return true;
    }

    public void discoverAll() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                state[x][y].discover();
            }
        }
    }
}
