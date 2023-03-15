package projectmanager.projectManagerAdmin;

import java.util.Date;

public class Manager extends Employee{
    private Date promotionDate;

    Manager(String id, String name, String empOfficeNo, String empOfficePhone){
        super(id, name, empOfficeNo, empOfficePhone);

    }

    @Override
    public String toString() {
        return "Manager{" +
                "promotionDate=" + promotionDate +
                super.toString() +
                '}';
    }

    public Date getPromotionDate() {
        return promotionDate;
    }

    public void setPromotionDate(Date promotionDate) {
        this.promotionDate = promotionDate;
    }
}
