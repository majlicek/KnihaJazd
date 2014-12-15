
package sk.upjs.ics.cestak;

import java.util.List;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Majlo
 */
public class JazdaTableModel extends AbstractTableModel {

    private static final int POCET_STLPCOV = 5;

    private static final Class[] TYPY_STLPCOV = {
        String.class,
        String.class,
        int.class,
        Double.class,
        String.class
    };
    
    private static final String[] NAZVY_STLPCOV = { "Odkiaľ", "Kam", "Prejdené km" , "Čerpanie PHM", "Dátum"};

    private JazdaDAO jazdaDao = DaoFactory.INSTANCE.jazdaDao();

    private List<Jazda> jazda = new LinkedList<>();

    @Override
    public int getRowCount() {
        return jazda.size();
    }

    @Override
    public int getColumnCount() {
        return POCET_STLPCOV;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Jazda selectedJazda = jazda.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return selectedJazda.getVyjazd();
            case 1:
                return selectedJazda.getPrijazd();
            case 2:
                return selectedJazda.getPrejdeneKilometre();
            case 3:
                return selectedJazda.getCerpaniePHM();
            case 4:
                return selectedJazda.getDatum();
            default:
                return "!!!";
        }
    }
    @Override
    public String getColumnName(int column) {
        return NAZVY_STLPCOV[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return TYPY_STLPCOV[columnIndex];
    }
    public Jazda dajPodlaCislaRiadku(int riadok) {
        return jazda.get(riadok);
    }

    public void obnov(Auto auto, Login login) {
        jazda = jazdaDao.zoznamPodlaAut(auto, login);
        fireTableDataChanged();
    }
}
