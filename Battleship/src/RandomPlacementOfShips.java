/**
 * Created by amolmoses on 11/18/16.
 */
import java.util.Random;
import java.util.Scanner;

public class RandomPlacementOfShips {

    public String getPlacementColumn(int num){
        Random r = new Random();
        String A ="";
        String row = "" + ConvertNumToLetter.numToLetter(r.nextInt(8));
        int starting = r.nextInt(8);
        int ending = starting+num;
        if(ending>7){
            ending = 7;
        }
        String placement = row+starting + " " + row+ending;

        return placement;
    }

    public String getPlacementRow(int num){
        Random r = new Random();
        String A = "" + ConvertNumToLetter.numToLetter(r.nextInt(4));
        int startingInt = r.nextInt(8);
        String starting = "" + A +startingInt;
        int endingInt = startingInt+num;
        if(endingInt>7){
            endingInt=7;
        }
        String ending= "" + A + endingInt;

        return starting + " " + ending;
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        char ch='A';
        while(ch!='N'&&ch!='n') {
            System.out.println(new RandomPlacementOfShips().getPlacementRow(4));
            ch = scanner.nextLine().charAt(0);
        }
    }
}
