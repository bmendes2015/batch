package br.com.original.batch.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"cpf"})})
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String cpf;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Product> products;


    public Customer(String name, String cpf, String code, String product, String days, String value) {
        this.name = name;
        this.cpf = cpf;
        this.getProducts().add(new Product(this, code,product, Long.parseLong(days), new BigDecimal(value)));
    }

    public Customer(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Product> getProducts() {
        if(products == null){
            products = new ArrayList<>();
        }
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
