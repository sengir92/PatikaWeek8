import Core.Db;
import business.UserManager;
import view.AdminView;
import view.EmployeeView;
import view.LoginView;

import java.sql.Connection;

public class App {
    public static void main(String[] args) {

        Connection con = Db.getInstance();
        LoginView loginView = new LoginView();

    }
}