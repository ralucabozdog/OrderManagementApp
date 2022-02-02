package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;

/**
 * Clasa pentru construirea interogarilor asupra bazei de date
 * @param <T> tip de data abstract (Customer, Product, Request, PlacedOrder)
 */
public class AbstractDAO<T> {

    /**
     * Logger-ul folosit pentru indicarea unor operatii efectuate intre java si mysql
     */
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    /**
     * Constructorul fara parametri al clasei seteaza tipul generic transmis
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    /**
     * Metoda pentru crearea pattern-ului unei interogerii de tipul SELECT * FROM... asupra bazei de date
     * @param field numele campului dupa care se face selectia
     * @return String-ul pattern al interogarii
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * Metoda pentru crearea unei comenzi de stergere a unor tuple din baza de date
     * @param field campul dupa care se va face stergerea
     * @return String-ul pattern al interogarii
     */
    private String createDeleteQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * Metoda pentru crearea unei interogari asupra bazei de date ce selecteaza toate intrarile din tabelul onomonim
     * cu tipul generic T
     * @return String-ul ce contine interogarea
     */
    private String createSelectAllQuery(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        return sb.toString();
    }

    /**
     * Metoda pentru crearea unui statement de actualizarea a unei tuple din tabelul omonim cu tipul generic T
     * al bazei de date
     * @param id id-ul (cheia primara) la care se va face actualizarea
     * @return String-ul pattern al interogarii
     */
    private String createUpdateQuery(int id){
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");
        int i = 0;
        for(Field f : type.getDeclaredFields()){
            if(i != 0){
                sb.append(f.getName());
                sb.append("=?, ");
            }
            i++;
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append(" WHERE id=");
        sb.append(id);
        return sb.toString();
    }

    /**
     * Metoda pentru crearea unui statement de inserare a unei tuple in baza de date in tabelul omonim cu tipul generic T
     * @param t obiectul ale carui valori de variabile instanta vor fi inserate in baza de date
     * @return String-ul pattern al interogarii
     */
    private String createInsertQuery(T t){
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append("(");
        int i = 0;
        for(Field f : type.getDeclaredFields()){
            if(i != 0){
                sb.append(f.getName());
                sb.append(", ");
            }
            i++;
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append(") VALUES (");
        i = 0;
        for(Field f : type.getDeclaredFields()){
            if(i != 0){
                sb.append("?, ");
            }
            i++;
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append(")");
        return sb.toString();
    }

    /**
     * Metoda pentru gasirea tuturor intrarilor din tabelul omonim cu tipul genetic T
     * @return lista tuturor obiectelor de tip T din tabel
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Metoda pentru crearea obiectelor folosite in java pornind de la informatiile extrase din baza de date
     * @param resultSet setul de date returnat din baza de date in urma unei interogari
     * @return Lista de obiecte de tip T, utilizabila in java
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Metoda pentru stergerea tuplei cu id-ul corespunzator din baza de date, din tabelul omonim cu tipul genetric T
     * @param id valoarea cheii straine al carei rand vai fi sters din tabel
     */
    public void delete(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Metoda pentru inserarea in baza de date a unui obiect din java
     * @param t obiectul ale carui date se vor insera in baza de date
     */
    public void insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsertQuery(t);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int i = 0;
            for(Field f : type.getDeclaredFields()){
                if(i != 0){
                    f.setAccessible(true);
                    try {
                        statement.setObject(i, f.get(t));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                i++;
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Metoda pentru realizarea unei actualizari de campuri la tupla cu id-ul dat, cu valorile date de obiectul t
     * @param id valoarea cheii straine la al carei rand se va face actualizarea
     * @param t obiectul cu care se va face actualizarea campurilor existente in baza de date
     */
    public void update(int id, T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery(id);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int i = 0;
            for(Field f : type.getDeclaredFields()){
                if(i != 0){
                    f.setAccessible(true);
                    try {
                        statement.setObject(i, f.get(t));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                i++;
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}
