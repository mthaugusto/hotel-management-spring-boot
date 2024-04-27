package br.com.fiap.hotelmanagement.resource;

import br.com.fiap.hotelmanagement.dto.request.HotelRequest;
import br.com.fiap.hotelmanagement.dto.response.HotelResponse;
import br.com.fiap.hotelmanagement.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/hotel")
public class HotelResource {

    @Autowired
    private HotelService service;

    @GetMapping
    public List<HotelResponse> findAll() {
        var encontrados = service.findAll();
        var resposta = encontrados.stream()
                .map(service::toResponse)
                .toList();
        return resposta;
    }

    @GetMapping(value="/{id}")
    public HotelResponse findById(@PathVariable Long id) {
        var encontrado = service.findById(id);
        var resposta = service.toResponse(encontrado);
        return resposta;
    }

    @Transactional
    @PostMapping
    public HotelResponse save (@RequestBody HotelRequest r) {
        var entity = service.toEntity(r);
        var salvo = service.save(entity);
        return service.toResponse(salvo);

    }

}
