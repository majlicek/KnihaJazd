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
    
    private String cas_odchod;

    private String prijazd;
    
    private String cas_prichod;

    private String prejdeneKilometre;
    
    private int poc_stav_km;

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

    /**
     * @return the poc_stav_km
     */
    public int getPoc_stav_km() {
        return poc_stav_km;
    }

    /**
     * @param poc_stav_km the poc_stav_km to set
     */
    public void setPoc_stav_km(int poc_stav_km) {
        this.poc_stav_km = poc_stav_km;
    }

    /**
     * @return the cas_odchod
     */
    public String getCas_odchod() {
        return cas_odchod;
    }

    /**
     * @param cas_odchod the cas_odchod to set
     */
    public void setCas_odchod(String cas_odchod) {
        this.cas_odchod = cas_odchod;
    }

    /**
     * @return the cas_prichod
     */
    public String getCas_prichod() {
        return cas_prichod;
    }

    /**
     * @param cas_prichod the cas_prichod to set
     */
    public void setCas_prichod(String cas_prichod) {
        this.cas_prichod = cas_prichod;
    }
}
