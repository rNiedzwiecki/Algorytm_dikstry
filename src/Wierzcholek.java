import java.awt.*;

/**
 * Created by Admin on 2014-11-15.
 */
public class Wierzcholek {
    private Point p;
    public Wierzcholek(Point temp)
    {
        p=new Point(temp.x,temp.y);
    }
    public Point getP()
    {
        return p;
    }
}
