package grp16.tripmate.post.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ListAllPostTest {

    @Test
    void getAllPosts() {
    }

    @Mock
    private ArrayList<String> mockArrayList;


    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    public void testMyTest() {
        when(mockArrayList.get(0)).thenReturn("Hello world");

        String result = mockArrayList.get(0);

        assertEquals("Should have the correct string", "Hello world", result);

        verify(mockArrayList).get(0);
    }
}