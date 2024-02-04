package entity;

import java.time.LocalDate;

public class Reservation {
    private int id;
    private int room_id;
    private LocalDate check_in_date;
    private LocalDate check_out_date;
    private int total_price;
    private int adult_number;
    private String name;
    private String citizen_id;
    private String mail;
    private String phone;
    private int child_number;

    public Reservation() {

    }
    public Reservation(int room_id) {
        this.room_id = room_id;
    }

    public int getChild_number() {
        return child_number;
    }

    public void setChild_number(int child_number) {
        this.child_number = child_number;
    }

    public int getId() {
        return id;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public LocalDate getCheck_in_date() {
        return check_in_date;
    }

    public void setCheck_in_date(LocalDate check_in_date) {
        this.check_in_date = check_in_date;
    }

    public LocalDate getCheck_out_date() {
        return check_out_date;
    }

    public void setCheck_out_date(LocalDate check_out_date) {
        this.check_out_date = check_out_date;
    }

    public int getAdult_number() {
        return adult_number;
    }

    public void setAdult_number(int adult_number) {
        this.adult_number = adult_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCitizen_id() {
        return citizen_id;
    }

    public void setCitizen_id(String citizen_id) {
        this.citizen_id = citizen_id;
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
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", room_id=" + room_id +
                ", check_in_date=" + check_in_date +
                ", check_out_date='" + check_out_date + '\'' +
                ", total_price='" +total_price + '\'' +
                ", guest_count='" + adult_number + '\'' +
                ", name='" + name + '\'' +
                ", citizen_id=" + citizen_id +
                ", mail=" + mail +
                ", phone=" + phone + '\'' +
                '}';
    }
}
