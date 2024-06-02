package Food;

public abstract class Produs {

    //int id; //pt baza de date
    protected String name;
    protected double price;

    public Produs() {}

    public Produs( String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice(){
        return this.price;
    }

    public abstract void reader();

    @Override
    public abstract String toString();

    public String getName() {
        return name;
    }

}
