import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
    private JSpinner spinWaga;
    private JLabel labWierzcholekPocz;
    private JLabel labWierzcholekKoncowy;
    private JSpinner spinWiePocz;
    private JSpinner spinWieKon;
    private JButton butOblicz1;
    private JLabel labAktKopia;
    private JLabel labStan;
    private MyPanel panel;
    private boolean CoRysuje = true;

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

    public JSpinner getSpinWaga() {
        return spinWaga;
    }

    public JSpinner getSpinWiePocz() {
        return spinWiePocz;
    }

    public JSpinner getSpinWieKon() {
        return spinWieKon;
    }

    public JLabel getLabAktKopia() {
        return labAktKopia;
    }

    public JLabel getLabStan() {
        return labStan;
    }

    public GUI() {
        super("Algorytm Dijkstry");
        ustawieniaOkna();
        ustawKomponenty();
    }

    private void ustawieniaOkna() {
        setContentPane(rootPanel);
        setSize(1024, 610);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            BufferedImage bufIm = ImageIO.read(getClass().getResource("icons/Dragon-Radar-icon.png"));
            setIconImage(bufIm);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }


    private void ustawKomponenty()
    {
        butKrawedz.addActionListener(new ActionListenerButKrawedz());
        butWierzcholek.addActionListener(new ActionListenerButWierzcholek());
        butNowy.addActionListener(new ActionListenerButNowy());
        butDoPrzodu.addActionListener(new ActionListenerButDoPrzodu());
        butCofnij.addActionListener(new ActionListenerButCofnij());
        rbDodajKrawedz.addActionListener(new ActionListenerButKrawedz());
        rbdodajWierzcholek.addActionListener(new ActionListenerButWierzcholek());
        spinWaga.setModel(new SpinnerNumberModel(1, 1, 100, 1));
        spinWieKon.setModel(new SpinnerNumberModel(0,0,100,1));
        spinWiePocz.setModel(new SpinnerNumberModel(0,0,100,1));
        butOblicz.addActionListener(new ActionListenerButOblicz());
        butOblicz1.addActionListener(new ActionListenerButOblicz());
        butZapisz.addActionListener(new ActionListenerButZapisz());
        panel = new MyPanel();
        try {
        getContentPane().add(panel);
        }
        catch(NullPointerException e)
        {
        }

    }
}
