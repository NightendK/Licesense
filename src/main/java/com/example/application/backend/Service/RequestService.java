package com.example.application.backend.Service;

import com.example.application.backend.Model.RequestModel;
import com.example.application.backend.Repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;
@Service
@RequiredArgsConstructor
public class RequestService implements CrudListener<RequestModel> {
   private final RequestRepository requestRepository;


    @Override
    public Collection<RequestModel> findAll() {
        return requestRepository.findAll();
    }

    @Override
    public RequestModel add(RequestModel requestModel) {
       return requestRepository.save(requestModel);
    }

    @Override
    public RequestModel update(RequestModel requestModel) {
        return requestRepository.save(requestModel);
    }

    @Override
    public void delete(RequestModel requestModel) {
        requestRepository.delete(requestModel);
    }
}
