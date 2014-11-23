import java.util.ArrayList;

/**
 * Created by Admin on 2014-11-22.
 */
public class Dijkstra extends Graf {
    Boolean[] odwiedzeni;
    Integer[] odleglosci;
    Integer[] przodkowie;
    static final int INF=1000;
    int iloscWierzcholkow;
    public Dijkstra()
    {
        super();
    }
    public void wczytaj(ArrayList<Wierzcholek> w,ArrayList<Krawedz> kr)
    {
        iloscWierzcholkow=w.size();
        stworzTablice();
        super.wczytaj(w,kr);
    }
    protected void stworzTablice() {
        odleglosci = new Integer[iloscWierzcholkow];
        przodkowie = new Integer[iloscWierzcholkow];
        odwiedzeni = new Boolean[iloscWierzcholkow];
    }
    private void zerowanie()
    {
        for(int i=0;i<iloscWierzcholkow;i++)
        {
            odleglosci[i]=INF;
            przodkowie[i]=-1;
            odwiedzeni[i]=false;
        }
    }
    private int znajdzNajblizszyWierzcholek()
    {
        int minOdleglosc = INF;
        int najblizszyWierzcholek=0;
        for (int i = 0; i < iloscWierzcholkow; i++)
        {
            //sprawdzam czy wierzcholek nie byl odwiedzony oraz czy jego odleglosc jest mniejsza od aktualnej odleglosci
            if ((!odwiedzeni[i]) && (minOdleglosc >= odleglosci[i]))
            {
                //jesli tak to aktualizuje
                najblizszyWierzcholek = i;
                minOdleglosc = odleglosci[i];
            }
        }
        return najblizszyWierzcholek;
    }
    public ArrayList<Integer> obliczDroge(int wierzcholekPoczatkowy,int wierzcholekKoncowy)
    {
        //czyszcze tablice
        zerowanie();
        odleglosci[wierzcholekPoczatkowy]=0;
        int najblizszyNieoznaczonyWierzcholek;
        //dla kazdego wierzcholka
        for (int j = 0; j <iloscWierzcholkow; j++)
        {
            //znajduje najblizszego nieodwiedzonego sasaiada
            najblizszyNieoznaczonyWierzcholek = znajdzNajblizszyWierzcholek();
            //i oznaczam go jako odwiedzonego
            odwiedzeni[najblizszyNieoznaczonyWierzcholek] = true;
            //dla kazdych innych nieodwiedzonych wierzcholkow obliczam odleglosci od niego
            for (int i = 0; i < iloscWierzcholkow; i++)
            {
                //jezeli wierzcholek nie jest odwiedzony i to nie jest najblizszyNieodwiedzonyWierzcholek
                if (!odwiedzeni[i] && graf.get(najblizszyNieoznaczonyWierzcholek).get(i)>0)
                {
                    //jezeli odleglosc jest mniejsza
                    if (odleglosci[i]>odleglosci[najblizszyNieoznaczonyWierzcholek] + graf.get(najblizszyNieoznaczonyWierzcholek).get(i))
                    {
                        //to aktualizuje
                        odleglosci[i] = odleglosci[najblizszyNieoznaczonyWierzcholek] +graf.get(najblizszyNieoznaczonyWierzcholek).get(i);
                        przodkowie[i] = najblizszyNieoznaczonyWierzcholek;
                    }
                }
            }
        }
        return zwrocNajkrotszaDroge(wierzcholekPoczatkowy,wierzcholekKoncowy);
    }
    public ArrayList<Integer> zwrocNajkrotszaDroge(int wierzcholekPoczatkowy,int wierzcholekKoncowy)
    {
        ArrayList<Integer> droga = new ArrayList<Integer>();
        int aktualnyWierzcholek=wierzcholekKoncowy;
        while(aktualnyWierzcholek!=-1)
        {
            droga.add(aktualnyWierzcholek);
            aktualnyWierzcholek=przodkowie[aktualnyWierzcholek];
        }

        return droga;
    }
}
