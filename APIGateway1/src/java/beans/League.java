/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
 * @author PillerD
 */
@Entity
@Table(name = "t_league")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "League.findAll", query = "SELECT l FROM League l"),
    @NamedQuery(name = "League.findByPKLeague", query = "SELECT l FROM League l WHERE l.pKLeague = :pKLeague"),
    @NamedQuery(name = "League.findByName", query = "SELECT l FROM League l WHERE l.name = :name"),
    @NamedQuery(name = "League.findByCountryCode", query = "SELECT l FROM League l WHERE l.countryCode = :countryCode")})
public class League implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_League")
    private Integer pKLeague;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Country_Code")
    private String countryCode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKLeague")
    private List<Team> teamList;

    public League() {
    }

    public League(Integer pKLeague) {
        this.pKLeague = pKLeague;
    }

    public League(Integer pKLeague, String name, String countryCode) {
        this.pKLeague = pKLeague;
        this.name = name;
        this.countryCode = countryCode;
    }

    public Integer getPKLeague() {
        return pKLeague;
    }

    public void setPKLeague(Integer pKLeague) {
        this.pKLeague = pKLeague;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @XmlTransient
    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pKLeague != null ? pKLeague.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof League)) {
            return false;
        }
        League other = (League) object;
        if ((this.pKLeague == null && other.pKLeague != null) || (this.pKLeague != null && !this.pKLeague.equals(other.pKLeague))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.League[ pKLeague=" + pKLeague + " ]";
    }
    
}
