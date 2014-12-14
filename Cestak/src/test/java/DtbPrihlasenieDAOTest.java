
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.cestak.DaoFactory;
import sk.upjs.ics.cestak.DtbPrihlasenieDAO;
import sk.upjs.ics.cestak.Login;
import sk.upjs.ics.cestak.PrihlasenieDAO;

/**
 *
 * @author Matej Perejda
 */
public class DtbPrihlasenieDAOTest {

    private BeanPropertyRowMapper<Login> mapovac = new BeanPropertyRowMapper<>(Login.class);
    private DtbPrihlasenieDAO dtbPrihlasenieDAO;
   
    private PrihlasenieDAO prihlasenieDao = DaoFactory.INSTANCE.prihlasenieDao();
    private Login login;
    

    @Before
    public void setUp() {
        dtbPrihlasenieDAO = new DtbPrihlasenieDAO();
        login = new Login();
    }

    @Test
    public void testPrihlasenie() {
        login.setId(9);
        login.setLogin("matej");
        login.setHeslo("matej");
        boolean jePrihlaseny = dtbPrihlasenieDAO.verifyOnlyLogin(login);

        assertTrue(jePrihlaseny);
    }

}
