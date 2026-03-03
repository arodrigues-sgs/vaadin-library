package com.library.ui.views;

import com.library.backend.Book;
import com.library.backend.MockBookRepository;
import com.library.ui.components.BookForm;
import com.library.ui.components.ViewToolbar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("books/new")
public class NewBook extends VerticalLayout {
    private final MockBookRepository bookRepo;

    public NewBook(MockBookRepository bookRepo) {
        this.bookRepo = bookRepo;

        BookForm bookForm = new BookForm();
        bookForm.setBook(new Book());
        bookForm.addSaveListener(this::saveBook);
        bookForm.addCancelListener(() -> getUI().ifPresent(ui -> ui.navigate("books")));
        bookForm.setEditable(true);

        add(new ViewToolbar("New Book"), bookForm);

    }

    private void saveBook(Book book) {
        bookRepo.save(book);
        getUI().ifPresent(ui -> ui.navigate("books?message=created"));
    }

}
