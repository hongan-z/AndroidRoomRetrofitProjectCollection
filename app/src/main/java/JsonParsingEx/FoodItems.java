package JsonParsingEx;

// create a model class
public class FoodItems {

    public int id;
    public String name, price, imageurl;

    public FoodItems(int id, String name, String price, String imageurl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageurl = imageurl;
    }

    public FoodItems() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
