/**
 * Created by NYoungBall on 11/19/2016.
 */
public class ProbabilityMap {

    private int[][] probability = new int[8][8];
    private Battleship b;

    public ProbabilityMap(Battleship b) {
        this.b = b;

    }


    public void createMap(int[] position) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (b.grid[i][j] == 1) {
                    if (i >= 1) probability[i - 1][j] += 10;
                    if (i <= 6) probability[i + 1][j] += 10;
                    if (j >= 1) probability[i][j - 1] += 10;
                    if (j <= 6) probability[i][j + 1] += 10;
                    if (i >= 2) probability[i - 2][j] += 7;
                    if (i <= 5) probability[i + 2][j] += 7;
                    if (j >= 2) probability[i][j - 2] += 7;
                    if (j <= 5) probability[i][j + 2] += 7;
                    if (i >= 3) probability[i - 3][j] += 4;
                    if (i <= 4) probability[i + 3][j] += 4;
                    if (j >= 3) probability[i][j - 3] += 4;
                    if (j <= 4) probability[i][j + 3] += 4;
                    if (i >= 4) probability[i - 4][j] += 1;
                    if (i <= 3) probability[i + 4][j] += 1;
                    if (j >= 4) probability[i][j - 4] += 1;
                    if (j <= 3) probability[i][j + 4] += 1;

                    try {
                        if (i >= 1 && b.grid[i - 1][j] == 1) probability[i + 1][j] += 15;
                        if (i <= 6 && b.grid[i + 1][j] == 1) probability[i - 1][j] += 15;
                        if (j >= 1 && b.grid[i][j + 1] == 1) probability[i][j - 1] += 15;
                        if (j <= 6 && b.grid[i][j - 1] == 1) probability[i][j + 1] += 15;
                        /**
                        if (i >= 2 && b.grid[i - 2][j] == 1) probability[i + 1][j] += 15;
                        if (i <= 5 && b.grid[i + 2][j] == 1) probability[i - 1][j] += 15;
                        if (j >= 2 && b.grid[i][j + 2] == 1) probability[i][j - 1] += 15;
                        if (j <= 5 && b.grid[i][j - 2] == 1) probability[i][j + 1] += 15;
                         **/
                    } catch (ArrayIndexOutOfBoundsException e) {

                    }
                    /**
                    if (i == position[0] && j == position[1]) {
                        if (i >= 1) probability[i - 1][j] += 5;
                        if (i <= 6) probability[i + 1][j] += 5;
                        if (j >= 1) probability[i][j - 1] += 5;
                        if (j <= 6) probability[i][j + 1] += 5;
                    }
                     **/
                }
            }
        }
    }

    public int[] findHighest(int[] position) {
        createMap(position);
        int Highest = 0;
        int iValue = 0;
        int jValue = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (getProbability()[i][j] > Highest && b.grid[i][j] == -1) {
                    Highest = getProbability()[i][j];
                    iValue = i;
                    jValue = j;
                }
            }
        }
        return new int[] {iValue, jValue};


    }

    public void setProbability(int[][] probability) {
        this.probability = probability;
    }

    public int[][] getProbability() {
        return probability;
    }


}
