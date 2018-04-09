package HoanTran.com;

/**
 * Created by tnhoa on 11/6/2016.
 */



import java.io.*;
import java.util.*;


class Vending {
    String url=System.getProperty("user.dir");

    private Item[] item = new Item[999];
    private int money = 0;// tien vao
    private int number = 0;// so hang trong kho
    private int[] InItem;// so luong hang trong cart

    int nn = 0;// so luong hang da mua



    public Vending() {
        try {
            input_file();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");

        }
        InItem=new int[number+1];
        for(int i=0;i<=number;i++)InItem[i]=0;
    }

    public int getMoney() {
        return money;
    }



    static String standardized(String stt) {// chuan hoa xau
        String st = stt;
        int n = st.length() - 1;


        while (st.charAt(0) == ' ') {
            st = st.substring(1, n + 1);
            n--;
        }


        while (st.charAt(n) == ' ') {
            st = st.substring(0, n);
            n--;
        }

        return st;
    }// chuan hoa xau khi ghi file

    void input_file() throws FileNotFoundException {


        FileInputStream fi = new FileInputStream(url+"\\itemList_3.txt");
        Scanner inp = new Scanner(fi, "UTF-8");
        String temp = new String();
        temp = "";
        item[0] = new Item(0, "0", 0, 0);

        while (true) {

            try {
                temp = inp.nextLine(); //doc dong mang trong file
            } catch (Exception e) {
                break;
            }

            String[] itemm = new String[3];//temp.split(" "); //tach chuoi thanh cac phan tu chuoi
            itemm[0] = standardized(temp.substring(temp.length() - 3, temp.length()));// tach amount
            itemm[1] = standardized(temp.substring(temp.length() - 7, temp.length() - 3));//tach price
            itemm[2] = standardized(temp.substring(0, temp.length() - 8));//tach name
            number++;
            Item iem = new Item(number, itemm[2], Integer.parseInt(itemm[1]), Integer.parseInt(itemm[0]));
            item[number] = iem;
            //System.out.println(item[number].getName());
            //Item(id,name,price,mount)

            System.out.println();


        }


        int i;
        for (i = 1; i <= number; i++) {
            System.out.println(item[i].getId() + " " + item[i].getName()
                    + " " + item[i].getPrice() + " " + item[i].getAmount());
        }


        inp.close();
        try {
            fi.close();
        } catch (IOException e) {
            System.out.println("Eror!");
            ;
        }
    }// doc file

    public Item getItem(int i) {


        return item[i];


    }// lay item i

    public int getNumber() {
        return number;
    }// lay so hang trong kho

    public void update() {
        FileOutputStream fop = null;
        File file;


        try {

            file = new File(url+"\\itemList_3.txt");
            fop = new FileOutputStream(file);

            // neu khong ton tai file, tao file
            if (!file.exists()) {
                file.createNewFile();
            }

            // chuyen noi dung thanh byte
            int i;

            byte[] Ent = "\r\n".getBytes();


            i = 1;
            while (i <= number) {


                String Prs = String.valueOf(item[i].getPrice());
                while (Prs.length() < 3) Prs = "0" + Prs;

                String Ams = String.valueOf(item[i].getAmount());
                while (Ams.length() < 3) Ams = "0" + Ams;

                String itemout = item[i].getName() + "  " + Prs + " " + Ams;
               // System.out.println(Prs+Ams+itemout);
                byte[] itm = itemout.getBytes();
                fop.write(itm);
                fop.write(Ent);


                fop.flush();

                i++;
            }

            fop.close();
            System.out.println("Done");


        } catch (IOException e) {
            System.out.println(" write file error");;
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }// ghi vao file

    public void insertMoney(int momo) {
        money = money + momo;
    }// them tien

    public String listItem(){
        String text=new String();
        text="";
        for(int i=1;i<number;i++){

            if(InItem[i]>0){

                text=text+item[i].getName()+"x"+Integer.toString(InItem[i])+"\r\n";
            }
        }

        return  text;
    }//tra ve danh sach san pham da chon

    public  void reItem(){
        for(int i=1;i<=number;i++){
            if(InItem[i]>0){
                money=money+item[i].getPrice()*InItem[i];
                item[i].setAmount(item[i].getAmount()+InItem[i]);
                InItem[i]=0;

            }
        }
    }//khi xoa san pham

    public String selectItem(int i,String m) {
        int check=0;
        try {
            check = Integer.parseInt(m);
        }
        catch (Exception e){check=-1;}

        if(check==0)return "0";
        if(check<1) return "Fail to quantity!";
        if ((item[i].getPrice()*check > money) || (item[i].getAmount() < check))// kt san pham thoa man
        {
            if ((item[i].getAmount() < check)) return ("Sorry, we don't have any left!");// het hang
            else if (item[i].getPrice()*check > money) return ("Sorry, You Do Not Have  Enough Money!");//ko du tien

        } else {

            item[i].setAmount(item[i].getAmount() - check);


            InItem[i]+=check;
            money = money - item[i].getPrice()*check;
            return "";
        }
        return "FALSE!";
    }// chon item i so luong m

    public void resetVending() {

        money = 0;// tien vao

        InItem=new int[number+1];

        nn = 0;// so luong hang da mua


        update();
    }//reset khi ket thuc mua hang

    public int Stopbuy() {

        if (money - (money / 5) * 5 == 0) return money;
        else {
            return ((money / 5) * 5);
        }


    }// tra lai tien thua neu co

}



