package eu.janinko.games.slider.entity;

import java.util.Random;

import eu.janinko.games.slider.Art;
import eu.janinko.games.slider.Screen;
import eu.janinko.games.slider.level.Level;
import eu.janinko.games.slider.locations.Circle;

public class Bullet extends Entity {
	public int life;
	public int speed;
	public int tick;
	
	public int type;
	

	public Bullet(Level l, int x, int y, int dir) {
		super(l, x, y, dir);
		life=100;
		speed = 6;
		type = new Random().nextInt(7)+1;
		area = new Circle(this, 8);
	}
	
	@Override
	public void tick() {
		if(--life <= 0){
			removed = true;
			return;
		}
		tick++;
		
		move(speed);
	}
	
	@Override
	public void render(Screen screen) {
		int state = (tick) % 14;
		if(state >= 7) state = 13 - state;
		screen.blit(Art.bullets[state+1][type], pos.x, pos.y);
	}
	
	

}
