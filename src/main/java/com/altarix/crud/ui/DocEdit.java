package com.altarix.crud.ui;

import com.altarix.crud.model.entity.Doc;
import com.altarix.crud.service.DataService;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;

import static java.lang.Integer.parseInt;
import static java.util.Date.from;
import static java.time.ZoneId.systemDefault;

@SpringView
@Theme("valo")
public class DocEdit extends FormLayout implements View {

    private DataService dataService;
    private TextField codeTextField;
    private TextField nameTextField;
    private NativeSelect<String> docKind;
    private DateField dateField;
    private boolean isUpdate;

    public DocEdit(DataService dataService) {
        this.dataService = dataService;
        docKind = new NativeSelect<>("Вид:");
        docKind.setItems("Инструкция", "Отчет", "Доклад", "Приказ");
        codeTextField = new TextField("Код:");
        nameTextField = new TextField("Имя:");
        dateField = new DateField("Дата:");
        Button saveButton = new Button("Сохранить и закрыть");
        saveButton.addClickListener(e -> {
            if (isUpdate) {
                Doc doc = getUI().getSession().getAttribute(Doc.class);
                doc.setCode(parseInt(codeTextField.getValue()));
                doc.setDate(from(dateField.getValue().atStartOfDay(systemDefault()).toInstant()));
                doc.setName(nameTextField.getValue());
                doc.setKind(docKind.getValue());
                dataService.update(doc);
                isUpdate = false;
                clearContent();
            } else {
                dataService.save(new Doc(
                        parseInt(codeTextField.getValue()),
                        from(dateField.getValue().atStartOfDay(systemDefault()).toInstant()),
                        nameTextField.getValue(),
                        docKind.getValue()));
            }
            getUI().getNavigator().navigateTo("docs");
        } );
        addComponents(codeTextField, nameTextField, docKind, dateField, saveButton);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Doc doc = getUI().getSession().getAttribute(Doc.class);
        if (doc != null) {
            codeTextField.setValue(String.valueOf(doc.getCode()));
            nameTextField.setValue(doc.getName());
            docKind.setSelectedItem(doc.getKind());
            dateField.setValue(doc.getDate().toInstant().atZone(systemDefault()).toLocalDate());
            isUpdate = true;
        }
    }

    private void clearContent() {
        codeTextField.clear();
        nameTextField.clear();
        docKind.clear();
        dateField.clear();
    }
}
