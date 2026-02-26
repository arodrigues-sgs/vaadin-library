package com.library.ui.views;

import com.library.backend.Book;
import com.library.backend.MockBookRepository;
import com.library.ui.components.BookGrid;
import com.library.ui.components.SearchBar;
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

    public Books(MockBookRepository bookRepo) {
        this.bookRepo = bookRepo;

        BookGrid grid = new BookGrid(this.bookRepo.findAll());
        ViewToolbar toolbar = new ViewToolbar("Catalogue", new SearchBar(grid::filter, true));

        add(toolbar, grid);
    }
}
