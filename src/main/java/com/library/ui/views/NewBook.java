package com.library.ui.views;

import com.library.backend.Book;
import com.library.backend.MockBookRepository;
import com.library.ui.components.BookForm;
import com.library.ui.components.ViewToolbar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("books/new")
public class NewBook extends VerticalLayout {
    private final MockBookRepository bookRepo;
    private final Book book = new Book();
    private final BookForm bookForm = new BookForm();

    private final Button saveBtn = new Button("Save");
    private final Button cancelBtn = new Button("Cancel");

    public NewBook(MockBookRepository bookRepo) {
        this.bookRepo = bookRepo;

        bookForm.getBinder().readBean(book);
        bookForm.setEditable(true);

        configureButtons();

        HorizontalLayout actions = new HorizontalLayout();
        actions.add(saveBtn, cancelBtn);

        add(new ViewToolbar("New Book", actions), bookForm);

    }

    private void configureButtons() {
        // Styling
        saveBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancelBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        saveBtn.addClickListener(e -> {
            if (bookForm.getBinder().writeBeanIfValid(book)) {
                bookRepo.save(book);
                getUI().ifPresent(ui -> ui.navigate("books"));
            }
        });

        cancelBtn.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.navigate("books"));
        });
    }

}
