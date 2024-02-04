package dao;

import Core.Db;
import entity.Hotel;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelDao {
    private final Connection con;

    public HotelDao() {
        this.con = Db.getInstance();
    }

    public Hotel getById(int id) {
        Hotel obj = null;
        String querry = "SELECT * FROM public.hotel WHERE hotel_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(querry);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return obj;
    }

    public ArrayList<Hotel> findAll() {
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String sql = "SELECT * FROM public.hotel ORDER BY hotel_id ASC";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()) {
                hotelList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelList;
    }
    public ArrayList<Hotel> selectByQuery(String query) {
        ArrayList<Hotel> hotelList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                hotelList.add(this.match(rs));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return hotelList;
    }



    public Hotel match(ResultSet rs) throws SQLException {
        Hotel obj = new Hotel();
        obj.setId(rs.getInt("hotel_id"));
        obj.setName(rs.getString("hotel_name"));
        obj.setAddress(rs.getString("hotel_address"));
        obj.setMail(rs.getString("hotel_mail"));
        obj.setPhone(rs.getString("hotel_phone"));
        obj.setStar(rs.getString("hotel_star"));
        obj.setCar_park(rs.getBoolean("hotel_car_park"));
        obj.setWifi(rs.getBoolean("hotel_wifi"));
        obj.setPool(rs.getBoolean("hotel_pool"));
        obj.setFitness(rs.getBoolean("hotel_fitness"));
        obj.setConcierge(rs.getBoolean("hotel_concierge"));
        obj.setSpa(rs.getBoolean("hotel_spa"));
        obj.setRoom_service(rs.getBoolean("hotel_room_service"));
        return obj;
    }
    public boolean save(Hotel hotel) {
        String query = "INSERT INTO public.hotel" +
                "(" +
                "hotel_name," +
                "hotel_address," +
                "hotel_mail," +
                "hotel_phone," +
                "hotel_star," +
                "hotel_car_park," +
                "hotel_wifi," +
                "hotel_pool," +
                "hotel_fitness," +
                "hotel_concierge," +
                "hotel_spa," +
                "hotel_room_service" +
                ")" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1,hotel.getName());
            pr.setString(2,hotel.getAddress());
            pr.setString(3,hotel.getMail());
            pr.setString(4,hotel.getPhone());
            pr.setString(5,hotel.getStar());
            pr.setBoolean(6,hotel.isCar_park());
            pr.setBoolean(7,hotel.isWifi());
            pr.setBoolean(8,hotel.isPool());
            pr.setBoolean(9,hotel.isFitness());
            pr.setBoolean(10,hotel.isConcierge());
            pr.setBoolean(11,hotel.isSpa());
            pr.setBoolean(12,hotel.isRoom_service());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

}
