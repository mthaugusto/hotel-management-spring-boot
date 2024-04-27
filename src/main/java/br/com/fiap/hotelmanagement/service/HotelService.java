package br.com.fiap.hotelmanagement.service;

import br.com.fiap.hotelmanagement.dto.request.HotelRequest;
import br.com.fiap.hotelmanagement.dto.response.HotelResponse;
import br.com.fiap.hotelmanagement.entity.Hotel;
import br.com.fiap.hotelmanagement.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService implements ServiceDTO<Hotel, HotelRequest, HotelResponse>{

    @Autowired
    private HotelRepository repo;

    @Override
    public Hotel toEntity(HotelRequest r) {
        return Hotel.builder()
                .nome(r.nome())
                .descricao(r.descricao())
                .endereco((r.endereco()))
                .avaliacao(r.avaliacao())
                .build();
    }

    @Override
    public HotelResponse toResponse(Hotel e) {
        return HotelResponse.builder()
                .id(e.getId())
                .nome(e.getNome())
                .descricao(e.getDescricao())
                .endereco(e.getEndereco())
                .avaliacao(e.getAvaliacao())
                .build();
    }

    @Override
    public List<Hotel> findAll() {
        return repo.findAll();
    }

    @Override
    public List<Hotel> findAll(Example<Hotel> example) {
        return repo.findAll(example);
    }

    @Override
    public Hotel findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Hotel save(Hotel e) {
        return repo.save(e);
    }
}
