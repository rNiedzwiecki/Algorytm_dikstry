import javax.swing.*;
import java.awt.*;

/**
 * Created by Admin on 2014-11-15.
 */
public class Krawedz {
    private Wierzcholek Poczatkowy;
    private Wierzcholek Koncowy;
    private int waga;

    public Krawedz()
    {
        Poczatkowy= new Wierzcholek(new Point(0,0));
        Koncowy=new Wierzcholek(new Point(0,0));

    }
    public Wierzcholek getPoczatkowy() {
        return Poczatkowy;
    }

    public void setPoczatkowy(Wierzcholek poczatkowy) {
        Poczatkowy = poczatkowy;
        System.out.println("Ustawielm wartosc");
    }

    public Wierzcholek getKoncowy() {
        return Koncowy;
    }

    public void setKoncowy(Wierzcholek koncowy) {
        Koncowy = koncowy;
    }

    public int getWaga() {
        return waga;
    }

    public void setWaga(int waga) {
        this.waga = waga;
    }
    public void rysuj(JPanel panel)
    {
        Graphics g= panel.getGraphics();
        g.drawLine(Poczatkowy.getP().x,Poczatkowy.getP().y,Koncowy.getP().x,Koncowy.getP().y);
    }
    public void rysuj(JPanel panel, Point p)
    {
        Graphics g= panel.getGraphics();
        g.drawLine(Poczatkowy.getP().x,Poczatkowy.getP().y,p.x,p.y);
        System.out.println("Punkt gdzie zaczynam "+ Poczatkowy.getP().toString());
        System.out.println("Punkt gdzie zaczynam1242 "+ Koncowy.getP().toString());
    }

}
