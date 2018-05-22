package com.example.techsupport.projetnfcdespi;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;
import java.util.List;

@Table(database=MacartedeBase.class)
public class CarteDespi extends BaseModel implements Serializable {
@PrimaryKey(autoincrement = true)
    @Column
    private Integer id;
    @Column
    private  String firstname ;
    @Column
    private String LastName;
    @Column
    private String fonction;
    @Column
    private  String courriel;
    @Column
    private String cellphone;
    @Column
    private Integer type;

    public
    Integer getType() {
        return type;
    }

    public
    void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getCourriel() {
        return courriel;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public CarteDespi(String firstname, String lastName, String fonction, String courriel, String cellphone, Integer type) {
      //  this.id=id;
              this.firstname = firstname;
        this.LastName = lastName;
        this.fonction = fonction;
        this.courriel = courriel;
        this.cellphone = cellphone;
        this.type =type;
    }
    public CarteDespi(){

    }

}
