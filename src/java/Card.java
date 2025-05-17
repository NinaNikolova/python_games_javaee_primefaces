
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nina
 */
public class Card implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String icon;
    private boolean flipped;
    private boolean matched;
    
    public Card(int id, String icon) {
        this.id = id;
        this.icon = icon;
        this.flipped = false;
        this.matched = false;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getIcon() {
        return icon;
    }
    
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    public boolean isFlipped() {
        return flipped;
    }
    
    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }
    
    public boolean isMatched() {
        return matched;
    }
    
    public void setMatched(boolean matched) {
        this.matched = matched;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Card)) {
            return false;
        }
        Card other = (Card) obj;
        return this.icon.equals(other.icon);
    }
}
