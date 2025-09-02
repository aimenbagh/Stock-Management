/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.main_project;

/**
 * Produit Class : Defines the products in the stock<br>
 * Inherits from the Class "Categorie"<br>
 *
 * @author G1 INF2 HIS
 */
public class Produit extends Categorie {

    private String id;
    private String sousCategorie;
    private String Nom_prod;
    private String Fabriquant;
    private String Description;
    private int Quantite;
    private double prix_unit;
    private String etat;
    private int prix_tot;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the sousCategorie
     */
    public String getSousCategorie() {
        return sousCategorie;
    }

    /**
     * @param sousCategorie the sousCategorie to set
     */
    public void setSousCategorie(String sousCategorie) {
        this.sousCategorie = sousCategorie;
    }

    /**
     * @return the Nom_prod
     */
    public String getNom_prod() {
        return Nom_prod;
    }

    /**
     * @param Nom_prod the Nom_prod to set
     */
    public void setNom_prod(String Nom_prod) {
        this.Nom_prod = Nom_prod;
    }

    /**
     * @return the Fabriquant
     */
    public String getFabriquant() {
        return Fabriquant;
    }

    /**
     * @param Fabriquant the Fabriquant to set
     */
    public void setFabriquant(String Fabriquant) {
        this.Fabriquant = Fabriquant;
    }

    /**
     * @return the Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @param Description the Description to set
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     * @return the Quantite
     */
    public int getQuantite() {
        return Quantite;
    }

    /**
     * @param Quantite the Quantite to set
     */
    public void setQuantite(int Quantite) {
        this.Quantite = Quantite;
    }

    /**
     * @return the prix_unit
     */
    public double getPrix_unit() {
        return prix_unit;
    }

    /**
     * @param prix_unit the prix_unit to set
     */
    public void setPrix_unit(double prix_unit) {
        this.prix_unit = prix_unit;
    }

    /**
     * @return the etat
     */
    public String getEtat() {
        return etat;
    }

    /**
     * @param etat the etat to set
     */
    public void setEtat(String etat) {
        this.etat = etat;
    }

    /**
     * Default constructor
     */
    public Produit() {
    }

    /**
     * Constructor of a product
     *
     * @param id Identifieant d'un produit ou sa référence
     * @param nom_categorie la categorie du produit
     * @param code_categorie le code catégorie du produit
     * @param sousCategorie la sous categorie du produit
     * @param Nom_prod le nom du produit
     * @param Fabriquant le nom du fabriquant du produit
     * @param Description la description du produit
     * @param Quantite la quantié en stock du produit
     * @param prix_unit le prix unitaire du produi
     * @param etat l'etat en stock du produi
     */
    public Produit(String id, String nom_categorie, int code_categorie, String sousCategorie, String Nom_prod, String Fabriquant, String Description, int Quantite, double prix_unit, String etat) {
        super(code_categorie, nom_categorie);
        this.id = id;
        this.sousCategorie = sousCategorie;
        this.Nom_prod = Nom_prod;
        this.Fabriquant = Fabriquant;
        this.Description = Description;
        this.Quantite = Quantite;
        this.prix_unit = prix_unit;
        this.etat = etat;
    }

    /**
     * Overriden Constructor
     *
     * @param id Identifieant d'un produit ou sa référence
     * @param nom_categorie la categorie du produit
     * @param sousCategorie la sous categorie du produit
     * @param Nom_prod le nom du produit
     * @param Fabriquant le nom du fabriquant du produit
     * @param Description la description du produit
     * @param Quantite la quantié en stock du produit
     * @param prix_unit le prix unitaire du produi
     * @param etat l'etat en stock du produi
     */
    public Produit(String id, String nom_categorie, String sousCategorie, String Nom_prod, String Fabriquant, String Description, int Quantite, double prix_unit, String etat) {
        super(nom_categorie);
        this.id = id;
        this.sousCategorie = sousCategorie;
        this.Nom_prod = Nom_prod;
        this.Fabriquant = Fabriquant;
        this.Description = Description;
        this.Quantite = Quantite;
        this.prix_unit = prix_unit;
        this.etat = etat;
    }

    /**
     * Overriden Constructor
     *
     * @param id Identifieant d'un produit ou sa référence
     * @param nom_categorie la categorie du produit
     * @param sousCategorie la sous categorie du produit
     * @param Nom_prod le nom du produit
     * @param Fabriquant le nom du fabriquant du produit
     * @param Description la description du produit
     * @param Quantite la quantié en stock du produit
     * @param prix_unit le prix unitaire du produi
     */
    public Produit(String id, String nom_categorie, String sousCategorie, String Nom_prod, String Fabriquant, String Description, int Quantite, double prix_unit, int prix_tot) {
        super(nom_categorie);
        this.id = id;
        this.sousCategorie = sousCategorie;
        this.Nom_prod = Nom_prod;
        this.Fabriquant = Fabriquant;
        this.Description = Description;
        this.Quantite = Quantite;
        this.prix_unit = prix_unit;
        this.prix_tot = prix_tot;
    }

    /**
     * @return the prix_tot
     */
    public int getPrix_tot() {
        return prix_tot;
    }

    /**
     * @param prix_tot the prix_tot to set
     */
    public void setPrix_tot(int prix_tot) {
        this.prix_tot = prix_tot;
    }

}
