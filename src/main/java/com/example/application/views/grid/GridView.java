package com.example.application.views.grid;

import com.example.application.backend.Model.Person;
import com.example.application.backend.Model.User;
import com.example.application.backend.Repository.UserRepository;
import com.example.application.backend.Service.PersonService;
import com.example.application.backend.Service.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;


@PageTitle("Grid")
@Route("/grid")
public class GridView extends HorizontalLayout {
    private static PersonService personService;
    private static UserService userService;
    private UserRepository userRepository;
    private String sessionUsername;
    private User user;
    public GridView(PersonService personService, UserService userService, UserRepository userRepository) {

        this.personService = personService;
        this.userService = userService;
        this.userRepository = userRepository;
        setSizeFull();

        GridCrud<Person> personGrid = new GridCrud<>(Person.class, this.personService);
        personGrid.setSizeFull();

        VaadinSession session = VaadinSession.getCurrent();

        sessionUsername =  session.getAttribute("username").toString();
        user = userRepository.getByUsername(sessionUsername);
        if (user == null) {
            Notification.show("User is NULL!!!");
            UI.getCurrent().close();
        }
        String userId = user.getId().toString();

        personGrid.getGrid().setColumns("id","firstName", "lastName", "email", "sex", "race", "shirtSize");
        personGrid.setUpdateOperationVisible(true);
        personGrid.setDeleteOperationVisible(true);
        personGrid.setAddOperationVisible(false);

        GridCrud<User> userGrid = new GridCrud<>(User.class, this.userService);
        userGrid.setSizeFull();

        userGrid.getGrid().setColumns("username", "id");
        userGrid.setUpdateOperationVisible(true);
        userGrid.setDeleteOperationVisible(true);
        userGrid.setAddOperationVisible(false);

        add(personGrid, userGrid);


    }
}
