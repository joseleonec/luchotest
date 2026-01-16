package co.com.sofka.luchotest.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cliente")
public class ClienteEntity extends PersonaEntity {

    private String clienteId;

    private String contrasena;

    private String estado;
}
