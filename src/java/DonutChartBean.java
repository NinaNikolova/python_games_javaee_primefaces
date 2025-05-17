import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.donut.DonutChartDataSet;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nina
 */
@ManagedBean(name = "donutChartBean")
@ViewScoped
public class DonutChartBean implements Serializable {
    private DonutChartModel donutModel;

    @PostConstruct
    public void init() {
        donutModel = new DonutChartModel();
        ChartData data = new ChartData();

        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<Number>();
          values.add(25);
        values.add(25);
        values.add(25);
        values.add(25);
        
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<String>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(54, 162, 235)");
        bgColors.add("rgb(255, 205, 86)");
        bgColors.add("rgb(75, 192, 192)");
 
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
  ResourceBundle bundle = ResourceBundle.getBundle("messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
        List<String> labels = new ArrayList<String>();
 labels.add(bundle.getString("month.january"));
        labels.add(bundle.getString("month.february"));
        labels.add(bundle.getString("month.march"));
        labels.add(bundle.getString("month.april"));

        data.setLabels(labels);

        donutModel.setData(data); // ✅ CORRECT METHOD

    }

    public DonutChartModel getDonutModel() {
        return donutModel;
    }
}
