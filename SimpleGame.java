import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by alexander on 15-10-09.
 */
public class SimpleGame {
	public static void main(String[] args) {
		
		int width = 1100, height = 25 * 26;
		SimpleWindowMod w = new SimpleWindowMod(width, height, " snake ; Alexander ");
		//Random klassen är simplare än Math.random
		Random random = new Random();

		//listan för svansen
		List<SquareMod> tail = new ArrayList<>();
		//för att förhindra en nullpointer
		tail.add(new SquareMod(0,0,0));
		
		//kvadraterna som ska symbolisera spelaren och äpplet
		SquareMod player = new SquareMod(width / 2 - 25, height / 2, 25);
		SquareMod apple = new SquareMod(random.nextInt(w.getWidth() / 25) * 25 + 1,
				random.nextInt(w.getHeight() / 25) * 25 + 1, 23);

		//för att hålla koll på poäng och riktning. om dir = 4 så rör sig spelaren inte
		int dir = 4, score = 1, highscore = score; //jag är generös, spelaren börjar med 1 poäng :)
		//för att hålla koll på om spelaren lever eller inte
		boolean alive = false;

		while (true) {
			
			/*
			 * när man inte lever så ska huvudmenyn renderas.
			 * om spelaren klickar e så börjar nedräkningen till att spelet
			 * ska startas.
			 * */
			while (!alive) {
				w.moveTo(0, w.getHeight() / 2);
				w.setLineColor(new Color(0x008800));
				w.setLineWidth(w.getHeight());
				w.lineTo(w.getWidth(), w.getHeight() / 2);
				w.setLineColor(Color.YELLOW);
				w.moveTo(w.getWidth() / 2 - 200, w.getHeight() / 2 - 160);
				w.writeText("snake, programmerat av : Alexander Norozkhani");
				w.moveTo(w.getWidth() / 2 - 200, w.getHeight() / 2 - 145);
				w.writeText("ditt mål är att äta upp det röda äpplet.");
				w.moveTo(w.getWidth() / 2 - 200, w.getHeight() / 2 - 130);
				w.writeText("du styr med w a s d.");
				w.moveTo(w.getWidth() / 2 - 200, w.getHeight() / 2 - 115);
				w.writeText("w = upp , s = ner, d = höger, a = vänster");
				w.moveTo(w.getWidth() / 2 - 200, w.getHeight() / 2 - 100);
				w.writeText("klicka 'e' för att börja spela");
				
				if(score > 1){  //om du tidigare har spelat och vill se ditt tidigare score
					if(score > highscore)
						highscore = score;

					w.setLineColor(Color.CYAN);
					w.moveTo(w.getWidth() / 2 - 200, w.getHeight() / 2 - 185);
					w.writeText("highScore : " + highscore);
					w.setLineColor(Color.WHITE);
					w.moveTo(w.getWidth() / 2 - 200, w.getHeight() / 2 - 200);
					w.writeText("score : "+score);
					w.moveTo(w.getWidth() / 2 - 200, w.getHeight() / 2 - 215);
					w.writeText("game over, ");
				}

				w.waitForEvent();

				if (w.getKey() == 'e') { //när man klickar e så ska spelet starta om 4 sec
					for (int i = 4; i > 0; i--) {
						w.clear();
						w.moveTo(0, w.getHeight() / 2);
						w.setLineColor(new Color(0x008800));
						w.setLineWidth(w.getHeight());
						w.lineTo(w.getWidth(), w.getHeight() / 2);
						w.setLineColor(Color.YELLOW);
						w.moveTo(w.getWidth() / 2 - 200, w.getHeight() / 2 - 100);
						w.writeText("lycka till !");
						w.moveTo(w.getWidth() / 2 - 200, w.getHeight() / 2 - 115);
						w.writeText("spelet börjar om " + i + " sekund(er).");
						w.delay(1000);
					}
					//reseta spelet och starta spelet
					score = 1;
					for(int i = 0; i < tail.size();)tail.remove(i);
					tail.add(new SquareMod(0,0,0));
					apple = new SquareMod(random.nextInt(w.getWidth() / 25) * 25 + 1,
							random.nextInt(w.getHeight() / 25) * 25 + 1, 23);
					player = new SquareMod(width / 2 - 25, height / 2, 25);
					alive = true;
				}
			}
			//förbered för bakgrunden
			w.moveTo(0, w.getHeight() / 2);
			w.setLineColor(new Color(0x008800));
			w.setLineWidth(w.getHeight());
			
			//rensa skärmen
			w.clear();
			//rita upp bakgrunden
			w.lineTo(w.getWidth(), w.getHeight() / 2);

			//kolla efter input
			switch (w.getKey()) {
			case ('w'):
				dir = 0;
				//om spelaren åker över skärmen, skicka ner spelaren till botten.
				//ta även bort den sista svansen och lägg till en där spelaren tidigare var.
				if (player.getY() <= 0){
					tail.remove(score-1);
					tail.add(0, new SquareMod(player.getX() + 2, player.getY() + 2, player.getSide() - 4));					
					player.move(0, w.getHeight() - player.getSide());
				}else {
					tail.remove(score-1);
					tail.add(0, new SquareMod(player.getX() + 2, player.getY() + 2, player.getSide() - 4));
					player.move(0, -player.getSide());
				}
				break;

			case ('s'):
				dir = 2;
				//om spelaren åker under skärmen, skicka upp spelaren till toppen.
				//ta även bort den sista svansen och lägg till en där spelaren tidigare var.
				if (player.getY() + player.getSide() >= w.getHeight()){
					tail.remove(score-1);
					tail.add(0, new SquareMod(player.getX() + 2, player.getY() + 2, player.getSide() - 4));
					player.move(0, -w.getHeight() + player.getSide());
				}else {
					tail.remove(score-1);
					tail.add(0, new SquareMod(player.getX() + 2, player.getY() + 2, player.getSide() - 4));
					player.move(0, player.getSide());
				}
				break;

			case ('a'):
				dir = 3;
				//om spelaren åker för långt till vänster, skicka spelaren till högerkanten
				//ta även bort den sista svansen och lägg till en där spelaren tidigare var.
				if (player.getX() <= 0){
					tail.remove(score-1);
					tail.add(0, new SquareMod(player.getX() + 2, player.getY() + 2, player.getSide() - 4));					
					player.move(w.getWidth() - player.getSide(), 0);
				}else {
					tail.remove(score-1);
					tail.add(0, new SquareMod(player.getX() + 2, player.getY() + 2, player.getSide() - 4));
					player.move(-player.getSide(), 0);
				}
				break;

			case ('d'):
				dir = 1;
				//om spelaren åker för långt till höger, skicka spelaren till vänsterkanten
				//ta även bort den sista svansen och lägg till en där spelaren tidigare var.
				if (player.getX() + player.getSide() >= w.getWidth()){
					tail.remove(score-1);
					tail.add(0, new SquareMod(player.getX() + 2, player.getY() + 2, player.getSide() - 4));
					player.move(-w.getWidth() + player.getSide(), 0);
				}else {
					tail.remove(score-1);
					tail.add(0, new SquareMod(player.getX() + 2, player.getY() + 2, player.getSide() - 4));
					player.move(player.getSide(), 0);
				}
				break;

			default:
				dir = 4; // om man inte rör sig så är dir == 4
			}

			// rita spelaren
			w.setLineWidth(2);
			w.setLineColor(new Color(0x70360C));
			player.draw(w);
			
			//kolla om spelaren nuddar äpplet innan man ritar äpplet
			if (interSects(player, apple)) {
				score++;
				//lägg till en svansbit om man äter äpplet
				SquareMod lastTail = tail.get(tail.size() - 1);
				if(dir == 0){
					tail.add(new SquareMod(lastTail.getX(), lastTail.getY() + lastTail.getSide(), lastTail.getSide()));
				}
				if(dir == 1){
					tail.add(new SquareMod(lastTail.getX() - lastTail.getSide(), lastTail.getY(), lastTail.getSide()));
				}
				if(dir == 2){
					tail.add(new SquareMod(lastTail.getX(), lastTail.getY() - lastTail.getSide(), lastTail.getSide()));
				}
				if(dir == 3){
					tail.add(new SquareMod(lastTail.getX() + lastTail.getSide(), lastTail.getY(), lastTail.getSide()));
				}

				//slumpa ut en ny position till äpplet
				apple = new SquareMod(random.nextInt(w.getWidth() / 25) * 25 + 1,
						random.nextInt(w.getHeight() / 25) * 25 + 1, 23);
			}

			//svansfärgen
			w.setLineColor(new Color(0xBF5C13));
			//rita svansen och kolla om spelaren krockar med svansen
			for(int i = 0; i < tail.size(); i++){
				
				w.setLineWidth(2);
				tail.get(i).draw(w);
				
				//w.moveTo(tempTail.getX(), tempTail.getY() + tempTail.getSide() / 2);
				//w.setLineWidth(tempTail.getSide());
				//w.lineTo(tempTail.getX() + tempTail.getSide(), tempTail.getY() + tempTail.getSide() / 2);
				
				//om spelaren krockar med svansen så är spelet över
				if(interSects(player,tail.get(i))){
					alive = false;
				}
				//om äpplet är på svansen så ska äpplet flyttas
				//äpplet kan fortfarande hamna under svansen men sannolikheten sänks
				if(interSects(tail.get(i), apple)){
					apple = new SquareMod(random.nextInt(w.getWidth() / 25) * 25 + 1,
							random.nextInt(w.getHeight() / 25) * 25 + 1, 23);
				}
			}

			//rita äpplet
			w.setLineColor(Color.RED);
			w.setLineWidth(1);
			apple.draw(w);
			w.setLineWidth(apple.getSide());
			w.moveTo(apple.getX(), apple.getY() + apple.getSide() / 2);
			w.lineTo(apple.getX() + apple.getSide(), apple.getY() + apple.getSide() / 2);

			//visa score
			w.setLineColor(Color.YELLOW);
			w.moveTo(15, 15);
			w.writeText("score : " +score);
			
			//vänta för input och delaya så att man max får 10 fps
			w.waitForEvent();
		}
	}

	/** returnerar true om de 2 kvadraterna nuddar varandra */
	static boolean interSects(SquareMod sq1, SquareMod sq2) {
		if ((sq1.getX() >= sq2.getX() || sq1.getX() + sq1.getSide() >= sq2.getX()) &&
				(sq1.getX() <= sq2.getX() + sq2.getSide() || sq1.getX() + sq1.getSide() <= sq2.getX() + sq2.getSide())) {

			if ((sq1.getY() >= sq2.getY() || sq1.getY() + sq1.getSide() >= sq2.getY()) &&
					(sq1.getY() <= sq2.getY() + sq2.getSide() || sq1.getY() + sq1.getSide() <= sq2.getY() + sq2.getSide())) {

				return true;
			}
		}
		return false;
	}
}
