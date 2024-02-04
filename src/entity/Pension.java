package entity;

import Core.ComboItem;

public class Pension {
    private int id;
    private int hotel_id;
    private String pension_Type;

    public enum pensionType {
        UltraAllInclusive,
        AllInclusive,
        RoomBreakfast,
        FullPension,
        HalfBoard,
        JustBed,
        ExcludingAlcoholFullCredit
    }

    public Pension() {
    }
    public Pension(int hotel_id) {
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

    public String getPension_Type() {
        return pension_Type;
    }

    public ComboItem getComboItem() {
        return new ComboItem(this.getId(),this.getPension_Type());
    }

    public void setPension_Type(String pension_Type) {
        this.pension_Type = pension_Type;
    }
    @Override
    public String toString() {
        return "Pension{" +
                "id=" + id +
                ", hotel_id='" + hotel_id + '\'' +
                ", type='" + pension_Type + '\'' +
                '}';
    }
}
