/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.cestak;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
/**
 *
 * @author Majlo
 */



public class AutoRowMapper implements RowMapper<Auto> {

    @Override
    public Auto mapRow(ResultSet rs, int rowNum) throws SQLException {
        Auto auto = new Auto();
        auto.setSpz(rs.getString("SPZ"));
        auto.setModel(rs.getString("model"));
        auto.setZnacka(rs.getString("znacka"));
        auto.setFarba(rs.getString("farba"));
        return auto;
    }

}

