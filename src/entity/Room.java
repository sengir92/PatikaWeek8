package entity;

public class Room {
    private int id;
    private int hotel_id;
    private int pension_id;
    private int season_id;
    private String room_type;
    private int stock;
    private int adult_price;
    private int child_price;
    private int capacity;
    private int square_meter;
    private boolean television;
    private boolean minibar;
    private boolean game_console;
    private boolean cash_box;
    private boolean projection;
    private Hotel hotel;
    private Pension pension;
    private Season season;

    public enum RoomType{
        Single,
        Double,
        JuniorSuite,
        Suite
    }



    public void Room() {

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

    public int getPension_id() {
        return pension_id;
    }

    public void setPension_id(int pension_id) {
        this.pension_id = pension_id;
    }

    public int getSeason_id() {
        return season_id;
    }

    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getAdult_price() {
        return adult_price;
    }

    public void setAdult_price(int adult_price) {
        this.adult_price = adult_price;
    }

    public int getChild_price() {
        return child_price;
    }

    public void setChild_price(int child_price) {
        this.child_price = child_price;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getSquare_meter() {
        return square_meter;
    }

    public void setSquare_meter(int square_meter) {
        this.square_meter = square_meter;
    }

    public boolean isTelevision() {
        return television;
    }

    public void setTelevision(boolean television) {
        this.television = television;
    }

    public boolean isMinibar() {
        return minibar;
    }

    public void setMinibar(boolean minibar) {
        this.minibar = minibar;
    }

    public boolean isGame_console() {
        return game_console;
    }

    public void setGame_console(boolean game_console) {
        this.game_console = game_console;
    }

    public boolean isCash_box() {
        return cash_box;
    }

    public void setCash_box(boolean cash_box) {
        this.cash_box = cash_box;
    }

    public boolean isProjection() {
        return projection;
    }

    public void setProjection(boolean projection) {
        this.projection = projection;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Pension getPension() {
        return pension;
    }

    public void setPension(Pension pension) {
        this.pension = pension;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", hotel_id='" + hotel_id + '\'' +
                ", pension_id='" + pension_id + '\'' +
                ", season_id='" +season_id + '\'' +
                ", room_type='" + room_type + '\'' +
                ", stock='" + stock + '\'' +
                ", adult_price='" + adult_price + '\'' +
                ", child_price='" + child_price + '\'' +
                ", capacity='" + capacity + '\'' +
                ", square_meter='" + square_meter + '\'' +
                ", television='" + television + '\'' +
                ", minibar='" + minibar + '\'' +
                ", game_console='" + game_console + '\'' +
                ", cash_box='" + cash_box + '\'' +
                ", projection='" + projection + '\'' +
                '}';
    }
}
