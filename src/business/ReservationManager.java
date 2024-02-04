package business;

import Core.Helper;
import dao.ReservationDao;
import entity.Reservation;

import java.util.ArrayList;

public class ReservationManager {
    private final ReservationDao reservationDao;

    public ReservationManager() {
    this.reservationDao = new ReservationDao();
    }

    public ArrayList<Reservation> findAll() {
        return this.reservationDao.findAll();
    }

    public Reservation getById(int id) {
        return this.reservationDao.getById(id);
    }
    public ArrayList<Object[]> getForTable(int size,ArrayList<Reservation>reservationList) {
        ArrayList<Object[]> resObjList = new ArrayList<>();
        for(Reservation obj : reservationList) {
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getRoom_id();
            rowObject[i++] = obj.getCheck_in_date();
            rowObject[i++] = obj.getCheck_out_date();
            rowObject[i++] = obj.getTotal_price();
            rowObject[i++] = obj.getAdult_number();
            rowObject[i++] = obj.getChild_number();
            rowObject[i++] = obj.getName();
            rowObject[i++] = obj.getCitizen_id();
            rowObject[i++] = obj.getMail();
            rowObject[i++] = obj.getPhone();
            resObjList.add(rowObject);
        }
        return resObjList;
    }

    public boolean save(Reservation reservation) {
        if(reservation.getId() != 0) {
            Helper.showMsg("error");
        }
        return this.reservationDao.save(reservation);
    }



    public boolean update(Reservation reservation) {
        if (this.getById(reservation.getId()) == null) {
            Helper.showMsg("notFound");
        }
        return this.reservationDao.update(reservation);
    }

    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg("notFound");
            return false;
        }
        return this.reservationDao.delete(id);
    }


}
