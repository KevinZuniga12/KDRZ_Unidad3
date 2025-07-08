package mx.edu.utez.unidad3.modules.user;

//NOTA: Este paso ya va implicito en la cracion del proyecto

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<BeanUser,Long> {
    Optional<BeanUser> findByUsernameAndPassword(String username, String password);
    Optional<BeanUser> findByUsername(String username);


}
