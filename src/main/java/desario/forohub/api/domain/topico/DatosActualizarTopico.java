package desario.forohub.api.domain.topico;

import java.time.LocalDateTime;

public record DatosActualizarTopico(String titulo, String mensaje, LocalDateTime fecha, Boolean status) {
}
