package com.phone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactManagerImp implements ContactManager {
    Map<String,Contact> contacts=new HashMap<>();
    List<String> contactIds=new ArrayList<>();
    @Override
    public boolean add(Contact contact) {
        String id=contact.getFirstName()+"-"+contact.getLastName()+"-"+contact.getPhoneNumber();
        if(!contacts.containsKey(id)) {
            boolean isValidPhoneNumber=validatePhoneNumber(contact.getPhoneNumber());
            if(isValidPhoneNumber) {
                contacts.put(id, contact);
                contactIds.add(id);
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean update(Contact contact) {
        String id=contact.getFirstName()+"-"+contact.getLastName()+"-"+contact.getPhoneNumber();
        if(contacts.containsKey(id)){
            boolean isValidPhoneNumber=validatePhoneNumber(contact.getPhoneNumber());
            if(isValidPhoneNumber) {
                contacts.put(id, contact);
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }

    @Override
    public SearchResponse search(SearchRequest searchRequest) {
        SearchField sf=searchRequest.getSearchField();
        SearchType st=searchRequest.getSearchType();
        List<Contact> resultContacts=new ArrayList<>();
        if(SearchField.FIRST_NAME==sf){
            List<String> firstNames=new ArrayList<>();
            //List<String> ids=new ArrayList<>();
            for(int i=0;i<contacts.size();i++){
                String id=contactIds.get(i);
                firstNames.add(contacts.get(id).getFirstName());
            }
            if(SearchType.COMPLETE==st){
                for(int i=0;i<firstNames.size();i++){
                    if(firstNames.get(i).equals(searchRequest.getInput())){
                        String id=contactIds.get(i);
                        resultContacts.add(contacts.get(id));
                    }
                }
            }
            else if(SearchType.PARTIAL==st){
                for(int i=0;i<firstNames.size();i++){
                    String subString=firstNames.get(i).substring(0,searchRequest.getInput().length());

                    if(subString.equals(searchRequest.getInput())) {
                        String id=contactIds.get(i);
                        resultContacts.add(contacts.get(id));
                    }
                }
            }
        }
        if(SearchField.LAST_NAME==sf){
            List<String> lastNames=new ArrayList<>();
            //List<String> ids=new ArrayList<>();
            for(int i=0;i<contacts.size();i++){
                String id=contactIds.get(i);
                lastNames.add(contacts.get(id).getLastName());
                //ids.add(contacts.get(i).getId());
            }
            if(SearchType.COMPLETE==st){
                for(int i=0;i<lastNames.size();i++){
                    if(lastNames.get(i).equals(searchRequest.getInput())){
                        String id=contactIds.get(i);
                        resultContacts.add(contacts.get(id));
                    }
                }
            }
            else if(SearchType.PARTIAL==st){
                for(int i=0;i<lastNames.size();i++){
                    String subString=lastNames.get(i).substring(0,searchRequest.getInput().length());
                    if(subString.equals(searchRequest.getInput())) {
                        String id=contactIds.get(i);
                        resultContacts.add(contacts.get(id));
                    }
                }
            }
        }
        if(SearchField.PHONE==sf){
            List<String> phoneNumbers=new ArrayList<>();
            //List<String> ids=new ArrayList<>();
            for(int i=0;i<contacts.size();i++){
                String id=contactIds.get(i);
                phoneNumbers.add(contacts.get(id).getPhoneNumber());
                //ids.add(contacts.get(i).getId());
            }
            if(SearchType.COMPLETE==st){
                for(int i=0;i<phoneNumbers.size();i++){
                    if(phoneNumbers.get(i).equals(searchRequest.getInput())){
                        String id=contactIds.get(i);
                        resultContacts.add(contacts.get(id));
                    }
                }
            }
            else if(SearchType.PARTIAL==st){
                for(int i=0;i<phoneNumbers.size();i++){
                    String subString=phoneNumbers.get(i).substring(0,searchRequest.getInput().length());
                    if(subString.equals(searchRequest.getInput())) {
                        String id=contactIds.get(i);
                        resultContacts.add(contacts.get(id));
                    }
                }
            }
        }
        SearchResponse result=new SearchResponse(resultContacts.size(),resultContacts);
        return result;
    }
    static boolean validatePhoneNumber(String phoneNumber){
        String pattern="^\\+[1-9]\\d{1,14}$";
        if(phoneNumber.matches(pattern)) {
            return true;
        }
        return false;
    }
}
