package com.codigo.retrofit.service;

import com.codigo.retrofit.aggregates.response.ReniecResponse;
import com.codigo.retrofit.entity.PersonEntity;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface PersonService {

    // Mantienes el metodo existente para Retrofit
    ReniecResponse findByDni(String dni) throws IOException;

    // Métodos para el CRUD
    PersonEntity create(PersonEntity person);

    List<PersonEntity> getAll();

    Optional<PersonEntity> getById(Long id);

    PersonEntity update(Long id, PersonEntity person);

    void delete(Long id);
}

