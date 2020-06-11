/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doranco.eboutique.entity;


import fr.doranco.eboutique.enums.Role;
import java.io.File;
import java.util.List;

/**
 *
 * @author Boule
 */
public class Utilisateur {
   private Integer id;
   private String nom;
   private String prenom;
   private Integer age;
   private Role role;
   private Adresse adresse;
   private byte[] motDepasse;
   private String email;
   private String telephone;
   private String photoProfil;
   private List<Commande> commandes;
   private List<CartePaiement> cartesPaiement;
   private Panier panier;
   private Boolean isActive;
   private Boolean isOnline;
   private byte[] cle;

    public Utilisateur() {
    }

    

   
   
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPhotoProfil() {
        return photoProfil;
    }

    public void setPhotoProfil(String photoProfil) {
        this.photoProfil = photoProfil;
    }
    
    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }

    public List<CartePaiement> getCartesPaiement() {
        return cartesPaiement;
    }

    public void setCartesPaiement(List<CartePaiement> cartesPaiement) {
        this.cartesPaiement = cartesPaiement;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public byte[] getMotDepasse() {
        return motDepasse;
    }

    public void setMotDepasse(byte[] motDepasse) {
        this.motDepasse = motDepasse;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Boolean isOnline) {
        this.isOnline = isOnline;
    }

    public byte[] getCle() {
        return cle;
    }

    public void setCle(byte[] cle) {
        this.cle = cle;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", role=" + role + ", adresse=" + adresse + ", motDepasse=" + motDepasse + ", email=" + email + ", telephone=" + telephone + ", commandes=" + commandes + ", cartesPaiement=" + cartesPaiement + ", panier=" + panier + ", isActive=" + isActive + ", isOnline=" + isOnline + ", cle=" + cle + '}';
    }

}
