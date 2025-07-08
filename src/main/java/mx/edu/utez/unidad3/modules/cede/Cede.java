package mx.edu.utez.unidad3.modules.cede;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import mx.edu.utez.unidad3.modules.warehouse.Warehouse;

import java.util.List;

@Entity
@Table(name = "cede")
public class Cede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "clave", nullable = false)
    private String clave;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "city", nullable = false)
    private String city;

    @OneToMany(mappedBy = "cede")
    @JsonIgnore
    private List<Warehouse> warehouses;

    public Cede() {
    }

    public Cede(Long id, String clave, String phone, String city, List<Warehouse> warehouses) {
        this.id = id;
        this.clave = clave;
        this.state = phone;
        this.city = city;
        this.warehouses = warehouses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }
}
