
package aplication;

import java.util.Date;
import model.entities.Department;
import model.entities.Seller;


public class DemoDaoJdbc {

   
    public static void main(String[] args) {
        Department obj = new Department(2, "Books");
        Seller seller  = new Seller(1, "Caio", "caio@gmail.com", new Date(), 3000.00, obj);
        System.out.println(seller);
    }
}
