/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "candidat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Candidat.findAll", query = "SELECT c FROM Candidat c"),
    @NamedQuery(name = "Candidat.findByIdCandidat", query = "SELECT c FROM Candidat c WHERE c.idCandidat = :idCandidat"),
    @NamedQuery(name = "Candidat.findByNomCandidat", query = "SELECT c FROM Candidat c WHERE c.nomCandidat = :nomCandidat"),
    @NamedQuery(name = "Candidat.findByPostnomCandidat", query = "SELECT c FROM Candidat c WHERE c.postnomCandidat = :postnomCandidat"),
    @NamedQuery(name = "Candidat.findByPrenomCandidat", query = "SELECT c FROM Candidat c WHERE c.prenomCandidat = :prenomCandidat"),
    @NamedQuery(name = "Candidat.findByGenreCandidat", query = "SELECT c FROM Candidat c WHERE c.genreCandidat = :genreCandidat"),
    @NamedQuery(name = "Candidat.findByFaculte", query = "SELECT c FROM Candidat c WHERE c.faculte = :faculte"),
    @NamedQuery(name = "Candidat.findByPromotion", query = "SELECT c FROM Candidat c WHERE c.promotion = :promotion"),
    @NamedQuery(name = "Candidat.findByTypeCandidat", query = "SELECT c FROM Candidat c WHERE c.typeCandidat = :typeCandidat"),
    @NamedQuery(name = "Candidat.findByTypePhotoName", query = "SELECT c FROM Candidat c WHERE c.photoName = :photoName")})
public class Candidat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_candidat")
    private Integer idCandidat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nom_candidat")
    private String nomCandidat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "postnom_candidat")
    private String postnomCandidat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "prenom_candidat")
    private String prenomCandidat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "genre_candidat")
    private String genreCandidat;
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
    @Size(min = 1, max = 30)
    @Column(name = "type_candidat")
    private String typeCandidat;
    @Basic(optional = false)
    @Size(min = 1, max = 30)
    @Column(name = "photo_name")
    private String photoName;
    @OneToMany(mappedBy = "idCandidatVote")
    private Collection<Electeur> electeurCollection;

    public Candidat() {
    }

    public Candidat(Integer idCandidat) {
        this.idCandidat = idCandidat;
    }

    public Candidat(Integer idCandidat, String nomCandidat, String postnomCandidat, String prenomCandidat, String genreCandidat, String faculte, String promotion, String typeCandidat, String photoName) {
        this.idCandidat = idCandidat;
        this.nomCandidat = nomCandidat;
        this.postnomCandidat = postnomCandidat;
        this.prenomCandidat = prenomCandidat;
        this.genreCandidat = genreCandidat;
        this.faculte = faculte;
        this.promotion = promotion;
        this.typeCandidat = typeCandidat;
        this.photoName = photoName;
    }

    public Integer getIdCandidat() {
        return idCandidat;
    }

    public void setIdCandidat(Integer idCandidat) {
        this.idCandidat = idCandidat;
    }

    public String getNomCandidat() {
        return nomCandidat;
    }

    public void setNomCandidat(String nomCandidat) {
        this.nomCandidat = nomCandidat;
    }

    public String getPostnomCandidat() {
        return postnomCandidat;
    }

    public void setPostnomCandidat(String postnomCandidat) {
        this.postnomCandidat = postnomCandidat;
    }

    public String getPrenomCandidat() {
        return prenomCandidat;
    }

    public void setPrenomCandidat(String prenomCandidat) {
        this.prenomCandidat = prenomCandidat;
    }

    public String getGenreCandidat() {
        return genreCandidat;
    }

    public void setGenreCandidat(String genreCandidat) {
        this.genreCandidat = genreCandidat;
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

    public String getTypeCandidat() {
        return typeCandidat;
    }

    public void setTypeCandidat(String typeCandidat) {
        this.typeCandidat = typeCandidat;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    @XmlTransient
    public Collection<Electeur> getElecteurCollection() {
        return electeurCollection;
    }

    public void setElecteurCollection(Collection<Electeur> electeurCollection) {
        this.electeurCollection = electeurCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCandidat != null ? idCandidat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Candidat)) {
            return false;
        }
        Candidat other = (Candidat) object;
        if ((this.idCandidat == null && other.idCandidat != null) || (this.idCandidat != null && !this.idCandidat.equals(other.idCandidat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sysvote.Candidat[ idCandidat=" + idCandidat + " ]";
    }

}
