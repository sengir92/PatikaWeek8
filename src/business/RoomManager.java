package business;

import Core.Helper;
import dao.RoomDao;
import entity.Hotel;
import entity.Pension;
import entity.Room;
import entity.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RoomManager {
    private final RoomDao roomDao;

    public RoomManager() {
        this.roomDao = new RoomDao();
    }

    public ArrayList<Room> findAll() {
        return this.roomDao.findAll();
    }

    public Room getById(int id) {
        return this.roomDao.getById(id);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Room> rooms) {
        ArrayList<Object[]> roomObjList = new ArrayList<>();
        for (Room obj : rooms) {
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getHotel().getName();
            rowObject[i++] = obj.getHotel().getAddress();
            rowObject[i++] = obj.getPension().getPension_Type();
            rowObject[i++] = obj.getSeason().getStrt_date();
            rowObject[i++] = obj.getSeason().getFnsh_date();
            rowObject[i++] = obj.getRoom_type();
            rowObject[i++] = obj.getStock();
            rowObject[i++] = obj.getAdult_price();
            rowObject[i++] = obj.getChild_price();
            rowObject[i++] = obj.getCapacity();
            rowObject[i++] = obj.getSquare_meter();
            rowObject[i++] = obj.isTelevision();
            rowObject[i++] = obj.isMinibar();
            rowObject[i++] = obj.isGame_console();
            rowObject[i++] = obj.isCash_box();
            rowObject[i++] = obj.isProjection();
            roomObjList.add(rowObject);
        }
        return roomObjList;
    }

    public boolean save(Room room) {
        if (room.getId() != 0) {
            Helper.showMsg("error");
            return false;
        }
        return this.roomDao.save(room);
    }


    public ArrayList<Room> getByListHotelId(int hotelId) {
        return this.roomDao.getByListHotelId(hotelId);
    }

    public  ArrayList<Room> searchForBooking(String check_in_date, String check_out_date,
                                           int guestCount,String hotelName,String hotelAddress) {
        String query = "SELECT * FROM public.room AS r\n" +
                "LEFT JOIN public.hotel AS h ON r.hotel_id = h.hotel_id\n" +
                "LEFT JOIN public.hoteL_season AS s ON r.hotel_season_id = s.season_id\n" +
                "WHERE r.room_stock > 0";


        ArrayList<String> where = new ArrayList<>();


        check_in_date = LocalDate.parse(check_in_date, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
        check_out_date = LocalDate.parse(check_out_date, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();

        if (hotelName != null) {
            where.add(" AND h.hotel_name ILIKE '%" +  hotelName + "%'");
        }
        if (hotelAddress != null) {
            where.add(" AND h.hotel_address ILIKE '%" + hotelAddress + "%'");
        }


        where.add(" AND ('" + check_in_date + "' BETWEEN start_date AND finish_date)");
        where.add(" AND ('" + check_out_date + "' BETWEEN start_date AND finish_date)");
        where.add("AND '" +   guestCount + "'<= r.room_bed_capacity");


        query += String.join("", where);

        ArrayList<Room> roomList = this.roomDao.selectByQuery(query);
       return roomList;

    }
    public boolean updateStock(Room room) {
        return this.roomDao.updateStock(room);
    }

    }




