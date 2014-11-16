import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Admin on 2014-11-16.
 */
public class GUI extends JFrame {
    private JPanel rootPanel;
    private JButton butDoPrzodu;
    private JButton butNowy;
    private JButton butKrawedz;
    private JButton butZapisz;
    private JButton butCofnij;
    private JButton butWierzcholek;
    private JLabel labMysz;
    private JButton butOblicz;
    private boolean CoRysuje;

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public void setRootPanel(JPanel rootPanel) {
        this.rootPanel = rootPanel;
    }

    public JButton getButDoPrzodu() {
        return butDoPrzodu;
    }

    public void setButDoPrzodu(JButton butDoPrzodu) {
        this.butDoPrzodu = butDoPrzodu;
    }

    public JButton getButNowy() {
        return butNowy;
    }

    public void setButNowy(JButton butNowy) {
        this.butNowy = butNowy;
    }

    public JButton getButKrawedz() {
        return butKrawedz;
    }

    public void setButKrawedz(JButton butKrawedz) {
        this.butKrawedz = butKrawedz;
    }

    public JButton getButZapisz() {
        return butZapisz;
    }

    public void setButZapisz(JButton butZapisz) {
        this.butZapisz = butZapisz;
    }

    public JButton getButCofnij() {
        return butCofnij;
    }

    public void setButCofnij(JButton butCofnij) {
        this.butCofnij = butCofnij;
    }

    public JButton getButWierzcholek() {
        return butWierzcholek;
    }

    public void setButWierzcholek(JButton butWierzcholek) {
        this.butWierzcholek = butWierzcholek;
    }

    public JLabel getLabMysz() {
        return labMysz;
    }

    public void setLabMysz(JLabel labMysz) {
        this.labMysz = labMysz;
    }

    public JButton getButOblicz() {
        return butOblicz;
    }

    public void setButOblicz(JButton butOblicz) {
        this.butOblicz = butOblicz;
    }

    public boolean isCoRysuje() {
        return CoRysuje;
    }

    public void setCoRysuje(boolean coRysuje) {
        CoRysuje = coRysuje;
    }

    public GUI()
    {
        super("Algorytm Dijkstry");
        setContentPane(rootPanel);
        setSize(800,600);
        CoRysuje=true;
        butKrawedz.addActionListener(new ActionListenerButKrawedz());
        this.setVisible(true);
        MyPanel panel = new MyPanel();
        try {
            getContentPane().add(panel);
        }
        catch(NullPointerException e)
        {
            System.out.println("NullPointer");
        }
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    public static void main(String[] args)
    {
        GUI temp = new GUI();
        Singleton.getInstance().okno=temp;
    }
}
