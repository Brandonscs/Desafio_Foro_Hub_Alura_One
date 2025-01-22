package desafio.forohub.api.controller;

import desafio.forohub.api.domain.curso.Curso;
import desafio.forohub.api.domain.curso.CursoRepository;
import desafio.forohub.api.domain.topico.*;
import desafio.forohub.api.domain.usuario.Usuario;
import desafio.forohub.api.domain.usuario.UsuarioRepository;
import desario.forohub.api.domain.topico.DatosActualizarTopico;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;

    public TopicoController(TopicoRepository topicoRepository, UsuarioRepository usuarioRepository, CursoRepository cursoRepository) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder) {

        Usuario autor = usuarioRepository.findById(datosRegistroTopico.autor())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        Curso curso = cursoRepository.findById(datosRegistroTopico.curso())
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));

        Topico topico = new Topico(datosRegistroTopico, autor, curso);
        topicoRepository.save(topico);
        DatosRespuestaTopico datosTopico = new DatosRespuestaTopico(
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFecha(),
                topico.getStatus(),
                topico.getAutor().getNombre(),
                topico.getCurso().getCurso()
        );
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosTopico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> muestraTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tópico no encontrado"));

        DatosRespuestaTopico datosTopico = new DatosRespuestaTopico(
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFecha(),
                topico.getStatus(),
                topico.getAutor().getNombre(),
                topico.getCurso().getCurso()
        );

        return ResponseEntity.ok().body(datosTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> obtenerTopicos(@PageableDefault(size = 2) Pageable pageable) {

        Page<Topico> pageTopicos = topicoRepository.findAll(pageable);

        Page<DatosListadoTopico> datosTopicos = pageTopicos.map(DatosListadoTopico::new);

        return ResponseEntity.ok(datosTopicos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosActualizarTopico> actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosRegistroTopico datosRegistroTopico) {

        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tópico no encontrado"));

        Usuario autor = usuarioRepository.findById(datosRegistroTopico.autor())
                .orElseThrow(() -> new IllegalArgumentException("Autor no encontrado"));
        Curso curso = cursoRepository.findById(datosRegistroTopico.curso())
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));

        topico.setTitulo(datosRegistroTopico.titulo());
        topico.setMensaje(datosRegistroTopico.mensaje());
        topico.setAutor(autor);
        topico.setCurso(curso);

        topicoRepository.save(topico);

        DatosActualizarTopico datosActualizarTopico = new DatosActualizarTopico(
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFecha(),
                topico.getStatus()
        );

        return ResponseEntity.ok(datosActualizarTopico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {

        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tópico no encontrado"));

        topicoRepository.delete(topico);

        return ResponseEntity.noContent().build();
    }

}
