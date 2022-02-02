package presentation.view.general;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Clasa pentru definirea modelului general de fereastra de vizualizare a tuturor datelor dintr-un tabel din baza de date
 * @param <T> tip generic ce denumeste, practic, tabelul ale carui date vor fi vizualizate
 */
public class ViewGeneral<T> extends JFrame{
    /**
     * Tabelul cu obiecte extrase din baza de date
     */
    private JTable table;
    /**
     * Lista tuturor obiectelor din tabelul T din baza de date
     */
    private List<T> objectList;
    /**
     * Clasa tipului abstract declarat
     */
    private final Class<T> type;

    /**
     * Constructorul clasei ViewGeneral
     */
    @SuppressWarnings("unchecked")
    public ViewGeneral()
    {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Metoda de construire a tabelului pentru vizualizarea tuturor intrarilor dintr-un tabel al bazei de date
     * @param objectList lista tuturor obiectelor ale caror date vor fi afisate in tabel
     */
    public void buildTable(List<T> objectList){
        this.objectList = objectList;
        StringBuilder sb = new StringBuilder();
        sb.append("All ");
        sb.append(type.getSimpleName());
        sb.append("s");
        setTitle(sb.toString());

        int cols = type.getDeclaredFields().length;
        int rows = objectList.size();

        String[][] objects = new String[rows][cols - 1];
        String[] columnNames = new String[cols - 1];

        int i = 0;
        for(Field f : type.getDeclaredFields()){
            if(i != 0){
                columnNames[i - 1] = f.getName();
            }
            i++;
        }
        int j = 0;
        for(T t: objectList){
            i = 0;
            for(Field f : type.getDeclaredFields()){
                if(i != 0){
                    f.setAccessible(true);
                    try {
                        objects[j][i - 1] = f.get(t).toString();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                i++;
            }
            j++;
        }

        table = new JTable(objects, columnNames);
        table.setBounds(30, 40, 200, 300);
        table.setRowHeight(30);
        table.setBackground(new Color(186, 200, 222));
        table.setFont(new Font("Copper Black", Font.PLAIN, 18));
        table.setSelectionBackground(new Color(188, 143, 143));
        table.setDefaultEditor(Object.class, null);

        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(188, 143, 143));
        header.setFont(new Font("Copper Black", Font.BOLD, 18));

        JScrollPane sp = new JScrollPane(table);
        add(sp);
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
