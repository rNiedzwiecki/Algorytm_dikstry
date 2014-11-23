import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Admin on 2014-11-19.
 */
public class ActionListenerButCofnij implements ActionListener {

    public void actionPerformed(ActionEvent e)
    {
        Singleton.getInstance().okno.getPanel().cofnijKopie();
    }
}
