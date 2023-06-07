package com.example.application.backend.Service;

import com.example.application.backend.Enums.Role;
import com.example.application.backend.Model.User;
import com.example.application.backend.Repository.UserRepository;
import com.example.application.views.MainLayout;
import com.example.application.views.cursa10km.Cursa10kmView;
import com.example.application.views.cursa21km.Cursa21KmView;
import com.example.application.views.cursa42km.Cursa42KmView;
import com.example.application.views.cursacopii.CursaCopiiView;
import com.example.application.views.home.HomeView;
import com.example.application.views.logout.LogoutView;
import com.vaadin.flow.router.RouteConfiguration;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public class AuthException extends Exception {

    }
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
            RouteConfiguration.forSessionScope().setRoute("/42km", Cursa42KmView.class, MainLayout.class);
            RouteConfiguration.forSessionScope().setRoute("/21km", Cursa21KmView.class, MainLayout.class);
            RouteConfiguration.forSessionScope().setRoute("/10km", Cursa10kmView.class, MainLayout.class);
            RouteConfiguration.forSessionScope().setRoute("/copii", CursaCopiiView.class, MainLayout.class);
            RouteConfiguration.forSessionScope().setRoute("/logout", LogoutView.class, MainLayout.class);
        }

        else if (role.equals(Role.ADMIN)) {
            RouteConfiguration.forSessionScope().setRoute("/home", HomeView.class, MainLayout.class);
            RouteConfiguration.forSessionScope().setRoute("/42km", Cursa42KmView.class, MainLayout.class);
            RouteConfiguration.forSessionScope().setRoute("/21km", Cursa21KmView.class, MainLayout.class);
            RouteConfiguration.forSessionScope().setRoute("/10km", Cursa10kmView.class, MainLayout.class);
            RouteConfiguration.forSessionScope().setRoute("/copii", CursaCopiiView.class, MainLayout.class);
            RouteConfiguration.forSessionScope().setRoute("/logout", LogoutView.class, MainLayout.class);
        }
    }

}
