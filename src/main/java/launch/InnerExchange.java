package launch;

/**
 * Created with Intellij IDEA.
 * Project name: cashTestConverter.
 * User: Partizanin.
 * Date: 29.03.2015.
 * Time:  16:35.
 * To change this template use File|Setting|Editor|File and Code Templates.
 */
public class InnerExchange {

    private String id;

    private String bankName;

    private double buyCourse;

    private double sellCourse;

    public InnerExchange() {
    }

    public InnerExchange(String id, String bankName, double buyCourse, double sellCourse) {
        this.id = id;
        this.bankName = bankName;
        this.buyCourse = buyCourse;
        this.sellCourse = sellCourse;
    }

    public double getCourseByTransactionValue(String transactionValue) {
        return transactionValue.equals("buy") ? buyCourse : sellCourse;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBuyCourse(double buyCourse) {
        this.buyCourse = buyCourse;
    }

    public void setSellCourse(double sellCourse) {
        this.sellCourse = sellCourse;
    }

    public double getBuyCourse() {
        return buyCourse;
    }

    public double getSellCourse() {
        return sellCourse;
    }

    @Override
    public String toString() {
        return "InnerExchange{" +
                "id='" + id + '\'' +
                ", bankName='" + bankName + '\'' +
                ", buyCourse=" + buyCourse +
                ", sellCourse=" + sellCourse +
                '}';
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
