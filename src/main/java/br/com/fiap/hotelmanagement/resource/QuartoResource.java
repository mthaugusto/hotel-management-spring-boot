package br.com.fiap.hotelmanagement.resource;

import br.com.fiap.hotelmanagement.dto.request.QuartoRequest;
import br.com.fiap.hotelmanagement.dto.response.QuartoResponse;
import br.com.fiap.hotelmanagement.entity.Quarto;
import br.com.fiap.hotelmanagement.service.QuartoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value="/quarto")
public class QuartoResource {

    @Autowired
    private QuartoService service;

    @GetMapping
    public ResponseEntity<List<QuartoResponse>> findAll(
            @RequestParam(name = "numeroQuarto", required = false) String numeroQuarto
    ) {
        var quarto = Quarto.builder()
                .numeroQuarto(numeroQuarto)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Quarto> example = Example.of(quarto, matcher);

        var encontrados = service.findAll(example);
        if (encontrados.isEmpty()) {return ResponseEntity.notFound().build();}
        var resposta = encontrados.stream().
                map(service::toResponse)
                .toList();
        return ResponseEntity.ok(resposta);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<QuartoResponse> findById(@PathVariable Long id) {
        var encontrado = service.findById(id);
        if (encontrado == null) {return ResponseEntity.notFound().build();}
        var resposta = service.toResponse(encontrado);
        return ResponseEntity.ok(resposta);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<QuartoResponse> save(@RequestBody QuartoRequest r) {
        var entity = service.toEntity(r);
        var salvo = service.save(entity);
        var resposta = service.toResponse(salvo);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(salvo.getId())
                .toUri();

        return ResponseEntity.created(uri).body(resposta);
    }
}
