import java.awt.*;

/**
 * Created by Admin on 2014-11-15.
 */
public class Wierzcholek {
    private Point p;
    private int numer;
    public Wierzcholek(Point temp,int _numer)
    {
        p=new Point(temp.x,temp.y);
        numer=_numer;
    }
    public Point getP()
    {
        return p;
    }
    public int getNumer()
    {
        return numer;
    }
}
