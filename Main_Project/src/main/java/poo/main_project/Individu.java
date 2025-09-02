/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.main_project;

/**
 * Individu Class : generic type of an individual meant to be either a client or
 * supplier
 *
 * @author E01 G1 INF2 HIS
 */
public class Individu {

    private String Responsable;
    private String numtelmobil;
    private String numtelfix;
    private String adresse;
    private String email;

    /**
     * @return the Responsable
     */
    public String getResponsable() {
        return Responsable;
    }

    /**
     * @param Responsable the Responsable to set
     */
    public void setResponsable(String Responsable) {
        this.Responsable = Responsable;
    }

    /**
     * @return the numtelmobil
     */
    public String getNumtelmobil() {
        return numtelmobil;
    }

    /**
     * @param numtelmobil the numtelmobil to set
     */
    public void setNumtelmobil(String numtelmobil) {
        this.numtelmobil = numtelmobil;
    }

    /**
     * @return the numtelfix
     */
    public String getNumtelfix() {
        return numtelfix;
    }

    /**
     * @param numtelfix the numtelfix to set
     */
    public void setNumtelfix(String numtelfix) {
        this.numtelfix = numtelfix;
    }

    /**
     * @return the adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * @param adresse the adresse to set
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Default constructor
     */
    public Individu() {
    }

    /**
     * Constructeur of said individual. Only called to instantiate its children
     * classes "Flient or Fournisseur"
     *
     * @param Responsable nom du responsable
     * @param numtelmobil numero de telephone mobile
     * @param numtelfix numero de telephone fix
     * @param adresse adresse physique
     * @param email adresse email
     */
    public Individu(String Responsable, String numtelmobil, String numtelfix, String adresse, String email) {
        this.Responsable = Responsable;
        this.numtelmobil = numtelmobil;
        this.numtelfix = numtelfix;
        this.adresse = adresse;
        this.email = email;
    }

}
