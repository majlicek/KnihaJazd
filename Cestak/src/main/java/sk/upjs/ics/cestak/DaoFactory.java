package sk.upjs.ics.cestak;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Majlo
 */
public enum DaoFactory {

    INSTANCE;

    private PrihlasenieDAO prihlasenieDao;

    private AutoDAO autoDao;

    private JazdaDAO jazdaDao;

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate jdbcTemplate() {
        if (this.jdbcTemplate == null) {
            this.jdbcTemplate = new JdbcTemplate(dataSource());
        }
        return this.jdbcTemplate;

    }

    public DataSource dataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://sql4.freemysqlhosting.net:3306/sql457246?zeroDateTimeBehavior=convertToNull");
        dataSource.setUser("sql457246");
        dataSource.setPassword("nV1*qI2%");

        return dataSource;
    }

    public PrihlasenieDAO prihlasenieDao() {
        if (this.prihlasenieDao == null) {
            this.prihlasenieDao = new DtbPrihlasenieDAO(jdbcTemplate());
        }
        return this.prihlasenieDao;
    }

    public AutoDAO autoDao() {
        if (this.autoDao == null) {
            this.autoDao = new DtbAutoDAO(jdbcTemplate());
        }
        return this.autoDao;
    }

    public JazdaDAO jazdaDao() {
        if (this.jazdaDao == null) {
            this.jazdaDao = new DtbJazdaDAO(jdbcTemplate());
        }
        return this.jazdaDao;
    }

}
