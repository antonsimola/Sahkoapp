package com.example.android.horizontalpaging;

/**
 * Created by Anton_2 on 5.4.2015.
 */
public class TyotehtavaSisalto {
    private String tehtava;
    private String aloitus;
    private String deadline;
    private String yhteyshenkilo;
    private String muuta;
    private String osoite;
    private String ohjPiirLinkki;

    public String getTehtava() {
        return "Tehtävä: " + tehtava;
    }

    public void setTehtava(String tehtava) {
        this.tehtava = tehtava;
    }

    public String getAloitus() {
        return "Aloitus: "+aloitus;
    }

    public void setAloitus(String aloitus) {
        this.aloitus = aloitus;
    }

    public String getDeadline() {
        return "Deadline: "+deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getYhteyshenkilo() {
        return "Yhteyshenkilö +" +yhteyshenkilo;
    }

    public void setYhteyshenkilo(String yhteyshenkilo) {
        this.yhteyshenkilo = yhteyshenkilo;
    }

    public String getMuuta() {
        return "Muuta: " + muuta;
    }

    public void setMuuta(String muuta) {
        this.muuta = muuta;
    }

    public String getOsoite() {
        return "Osoite: " +osoite;
    }

    public void setOsoite(String osoite) {
        this.osoite = osoite;
    }

    public String getOhjPiirLinkki() {
        return ohjPiirLinkki;
    }

    public void setOhjPiirLinkki(String ohjPiirLinkki) {
        this.ohjPiirLinkki = ohjPiirLinkki;
    }
}
