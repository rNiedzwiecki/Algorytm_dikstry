import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Admin on 2014-11-17.
 */
public class ActionListenerButWierzcholek implements ActionListener {
    public void actionPerformed(ActionEvent e)
    {
        Singleton.getInstance().okno.setCoRysuje(true);
        Singleton.getInstance().okno.getRbDodajKrawedz().setSelected(false);
        Singleton.getInstance().okno.getRbdodajWierzcholek().setSelected(true);
    }
}
