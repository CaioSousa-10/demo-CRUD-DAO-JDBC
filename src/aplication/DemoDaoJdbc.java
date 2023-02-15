
package aplication;

import java.util.Date;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;


public class DemoDaoJdbc {

   
    public static void main(String[] args) {
        SellerDao sellerdao = DaoFactory.createSellerDao();
        Seller seller = sellerdao.findById(3);
        System.out.println(seller);
    }
}
