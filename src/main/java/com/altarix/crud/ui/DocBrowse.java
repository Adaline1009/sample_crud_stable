package com.altarix.crud.ui;

import com.altarix.crud.model.entity.Doc;
import com.altarix.crud.service.DataService;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;

@SpringView
@Theme("valo")
public class DocBrowse extends VerticalLayout implements View {

    private DataService dataService;
    private Grid<Doc> docGrid = new Grid<>(Doc.class);

    public DocBrowse(DataService dataService) {
        this.dataService = dataService;
        final HorizontalLayout horizontalLayout = new HorizontalLayout();

        Button buttonCreate = new Button("Создать");
        buttonCreate.addClickListener(e -> {
            getUI().getSession().setAttribute(Doc.class, null);
            getUI().getNavigator().navigateTo("edit");
        });
        Button buttonDelete = new Button("Удалить");
        buttonDelete.addClickListener(e -> {
            dataService.remove(docGrid.getSelectedItems().iterator().next());
            updateList();
        });
        Button buttonEdit = new Button("Редактировать");
        buttonEdit.addClickListener(e -> {
            getUI().getSession().setAttribute(Doc.class, docGrid.getSelectedItems().iterator().next());
            getUI().getNavigator().navigateTo("edit");
        });

        Button buttonReport = new Button("Отчет");
        buttonEdit.addClickListener(e -> {
            getUI().getSession().setAttribute(Doc.class, docGrid.getSelectedItems().iterator().next());
        });

        horizontalLayout.addComponents(buttonCreate, buttonEdit, buttonDelete, buttonReport);
        addComponents(docGrid, horizontalLayout);

        docGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        docGrid.setColumns("code", "name", "kind", "date");
        docGrid.getColumn("code").setCaption("Код");
        docGrid.getColumn("name").setCaption("Название");
        docGrid.getColumn("kind").setCaption("Вид");
        docGrid.getColumn("date").setCaption("Дата");
        updateList();
        docGrid.setSizeFull();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        updateList();
    }

    private void updateList() {
        docGrid.setItems(dataService.getAll());
    }
}
