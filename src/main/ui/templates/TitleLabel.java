package ui.templates;

import javax.swing.*;
import java.awt.*;

public class TitleLabel extends JLabel {
    public TitleLabel(String name, int size) {
        super(name, SwingConstants.CENTER);
        this.setFont(new Font("Impact", Font.BOLD, size));
        this.setForeground(Color.white);
        this.setVisible(true);
    }
}
