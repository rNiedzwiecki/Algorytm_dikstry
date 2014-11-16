import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Admin on 2014-11-15.
 */
public class Wierzcholek {
    private Point p;
    private ArrayList<Krawedz> krawedzie;
    final static int PROMIEN=40;
    public Wierzcholek(Point temp)
    {
        krawedzie = new ArrayList<Krawedz>();
        p=new Point(temp.x,temp.y);
    }
    public Point getP()
    {
        return p;
    }
    public ArrayList<Krawedz> getKrawedzie()
    {
        return krawedzie;
    }
    public void dodajKrawedz(Krawedz a)
    {
        krawedzie.add(a);
    }
    public void rysuj(JPanel panel)
    {
        Graphics g= panel.getGraphics();
        int x=p.x-PROMIEN;
        int y=p.y-PROMIEN;

        g.drawOval(x,y,PROMIEN*2,PROMIEN*2);


    }

}
