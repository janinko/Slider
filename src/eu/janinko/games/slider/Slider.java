package eu.janinko.games.slider;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

import eu.janinko.games.slider.level.Level;


public class Slider extends Canvas implements Runnable {
	public final int GAME_WIDTH = 686;
	public final int GAME_HEIGHT = 400;
	public final int SCALE = 1;
	
    private Screen screen = new Screen(GAME_WIDTH, GAME_HEIGHT);
    
	private boolean running;
	private int framerate = 30;
	private int fps;
	
	private Level level;
	
	
	public Slider(){
        this.setPreferredSize(new Dimension(GAME_WIDTH * SCALE, GAME_HEIGHT * SCALE));
        this.setMinimumSize(new Dimension(GAME_WIDTH * SCALE, GAME_HEIGHT * SCALE));
        this.setMaximumSize(new Dimension(GAME_WIDTH * SCALE, GAME_HEIGHT * SCALE));
        //this.addKeyListener(new InputHandler(keys));
        //this.addMouseMotionListener(this);
        //this.addMouseListener(this);

        //TitleMenu menu = new TitleMenu(GAME_WIDTH, GAME_HEIGHT);
        //addMenu(menu);
        //addKeyListener(this);
	}
	
	
	public static void main(String[] args){
		Slider s = new Slider();
        JFrame frame = new JFrame();
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(s);
        frame.setContentPane(panel);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        s.start();
	}

	public void start() {
        running = true;
        Thread thread = new Thread(this);
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
	}


	@Override
	public void run() {
        long lastTime = System.nanoTime();
        double unprocessed = 0;
        int frames = 0;
        long lastTimer1 = System.currentTimeMillis();
        
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        
        int toTick = 0;

        long lastRenderTime = System.nanoTime();
        int min = 999999999;
        int max = 0;
		
        while (running) {
            double nsPerTick = 1000000000.0 / framerate;
            boolean shouldRender = false;
            
            while (unprocessed >= 1) {
                toTick++;
                unprocessed -= 1;
            }
            
            int tickCount = toTick;
            if (toTick > 0 && toTick < 3) {
                tickCount = 1;
            }
            if (toTick > 20) {
                toTick = 20;
            }
            
            for (int i = 0; i < tickCount; i++) {
                toTick--;
                tick();
                shouldRender = true;
            }

            BufferStrategy bs = getBufferStrategy();
            if (bs == null) {
                createBufferStrategy(3);
                continue;
            }
            if (shouldRender) {
                frames++;
                Graphics g = bs.getDrawGraphics();

                //Random lastRandom = TurnSynchronizer.synchedRandom;
                //TurnSynchronizer.synchedRandom = null;

                render(g);

                //TurnSynchronizer.synchedRandom = lastRandom;

                long renderTime = System.nanoTime();
                int timePassed = (int) (renderTime - lastRenderTime);
                if (timePassed < min) {
                    min = timePassed;
                }
                if (timePassed > max) {
                    max = timePassed;
                }
                lastRenderTime = renderTime;
            }
            long now = System.nanoTime();
            unprocessed += (now - lastTime) / nsPerTick;
            lastTime = now;

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (shouldRender) {
                if (bs != null) {
                    bs.show();
                }
            }

            if (System.currentTimeMillis() - lastTimer1 > 1000) {
                lastTimer1 += 1000;
                fps = frames;
                frames = 0;
                System.out.println(fps);
            }
        }
		
	}


	private void render(Graphics g) {
        g.setColor(Color.BLACK);

        g.fillRect(0, 0, getWidth(), getHeight());
        g.translate((getWidth() - GAME_WIDTH * SCALE) / 2, (getHeight() - GAME_HEIGHT * SCALE) / 2);
        g.clipRect(0, 0, GAME_WIDTH * SCALE, GAME_HEIGHT * SCALE);

        level.render(screen);

        g.drawImage(screen.image, 0, 0, GAME_WIDTH * SCALE, GAME_HEIGHT * SCALE, null);
	}


	private void tick() {
		level.tick();
	}


	private void init() {
		level = new Level();
	}

}
