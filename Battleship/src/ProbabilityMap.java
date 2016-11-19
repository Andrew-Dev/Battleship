/**
 * Created by NYoungBall on 11/19/2016.
 */
public class ProbabilityMap {

    private int[][] probability = new int[8][8];


    public void createMap(Battleship b) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (b.grid[i][j] == 1) {
                    if (i >= 1 && b.grid[i - 1][j] == -1) probability[i - 1][j] += 5;
                    if (i <= 6 && b.grid[i + 1][j] == -1) probability[i + 1][j] += 5;
                    if (j >= 1 && b.grid[i][j - 1] == -1) probability[i][j - 1] += 5;
                    if (j <= 6 && b.grid[i][j + 1] == -1) probability[i][j + 1] += 5;
                    if (i >= 2 && b.grid[i - 2][j] == -1) probability[i - 2][j] += 2;
                    if (i <= 5 && b.grid[i + 2][j] == -1) probability[i + 2][j] += 2;
                    if (j >= 2 && b.grid[i][j - 2] == -1) probability[i][j - 2] += 2;
                    if (j <= 5 && b.grid[i][j + 2] == -1) probability[i][j + 2] += 2;
                    if (i >= 3 && b.grid[i - 3][j] == -1) probability[i - 3][j] += 1;
                    if (i <= 4 && b.grid[i + 3][j] == -1) probability[i + 3][j] += 1;
                    if (j >= 3 && b.grid[i][j - 3] == -1) probability[i][j - 3] += 1;
                    if (j <= 4 && b.grid[i][j + 3] == -1) probability[i][j + 3] += 1;
                    probability[i][j] = 0;
                }
            }
        }
    }

    public int[] findHighest(Battleship b) {
        createMap(b);
        int Highest, iValue, jValue;
        Highest = 0;
        iValue = 0;
        jValue = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (getProbability()[i][j] > Highest) {
                    Highest = getProbability()[i][j];
                    iValue = i;
                    jValue = j;
                }
            }
        }
        return new int[] {iValue, jValue};


    }

    public int[][] getProbability() {
        return probability;
    }


}
