package src.AsteroidMining;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;

public class Game extends Canvas implements Runnable{

    private Thread thread;
    protected static boolean running = true;

    public static int WIDTH = 1000;
    public static int HEIGHT = WIDTH / 12 * 9;
    Handler handler;
    BufferedImage backImg = null;

    public Game(){
        handler = new Handler();
        Settler settler = new Settler(300, 400);
        handler.addObject(new Asteroid(100, 200, new Carbon(), 10));
        handler.addObject(new Asteroid(400, 220, new Iron(), 10));
        handler.addObject(new Asteroid(600, 250, new WaterIce(), 10));
        handler.addObject(settler);

        this.addKeyListener(new KeyHandler(handler, this));

        new Window(WIDTH, HEIGHT, "Asteroid Mining", this);

        this.requestFocusInWindow(); // if this is not taken we do not take input from the user

    }
    // after constructor come here
    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/ amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while(running) {
            long now = System.nanoTime();
            delta += (now- lastTime) / ns;
            lastTime = now;

            while(delta >= 1) {
                tick();
                delta--;

            }
            if(running) {
                render();
            }

            frames++;

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                //System.out.println("FPS:" + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        //System.out.println("Tick!");
        handler.tick();
    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs==null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        try{
            backImg = ImageIO.read(new File("Assets/space.png"));
            //g.drawImage(backImg,0,0, WIDTH/2, HEIGHT/2, null);
            g.drawImage(backImg, 0,0,this);
        }
        catch(IOException e){
            e.printStackTrace();
        }

        //g.setColor(Color.black);
        //g.fillRect(0, 0, WIDTH, HEIGHT);


        handler.render(g);

        g.dispose();
        bs.show();
    }

    public static void main(String args[]){
        Game game = new Game();

        System.out.println("Hello");
    }
}
