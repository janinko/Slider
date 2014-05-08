package eu.janinko.games.slider.locations;

public class Position {
	public double x;
	public double y;
	public int dir;
	
	public Position(int x2, int y2, int dir2) {
		x = x2;
		y = y2;
					//  .       .
		dir = dir2; // / - \ / - \
					//      ' 
	}

	public Position(Position p) {
		x = p.x;
		y = p.y;
	}

	public Position(int x2, int y2) {
		x = x2;
		y = y2;
	}
}
