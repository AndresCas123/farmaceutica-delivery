package co.edu.javeriana.farmaceutica.delivery.api;

import co.edu.javeriana.farmaceutica.delivery.message.AuthRequest;
import co.edu.javeriana.farmaceutica.delivery.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "autenticacion", description = "API para autenticacion de usuarios.", tags = { "autenticacion" })
@RequestMapping("${app.context-api}/auth")
@RestController
@Slf4j
@AllArgsConstructor
public class AuthApiController {
    private final AuthService authService;

    @ApiOperation(value = "Autenticacion de usuarios.",
            nickname = "auth",
            notes = "Autenticacion de usuarios.",
            tags = { "autenticacion" })
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200, message = "Token de autenticacion."),
            @ApiResponse(
                    code = 401, message = "Usuario o clave invalida.")
    })
    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody AuthRequest authRequest) {
        return authService.login(authRequest).map(res -> {
            return new ResponseEntity<>(res, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
    }
}
