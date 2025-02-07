/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import Model.Product;
import java.util.List;

/**
 *
 * @author thang
 */
public interface ICustomerDAO {

//    public List<Product> PagingProductByCustomerId(int index, int CustomerId);

    public int GetTotalProductByProductId(int CustomerId, Product p);

  //  public List<Product> SearchingProductByProductId(int index, int CustomerId, Product p , String sort , String order);
//    public List<Product> SearchingProductByProductId(int index, int CustomerId, Product p);

//    public int GetTotalProductByBrand(int CustomerId, String ProductBrand);
//
//    public List<Product> FilterProductByProductBrand(int index, int CustomerId, String ProductBrand);

}
