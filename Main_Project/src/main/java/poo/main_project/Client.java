/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.main_project;

/**
 * Client Class :  Defines the clients of the store <br>
 * Inherits from the Individu Class<br>
 * @author E01 G1 INF2 HIS 
 */
public class Client extends Individu{
   private String nomClient;

    /**
     * @return the nomClient
     */
    public String getNomClient() {
        return nomClient;
    }

    /**
     * @param nomClient the nomClient to set
     */
    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    /**
     * Default Constructor
     */
    public Client() {
    }

    /**
     * Constructor of an individual of type Client
     * @param nomClient nom du client
     * @param Responsable nom du responsable
     * @param numtelmobil numero de telephone mobile 
     * @param numtelfix   numero de telephone fix
     * @param adresse adresse physique
     * @param email adresse email
     */
    public Client(String nomClient, String Responsable, String numtelmobil, String numtelfix, String adresse, String email) {
        super(Responsable, numtelmobil, numtelfix, adresse, email);
        this.nomClient = nomClient;
    }

    /**
     * Constructer without inherited parameters
     * @param nomClient
     */
    public Client(String nomClient) {
        this.nomClient = nomClient;
    }
  
 
    
}
