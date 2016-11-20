/**
 * Created by NYoungBall on 11/19/2016.
 */
public class InARowCounter {
    private Battleship b;

    public InARowCounter(Battleship b) {
        this.b = b;
    }

    public int horizontalCounter(int value, int x, int y, int secondValue)  {
        int counter = 1;
        try {
            for (int i = 1; i <= x; i++) {
                if (b.grid[x - i][y] == value || b.grid[x - i][y] == secondValue) {
                    counter++;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }

        try {
            for (int i = 1; i <= 8 - x; i++) {
                if (b.grid[x + i][y] == value || b.grid[x + 1][y] == secondValue) {
                    counter++;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }

        return counter;
    }

    public int verticalCounter(int value, int x, int y, int secondValue)  {
        int counter = 1;
        try {
            for (int i = 1; i <= y; i++) {
                if (b.grid[x][y - i] == value || b.grid[x][y - 1] == secondValue) {
                    counter++;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }

        try {
            for (int i = 1; i <= 8 - y; i++) {
                if (b.grid[x][y + i] == value || b.grid[x][y - 1] == secondValue) {
                    counter++;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        return counter;
    }

    //overloaded method without secondary value
    public int horizontalCounter(int value, int x, int y)  {
        int counter = 1;
        try {
            for (int i = 1; i <= x; i++) {
                if (b.grid[x - i][y] == value) {
                    counter++;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }

        try {
            for (int i = 1; i <= 8 - x; i++) {
                if (b.grid[x + i][y] == value) {
                    counter++;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }

        return counter;
    }

    //overloaded method without secondary value
    public int verticalCounter(int value, int x, int y)  {
        int counter = 1;
        try {
            for (int i = 1; i <= y; i++) {
                if (b.grid[x][y - i] == value) {
                    counter++;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }

        try {
            for (int i = 1; i <= 8 - y; i++) {
                if (b.grid[x][y + i] == value) {
                    counter++;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        return counter;
    }


}
