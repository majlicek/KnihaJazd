package GUI;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import net.miginfocom.swing.MigLayout;

/**
 * Registračné okno (Register screen)
 *
 * @author Matej Perejda
 */
public class RegistracnyForm extends JFrame {

    private static final Component CENTER_SCREEN = null;

    // Tlačidlá
    private JButton btnRegistrovat = new JButton("Registrovať");
    private JButton btnZrusit = new JButton("Zrušiť");

    // Labely
    private JLabel lblText = new JLabel("Registrácia nového užívateľa.");
    private JLabel lblLogin = new JLabel("Login:");
    private JLabel lblHeslo = new JLabel("Heslo:");
    private JLabel lblHeslo2 = new JLabel("Heslo:"); // Zopakujte heslo hint
    private JLabel lblMeno = new JLabel("Meno:");
    private JLabel lblPriezvisko = new JLabel("Priezvisko:");
    private JLabel lblPohlavie = new JLabel("Pohlavie:");
    private JLabel lblDatumNarodenia = new JLabel("Dátum narodenia:");
    private JLabel lblAdresa = new JLabel("Adresa:");
    private JLabel lblEmail = new JLabel("E-mail:");
    private JLabel lblTel = new JLabel("Tel.:");

    // Textove polia
    private JTextField txtLogin = new JTextField();
    private JTextField txtHeslo = new JTextField();
    private JTextField txtHeslo2 = new JTextField();
    private JTextField txtMeno = new JTextField();
    private JTextField txtPriezvisko = new JTextField();
    private JTextField txtAdresa = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JTextField txtTel = new JTextField();

    // Combobox
    private JComboBox comboPohlavie = new JComboBox();
    private JComboBox comboDen = new JComboBox();
    private JComboBox comboMesiac = new JComboBox();
    private JComboBox comboRok = new JComboBox();

    // Konštruktor
    public RegistracnyForm() throws HeadlessException {
        setLayout(new MigLayout("", "[fill, grow][fill, grow][fill,grow]")); // NASTAVIŤ LAYOUT !!!

        // Úvodný text k prihlasovaniu
        add(lblText, "wrap");
        nastavPrihlasovacieUdajeGUI();
        nastavOsobneUdajeGUI();
        nastavDatumNarodeniaGUI();
        nastavKontaktInfoGUI();

        // Tlačidlo "Registrovať"
        add(btnRegistrovat);
        // Akcia pre stlačenie tlačidla registrovať.
        btnRegistrovat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Zaregistrovať
                // btnRegistrovatActionPerformed(e);
                System.out.println("Registrujem...");
            }
        });

        // Tlačídlo "Zrušiť"
        add(btnZrusit);
        // Akcia pre stlačenie tlačidla zrušiť.
        btnZrusit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        });

        setPreferredSize(new Dimension(500, 500));
        setResizable(false); // ZMENIŤ NA FALSE !!!
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }

    // Nastavenie pre kontaktné údaje.
    private void nastavKontaktInfoGUI() {
        add(lblAdresa);
        add(txtAdresa, "wrap");
        txtAdresa.setToolTipText("ulica/PSČ/mesto/krajina");
        add(lblEmail);
        add(txtEmail, "wrap");
        txtEmail.setToolTipText("mailto@server.com");
        add(lblTel);
        add(txtTel, "wrap");
    }

    // Nastavenie pre dátum narodenia.
    private void nastavDatumNarodeniaGUI() {
        add(lblDatumNarodenia);
        add(comboDen);
        generujDen();
        comboDen.setSelectedItem(null);
        add(comboMesiac);
        generujMesiac();
        comboMesiac.setSelectedItem(null);
        add(comboRok, "wrap");
        generujRok();
        comboRok.setSelectedItem(null);
    }

    // Nastavenie pre osobne údaje.
    private void nastavOsobneUdajeGUI() {
        add(lblMeno);
        add(txtMeno, "wrap");
        add(lblPriezvisko);
        add(txtPriezvisko, "wrap");
        add(lblPohlavie);
        add(comboPohlavie, "wrap");
        comboPohlavie.addItem("muž");
        comboPohlavie.addItem("žena");
    }

    // Nastavenie pre login a heslo.
    private void nastavPrihlasovacieUdajeGUI() {
        add(lblLogin);
        add(txtLogin, "wrap");
        txtLogin.setToolTipText("Prihlasovacie meno");
        add(lblHeslo);
        add(txtHeslo, "wrap");
        txtHeslo.setToolTipText("Zadajte heslo");
        add(lblHeslo2);
        add(txtHeslo2, "wrap");
        txtHeslo2.setToolTipText("Zopakujte heslo");
    }

    // Akcia pre registráciu.
    private void btnRegistrovatActionPerformed(ActionEvent e) {
        // kód
    }

    // Generuje dni pre dátum narodenia.
    private void generujDen() {
        for (int i = 1; i <= 31; i++) {
            comboDen.addItem(new Integer(i));
        }
    }

    // Generuje mesiac pre dátum narodenia.
    private void generujMesiac() {
        for (int i = 1; i <= 12; i++) {
            comboMesiac.addItem(new Integer(i));
        }
    }

    // Generuje rok pre dátum narodenia.
    private void generujRok() {
        for (int i = 1900; i <= 2050; i++) {
            comboRok.addItem(new Integer(i));
        }
    }

    // Main - RegistracnyForm 
    public static void main(String args[]) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new WindowsLookAndFeel());

        RegistracnyForm registracnyForm = new RegistracnyForm();
        registracnyForm.setVisible(true);
        registracnyForm.setTitle("Kniha jázd - registrácia užívateľa");
        registracnyForm.setLocationRelativeTo(CENTER_SCREEN);
    }
}
