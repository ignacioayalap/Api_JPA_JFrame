/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;



import java.util.List;


public interface IGenericDAO<T, ID> {
    
   
    void save(T entity);
    
   
    T findById(ID id);
    
    
    List<T> findAll();
    
    
    void update(T entity);
    
    void delete(T entity);
    
   
    void deleteById(ID id);
    
    
    long count();
}
