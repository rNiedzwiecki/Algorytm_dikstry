import java.awt.*;

/**
 * Created by Admin on 2014-11-15.
 */
public class Krawedz {
    private Wierzcholek poczatkowy;
    private Wierzcholek koncowy;
    private int waga;

    public Krawedz()
    {
        poczatkowy= new Wierzcholek(new Point(0,0));
        koncowy=new Wierzcholek(new Point(0,0));

    }
    public Krawedz(Wierzcholek w1,Wierzcholek w2)
    {
        poczatkowy=w1;
        koncowy=w2;
        waga=1;
    }
    public Wierzcholek getPoczatkowy() {
        return poczatkowy;
    }

    public void setPoczatkowy(Wierzcholek pocz) {
        poczatkowy = pocz;

    }

    public Wierzcholek getKoncowy() {
        return koncowy;
    }

    public void setKoncowy(Wierzcholek Koncowy) {
        this.koncowy = Koncowy;
    }

    public int getWaga() {
        return waga;
    }

    public void setWaga(int waga) {
        this.waga = waga;
    }


    @Override
    public boolean equals(Object ob)
    {
        if(poczatkowy==((Krawedz)ob).poczatkowy && koncowy==((Krawedz)ob).koncowy)
            return true;
        else
            return false;
    }
}
