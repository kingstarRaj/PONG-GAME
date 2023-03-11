import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {
    int width = 1000;
    int height = (int)(0.555*width);

    int Paddle_height = 100;
    int Paddle_width = 25;

    //creating paddle obj
    Paddle paddle1;
    Paddle paddle2;

    int ball_diameter = 20;
    Ball ball;

    //while running it create multiple task so creates a thread to handle them all
    Thread gameThread;


    // to use paint func we takes graphics class for editing
    Graphics graphics;

    //will add some images
    Image image;
    Score score = new Score(width,height);

    Dimension Screen_size = new Dimension(width,height);

    //Constructor
    GamePanel(){
       newPaddles();
       newBall();
       this.setFocusable(true);
       this.addKeyListener(new AL());

       this.setPreferredSize(Screen_size);

       gameThread = new Thread(this);
       gameThread.start();
       

    }

    private void newBall() {
        Random random = new Random();
        ball = new Ball(width/2-ball_diameter/2,random.nextInt(height-ball_diameter),ball_diameter,ball_diameter);

    }

    private void newPaddles() {
        paddle1 =  new Paddle(0,height/2-Paddle_height/2,Paddle_width,Paddle_height,1);
        paddle2 = new Paddle(width-Paddle_width,height/2-Paddle_height/2,Paddle_width,Paddle_height,2);


    }

    @Override
    public void paint(Graphics g) {
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
        
    }

    private void draw(Graphics g) {
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }

    @Override
    public void run() {
        long Lasttime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/amountOfTicks;
        double delta = 0;
        while(true){
            long now = System.nanoTime();
            delta+=(now - Lasttime)/ns;
            Lasttime = now;
            if(delta >=1){
                move();
                repaint();
                checkCollision();
                delta--;
            }


        }
        
    }

    private void checkCollision() {
        if(ball.y<=0){
            ball.setYDirection(-ball.yVeleocity);
        }
        if(ball.y>=height-ball_diameter){
            ball.setYDirection(-ball.yVeleocity);
        }
        if(ball.intersects(paddle1))
        {
            ball.xVeleocity=-ball.xVeleocity;
            ball.xVeleocity++;
            if(ball.yVeleocity>0)
            {
                ball.yVeleocity++;
            }
            else {
                ball.yVeleocity--;
            }
            ball.setXDirection(ball.xVeleocity);
            ball.setYDirection(ball.yVeleocity);

        }
        if(ball.intersects(paddle2))
        {
            ball.xVeleocity=-ball.xVeleocity;
            ball.xVeleocity++;
            if(ball.yVeleocity>0)
            {
                ball.yVeleocity++;
            }
            else {
                ball.yVeleocity--;
            }
            ball.setXDirection(ball.xVeleocity);
            ball.setYDirection(ball.yVeleocity);

        }
        if(paddle1.y<=0)
        {
            paddle1.y=0;
        }
        if(paddle1.y>=height-Paddle_height)
        {
            paddle1.y=height-Paddle_height;
        }
        if(paddle2.y<=0)
        {
            paddle2.y=0;
        }
        if(paddle2.y>=height-Paddle_height)
        {
            paddle2.y=height-Paddle_height;
        }

        //check the ball
        if(ball.x>=width-ball_diameter)
        {
            newPaddles();
            newBall();
            score.Player1++;

        }
        if(ball.x<=0)
        {
            newPaddles();
            newBall();
            score.Player2++;

        }
    }

    private void move() {
        paddle1.move();
        paddle2.move();
        ball.move();
    }
    public class AL extends KeyAdapter{

        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);

        }


        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}
