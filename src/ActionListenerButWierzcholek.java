import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Admin on 2014-11-17.
 */
public class ActionListenerButWierzcholek implements ActionListener {
    public void actionPerformed(ActionEvent e)
    {
        Singleton.getInstance().okno.setCoRysuje(true);
        Singleton.getInstance().okno.getRbdodajWierzcholek().setSelected(true);
        Singleton.getInstance().okno.getLabStan().setText("Stan: Rysuje wierzchołki");
    }
}
