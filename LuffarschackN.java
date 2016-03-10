//Måns, Wictor och Tim!

import javax.swing.JOptionPane;
import java.awt.Color;

public class LuffarschackN {

	public static int[] type;
	private static int size = 3, sqSize = 700 / size;
	private static ComputerPlayer cp = new ComputerPlayer(size);

	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(700, 700, "LuffarSchack");

		welcome(w);

		Square startButton = new Square(250, 300, 60);
		w.moveTo(260, 335);	w.writeText("Start");
		startButton.draw(w);

		w.waitForMouseClick();
		w.clear() ;
		int mX = w.getMouseX(), mY = w.getMouseY();


		if (mX > 250 && mX < 310 && mY > 300 && mY < 360)
			rita(w);
		else
			System.exit(0);
	}

	private static void welcome(SimpleWindow window){

		window.moveTo(100, 100); 	window.writeText("Välkommen till Luffarschack. ");
		window.moveTo(100, 120); 	window.writeText("det går ut på att få 3 i rad, du ritar själv ut kryssen, var fjärde musklick");
		window.moveTo(100, 140);	window.writeText("så byter den färg så var varsam om dina musklick");
		window.moveTo(100, 160); 	window.writeText("ni får välja mellan färgerna svart och röd ");
		window.moveTo(100, 180); 	window.writeText("starta spelet genom att clicka på Start");

	}

	private static void rita (SimpleWindow w) {
        Square[] sq = new Square[size*size];
        type = new int[sq.length];

        for (int i = 0; i < sq.length; i++) {
            sq[i] = new Square(i % size * (w.getWidth()/size), i / size * (w.getHeight()/size), w.getWidth()/size);
            sq[i].draw(w);
        }

        w.setLineWidth(10);

        while (!vinnst()) {
			w.setLineColor(Color.BLACK);

            do {
                w.waitForMouseClick();
            } while (type[getIndex(w.getMouseX(), w.getMouseY())] != 0);

			kryss(w, sq);

			if(!vinnst(sq))cp.makeMove(w, type, sq);

        }
        JOptionPane.showMessageDialog(null, w.getLineColor().equals(Color.RED) ? "röd vann" : "svart vann",
				"vinnst!", JOptionPane.INFORMATION_MESSAGE);

        System.exit(0);
    }

	private static void kryss(SimpleWindow w, Square[] sq){

		//	så att man slipper skriva "sq[getIndex(w.getMouseX(), w.getMouseY())]. ..."
		// 	mycket att bara skriva "tempSquare. ..."
		Square tempSquare = sq[getIndex(w.getMouseX(), w.getMouseY())];

		//	övere vänstra hörnet till nedre högra
		w.moveTo(tempSquare.getX() + sqSize/10, tempSquare.getY() + sqSize/10);
		w.lineTo(tempSquare.getX() + tempSquare.getSide() - sqSize/10, tempSquare.getY() + tempSquare.getSide() - sqSize/10);

		//	nedre vänstra hörnet till övre högre
		w.moveTo(tempSquare.getX() + tempSquare.getSide() - sqSize/10, tempSquare.getY() + sqSize/10);
		w.lineTo(tempSquare.getX() + sqSize/10, tempSquare.getY() + tempSquare.getSide() - sqSize/10);

		type[getIndex(w.getMouseX(), w.getMouseY())] = -1;
	}

	private static boolean vinnst(Square[]sq){

		//lodrät vinnst
		for(int i = 0; i < type.length - size*2; i++)
			if(Math.abs(type[i] + type[i + size] + type[i + size*2]) == 3
					)return true;

		//vågrät vinnst
		for(int i = 0; i < type.length - 2; i++)
			if(Math.abs(type[i] + type[i+1] + type[i+2]) == 3 && i % size < (i + 2) % size)return true;

		//uppevänster till nere höger
		for(int i = 0; i < type.length - (size*2+2); i++)
			if(Math.abs(type[i] + type[i + size+1] + type[i + size*2+2]) == 3 && i % size < (i + 2) % size)return true;

		//uppehöger till nere vänster
		for(int i = 0; i < type.length - (size*2-2); i++)
			if(Math.abs(type[i] + type[i + size-1] + type[i + size*2-2]) == 3 && i % size > (i + 5) % size)return true;

		return false;
	}

	private static int getIndex(int x, int y){
		//	mattematik ??
		return x/sqSize + y/sqSize * size;
	}
}
