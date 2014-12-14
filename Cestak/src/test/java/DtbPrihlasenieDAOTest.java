
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import sk.upjs.ics.cestak.DaoFactory;
import sk.upjs.ics.cestak.Login;
import sk.upjs.ics.cestak.PrihlasenieDAO;

/**
 *
 * @author Matej Perejda
 */
public class DtbPrihlasenieDAOTest {

    private BeanPropertyRowMapper<Login> mapovac = new BeanPropertyRowMapper<>(Login.class);

    private PrihlasenieDAO prihlasenieDao = DaoFactory.INSTANCE.prihlasenieDao();
    private Login login;

    @Before
    public void setUp() {
        login = new Login();
    }

    @Test
    public void testPrihlasenieZadanyInyLogin() {
        login.setLogin("robert");
       
        boolean jePrihlaseny = prihlasenieDao.verifyOnlyLogin(login);

        assertFalse(jePrihlaseny);
    }
    
    @Test
    public void testPrihlasenieZadanyRovnakyLogin() {
        login.setLogin("matej");
       
        boolean jePrihlaseny = prihlasenieDao.verifyOnlyLogin(login);

        assertTrue(jePrihlaseny);
    }


}
