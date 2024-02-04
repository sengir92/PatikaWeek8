package dao;

import Core.Db;
import entity.Pension;
import entity.Room;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDao {

    private final Connection con;
    private HotelDao hotelDao;
    private PensionDao pensionDao;
    private SeasonDao seasonDao;

    public RoomDao() {
        this.con = Db.getInstance();
        this.hotelDao = new HotelDao();
        this.pensionDao = new PensionDao();
        this.seasonDao = new SeasonDao();
    }



    public ArrayList<Room> findAll() {
        ArrayList<Room> roomList = new ArrayList<>();
        String sql = "SELECT * FROM public.room ORDER BY room_id ASC";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()) {
                roomList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }

    public ArrayList<Room> selectByQuery(String query) {
        ArrayList<Room> roomList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                roomList.add(this.match(rs));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return roomList;
    }


    public Room getById(int id) {
        Room obj = null;
        String query = "SELECT * FROM public.room WHERE room_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public Room match(ResultSet rs) throws SQLException {
        Room obj = new Room();
        obj.setId(rs.getInt("room_id"));
        obj.setHotel_id(rs.getInt("hotel_id"));
        obj.setPension_id(rs.getInt("hotel_pension_id"));
        obj.setSeason_id(rs.getInt("hotel_season_id"));
        obj.setRoom_type(rs.getString("room_type"));
        obj.setStock(rs.getInt("room_stock"));
        obj.setAdult_price(rs.getInt("room_adult_price"));
        obj.setChild_price(rs.getInt("room_child_price"));
        obj.setCapacity(rs.getInt("room_bed_capacity"));
        obj.setSquare_meter(rs.getInt("room_square_meter"));
        obj.setTelevision(rs.getBoolean("room_television"));
        obj.setMinibar(rs.getBoolean("room_minibar"));
        obj.setGame_console(rs.getBoolean("room_game_console"));
        obj.setCash_box(rs.getBoolean("room_cash_box"));
        obj.setProjection(rs.getBoolean("room_projection"));
        obj.setHotel(this.hotelDao.getById(rs.getInt("hotel_id")));
        obj.setPension(this.pensionDao.getById(rs.getInt("hotel_pension_id")));
        obj.setSeason(this.seasonDao.getById(rs.getInt("hotel_season_id")));

        return obj;
    }
    public boolean save(Room room) {
        String query = "INSERT INTO public.room" +
                "(" +
                "hotel_id," +
                "hotel_pension_id," +
                "hotel_season_id," +
                "room_type," +
                "room_stock," +
                "room_adult_price," +
                "room_child_price," +
                "room_bed_capacity," +
                "room_square_meter," +
                "room_television," +
                "room_minibar," +
                "room_game_console," +
                "room_cash_box," +
                "room_projection" +
                ")" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,room.getHotel_id());
            pr.setInt(2,room.getPension_id());
            pr.setInt(3,room.getSeason_id());
            pr.setString(4,room.getRoom_type());
            pr.setInt(5,room.getStock());
            pr.setInt(6,room.getAdult_price());
            pr.setInt(7,room.getChild_price());
            pr.setInt(8,room.getCapacity());
            pr.setInt(9,room.getSquare_meter());
            pr.setBoolean(10,room.isTelevision());
            pr.setBoolean(11,room.isMinibar());
            pr.setBoolean(12,room.isGame_console());
            pr.setBoolean(13,room.isCash_box());
            pr.setBoolean(14, room.isProjection());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public ArrayList<Room> getByListHotelId(int hotelId) {
        return this.selectByQuery("SELECT * FROM public.room WHERE hotel_id = " + hotelId);
    }

    public boolean updateStock(Room room) {
        String query = "UPDATE public.room SET room_stock = ?  WHERE room_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, room.getStock());
            pr.setInt(2,room.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


}
