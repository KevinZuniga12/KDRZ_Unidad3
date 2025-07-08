package mx.edu.utez.unidad3.modules.warehouse;

import jakarta.persistence.*;
import mx.edu.utez.unidad3.modules.cede.Cede;
import mx.edu.utez.unidad3.modules.client.Client;

@Entity
@Table(name = "warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "clave", nullable = false)
    private String clave;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "sell_price", nullable = false)
    private String sellprice;

    @Column(name = "rent_price", nullable = false)
    private String rentprice;

    @ManyToOne
    @JoinColumn(name = "id_cede", nullable = false)
    private Cede cede;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    public Warehouse() {
    }

    public Warehouse(Long id, String clave, String status, String sellprice, String rentprice, Cede cede, Client client) {
        this.id = id;
        this.clave = clave;
        this.status = status;
        this.sellprice = sellprice;
        this.rentprice = rentprice;
        this.cede = cede;
        this.client = client;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSellprice() {
        return sellprice;
    }

    public void setSellprice(String sellprice) {
        this.sellprice = sellprice;
    }

    public String getRentprice() {
        return rentprice;
    }

    public void setRentprice(String rentprice) {
        this.rentprice = rentprice;
    }

    public Cede getCede() {
        return cede;
    }

    public void setCede(Cede cede) {
        this.cede = cede;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
