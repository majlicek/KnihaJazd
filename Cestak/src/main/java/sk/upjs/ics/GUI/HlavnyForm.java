package sk.upjs.ics.GUI;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import net.miginfocom.swing.MigLayout;
import sk.upjs.ics.cestak.Login;

/**
 *
 * @author Majlo
 */
public class HlavnyForm extends JFrame {

    private static final Component CENTER_SCREEN = null;

    //labely
    private JLabel lblTitulok = new JLabel("ZoznamCiest:");

    //Buttony
    private JButton btnVytvorCestu = new JButton("Nová Cesta");
    private JButton btnVymazCestu = new JButton("Vymazať cestu");
    private JButton btnVytvorAuto = new JButton("Pridaj auto");
    private JButton btnVymazAuto = new JButton("Odstranit auto");

    //panel
    private JScrollPane panel = new JScrollPane();
   
    private JComboBox cmbVyberAuta = new JComboBox();
    
    private Login login;

    public HlavnyForm(Login login) {
        this();
        this.login = login;
    }

    private HlavnyForm() {
        setLayout(new MigLayout("", "[fill][fill, grow][fill,grow][fill,grow]", "[][][][][][][][][][][nogrid]"));

        tlacidloveMenu();

        setPreferredSize(new Dimension(800, 600));
        pack();
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new WindowsLookAndFeel());

        HlavnyForm hlavnyForm = new HlavnyForm();
        hlavnyForm.setVisible(true);
        hlavnyForm.setTitle("Kniha jázd");
        hlavnyForm.setLocationRelativeTo(CENTER_SCREEN);
    }

    private void tlacidloveMenu() {
        add(btnVytvorCestu, "span 1");
        add(btnVymazCestu);
        add(btnVytvorAuto);
        add(btnVymazAuto, "wrap");
        add(lblTitulok);
        add(cmbVyberAuta, "wrap");
        add(panel, "span 4");
        
    }

}
