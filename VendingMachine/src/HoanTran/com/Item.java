package HoanTran.com;

/**
 * Created by tnhoa on 11/2/2016.
 */
public class Item {
    private  int id;
    private  String Name;





    private  int amount;
    private  int price;

    public Item(int id, String name, int price, int amount) {
        this.id = id;
        Name = name;
        this.amount = amount;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
