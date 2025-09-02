/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.main_project;

/**
 * Fournisseur class : Defines the suppliers of the store<br>
 * Inherits from the Individu Class
 *
 * @author E01 G1 INF2 HIS
 */
public class Fournisseur extends Individu {

    private String Entreprise;

    /**
     * @return the Entreprise
     */
    public String getEntreprise() {
        return Entreprise;
    }

    /**
     * @param Entreprise the Entreprise to set
     */
    public void setEntreprise(String Entreprise) {
        this.Entreprise = Entreprise;
    }

    /**
     * Constructeur par default
     */
    public Fournisseur() {
    }

    /**
     * Constructeur d'un individue de type Fournisseur
     *
     * @param Entreprise nom de l'entreprise
     * @param Responsable nom du responsable
     * @param numtelmobil numero de telephone mobile
     * @param numtelfix numero de telephone fix
     * @param adresse adresse physique
     * @param email adresse email
     */
    public Fournisseur(String Entreprise, String Responsable, String numtelmobil, String numtelfix, String adresse, String email) {
        super(Responsable, numtelmobil, numtelfix, adresse, email);
        this.Entreprise = Entreprise;
    }

    /**
     *
     * @param Entreprise
     */
    public Fournisseur(String Entreprise) {
        this.Entreprise = Entreprise;
    }

    /**
     * @return the Entreprise
     */
}
