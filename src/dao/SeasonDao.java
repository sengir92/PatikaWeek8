package dao;

import Core.Db;
import entity.Pension;
import entity.Season;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class SeasonDao {
    private final Connection con;

    public SeasonDao() {
        this.con = Db.getInstance();
    }

    public ArrayList<Season> findAll() {
        ArrayList<Season> seasonList = new ArrayList<>();
        String sql = "SELECT * FROM public.hotel_season ORDER BY season_id ASC";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()) {
                seasonList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seasonList;
    }

    public ArrayList<Season> selectByQuery(String query) {
        ArrayList<Season> seasonList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                seasonList.add(this.match(rs));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return seasonList;
    }

    public Season getById(int id) {
        Season obj = null;
        String query = "SELECT * FROM public.hotel_season WHERE season_id = ?";
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

    public ArrayList<Season> getSeasonByHotelId(int hotelId) {
        ArrayList<Season> seasons = new ArrayList<>();
        String query = "SELECT * FROM public.hotel_season WHERE hotel_id = ?";
        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setInt(1, hotelId);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                Season season = match(rs);
                seasons.add(season);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seasons;
    }


    public Season match(ResultSet rs) throws SQLException {
        Season obj = new Season();
        obj.setId(rs.getInt("season_id"));
        obj.setHotel_id(rs.getInt("hotel_id"));
        obj.setStrt_date(LocalDate.parse(rs.getString("start_date")));
        obj.setFnsh_date(LocalDate.parse(rs.getString("finish_date")));
        return obj;
    }

    public boolean save(Season season) {
        String query = "INSERT INTO public.hotel_season" +
                "(" +
                "hotel_id," +
                "start_date," +
                "finish_date" +
                ")" +
                "VALUES (?,?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,season.getHotel_id());
            pr.setDate(2, Date.valueOf(season.getStrt_date()));
            pr.setDate(3, Date.valueOf(season.getFnsh_date()));
            return pr.executeUpdate() != -1;

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
