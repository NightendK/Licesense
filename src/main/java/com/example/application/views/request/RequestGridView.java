package com.example.application.views.request;

import com.example.application.backend.Model.RequestModel;
import com.example.application.backend.Service.RequestService;
import com.vaadin.flow.component.crud.Crud;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.impl.GridCrud;

@PageTitle("Request Grid")
@Route("/requestGrid")
public class RequestGridView extends VerticalLayout {
    private final RequestService requestService;
    RequestGridView(RequestService requestService) {
        this.requestService = requestService;

        setSizeFull();

        GridCrud<RequestModel> grid = new GridCrud<>(RequestModel.class, this.requestService);
        grid.setSizeFull();

        grid.setAddOperationVisible(false);
        grid.setUpdateOperationVisible(false);
        grid.setDeleteOperationVisible(true);

        add(grid);
    }
}
