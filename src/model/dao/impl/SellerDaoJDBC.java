
package model.dao.impl;

import Db.DB;
import java.sql.PreparedStatement;
import java.util.List;
import model.dao.SellerDao;
import model.entities.Seller;
import java.sql.Connection;
import java.sql.ResultSet;
import model.entities.Department;
import java.sql.SQLException;


public class SellerDaoJDBC implements SellerDao {
    
    private Connection conn;
    
    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {
        throw new UnsupportedOperationException("Not supported yet."); //metodo para inserir um departamento no banco de dados usando o JDBC
    }

    @Override
    public void update(Seller obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            st = conn.prepareStatement(
                      "select seller.*, department.name as DepName "
                    + "from seller inner join department "
                    + "on seller.DepartmentId = department.Id "
                    + "where seller.Id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            
            if (rs.next()) {
                Department dep = new Department();
                dep.setId(rs.getInt("DepartmentId"));
                dep.setName(rs.getString("DepName"));
                Seller seller = new Seller();
                seller.setId(rs.getInt("Id"));
                seller.setName(rs.getString("Name"));
                seller.setEmail(rs.getString("Email"));
                seller.setBaseSalary(rs.getDouble("BaseSalary"));
                seller.setBirthDate(rs.getDate("BirthDate"));
                seller.setDepartment(dep);
                return seller;
            }else {
                return null;
            }
        } catch (SQLException e) {
            throw new Db.DbException("Houve um erro ao efetuar a buscar por id!");
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Seller> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
