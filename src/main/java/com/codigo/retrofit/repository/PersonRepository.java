package com.codigo.retrofit.repository;

import com.codigo.retrofit.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    // Consulta personalizada para buscar por número de documento (DNI)
    Optional<PersonEntity> findByNumeroDocumento(String numeroDocumento);
}
