package org.main.util;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
    private String datePatten = "yyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePatten);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if(value != null ){
            Calendar cal = (Calendar)value;
            return dateFormatter.format(cal.getTime());
        }
    return "";
    }

}
