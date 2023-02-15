
package aplication;

import java.util.Date;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;


public class DemoDaoJdbc {

   
    public static void main(String[] args) {
        Department obj = new Department(2, "Books");
        Seller seller  = new Seller(1, "Caio", "caio@gmail.com", new Date(), 3000.00, obj);
        SellerDao sellerdao = DaoFactory.createSellerDao();
        System.out.println(seller);
    }
}
