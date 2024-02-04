package view;


import business.PensionManager;
import Core.Helper;
import entity.Hotel;
import entity.Pension;

import javax.swing.*;

public class PensionView extends Layout {
    private JPanel container;
    private JLabel lbl_pension_add;
    private JComboBox<Pension.pensionType> cmb_pension;
    private JLabel lbl_pension;
    private JButton btn_save_pension;
    private JLabel lbl_pension_otel_id;
    private PensionManager pensionManager;
    private Pension pension;
    private Hotel hotel;





    public PensionView(Pension pension) {
        this.pensionManager = new PensionManager();
        this.add(container);
        this.guiInitilaze(300, 250);
        this.pension = pension;

    this.cmb_pension.setModel(new DefaultComboBoxModel<>(Pension.pensionType.values()));

    btn_save_pension.addActionListener(e -> {
        boolean result;
        this.pension.setPension_Type(cmb_pension.getSelectedItem().toString());
        result = this.pensionManager.save(this.pension);
        if(result) {
            Helper.showMsg("done");
            dispose();
        }
    });
    }

}
