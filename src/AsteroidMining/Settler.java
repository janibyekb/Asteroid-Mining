package src.AsteroidMining;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Settler extends Visitor{

    private List<TeleportationGate> gates;
    private SpaceShip spaceship;
    private BufferedImage img = null;



    public Settler(int x, int y) {

        super(x, y, ID.Settler);
        try{
            img = ImageIO.read(new File("Assets/spaceship.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * @author kasay
     */
    public boolean putResource(Resource r, Asteroid a){

        Place p=this.getPlace();
        if(p.equals(a)) {
            if(a.isHollow()&&spaceship.countResource(r)>=1)
            {}
        }
        return false;
    }

    @Override
    public void tick() {
        x = x+velX;
        y = y+velY;

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img, x, y, 100, 100, null);
    }
}
