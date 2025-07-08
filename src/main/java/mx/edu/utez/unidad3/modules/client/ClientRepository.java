package mx.edu.utez.unidad3.modules.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository <Client, Long>{
    List<Client> findAll();
    Optional<Client> findById(Long id);
    Client save(Client client); //Guradar y Actualizar

    @Modifying
    @Query(value  = "", nativeQuery = true)
    void deleteClientById(@Param("id")Long id);



}
