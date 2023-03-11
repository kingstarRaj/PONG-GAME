import java.awt.*;

public class Score extends Rectangle {
    int width;
    int height;
    int Player1;
    int Player2;

    Score(int width,int height){
        this.width=width;
        this.height=height;
    }
    public void draw(Graphics g)
    {
       g.setColor(Color.white);
       g.setFont(new Font("Consolas",Font.PLAIN,60));
       g.drawString(String.valueOf(Player1),width/2-80,100);
       g.drawString(String.valueOf(Player2),width/2+40,100);

    }
}
