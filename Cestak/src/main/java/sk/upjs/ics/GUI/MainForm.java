package sk.upjs.ics.GUI;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import net.miginfocom.swing.MigLayout;
import sk.upjs.ics.cestak.Auto;
import sk.upjs.ics.cestak.AutoDAO;
import sk.upjs.ics.cestak.AutoListCellRenderer;
import sk.upjs.ics.cestak.DaoFactory;
import sk.upjs.ics.cestak.Login;

/**
 * MainForm
 *
 * @author Matej Perejda
 */
public class MainForm extends JFrame {

    private static final Component CENTER_SCREEN = null;

    // Tlačidlá
    private JButton btnNovaCesta = new JButton("Nová cesta");
    private JButton btnVymazatCestu = new JButton("Vymazať cestu");
    private JButton btnPridatAuto = new JButton("Pridať auto");
    private JButton btnUpravitAuto = new JButton("Upraviť auto");
    private JButton btnVymazatAuto = new JButton("Vymazať auto");
    private JButton btnUpravitUzivatela = new JButton("Upraviť profil");
    private JButton btnKoniec = new JButton("Koniec");

    // Comboboxy
    private JComboBox comboAuta = new JComboBox();

    // Labely
    private JLabel lblJazdy = new JLabel("Aktuálny zoznam jázd:");

    // Tabulky
    private JTable tabJazdy;

    // JPanel
    private JPanel panPanel = new JPanel();
    private JPanel panPanel2 = new JPanel();
    private JPanel panPanel3 = new JPanel();

    private Login login;
    private Auto selectedAuto;

    private AutoDAO autoDao = DaoFactory.INSTANCE.autoDao();
    private ListCellRenderer autoListCellRenderer = new AutoListCellRenderer();
    
    public MainForm(Login login) {
        this();
        this.login = login;
        obnovAuta();
    }

    public MainForm() {
        setLayout(new MigLayout("", "[fill, grow][fill][fill][fill][fill][fill]", "[][][][][][][nogrid]"));
        // Vrchny panel s tlacidlami
        add(btnNovaCesta);
        add(btnVymazatCestu);
        add(btnPridatAuto);
        add(btnUpravitAuto);
        add(btnVymazatAuto);
        add(btnUpravitUzivatela, "wrap");

        // Prázdny panel 1
        panPanel.setPreferredSize(new Dimension(700, 30));
        add(panPanel, "wrap");

        // Label a combo
        add(lblJazdy);
        add(comboAuta, "wrap, span 5");

        // Prazdny panel 2
        panPanel2.setPreferredSize(new Dimension(700, 10));
        add(panPanel2, "wrap");

        // Nastavnie JTable
        setJTable();

        // Prázdny panel 3
        panPanel3.setPreferredSize(new Dimension(700, 10));
        add(panPanel3, "wrap");

        /* ******************** AKCIE ************************ */
        // Akcia pre pridanie novej cesty.
        btnNovaCesta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // otvorí PridatCestuForm
            }
        });

        // Akcia pre vymazanie vyznacenej cesty.
        btnVymazatCestu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // otvorí upozornenie, či naozaj chceme vymazať túto cestu.
            }
        });

        // Akcia pre pridanie noveho auta.
        btnPridatAuto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // otvorí PridatAutoForm
                PridatAutoForm pridatAutoForm = null;
                try {
                    pridatAutoForm = new PridatAutoForm(login);
                } catch (HeadlessException ex) {
                    System.err.println(ex);
                } catch (FileNotFoundException ex) {
                    System.err.println("Nenacitany subor.");
                }
                pridatAutoForm.setVisible(true); 
                obnovAuta();
            }
        });
        
        comboAuta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedAuto = (Auto) comboAuta.getSelectedItem();
                System.out.println(selectedAuto.getSpz());
            }
        });

        // Akcia pre pridanie novej cesty.
        btnUpravitAuto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // otvorí PridatCestuForm s vydolovanými dátami z databázy
            }
        });

        // Akcia pre pridanie novej cesty.
        btnVymazatAuto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // otvorí upozornenie, či naozaj chceme vymazať vyznačené auto
            }
        });

        // Akcia pre pridanie novej cesty.
        btnUpravitUzivatela.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // otvorí RegistracnyForm s vydolovanými dátami z databázy
            }
        });

        // Tlacidlo koniec
        add(btnKoniec, "tag cancel, span 1");
        // Akcia pre stlačenie tlačidla zrušiť.
        btnKoniec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        /* ******************** AKCIE ************************ */

        // Nastavenia
        setPreferredSize(new Dimension(700, 500));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }

    // Nastavenie tabuľky
    public void setJTable() {
        // Stĺpce upraviť podľa potreby.
        String[] columnNames = {"EČV", "Značka", "Model", "Farba"};

        // Testovacie data. 
        // Dolovať dáta z databázy !!!
        Object[][] testData = {
            {"BL-TR235DS", "BMW", "X5", "čierna"},
            {"VT-TR235DS", "Mercedes", "S2", "biela"},
            {"KE-TR235DS", "Audi", "R8", "červená"},
            {"BL-TR235DS", "BMW", "X5", "čierna"},
            {"VT-TR235DS", "Mercedes", "S2", "biela"},
            {"KE-TR235DS", "Audi", "R8", "červená"},
            {"BL-TR235DS", "BMW", "X5", "čierna"},
            {"VT-TR235DS", "Mercedes", "S2", "biela"},
            {"KE-TR235DS", "Audi", "R8", "červená"},
            {"BL-TR235DS", "BMW", "X5", "čierna"},
            {"VT-TR235DS", "Mercedes", "S2", "biela"},
            {"KE-TR235DS", "Audi", "R8", "červená"},
            {"BL-TR235DS", "BMW", "X5", "čierna"},
            {"VT-TR235DS", "Mercedes", "S2", "biela"},
            {"KE-TR235DS", "Audi", "R8", "červená"},
            {"BL-TR235DS", "BMW", "X5", "čierna"},
            {"VT-TR235DS", "Mercedes", "S2", "biela"},
            {"KE-TR235DS", "Audi", "R8", "červená"},
            {"BL-TR235DS", "BMW", "X5", "čierna"},
            {"VT-TR235DS", "Mercedes", "S2", "biela"},
            {"KE-TR235DS", "Audi", "R8", "červená"},};

        tabJazdy = new JTable(testData, columnNames);
        tabJazdy.setPreferredScrollableViewportSize(new Dimension(700, 320));
        tabJazdy.setFillsViewportHeight(true);
        add(tabJazdy, "wrap, span 6");

        JScrollPane scrollPane = new JScrollPane(tabJazdy);
        add(scrollPane, "wrap, span 6");
    }

    // Main - MainForm
    public static void main(String args[]) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new WindowsLookAndFeel());

        MainForm mainForm = new MainForm();
        mainForm.setVisible(true);
        mainForm.setTitle("Kniha jázd - hlavné okno");
        mainForm.setLocationRelativeTo(CENTER_SCREEN);
    }

    private ComboBoxModel getAutaModel() {
        List<Auto> auto = autoDao.zoznamPodlaPouzivatela(login);
        System.out.println(login.getId());
        return new DefaultComboBoxModel(auto.toArray());
    }

    public void obnovAuta() {
        comboAuta.setModel(getAutaModel());
        comboAuta.setRenderer(autoListCellRenderer);
    }
}
