
package model.dao.impl;

import Db.DB;
import Db.DbException;
import java.sql.PreparedStatement;
import java.util.List;
import model.dao.SellerDao;
import model.entities.Seller;
import java.sql.Connection;
import java.sql.ResultSet;
import model.entities.Department;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.sql.Statement;


public class SellerDaoJDBC implements SellerDao {
    
    private Connection conn;
    
    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "insert into seller "
                   +"(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                   +"values (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
            );
            
            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
            st.setDouble(4, obj.getBaseSalary());
            st.setInt(5, obj.getDepartment().getId());
            
            int rowsAffected = st.executeUpdate();
            
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                while (rs.next()) {                    
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            } 
            else {
                throw new DbException("Houve um erro ao inserir usuario!");
            }
            
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
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
                Department dep = instantiateDepartment(rs);
                return instantiateSeller(rs, dep);
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
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            st = conn.prepareStatement(
                    "select seller.*, department.name as DepName "
                   +"from seller inner join department "
                   +"on seller.DepartmentId = department.Id "
                   +"order by name"
            );
            
            rs = st.executeQuery();
            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();
            while (rs.next()) {                
                Department dep = map.get(rs.getInt("DepartmentId"));
                
                if (dep == null) {
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }
                
                Seller seller = instantiateSeller(rs, dep);
                list.add(seller);
            }
            
            return list;
        } catch (SQLException e) {
            throw new DbException("Houve um erro ao buscar todos os seller por departamento!");
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
    
    private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
        Seller seller = new Seller();
        seller.setId(rs.getInt("Id"));
        seller.setName(rs.getString("Name"));
        seller.setEmail(rs.getString("Email"));
        seller.setBirthDate(rs.getDate("BirthDate"));
        seller.setBaseSalary(rs.getDouble("BaseSalary"));
        seller.setDepartment(dep);
        return seller;
   }
    
    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
         PreparedStatement st = null;
         ResultSet rs = null;
         
         try {
            st = conn.prepareStatement(
                      "select seller.*, department.name as DepName "
                    + "from seller inner join department on seller.DepartmentId = department.Id "
                    + "where seller.DepartmentId = ?"
            );
            st.setInt(1, department.getId());
            rs = st.executeQuery();
            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();
            while (rs.next()) {                 
                Department dep = map.get(rs.getInt("DepartmentId"));
                if (dep == null) {
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }
                
                Seller seller = instantiateSeller(rs, dep);
                list.add(seller);
             }
            return list;
        } catch (SQLException e) {
            throw new DbException("Houve um erro buscar um departamento por ID!");
        } finally {
             DB.closeStatement(st);
             DB.closeResultSet(rs);
         }
    }
    
}
