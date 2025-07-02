package utez.edu.mx.unidad3.modules.cede;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import utez.edu.mx.unidad3.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cede")
@Tag(name = "Controlador de Cedes", description = "Operaciones relacionadas con cedes")
public class CedeController {

    @Autowired
    private CedeService cedeService;

    @GetMapping("")
    @Operation(summary = "Listar todas las cedes", description = "Obtiene todas las cedes registradas en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cedes encontradas", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class))
            })
    })
    public ResponseEntity<APIResponse> findAll() {
        APIResponse response = cedeService.findAll();
        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar cede por ID", description = "Obtiene una cede específica mediante su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cede encontrada", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class))
            }),
            @ApiResponse(responseCode = "404", description = "Cede no encontrada", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class))
            })
    })
    public ResponseEntity<APIResponse> findById(@PathVariable("id") Long id) {
        APIResponse response = cedeService.findById(id);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PostMapping("")
    @Operation(summary = "Registrar nueva cede", description = "Agrega una nueva cede al sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cede registrada exitosamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class))
            })
    })
    public ResponseEntity<APIResponse> saveCede(@Valid @RequestBody Cede payload) {
        APIResponse response = cedeService.saveCede(payload);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
