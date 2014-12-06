package sk.upjs.ics.cestak;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import net.miginfocom.swing.MigLayout;

/**
 * Prihlasovacia obrazovka (Login screen)
 *
 * @author Matej Perejda
 */
public class PrihlasovaciForm2 extends JFrame {
    private static final Component CENTER_SCREEN = null;

    // Tlačidlá
    private JButton btnPrihlasit = new JButton("Prihlásiť");
    private JButton btnRegistrovat = new JButton("Registrovať");

    // Textové polia 
    private JTextField txtLogin = new JTextField();
    private JTextField txtHeslo = new JTextField();

    // Labely
    private JLabel lblLogin = new JLabel("Login:");
    private JLabel lblHeslo = new JLabel("Heslo:");
    private JLabel lblTitulka = new JLabel("Kniha jázd");

    // Konštruktor
    public PrihlasovaciForm2() throws HeadlessException {
        // NASTAVIŤ LAYOUT !!!
        setLayout(new MigLayout("", "[fill, grow][fill, grow]", "[][][][]"));

        //add(lblTitulka, "dock center, span 2, wrap");        
        add(lblTitulka, "wrap");
        add(lblLogin);
        add(txtLogin, "wrap");
        add(lblHeslo);
        add(txtHeslo, "wrap");

        // Tlačidlo "Prihlásiť"
            add(btnPrihlasit);
        // Akcia pre stlačenie tlačídla prihlásiť.
        btnPrihlasit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prechod do MainWindow
                // btnPrihlasitActionPerformed(e);
                System.out.println("Prihlasujem...");
            }
        });

        // Tlačídlo "Registrovať"
        add(btnRegistrovat);
        // Akcia pre stlačenie tlačídla registrovať.
        btnRegistrovat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prechod do RegistracnyForm
                // btnRegistrovatActionPerformed(e);
                System.out.println("Registrujem...");
            }
        });

        setPreferredSize(new Dimension(350, 500));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }

    // Akcia pre prihlásenie.
    private void btnPrihlasitActionPerformed(ActionEvent event) {
        // kód
    }

    // Akcia pre registrovanie.
    private void btnRegistrovatActionPerformed(ActionEvent event) {
        // kód
    }

    // Main - PrihlasovaciForm2
    public static void main(String args[]) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new WindowsLookAndFeel());

        PrihlasovaciForm2 prihlasovaciForm = new PrihlasovaciForm2();
        prihlasovaciForm.setVisible(true);
        prihlasovaciForm.setTitle("Kniha jázd - prihlásenie");
        prihlasovaciForm.setLocationRelativeTo(CENTER_SCREEN);
    }
}
