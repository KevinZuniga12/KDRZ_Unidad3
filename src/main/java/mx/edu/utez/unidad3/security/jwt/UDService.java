package mx.edu.utez.unidad3.security.jwt;

import mx.edu.utez.unidad3.modules.user.BeanUser;
import mx.edu.utez.unidad3.modules.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

//Tercer paso: Crear nuestro servicoo de gestion de autoridades
@Service
public class UDService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BeanUser found = userRepository.findByUsername(username).orElse(null);
        if (found == null) throw new UsernameNotFoundException("Usuario no encontrado");

        //Genera las autoridades para el contesxto de seguridad
        //authority = ROLE_ADMIN, ROLE_EMPLOYEE
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + found.getRol().getName());

        //Retornar el objeto de usiuario para registrar en el contexto de seguridad
        return new User(
                found.getUsername(),
                found.getPassword(),
                Collections.singleton(authority)

        );
    }
}
