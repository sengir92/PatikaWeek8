package view;

import Core.Helper;
import business.HotelManager;
import entity.Hotel;

import javax.swing.*;

public class HotelView extends Layout {
    private JPanel container;
    private JLabel lbl_hotel_add;
    private JTextField fld_name;
    private JTextField fld_mail;
    private JTextField fld_phone;
    private JTextField fld_address;
    private JComboBox<Hotel.Star> cmb_star;
    private JRadioButton radio_car_park;
    private JRadioButton radio_wifi;
    private JRadioButton radio_pool;
    private JRadioButton radio_fitness;
    private JRadioButton radio_concierge;
    private JRadioButton radio_spa;
    private JRadioButton radio_room_service;
    private JButton btn_save;
    private HotelManager hotelManager;
    private Hotel hotel;

    public HotelView(Hotel hotel) {
        this.hotelManager = new HotelManager();
        this.add(container);
        this.guiInitilaze(300, 450);
        this.hotel = hotel;

        this.cmb_star.setModel(new DefaultComboBoxModel<>(Hotel.Star.values()));

            if(hotel != null) {
            this.fld_name.setText(hotel.getName());
            this.fld_mail.setText(hotel.getMail());
            this.fld_phone.setText(hotel.getPhone());
            this.fld_address.setText(hotel.getAddress());


            this.btn_save.addActionListener(e -> {
                if (Helper.isFieldListEmpty(new JTextField[]{this.fld_name, this.fld_mail,fld_phone,fld_address})) {
                    Helper.showMsg("fill");
                } else {
                    boolean result;

                    this.hotel.setName(this.fld_name.getText());
                    this.hotel.setMail(this.fld_mail.getText());
                    this.hotel.setAddress(this.fld_address.getText());
                    this.hotel.setPhone(this.fld_phone.getText());
                    this.hotel.setStar(this.cmb_star.getSelectedItem().toString());
                    this.hotel.setCar_park(this.radio_car_park.getModel().isSelected());
                    this.hotel.setWifi(this.radio_wifi.getModel().isSelected());
                    this.hotel.setPool(this.radio_pool.getModel().isSelected());
                    this.hotel.setFitness(this.radio_fitness.getModel().isSelected());
                    this.hotel.setConcierge(this.radio_concierge.getModel().isSelected());
                    this.hotel.setSpa(this.radio_spa.getModel().isSelected());
                    this.hotel.setRoom_service(this.radio_room_service.getModel().isSelected());

                    result = this.hotelManager.save(this.hotel);

                    if (result) {
                        Helper.showMsg("done");
                        dispose();
                    } else {
                        Helper.showMsg("error");
                    }
            };
        });

    }
}
}
