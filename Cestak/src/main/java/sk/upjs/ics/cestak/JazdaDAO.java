package sk.upjs.ics.cestak;

import java.util.List;

/**
 * SKONTROLOVAT !
 *
 * @author Matej Perejda
 */
public interface JazdaDAO {

    List<Jazda> zoznamPodlaAut(Auto auto);

    void saveJazda(Auto auto, Jazda jazda);

    void vymazJazda(Auto auto, Jazda jazda);
}
