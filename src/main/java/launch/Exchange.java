package launch;

/**
 * Created with Intellij IDEA.
 * Project name: Partizanin.
 * User: Partizanin.
 * Date: 09.03.2015.
 * Time:  18:51.
 * To change this template use File|Setting|Editor|File and Code Templates.
 */
public class Exchange {

    private String name;
    private String id;

    private double buyCourse;

    private double sellCourse;

    @Override
    public String toString() {
        return "Exchange{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", buyCourse=" + buyCourse +
                ", sellCourse=" + sellCourse +
                '}';
    }

    public Exchange() {
    }

    public Exchange(String name, String id, double BuyCourse, double sellCourse) {
        this.name = name;
        this.id = id;
        this.buyCourse = BuyCourse;
        this.sellCourse = sellCourse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getCourse(String transactionValue) {
        return   transactionValue.equals("buy") ? buyCourse : sellCourse;
    }
    public double getBuyCourse() {
        return buyCourse;
    }

    public void setBuyCourse(double buyCourse) {
        this.buyCourse = buyCourse;
    }

    public double getSellCourse() {
        return sellCourse;
    }

    public void setSellCourse(double sellCourse) {
        this.sellCourse = sellCourse;
    }
}
