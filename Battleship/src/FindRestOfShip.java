/**
 * Created by NYoungBall on 11/18/2016.
 */
public class FindRestOfShip {

    private int[] position;  //position 0 is x, and position 1 is y
    private int[] newPosition;
    private String attackSpot;
    private String result;
    private Battleship b;

    public FindRestOfShip(Battleship b) {
        this.b = b;
        result = "";
    }

    //0 if miss, 1 if hit, -1 if unchecked
    public void nextPosition() {
        ProbabilityMap probabilityMap = new ProbabilityMap();
        newPosition = probabilityMap.findHighest(b);
        move();

    }

    public void move() {
        setPositionToNewPos();
        attackSpot = "" + ConvertNumToLetter.numToLetter(newPosition[0]) + newPosition[1];
        System.out.printf("Attackspot: %s", attackSpot + "\n");
        result = b.placeMove(attackSpot);
        if (result.equals("Hit") || result.equals("Sunk")) {
            b.grid[position[0]][position[1]] = 1;
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

    public Battleship getB() {
        return b;
    }

}
