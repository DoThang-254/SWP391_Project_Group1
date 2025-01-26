/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import Model.Customer;

/**
 *
 * @author thang
 */
public interface ILoginDAO {
    public Customer Login(String Username , String password) ;
}
