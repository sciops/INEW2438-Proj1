/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advjava.project1;

;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author dawit.gebremichael1
 * 
 * modified FinalTableModel for CompactDisc class - Stephen R. Williams, 2015
 */


public class FinalTableModel extends AbstractTableModel {

    private List<CompactDisc> list = new ArrayList();
    private String[] columnNames = {"Title", "Artist", "Company", "Country","Price","Year"};

    public FinalTableModel(List<CompactDisc> list) {
        this.list = list;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CompactDisc si = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return si.getTitle();
            case 1:
                return si.getArtist();
            case 2:
                return si.getCompany();
            case 3:
                return si.getCountry();
            case 4:
                NumberFormat formatter = new DecimalFormat("#0.00");     
                return formatter.format(si.getPrice());
            case 5:
                return si.getYear();
           
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;//sic, formatted into string in getValueAt method override
            case 5:
                return Integer.class;
           
        }
        return null;
    }
}
