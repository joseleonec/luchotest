package co.com.sofka.luchotest.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Domain model representing a Client.
 * This is a pure POJO without any framework dependencies.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Cliente extends Persona {
    
    private String clienteId;
    private String contrasena;
    private String estado;
}
