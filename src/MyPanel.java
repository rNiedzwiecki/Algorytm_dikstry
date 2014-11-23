import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Admin on 2014-11-16.
 */
public class MyPanel extends JPanel {
    private Point punktPocz = null;
    private Point punktKon = null;
    private boolean dragMode = false;
    private boolean czyJaWywoluje = false;
    private boolean czyKoniecModyfikacji=false;
    private ArrayList<Boolean> historia;
    private int aktualnaKopia=0;
    static final int PROMIEN = 15;


    public MyPanel() {
        super();

        zerujPunkty();
        historia = new ArrayList<Boolean>();
        ustawJPanel();

        super.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (coRysuje()) {
                    czyJaWywoluje = true;
                    czyKoniecModyfikacji=true;
                    nowyWierzcholek(e.getPoint());
                    wyczyscWynik();
                    repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (!coRysuje()) {
                    if(iloscWierzcholkow()>=2)
                    {
                        punktKon = e.getPoint();
                        czyJaWywoluje = false;
                        czyKoniecModyfikacji=true;
                        usunKopie();
                        wyczyscWynik();
                        nowaKrawedz(punktPocz, punktKon);
                    }
                    else
                    {
                        zerujPunkty();
                    }
                    dragMode=false;
                    repaint();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

                usunKopie();
                if (!coRysuje() && iloscWierzcholkow()>=2 )
                {
                    punktPocz = e.getPoint();
                }
            }


        });
        super.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (!coRysuje()) {
                        if (!dragMode) {
                            punktPocz = e.getPoint();
                            dragMode = true;
                            czyJaWywoluje = false;
                            czyKoniecModyfikacji = true;
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

    private void wyczyscWynik() {
        if(Singleton.getInstance().droga.size()!=0)
            Singleton.getInstance().droga.clear();
    }

    private int iloscWierzcholkow() {
        return Singleton.getInstance().wierzcholki.size();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        ustawGrafike(g2d, 2, Color.BLACK);
        odtworzJPanel(g2d);
        Rysuj(g2d);
        if (!dragMode&& iloscWierzcholkow()>0)
            odtworzJPanel(g2d);
        if(Singleton.getInstance().droga.size()!=0&& iloscWierzcholkow()>0) {
            rysujWynik(g2d);
        }
        else
            wyczyscWynik();
    }

    private void rysujWynik(Graphics2D g2d) {
        ustawGrafike(g2d,3,Color.GREEN);
        ArrayList<Integer> droga=Singleton.getInstance().droga;
        ArrayList<Wierzcholek>w=Singleton.getInstance().wierzcholki;
        Point wspolrzednePoczatkaKrawedzi=null;
        Point wspolrzedneKoncaKrawedzi=null;
        for(int i=0;i<droga.size()-1;i++) {
            wspolrzednePoczatkaKrawedzi=w.get(droga.get(i)).getP();
            wspolrzedneKoncaKrawedzi=w.get(droga.get(i+1)).getP();
            rysujKolo(g2d, wspolrzednePoczatkaKrawedzi);
            rysujKrawedz(g2d,wspolrzednePoczatkaKrawedzi,wspolrzedneKoncaKrawedzi);
        }
       rysujKolo(g2d,wspolrzedneKoncaKrawedzi);

    }

    private boolean coRysuje() {
        return Singleton.getInstance().okno.isCoRysuje();
    }

    private void Rysuj(Graphics2D g2d) {


        if(czyKoniecModyfikacji)
        {
            usunKopie();

            ustawPrzyciskDoPrzodu(false);

        }
        if (coRysuje()&& punktPocz!=null) {
            rysujKolo(g2d);
        }
        else {
            rysujKrawedz(g2d);
        }

        if (!jestWDragMode() && czyJaWywoluje) {
            czyJaWywoluje = false;
            dodajDoHistorii(coRysuje());

        }
        ustawLabAktualnaKopia();
    }

    private Wierzcholek nowyWierzcholek(Point p) {
        punktPocz = p;
        Wierzcholek temp = new Wierzcholek(p,aktualnyNumerWierzcholka());
        Singleton.getInstance().wierzcholki.add(temp);
        return temp;
    }

    private int aktualnyNumerWierzcholka() {
        return Singleton.getInstance().wierzcholki.size();
    }

    private void rysujKolo(Graphics2D g2d) {
        g2d.drawOval(punktPocz.x - PROMIEN, punktKon.y - PROMIEN, PROMIEN * 2, PROMIEN * 2);

    }

    private void rysujKolo(Graphics2D g2d, Point p) {
        g2d.drawOval(p.x - PROMIEN, p.y - PROMIEN, PROMIEN * 2, PROMIEN * 2);
    }

    private void rysujKrawedz(Graphics2D g2d, Point p1, Point p2) {
        g2d.drawLine(p1.x, p1.y, p2.x, p2.y);

    }

    private void rysujKrawedz(Graphics2D g2d) {
        rysujKrawedz(g2d, punktPocz, punktKon);
    }

    private void ustawJPanel() {
        super.setSize(550, 550);
        super.setLocation(0, 0);
        super.setBorder(BorderFactory.createRaisedBevelBorder());
        super.setBackground(Color.white);
    }

    private void ustawGrafike(Graphics2D g2d,int rozmiar,Color col) {
        g2d.setFont(new Font(null,Font.BOLD,14));
        g2d.setColor(col);
        g2d.setStroke(new BasicStroke(rozmiar));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    private void odtworzJPanel(Graphics2D g2d) {
        wyczyscJPanel(g2d);
        ArrayList<Wierzcholek> w = Singleton.getInstance().wierzcholki;
        ArrayList<Krawedz> kr = Singleton.getInstance().krawedzie;
        int ileByloWierzcholkow=0;
        int ileByloKrawedzi=0;
        for(int i=0;i<aktualnaKopia;i++)
        {
            if(historia.get(i)) {
                Point punktWierzcholka=w.get(ileByloWierzcholkow).getP();
                rysujKolo(g2d, punktWierzcholka);
                napiszNumerWierzcholka(g2d,ileByloWierzcholkow,punktWierzcholka);
                ileByloWierzcholkow++;
            }
            else
            {


                    Point poczatek=kr.get(ileByloKrawedzi).getPoczatkowy().getP();
                    Point koniec=kr.get(ileByloKrawedzi).getKoncowy().getP();
                    Point wspWagi=wspolrzedneDoWpisaniaWagi(poczatek,koniec);
                    int waga=kr.get(ileByloKrawedzi).getWaga();
                    rysujKrawedz(g2d, poczatek, koniec);
                    napiszWageKrawedz(g2d,wspWagi,waga);
                    ileByloKrawedzi++;
            }

        }
    }

    private void napiszWageKrawedz(Graphics2D g2d, Point wspWagi, int waga) {
        ustawGrafike(g2d,2,Color.RED);
        g2d.drawString(Integer.toString(waga),wspWagi.x,wspWagi.y);
        ustawGrafike(g2d,2,Color.BLACK);
    }

    private void napiszNumerWierzcholka(Graphics2D g2d, int ileByloWierzcholkow,Point punktWierzcholka) {
        ustawGrafike(g2d,4,Color.RED);
        g2d.drawString(Integer.toString(ileByloWierzcholkow),punktWierzcholka.x,punktWierzcholka.y);
        ustawGrafike(g2d,2,Color.black);
    }

    private void wyczyscJPanel(Graphics2D g2d) {
        g2d.clearRect(0, 0, 550, 550);
    }

    private void nowaKrawedz(Point p1, Point p2) {
        Wierzcholek pocz= zwrocWierzcholekOIndeksie(najblizszyWierzcholek(p1));
        Wierzcholek kon=zwrocWierzcholekOIndeksie(najblizszyWierzcholek(p2));
        if(!wierzcholkiSaTakieSame(pocz,kon)) {
            int wagaKrawedzi=pobierzWageKrawedzi();
            Krawedz temp = new Krawedz(pocz, kon,wagaKrawedzi);
            if (!czyIstniejeTakaKrawedz(temp)) {
                Singleton.getInstance().krawedzie.add(temp);
                czyJaWywoluje=true;
            } else {
                wiadomoscOBledzie("Taka krawedz już istnieje");
            }
        }
        else{
            czyJaWywoluje=false;
        }
    }

    private int pobierzWageKrawedzi() {
        return (Integer)Singleton.getInstance().okno.getSpinWaga().getValue();
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
        return Math.sqrt(Math.pow((p1.y - p2.y), 2) + Math.pow(p2.x - p1.x, 2));
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
    private void wiadomoscOBledzie(String str)
    {
        JOptionPane.showMessageDialog(null,str,"Błąd",JOptionPane.WARNING_MESSAGE);
    }
    private boolean wierzcholkiSaTakieSame(Wierzcholek w1,Wierzcholek w2)
    {
        return w1==w2;
    }
    private boolean jestWDragMode()
    {
        return dragMode;
    }
    private void dodajDoHistorii(boolean co)
    {
        if(aktualnaKopia<historia.size())
            usunKopie();
        historia.add(co);
        aktualnaKopia++;
        ustawPrzycziskWstecz(true);
    }

    private void usunKopie() {
        while(aktualnaKopia!=historia.size()) {
            if(historia.get(aktualnaKopia)==true) {
                if(iloscWierzcholkow()>2)
                    Singleton.getInstance().wierzcholki.remove(Singleton.getInstance().wierzcholki.size() - 2);
                else
                    Singleton.getInstance().wierzcholki.remove(iloscWierzcholkow()-1);
            }
            else {
                if(Singleton.getInstance().krawedzie.size()>2)
                    Singleton.getInstance().krawedzie.remove(Singleton.getInstance().krawedzie.size() - 1);
                else
                    Singleton.getInstance().krawedzie.remove(Singleton.getInstance().krawedzie.size()-1);
            }


            historia.remove(aktualnaKopia);
        }
    }

    public void cofnijKopie()
    {
        if(aktualnaKopia>0)
        {
            czyKoniecModyfikacji=false;
            aktualnaKopia--;
            ustawPrzyciskDoPrzodu(true);
            repaint();
            if(aktualnaKopia==0)
            {
                ustawPrzycziskWstecz(false);
            }
        }
    }

    private void ustawPrzycziskWstecz(boolean stan) {
        Singleton.getInstance().okno.getButCofnij().setEnabled(stan);
    }

    public void doPrzoduKopie()
    {
        czyKoniecModyfikacji=false;
        if(aktualnaKopia<historia.size())
        {
            aktualnaKopia++;
            repaint();
            ustawPrzycziskWstecz(true);
            if(aktualnaKopia==historia.size())
            {
                ustawPrzyciskDoPrzodu(false);
            }
        }
    }

    private void ustawPrzyciskDoPrzodu(boolean stan) {
        Singleton.getInstance().okno.getButDoPrzodu().setEnabled(stan);
    }
    private void ustawLabAktualnaKopia()
    {
        Singleton.getInstance().okno.getLabAktKopia().setText("Aktualna kopia: " + Integer.toString(aktualnaKopia) + "/" + Integer.toString(historia.size()));
    }
    public void wyczysc()
    {
        aktualnaKopia=0;
        Singleton.getInstance().krawedzie.clear();
        Singleton.getInstance().wierzcholki.clear();
        Singleton.getInstance().droga.clear();
        historia.clear();
        ustawPrzycziskWstecz(false);
        repaint();
    }
    private Point wspolrzedneDoWpisaniaWagi(Point p1,Point p2)
    {
        Point zwr=new Point();
        //wierzcholki sa na jednej linii poziomej
        if(Math.abs(p2.x-p1.x)<5)
        {
            zwr.x=p2.x+3;
            int temp=Math.abs(p2.y - p1.y)/2;
            if(p2.y>p1.y)
            {
                zwr.y=(p1.y+temp);
            }
            else
                zwr.y=(p2.y+temp);
        }
        //wierzcholki sa na jednej linii pionowej
        else if(Math.abs(p2.y-p1.y)<5)
        {
            zwr.y=(p2.y-20);
            int temp=Math.abs(p2.x-p1.x)/2;
            if(p2.x>p1.x)
            {
                zwr.x=(p1.x+temp);
            }
            else
            {
                zwr.x=(p2.x+temp);
            }
        }
        // wierzcholki ulozone w sposob " \ "
        else if(p1.x>p2.x)
        {
            int tempx=(p1.x-p2.x)/2;
            zwr.x=(p2.x+tempx-20);
            int tempy=Math.abs(p2.y-p1.y)/2;
            if(p1.y>p2.y)
            {
                zwr.y=(p2.y+tempy-6);
            }
            else
            {
                zwr.y=(tempy+p1.y-6);
            }
        }
        else
        {
            int tempx=(p2.x-p1.x)/2;
            zwr.x=(p1.x+tempx-6);
            int tempy=Math.abs(p2.y-p1.y)/2;
            if(p1.y>p2.y)
                zwr.y=(p2.y+tempy-20);
            else
                zwr.y=(p1.y+tempy-20);

        }
        return zwr;
    }
    private void zerujPunkty()
    {
        punktPocz=new Point();
        punktKon=new Point();
    }
    public BufferedImage zapiszDoPliku()
    {
        BufferedImage bi = new BufferedImage(this.getSize().width, this.getSize().height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bi.createGraphics() ;
        g2d.setBackground(Color.WHITE);
        g2d.clearRect(0, 0, getSize().width, getSize().height);
        this.paint(g2d);  //this == JComponent
        g2d.dispose();
        return bi;
    }
}

