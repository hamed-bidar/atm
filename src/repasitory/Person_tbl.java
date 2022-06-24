package repasitory;

import model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Person_tbl {

    private Connection conn = null;

    public Person_tbl(Connection conn) {
        this.conn = conn;
    }

    public long insertPerson(Person person ){

        long dbId=0;
        String SQL = "INSERT INTO person ( name,family,nationalcode,age) "
                + "VALUES(?,?,?,? )";
        try{
            PreparedStatement pstmt = conn.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1,person.getName());
            pstmt.setString(2,person.getFamily());
            pstmt.setString(3,person.getNationalCode());
            pstmt.setLong(4,person.getAge());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        dbId = rs.getLong("id");
                        System.out.println("person " + rs.getString("name") + " created.");

                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


        return dbId;
    }

    public List<Person> getAllPersons(){
        List<Person> personList=new ArrayList<>();

        String SQL = "SELECT * FROM person";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = null;
        try {
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String family = resultSet.getString("family");
                String nc =resultSet.getString("nationalcode");
                long age =resultSet.getLong("age");


                Person person = new Person();
                person.setId(id);
                person.setName(name);
                person.setFamily(family);
                person.setNationalCode(nc);
                person.setAge(age);

                personList.add(person);


            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return personList;
    }

    public  Person getPersonById(long id) {
        Person person = new Person();
        person.setId(id);
        String SQL = "SELECT  * from person where id=" + id;
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = null;
        try {
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {

                String name = resultSet.getString("name");
                String family = resultSet.getString("family");
                String nc = resultSet.getString("nationalcode");
                long age = resultSet.getLong("age");
                Person_tbl person_tbl = new Person_tbl(conn);

                person.setId(id);
                person.setName(name);
                person.setFamily(family);
                person.setNationalCode(nc);
                person.setAge(age);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }


}
