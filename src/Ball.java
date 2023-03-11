import java.awt.*;
import java.util.Random;


public class Ball extends Rectangle{
    int xVeleocity;
    int yVeleocity;
    int initialspeed=5;
    Random random;
    Ball(int x,int y, int width,int height){
        super(x,y,width,height);
        random = new Random();
        int RandomXDirection =  random.nextInt(2);
        if(RandomXDirection==0)
        {
            RandomXDirection--;
        }
        setXDirection(RandomXDirection);
        int RandomYDirection =  random.nextInt(2);
        if(RandomYDirection==0)
        {
            RandomYDirection--;
        }
        setYDirection(RandomYDirection);

    }

    public void setYDirection(int randomYDirection) {
        yVeleocity=randomYDirection;
    }

    public void setXDirection(int randomXDirection) {
        xVeleocity=randomXDirection;
    }
    public void move()
    {
        x+=xVeleocity;
        y+=yVeleocity;

    }

    public void draw(Graphics g)
    {
        g.setColor(Color.white);
        g.fillOval(x,y,width,height);

        g.drawLine(1000/2,0,1000/2,555);
    }

}
