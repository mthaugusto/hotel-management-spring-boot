package br.com.fiap.hotelmanagement.repository;

import br.com.fiap.hotelmanagement.entity.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Long> {
}
