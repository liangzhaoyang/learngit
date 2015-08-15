package cn.xxs.utility;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

public class MyDateFormat extends SimpleDateFormat {  
	private static Logger log = Logger.getLogger(MyDateFormat.class);
	private static final long serialVersionUID = -8128230406908356298L;
	public MyDateFormat(String s) {  
        super(s);  
    }  
    public Date parse(String text) throws ParseException {  
    	log.debug("text:" + text);
        DateFormat df = null;  
        if(text.length()<=10){  
            df = new SimpleDateFormat("yyyy-MM-dd");  
            return new java.sql.Date(df.parse(text).getTime());  
        }  
        java.util.Date date = super.parse(text);  
        return new java.sql.Date(date.getTime());  
    }  
}   
