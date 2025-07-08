package mx.edu.utez.unidad3.modules.cede;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.edu.utez.unidad3.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cede")
@Tag(name = "Controlador de Cede" , description = "Operaciones realizadas con las Cedes")
@SecurityRequirement(name = "bearerAuth")
public class CedeController {
    @Autowired
    private CedeService cedeService;

    @GetMapping
    @Operation(summary = "Obtencion de todos las Cedes", description = "Mandar a traer todos las cedes registrados en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Obtencion de todos las Cedes",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = APIResponse.class))

                    }
            )
    })
    public ResponseEntity<APIResponse> findAll(){
        APIResponse response = cedeService.findAll();
                return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtencion de una sola Cede", description = "Mandar a traer una sola Cede con uso del id dentro del sistema.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Obtencion de una sola Cede",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = APIResponse.class))

                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se logro encontrar la Cede",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = APIResponse.class))

                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "No se pudo consultar la Cede",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = APIResponse.class))

                    }
            )
    })
    public ResponseEntity<APIResponse> findById(@PathVariable("id") Long id){
        APIResponse response = cedeService.findById(id);
        return  new ResponseEntity<>(response, response.getStatus());
    }

    @PostMapping
    @Operation(summary = "Crear una nueva Cede", description = "Creacion de una nueva Cede dentro del sistema.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "REGISTRO exitoso de una nueva Cede",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = APIResponse.class))

                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "No se pudo GUARDAR la Cede",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = APIResponse.class))

                    }
            )
    })
    public ResponseEntity<APIResponse> save(@RequestBody Cede payload){
        APIResponse response = cedeService.save(payload);
                return new ResponseEntity<>(response, response.getStatus());
    }

    @PutMapping
    @Operation(summary = "Actualizar una Cede", description = "Actualizacion de una Cede dentro del sistema.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Actualizacion exitosa de la Cede",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = APIResponse.class))

                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "No se pudo Actualizar la Cede",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = APIResponse.class))

                    }
            )
    })
    public ResponseEntity<APIResponse> update(@RequestBody Cede payload){
        APIResponse response = cedeService.update(payload);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @DeleteMapping
    @Operation(summary = "Eliminar una Cede", description = "Eliminación de una Cede dentro del sistema.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "ELIMINACION exitoso de la Cede",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = APIResponse.class))

                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "No se pudo Eliminar la Cede",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = APIResponse.class))

                    }
            )
    })
    public ResponseEntity<APIResponse> delete(@RequestParam Cede payload){
        APIResponse response = cedeService.remove(payload);
        return new ResponseEntity<>(response, response.getStatus());
    }

}
