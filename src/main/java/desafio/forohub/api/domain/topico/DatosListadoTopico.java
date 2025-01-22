package desafio.forohub.api.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosListadoTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull String autor,
        @NotNull String curso
) {
    public DatosListadoTopico(Topico topico) {
        this(
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getAutor().getNombre(),
                topico.getCurso().getCurso()
        );
    }
}

