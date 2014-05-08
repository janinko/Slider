package eu.janinko.games.slider.locations;

import eu.janinko.games.slider.entity.Entity;


public class Square extends Area{
	Position first;
	Position second;
	
	public Square(Entity e, int x0, int y0, int x1, int y1){
		first = new Position(x0, y0);
		second = new Position(x1, y1);
		owner = e;
	}
	
	public Square(int x0, int y0, int x1, int y1){
		first = new Position(x0, y0);
		second = new Position(x1, y1);
		owner = null;
	}
	
	public Square(Position f, Position s){
		first = new Position(f);
		second = new Position(s);
		owner = null;
	}

	@Override
	public boolean intersect(Area a) {
		if(a instanceof Square){
			Square s = (Square) a;
			
			if(second.x < s.first.x || first.x > s.second.x || second.y < s.first.y || first.y > s.second.y){
				return false;
			}else{
				return true;
			}
		}else if(a instanceof Circle){
			a.intersect(this);
		}
		
		return false;
	}
	
	@Override
	public Square clone(){
		return new Square(first, second);
	}

	@Override
	public void move(int dx, int dy) {
		this.first.x += dx;
		this.first.y += dy;
		this.second.x += dx;
		this.second.y += dy;
	}

	@Override
	public Square copy() {
		return new Square(first, second);
	}
	

}
