package com.naeunminchocofarm.ncf_api.smart_farm.entity;

public class Uuid {
    private Integer id;
    private String uuid;

    public Uuid() {
    }

    public Uuid(Integer id, String uuid) {
        this.id = id;
        this.uuid = uuid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer uuidId) {
        this.id = uuidId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
