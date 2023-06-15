package com.example.application.views.request;

import com.example.application.backend.Model.RequestModel;
import com.example.application.backend.Service.RequestService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("RequestGridView")
@Route("/requestGrid")
public class RequestGridView extends VerticalLayout {
    private final RequestService requestService;
    RequestGridView(RequestService requestService) {
        this.requestService = requestService;

        setSizeFull();

        Grid<RequestModel> grid = new Grid<>(RequestModel.class, false);

        grid.addColumn(RequestModel::getId).setHeader("Request ID");
        grid.addColumn(RequestModel::getFirstName).setHeader("First name");
        grid.addColumn(RequestModel::getLastName).setHeader("Last name");
        grid.addColumn(RequestModel::getEmail).setHeader("Email");
        grid.addColumn(RequestModel::getUsername).setHeader("Username");
        grid.addColumn(RequestModel::getShirtSize).setHeader("ShirtSize");
        grid.addColumn(RequestModel::getUserId).setHeader("User ID");
        grid.addColumn(RequestModel::getPersonId).setHeader("Person ID");

        grid.setItems(requestService.findAll());

        add(grid);
    }
}
