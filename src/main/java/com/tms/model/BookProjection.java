package com.tms.model;

public interface BookProjection {
    String getAuthor();

    String getTitle();

    Integer getYear();

    Integer getCount();

    void setAuthor(String author);

    void setTitle(String title);

    void setYear(Integer year);

    void setCount(Integer count);
}
