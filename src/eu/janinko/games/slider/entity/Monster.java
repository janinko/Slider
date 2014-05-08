package eu.janinko.games.slider.entity;

import eu.janinko.games.slider.Art;
import eu.janinko.games.slider.Screen;
import eu.janinko.games.slider.level.Level;
import eu.janinko.games.slider.locations.Square;

public class Monster extends Entity {
	int lives;

	public Monster(Level l, int x, int y, int dir) {
		super(l, x, y, dir);
		lives = 600;
		area = new Square(this, x-22, y-22, x+22, y+22);
	}

	@Override
	public void tick() {
		if(--lives <= 0){
			removed = true;
			return;
		}
		move(level.generator.nextInt(15) - 7, level.generator.nextInt(15) - 7);
		lives--;
	}

	@Override
	public void render(Screen screen) {
		screen.blit(Art.monster[(lives / 2) % 8][0], pos.x - 32, pos.y - 27);
	}
	

	@Override
	public void colide(Entity entity) {
		System.out.println("Auč!");
		lives -= 30;
		super.colide(entity);
	}
}
