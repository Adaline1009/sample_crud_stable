package com.altarix.crud.ui;

import com.altarix.crud.service.DataService;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
@Theme("valo")
public class MainUI extends UI {

    @Autowired
    private DataService dataService;

    @Override
    protected void init(VaadinRequest request) {
        Navigator navigator = new Navigator(this,this);
        navigator.addView("edit", new DocEdit(dataService));
        navigator.addView("docs", new DocBrowse(dataService));
        navigator.navigateTo("docs");
    }
}
