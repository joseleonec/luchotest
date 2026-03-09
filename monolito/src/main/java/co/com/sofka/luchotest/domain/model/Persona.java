package co.com.sofka.luchotest.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain model representing a Person.
 * This is a pure POJO without any framework dependencies.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Persona {
    
    private Long id;
    private String nombre;
    private String genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}
