package desafio.forohub.api.domain.topico;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        Boolean status,
        String Autor,
        String Curso
) {
}