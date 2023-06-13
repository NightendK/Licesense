package com.example.application.backend.Service;

import com.example.application.backend.Enums.Race;
import com.example.application.backend.Enums.Role;
import com.example.application.backend.Enums.Sex;
import com.example.application.backend.Enums.ShirtSize;
import com.example.application.backend.Model.Person;
import com.example.application.backend.Model.User;
import com.example.application.backend.Repository.PersonRepository;
import com.example.application.backend.Repository.UserRepository;
import com.example.application.views.MainLayout;
import com.example.application.views.cursa10km.Cursa10kmView;
import com.example.application.views.cursa21km.Cursa21KmView;
import com.example.application.views.cursa42km.Cursa42KmView;
import com.example.application.views.cursacopii.CursaCopiiView;
import com.example.application.views.grid.GridView;
import com.example.application.views.home.HomeView;
import com.example.application.views.logout.LogoutView;
import com.example.application.views.profile.ProfileView;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Service;



@Service
public class AuthService {
    public class AuthException extends Exception {
    }
    private final UserRepository userRepository;
    private final PersonRepository personRepository;
    private final Person person = new Person();

    public AuthService(UserRepository userRepository, PersonRepository personRepository) {
        this.userRepository = userRepository;
        this.personRepository = personRepository;
    }

    public void authenticate(String username, String password) throws AuthException {
        User user = userRepository.getByUsername(username);
        if(user != null && user.checkPassword(password)) {
            createRoutes(user.getRole());
        } else {
            throw new AuthException();
        }
    }

    public void createRoutes(Role role) {

        if(role.equals(Role.USER)) {
            RouteConfiguration.forSessionScope().setRoute("/home", HomeView.class, MainLayout.class);
            RouteConfiguration.forSessionScope().setRoute("/profile", ProfileView.class, MainLayout.class);
            RouteConfiguration.forSessionScope().setRoute("/42km", Cursa42KmView.class, MainLayout.class);
            RouteConfiguration.forSessionScope().setRoute("/21km", Cursa21KmView.class, MainLayout.class);
            RouteConfiguration.forSessionScope().setRoute("/10km", Cursa10kmView.class, MainLayout.class);
            RouteConfiguration.forSessionScope().setRoute("/copii", CursaCopiiView.class, MainLayout.class);
            RouteConfiguration.forSessionScope().setRoute("/logout", LogoutView.class, MainLayout.class);
        }

        else if (role.equals(Role.ADMIN)) {
            RouteConfiguration.forSessionScope().setRoute("/home", HomeView.class, MainLayout.class);
            RouteConfiguration.forSessionScope().setRoute("/grid", GridView.class, MainLayout.class);
            RouteConfiguration.forSessionScope().setRoute("/42km", Cursa42KmView.class, MainLayout.class);
            RouteConfiguration.forSessionScope().setRoute("/21km", Cursa21KmView.class, MainLayout.class);
            RouteConfiguration.forSessionScope().setRoute("/10km", Cursa10kmView.class, MainLayout.class);
            RouteConfiguration.forSessionScope().setRoute("/copii", CursaCopiiView.class, MainLayout.class);
            RouteConfiguration.forSessionScope().setRoute("/logout", LogoutView.class, MainLayout.class);
        }
    }

    public void signUp(String firstName, String lastName, String email,
                       String userName, String password, String checkPassword,
                       Sex sex, ShirtSize shirtSize, Race race) {

        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setEmail(email);
        person.setSex(sex);
        person.setShirtSize(shirtSize);
        person.setRace(race);


        User user = new User(userName, password, Role.USER);
        person.setUser(user);

        VaadinSession.getCurrent().setAttribute("username", userName);
        VaadinSession.getCurrent().setAttribute("person", person);

    }

    public Person getPerson() {
        return person;
    }

}
