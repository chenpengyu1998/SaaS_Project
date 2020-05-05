package com.cpy.saas_test.element;

public class JaccardElement {
    private String name;
    private double jaccard;

    public JaccardElement(double jaccard, String name) {
        this.jaccard = jaccard;
        this.name = name;
    }

    @Override
    public String toString() {
        return "JaccardElement{" +
                "name='" + name + '\'' +
                ", jaccard=" + jaccard +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getJaccard() {
        return jaccard;
    }

    public void setJaccard(double jaccard) {
        this.jaccard = jaccard;
    }
}
