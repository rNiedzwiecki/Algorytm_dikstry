import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Admin on 2014-11-16.
 */
public class ActionListenerButKrawedz implements ActionListener {
    public void actionPerformed(ActionEvent e)
    {
        Singleton.getInstance().okno.setCoRysuje(false);
        Singleton.getInstance().okno.getRbDodajKrawedz().setSelected(true);
        Singleton.getInstance().okno.getRbdodajWierzcholek().setSelected(false);
    }

}
