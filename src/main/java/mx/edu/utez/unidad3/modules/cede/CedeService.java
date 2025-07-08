package mx.edu.utez.unidad3.modules.cede;

import mx.edu.utez.unidad3.utils.APIResponse;
import mx.edu.utez.unidad3.utils.ClaveGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class CedeService {
    @Autowired
    private CedeRepository cedeRepository;

    @Transactional(readOnly = true)
    public APIResponse findAll(){
        List<Cede> list = cedeRepository.findAll();
        return new APIResponse("Operacion exitosa", list,false, HttpStatus.OK);
    }

    @Transactional
    public APIResponse findById(Long id){
        try {
            Cede found = cedeRepository.findById(id).orElse(null);
            if(found == null){
                return new APIResponse("Cede no encontrada", null,false, HttpStatus.NOT_FOUND);

            }
            return new APIResponse("Operacion exitosa", found,false, HttpStatus.OK);

        }catch (Exception ex) {
            return new APIResponse("No se pudo consultar la cede", true, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public APIResponse save(Cede payload){
        try {
            payload.setClave("pending...");
            Cede saved = cedeRepository.save(payload);

            saved.setClave(ClaveGenerator.generateCedeClave(saved.getId()));
            cedeRepository.save(saved);

            return new APIResponse("Opereción exitosa", false, HttpStatus.CREATED);

        } catch (Exception ex) {
            return new APIResponse("No se pudo registrar la cede", true, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public APIResponse update(Cede payload){
        try {
            if(cedeRepository.findById(payload.getId()).isEmpty()){
                return new APIResponse("Cede no encontrada", null,false, HttpStatus.NOT_FOUND);
            }
            cedeRepository.save(payload);
            return new APIResponse("Operacion Exitosa", false, HttpStatus.OK);

        }catch (Exception ex) {
            ex.printStackTrace();
            return new APIResponse("No se pudo actualizar la cede", true, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public APIResponse remove(Cede payload){
        try {
            if(cedeRepository.findById(payload.getId()).isEmpty()){
                return new APIResponse("Cede no encontrada", null,false, HttpStatus.NOT_FOUND);
            }
            cedeRepository.save(payload);
            return new APIResponse("Operacion Exitosa", false, HttpStatus.OK);

        }catch (Exception ex) {
            ex.printStackTrace();
            return new APIResponse("No se pudo eliminar la cede", true, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
