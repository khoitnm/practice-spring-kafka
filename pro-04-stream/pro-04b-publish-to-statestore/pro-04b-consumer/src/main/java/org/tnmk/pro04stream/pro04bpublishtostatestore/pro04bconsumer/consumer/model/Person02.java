package org.tnmk.pro04stream.pro04bpublishtostatestore.pro04bconsumer.consumer.model;

public class Person02 {
    private Long id;
    private String fullName;

    public Person02(){
    }

    public Person02(Long id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Person02{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
