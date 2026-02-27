package com.library.ui.views;

import com.library.backend.Book;
import com.library.backend.MockBookRepository;
import com.library.ui.components.BookForm;
import com.library.ui.components.ViewToolbar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;


@Route("books")
public class BookDetails extends VerticalLayout implements HasUrlParameter<Long> {
    private final MockBookRepository bookRepo;

    private Book book;
    private final BookForm bookForm = new BookForm();

    private final Button backBtn = new Button("Back to All Books", VaadinIcon.ARROW_LEFT.create());
    private final Button editBtn = new Button(VaadinIcon.PENCIL.create());
    private final Button deleteBtn = new Button(VaadinIcon.TRASH.create());
    private final Button saveBtn = new Button("Save");
    private final Button cancelBtn = new Button("Cancel");

    public BookDetails(MockBookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Long bookId) {
        bookRepo.findById(bookId).ifPresentOrElse(
            (b) -> {
                book = b;
                showBookDetails();
            },
            () -> beforeEvent.forwardTo("books")
        );
    }

    private void showBookDetails() {
        bookForm.getBinder().readBean(book);

        configureButtons();
        setIsEditing(false);
        HorizontalLayout actions = new HorizontalLayout();
        actions.add(editBtn, deleteBtn, saveBtn, cancelBtn);

        add(new ViewToolbar(book.getTitle(), backBtn), bookForm, actions);
    }

    private void configureButtons() {
        // Styling
        deleteBtn.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_TERTIARY);
        saveBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancelBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        // Logic
        editBtn.addClickListener(e -> setIsEditing(true));
        cancelBtn.addClickListener(e -> {
            bookForm.getBinder().readBean(book); // Revert changes in UI
            setIsEditing(false);
        });

        saveBtn.addClickListener(e -> {
            if (bookForm.getBinder().writeBeanIfValid(book)) {
                bookRepo.save(book);
                setIsEditing(false);
            }
        });

        deleteBtn.addClickListener(e -> {
            bookRepo.delete(book);
            getUI().ifPresent(ui -> ui.navigate("books"));
        });

        backBtn.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.navigate("books"));
        });
    }

    private void setIsEditing(boolean isEditing) {
        editBtn.setVisible(!isEditing);
        deleteBtn.setVisible(!isEditing);
        saveBtn.setVisible(isEditing);
        cancelBtn.setVisible(isEditing);
        bookForm.setEditable(isEditing);
    }
}
