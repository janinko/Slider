package eu.janinko.games.slider.level;

import java.util.ArrayList;
import java.util.Random;

import eu.janinko.games.slider.Art;
import eu.janinko.games.slider.Bitmap;
import eu.janinko.games.slider.Player;
import eu.janinko.games.slider.Screen;
import eu.janinko.games.slider.entity.Entity;
import eu.janinko.games.slider.entity.Monster;

public class Level {
	public int tick;
	private Bitmap background;
	
	public Random generator = new Random();
	
	Player player;
	
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	
	public Level(){
		player = new Player(this);
		background = new Bitmap(7*98,12*54);
		
		for(int y=0; y < 20; y++){
			for(int x=0; x<8; x++){
				background.blit(Art.floorTiles[generator.nextInt(8)][0], x*96 + (y%2)*48 - 32, y*27 - 27);
			}
		}
	}
	
	
	
	public void tick(){
		tick++;
		player.tick();
		if(tick % 30 == 0){
			entities.add(new Monster(this, generator.nextInt(700) + 20, generator.nextInt(400) + 20, 0));
		}
		for(int i=0; i< entities.size(); i++){
			if(entities.get(i).removed){
				entities.remove(i--);
			}else{
				entities.get(i).tick();
			}
		}
	}
	
	public void render(Screen screen){
		screen.blit(background, 0, 0);
		
		

		for(int i=0; i< entities.size(); i++){
			if(!entities.get(i).removed){
				entities.get(i).render(screen);
			}
		}

        player.render(screen);
	}

}
