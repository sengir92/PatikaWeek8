package entity;

import Core.ComboItem;

public class Hotel {

    private int id;
    private String name;
    private String address;
    private String mail;
    private String phone;
    private String star;
    private boolean car_park;
    private boolean wifi;
    private boolean pool;
    private boolean fitness;
    private boolean concierge;
    private boolean spa;
    private boolean room_service;

    public enum Star {
        one,
        two,
        three,
        four,
        five
    }

    public Hotel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public boolean isCar_park() {
        return car_park;
    }

    public void setCar_park(boolean car_park) {
        this.car_park = car_park;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isPool() {
        return pool;
    }

    public void setPool(boolean pool) {
        this.pool = pool;
    }

    public boolean isFitness() {
        return fitness;
    }

    public void setFitness(boolean fitness) {
        this.fitness = fitness;
    }

    public boolean isConcierge() {
        return concierge;
    }

    public void setConcierge(boolean concierge) {
        this.concierge = concierge;
    }

    public boolean isSpa() {
        return spa;
    }

    public void setSpa(boolean spa) {
        this.spa = spa;
    }

    public boolean isRoom_service() {
        return room_service;
    }

    public ComboItem getComboItem() {
        return new ComboItem(this.getId(),this.getName());
    }

    public void setRoom_service(boolean room_service) {
        this.room_service = room_service;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", mail='" + mail + '\'' +
                ", phone='" + phone + '\'' +
                ", star='" + star + '\'' +
                ", car_park='" + car_park + '\'' +
                ", wifi='" + wifi + '\'' +
                ", pool='" + pool + '\'' +
                ", fitness='" + fitness + '\'' +
                ", concierge='" + concierge + '\'' +
                ", spa='" + spa + '\'' +
                ", room_service='" + room_service + '\'' +
                '}';
    }
}

