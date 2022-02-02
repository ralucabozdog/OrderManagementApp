package presentation.view.general;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa pentru definirea modelului general de fereastra de stergere dintr-un tabel din baza de date
 * @param <T> tip generic ce denumeste, practic, tabelul ale carui date urmeaza sa fie selectate spre stergere
 */
public class DeleteGeneral<T> extends JFrame {
    /**
     * Tabelul cu obiecte extrase din baza de date
     */
    private JTable table;
    /**
     * Butonul prin actionarea caruia se realizeaza stergeri in baza de date
     */
    private JButton btnDelete;
    /**
     * Lista tuturor obiectelor din tabelul T din baza de date
     */
    private List<T> objectList;
    /**
     * Clasa tipului abstract declarat
     */
    private final Class<T> type;

    /**
     * Constructorul clasei DeleteGeneral
     */
    @SuppressWarnings("unchecked")
    public DeleteGeneral()
    {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Metoda de construire a tabelului pentru stergerea unor intrari dintr-un tabel al bazei de date
     * @param objectList lista tuturor obiectelor ale caror date vor fi afisate in tabel
     */
    public void buildTable(List<T> objectList){
        this.objectList = objectList;
        StringBuilder sb = new StringBuilder();
        sb.append("Delete ");
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
        this.add(sp, BorderLayout.CENTER);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnDelete = new JButton("Delete");
        btnDelete.setForeground(new Color(255, 255, 255));
        btnDelete.setFont(new Font("Copper Black", Font.BOLD, 25));
        btnDelete.setBackground(new Color(188, 143, 143));
        btnDelete.setBounds(170, 480, 150, 50);
        JPanel p2 = new JPanel();
        p2.setPreferredSize(new Dimension(900, 60));
        p2.setBackground(new Color(186, 200, 222));
        p2.add(btnDelete);
        this.add(p2, BorderLayout.SOUTH);
    }

    /**
     * Metoda ce returneaza id-ul obiectelor selectate din tabel; acestea sunt obiectele ce vor fi sterse din baza de date
     * @return lista obiectelor ce se vor sterge
     */
    public List<Integer> selectedRows(){
        List<Integer> list = new ArrayList<Integer>();

        int[] vector = table.getSelectedRows();
        for(int i = 0; i < vector.length; i++){
            T objectToDelete = objectList.get(vector[i]);
            int j = 0;
            for(Field f : type.getDeclaredFields()){
                if(j == 0){
                    f.setAccessible(true);
                    try {
                        list.add((Integer)f.get(objectToDelete));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                j++;
            }
        }
        return list;
    }

    /**
     * Metoda pentru adaugarea de listener butonului de stergere de obiecte din baza de date
     * @param delete ActionListener-ul folosit
     */
    public void addDeleteListener(ActionListener delete){this.btnDelete.addActionListener(delete);}
}
