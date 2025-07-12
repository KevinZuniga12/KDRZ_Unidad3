package mx.edu.utez.unidad3.modules.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    @Column(name = "id", nullable = false)
    private Long id;

    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚÑñ][\sa-zA-ZáéíóúÁÉÍÓÚÑñ]{2,}$", message = "Solo se aceptan letras")
    @NotNull(message = "Favor de ingresar los datos en el campo")
    @NotBlank(message = "Favor de no dejar los datos en Blanco")
    @Column(name = "name", nullable = false)
    private String name;

    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚÑñ][\sa-zA-ZáéíóúÁÉÍÓÚÑñ]{2,}$", message = "Solo se aceptan letras")
    @NotNull(message = "Favor de ingresar los datos en el campo")
    @NotBlank(message = "Favor de no dejar los datos en Blanco")
    @Column(name = "phone", nullable = false)
    private String phone;

    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚÑñ][\sa-zA-ZáéíóúÁÉÍÓÚÑñ]{2,}$", message = "Solo se aceptan letras")
    @NotNull(message = "Favor de ingresar los datos en el campo")
    @NotBlank(message = "Favor de no dejar los datos en Blanco")
    @Column(name = "email", nullable = false)
    private String email;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Warehouse> warehouses;

    public Client() {
    }

    public Client(Long id, String name, String phone, String email, List<Warehouse> warehouses) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.warehouses = warehouses;
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

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }
}
