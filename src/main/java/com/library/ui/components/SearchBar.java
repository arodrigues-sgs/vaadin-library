package com.library.ui.components;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

import java.util.function.Consumer;

public class SearchBar extends HorizontalLayout {

    public SearchBar(Consumer<String> onSearch, boolean isEager) {
        TextField searchField = new TextField();
        searchField.setPlaceholder("Search ...");
        searchField.setValueChangeMode(isEager ? ValueChangeMode.EAGER : ValueChangeMode.LAZY);
        searchField.addValueChangeListener(event -> onSearch.accept(event.getValue()));

        add(searchField);
    }

}
