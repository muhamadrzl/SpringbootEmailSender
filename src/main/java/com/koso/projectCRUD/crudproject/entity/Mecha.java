package com.koso.projectCRUD.crudproject.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name="mecha")
public class Mecha {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="mecha_name")
    private String productName;
    @Column(name="version_number")
    private String versionNumber;
    @Column(name="linux_sourceclose")
    private LocalDateTime linuxSourceClose;

    public Mecha() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public LocalDateTime getLinuxSourceClose() {
        return linuxSourceClose;
    }

    public void setLinuxSourceClose(LocalDateTime linuxSourceClose) {
        this.linuxSourceClose = linuxSourceClose;
    }
}
