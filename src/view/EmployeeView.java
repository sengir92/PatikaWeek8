package view;

import Core.ComboItem;
import Core.Helper;
import business.*;
import entity.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EmployeeView extends Layout {
    private JPanel container;
    private JTabbedPane tab_menu;
    private JPanel pnl_hotel;
    private JTable tbl_hotel;
    private JTable tbl_pension;
    private JTable tbl_season;
    private JButton btn_hotel_add;
    private JScrollPane scrl_hotel;
    private JLabel lbl_welcome;
    private JScrollPane scrl_pension;
    private JPanel pnl_pension;
    private JLabel lbl_pension;
    private JLabel lbl_season;
    private JScrollPane scrl_season;
    private JPanel pnl_season;
    private JPanel pnl_room;
    private JTextField fld_hotel_name;
    private JTextField fld_city;
    private JFormattedTextField fld_check_in;
    private JFormattedTextField fld_check_out;
    private JButton btn_reset_room;
    private JButton btn_search_room;
    private JButton btn_room_add;
    private JTable tbl_room;
    private JLabel lbl_hotel_name;
    private JLabel lbl_city;
    private JLabel lbl_check_in;
    private JLabel lbl_check_out;
    private JLabel lbl_adult;
    private JLabel lbl_child;
    private JScrollPane scrl_room;
    private JComboBox cmb_adult_number;
    private JComboBox cmb_child_number;
    private JPanel pnl_res;
    private JScrollPane scrl_res;
    private JTable tbl_res;
    private User user;
    private UserManager userManager;
    private DefaultTableModel tmdl_hotel = new DefaultTableModel();
    private DefaultTableModel tmdl_pension = new DefaultTableModel();
    private DefaultTableModel tmdl_season = new DefaultTableModel();
    private DefaultTableModel tmdl_room = new DefaultTableModel();
    private DefaultTableModel tmdl_reservation = new DefaultTableModel();
    private HotelManager hotelManager;
    private PensionManager pensionManager;
    private SeasonManager seasonManager;
    private RoomManager roomManager;
    private ReservationManager reservationManager;
    private JPopupMenu hotelMenu;
    private JPopupMenu roomMenu;
    private JPopupMenu reservationMenu;
    private Object[] col_hotel;
    private Object[] col_pension;
    private Object[] col_room;
    private Object[] col_res;
    private JFormattedTextField fld_fnsh_date;
    private JFormattedTextField fld_strt_date;


    public EmployeeView(User user) {
        this.user = user;
        this.userManager = new UserManager();
        this.hotelManager = new HotelManager();
        this.pensionManager = new PensionManager();
        this.seasonManager = new SeasonManager();
        this.roomManager = new RoomManager();
        this.reservationManager = new ReservationManager();
        this.add(container);
        this.guiInitilaze(1200, 500);


        loadHotelComponent();
        loadHotelTable();


        loadPensionTable();
        loadSeasonTable();

        loadRoomTable(null);
        loadRoomComponent();

        loadReservationTable();
        loadReservationComponent();


        this.tbl_hotel.setComponentPopupMenu(hotelMenu);
    }

    public void loadHotelComponent() {

        //otele pansiyon ekleme
        tableRowSelect(this.tbl_hotel); //pensiontable
        this.hotelMenu = new JPopupMenu();
        this.hotelMenu.add("Pension Add").addActionListener(e -> {
            int selectHotelId = this.getTableSelectionRow(this.tbl_hotel, 0); //pensiontable
            PensionView pensionView = new PensionView(new Pension(selectHotelId));
            pensionView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPensionTable();
                }
            });


        });

        this.tbl_hotel.setComponentPopupMenu(hotelMenu);
        //otele sezon ekleme
        this.hotelMenu.add("Season Add").addActionListener(e -> {
            int selectHotelId = this.getTableSelectionRow(this.tbl_hotel, 0);
            SeasonView seasonView = new SeasonView(new Season(selectHotelId));
            seasonView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadSeasonTable();
                }
            });
        });


        this.tbl_hotel.setComponentPopupMenu(hotelMenu);

        this.btn_hotel_add.addActionListener(e -> {
            HotelView hotelView = new HotelView(new Hotel());
            hotelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();
                }
            });

        });


    }

    public void loadHotelTable() {
        Object[] col_hotel = {"ID", "Hotel Name", "Hotel Address", "Hotel mail",
                "Hotel Phone", "Hotel Star", "Hotel Car Park", "Hotel Wifi", "Hotel Pool",
                "Hotel Fitness", "Hotel Concierge", "Hotel Spa", "Hotel Room Service"};
        ArrayList<Object[]> hotelList = this.hotelManager.getForTable(col_hotel.length, this.hotelManager.findAll());
        createTable(this.tmdl_hotel, this.tbl_hotel, col_hotel, hotelList);

    }


    public void loadPensionTable() {
        Object[] col_pension = {"ID", "Hotel ID", "Pension Type"};
        ArrayList<Object[]> pensionList = this.pensionManager.getForTable(col_pension.length, this.pensionManager.findAll());
        createTable(this.tmdl_pension, this.tbl_pension, col_pension, pensionList);
    }

    public void loadSeasonTable() {
        Object[] col_season = {"ID", "Hotel ID", "Start Date", "Finish Date"};
        ArrayList<Object[]> seasonList = this.seasonManager.getForTable(col_season.length, this.seasonManager.findAll());
        createTable(this.tmdl_season, this.tbl_season, col_season, seasonList);

    }

    public void loadRoomTable(ArrayList<Object[]> roomList) {
         col_room = new Object[] {"ID", "Hotel Name","Hotel Address", "Pension Type", "Season Start Date", "Season Finish Date",
                "Room Type", "Room Stock", "Room Adult Price", "Room Child Price", "Room Capacity",
                "Room Squaremeter", "Television", "Minibar", "Game Console", "Cash Box", "Projection"};
        if (roomList == null) {
            roomList = this.roomManager.getForTable(col_room.length, this.roomManager.findAll());
        }
        createTable(this.tmdl_room, this.tbl_room, col_room, roomList);
    }

    public void loadRoomComponent() {
        tableRowSelect(this.tbl_room); //booktable
        this.roomMenu = new JPopupMenu();
        this.roomMenu.add("Reservation").addActionListener(e2 -> {
            int selectRoomId = this.getTableSelectionRow(this.tbl_room, 0);
            ComboItem selectAdult = (ComboItem) this.cmb_adult_number.getSelectedItem();
            int selectedAdult = selectAdult.getKey();
            ComboItem selectChild = (ComboItem) this.cmb_child_number.getSelectedItem();
            int selectedChild = selectChild.getKey();
            ReservationView reservationView = new ReservationView(
                    roomManager.getById(selectRoomId),
                    new Reservation(),
                    fld_check_in.getText(),
                    fld_check_out.getText(),
                    (selectedAdult),
                    (selectedChild));
            reservationView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable(null);
                    loadReservationTable();
                }
            });


        });
        this.tbl_room.setComponentPopupMenu(roomMenu);

            this.btn_room_add.addActionListener(e -> {
                RoomView roomView = new RoomView(new Room());
                roomView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadRoomTable(null);
                    }
                });


            });
            addAdultChildCmb(this.cmb_adult_number, this.cmb_child_number);
            this.btn_search_room.addActionListener(e1 -> {
                ComboItem selectAdult = (ComboItem) this.cmb_adult_number.getSelectedItem();
                int selectedAdult = selectAdult.getKey();
                ComboItem selectChild = (ComboItem) this.cmb_child_number.getSelectedItem();
                int selectedChild = selectChild.getKey();
                ArrayList<Room> roomList = this.roomManager.searchForBooking(
                        fld_check_in.getText(),
                        fld_check_out.getText(),
                        (selectedAdult + selectedChild),
                        fld_hotel_name.getText(),
                        fld_city.getText()


                );
                ArrayList<Object[]> roomBookingRow = this.roomManager.getForTable(this.col_room.length, roomList);
                loadRoomTable(roomBookingRow);
            });
            this.btn_reset_room.addActionListener(e1 -> {
                cmb_child_number.setSelectedItem(null);
                cmb_adult_number.setSelectedItem(null);
                loadRoomTable(null);
            });


    }
         public void loadReservationTable() {
            Object[] col_reservation = {"ID", "Room ID", "Check In Date", "Check Out Date", "Total Price",
                                        "Adult Number", "Child Number",  "Guest Name", "Guest Citizenship Id","Guest Mail","Guest Phone"};
            ArrayList<Object[]> reservationList = this.reservationManager.getForTable(col_reservation.length, this.reservationManager.findAll());
            createTable(this.tmdl_reservation, this.tbl_res, col_reservation, reservationList);

        }

        public void loadReservationComponent() {
            tableRowSelect(this.tbl_res);
            this.reservationMenu = new JPopupMenu();
            this.reservationMenu.add("Update").addActionListener(e2 -> {
                int selectReservationId = this.getTableSelectionRow(this.tbl_res, 0);
                Reservation selectReservation = this.reservationManager.getById(selectReservationId);
                //Seçtiğimiz rezervasyonun room nesnesi bulunur
                int selectRoomId = selectReservation.getRoom_id();
                Room selectRoom = this.roomManager.getById(selectRoomId);
                ReservationView reservationView = new ReservationView(
                        selectRoom,
                        selectReservation,
                        selectReservation.getCheck_in_date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        selectReservation.getCheck_out_date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        selectReservation.getAdult_number(),
                        selectReservation.getChild_number()

                );
                reservationView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadReservationTable();
                    }
                });
        });
            this.tbl_res.setComponentPopupMenu(reservationMenu);
            this.reservationMenu.add("Delete").addActionListener(e -> {
                if (Helper.confirm("sure")) {
                    int selectResID = this.getTableSelectionRow(tbl_res,0);
                    int selectRoomID = reservationManager.getById(selectResID).getRoom_id();
                    Room selectRoom =roomManager.getById(selectRoomID);
                    selectRoom.setStock(selectRoom.getStock() + 1);
                    roomManager.updateStock(selectRoom);
                    if(this.reservationManager.delete(selectResID)){
                        Helper.showMsg("done");

                        loadReservationTable();
                        loadRoomTable(null);
                    }else {
                        Helper.showMsg("error");
                    }
                }
            });

        }

    private void addAdultChildCmb(JComboBox<ComboItem> adultNumber, JComboBox<ComboItem> childNumber) {
        Object[] adultList = {
                new ComboItem(0, " "),
                new ComboItem(1, "1 Adult"),
                new ComboItem(2, "2 Adult"),
                new ComboItem(3, "3 Adult"),
                new ComboItem(4, "4 Adult"),
                new ComboItem(5, "5 Adult")
        };
        Object[] childList = {
                new ComboItem(0, " "),
                new ComboItem(1, "1 Child "),
                new ComboItem(2, "2 Child "),
                new ComboItem(3, "3 Child "),
                new ComboItem(4, "4 Child "),
                new ComboItem(5, "5 Child "),
        };

        for (Object obj : adultList) {
            ComboItem comboItem = (ComboItem) obj;
            adultNumber.addItem(comboItem);
        }

        for (Object obj : childList) {
            ComboItem comboItem = (ComboItem) obj;
            childNumber.addItem(comboItem);
        }

    }


    private void createUIComponents() throws ParseException {
        this.fld_check_in = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_check_in.setText("dd/MM/yyyy");
        this.fld_check_out = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_check_out.setText("dd/MM/yyyy");


    }
}






