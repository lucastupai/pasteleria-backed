package cl.duoc.pasteleria.pasteleria_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import cl.duoc.pasteleria.pasteleria_backend.dto.LoginRequest;
import cl.duoc.pasteleria.pasteleria_backend.dto.LoginResponse;
import cl.duoc.pasteleria.pasteleria_backend.model.Usuario;
import cl.duoc.pasteleria.pasteleria_backend.repository.UsuarioRepository;
import cl.duoc.pasteleria.pasteleria_backend.security.JwtUtil;

@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://54.226.254.75"
})
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(UsuarioRepository usuarioRepository,
                          PasswordEncoder passwordEncoder,
                          JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return usuarioRepository.findByUsername(request.getUsername())
                .map(usuario -> {
                    if (passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
                        String token = jwtUtil.generateToken(usuario.getUsername(), usuario.getRol());
                        LoginResponse response = new LoginResponse(
                                token,
                                usuario.getUsername(),
                                usuario.getRol()
                        );
                        return ResponseEntity.ok(response);
                    } else {
                        return ResponseEntity.status(401).body("Credenciales inválidas");
                    }
                })
                .orElse(ResponseEntity.status(401).body("Credenciales inválidas"));
    }
}
