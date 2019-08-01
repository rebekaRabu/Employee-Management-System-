/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

import entity.User;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import registerDAO.UserDAO;

/**
 *
 * @author rebeka
 */
@ManagedBean(name = "userRegisterBean")  
@ViewScoped  
public class UserRegisterBean {
    
    User use =new User();

    public User getUse() {
        return use;
    }

    public void setUse(User use) {
        this.use = use;
    }
    
    
    
    
    
    public String register(){
        use.setName(use.getName());
        use.setRecordNo(use.getRecordNo());
        use.setAge(use.getAge());
        
        if(new UserDAO().register(use)){
            
             System.out.println("Registration success");
           
             RequestContext.getCurrentInstance().update("growl");
             FacesContext context=FacesContext.getCurrentInstance();
            
           
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR , "Registration Successful!!!!  thanks for registration." , ""));
    
            return null;
       
        
        
        }else{
            System.out.println("Registration Failed");
            
            
            RequestContext.getCurrentInstance().update("growl");
             FacesContext context=FacesContext.getCurrentInstance();
            
           
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR , "Registration Successful" , ""));}
       
         return "index.xhtml?faces-redirect=true";
    
    
    }
}
