package view;

import Core.Helper;
import business.SeasonManager;
import entity.Hotel;
import entity.Season;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SeasonView extends Layout {
    private JPanel container;
    private JLabel lbl_season;
    private JLabel lbl_season_otel_id;
    private JLabel lbl_strt_date;
    private JTextField fld_strt_date;
    private JLabel lbl_fnsh_date;
    private JTextField fld_fnsh_date;
    private JButton btn_save_season;
    private SeasonManager seasonManager;
    private Season season;
    private Hotel hotel;


    public SeasonView(Season season) {
        this.seasonManager = new SeasonManager();
        this.add(container);
        this.guiInitilaze(300, 250);
        this.season = season;

       btn_save_season.addActionListener(e -> {
        boolean result;
        season.setStrt_date(LocalDate.parse(this.fld_strt_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        season.setFnsh_date(LocalDate.parse(this.fld_fnsh_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            result = this.seasonManager.save(this.season);
            if(result) {
                Helper.showMsg("done");
                dispose();
            }else {
                Helper.showMsg("error");
            }

        });


    }

    //tarih formatı maskelemeyi dönemde kullanacaz
    private void createUIComponents() throws ParseException {
        this.fld_strt_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_strt_date.setText("dd/MM/yyyy");
        this.fld_fnsh_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_fnsh_date.setText("dd/MM/yyyy");


    }
}