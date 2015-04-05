package com.example.android.horizontalpaging;

/**
 * Created by Anton_2 on 5.4.2015.
 */
public class TyotehtavaOtsikko {
    private String pvm;
    private String tyomaa;

    public TyotehtavaSisalto getSisalto() {
        return sisalto;
    }

    public void setSisalto(TyotehtavaSisalto sisalto) {
        this.sisalto = sisalto;
    }

    private TyotehtavaSisalto sisalto;
    public String getPvm() {
        return pvm;
    }

    public void setPvm(String pvm) {
        this.pvm = pvm;
    }

    public String getTyomaa() {
        return tyomaa;
    }

    public void setTyomaa(String tyomaa) {
        this.tyomaa = tyomaa;
    }

    public TyotehtavaOtsikko(){
    }
}
