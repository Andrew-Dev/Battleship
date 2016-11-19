/**
 * Created by NYoungBall on 11/18/2016.
 */
public class FindRestOfShip {

    private int[] position;  //position 0 is x, and position 1 is y
    private int[] newPosition;
    private int count;
    private String attackSpot;
    private String result;

    public FindRestOfShip(int[] position) {
        this.position = newPosition = position;
        count = 0;
        result = "";
    }

    //0 if miss, 1 if hit
    public void nextPosition(Battleship b) {
        try {

            if (b.grid[position[0] + 1][position[1]] == -1) {
                newPosition[0] = position[0] + 1;
                newPosition[1] = position[1];
                move(b);
            } else if (b.grid[position[0] - 1][position[1]] == -1) {
                newPosition[0] = position[0] - 1;
                newPosition[1] = position[1];
                move(b);
            } else if (b.grid[position[0]][position[1] + 1] == -1) {
                newPosition[0] = position[0];
                newPosition[1] = position[1] + 1;
                move(b);
            } else if (b.grid[position[0]][position[1] - 1] == -1) {
                newPosition[0] = position[0];
                newPosition[1] = position[1] - 1;
                move(b);
            } else if (b.grid[position[0] + 1][position[1]] == 1) {
                newPosition[0] = position[0] - count - 1;
                newPosition[1] = position[1];
                if (b.grid[position[0] - 1][position[1]] == -1) {
                    newPosition[0] = position[0] - 1;
                    newPosition[1] = position[1];
                    move(b);
                }
            } else if (b.grid[position[0] - 1][position[1]] == 1) {
                newPosition[0] = position[0] + count + 1;
                newPosition[1] = position[1];
                if (b.grid[position[0] + 1][position[1]] == -1) {
                    newPosition[0] = position[0] - 1;
                    newPosition[1] = position[1];
                    move(b);
                }
            } else if (b.grid[position[0]][position[1] + 1] == 1) {
                newPosition[0] = position[0];
                newPosition[1] = position[1] - count - 1;
                if (b.grid[position[0]][position[1] - 1] == -1) {
                    newPosition[0] = position[0] - 1;
                    newPosition[1] = position[1];
                    move(b);
                }
            } else if (b.grid[position[0]][position[1] - 1] == 1) {
                newPosition[0] = position[0];
                newPosition[1] = position[1] + count + 1;
                if (b.grid[position[0]][position[1] + 1] == -1) {
                    newPosition[0] = position[0] - 1;
                    newPosition[1] = position[1];
                    move(b);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            newPosition = position;
        }

    }

    public void move(Battleship b) {
        setPositionToNewPos();
        attackSpot = "" + ConvertNumToLetter.numToLetter(newPosition[0]) + newPosition[1];
        System.out.printf("Attackspot: %s", attackSpot + "\n");
        result = b.placeMove(attackSpot);
        if (result.equals("Hit") || result.equals("Sunk")) {
            b.grid[position[0]][position[1]] = 1;
            count++;
            System.out.println("Count:" + count);
        } else {
            b.grid[newPosition[0]][newPosition[1]] = 0;
        }
    }

    public int[] getNewPosition() {
        return newPosition;
    }

    public String getResult() {
        return result;
    }

    public void setPositionToNewPos() {
        position = newPosition;
    }

}
