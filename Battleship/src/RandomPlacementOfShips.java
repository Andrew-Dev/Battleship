/**
 * Created by amolmoses on 11/18/16.
 */
import java.util.Random;
import java.util.Scanner;

public class RandomPlacementOfShips {

    public String getPlacement(int num){
        Random r = new Random();
        String row = "" + ConvertNumToLetter.numToLetter(r.nextInt(8));
        System.out.printf("Row=%s\n" , row);
        int starting = r.nextInt(8);
        int ending = starting+num;
        if(ending>7){
            ending = 7;
        }
        System.out.printf("Starting=%d \n Ending=%d" , starting , ending);

        String placement = row+starting + " " + row+ending;

        return placement;
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        char ch='A';
        while(ch!='N') {
            System.out.println(new RandomPlacementOfShips().getPlacement(4));
            ch = scanner.nextLine().charAt(0);
        }
    }
}
