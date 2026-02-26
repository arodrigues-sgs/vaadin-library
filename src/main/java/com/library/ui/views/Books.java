package com.library.ui.views;

import com.library.backend.Book;
import com.library.backend.MockBookRepository;
import com.library.ui.components.ViewToolbar;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route("books")
@PageTitle("Catalogue")
@Menu(order = 1, icon = "vaadin:book", title = "Catalogue")
public class Books extends VerticalLayout {
    private final MockBookRepository bookRepo;
    private final Grid<Book> grid = new Grid<>();

    public Books(MockBookRepository bookRepo) {
        this.bookRepo = bookRepo;

        TextField searchField = new TextField();
        searchField.setPlaceholder("Search");
        searchField.setValueChangeMode(ValueChangeMode.EAGER);
        searchField.addValueChangeListener(event -> {
            String searchTerm = event.getValue().toLowerCase();
            List<Book> filteredBooks = bookRepo.findAll().stream()
                    .filter(book -> book.getTitle().toLowerCase().contains(searchTerm) ||
                            book.getAuthor().toLowerCase().contains(searchTerm) ||
                            book.getIsbn().toLowerCase().contains(searchTerm))
                    .toList();
            grid.setItems(filteredBooks);
        });

        ViewToolbar toolbar = new ViewToolbar("Catalogue", searchField);

        add(toolbar);
        createGrid();
        add(grid);
    }

    private void createGrid() {
        grid.setItems(bookRepo.findAll());
        grid.addColumn(Book::getTitle).setHeader("Title").setSortable(true);
        grid.addColumn(Book::getAuthor).setHeader("Author").setSortable(true);
        grid.addColumn(Book::getIsbn).setHeader("ISBN");
    }
}
