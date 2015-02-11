package sk.upjs.ics.cestak;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
/**
 *
 * @author Majlo
 */



public class JazdaRowMapper implements RowMapper<Jazda> {

    @Override
    public Jazda mapRow(ResultSet rs, int rowNum) throws SQLException {
        Jazda jazda = new Jazda();
        Auto auto = new Auto();
        jazda.setAuto(auto);
        jazda.setIdPouzivatel(rs.getInt("IdPouzivatela"));
        jazda.setPrejdeneKilometre(rs.getString("PrejdeneKilometre"));
        jazda.setDatum(rs.getString("Datum"));
        jazda.getAuto().setSpotreba_avg(rs.getDouble("Spotreba"));
        jazda.getAuto().setSpotreba_mesto(rs.getDouble("VMeste"));
        jazda.getAuto().setSpotreba_mimo(rs.getDouble("MimoMesta"));
        
        return jazda;
    }

}
