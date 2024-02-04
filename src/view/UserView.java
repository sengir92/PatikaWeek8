package view;

import Core.Helper;
import business.UserManager;
import entity.User;

import javax.swing.*;

public class UserView extends Layout {
    private JPanel container;
    private JLabel lbl_user;
    private JLabel lbl_username;
    private JTextField fld_user_name;
    private JLabel lbl_pass;
    private JTextField fld_pass;
    private JLabel lbl_role;
    private JComboBox<User.Role> cmb_user_role;
    private JButton btn_save;
    private User user;
    private UserManager userManager;


    public UserView(User user) {
        this.userManager = new UserManager();
        this.add(container);
        this.guiInitilaze(300, 250);
        this.user = user;


        this.cmb_user_role.setModel(new DefaultComboBoxModel<>(User.Role.values())); // comboboxtan veri çekmek için lazım
            if (user != null) {
                this.fld_user_name.setText(user.getUsername());
                this.fld_pass.setText(user.getPassword());
                this.cmb_user_role.setSelectedItem(user.getRole());


            this.btn_save.addActionListener(e -> {
                if (Helper.isFieldListEmpty(new JTextField[]{this.fld_user_name, this.fld_pass})) {
                    Helper.showMsg("fill");
                } else {
                    boolean result;

                        this.user.setUsername(fld_user_name.getText());
                        this.user.setPassword(fld_pass.getText());
                        this.user.setRole((User.Role) this.cmb_user_role.getSelectedItem()); // combobox veri seçmek için

                    if (this.user.getId() != 0) {
                        result = this.userManager.update(this.user);
                    } else {
                        result = this.userManager.save(this.user);
                    }
                    if (result) {
                        Helper.showMsg("done");
                        dispose();
                    } else {
                        Helper.showMsg("error");
                    }
                }
            });


        }


        }



    }


