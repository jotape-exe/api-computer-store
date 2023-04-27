package com.computershop.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address {

    @Id
    private String cep;

    @Column(name = "logradouro", columnDefinition = "VARCHAR(255)")
    private String logradouro;

    @Column(name = "complemento", columnDefinition = "VARCHAR(255)")
    private String complemento;

    @Column(name = "bairro", columnDefinition = "VARCHAR(255)")
    private String bairro;

    @Column(name = "localidade", columnDefinition = "VARCHAR(255)")
    private String localidade;

    @Column(name = "uf", columnDefinition = "VARCHAR(2)")
    private String uf;

    @Column(name = "ddd", columnDefinition = "VARCHAR(2)")
    private String ddd;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public Address(String cep) {
        this.cep = cep;
    }

    public Address(String cep, String logradouro, String complemento, String bairro, String localidade, String uf, String ddd) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
        this.ddd = ddd;
    }


    public Address(){

    }
}
