package com.library.ui.views;

import com.library.backend.Book;
import com.library.backend.MockBookRepository;
import com.library.ui.components.ViewToolbar;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.InMemoryDataProvider;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

@Route("books")
public class BookDetails extends VerticalLayout implements HasUrlParameter<Long> {
    private final MockBookRepository bookRepo;
    private Long bookId;

    public BookDetails(MockBookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Long aLong) {
        bookId = aLong;
        Book book = bookRepo.findById(bookId).orElseThrow(); // TODO make this renavigate
        add(new ViewToolbar(book.getTitle()));
        createDetailsTable(book);
    }

    private void createDetailsTable(Book book) {}
}
