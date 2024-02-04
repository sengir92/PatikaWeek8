package business;

import Core.Helper;
import dao.PensionDao;
import entity.Pension;
import entity.Season;

import java.util.ArrayList;

public class PensionManager {
    private final PensionDao pensionDao;


    public PensionManager() {
        this.pensionDao = new PensionDao();

    }

    public ArrayList<Pension> findAll() {
        return this.pensionDao.findAll();
    }

    public Pension getById(int id) {
        return this.pensionDao.getById(id);
    }

    public ArrayList<Object[]> getForTable(int size,ArrayList<Pension> pensions) {
        ArrayList<Object[]> pensionObjList = new ArrayList<>();
        for (Pension obj : pensions) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getHotel_id();
            rowObject[i++] = obj.getPension_Type();

            pensionObjList.add(rowObject);
        }
        return pensionObjList;
    }
    public boolean save(Pension pension) {
        if(this.getById(pension.getId()) != null) {
            Helper.showMsg("error");
            return false;
        }
        return this.pensionDao.save(pension);
    }

    public ArrayList<Pension> getPensionByHotelId(int id){
        return this.pensionDao.getPensionByHotelId(id);
    }
}
