package com.altarix.crud;

import com.altarix.crud.model.entity.Doc;
import com.altarix.crud.service.DataServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class CreateLetterServiceTest {
    @Mock
    private DataServiceImpl dataService;
    @Mock
    Doc doc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetById() {
        when(dataService.getById(2)).thenReturn(new Doc());
        System.out.println(dataService.getById(2));


    }

    @Test
    public void testSaveDoc() {
        doNothing().when(dataService).save(doc);
        dataService.save(doc);
        verify(dataService, times(1)).save(doc);

    }

    @Test
    public void testRemoveDoc() {
        doNothing().when(dataService).remove(doc);
        dataService.remove(doc);
        verify(dataService, times(1)).remove(doc);

    }
    @Test
    public void testRem(){
        doNothing().when(dataService).remove(doc);
    }

}
