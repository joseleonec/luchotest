package co.com.sofka.msclientes.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "cliente")
public class ClienteEntity extends PersonaEntity {

    @Column(name = "cliente_id", nullable = false, unique = true)
    private String clienteId;

    private String contrasena;

    private String estado;
}
