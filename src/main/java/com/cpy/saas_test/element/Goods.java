package com.cpy.saas_test.element;

public class Goods {
    private String Id;
    private String Gname;
    private String Gtype;
    private int amount;

    @Override
    public String toString() {
        return "Goods{" +
                "Id='" + Id + '\'' +
                ", Gname='" + Gname + '\'' +
                ", Gtype='" + Gtype + '\'' +
                ", amount=" + amount +
                '}';
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getGname() {
        return Gname;
    }

    public void setGname(String gname) {
        Gname = gname;
    }

    public String getGtype() {
        return Gtype;
    }

    public void setGtype(String gtype) {
        Gtype = gtype;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
