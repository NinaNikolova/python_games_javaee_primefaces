/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Bean for managing application language
 * @author nina
 */
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

@ManagedBean(name = "languageBean")
@SessionScoped
public class LanguageBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String localeCode = "en";
    
    private static Map<String, Object> countries;
    static {
        countries = new LinkedHashMap<String, Object>();
        countries.put("EN", "en");
        countries.put("BG", "bg");
    }
    
    public Map<String, Object> getCountries() {
        return countries;
    }
    
    public String getLocaleCode() {
        return localeCode;
    }
    
    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }
    
    public void changeLanguage(ValueChangeEvent event) {
        String newLocaleValue = event.getNewValue().toString();
        this.localeCode = newLocaleValue;
        
        // Update the locale
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(this.localeCode));
    }
}
