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
    public List<Jazda> zoznamPodlaAut(Auto auto, Login login) {
        String sql = "SELECT * FROM Jazda WHERE idPouzivatel = ? AND SPZ = ?";
        List<Jazda> s = jdbcTemplate.query(sql, mapovac, login.getId(), auto.getSpz()); 

        return s;
    }

    @Override
    public void saveJazda(Jazda jazda) {
        Map into = new HashMap();
        into.put("datum", jazda.getDatum());
        into.put("vyjazd", jazda.getVyjazd());
        into.put("prijazd", jazda.getPrijazd());
        into.put("SPZ", jazda.getSPZ());
        into.put("poc_stav_km", jazda.getPoc_stav_km());
        into.put("cas_odchod", jazda.getCas_odchod());
        into.put("cas_prichod", jazda.getCas_prichod());
        into.put("poznamka", jazda.getPoznamka());
        into.put("prejdeneKilometre", jazda.getPrejdeneKilometre());
        into.put("cerpaniePHM", jazda.getCerpaniePHM());
        into.put("idPouzivatel", jazda.getIdPouzivatel());

        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
            
        insert.setTableName("Jazda");
        insert.execute(into);
    }

    @Override
    public void vymazJazda(Jazda jazda) {
        jdbcTemplate.update("DELETE FROM Jazda WHERE idJazda = ?",
                jazda.getIdJazda());
    }
}
