package com.library.ui.views;

import com.library.security.Roles;
import com.library.backend.dto.UserDTO;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;


@Route("signup")
@PageTitle("Sign Up")
@AnonymousAllowed
public class Signup extends VerticalLayout {
    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    private final BeanValidationBinder<UserDTO> binder = new BeanValidationBinder<>(UserDTO.class);

    // these fields must match DTO field names exactly for the binder to work
    private final TextField username = new TextField("Username");
    private final PasswordField password = new PasswordField("Password");
    private final PasswordField confirmPassword = new PasswordField("Confirm Password");

    public Signup(UserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;

        FormLayout formLayout = new FormLayout();

        username.setValueChangeMode(ValueChangeMode.LAZY);
        password.setValueChangeMode(ValueChangeMode.LAZY);
        confirmPassword.setValueChangeMode(ValueChangeMode.LAZY);

        Button submitBtn = new Button("Sign Up");
        submitBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        binder.bindInstanceFields(this);
        binder.forField(username)
                .withValidator(name -> !userDetailsManager.userExists(name), "Username already exists");
        binder.forField(confirmPassword)
                .withValidator(confirmPass -> confirmPass.equals(password.getValue()), "Passwords do not match");

        submitBtn.addClickListener(e -> {
            UserDTO dto = new UserDTO();
            if(binder.writeBeanIfValid(dto)) {
                String hashedPassword = passwordEncoder.encode(dto.getPassword());
                UserDetails newUser = User.withUsername(dto.getUsername())
                        .password(hashedPassword)
                        .roles(Roles.USER)
                        .build();
                userDetailsManager.createUser(newUser);

                getUI().ifPresent(ui -> ui.navigate("login"));

            } else {
                binder.validate();
            }
        });

        Button loginLink = new Button("Already have an account? Log in", e ->
                getUI().ifPresent(ui -> ui.navigate("login"))
        );
        loginLink.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        formLayout.add(username, password, confirmPassword, submitBtn, loginLink);
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));
        formLayout.setRowSpacing(20, Unit.PIXELS);

        add(formLayout);
    }
}
