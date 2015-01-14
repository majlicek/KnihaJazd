package sk.upjs.ics.GUI;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import net.miginfocom.swing.MigLayout;
import sk.upjs.ics.cestak.DaoFactory;
import sk.upjs.ics.cestak.Login;
import sk.upjs.ics.cestak.Pouzivatel;
import sk.upjs.ics.cestak.PrihlasenieDAO;

/**
 * Prihlasovacia obrazovka (Login screen) Beta verzia
 *
 * @author Matej Perejda
 */
public class PrihlasovaciForm extends JFrame {

    private static final Component CENTER_SCREEN = null;

    // Tlačidlá
    private JButton btnPrihlasit = new JButton("Prihlásiť");
    private JButton btnRegistrovat = new JButton("Registrovať");

    // Textové polia 
    private JTextField txtLogin = new JTextField();
    private JPasswordField txtHeslo = new JPasswordField();

    // Labely
    private JLabel lblLogin = new JLabel("Login:");
    private JLabel lblHeslo = new JLabel("Heslo:");
    private JLabel lblTitulka = new JLabel("Vitajte v knihe jázd !", SwingConstants.CENTER);
    private JLabel lblWarning = new JLabel("Zlé meno alebo heslo!", SwingConstants.RIGHT);

    private PrihlasenieDAO prihlasenieDao = DaoFactory.INSTANCE.prihlasenieDao();
    private Login login;

    // Konštruktor
    public PrihlasovaciForm() throws HeadlessException {
        // NASTAVIŤ LAYOUT !!!
        setLayout(new MigLayout("", "[fill, grow][fill, grow]", "[][][][]"));

        add(lblTitulka, "wrap, span 2"); // Namiesto titulky nejaké logo, resp. uvítanie.
        add(lblLogin);
        add(txtLogin, "wrap");
        add(lblHeslo);
        add(txtHeslo, "wrap");
        add(lblWarning, "wrap, span 2");
        lblWarning.setVisible(false); // Nastaviť na TRUE, ak zadané zlé meno alebo heslo.
        lblWarning.setForeground(Color.RED);

        /* ******************** AKCIE ************************ */
        // Tlačidlo "Prihlásiť"
        add(btnPrihlasit);
        getRootPane().setDefaultButton(btnPrihlasit);
        // Akcia pre stlačenie tlačídla prihlásiť.
        btnPrihlasit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prechod do MainWindow
                btnPrihlasitActionPerformed(e);
                System.out.println("Prihlasujem...");
            }
        });

        // Tlačídlo "Registrovať"
        add(btnRegistrovat);
        // Akcia pre stlačenie tlačídla registrovať.
        btnRegistrovat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnRegistrovatActionPerformed(e);
                System.out.println("Registrujem...");
            }
        });
        /* ******************** AKCIE ************************ */

        setPreferredSize(new Dimension(350, 150));
        setResizable(false);
               
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }

    // Akcia pre prihlásenie.
    private void btnPrihlasitActionPerformed(ActionEvent event) {
        login = new Login();
        login.setLogin(txtLogin.getText());
        login.setHeslo(String.valueOf(txtHeslo.getPassword()));
        login = prihlasenieDao.verifyLogin(login);
        if (login != null) {
            dispose();
            System.out.println(login.getId());
            MainForm mainForm = new MainForm(login);                    
            mainForm.setTitle("Kniha jázd - hlavné okno. Používateľ: " + login.getLogin().toString().toUpperCase());
            mainForm.setLocationRelativeTo(CENTER_SCREEN);
            mainForm.setVisible(true);
        } else {
            lblWarning.setVisible(true);
        }
    }

    // Akcia pre registrovanie.
    private void btnRegistrovatActionPerformed(ActionEvent event) {
        RegistracnyForm registrujPouzivatela;
        registrujPouzivatela = new RegistracnyForm();
        registrujPouzivatela.setTitle("Kniha jázd - registrácia užívateľa");
        registrujPouzivatela.setLocationRelativeTo(CENTER_SCREEN);
        registrujPouzivatela.setVisible(true);
    }

    // Main - PrihlasovaciForm
    public static void main(String args[]) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new WindowsLookAndFeel());

        PrihlasovaciForm prihlasovaciForm = new PrihlasovaciForm();
        prihlasovaciForm.setVisible(true);
        prihlasovaciForm.setTitle("Kniha jázd - prihlásenie");
        prihlasovaciForm.setLocationRelativeTo(CENTER_SCREEN);
    }    
}
