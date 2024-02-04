package entity;

import Core.ComboItem;

import java.time.LocalDate;

public class Season {
    private int id;
    private int hotel_id;
    private LocalDate strt_date;
    private LocalDate fnsh_date;

    public Season() {
    }

    public Season(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public LocalDate getStrt_date() {
        return strt_date;
    }

    public void setStrt_date(LocalDate strt_date) {
        this.strt_date = strt_date;
    }

    public LocalDate getFnsh_date() {
        return fnsh_date;
    }

    public void setFnsh_date(LocalDate fnsh_date) {
        this.fnsh_date = fnsh_date;
    }

    public ComboItem getComboItem() {
        return new ComboItem(this.getId(),getStrt_date() + " - " + getFnsh_date());
    }

    public String toString() {
        return "Season{" +
                "id=" + id +
                ", hotel_id='" + hotel_id + '\'' +
                ", strt_date='" + strt_date + '\'' +
                ", fnsh_date='" + fnsh_date + '\'' +
                '}';
    }
}
