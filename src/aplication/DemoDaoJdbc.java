
package aplication;

import java.util.Date;
import java.util.List;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;


public class DemoDaoJdbc {

   
    public static void main(String[] args) {
        SellerDao sellerdao = DaoFactory.createSellerDao();
        System.out.println("->>>> teste 1: seller findById <<<<<<");
        Seller seller = sellerdao.findById(3);
        System.out.println(seller);
        
        System.out.println("->>>> teste 2: seller findByDepartment <<<<<<");
        
        List<Seller> list = sellerdao.findByDepartment(new Department(2, "Electronics"));
        for (Seller seller1 : list) {
            System.out.println(seller1);
        }
    }
}
