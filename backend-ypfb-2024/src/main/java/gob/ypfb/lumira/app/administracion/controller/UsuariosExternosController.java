package gob.ypfb.lumira.app.administracion.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("lumira/api/v1/login")
@Slf4j
@Tag(name = "Login - Regitro ", description = " Login y Registro de usuario externo")
public abstract class UsuariosExternosController {

}
