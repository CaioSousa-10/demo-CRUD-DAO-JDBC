
package aplication;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;


public class DemoDaoJdbc {

        
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        
        SellerDao sellerdao = DaoFactory.createSellerDao();
        System.out.println("->>>> teste 1: seller findById <<<<<<");
        Seller seller = sellerdao.findById(3);
        System.out.println(seller);
        
        System.out.println("->>>> teste 2: seller findByDepartment <<<<<<");
        
        List<Seller> list = sellerdao.findByDepartment(new Department(2, "Electronics"));
        for (Seller seller1 : list) {
            System.out.println(seller1);
        }
        
        System.out.println(">>>> teste 3: seller findAll <<<<<");
        list = sellerdao.findAll();
        for (Seller seller2 : list) {
            System.out.println(seller2);
        }
        
        System.out.println("");
        System.out.println(">>>> teste 4: seller insert <<<<");
        Seller sellerinsert = new Seller(null, "Rodrigo", "rodrigo@gmail.com", new Date(), 4000.00, new Department(2, "Serviço Social"));
        sellerdao.insert(sellerinsert);
        System.out.println("Inserted! New Id = " + sellerinsert.getId());
        
        System.out.println("");
        System.out.println(">>>> teste 5: seller update <<<<");
        seller = sellerdao.findById(1);
        seller.setName("Keila Gusmao");
        sellerdao.update(seller);
        System.out.println("Update completed!!!!");
        
        System.out.println("");
        System.out.println(">>>> teste 6: seller delete <<<<");
        System.out.print("Enter Id for delete test: ");
        int id = sc.nextInt();
        sellerdao.deleteById(id);
        System.out.println("delete completed!");
        
        sc.close();
        
    }
}
