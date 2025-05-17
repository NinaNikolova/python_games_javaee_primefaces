/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nina
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "monthBean")
@ViewScoped
public class MonthBean implements Serializable {

    private List<MonthData> months;

    @PostConstruct
    public void init() {
        ResourceBundle bundle = ResourceBundle.getBundle("messages", 
                FacesContext.getCurrentInstance().getViewRoot().getLocale());
        
        months = new ArrayList<MonthData>();
        months.add(new MonthData(bundle.getString("month.january"), 25));
        months.add(new MonthData(bundle.getString("month.february"), 25));
        months.add(new MonthData(bundle.getString("month.march"), 25));
        months.add(new MonthData(bundle.getString("month.april"), 25));

    }

    // ✅ Getter for JSF to access
    public List<MonthData> getMonths() {
        return months;
    }

    // Optional Setter (not required unless you plan to modify from JSF)
    public void setMonths(List<MonthData> months) {
        this.months = months;
    }
}

