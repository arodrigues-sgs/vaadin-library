package com.library.ui.components;

import com.library.backend.Book;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;


public class BookForm extends FormLayout {
    private Binder<Book> binder;

    public BookForm() {
        this.binder = new Binder<>(Book.class);

        TextField title = new TextField("Title");
        TextField author = new TextField("Author");
        TextField isbn = new TextField("ISBN");

        binder.forField(title).bind(Book::getTitle, Book::setTitle);
        binder.forField(author).bind(Book::getAuthor, Book::setAuthor);
        binder.forField(isbn).bind(Book::getIsbn, Book::setIsbn);

        // 1 column
        setResponsiveSteps(new ResponsiveStep("0", 1));

        add(title, author, isbn);

        setEditable(false);
    }

    public void setEditable(boolean editing) {
        binder.getFields().forEach(field -> field.setReadOnly(!editing));
    }

    public Binder<Book> getBinder() { return binder; }

}