package com.cpy.saas_test.element;

import java.util.Date;

public class Goodsout {
    private String Id;
    private String Gname;
    private String Gtype;
    private int amount;
    private String OutDate;

    @Override
    public String toString() {
        return "Goodsout{" +
                "Id='" + Id + '\'' +
                ", Gname='" + Gname + '\'' +
                ", Gtype='" + Gtype + '\'' +
                ", amount=" + amount +
                ", OutDate='" + OutDate + '\'' +
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

    public String getOutDate() {
        return OutDate;
    }

    public void setOutDate(String outDate) {
        OutDate = outDate;
    }
}
