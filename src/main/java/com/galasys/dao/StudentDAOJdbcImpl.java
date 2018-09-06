package com.galasys.dao;

import com.galasys.domain.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * StudentDAO访问接口实现类：通过Spring jdbc的方式访问
 */
public class StudentDAOJdbcImpl implements StudentDAO {



    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Student> query() {
       final List<Student> students = new ArrayList<Student>();

        String sql ="select id,name,age from student";

        jdbcTemplate.query(sql, new RowCallbackHandler(){


            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");

                Student student = new Student();
                student.setId(id);
                student.setAge(age);
                student.setName(name);

                students.add(student);

            }
        });

        return students;
    }

    @Override
    public void save(Student student) {
        String sql ="insert into student(name,age) values (?,?)";
        jdbcTemplate.update(sql,new Object[]{student.getName(),student.getAge()});
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


}
