package business;

import Core.Helper;
import dao.HotelDao;
import entity.Hotel;
import entity.User;

import java.util.ArrayList;

public class HotelManager {
    private final HotelDao hotelDao;

    public HotelManager() {
        this.hotelDao = new HotelDao();
    }

    public ArrayList<Hotel> findAll() {
        return this.hotelDao.findAll();
    }

    public Hotel getById(int id) {
        return this.hotelDao.getById(id);
    }

    public ArrayList<Object[]> getForTable(int size,ArrayList<Hotel>hotels) {
        ArrayList<Object[]> hotelObjList = new ArrayList<>();
        for(Hotel obj : hotels) {
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getName();
            rowObject[i++] = obj.getAddress();
            rowObject[i++] = obj.getMail();
            rowObject[i++] = obj.getPhone();
            rowObject[i++] = obj.getStar();
            rowObject[i++] = obj.isCar_park();
            rowObject[i++] = obj.isWifi();
            rowObject[i++] = obj.isPool();
            rowObject[i++] = obj.isFitness();
            rowObject[i++] = obj.isConcierge();
            rowObject[i++] = obj.isSpa();
            rowObject[i++] = obj.isRoom_service();
            hotelObjList.add(rowObject);
        }
        return hotelObjList;
    }
    public boolean save(Hotel hotel) {
        if(hotel.getId() != 0) {
            Helper.showMsg("error");
            return false;
        }
        return this.hotelDao.save(hotel);
    }

    

}
