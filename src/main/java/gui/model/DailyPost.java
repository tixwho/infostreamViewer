package gui.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DailyPost {
    
    private final StringProperty USERNAME;
    private final IntegerProperty POSTCOUNT;
    private final BooleanProperty ISFREQUENT;
    public DailyPost(String usrname, int postcnt) {
        this.USERNAME=new SimpleStringProperty(usrname);
        this.POSTCOUNT=new SimpleIntegerProperty(postcnt);
        this.ISFREQUENT=new SimpleBooleanProperty(false);
        //System.out.println(ISFREQUENT.get());
    }
    public DailyPost(String usrname, int postcnt, boolean isfrequent) {
        this.USERNAME=new SimpleStringProperty(usrname);
        this.POSTCOUNT=new SimpleIntegerProperty(postcnt);
        this.ISFREQUENT=new SimpleBooleanProperty(isfrequent);
        //System.out.println(ISFREQUENT.get());
    }
    public String getUSERNAME() {
        return USERNAME.get();
    }
    public void setUSERNAME(String username) {
        this.USERNAME.set(username);
    }
    public StringProperty USERNAMEProperty() {
        return this.USERNAME;
    }
    public Integer getPOSTCOUNT() {
        return POSTCOUNT.get();
    }
    public IntegerProperty POSTCOUNTProperty() {
        return this.POSTCOUNT;
    }
    public void setPOSTCOUNT(int postcount) {
        this.POSTCOUNT.set(postcount);
    }
    public boolean getISFREQUENT() {
        return ISFREQUENT.get();
    }
    public BooleanProperty ISFREQUENTProperty() {
        return this.ISFREQUENT;
    }
    public void setISFREQUENT(boolean isfrequent) {
        this.ISFREQUENT.set(isfrequent);
    }

}
