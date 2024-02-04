package business;

import Core.Helper;
import dao.UserDao;
import entity.User;

import java.util.ArrayList;

public class UserManager {
    private final UserDao userDao;

    public UserManager() {
        this.userDao = new UserDao();
    }

    public User findByLogin (String username, String password) {
        return this.userDao.findByLogin(username,password);

    }

    public ArrayList<User> findAll() {
        return this.userDao.findAll();
    }

    public ArrayList<Object[]> getForTable(int size,ArrayList<User>userList) {
        ArrayList<Object[]> userObjList = new ArrayList<>();
        for(User obj : userList) {
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getUsername();
            rowObject[i++] = obj.getPassword();
            rowObject[i++] = obj.getRole();
            userObjList.add(rowObject);
        }
        return userObjList;
    }

    public boolean save(User user) {
        if(user.getId() != 0) {
            Helper.showMsg("error");
        }
        return this.userDao.save(user);
    }

    public User getById(int id) {
        return this.userDao.getById(id);
    }

    public boolean update(User user) {
        if (this.getById(user.getId()) == null) {
            Helper.showMsg("notFound");
        }
        return this.userDao.update(user);
    }

    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg("notFound");
            return false;
        }
        return this.userDao.delete(id);
    }

    public ArrayList<User> searchForTable(User.Role role) {
        String select = "SELECT * FROM public.user";
        ArrayList<String> whereList = new ArrayList<>();

        if(role != null) {
            whereList.add("user_role = '" + role.toString() + "'" );
        }
        String whereStr = String.join(" AND ",whereList);
        String query = select;
        if(whereStr.length() > 0) {
            query += " WHERE " + whereStr;
        }
        return this.userDao.selectByQuery(query);
    }
}

