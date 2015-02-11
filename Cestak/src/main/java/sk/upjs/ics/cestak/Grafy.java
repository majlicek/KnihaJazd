package sk.upjs.ics.cestak;

import java.util.List;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

/**
 *
 * @author Majlo
 */
public class Grafy {
    
    private List<Jazda> jazdy;
    private int druhSpotreby;
    private JazdaDAO jazdaDao = DaoFactory.INSTANCE.jazdaDao();

    public Grafy(Login login, int vstup) {
        this.druhSpotreby = vstup;
        jazdy = jazdaDao.zoznamJazdPouzivatela(login);
        XYDataset ds = createDataset();
        JFreeChart chart
                = ChartFactory.createXYLineChart("Graf spotreby",
                        "Počet jázd", "Spotreba", ds, PlotOrientation.VERTICAL, true, true,
                        false);
        ChartPanel cp = new ChartPanel(chart);
        
        JFrame frame = new JFrame("Spotreba");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.getContentPane().add(cp);
        
    }

    public XYDataset createDataset() {
        DefaultXYDataset ds = new DefaultXYDataset();
        String[] vypis = {"Priemerná spotreba", "Mimo mesta", "V meste"};
        double[][] data = new double[2][jazdy.size()];
        for (int i = 0; i < jazdy.size(); i++) {
            data[0][i] = i + 1;
            Jazda jazda = jazdy.get(i);
            Long tmp1 = new Long(jazda.getPrejdeneKilometre());

            if (druhSpotreby == 0) {
                data[1][i] = tmp1 * jazda.getAuto().getSpotreba_avg() / 100;
            }
            if (druhSpotreby == 1) {
                data[1][i] = tmp1 * jazda.getAuto().getSpotreba_mimo() / 100;
            }
            if (druhSpotreby == 2) {
                data[1][i] = tmp1 * jazda.getAuto().getSpotreba_mesto() / 100;
            }
        }
        ds.addSeries(vypis[druhSpotreby], data);
        return ds;
    }
}
