package src.AsteroidMining;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Asteroid extends Place {

    private boolean hollow=false;
    protected int depth;
    protected int distanceFromSun;
    protected Resource resource;
    private BufferedImage img = null;

    public Asteroid(int x, int y, Resource r, int depth) {
        super(x, y, ID.Asteroid);

        if(r!=null)
            this.resource = r;
        else
            this.hollow = true;

        this.depth = depth;
        if(resource instanceof Uranium)
            this.setId(ID.RadioActiveAsteroid);

        try{
            img = ImageIO.read(new File("Assets/Asteroid.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        this.width = depth*10;
        this.height = depth*10;


    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.drawRect(x,y, width, height);
        g.drawImage(img, x, y,width,height, null);

    }


    public void deepenHole(int n){
        this.depth-=n;
    }
    public Resource getResource(){
        return this.resource;
    }

    public void addResource(Resource r){
        this.resource = r;
    }
    public void removeResource(){
        this.resource = null;
    }

    public boolean isHollow(){
        return hollow;
    }

    public boolean isPerihelion(){
        return false;
    }


}
