/**
 * Created by andrew on 11/18/16.
 */
public class DecideAttack {

    public int[] findShip(Battleship b) {
        for(int i = 0; i < 8; i+=2) {
            for(int j = 0; j < 8; j+=2) {
                char letter = ConvertNumToLetter.numToLetter(j);
                String posStr = "" + letter + i;
                String result = b.placeMove(posStr);
                if(result.equals("Hit") || result.equals("Sunk")) {
                    int[] pos = new int[2];
                    pos[0] = i;
                    pos[1] = j;
                    b.grid[i][j] = 1;
                } else if(result.equals("Miss")) {
                    b.grid[i][j] = 0;
                }
            }
        }
        return null;
    }

}
