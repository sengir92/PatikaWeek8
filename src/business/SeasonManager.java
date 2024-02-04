package business;

import Core.Helper;
import dao.SeasonDao;
import entity.Season;

import java.util.ArrayList;

public class SeasonManager {
    private final SeasonDao seasonDao;

    public SeasonManager() {
        this.seasonDao = new SeasonDao();

    }

    public ArrayList<Season> findAll() {
        return this.seasonDao.findAll();
    }

    public Season getById(int id) {
        return this.seasonDao.getById(id);
    }

    public ArrayList<Object[]> getForTable(int size,ArrayList<Season> seasons) {
        ArrayList<Object[]> seasonObjList = new ArrayList<>();
        for (Season obj : seasons) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getHotel_id();
            rowObject[i++] = obj.getStrt_date();
            rowObject[i++] = obj.getFnsh_date();

            seasonObjList.add(rowObject);
        }
        return seasonObjList;
    }
    public boolean save(Season season) {
        if(this.getById(season.getId()) != null) {
            Helper.showMsg("error");
            return false;
        }
        return this.seasonDao.save(season);
    }

    public ArrayList<Season> getSeasonByHotelId(int id){
        return this.seasonDao.getSeasonByHotelId(id);
    }
}
