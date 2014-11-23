import java.util.ArrayList;

/**
 * Created by Admin on 2014-11-15.
 */
public class Graf {
    protected ArrayList<ArrayList<Integer>> graf;
    static final int INF=1000;
    public Graf()
    {
        graf= new ArrayList<ArrayList<Integer>>();
    }
    ArrayList<ArrayList<Integer>> getGraf()
    {
        return graf;
    }
    public void wczytaj (ArrayList<Wierzcholek> w,ArrayList<Krawedz> kr)
    {
        int iloscWierzcholkow = w.size();
        int iloscKrawedzi=kr.size();
        stworzTablice(iloscWierzcholkow);
        for (int i=0;i<iloscKrawedzi;i++)
        {
            Krawedz aktualnaKrawedz=kr.get(i);
            dodajKrawedzDoGrafu(aktualnaKrawedz);
        }
    }
    protected void stworzTablice(int ile)
    {
        for(int i=0;i<ile;i++)
        {
            graf.add(new ArrayList<Integer>());
            for(int j=0;j<ile;j++)
                if (i == j)
                    graf.get(i).add(INF);
                else
                graf.get(i).add(0);
        }
    }
    private void dodajKrawedzDoGrafu(int pocz,int kon,int waga)
    {
        graf.get(pocz).set(kon,waga);
        graf.get(kon).set(pocz, waga);
    }
    private void dodajKrawedzDoGrafu(Krawedz kr)
    {
        int numerWierzcholkaPoczatkowego=kr.getPoczatkowy().getNumer();
        int numerWierzcholkaKoncowego=kr.getKoncowy().getNumer();
        int wagaKrawedzi=kr.getWaga();
        dodajKrawedzDoGrafu(numerWierzcholkaPoczatkowego,numerWierzcholkaKoncowego,wagaKrawedzi);
    }
    @Override
    public String toString()
    {
        String tablica = new String("");
        for (int i=0;i<graf.size();i++)
        {
            tablica+=graf.get(i).toString();
            tablica+='\n';
        }
        return tablica;
    }
}
