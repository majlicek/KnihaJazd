
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import sk.upjs.ics.cestak.Auto;
import sk.upjs.ics.cestak.DaoFactory;
import sk.upjs.ics.cestak.Jazda;
import sk.upjs.ics.cestak.JazdaDAO;
import sk.upjs.ics.cestak.Login;

/*
 *
 * @author Matej Perejda
 */
public class DtbJazdaDAOTest {

    @BeforeClass
    public static void setUpTests() {
        System.setProperty("testovaciRezim", "true");
    }

    @Test
    public void testPocetRiadkov() {
        int pocetRiadkov = DaoFactory.INSTANCE.jdbcTemplate().queryForInt("SELECT COUNT(*) FROM Jazda");
        assertEquals(1, pocetRiadkov);
    }

    @Test
    public void testDajVsetky() {
        Auto auto = new Auto();        
        Login login = new Login();
        JazdaDAO jazdaDAO = DaoFactory.INSTANCE.jazdaDao();
        List<Jazda> jazdy = jazdaDAO.zoznamPodlaAut(auto, login);
        assertEquals(1, jazdy.size());
    }
}
