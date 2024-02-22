package com.phone;

import java.util.List;

public class SearchResponse {
    private int totalCount;

    private List<Contact> results;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<Contact> getResults() {
        return results;
    }

    public void setResults(List<Contact> results) {
        this.results = results;
    }

    public SearchResponse(int totalCount, List<Contact> results) {
        this.totalCount = totalCount;
        this.results = results;
    }
}
