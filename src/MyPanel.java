import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by Admin on 2014-11-16.
 */
public class MyPanel extends JPanel {
    private Point punktPocz = null;
    private Point punktKon = null;
    private boolean pierwszePrzejscie = true;
    private boolean dragMode = false;
    static final int PROMIEN = 10;


    public MyPanel() {
        super();
        punktPocz = new Point();
        punktKon = new Point();
        ustawJPanel();
        super.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (coRysuje()) {
                    nowyWierzcholek(e.getPoint());
                    repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (!coRysuje()) {
                    punktKon=e.getPoint();

                    nowaKrawedz(punktPocz, punktKon);
                    dragMode=false;
                    repaint();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (!coRysuje())
                    punktPocz = e.getPoint();
            }


        });
        super.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                System.out.println("DRAG");
                if (!coRysuje()) {
                    if (!dragMode) {
                        punktPocz = e.getPoint();
                        dragMode = true;
                    }
                    punktKon = e.getPoint();
                    repaint();
                }

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                Singleton.getInstance().okno.getLabMysz().setText("Położenie Myszy : [x=" + e.getPoint().x + " , y=" + e.getPoint().y + "]");
            }
        });

    }

    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2d = (Graphics2D) g;
        ustawGrafike(g2d);
        odtworzJPanel(g2d);
        if (!pierwszePrzejscie) {
            //Czy rysuje kolo
            if (coRysuje())
                rysujKolo(g2d);
            else
                rysujKrawedz(g2d);

        } else
            pierwszePrzejscie = false;

        if(!dragMode)
            odtworzJPanel(g2d);

    }

    private boolean coRysuje() {
        return Singleton.getInstance().okno.isCoRysuje();
    }

    private Wierzcholek nowyWierzcholek(Point p) {
        punktPocz = p;
        Wierzcholek temp = new Wierzcholek(p);
        Singleton.getInstance().wierzcholki.add(temp);
        return temp;
    }
    private void rysujKolo(Graphics g) {

        System.out.println("Mamy:" + Singleton.getInstance().wierzcholki.size());
        g.drawOval(punktPocz.x-PROMIEN, punktKon.y-PROMIEN, PROMIEN * 2, PROMIEN * 2);
    }

    private void rysujKolo(Graphics g, Point p) {
        g.drawOval(p.x - PROMIEN, p.y - PROMIEN, PROMIEN * 2, PROMIEN * 2);
    }

    private void rysujKrawedz(Graphics g, Point p1, Point p2) {
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
    }

    private void rysujKrawedz(Graphics g) {
        rysujKrawedz(g, punktPocz, punktKon);
    }

    private void ustawJPanel() {
        super.setSize(550, 550);
        super.setLocation(0, 0);
        super.setBorder(BorderFactory.createRaisedBevelBorder());
    }

    private void ustawGrafike(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(2));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    private void odtworzJPanel(Graphics2D g2d) {
        wyczyscJPanel(g2d);
        for (Wierzcholek w : Singleton.getInstance().wierzcholki) {
            rysujKolo(g2d, w.getP());
        }
        for (Krawedz kr : Singleton.getInstance().krawedzie) {
            rysujKrawedz(g2d, kr.getPoczatkowy().getP(), kr.getKoncowy().getP());
        }
    }

    private void wyczyscJPanel(Graphics2D g2d) {
        g2d.clearRect(0, 0, 550, 550);
    }

    private void nowaKrawedz(Point p1, Point p2) {

        Wierzcholek pocz= zwrocWierzcholekOIndeksie(najblizszyWierzcholek(p1));
        Wierzcholek kon=zwrocWierzcholekOIndeksie(najblizszyWierzcholek(p2));
        if(!wierzcholkiSaTakieSame(pocz,kon)) {
            Krawedz temp = new Krawedz(pocz, kon);
            if (!czyIstniejeTakaKrawedz(temp)) {
                Singleton.getInstance().krawedzie.add(temp);
            } else {
                wiadmoscOBledzie("Taka krawedz już istnieje");
            }
        }
    }

    private int najblizszyWierzcholek(Point p) {
        ArrayList<Wierzcholek> temp = Singleton.getInstance().wierzcholki;
        int najblizszy = 0;
        double najmniejszaOdleglosc = wyliczOdlegloscMiedzyPunktami(p, temp.get(0).getP());
        for (int i = 1; i < temp.size(); i++) {
            double aktualnaOdleglosc = wyliczOdlegloscMiedzyPunktami(p, temp.get(i).getP());
            if (najmniejszaOdleglosc > aktualnaOdleglosc) {
                najblizszy = i;
                najmniejszaOdleglosc = aktualnaOdleglosc;
            }
        }
        return najblizszy;

    }
    private double wyliczOdlegloscMiedzyPunktami(Point p1, Point p2)
    {
        return Math.sqrt(Math.pow((p1.y-p2.y),2)+Math.pow(p2.x-p1.x,2));
    }
    private Wierzcholek zwrocWierzcholekOIndeksie(int index)
    {
        return Singleton.getInstance().wierzcholki.get(index);
    }
    private boolean czyIstniejeTakaKrawedz(Krawedz kr)
    {
        for(Krawedz k:Singleton.getInstance().krawedzie)
        {
            if(k.equals(kr))
            {
                return true;
            }
        }
        return false;
    }
    private void wiadmoscOBledzie(String str)
    {
        JOptionPane.showMessageDialog(null,str,"Błąd",JOptionPane.WARNING_MESSAGE);
    }
    private boolean wierzcholkiSaTakieSame(Wierzcholek w1,Wierzcholek w2)
    {
        return w1==w2;
    }
}

