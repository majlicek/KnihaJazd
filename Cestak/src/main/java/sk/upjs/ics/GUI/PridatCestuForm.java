package GUI;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
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
 * PridatCestuForm Beta verzia
 *
 * @author Matej Perejda
 */
public class PridatCestuForm extends JFrame {

    private static final Component CENTER_SCREEN = null;

    // Labely    
    private JLabel lblDatum = new JLabel("Dátum:");
    private JLabel lblCielCesty = new JLabel("Cieľ cesty:");
    private JLabel lblOdkial = new JLabel("Odkiaľ:");
    private JLabel lblKam = new JLabel("Kam:");
    private JLabel lblOdchod = new JLabel("Odchod (hod:min):");
    private JLabel lblPrichod = new JLabel("Príchod (hod:min):");
    private JLabel lblKilometre = new JLabel("Kilometre:");
    private JLabel lblPociatokKm = new JLabel("Počiatočný stav počítadla:");
    private JLabel lblNajazdene = new JLabel("Najazdené (km):");
    private JLabel lblDoplnenieHmot = new JLabel("Doplnenie hmôt:");
    private JLabel lblPHM = new JLabel("PHM (litre):");
    private JLabel lblUcel = new JLabel("Účel cesty:");
    private JLabel lblPoznamka = new JLabel("Poznámka:");

    //Textove polia
    private JTextField txtDatum = new JTextField();
    private JTextField txtOdkial = new JTextField();
    private JTextField txtKam = new JTextField();
    private JTextField txtPociatokKm = new JTextField();
    private JTextField txtNajazdene = new JTextField();
    private JTextField txtPHM = new JTextField();
    private JTextField txtPoznamka = new JTextField();

    // Combobox
    private JComboBox comboOdchodHodina = new JComboBox();
    private JComboBox comboOdchodMinuta = new JComboBox();
    private JComboBox comboPrichodHodina = new JComboBox();
    private JComboBox comboPrichodMinuta = new JComboBox();

    // Tlačidlá
    private JButton btnUlozit = new JButton("Uložiť");
    private JButton btnZrusit = new JButton("Zrušiť");

    public PridatCestuForm() {
        setLayout(new MigLayout("", "[fill][fill, grow][fill, grow][fill,grow][fill,grow]", "[][][][][][][][][][][nogrid]"));

        nastavDatumGUI();
        nastavCielCestyGUI();

        // Kilometre
        add(lblKilometre, "wrap");
        setBold(lblKilometre);
        add(lblPociatokKm);
        add(txtPociatokKm);
        add(lblNajazdene);
        add(txtNajazdene, "wrap, span 2");

        // Doplnenie hmôt
        add(lblDoplnenieHmot, "wrap");
        setBold(lblDoplnenieHmot);
        add(lblPHM);
        add(txtPHM, "wrap");

        // Účel cesty
        add(lblUcel, "wrap");
        setBold(lblUcel);
        add(lblPoznamka);
        add(txtPoznamka, "wrap, span 4");
        txtPoznamka.setToolTipText("Poznámka k jazde");

        /* ******************** AKCIE ************************ */
        add(btnUlozit, "tag ok");
        // Akcia pre stlačenie tlačidla uložiť
        btnUlozit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ulozit
                // btnUlozitActionPerformed(e);
                System.out.println("Ukladam...");
            }
        });

        add(btnZrusit, "tag cancel");
        // Akcia pre stlačenie tlačidla zrušiť.
        btnZrusit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        /* ******************** AKCIE ************************ */

        // Nastavenia
        setPreferredSize(new Dimension(500, 285));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();

    }

    // Nastaví dátum jazdy pre GUI.
    private void nastavDatumGUI() {
        add(lblDatum);
        add(txtDatum, "wrap");
        txtDatum.setText("DD.MM.RRRR");
    }

    // Nastaví cieľ cesty pre GUI
    private void nastavCielCestyGUI() {
        add(lblCielCesty, "wrap");
        setBold(lblCielCesty);
        add(lblOdkial);
        add(txtOdkial);
        add(lblOdchod);
        add(comboOdchodHodina);
        comboOdchodHodina.setToolTipText("Hodina odchodu");
        add(comboOdchodMinuta, "wrap");
        comboOdchodMinuta.setToolTipText("Minúta odchodu");
        generujMinutyAHodiny();
        add(lblKam);
        add(txtKam);
        add(lblPrichod);
        add(comboPrichodHodina);
        comboPrichodHodina.setToolTipText("Hodina príchodu");
        add(comboPrichodMinuta, "wrap");
        comboPrichodMinuta.setToolTipText("Minúta príchodu");
    }

    // Nastaví label na BOLD
    private void setBold(JLabel lbl) {
        Font f = lbl.getFont();
        lbl.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
    }

    // Generuje hodiny a minúty do comboboxu v správnom formáte 00:00
    private void generujMinutyAHodiny() {
        // Hodiny
        for (int i = 0; i < 24; i++) {
            StringBuilder sb = new StringBuilder();
            String cislo = "0";
            if (i >= 10) {
                cislo = Integer.toString(i);
                comboOdchodHodina.addItem(cislo);
                comboPrichodHodina.addItem(cislo);
            } else {
                sb.append(Integer.toString(i));
                cislo += sb.toString();
                comboOdchodHodina.addItem(cislo);
                comboPrichodHodina.addItem(cislo);
            }

        }
        comboOdchodHodina.setSelectedItem(null);
        comboPrichodHodina.setSelectedItem(null);

        // Minúty
        for (int i = 0; i < 60; i++) {
            StringBuilder sb = new StringBuilder();
            String cislo = "0";
            if (i >= 10) {
                cislo = Integer.toString(i);
                comboOdchodMinuta.addItem(cislo);
                comboPrichodMinuta.addItem(cislo);
            } else {
                sb.append(Integer.toString(i));
                cislo += sb.toString();
                comboOdchodMinuta.addItem(cislo);
                comboPrichodMinuta.addItem(cislo);
            }
        }
        comboOdchodMinuta.setSelectedItem(null);
        comboPrichodMinuta.setSelectedItem(null);
    }

    // Main - PridatCestuForm
    public static void main(String arg[]) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new WindowsLookAndFeel());

        PridatCestuForm pridatCestuForm = new PridatCestuForm();
        pridatCestuForm.setVisible(true);
        pridatCestuForm.setTitle("Kniha jázd - pridanie nového záznamu");
        pridatCestuForm.setLocationRelativeTo(CENTER_SCREEN);
    }

}
