import javax.swing.*;

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
    private JLabel labWagaWierzcholka;
    private JRadioButton rbDodajKrawedz;
    private JRadioButton rbdodajWierzcholek;
    private JSpinner spinner1;
    private JLabel labWierzcholekPocz;
    private JLabel labWierzcholekKoncowy;
    private JSpinner spinner2;
    private JSpinner spinner3;
    private MyPanel panel;
    private boolean CoRysuje=true;

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public JButton getButDoPrzodu() {
        return butDoPrzodu;
    }



    public JButton getButNowy() {
        return butNowy;
    }



    public JButton getButKrawedz() {
        return butKrawedz;
    }



    public JButton getButZapisz() {
        return butZapisz;
    }



    public JButton getButCofnij() {
        return butCofnij;
    }



    public JButton getButWierzcholek() {
        return butWierzcholek;
    }

    public JLabel getLabMysz() {
        return labMysz;
    }



    public JButton getButOblicz() {
        return butOblicz;
    }


    public boolean isCoRysuje() {
        return CoRysuje;
    }

    public void setCoRysuje(boolean coRysuje) {
        CoRysuje = coRysuje;
    }

    public JLabel getLabWagaWierzcholka() {
        return labWagaWierzcholka;
    }

    public JRadioButton getRbDodajKrawedz() {
        return rbDodajKrawedz;
    }


    public JRadioButton getRbdodajWierzcholek() {
        return rbdodajWierzcholek;
    }


    public MyPanel getPanel() {
        return panel;
    }


    public GUI()
    {
        super("Algorytm Dijkstry");
        setContentPane(rootPanel);
        setSize(1024, 768);
        butKrawedz.addActionListener(new ActionListenerButKrawedz());
        butWierzcholek.addActionListener(new ActionListenerButWierzcholek());
        this.setVisible(true);
        panel = new MyPanel();
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
