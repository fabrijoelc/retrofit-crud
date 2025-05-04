package com.codigo.retrofit.controller;

import com.codigo.retrofit.aggregates.response.ReniecResponse;
import com.codigo.retrofit.entity.PersonEntity;
import com.codigo.retrofit.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    // Retrofit || CRUD
    @GetMapping("/find/{dni}")
    public ResponseEntity<ReniecResponse> findPerson(@PathVariable String dni) throws IOException {
        return new ResponseEntity<>(personService.findByDni(dni), HttpStatus.OK);
    }

    // Crear -persona
    @PostMapping
    public ResponseEntity<PersonEntity> createPerson(@RequestBody PersonEntity person) {
        return ResponseEntity.ok(personService.create(person));
    }

    // Listar -personas
    @GetMapping
    public ResponseEntity<List<PersonEntity>> getAllPersons() {
        return ResponseEntity.ok(personService.getAll());
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<PersonEntity> getById(@PathVariable Long id) {
        return personService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar -persona
    @PutMapping("/{id}")
    public ResponseEntity<PersonEntity> updatePerson(
            @PathVariable Long id,
            @RequestBody PersonEntity person) {
        return ResponseEntity.ok(personService.update(id, person));
    }

    // Eliminar -persona
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

