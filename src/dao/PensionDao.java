package dao;

import Core.Db;
import entity.Pension;
import entity.Season;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PensionDao {
    private final Connection con;

    public PensionDao() {
        this.con = Db.getInstance();
    }

    public ArrayList<Pension> findAll() {
        ArrayList<Pension> pensionList = new ArrayList<>();
        String sql = "SELECT * FROM public.hotel_pension ORDER BY pension_id ASC";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()) {
                pensionList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pensionList;
    }

    public ArrayList<Pension> selectByQuery(String query) {
        ArrayList<Pension> pensionList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                pensionList.add(this.match(rs));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return pensionList;
    }

    public Pension getById(int id) {
        Pension obj = null;
        String query = "SELECT * FROM public.hotel_pension WHERE pension_id = ?";
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

    public ArrayList<Pension> getPensionByHotelId(int hotelId) {
        ArrayList<Pension> pensions = new ArrayList<>();
        String query = "SELECT * FROM public.hotel_pension WHERE hotel_id = ?";
        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setInt(1, hotelId);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                Pension pension = match(rs);
                pensions.add(pension);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pensions;
    }

    public Pension match(ResultSet rs) throws SQLException {
        Pension obj = new Pension();
        obj.setId(rs.getInt("pension_id"));
        obj.setHotel_id(rs.getInt("hotel_id"));
        obj.setPension_Type(rs.getString("pension_type"));
        return obj;
    }

    public boolean save(Pension pension) {
        String query = "INSERT INTO public.hotel_pension" +
                "(" +
                "hotel_id," +
                "pension_type" +
                ")" +
                "VALUES (?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,pension.getHotel_id());
            pr.setString(2,pension.getPension_Type());
            return pr.executeUpdate() != -1;

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
