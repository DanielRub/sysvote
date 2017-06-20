/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "electeur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Electeur.findAll", query = "SELECT e FROM Electeur e"),
    @NamedQuery(name = "Electeur.findByIdElecteur", query = "SELECT e FROM Electeur e WHERE e.idElecteur = :idElecteur"),
    @NamedQuery(name = "Electeur.findByNomElecteur", query = "SELECT e FROM Electeur e WHERE e.nomElecteur = :nomElecteur"),
    @NamedQuery(name = "Electeur.findByPostnomElecteur", query = "SELECT e FROM Electeur e WHERE e.postnomElecteur = :postnomElecteur"),
    @NamedQuery(name = "Electeur.findByPrenomElecteur", query = "SELECT e FROM Electeur e WHERE e.prenomElecteur = :prenomElecteur"),
    @NamedQuery(name = "Electeur.findByGenreElecteur", query = "SELECT e FROM Electeur e WHERE e.genreElecteur = :genreElecteur"),
    @NamedQuery(name = "Electeur.findByFaculte", query = "SELECT e FROM Electeur e WHERE e.faculte = :faculte"),
    @NamedQuery(name = "Electeur.findByPromotion", query = "SELECT e FROM Electeur e WHERE e.promotion = :promotion"),
    @NamedQuery(name = "Electeur.findByEmpreinte", query = "SELECT e FROM Electeur e WHERE e.empreinte = :empreinte"),
    @NamedQuery(name = "Electeur.findByPhotoName", query = "SELECT e FROM Electeur e WHERE e.photoName = :photoName")})
public class Electeur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_electeur")
    private Integer idElecteur;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nom_electeur")
    private String nomElecteur;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "postnom_electeur")
    private String postnomElecteur;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "prenom_electeur")
    private String prenomElecteur;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "genre_electeur")
    private String genreElecteur;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "faculte")
    private String faculte;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "promotion")
    private String promotion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "empreinte")
    private String empreinte;
    @Basic(optional = false)
    @Size(min = 1, max = 30)
    @Column(name = "photo_name")
    private String photoName;
    @JoinColumn(name = "id_candidat_vote", referencedColumnName = "id_candidat")
    @ManyToOne
    private Candidat idCandidatVote;

    public Electeur() {
    }

    public Electeur(Integer idElecteur) {
        this.idElecteur = idElecteur;
    }

    public Electeur(Integer idElecteur, String nomElecteur, String postnomElecteur, String prenomElecteur, String genreElecteur, String faculte, String promotion, String photoName, String empreinte) {
        this.idElecteur = idElecteur;
        this.nomElecteur = nomElecteur;
        this.postnomElecteur = postnomElecteur;
        this.prenomElecteur = prenomElecteur;
        this.genreElecteur = genreElecteur;
        this.faculte = faculte;
        this.promotion = promotion;
        this.empreinte = empreinte;
        this.photoName = photoName;
    }

    public Integer getIdElecteur() {
        return idElecteur;
    }

    public void setIdElecteur(Integer idElecteur) {
        this.idElecteur = idElecteur;
    }

    public String getNomElecteur() {
        return nomElecteur;
    }
 public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }
    public void setNomElecteur(String nomElecteur) {
        this.nomElecteur = nomElecteur;
    }

    public String getPostnomElecteur() {
        return postnomElecteur;
    }

    public void setPostnomElecteur(String postnomElecteur) {
        this.postnomElecteur = postnomElecteur;
    }

    public String getPrenomElecteur() {
        return prenomElecteur;
    }

    public void setPrenomElecteur(String prenomElecteur) {
        this.prenomElecteur = prenomElecteur;
    }

    public String getGenreElecteur() {
        return genreElecteur;
    }

    public void setGenreElecteur(String genreElecteur) {
        this.genreElecteur = genreElecteur;
    }

    public String getFaculte() {
        return faculte;
    }

    public void setFaculte(String faculte) {
        this.faculte = faculte;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getEmpreinte() {
        return empreinte;
    }

    public void setEmpreinte(String empreinte) {
        this.empreinte = empreinte;
    }

    public Candidat getIdCandidatVote() {
        return idCandidatVote;
    }

    public void setIdCandidatVote(Candidat idCandidatVote) {
        this.idCandidatVote = idCandidatVote;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idElecteur != null ? idElecteur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Electeur)) {
            return false;
        }
        Electeur other = (Electeur) object;
        if ((this.idElecteur == null && other.idElecteur != null) || (this.idElecteur != null && !this.idElecteur.equals(other.idElecteur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sysvote.Electeur[ idElecteur=" + idElecteur + " ]" + getNomElecteur() + " " + getPrenomElecteur();
    }

}
