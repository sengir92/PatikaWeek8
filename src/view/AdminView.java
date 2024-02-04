package view;

import Core.Helper;
import business.UserManager;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AdminView extends Layout {
    private JPanel container;
    private JPanel pnl_admin;
    private JTable tbl_user;
    private JComboBox cmb_user_search;
    private JButton btn_srch_user;
    private JScrollPane scrl_user;
    private JButton btn_user_add;
    private JButton btn_logout;
    private JButton btn_cncl_user;
    private JLabel lbl_user;
    private JLabel lbl_welcome;
    private User user;
    private UserManager userManager;
    private DefaultTableModel tmdl_user = new DefaultTableModel();
    private JPopupMenu userMenu;
    private Object[] col_user;



    public AdminView(User user) {
        this.userManager = new UserManager();
        this.user = user;
        this.add(container);
        this.guiInitilaze(750, 400);
        if (this.user == null) {
            dispose();
        }

        this.lbl_welcome.setText("Welcome : " + this.user.getUsername());
        loadUserTable(null);
        loadUserComponent();
        loadUserFilter();

        this.tbl_user.setComponentPopupMenu(userMenu);


    }

    public void loadUserComponent() {
        this.tbl_user.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row = tbl_user.rowAtPoint(e.getPoint());
                tbl_user.setRowSelectionInterval(selected_row,selected_row);
            }
        });


        this.userMenu = new JPopupMenu();

        this.btn_user_add.addActionListener(e -> {
            UserView userView = new UserView(new User());
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable(null);
                }
            });

        });

        this.userMenu.add("Update").addActionListener(e -> {
            int selectUserId = this.getTableSelectionRow(tbl_user,0);
            UserView userView = new UserView(this.userManager.getById(selectUserId));
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable(null);
                }
            });
        });
        this.userMenu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectUserId = this.getTableSelectionRow(tbl_user,0);
                if(this.userManager.delete(selectUserId)){
                    Helper.showMsg("done");
                    loadUserTable(null);
                }else {
                    Helper.showMsg("error");
                }
            }
        });


        this.btn_srch_user.addActionListener(e -> {
            ArrayList<User> userListBySearch = this.userManager.searchForTable((User.Role)  cmb_user_search.getSelectedItem());
            ArrayList<Object[]> userRowListBySearch = this.userManager.getForTable(this.col_user.length, userListBySearch);
            loadUserTable(userRowListBySearch);

        });
        this.btn_cncl_user.addActionListener(e -> {
            this.cmb_user_search.setSelectedItem(null);
            loadUserTable(null);
        });

    }

    public void loadUserTable(ArrayList<Object[]> userList) {
        this. col_user = new Object[]{"User ID", "User Name", "User Password", "User Role"};
        if (userList == null) {
            userList = this.userManager.getForTable(this.col_user.length,this.userManager.findAll());
        }
        createTable(this.tmdl_user,this.tbl_user,col_user,userList);
    }


    public void loadUserFilter() {
        this.cmb_user_search.setModel(new DefaultComboBoxModel<>(User.Role.values()));
        this.cmb_user_search.setSelectedItem(null);
    }

}

