package eu.janinko.games.slider.mob;

public class Mob {
	protected double x;
	protected double y;
	protected double dir;
	protected double speed;
	
	protected double w;
	protected double h;
	
	
	public void tick(){
		move();
		
	}


	private void move() {
		this.move(Math.sin(dir)*speed,Math.cos(dir)*speed);
	}


	private void move(double xx, double yy) {
		double tx = x+xx;
		double ty = y+yy;
		double cw = Math.max(Math.abs(Math.cos(dir)*w), Math.abs(Math.sin(dir)*h));
		double ch = Math.max(Math.abs(Math.cos(dir)*h), Math.abs(Math.sin(dir)*w));

		if(tx < cw/2) tx = cw/2;
		if(ty < ch/2) tx = ch/2;
		
	}
	

}
