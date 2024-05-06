package br.com.fiap.hotelmanagement.service;

import br.com.fiap.hotelmanagement.dto.request.QuartoRequest;
import br.com.fiap.hotelmanagement.dto.response.QuartoResponse;
import br.com.fiap.hotelmanagement.entity.Quarto;
import br.com.fiap.hotelmanagement.repository.QuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuartoService implements ServiceDTO<Quarto, QuartoRequest, QuartoResponse> {

    @Autowired
    private QuartoRepository repo;

    @Autowired
    private HotelService hotelService;

    @Override
    public Quarto toEntity(QuartoRequest r) {

        var hotel = hotelService.findById(r.hotelId());

        return Quarto.builder()
                .tipoQuarto(r.tipoQuarto())
                .numeroQuarto(r.numeroQuarto())
                .precoPorNoite(r.precoPorNoite())
                .hotel(hotel)
                .build();
    }

    @Override
    public QuartoResponse toResponse(Quarto e) {

        var hotelResponse = hotelService.toResponse(e.getHotel());

        return QuartoResponse.builder()
                .tipoQuarto(e.getTipoQuarto())
                .numeroQuarto(e.getNumeroQuarto())
                .precoPorNoite(e.getPrecoPorNoite())
                .hotel(hotelResponse)
                .build();
    }

    @Override
    public List<Quarto> findAll() {
        return repo.findAll();
    }

    @Override
    public List<Quarto> findAll(Example<Quarto> example) {
        return repo.findAll(example);
    }

    @Override
    public Quarto findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Quarto save(Quarto e) {
        return repo.save(e);
    }
}
