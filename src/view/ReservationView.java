package view;

import Core.Helper;
import business.*;
import entity.Hotel;
import entity.Reservation;
import entity.Room;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ReservationView  extends Layout {
    private JPanel container;
    private JTextField fld_hotel_name;
    private JTextField fld_hotel_address;
    private JTextField fld_hotel_star;
    private JRadioButton radio_car_park;
    private JRadioButton radio_coincerge;
    private JRadioButton radio_wifi;
    private JRadioButton radio_spa;
    private JRadioButton radio_pool;
    private JRadioButton radio_room_service;
    private JRadioButton radio_fitness;
    private JPanel pnl_hotel_info;
    private JLabel lbl_hotel_name;
    private JLabel lbl_hotel_address;
    private JLabel lbl_hotel_star;
    private JPanel pnl_room_info;
    private JTextField fld_room_type;
    private JTextField fld_pension_type;
    private JFormattedTextField fld_check_in;
    private JFormattedTextField fld_check_out;
    private JTextField fld_total_price;
    private JTextField fld_bed_capacity;
    private JTextField fld_square_meter;
    private JRadioButton radio_tv;
    private JRadioButton radio_minibar;
    private JRadioButton radio_game_console;
    private JRadioButton radio_cash_box;
    private JRadioButton radio_projection;
    private JLabel lbl_room_type;
    private JLabel lbl_pension_type;
    private JLabel lbl_check_in;
    private JLabel lbl_check_out;
    private JLabel lbl_total_price;
    private JLabel lbl_capacity;
    private JLabel lbl_square_meter;
    private JPanel pnl_guest;
    private JTextField fld_guest_count;
    private JTextField fld_guest_name;
    private JTextField fld_guest_id;
    private JTextField fld_guest_mail;
    private JTextField fld_guest_phone;
    private JButton btn_reservation;
    private JLabel lbl_guest_count;
    private JLabel lbl_guest_name;
    private JLabel lbl_guest_id;
    private JLabel lbl_guest_mail;
    private JLabel lbl_guest_phone;
    private ReservationManager reservationManager;
    private RoomManager roomManager;
    private HotelManager hotelManager;
    private Reservation reservation;
    private Hotel hotel;
    private Room room;


    public ReservationView(Room selectedRoom, Reservation reservation, String check_in, String check_out,
                           int adultNumber, int childNumber) {
        this.reservationManager = new ReservationManager();
        this.add(container);
        this.guiInitilaze(850, 600);
        this.reservation = reservation;
        this.room = selectedRoom;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate entryDate = LocalDate.parse(check_in, formatter);
        LocalDate exitDate = LocalDate.parse(check_out, formatter);
        long days = ChronoUnit.DAYS.between(entryDate, exitDate);
        int guessPrice = (adultNumber * room.getAdult_price()) + (childNumber * room.getChild_price());
        int totalPrice = (int) (guessPrice * days);
        this.roomManager = new RoomManager();


        this.fld_hotel_name.setText(this.room.getHotel().getName());
        this.fld_hotel_address.setText(this.room.getHotel().getAddress());
        this.fld_hotel_star.setText(this.room.getHotel().getStar());
        this.radio_car_park.setSelected(this.room.getHotel().isCar_park());
        this.radio_coincerge.setSelected(this.room.getHotel().isConcierge());
        this.radio_wifi.setSelected(this.room.getHotel().isWifi());
        this.radio_spa.setSelected(this.room.getHotel().isSpa());
        this.radio_room_service.setSelected(this.room.getHotel().isRoom_service());
        this.radio_fitness.setSelected(this.room.getHotel().isFitness());
        this.fld_guest_count.setText(String.valueOf(adultNumber + childNumber));
        this.fld_room_type.setText(this.room.getRoom_type());
        this.fld_pension_type.setText(this.room.getPension().getPension_Type());
        this.fld_total_price.setText(String.valueOf(totalPrice));
        this.fld_check_in.setText(check_in);
        this.fld_check_out.setText(check_out);
        this.fld_bed_capacity.setText(String.valueOf(this.room.getCapacity()));
        this.fld_square_meter.setText(String.valueOf(this.room.getSquare_meter()));
        this.radio_tv.setSelected(this.room.isTelevision());
        this.radio_minibar.setSelected(this.room.isMinibar());
        this.radio_game_console.setSelected(this.room.isGame_console());
        this.radio_cash_box.setSelected(this.room.isCash_box());
        this.radio_projection.setSelected(this.room.isProjection());


        if (reservation != null) {
            this.fld_guest_name.setText(reservation.getName());
            this.fld_guest_id.setText(reservation.getCitizen_id());
            this.fld_guest_mail.setText(reservation.getMail());
            this.fld_guest_phone.setText(reservation.getPhone());
            this.btn_reservation.setText("Reservation Update");
        }
        this.btn_reservation.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_guest_name, this.fld_guest_id, this.fld_guest_mail, this.fld_guest_phone})) {
                Helper.showMsg("fill");
            } else {
                boolean result;
                this.reservation.setRoom_id(this.room.getId());
                this.reservation.setName(fld_guest_name.getText());
                this.reservation.setCitizen_id(fld_guest_id.getText());
                this.reservation.setMail(fld_guest_mail.getText());
                this.reservation.setPhone(fld_guest_phone.getText());
                this.reservation.setCheck_in_date(LocalDate.parse(this.fld_check_in.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                this.reservation.setCheck_out_date(LocalDate.parse(this.fld_check_out.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                this.reservation.setAdult_number(adultNumber);
                this.reservation.setChild_number(childNumber);
                this.reservation.setTotal_price(Integer.parseInt(fld_total_price.getText()));
                //veritabanında eksik olanları ekle sezonviewdan bak

                if (this.reservation.getId() != 0) {
                    result = this.reservationManager.update(this.reservation);
                } else {
                    result = this.reservationManager.save(this.reservation);
                    this.room.setStock(room.getStock() - 1);
                    roomManager.updateStock(room);
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

    private void createUIComponents() throws ParseException {
        this.fld_check_in = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_check_in.setText("dd/MM/yyyy");
        this.fld_check_out = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_check_out.setText("dd/MM/yyyy");

    }
}