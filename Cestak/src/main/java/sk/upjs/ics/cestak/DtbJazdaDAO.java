package sk.upjs.ics.cestak;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

/**
 * SKONTROLOVAT !
 *
 * @author Matej
 */
public class DtbJazdaDAO implements JazdaDAO {

    private JdbcTemplate jdbcTemplate;

    public DtbJazdaDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private BeanPropertyRowMapper<Jazda> mapovac
            = new BeanPropertyRowMapper<>(Jazda.class);

    @Override
    public List<Jazda> zoznamPodlaAut(Auto auto) {
        String sql = "SELECT * FROM Jazda WHERE idPouzivatel = ?";
        List<Jazda> s = jdbcTemplate.query(sql, mapovac, auto.getIdPouzivatela());

        return s;
    }

    @Override
    public void saveJazda(Auto auto, Jazda jazda) {
        Map into = new HashMap();
        into.put("datum", jazda.getDatum());
        into.put("vyjazd", jazda.getVyjazd());
        into.put("prijazd", jazda.getPrijazd());
        into.put("prejdeneKilometre", jazda.getPrejdeneKilometre());
        into.put("cerpaniePHM", jazda.getCerpaniePHM());
        // pridat aj ostatne

        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);

        insert.setTableName("Jazda");
        insert.execute(into);

        // ? Neviem ako ? 
        //String sql = "INSERT INTO PouzivatelAuto(`idPouzivatel`, `SPZ`) VALUES(?, ?)";
        //jdbcTemplate.update(sql, jazda.getIdJazda(), auto.getSpz());
    }

    @Override
    public void vymazJazda(Auto auto, Jazda jazda) {
        jdbcTemplate.update("DELETE FROM Jazda WHERE SPZ = ?",
                auto.getSpz());
    }
}
