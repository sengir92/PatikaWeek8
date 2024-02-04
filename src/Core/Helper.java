package Core;

import javax.swing.*;
import java.awt.*;

public class Helper {
    public static void setTheme() {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
        }

    }

    public static int getLocationPoint(String type, Dimension size) {
        return switch (type) {
            case "x" -> (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
            case "y" -> (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
            default -> 0;
        };
    }

    public static void showMsg(String str) {

        String msg;
        String title;

        switch (str) {
            case "fill":
                msg = "Please fill in all fields !";
                title = "Error!";
                break;
            case "done":
                msg = "Transaction successful !";
                title = "End";
                break;
            case "notFound":
                msg = "Registration not found !";
                title = "Not found";
                break;
            case "error":
                msg = "You made a mistake !";
                title = "Error";
                break;

            default:
                msg = str;
                title = "Message";
        }

        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirm(String str) {
        String msg;
        if (str.equals("sure")) {
            msg = "Are you sure you want to delete ? ";
        } else {
            msg = str;
        }
        return JOptionPane.showConfirmDialog(null, msg, "Are you sure ?", JOptionPane.YES_NO_OPTION) == 0;
    }

    public static boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }

    public static boolean isFieldListEmpty(JTextField[] fieldList) {
        for (JTextField field : fieldList) {
            if (isFieldEmpty(field)) return true;
        }
        return false;
    }
}