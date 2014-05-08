package eu.janinko.games.slider;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;



public class Art {
    //public static Bitmap titleScreen = load("/TITLESCREEN.png");
    //public static Bitmap[][] mapIcons = cut("/mapicons.png", 5, 5);
    public static Bitmap[][] bullets = cut("/bullets.png", 8, 8);
    //public static Bitmap[][] preset = cut("/preset.png", 64, 54);
    //public static Bitmap[][] wallTiles = cut("/floortiles.png", 32, 56, 0, 104);
    public static Bitmap[][] floorTiles = cut("/floor.png", 64, 54);
    public static Bitmap[][] player = cut("/player.png", 64, 54);
    public static Bitmap[][] monster = cut("/monster.png", 64, 54);
    //public static int[][] floorTileColors = getColors(floorTiles);
	
	
private static Bitmap[][] cut(String string, int w, int h) {
    return cut(string, w, h, 0, 0);
}

private static Bitmap[][] cut(String string, int w, int h, int bx, int by) {
    try {
        BufferedImage bi = ImageIO.read(Slider.class.getResource(string));

        int xTiles = (bi.getWidth() - bx) / w;
        int yTiles = (bi.getHeight() - by) / h;

        Bitmap[][] result = new Bitmap[xTiles][yTiles];

        for (int x = 0; x < xTiles; x++) {
            for (int y = 0; y < yTiles; y++) {
                result[x][y] = new Bitmap(w, h);
                bi.getRGB(bx + x * w, by + y * h, w, h, result[x][y].pixels, 0, w);
            }
        }

        return result;
    } catch (IOException e) {
        e.printStackTrace();
    }
    return null;
}

private static int[][] getColors(Bitmap[][] tiles) {
    int[][] result = new int[tiles.length][tiles[0].length];
    for (int y = 0; y < tiles[0].length; y++) {
        for (int x = 0; x < tiles.length; x++) {
            result[x][y] = getColor(tiles[x][y]);
        }
    }
    return result;
}

private static int getColor(Bitmap bitmap) {
    int r = 0;
    int g = 0;
    int b = 0;
    for (int i = 0; i < bitmap.pixels.length; i++) {
        int col = bitmap.pixels[i];
        r += (col >> 16) & 0xff;
        g += (col >> 8) & 0xff;
        b += (col) & 0xff;
    }

    r /= bitmap.pixels.length;
    g /= bitmap.pixels.length;
    b /= bitmap.pixels.length;

    return 0xff000000 | r << 16 | g << 8 | b;
}

private static Bitmap load(String string) {
    try {
        BufferedImage bi = ImageIO.read(Slider.class.getResource(string));

        int w = bi.getWidth();
        int h = bi.getHeight();

        Bitmap result = new Bitmap(w, h);
        bi.getRGB(0, 0, w, h, result.pixels, 0, w);

        return result;
    } catch (IOException e) {
        e.printStackTrace();
    }

    return null;
}

private static Bitmap[] cut(String string, int h) {
    try {
        BufferedImage bi = ImageIO.read(Slider.class.getResource(string));

        int yTiles = bi.getHeight() / h;
        int w = bi.getWidth();

        Bitmap[] result = new Bitmap[yTiles];

        for (int y = 0; y < yTiles; y++) {
            result[y] = new Bitmap(w, h);
            bi.getRGB(0, y * h, w, h, result[y].pixels, 0, w);
        }

        return result;
    } catch (IOException e) {
        e.printStackTrace();
    }

    return null;
}

}
