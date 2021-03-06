
package com.payworks.generated.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyTransactionsResponse {

    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("usertransactions")
    @Expose
    private List<Usertransaction> usertransactions = null;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Usertransaction> getUsertransactions() {
        return usertransactions;
    }

    public void setUsertransactions(List<Usertransaction> usertransactions) {
        this.usertransactions = usertransactions;
    }

}
