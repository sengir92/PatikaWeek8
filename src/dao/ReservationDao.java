package dao;

import Core.Db;
import entity.Reservation;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationDao {

    private Connection con;
    private final RoomDao roomDao;

    public ReservationDao() {
        this.con = Db.getInstance();
        this.roomDao = new RoomDao();
    }

    public ArrayList<Reservation> findAll() {
        ArrayList<Reservation> reservationList = new ArrayList<>();
        String sql = "SELECT * FROM public.reservation ORDER BY reservation_id ASC";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()) {
                reservationList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservationList;
    }
    public ArrayList<Reservation> selectByQuery(String query) {
        ArrayList<Reservation> reservationList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                reservationList.add(this.match(rs));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return reservationList;
    }


    public Reservation getById(int id) {
        Reservation obj = null;
        String query = "SELECT * FROM public.reservation WHERE reservation_id = ?";
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
    public Reservation match(ResultSet rs) throws SQLException {
        Reservation obj = new Reservation();
        obj.setId(rs.getInt("reservation_id"));
        obj.setRoom_id(rs.getInt("room_id"));
        obj.setCheck_in_date(LocalDate.parse(rs.getString("check_in_date")));
        obj.setCheck_out_date(LocalDate.parse(rs.getString("check_out_date")));
        obj.setTotal_price(rs.getInt("total_price"));
        obj.setAdult_number(rs.getInt("adult_number"));
        obj.setChild_number(rs.getInt("child_number"));
        obj.setName(rs.getString("guest_name"));
        obj.setCitizen_id(rs.getString("guest_citizen_id"));
        obj.setMail(rs.getString("guest_mail"));
        obj.setPhone(rs.getString("guest_phone"));
        return obj;
    }
    public boolean save(Reservation reservation) {
        String query = "INSERT INTO public.reservation" +
                "(" +
                "room_id," +
                "check_out_date," +
                "total_price," +
                "adult_number," +
                "guest_name," +
                "guest_citizen_id," +
                "guest_mail," +
                "guest_phone," +
                "check_in_date," +
                "child_number" +
                ")" +
                "VALUES (?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,reservation.getRoom_id());
            pr.setDate(2, Date.valueOf(reservation.getCheck_out_date()));
            pr.setInt(3,reservation.getTotal_price());
            pr.setInt(4,reservation.getAdult_number());
            pr.setString(5,reservation.getName());
            pr.setString(6,reservation.getCitizen_id());
            pr.setString(7,reservation.getMail());
            pr.setString(8,reservation.getPhone());
            pr.setDate(9,Date.valueOf(reservation.getCheck_in_date()));
            pr.setInt(10,reservation.getChild_number());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean update(Reservation reservation) {
        String query = "UPDATE public.reservation SET guest_name = ? ,guest_citizen_id = ? ," +
                "guest_mail = ?, guest_phone = ?  WHERE reservation_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, reservation.getName());
            pr.setString(2,reservation.getCitizen_id());
            pr.setString(3,reservation.getMail());
            pr.setString(4,reservation.getPhone());
            pr.setInt(5,reservation.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM public.reservation WHERE reservation_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate() != -1;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


}
