package com.example.application.backend.Service;

import com.example.application.backend.Model.Person;
import com.example.application.backend.Repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;
import java.util.Collection;
@Service
@RequiredArgsConstructor
public class PersonService implements CrudListener<Person> {

    private final PersonRepository personRepository;

    @Override
    public Collection<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Person add(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person update(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void delete(Person person) {
        personRepository.delete(person);
    }
}
