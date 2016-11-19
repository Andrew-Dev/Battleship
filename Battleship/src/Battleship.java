/**
 * @ AUTHOR NAME HERE
 * @ Starter Code By Guocheng
 *
 * 2016-01-30
 * For: Purdue Hackers - Battleship
 * Battleship Client
 */

import java.io.*;
import java.net.Socket;
import java.net.InetAddress;
import java.util.Random;

public class Battleship {
	public static String API_KEY = "206231291"; ///////// PUT YOUR API KEY HERE /////////
	public static String GAME_SERVER = "battleshipgs.purduehackers.com";

	//////////////////////////////////////  PUT YOUR CODE HERE //////////////////////////////////////

	char[] letters;
	int[][] grid;
	int[] foundPos = null;
	int counter = 0;
    boolean foundLocationOrigin = false;

	void placeShips(String opponentID) {
		// Fill Grid With -1s
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) grid[i][j] = -1;
		}

		// Place Ships

		Random r = new Random();
        int randomResult = r.nextInt(10);
        if (randomResult == 0) {
            placeDestroyer("A7", "B7");
            placeSubmarine("D1", "D3");
            placeCruiser("B1", "B3");
            placeBattleship("B5", "E5");
            placeCarrier("G1", "G5");
        } else if (randomResult == 1) {
            placeDestroyer("F3", "F4");
            placeSubmarine("H3", "H5");
            placeCruiser("C5", "E5");
            placeBattleship("A4", "A7");
            placeCarrier("B1", "F1");
        } else if (randomResult == 2) {
            placeDestroyer("G3", "H3");
            placeSubmarine("B2", "B4");
            placeCruiser("F6", "H6");
            placeBattleship("D4", "D7");
            placeCarrier("C1", "G1");
        } else if (randomResult == 3) {
            placeDestroyer("D3", "E3");
            placeSubmarine("C1", "E1");
            placeCruiser("H2", "H4");
            placeBattleship("D5", "G5");
            placeCarrier("B2", "B6");
        } else if (randomResult == 4) {
            placeDestroyer("G1", "G2");
            placeSubmarine("A6", "C6");
            placeCruiser("G4", "G6");
            placeBattleship("A1", "D1");
            placeCarrier("E2", "E6");
        } else if (randomResult == 5) {
            placeDestroyer("H3", "H4");
            placeSubmarine("A1", "A3");
            placeCruiser("E2", "G2");
            placeBattleship("D6", "G6");
            placeCarrier("B4", "F4");
        } else if (randomResult == 6) {
            placeDestroyer("F1", "G1");
            placeSubmarine("A1", "A3");
            placeCruiser("G4", "G6");
            placeBattleship("B2", "E2");
            placeCarrier("A5", "E5");
        } else if (randomResult == 7) {
            placeDestroyer("F2", "G2");
            placeSubmarine("G4", "G6");
            placeCruiser("C6", "E6");
            placeBattleship("D1", "D4");
            placeCarrier("B1", "B5");
        } else if (randomResult == 8) {
            placeDestroyer("A6", "B6");
            placeSubmarine("A1", "A3");
            placeCruiser("C5", "E5");
            placeBattleship("C2", "F2");
            placeCarrier("G3", "G7");
        } else if (randomResult == 9) {
            placeDestroyer("G4", "H4");
            placeSubmarine("C2", "C4");
            placeCruiser("A6", "C6");
            placeBattleship("A1", "A4");
            placeCarrier("E2", "E6");
        } else {
            placeDestroyer("A3", "B3");
            placeSubmarine("B4", "B6");
            placeCruiser("E3", "G3");
            placeBattleship("E4", "E7");
            placeCarrier("C2", "G2");
        }

    }

	void makeMove() {

        if (foundPos != null) {

            FindRestOfShip findRestOfShip = new FindRestOfShip(this);
            findRestOfShip.nextPosition();
            grid = findRestOfShip.getB().grid;

            if (findRestOfShip.getResult().equals("Sunk")) {
                foundPos = null;
            } else {
                foundPos = findRestOfShip.getNewPosition();
            }
        } else {
            DecideAttack decideAttack = new DecideAttack(this);
            decideAttack.findShip();
            grid = decideAttack.getB().grid;
            if (grid[decideAttack.getPos()[0]][decideAttack.getPos()[1]] == 1) {
                foundPos = decideAttack.getPos();
            } else {
                foundPos = null;
            }
        }


		/**
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if (this.grid[i][j] == -1) {
					String wasHitSunkOrMiss = placeMove(this.letters[i] + String.valueOf(j));

					if (wasHitSunkOrMiss.equals("Hit") || wasHitSunkOrMiss.equals("Sunk")) {
						this.grid[i][j] = 1;
						foundPos = new int[2];
						foundPos[0] = i;
						foundPos[1] = j;
					} else {
						this.grid[i][j] = 0;			
					}
					return;
				}
			}
		}
         **/
	}



	////////////////////////////////////// ^^^^^ PUT YOUR CODE ABOVE HERE ^^^^^ //////////////////////////////////////

	Socket socket;
	String[] destroyer, submarine, cruiser, battleship, carrier;

	String dataPassthrough;
	String data;
	BufferedReader br;
	PrintWriter out;
	Boolean moveMade = false;

	public Battleship() {
		this.grid = new int[8][8];
		for(int i = 0; i < grid.length; i++) { for(int j = 0; j < grid[i].length; j++) grid[i][j] = -1; }
		this.letters = new char[] {'A','B','C','D','E','F','G','H'};

		destroyer = new String[] {"A0", "A0"};
		submarine = new String[] {"A0", "A0"};
		cruiser = new String[] {"A0", "A0"};
		battleship = new String[] {"A0", "A0"};
		carrier = new String[] {"A0", "A0"};
	}

	void connectToServer() {
		try {
			InetAddress addr = InetAddress.getByName(GAME_SERVER);
			socket = new Socket(addr, 23345);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);

			out.print(API_KEY);
			out.flush();
			data = br.readLine();
		} catch (Exception e) {
			System.out.println("Error: when connecting to the server...");
			socket = null; 
		}

		if (data == null || data.contains("False")) {
			socket = null;
			System.out.println("Invalid API_KEY");
			System.exit(1); // Close Client
		}
	}



	public void gameMain() {
		while(true) {
			try {
				if (this.dataPassthrough == null) {
					this.data = this.br.readLine();
				}
				else {
					this.data = this.dataPassthrough;
					this.dataPassthrough = null;
				}
			} catch (IOException ioe) {
				System.out.println("IOException: in gameMain"); 
				ioe.printStackTrace();
			}
			if (this.data == null) {
				try { this.socket.close(); } 
				catch (IOException e) { System.out.println("Socket Close Error"); }
				return;
			}

			if (data.contains("Welcome")) {
				String[] welcomeMsg = this.data.split(":");
				placeShips(welcomeMsg[1]);
				if (data.contains("Destroyer")) { // Only Place Can Receive Double Message, Pass Through
					this.dataPassthrough = "Destroyer(2):";
				}
			} else if (data.contains("Destroyer")) {
				this.out.print(destroyer[0]);
				this.out.print(destroyer[1]);
				out.flush();
			} else if (data.contains("Submarine")) {
				this.out.print(submarine[0]);
				this.out.print(submarine[1]);
				out.flush();
			} else if (data.contains("Cruiser")) {
				this.out.print(cruiser[0]);
				this.out.print(cruiser[1]);
				out.flush();
			} else if (data.contains("Battleship")) {
				this.out.print(battleship[0]);
				this.out.print(battleship[1]);
				out.flush();
			} else if (data.contains("Carrier")) {
				this.out.print(carrier[0]);
				this.out.print(carrier[1]);
				out.flush();
			} else if (data.contains( "Enter")) {
				this.moveMade = false;
				this.makeMove();
			} else if (data.contains("Error" )) {
				System.out.println("Error: " + data);
				System.exit(1); // Exit sys when there is an error
			} else if (data.contains("Die" )) {
				System.out.println("Error: Your client was disconnected using the Game Viewer.");
				System.exit(1); // Close Client
			} else {
				System.out.println("Received Unknown Response:" + data);
				System.exit(1); // Exit sys when there is an unknown response
			}
		}
	}

	void placeDestroyer(String startPos, String endPos) {
		destroyer = new String[] {startPos.toUpperCase(), endPos.toUpperCase()}; 
	}

	void placeSubmarine(String startPos, String endPos) {
		submarine = new String[] {startPos.toUpperCase(), endPos.toUpperCase()}; 
	}

	void placeCruiser(String startPos, String endPos) {
		cruiser = new String[] {startPos.toUpperCase(), endPos.toUpperCase()}; 
	}

	void placeBattleship(String startPos, String endPos) {
		battleship = new String[] {startPos.toUpperCase(), endPos.toUpperCase()}; 
	}

	void placeCarrier(String startPos, String endPos) {
		carrier = new String[] {startPos.toUpperCase(), endPos.toUpperCase()}; 
	}

	String placeMove(String pos) {
		if(this.moveMade) { // Check if already made move this turn
			System.out.println("Error: Please Make Only 1 Move Per Turn.");
			System.exit(1); // Close Client
		}
		this.moveMade = true;

		this.out.print(pos);
		out.flush();
		try { data = this.br.readLine(); } 
		catch(Exception e) { System.out.println("No response after from the server after place the move"); }

		if (data.contains("Hit")) return "Hit";
		else if (data.contains("Sunk")) return "Sunk";
		else if (data.contains("Miss")) return "Miss";
		else {
			this.dataPassthrough = data;
			return "Miss";
		}
	}

	public static void main(String[] args) {
		Battleship bs = new Battleship();
		while(true) {
			bs.connectToServer();
			if (bs.socket != null) bs.gameMain();
		}	
	}
}

