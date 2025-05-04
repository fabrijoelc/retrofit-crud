package com.codigo.retrofit.service.impl;

import com.codigo.retrofit.aggregates.response.ReniecResponse;
import com.codigo.retrofit.entity.PersonEntity;
import com.codigo.retrofit.repository.PersonRepository;
import com.codigo.retrofit.retrofit.ClientReniecService;
import com.codigo.retrofit.retrofit.ClientRetrofit;
import com.codigo.retrofit.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    ClientReniecService retrofitPreConfig =
            ClientRetrofit.getRetrofit()
                    .create(ClientReniecService.class);

    @Value("${token.api}")
    private String token;

    // retrofit
    @Override
    public ReniecResponse findByDni(String dni) throws IOException {
        Response<ReniecResponse> executeReniec = preparedClient(dni).execute();
        if (executeReniec.isSuccessful() && Objects.nonNull(executeReniec.body())) {
            return executeReniec.body();
        }
        return new ReniecResponse();
    }

    private Call<ReniecResponse> preparedClient(String dni) {
        return retrofitPreConfig.findReniec("Bearer " + token, dni);
    }

    @Override
    public PersonEntity create(PersonEntity person) {
        return personRepository.save(person);
    }

    @Override
    public List<PersonEntity> getAll() {
        return personRepository.findAll();
    }

    @Override
    public Optional<PersonEntity> getById(Long id) {
        return personRepository.findById(id);
    }

    @Override
    public PersonEntity update(Long id, PersonEntity updatedPerson) {
        return personRepository.findById(id).map(person -> {
            person.setNumeroDocumento(updatedPerson.getNumeroDocumento());
            person.setNombres(updatedPerson.getNombres());
            person.setApellidoPaterno(updatedPerson.getApellidoPaterno());
            person.setApellidoMaterno(updatedPerson.getApellidoMaterno());
            return personRepository.save(person);
        }).orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + id));
    }

    @Override
    public void delete(Long id) {
        personRepository.deleteById(id);
    }
}

