package desafio.forohub.api.domain.usuario;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

@Table(name = "usuarios")
@Entity(name = "usuario")
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String clave;

    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getClave() {
        return clave;
    }
}
