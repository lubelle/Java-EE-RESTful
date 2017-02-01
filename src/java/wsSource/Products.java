package wsSource;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "PRODUCTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Products.findAll", query = "SELECT p FROM Products p"),
    @NamedQuery(name = "Products.findByPid", query = "SELECT p FROM Products p WHERE p.pid = :pid"),
    @NamedQuery(name = "Products.findByPname", query = "SELECT p FROM Products p WHERE p.pname = :pname"),
    @NamedQuery(name = "Products.findByPqty", query = "SELECT p FROM Products p WHERE p.pqty = :pqty"),
    @NamedQuery(name = "Products.findByPprice", query = "SELECT p FROM Products p WHERE p.pprice = :pprice")})
public class Products implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "PID")
    private String pid;
    @Size(max = 20)
    @Column(name = "PNAME")
    private String pname;
    @Column(name = "PQTY")
    private Integer pqty;
    // @Max(value=?)  @Min(value=?)
    @Column(name = "PPRICE")
    private Double pprice;

    public Products() {
    }

    public Products(String pid) {
        this.pid = pid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getPqty() {
        return pqty;
    }

    public void setPqty(Integer pqty) {
        this.pqty = pqty;
    }

    public Double getPprice() {
        return pprice;
    }

    public void setPprice(Double pprice) {
        this.pprice = pprice;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pid != null ? pid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Products)) {
            return false;
        }
        Products other = (Products) object;
        if ((this.pid == null && other.pid != null) || (this.pid != null && !this.pid.equals(other.pid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{\"pid\":\"" + pid + "\"," +
                "\"pname\":\"" + pname + "\","+
                "\"pprice\":" + pprice + ","+
                "\"pqty\":" + pqty + "}";
    }
    
}
