package view;

import Core.ComboItem;
import Core.Helper;
import business.HotelManager;
import business.PensionManager;
import business.RoomManager;
import business.SeasonManager;
import entity.Hotel;
import entity.Pension;
import entity.Room;
import entity.Season;

import javax.swing.*;
import java.util.ArrayList;

public class RoomView extends Layout {
    private RoomManager roomManager;
    private HotelManager hotelManager;
    private PensionManager pensionManager;
    private SeasonManager seasonManager;
    private Room room;
    private JPanel conatiner;
    private JComboBox cmb_hotel;
    private JLabel lbl_hotel;
    private JComboBox cmb_pension;
    private JLabel lbl_pension;
    private JComboBox cmb_season;
    private JLabel lbl_season;
    private JComboBox cmb_room_type;
    private JLabel lbl_room_type;
    private JLabel lbl_adult_price;
    private JTextField fld_adult_price;
    private JLabel lbl_child_price;
    private JTextField fld_child_price;
    private JTextField fld_capacity;
    private JTextField fld_square_meter;
    private JLabel lbl_capacity;
    private JLabel lbl_square_meter;
    private JRadioButton radio_tv;
    private JRadioButton radio_minibar;
    private JRadioButton radio_game_console;
    private JRadioButton radio_cash_box;
    private JRadioButton radio_projection;
    private JButton btn_room_save;


    public RoomView(Room room) {
        this.room = room;
        this.roomManager = new RoomManager();
        this.hotelManager = new HotelManager();
        this.pensionManager = new PensionManager();
        this.seasonManager = new SeasonManager();
        this.add(conatiner);
        this.guiInitilaze(450, 300);



        this.cmb_room_type.setModel(new DefaultComboBoxModel<>(Room.RoomType.values()));

        for (Hotel hotel : this.hotelManager.findAll()) {
            this.cmb_hotel.addItem(hotel.getComboItem());
        }
        this.cmb_hotel.addActionListener(e -> {
            ArrayList<Season> seasons = this.seasonManager.getSeasonByHotelId(((ComboItem) cmb_hotel.getSelectedItem()).getKey());

            cmb_season.removeAllItems();
            for (Season season : seasons) {
                cmb_season.addItem(season.getComboItem());
            }

            ArrayList<Pension> pensions = this.pensionManager.getPensionByHotelId(((ComboItem) cmb_hotel.getSelectedItem()).getKey());

            cmb_pension.removeAllItems();
            for (Pension pension : pensions) {
                cmb_pension.addItem(pension.getComboItem());
            }

        });




            this.btn_room_save.addActionListener(e -> {
                if (Helper.isFieldListEmpty(new JTextField[]{this.fld_adult_price, this.fld_child_price,fld_capacity,fld_square_meter})) {
                    Helper.showMsg("fill");
                } else {
                    boolean result;
                    ComboItem selectedHotel = (ComboItem) this.cmb_hotel.getSelectedItem();
                    this.room.setHotel_id(selectedHotel.getKey());
                    ComboItem selectedPension = (ComboItem) this.cmb_pension.getSelectedItem();
                    this.room.setPension_id(selectedPension.getKey());
                    ComboItem selectedSeason = (ComboItem) this.cmb_season.getSelectedItem();
                    this.room.setSeason_id(selectedSeason.getKey());
                    this.room.setRoom_type(this.cmb_room_type.getModel().getSelectedItem().toString());
                    this.room.setAdult_price(Integer.parseInt(this.fld_adult_price.getText()));
                    this.room.setChild_price(Integer.parseInt(this.fld_child_price.getText()));
                    this.room.setCapacity(Integer.parseInt(this.fld_capacity.getText()));
                    this.room.setSquare_meter(Integer.parseInt(this.fld_square_meter.getText()));
                    this.room.setTelevision(this.radio_tv.getModel().isSelected());
                    this.room.setMinibar(this.radio_minibar.getModel().isSelected());
                    this.room.setGame_console(this.radio_game_console.getModel().isSelected());
                    this.room.setCash_box(this.radio_cash_box.getModel().isSelected());
                    this.room.setProjection(this.radio_projection.getModel().isSelected());
                    result = this.roomManager.save(this.room);

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
