import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Admin on 2014-11-23.
 */
public class ActionListenerButZapisz implements ActionListener {
    public void actionPerformed(ActionEvent e)
    {
        BufferedImage bi= Singleton.getInstance().okno.getPanel().zapiszDoPliku();
        try{
            ImageIO.write(bi, "jpg", new File("test.jpg"));}catch (Exception ex) {}
    }

}
