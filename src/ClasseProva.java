
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marco
 */
public class ClasseProva {
    @JsonSerialize(using = IsoDateDeSerializer.class)
    private Date date;

    public ClasseProva(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
