package com.example.application.views.grid;

import com.example.application.backend.Model.Person;
import com.example.application.backend.Model.User;
import com.example.application.backend.Repository.UserRepository;
import com.example.application.backend.Service.PersonService;
import com.example.application.backend.Service.UserService;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;


@PageTitle("GridView")
@Route("/grid")
public class GridView extends HorizontalLayout {
    private static PersonService personService;
    private static UserService userService;
    public GridView(PersonService personService, UserService userService) {

        this.personService = personService;
        this.userService = userService;
        setSizeFull();

        GridCrud<Person> personGrid = new GridCrud<>(Person.class, this.personService);
        personGrid.setSizeFull();

        personGrid.getGrid().setColumns("firstName", "lastName", "email", "sex", "race", "shirtSize");
        personGrid.setUpdateOperationVisible(true);
        personGrid.setDeleteOperationVisible(true);
        personGrid.setAddOperationVisible(false);

        GridCrud<User> userGrid = new GridCrud<>(User.class, this.userService);
        userGrid.setSizeFull();

        userGrid.getGrid().setColumns("username");
        userGrid.setUpdateOperationVisible(true);
        userGrid.setDeleteOperationVisible(true);
        userGrid.setAddOperationVisible(false);

        add(personGrid, userGrid);


    }
}
