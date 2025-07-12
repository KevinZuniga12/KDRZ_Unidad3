package mx.edu.utez.unidad3.modules.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import mx.edu.utez.unidad3.modules.warehouse.Warehouse;

import java.util.List;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private Long id;


    @Pattern(regexp = "^[a-zA-záéíóúÁÉÍÓÚñÑ][\sa-zA-záéíóúÁÉÍÓÚñÑ]{3,}$", message = "Solo se aceptan letras")
    @NotNull(message = "Ingresa los datos")
    @NotBlank(message = "No puedes dejar este campo vacio")
    @Column(name = "name", nullable = false)
    private String name;

    @Pattern(regexp = "^?[-.\\s]?\\d{3,4}[-.\\s]?\\d{4}$\n", message = "Solo se aceptan numeros")
    @NotNull(message = "Ingresa los datos")
    @NotBlank(message = "No puedes dejar este campo vacio")
    @Column(name = "phone", nullable = false)
    private String phone;

    @Email
    @Pattern(regexp = "^[a-zA-záéíóúÁÉÍÓÚñÑ][\sa-zA-záéíóúÁÉÍÓÚñÑ]{3,}$", message = "Coloca un correo valido")
    @NotNull(message = "Ingresa los datos de correo")
    @NotBlank(message = "No puedes dejar este campo vacio")
    @Column(name = "email", nullable = false)
    private String email;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Warehouse> warehouseList;

    public Client(){}

    public Client(Long id, String name, String phone, String email, List<Warehouse> warehouseList) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.warehouseList = warehouseList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Warehouse> getWarehouseList() {
        return warehouseList;
    }

    public void setWarehouseList(List<Warehouse> warehouseList) {
        this.warehouseList = warehouseList;
    }
}
