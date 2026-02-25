package com.library.ui.views;

import com.library.backend.Book;
import com.library.backend.MockBookRepository;
import com.library.ui.components.ViewToolbar;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("books")
@PageTitle("Catalogue")
@Menu(order = 1, icon = "vaadin:book", title = "Catalogue")
public class Books extends VerticalLayout {
    private final MockBookRepository bookRepo;

    public Books(MockBookRepository bookRepo) {
        this.bookRepo = bookRepo;

        createGrid();
    }

    private void createGrid() {
        Grid<Book> grid = new Grid<>();
        grid.setItems(bookRepo.findAll());
        grid.addColumn(Book::getTitle).setHeader("Title");
        grid.addColumn(Book::getAuthor).setHeader("Author");
        grid.addItemClickListener(event -> {
            getUI().ifPresent(ui -> ui.navigate("books/" + event.getItem().getId()));
        });
        add(new ViewToolbar("Catalogue"));
        add(grid);
    }



}
