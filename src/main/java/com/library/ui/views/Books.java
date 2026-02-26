package com.library.ui.views;

import com.library.backend.MockBookRepository;
import com.library.ui.components.BookGrid;
import com.library.ui.components.SearchBar;
import com.library.ui.components.ViewToolbar;
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

        BookGrid grid = new BookGrid(this.bookRepo.findAll());

        // navigate to book details view when clicking a row in the grid
        grid.addItemClickListener(event -> {
            getUI().ifPresent(ui -> ui.navigate("books/" + event.getItem().getId()));
        });

        // Eager search bar (refreshes data on keystroke)
        SearchBar searchBar = new SearchBar(grid::filter, true);

        ViewToolbar toolbar = new ViewToolbar("Catalogue", searchBar);

        add(toolbar, grid);
    }
}
