package com.library.ui.views;

import com.library.backend.Book;
import com.library.backend.MockBookRepository;
import com.library.ui.components.ViewToolbar;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.InMemoryDataProvider;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

import java.util.Optional;

@Route("books")
public class BookDetails extends VerticalLayout implements HasUrlParameter<Long> {
    private final MockBookRepository bookRepo;

    public BookDetails(MockBookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Long bookId) {
        bookRepo.findById(bookId).ifPresentOrElse(
            this::showBookDetails,
            () -> beforeEvent.forwardTo("books")
        );
    }

    private void showBookDetails(Book book) {
        add(new ViewToolbar(book.getTitle()));
    }
}
