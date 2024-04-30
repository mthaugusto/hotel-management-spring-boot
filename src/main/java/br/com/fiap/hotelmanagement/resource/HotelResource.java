package br.com.fiap.hotelmanagement.resource;

import br.com.fiap.hotelmanagement.dto.request.HotelRequest;
import br.com.fiap.hotelmanagement.dto.response.HotelResponse;
import br.com.fiap.hotelmanagement.entity.Hotel;
import br.com.fiap.hotelmanagement.service.HotelService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/hotel")
public class HotelResource {

    @Autowired
    private HotelService service;

//    @GetMapping
//    public List<HotelResponse> findAll() {
//        var encontrados = service.findAll();
//        var resposta = encontrados.stream()
//                .map(service::toResponse)
//                .toList();
//        return resposta;
//    }

    @GetMapping
    public ResponseEntity<List<HotelResponse>> findAll(
            @RequestParam(name = "nome", required = false) String nome
    ) {

        var hotel = Hotel.builder()
                .nome(nome)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Hotel> example = Example.of(hotel, matcher);

        var encontrados = service.findAll(example);
        if (encontrados.isEmpty()) {return ResponseEntity.notFound().build();}


        var resposta = encontrados.stream()
                .map(service::toResponse)
                .toList();

        return ResponseEntity.ok(resposta);


    }

    @GetMapping(value="/{id}")
    public ResponseEntity<HotelResponse> findById(@PathVariable Long id) {
        var encontrado = service.findById(id);
        if (encontrado == null) {return ResponseEntity.notFound().build();}
        var resposta = service.toResponse(encontrado);
        return ResponseEntity.ok(resposta);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<HotelResponse> save (@RequestBody @Valid HotelRequest r) {
        var entity = service.toEntity(r);
        var salvo = service.save(entity);
        var resposta = service.toResponse(salvo);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);

    }

}
