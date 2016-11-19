/**
 * Created by NYoungBall on 11/18/2016.
 */
public class FindRestOfShip {

    private int[] position;  //position 0 is x, and position 1 is y
    private int[] newPosition;
    private int count;

    public FindRestOfShip(int[] position) {
        this.position = newPosition = position;
        count = 0;
    }

    //0 if miss, 1 if hit
    public void nextPosition(Battleship b) {
        try {

            if (b.grid[position[0] + 1][position[0]] == 0) {
                
            } else if (b.grid[position[0] - 1][position[0]] == 0) {

            } else if (b.grid[position[0]][position[0] + 1] == 0) {

            } else if (b.grid[position[0]][position[0] - 1] == 0) {

            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        b.placeMove();
    }
}
