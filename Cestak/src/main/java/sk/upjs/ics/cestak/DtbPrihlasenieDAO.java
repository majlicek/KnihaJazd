package sk.upjs.ics.cestak;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

/**
 *
 * @author Majlo
 */
public class DtbPrihlasenieDAO implements PrihlasenieDAO {

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DtbPrihlasenieDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.jdbcTemplate);
    }

    private BeanPropertyRowMapper<Login> mapovac
            = new BeanPropertyRowMapper<>(Login.class);

    @Override
    public Pouzivatel savePouzivatela(Pouzivatel pouzivatel) {
        Map<String, Object> into = new HashMap<String, Object>();
        into.put("meno", pouzivatel.getMeno());
        into.put("priezvisko", pouzivatel.getPriezvisko());
        into.put("pohlavie", pouzivatel.getPohlavie());
        into.put("datum_narodenia", pouzivatel.getDatum());
        into.put("adresa", pouzivatel.getAdresa());
        into.put("email", pouzivatel.getEmail());
        into.put("tel", pouzivatel.getTel());
        
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);

        insert.setGeneratedKeyName("idPouzivatel");

        insert.setTableName("Pouzivatel");
        Number id = insert.executeAndReturnKey(into);
        pouzivatel.setId(id.intValue());
        return pouzivatel;
    }
    @Override
    public void saveLogin(Login login) {
        String sql = "INSERT INTO Login(`idPouzivatel`, `login`, `heslo`) VALUES(?, ?, ?)";
        jdbcTemplate.update(sql,login.getId(), login.getLogin(), login.getHeslo());
    }

    @Override
    public Login verifyLogin(Login login) {
//        int hash = login.getHeslo().hashCode();
        String sql = "SELECT l.idPouzivatel AS id, l.login, l.heslo FROM Login AS l WHERE l.login = ? AND l.heslo = ?";
        try {            
            return jdbcTemplate.queryForObject(sql, mapovac, login.getLogin(), login.getHeslo());          
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public boolean verifyOnlyLogin(Login login) {
        String sql = "SELECT * FROM Login WHERE login = ?";
        try {
            jdbcTemplate.queryForObject(sql, mapovac, login.getLogin());
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
