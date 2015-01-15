package sk.upjs.ics.cestak;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

/**
 *
 * @author Matej Perejda
 */
public class DtbAutoDAO implements AutoDAO {

    private JdbcTemplate jdbcTemplate;

    public DtbAutoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private BeanPropertyRowMapper<Auto> mapovac
            = new BeanPropertyRowMapper<>(Auto.class);

    @Override
    public List<Auto> zoznamPodlaPouzivatela(Login login) {
        String sql = "SELECT * FROM Auto WHERE idPouzivatel = ?";
        List<Auto> s = jdbcTemplate.query(sql, mapovac, login.getId());
         
        return s;
    }

    @Override
    public void saveAuto(Login login, Auto auto) {
        Map into = new HashMap();
        into.put("znacka", auto.getZnacka());
        into.put("model", auto.getModel());
        into.put("SPZ", auto.getSpz());
        into.put("rok_vyr", auto.getRok_vyr());
        into.put("stav_tach", auto.getStav_tach());
        into.put("vykon", auto.getVykon());
        into.put("spotreba_mesto", auto.getSpotreba_mesto());
        into.put("spotreba_mimo", auto.getSpotreba_mimo());
        into.put("spotreba_avg", auto.getSpotreba_avg());
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
        jdbcTemplate.update("DELETE FROM Auto WHERE SPZ = ?", 
                auto.getSpz());
    }

}
