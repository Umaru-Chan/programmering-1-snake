/*

inte (helt) gjord av mig

*/

import java.awt.Color;
public class Luffarschack1 {
	public static void main(String[] args) {

		SimpleWindow w = new SimpleWindow(700, 700, "LuffarSchack");
		w.moveTo(100, 100); w.writeText("Välkommen till Luffarschack. ");
		w.moveTo(100,120); 	w.writeText(" det går ut på att få 3 i rad, du ritar själv ut kryssen, var fjärde musklick");
		w.moveTo (100,140);	w.writeText ("så byter den färg så var varsam om dina musklick");
		w.moveTo(100,160); 	w.writeText ("ni får välja mellan färgerna svart och röd ");
		w.moveTo(100,180); 	w.writeText ("starta spelet genom att clicka på Start");

		Square sq200 = new Square(250, 300, 60);
		w.moveTo (260,335);	w.writeText ("Start");
		sq200.draw(w);

		w.waitForMouseClick();
		w.clear() ;
		int mX = w.getMouseX();
		int mY = w.getMouseY();

		if (mX > 250 && mX < 310 && mY > 300 && mY < 360)
			rita(w);
		else
			System.exit(0);
	}//main

	private static void rita (SimpleWindow w) {
		
		Square[] sq = new Square[49];
		for(int y = 0; y < 7; y++)
			for(int x =0 ; x < 7; x++)
				sq[x + y * 7] = new Square(x * 100, y * 100, 100);
		for(int i = 0; i < sq.length; i++)sq[i].draw(w);
		
		w.setLineWidth(10); // Här skriver du hur brett Lijen du ritar ditt kryss med ska vara

		//Ritar upp kryss, omväxlande svarta och röda
		while (true) {
			for(int i = 0; i<2; i++) {
				w.setLineColor(Color.BLACK);
				w.waitForEvent();
				w.moveTo(w.getMouseX(), w.getMouseY() );
				w.waitForEvent();
				w.lineTo(w.getMouseX(), w.getMouseY() );
			}
			for(int i = 0; i<2; i++) {
				w.setLineColor(Color.RED);
				w.waitForEvent();
				w.moveTo(w.getMouseX(), w.getMouseY() );
				w.waitForEvent();
				w.lineTo(w.getMouseX(), w.getMouseY() );
			}
		}// while sats	
	}//rita-metoden
}//class
