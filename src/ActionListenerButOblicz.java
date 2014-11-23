import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Admin on 2014-11-22.
 */
public class ActionListenerButOblicz implements ActionListener {
    public void actionPerformed(ActionEvent e)
    {
        ArrayList<Wierzcholek>w=Singleton.getInstance().wierzcholki;
        ArrayList<Krawedz>kr=Singleton.getInstance().krawedzie;
        int wierzcholekPoczatkowy=(Integer)Singleton.getInstance().okno.getSpinWiePocz().getValue();
        int wierzcholekKoncowy=(Integer)Singleton.getInstance().okno.getSpinWieKon().getValue();
        if(danePoprawne(w.size(),kr.size(),wierzcholekPoczatkowy,wierzcholekKoncowy))
        {
            Dijkstra graf= new Dijkstra();
            graf.wczytaj(w,kr);
            Singleton.getInstance().droga=graf.obliczDroge(wierzcholekPoczatkowy,wierzcholekKoncowy);
            Singleton.getInstance().okno.getPanel().repaint();
        }
    }

    private boolean danePoprawne(int iloscWierzcholkow,int iloscKrawedzi,int wierzcholekPoczatkowy,int wierzcholekKoncowy) {
        if(iloscWierzcholkow<2) {
            wiadomoscOBledzie("Za malo wierzcholkow!");
            return false;
        }
        if(iloscKrawedzi==0)
        {
            wiadomoscOBledzie("Brak krawedzi!");
            return false;
        }
        if(wierzcholekKoncowy==wierzcholekPoczatkowy)
        {
            wiadomoscOBledzie("Wierzcholek poczatkowy jest taki sam jak koncowy!");
            return false;
        }
        if(wierzcholekKoncowy>=iloscWierzcholkow)
        {
            wiadomoscOBledzie("Wierzcholek koncowy jest niepoprawny!");
            return false;
        }
        if(wierzcholekPoczatkowy>=iloscWierzcholkow)
        {
            wiadomoscOBledzie("Wierzcholek poczatkowy jest niepoprawny!");
            return false;
        }
        return true;
    }

    private void wiadomoscOBledzie(String str)
    {
        JOptionPane.showMessageDialog(null,str,"Błąd",JOptionPane.WARNING_MESSAGE);
    }

}
