package eu.janinko.games.slider;

import eu.janinko.games.slider.entity.Bullet;
import eu.janinko.games.slider.entity.Entity;
import eu.janinko.games.slider.level.Level;
import eu.janinko.games.slider.locations.Square;

public class Player extends Entity {
	private int speed=3;
	
	public Player(Level l, int x, int y, int dir) {
		super(l, x, y, dir);
		area = new Square(this, x-16, y-16, x+16, y+16);
	}
	
	public Player(Level l) {
		super(l, 190, 160, 0);
		area = new Square(this, 190-16, 160-16, 190+16, 160+16);
	}
	
	public void tick(){
		if(level.tick % 8 == 0){
			pos.dir = (pos.dir + 1) % 6;
		}
		move(speed);
		if(level.tick % 3 == 0){
			level.entities.add(new Bullet(level, (int) pos.x, (int) pos.y, pos.dir));
		}
		
	}
	
	public void render(Screen screen){
		
		Bitmap image = Art.player[pos.dir][0];
        screen.blit(image, pos.x - 16, pos.y - 16);
		
	}

}
