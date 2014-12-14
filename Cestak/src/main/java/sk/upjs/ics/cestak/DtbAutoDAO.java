package sk.upjs.ics.cestak;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

/**
 *
 * @author Matej Perejda
 */
public class DtbAutoDAO implements AutoDAO {

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DtbAutoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.jdbcTemplate);
    }

    private BeanPropertyRowMapper<Login> mapovac
            = new BeanPropertyRowMapper<>(Login.class);

    @Override
    public List<Auto> zoznamPodlaPouzivatela(Login login) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void saveAuto(Login login, Auto auto) {
        Map<String, Object> into = new HashMap<String, Object>();
        into.put("znacka", auto.getZnacka());
        into.put("model", auto.getModel());
        into.put("SPZ", auto.getSpz());
        into.put("rok_vyroby", auto.getRok_vyr());
        into.put("stav_tachometra", auto.getStav_tach());
        into.put("vykon", auto.getStav_tach());
        into.put("spotreba_mesto", auto.getSpotreba_mesto());
        into.put("spotreba_mimo", auto.getSpotreba_mimo());
        into.put("avg_spotreba", auto.getSpotreba_avg());
        into.put("palivo", auto.getPalivo());
        into.put("prevodovka", auto.getPrevodovka());
        into.put("klima", auto.getKlima());
        into.put("farba", auto.getFarba());
        into.put("idPouzivatel", login.getId());

        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);

        //insert.setGeneratedKeyName("idPouzivatel");
        insert.setTableName("Auto");
        insert.execute(into);

        String sql = "INSERT INTO PouzivatelAuto(`idPouzivatel`, `SPZ`) VALUES(?, ?)";
        jdbcTemplate.update(sql, login.getId(), auto.getSpz());
    }

    @Override
    public void vymazAuto(Auto auto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
