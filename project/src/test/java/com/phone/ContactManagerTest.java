package com.phone;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

public class ContactManagerTest {
    @Test
    void addingContact() {
        ContactManagerImp cmi=new ContactManagerImp();
        Contact cont=new Contact("lokesh","ajjuguttu","+44207183875044");
        boolean result=cmi.add(cont);
        assertTrue(result);
    }
    @Test
    void addingInvalidContact() {
        ContactManagerImp cmi=new ContactManagerImp();
        Contact cont=new Contact("lokesh","ajjuguttu","999999999");
        boolean result=cmi.add(cont);
        assertFalse(result);
    }
    @Test
    void addingDuplicateContact(){
        ContactManagerImp cmi=new ContactManagerImp();
        Contact cont1=new Contact("lokesh","ajjuguttu","+44207183875044");
        Contact cont2=new Contact("lokesh","ajjuguttu","+44207183875044");
        boolean result1=cmi.add(cont1);
        boolean result2=cmi.add(cont2);
        assertFalse(result2);
    }
    @Test
    void searchTest(){
        ContactManagerImp cmi=new ContactManagerImp();
        Contact cont1=new Contact("lokesh","ajjuguttu","+44207183875044");
        Contact cont2=new Contact("lokesh1","ajjuguttu1","+44207183875042");
        boolean flag1=cmi.add(cont1);
        boolean flag2=cmi.add(cont2);
        SearchRequest sr=new SearchRequest(SearchField.FIRST_NAME,SearchType.COMPLETE,"lokesh1");
        SearchResponse result= cmi.search(sr);
        assertEquals(1,result.getTotalCount());
        assertNotNull(result.getResults());
        assertEquals("lokesh1",result.getResults().get(0).getFirstName());
    }



}
