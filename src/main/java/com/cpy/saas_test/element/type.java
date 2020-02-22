package com.cpy.saas_test.element;

public class type {
    private String Id;
    private String Typename;
    private String Describe;
    private String Tips;


    @Override
    public String toString() {
        return "type{" +
                "Id='" + Id + '\'' +
                ", Typename='" + Typename + '\'' +
                ", Describe='" + Describe + '\'' +
                ", Tips='" + Tips + '\'' +
                '}';
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTypename() {
        return Typename;
    }

    public void setTypename(String typename) {
        Typename = typename;
    }

    public String getDescribe() {
        return Describe;
    }

    public void setDescribe(String describe) {
        Describe = describe;
    }

    public String getTips() {
        return Tips;
    }

    public void setTips(String tips) {
        Tips = tips;
    }
}
