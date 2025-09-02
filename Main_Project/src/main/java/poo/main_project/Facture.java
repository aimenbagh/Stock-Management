/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.main_project;

import java.util.Date;

/**
 * Facture Class: Defines a bill for both sell and supply operations
 *
 * @author E01 G1 INF2 HIS
 */
public class Facture {

    private String date;
    private String idfacture;
    private String id;
    private String categorie;
    private String souscategorie;
    private String nom;
    private String fabriquant;
    private String quantite;
    private String valeur;

    /**
     * Default Construceur
     */
    public Facture() {
    }

    ;

    /**
     *Constructeur of an object of type Facture  
     * @param date date de la facturation
     * @param idfacture identifiant de la facture
     * @param id identifiant du produit
     * @param categorie catégorie du produit
     * @param souscategorie sous catégorie du produit
     * @param nom nom du produit 
     * @param fabriquant nom du fabriquant
     * @param quantite la achete ou vendu
     * @param valeur la valeur de l'achat ou de la vente
     */
    public Facture(String date, String idfacture, String id, String categorie, String souscategorie, String nom, String fabriquant, String quantite, String valeur) {
        this.date = date;
        this.idfacture = idfacture;
        this.id = id;
        this.categorie = categorie;
        this.souscategorie = souscategorie;
        this.nom = nom;
        this.fabriquant = fabriquant;
        this.quantite = quantite;
        this.valeur = valeur;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the idfacture
     */
    public String getIdfacture() {
        return idfacture;
    }

    /**
     * @param idfacture the idfacture to set
     */
    public void setIdfacture(String idfacture) {
        this.idfacture = idfacture;
    }

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
     * @return the categorie
     */
    public String getCategorie() {
        return categorie;
    }

    /**
     * @param categorie the categorie to set
     */
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    /**
     * @return the souscategorie
     */
    public String getSouscategorie() {
        return souscategorie;
    }

    /**
     * @param souscategorie the souscategorie to set
     */
    public void setSouscategorie(String souscategorie) {
        this.souscategorie = souscategorie;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the fabriquant
     */
    public String getFabriquant() {
        return fabriquant;
    }

    /**
     * @param fabriquant the fabriquant to set
     */
    public void setFabriquant(String fabriquant) {
        this.fabriquant = fabriquant;
    }

    /**
     * @return the quantite
     */
    public String getQuantite() {
        return quantite;
    }

    /**
     * @param quantite the quantite to set
     */
    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    /**
     * @return the valeur
     */
    public String getValeur() {
        return valeur;
    }

    /**
     * @param valeur the valeur to set
     */
    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

}
