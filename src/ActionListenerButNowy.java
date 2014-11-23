import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Admin on 2014-11-18.
 */
public class ActionListenerButNowy implements ActionListener {
    public void actionPerformed(ActionEvent e)
    {
       Singleton.getInstance().okno.getPanel().wyczysc();
    }
}
