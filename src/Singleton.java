import java.util.ArrayList;

/**
 * Created by Admin on 2014-11-15.
 */
public final class Singleton {
    private final static Singleton ourInstance = new Singleton();
    public GUI okno;
    public ArrayList<Wierzcholek> wierzcholki= new ArrayList<Wierzcholek>();
    public ArrayList<Krawedz> krawedzie = new ArrayList<Krawedz>();
    public ArrayList<Integer> droga=new ArrayList<Integer>();
    public static Singleton getInstance() {
        return ourInstance;
    }

    //żeby uniknąć automatycznego tworzenia domyślnego, publicznego, bezargumentowego konstruktora
    private Singleton() {
    }
}