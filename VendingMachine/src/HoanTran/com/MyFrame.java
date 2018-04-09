
package HoanTran.com;
/**
 * Created by tnhoa on 11/8/2016.
 */


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyFrame extends JFrame implements ActionListener {
    private String TITLE = "VENDING MACHINE";
    private String url = System.getProperty("user.dir");
    private JButton btnok,btdelete,btninsertmoney;
    private String listitem = new String("Your Cart:\r\n");
    private JTextArea listview = new JTextArea(listitem);
    private String[] money = new String[]{"0", "5", "10", "20"};
    private JLabel urmoney;
    private String selectnow="0";
    private JButton[] btnn = new JButton[999];
    private Vending VD = new Vending();
    int number = VD.getNumber();


    public MyFrame() {


        this.setSize(500, 650);
        //set display
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(TITLE);
        this.add(createMainPanel());
        //pack(); // kick thuoc tu dong
        setLocationRelativeTo(null);// xuat hien giua man hinh
        // setResizable(false);//set kick thuoc
        setVisible(true);// xuat hien


    }


    private JPanel createMainPanel() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.white);

        panel.setBackground(new Color(32, 64, 111));
        panel.add(createTilePanel(), BorderLayout.PAGE_START);

        panel.add(createButtonPanel(), BorderLayout.PAGE_END);

        panel.add(new JScrollPane(createListPanel()), BorderLayout.CENTER);

        panel.add(new JScrollPane(creatlistview()), BorderLayout.EAST);
        panel.add(west(), BorderLayout.WEST);

        return panel;

    }

    private JPanel west() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setOpaque(false);
        return panel;
    }



    private JPanel createListPanel() {
        int n = number + 1;


        JPanel panel = new JPanel(new GridLayout(n, 1, 5, 5));
        int i;
        panel.setBorder(new EmptyBorder(5, 0, 0, 0));
        //panel.setOpaque(false);
        panel.setBackground(new Color(110, 129, 197));
        for (i = 1; i < number + 1; i++)
            if (VD.getItem(i).getAmount() > 0) {
                JButton btn = new JButton(VD.getItem(i).getName() +
                        " " + Integer.toString(VD.getItem(i).getPrice()) +
                        "$ (" + VD.getItem(i).getAmount() + ")");
                btn.setHorizontalAlignment(SwingConstants.LEFT);//can le trai
                btn.setBackground(new Color(243, 255, 250));
                btn.setIcon(new ImageIcon(url + "\\src\\HoanTran\\resource\\item\\" + VD.getItem(i).getName() + ".png"));
                btnn[i] = btn;
                btnn[i].addActionListener(this);
                panel.add(btn);

            }
        return panel;
    }


    public JLabel setPicture(String filename, int y, int x) {
        JLabel label = new JLabel();
        try {
            BufferedImage image = ImageIO.read(new File(filename));

            ImageIcon icon = new ImageIcon(image.getScaledInstance(y, x, Image.SCALE_SMOOTH));
            label.setIcon(icon);
        } catch (IOException e) {
            e.printStackTrace();

        }
        return label;
    }

    private JPanel createTilePanel() {
        JPanel panel = new JPanel();

        JLabel lbTitle = new JLabel();
        lbTitle = setPicture(url + "\\src\\HoanTran\\resource\\til.png", 470, 60);

        panel.setOpaque(false);
        //  panel.setBackground(new Color(32, 64, 111));


        panel.add(lbTitle, BorderLayout.CENTER);


        return panel;
    }


    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 5, 5, 10));
        panel.setBackground(Color.white);
        panel.setBorder(new EmptyBorder(10, 5, 10, 0));
        creatInsertMoney(panel);
        //panel.add(yourMoney());
        creatbtnok();
        creatbtdelete();
        creatbtnisnertmoney();
        panel.add(btdelete);


        panel.add(btninsertmoney);
        panel.add(west());
        panel.add(btnok);
        panel.add(west());
        panel.add(west());



        btninsertmoney.addActionListener(this);
        btnok.addActionListener(this);
        btdelete.addActionListener(this);
        panel.setOpaque(false);
        return panel;


    }

    private void resetMoney() {
        urmoney.setText(Integer.toString(VD.getMoney()) + "$");
    }

    private JPanel yourMoney() {
        JPanel panel = new JPanel();


        urmoney = new JLabel(Integer.toString(VD.getMoney()));
        urmoney.setForeground(Color.WHITE);
        panel.setOpaque(false);
        panel.add(urmoney, BorderLayout.WEST);

        return panel;


    }

    private void creatbtdelete(){
        btdelete = creatButton("Delete all");



        btdelete.setBackground(Color.white);
        btdelete.setForeground(new Color(32, 64, 111));
    }
    private void creatbtnisnertmoney() {

        btninsertmoney = creatButton("Insert");

        // btnok.setSize(25, 25);

        btninsertmoney.setBackground(Color.white);
        btninsertmoney.setForeground(new Color(32, 64, 111));


    }

    private void creatbtnok() {

        btnok = creatButton("Purchase");

       // btnok.setSize(25, 25);

        btnok.setBackground(Color.white);
        btnok.setForeground(new Color(32, 64, 111));


    }

    private void creatInsertMoney(JPanel panell) {


        JComboBox cb = new JComboBox(money);//  select money


        JLabel inM = new JLabel("Insert Money:");
        inM.setForeground(Color.WHITE);
        panell.add(inM);

        panell.add(cb);
        cb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox jcmbType = (JComboBox) e.getSource();
                String cmbType = (String) jcmbType.getSelectedItem();
                selectnow=cmbType;

                //urmoney.setText(Integer.toString(VD.getMoney())+"$");
            }
        });

        JLabel lbTitle = new JLabel();
        lbTitle = setPicture(url + "\\src\\HoanTran\\resource\\cash1.png", 30, 30);

        panell.add(yourMoney());
        panell.add(lbTitle);


    }

    private JButton creatButton(String text) {
        JButton btn = new JButton(text);
        btn.setSize(10, 10);
        return btn;

    }

    private JPanel creatlistview() {
        JPanel panel = new JPanel();

        panel.setBackground(new Color(56, 115, 195));
        listview.setForeground(Color.white);

        listview.setOpaque(false);
        listview.setFocusable(false);
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.add(listview);

        return panel;
    }
    private void returnItem(){
        VD.reItem();
        resetFrame();
        for (int i = 1; i < number + 1; i++)
            if (VD.getItem(i).getAmount() > 0) {
                btnn[i].setText( VD.getItem(i).getName() +
                        " " + Integer.toString(VD.getItem(i).getPrice()) +
                        "$ (" + VD.getItem(i).getAmount() + ")");






            }

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        //  de t bat click, gop cac botton
        int n = -1;
        JButton b = (JButton) e.getSource();
        if(b==btninsertmoney){
            VD.insertMoney(Integer.parseInt(selectnow));
            resetMoney();
        }
        if(b==btdelete){
           returnItem();
        }
        else
        if (b == btnok) {        // click ok
            int outmn = VD.Stopbuy();

            JOptionPane.showMessageDialog(null, "THANK YOU! ", "VENDING_MACHINE", JOptionPane.CLOSED_OPTION);
            if ((5 > VD.getMoney()) && (VD.getMoney() > 0)) {
                JOptionPane.showMessageDialog(null, "Thank you for donation " + Integer.toString(VD.getMoney() - outmn) + "$", "VENDING_MACHINE", JOptionPane.CLOSED_OPTION);
            }
            if (outmn > 0) {
                JOptionPane.showMessageDialog(null, "RETURN " + Integer.toString(outmn) + " $", "VENDING_MACHINE", JOptionPane.CLOSED_OPTION);
                if (outmn < VD.getMoney()) {
                    JOptionPane.showMessageDialog(null, "Thank you for donation " + Integer.toString(VD.getMoney() - outmn) + "$ !", "VENDING_MACHINE", JOptionPane.CLOSED_OPTION);
                }
            }
            JOptionPane.showMessageDialog(null,listitem,"ORDER:",JOptionPane.CLOSED_OPTION);
            VD.Stopbuy();

            VD.resetVending();
            resetFrame();


        } else // click item
            for (int i = 1; i < number + 1; i++) {
                if (b == btnn[i]) {
                    n = i;
                    break;
                }
            }
        if (n > 0) select(n);


    }

    private void select(int i) {
        if (i > 0) {
            int bt = JOptionPane.showConfirmDialog(null, "Do you realy want to buy!", TITLE, JOptionPane.OK_OPTION);
            if (bt == 0) {
                String m = JOptionPane.showInputDialog(null, "Quantity:", "1");

                String ss = VD.selectItem(i, m);
                resetMoney();
                if (ss == "") {


                    //listitem += VD.getItem(i).getName() + "x" + m + "\r\n";
                    //listview.setText(listitem);
                    resetListItem();
                    btnn[i].setText(VD.getItem(i).getName() +
                            " " + Integer.toString(VD.getItem(i).getPrice()) +
                            "$ (" + VD.getItem(i).getAmount() + ")");
                } else if (ss != "0") JOptionPane.showMessageDialog(null, ss, TITLE, JOptionPane.DEFAULT_OPTION);

            }

        }
    }
    private void resetListItem(){
        listitem = "Your Cart: \r\n";
        listitem+=VD.listItem();
        listview.setText(listitem);
    }
    private void resetFrame() {
        resetMoney();
        resetListItem();



    }


}


