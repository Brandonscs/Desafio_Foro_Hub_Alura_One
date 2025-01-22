package desafio.forohub.api.domain.curso;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

@Table(name = "cursos")
@Entity(name = "curso")
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String curso;
    private String categoria;

    public Curso() {
    }

    public Long getId() {
        return id;
    }

    public String getCurso() {
        return curso;
    }

    public String getCategoria() {
        return categoria;
    }
}
