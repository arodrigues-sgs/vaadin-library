package com.library.ui.views;

import com.library.security.UserDTO;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

@Route("signup")
@PageTitle("Sign Up")
@AnonymousAllowed
public class Signup extends VerticalLayout {
    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    private final Binder<UserDTO> binder = new Binder<>(UserDTO.class);

    public Signup(UserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;

        // use the book form that we made as a guide
        // how do we set up form fields to accept user input for username, password, password confirmation


        // use the binder to mark fields as required and map them to the data-transfer-object (UserDTO)
        // we will worry about complex password requirements next class


        // check if the username already exists (hint: use the .withValidator() method)


        // check if the password and password confirmation match (hint: use the .withValidator() method)


        // create a submit button and handle the creation of a new user
        // hint: use binder.writeBeanIfValid() method to save the form contents to the UserDTO in memory,
        // then hash the password, and create a new user using the userDetailsManager


        // create a button that links back to the login page
    }

}
