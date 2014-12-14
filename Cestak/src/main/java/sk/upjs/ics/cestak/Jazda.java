package sk.upjs.ics.cestak;

/**
 * SKONTROLOVAT ! 
 * 
 * @author Matej Perejda
 */
public class Jazda {

    private int idJazda;

    private String SPZ;

    private int idPouzivatel;

    private String vyjazd;

    private String prijazd;

    private String prejdeneKilometre;

    private double cerpaniePHM;

    private double cenaPHM;

    private String datum;

    public int getIdJazda() {
        return idJazda;
    }

    public void setIdJazda(int idJazda) {
        this.idJazda = idJazda;
    }

    public String getSPZ() {
        return SPZ;
    }

    public void setSPZ(String SPZ) {
        this.SPZ = SPZ;
    }

    public int getIdPouzivatel() {
        return idPouzivatel;
    }

    public void setIdPouzivatel(int idPouzivatel) {
        this.idPouzivatel = idPouzivatel;
    }

    public String getVyjazd() {
        return vyjazd;
    }

    public void setVyjazd(String vyjazd) {
        this.vyjazd = vyjazd;
    }

    public String getPrijazd() {
        return prijazd;
    }

    public void setPrijazd(String prijazd) {
        this.prijazd = prijazd;
    }

    public String getPrejdeneKilometre() {
        return prejdeneKilometre;
    }

    public void setPrejdeneKilometre(String prejdeneKilometre) {
        this.prejdeneKilometre = prejdeneKilometre;
    }

    public double getCerpaniePHM() {
        return cerpaniePHM;
    }

    public void setCerpaniePHM(double cerpaniePHM) {
        this.cerpaniePHM = cerpaniePHM;
    }

    public double getCenaPHM() {
        return cenaPHM;
    }

    public void setCenaPHM(double cenaPHM) {
        this.cenaPHM = cenaPHM;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }
}
