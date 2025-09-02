/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.main_project;

/**
 * Class of the object of type categorie
 *
 * @author E01 G1 INF2 HIS
 */
public class Categorie {

    private int code_categorie;
    private String nom_categorie;

    /**
     * @return the code_categorie
     */
    public int getCode_categorie() {
        return code_categorie;
    }

    /**
     * @param code_categorie the code_categorie to set
     */
    public void setCode_categorie(int code_categorie) {
        this.code_categorie = code_categorie;
    }

    /**
     * @return the nom_categorie
     */
    public String getNom_categorie() {
        return nom_categorie;
    }

    /**
     * @param nom_categorie the nom_categorie to set
     */
    public void setNom_categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }

    /**
     * Constructeur par défault
     */
    public Categorie() {
    }

    /**
     * Construceur instanciant une catégorie
     *
     * @param code_categorie code catégorie d'un produit
     * @param nom_categorie nome de la catégorie d'un produit
     */
    public Categorie(int code_categorie, String nom_categorie) {
        this.code_categorie = code_categorie;
        this.nom_categorie = nom_categorie;
    }

    /**
     * Classe catégorie ayant comme attribut le nom et l'identifiant de la
     * catégorie majeur d'un produit
     *
     * @param nom_categorie de la catégorie d'un produit
     */
    public Categorie(String nom_categorie) {

        this.nom_categorie = nom_categorie;
    }

}
