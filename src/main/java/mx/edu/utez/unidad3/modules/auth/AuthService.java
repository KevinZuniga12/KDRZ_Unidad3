package mx.edu.utez.unidad3.modules.auth;

import mx.edu.utez.unidad3.modules.auth.dto.LoginRequestDTO;
import mx.edu.utez.unidad3.modules.user.BeanUser;
import mx.edu.utez.unidad3.modules.user.UserRepository;
import mx.edu.utez.unidad3.security.jwt.JWTUtils;
import mx.edu.utez.unidad3.security.jwt.UDService;
import mx.edu.utez.unidad3.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UDService udService;

    @Autowired
    private JWTUtils  jwtUtils;

    @Transactional(readOnly = true)
    public APIResponse doLogin(LoginRequestDTO payload) {
        BeanUser found = userRepository.findByUsernameAndPassword(
                payload.getUsername(),
                payload.getPassword()
        ).orElse(null);
        try {
            if (found == null) {
                return new APIResponse(
                        "Usuario no encontrado",
                        true,
                        HttpStatus.INTERNAL_SERVER_ERROR
                );
            }
            UserDetails ud = udService.loadUserByUsername(found.getUsername());
            String token = jwtUtils.generateToken(ud);
            return new APIResponse(
                    "Operacion Exitosa",
                    token,
                    false,
                    HttpStatus.OK
            );

        }catch(Exception ex){
            ex.printStackTrace();
            return new APIResponse(
                    "Error al crear sesion",
                    true,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
