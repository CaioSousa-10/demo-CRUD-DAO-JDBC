
package aplication;

import model.entities.Department;


public class DemoDaoJdbc {

   
    public static void main(String[] args) {
        Department obj = new Department(2, "Books");
        System.out.println(obj);
    }
}
