package eu.janinko.games.slider.entity;

import eu.janinko.games.slider.Screen;
import eu.janinko.games.slider.level.Level;
import eu.janinko.games.slider.locations.Area;
import eu.janinko.games.slider.locations.Position;

public class Entity {
	public boolean removed = false;
	public Level level;
	public Position pos;
	public Area area;
	
	public Entity(Level l, int x, int y, int dir){
		level = l;
		pos = new Position(x, y, dir);
	}
	
	
	public void tick(){
		
	}

	public void render(Screen screen){
		
	}
	
	public void move(int speed){
		double angle = Math.toRadians(pos.dir * 60);
		double dx = Math.sin(angle)*speed;
		double dy = -Math.cos(angle)*speed;
		
		move((int) dx,(int) dy);
	}
	
	public void move(int dx, int dy){
		
		Area a = this.area.copy();
		a.move((int) dx, (int) dy);
		boolean moved = true;
		for(Entity e : level.entities){
			if(e == this) continue;
			if(e.area.intersect(a)){
				e.colide(this);
				moved = false;
			}
		}
		if(!moved) return;
		
		pos.x += dx;
		pos.y += dy;
		area.move((int) dx, (int) dy);
	}


	public void colide(Entity entity) {
	}

}
