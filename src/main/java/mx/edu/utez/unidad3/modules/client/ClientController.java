package mx.edu.utez.unidad3.modules.client;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.edu.utez.unidad3.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
@Tag(name = "Controlador de Clientes" , description = "Operaciones realizadas con clientes")
@SecurityRequirement(name = "bearerAuth")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("")
    @Operation(summary = "Obtencion de todos los Clientes", description = "Mandar a traer todos los clientes registrados en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Obtencion de todos los clientes",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = APIResponse.class))

                    }
            )
    })
    public ResponseEntity<APIResponse> findAll() {
        APIResponse response = clientService.findAll();
        return new ResponseEntity<>(response, response.getStatus());
    }



    @GetMapping("/{id}")
    @Operation(summary = "Obtencion de un solo Cliente", description = "Mandar a traer a un solo cliente con uso del id dentro del sistema.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Obtencion de un solo Cliente",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = APIResponse.class))

                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se logro encontrar al Cliente",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = APIResponse.class))

                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "No se pudo consultar al Cliente",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = APIResponse.class))

                    }
            )
    })
    public ResponseEntity<APIResponse> findById(@PathVariable ("id") Long id) {
        APIResponse response = clientService.findById(id);
        return new ResponseEntity<>(response, response.getStatus());
    }



    @PostMapping("")
    @Operation(summary = "Crear un nuevo Cliente", description = "Creacion de un nuevo cliente dentro del sistema.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "REGISTRO exitoso de un Nuevo Cliente",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = APIResponse.class))

                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "No se pudo GUARDAR al Cliente",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = APIResponse.class))

                    }
            )
    })
    public ResponseEntity<APIResponse> save(@RequestBody Client payload) {
        APIResponse response = clientService.save(payload);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
