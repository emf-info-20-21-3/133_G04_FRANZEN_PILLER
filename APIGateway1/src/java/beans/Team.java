/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

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
 * @author PillerD
 */
@Entity
@Table(name = "t_team")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Team.findAll", query = "SELECT t FROM Team t"),
    @NamedQuery(name = "Team.findByPKTeam", query = "SELECT t FROM Team t WHERE t.pKTeam = :pKTeam"),
    @NamedQuery(name = "Team.findByName", query = "SELECT t FROM Team t WHERE t.name = :name"),
    @NamedQuery(name = "Team.findByCode", query = "SELECT t FROM Team t WHERE t.code = :code")})
public class Team implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_Team")
    private Integer pKTeam;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "Code")
    private String code;
    @JoinColumn(name = "FK_League", referencedColumnName = "PK_League")
    @ManyToOne(optional = false)
    private League fKLeague;

    public Team() {
    }

    public Team(Integer pKTeam) {
        this.pKTeam = pKTeam;
    }

    public Team(Integer pKTeam, String name, String code) {
        this.pKTeam = pKTeam;
        this.name = name;
        this.code = code;
    }

    public Integer getPKTeam() {
        return pKTeam;
    }

    public void setPKTeam(Integer pKTeam) {
        this.pKTeam = pKTeam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public League getFKLeague() {
        return fKLeague;
    }

    public void setFKLeague(League fKLeague) {
        this.fKLeague = fKLeague;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pKTeam != null ? pKTeam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Team)) {
            return false;
        }
        Team other = (Team) object;
        if ((this.pKTeam == null && other.pKTeam != null) || (this.pKTeam != null && !this.pKTeam.equals(other.pKTeam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Team[ pKTeam=" + pKTeam + " ]";
    }
    
}
