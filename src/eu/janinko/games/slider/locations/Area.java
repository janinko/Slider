package eu.janinko.games.slider.locations;

import eu.janinko.games.slider.entity.Entity;

public abstract class Area {
	Entity owner;
	
	public abstract boolean intersect(Area a);
	
	public abstract void move(int dx, int dy);
	
	public abstract Area copy();
	
}
