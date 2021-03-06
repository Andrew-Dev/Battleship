/**
 * Created by andrew on 11/18/16.
 */
public class DecideAttack {

    private int[] pos = new int[2];
    private Battleship b;

    public DecideAttack(Battleship b) {
        this.b = b;
    }

    public void findShip() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if (b.grid[i][j] == -1 && (Math.abs(i - j)) % 2 == 0 ) {
                    if (b.firstTime = true) {
                        execute(i, j);
                        return;
                    }

                }
            }
        }
        InARowCounter inARowCounter = new InARowCounter(b);
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if (b.grid[i][j] == -1 && (Math.abs(i - j)) % 2 == 0 ) {
                     if (inARowCounter.horizontalCounter(-1, i, j, 1) >= 2) {
                         execute(i, j);
                         return;
                     }
                }

            }
        }

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if (b.grid[i][j] == -1 && (Math.abs(i - j)) % 2 == 0 ) {
                    if (inARowCounter.verticalCounter(-1, i, j, 1) >= 2) {
                        execute(i, j);
                        return;
                    }
                }

            }
        }

        //safety measure
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if (b.grid[i][j] == -1 && (Math.abs(i - j)) % 2 == 0 ) {
                    execute(i, j);
                    return;
                }
            }
        }


    }

    public void execute(int i, int j) {
        char letter = ConvertNumToLetter.numToLetter(j);
        String posStr = "" + letter + i;
        String result = b.placeMove(posStr);
        if (result.equals("Hit") || result.equals("Sunk")) {
            pos[0] = i;
            pos[1] = j;
            b.grid[i][j] = 1;
        } else if (result.equals("Miss")) {
            b.grid[i][j] = 0;
        }
    }

    public int[] getPos() {
        return pos;
    }

    public Battleship getB() {
        return b;
    }

}
