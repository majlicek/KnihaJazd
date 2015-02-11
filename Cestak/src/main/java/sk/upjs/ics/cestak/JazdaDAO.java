package sk.upjs.ics.cestak;

import java.util.List;

/**
 *
 * @author Matej Perejda
 */
public interface JazdaDAO {

    List<Jazda> zoznamPodlaAut(Auto auto, Login login);
    
    List<Jazda> zoznamJazdPouzivatela(Login login);

    void saveJazda(Jazda jazda);

    void vymazJazda(Jazda jazda);
}
