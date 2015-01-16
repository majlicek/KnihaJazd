package sk.upjs.ics.GUI;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import net.miginfocom.swing.MigLayout;
import sk.upjs.ics.cestak.Auto;
import sk.upjs.ics.cestak.AutoDAO;
import sk.upjs.ics.cestak.AutoListCellRenderer;
import sk.upjs.ics.cestak.DaoFactory;
import sk.upjs.ics.cestak.Jazda;
import sk.upjs.ics.cestak.JazdaDAO;
import sk.upjs.ics.cestak.JazdaTableModel;
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
    private JButton btnOdhlasit = new JButton("Odhlásiť");

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
    private JazdaDAO jazdaDao = DaoFactory.INSTANCE.jazdaDao();
    private ListCellRenderer autoListCellRenderer = new AutoListCellRenderer();
    private JazdaTableModel jazdaTableModel = new JazdaTableModel();

    // ***********************************************************************
    public MainForm(Login login) {
        this();
        this.login = login;
        obnovAuta();
        obnovJazdy();
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
        System.out.println("Nastavujem table model 1. KRAT !\n"); // MateJ  
        setJTable();

        // Prázdny panel 3
        panPanel3.setPreferredSize(new Dimension(700, 10));
        add(panPanel3, "wrap");

        btnVymazatCestu.setEnabled(false);
        btnUpravitUzivatela.setEnabled(false);
        add(btnOdhlasit, "tag cancel, span 1");
        // ////////////////////////////////////////////////////////////////////

        // Tlačídlo NOVÁ CESTA. 
        btnNovaCesta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnPridatJazduActionPerformed(e);
            }
        });

        // Tlačidlo VYMAZAŤ CESTU.
        btnVymazatCestu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int vybranyRiadok = tabJazdy.getSelectedRow();
                int vybratyIndexVModeli = tabJazdy.convertRowIndexToModel(vybranyRiadok);
                Jazda jazda = jazdaTableModel.dajPodlaCislaRiadku(vybratyIndexVModeli);
                jazdaDao.vymazJazda(jazda);
                obnovJazdy();
            }
        });

        // Tlačidlo PRIDAŤ AUTO.
        btnPridatAuto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnPridatAutoActionPerformed(e);
            }
        });

        // Combobox pre autá.
        comboAuta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedAuto = (Auto) comboAuta.getSelectedItem();
                obnovJazdy();
            }
        });

        // Tlačidlo UPRAVIŤ AUTO.
        btnUpravitAuto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    btnUpravitAutoActionPerformed(e);
                } catch (HeadlessException ex) {
                    System.out.println(ex);
                } catch (FileNotFoundException ex) {
                    System.out.println("Súbor sa nenašiel.");
                }
            }
        });

        // Tlačídlo VYMAZAŤ AUTO.
        btnVymazatAuto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autoDao.vymazAuto(selectedAuto);
                obnovAuta();
                obnovJazdy();
            }
        });

        // Tlačidlo UPRAVIŤ PROFIL.
        btnUpravitUzivatela.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // otvorí RegistracnyForm s vydolovanými dátami z databázy
                // ZATIAĽ NEROBÍ NIČ
            }
        });

        // Tlačidlo ODHLÁSIŤ.
        btnOdhlasit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Click - Odhlasujem\n");
                btnOdhlasitActionPerformed(e);
            }
        });

        tabJazdy.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                tabJazdySelectionValueChanged(e);
            }
        });

        // ////////////////////////////////////////////////////////////////////
        // Dodatkové nastavenia pre main okno.
        setPreferredSize(new Dimension(700, 500));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }

    // Akcie tlačidla pre úpravu uloženého auta.
    private void btnUpravitAutoActionPerformed(ActionEvent e) throws HeadlessException, FileNotFoundException {
        PridatAutoForm editableAuto = new PridatAutoForm(selectedAuto, null);
        editableAuto.setLocationRelativeTo(CENTER_SCREEN);
        editableAuto.setTitle("Kniha jázd - úprava vozidla");
        editableAuto.setVisible(true);

        obnovAuta();
    }

    // Akcia tlačidla pre pridanie novej cesty. [DONE]
    private void btnPridatJazduActionPerformed(ActionEvent e) {
        PridatCestuForm pridatCestuForm = null;
        pridatCestuForm = new PridatCestuForm(login, selectedAuto, this);
        pridatCestuForm.setTitle("Kniha jázd - pridanie nového záznamu");
        pridatCestuForm.setLocationRelativeTo(CENTER_SCREEN);

        pridatCestuForm.setVisible(true);
        obnovJazdy();
    }

    // Akcia tlačidla pre pridanie nového auta.  [DONE]
    private void btnPridatAutoActionPerformed(ActionEvent e) {
        PridatAutoForm pridatAutoForm = null;
        pridatAutoForm = new PridatAutoForm(login, this);
        pridatAutoForm.setTitle("Kniha jázd - pridanie vozidla");
        pridatAutoForm.setLocationRelativeTo(CENTER_SCREEN);
        pridatAutoForm.setVisible(true);
        obnovAuta();
        obnovJazdy();
    }

    // Akcia tlačidla pre odhlásenie. [DONE]
    private void btnOdhlasitActionPerformed(ActionEvent e) {
        Object[] options = {"Áno", "Nie"};
        int n = JOptionPane.showOptionDialog(this, "Skutočne sa chcete odhlásiť ?", "Ohlásenie", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        // Ak užívateľ vyberie "ÁNO".
        if (n == 0) {
            dispose();
            PrihlasovaciForm prihlasovaciForm = new PrihlasovaciForm();
            prihlasovaciForm.setTitle("Kniha jázd - prihlásenie");
            prihlasovaciForm.setLocationRelativeTo(CENTER_SCREEN);
            prihlasovaciForm.setVisible(true);
        }
    }

    // Ošetrenia pre tabuľku, deaktivácia tlačidiel. [DONE]
    private void tabJazdySelectionValueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            if (!tabJazdy.getSelectionModel().isSelectionEmpty()) {
                btnVymazatCestu.setEnabled(true);
                btnUpravitUzivatela.setEnabled(false);
            } else {
                btnVymazatCestu.setEnabled(false);
                btnUpravitUzivatela.setEnabled(false);
            }
        }
    }

    // Nastavenie tabuľky - ÚPRAVA CESTY. [DONE]
    public void setJTable() {
        tabJazdy = new JTable();
        tabJazdy.setModel(jazdaTableModel);
        tabJazdy.setPreferredScrollableViewportSize(new Dimension(700, 320));
        tabJazdy.setFillsViewportHeight(true);
        add(tabJazdy, "wrap, span 6");

        JScrollPane scrollPane = new JScrollPane(tabJazdy);
        add(scrollPane, "wrap, span 6");

        // Klikanie na polozky v tabuľke + editácia vybratej položky.      
        tabJazdy.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table = (JTable) me.getSource();

                Point bod = me.getPoint();
                System.out.println("[" + bod.getX() + ", " + bod.getY() + "]");
                int riadok = table.rowAtPoint(bod);
                System.out.println("Riadok: " + riadok);

                // Ak spravíme dvojklik ľavým tlačídlom a zároveň sme s kurzorom na nejakom riadku.
                if (me.getClickCount() == 2 && riadok != -1 && SwingUtilities.isLeftMouseButton(me)) {

                    // Uložená vyznačená jazda v tabuľke na ktorú aktuálne dvojklikáme.
                    Jazda vybranaJazda = (Jazda) jazdaTableModel.dajPodlaCislaRiadku(riadok);
                    
                    // Otvoríme editovacie okno a predvyplníme vydolované dáta.
                    PridatCestuForm editableCesta = new PridatCestuForm(vybranaJazda, null);
                    editableCesta.setLocationRelativeTo(CENTER_SCREEN);
                    editableCesta.setTitle("Kniha jázd - úprava cesty");
                    editableCesta.setVisible(true);

                    obnovJazdy();
                }
            }
        });
    }

    // Uchováva info o aktuálne vyzerajúcom comboboxe. [DONE]
    private ComboBoxModel getAutaModel() {
        List<Auto> auto = autoDao.zoznamPodlaPouzivatela(login);

        // Ak combobox neuchováva žiadne položky.
        if (!auto.isEmpty()) {
            System.out.println("Načítaný zoznam aut nie je prazdny."); // Matej
            // Na tomto mieste je potrebné, ak sa prvýkrát prihlásime 
            // do comboboxu vložiť prvú položku z comboboxu.
            // Inak je tu potrebné vložiť aktuálne vybranú položku. 
            System.out.println("Nastavujem selectedAuto v comboBoxe " + auto.get(0).getSpz()); // Matej

            // TU BY BOLO DOBRE NEJAKÉ OŠETRENIE, LEBO SELECTEDAUTO JE VŽDY NASTAVENÉ ROVNAKO 
            // SPôSOBUJE TO PROBLÉMY: AK PRIDÁME NOVÉ AUTO DO ZOZNAMU, COMBOBOX SA ZMENÍ NA 
            // PRVÉ AUTO V ZOZNAME, A ZOZNAM SA NEAKTUALIZUJE
            selectedAuto = auto.get(0);
        }
        return new DefaultComboBoxModel(auto.toArray());
    }

    // Obnovenie áut v comboboxe. [DONE]
    public void obnovAuta() {
        comboAuta.setModel(getAutaModel());

        // Ak combobox nie je prázdny.
        if (getAutaModel().getSize() != 0) {
            comboAuta.setRenderer(autoListCellRenderer);
            btnUpravitAuto.setEnabled(true); 
            btnVymazatAuto.setEnabled(true);
            btnNovaCesta.setEnabled(true);
        } else {
            // Ak combobox je prázdny.
            btnUpravitAuto.setEnabled(false);
            btnVymazatAuto.setEnabled(false);
            btnNovaCesta.setEnabled(false);
        }

    }
    
    // Obnovenie jázd v JTable. [DONE]
    private void obnovJazdy() {
        // Ak sa v comboboxe nachádzajú nejaké autá.
        if (getAutaModel().getSize() != 0) {
            selectedAuto = (Auto) comboAuta.getSelectedItem(); // TOTO ZABEZPEČI, KOMPLET REFRESH

            jazdaTableModel.obnov(selectedAuto, login);
            System.out.println("Rok, stav tach, avg, id: " + selectedAuto.getRok_vyr() + " " + selectedAuto.getStav_tach() + " ," + selectedAuto.getSpotreba_avg() + ", " + selectedAuto.getIdPouzivatel());
            jazdaTableModel.fireTableDataChanged(); // Matej
        }
    }

    // Main - MainForm
    public static void main(String args[]) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new WindowsLookAndFeel());

        MainForm mainForm = new MainForm();
        mainForm.setVisible(true);
        mainForm.setTitle("Kniha jázd - hlavné okno");
        mainForm.setLocationRelativeTo(CENTER_SCREEN);
    }
}
