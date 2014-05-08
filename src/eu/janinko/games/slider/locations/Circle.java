package eu.janinko.games.slider.locations;

import eu.janinko.games.slider.entity.Entity;

public class Circle extends Area {
	Position center;
	int radius;
	
	public Circle(Entity e, int r){
		owner = e;
		center = new Position(e.pos);
	}
	
	public Circle(Position c, int r){
		center = new Position(c);
		radius = r;
		owner = null;
	}
	
	public Circle(int x, int y, int r){
		center = new Position(x,y);
		radius = r;
		owner = null;
	}

	@Override
	public boolean intersect(Area a) {
		if(a instanceof Circle){
			Circle c = (Circle) a;
			
			if(Math.round(
					Math.sqrt(
							(center.x - c.center.x) * (center.x - c.center.x)
						  + (center.y - c.center.y) * (center.y - c.center.y))) < radius){
				return true;
			}else{
				return false;
			}
		}else if(a instanceof Square){
			Square s = (Square) a;

			double halfwidth = (s.second.x - s.first.x) / 2;
			double halfheight = (s.second.y - s.first.y) / 2;

			double cdistx = (center.x - s.first.x - halfwidth);
			double cdisty = (center.y - s.first.y - halfwidth);

			if(cdistx > radius + halfwidth) return false;
			if(cdisty > radius + halfheight) return false;

			if(cdistx <= halfwidth) return true;
			if(cdisty <= halfheight) return true;
			
			double cornerdist = (cdistx - halfwidth)*(cdistx - halfwidth)
					          + (cdisty - halfheight)*(cdisty - halfheight);
			
			return cornerdist <= radius * radius;
		}
		
		return false;
	}

	@Override
	public void move(int dx, int dy) {
		this.center.x += dx;
		this.center.y += dy;
		
	}

	@Override
	public Circle copy() {
		return new Circle(center, radius);
	}

}
