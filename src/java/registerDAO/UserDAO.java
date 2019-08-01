/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registerDAO;

import entity.User;
import java.util.List;  
import java.util.ArrayList;  
import org.hibernate.Query;  
import org.hibernate.Session;  
  
  
import javax.faces.application.FacesMessage;  
import javax.servlet.http.HttpSession;
import org.hibernate.SessionFactory;
import org.primefaces.context.RequestContext;
import util.HibernateUtil;
import util.SessionUtil;
/**
 *
 * @author rebeka
 */
public class UserDAO  { 
    
     public boolean login(User use){
        try{
            SessionFactory sf =HibernateUtil.getSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            
            Query query= session.createQuery("select u from User u where u.name=:uname and u.recordNo=:record" );
             query.setString("uname", use.getName());
            query.setString("record", use.getRecordNo());
          
            List<User>list =query.list();
            list.toString();
            
            session.getTransaction().commit();
            session.close();
            
            
            if(list.size()>0){
                HttpSession hts=SessionUtil.getSession();
                hts.setAttribute("uname", list.get(0).getName());
                hts.setAttribute("id", list.get(0).getId());
                return true;
            }
            else{
                return false;
            }
            
        } catch (Exception e) {
            
             return false;
        }
    }
    
   public boolean register(User u){
        try {
            SessionFactory sf=HibernateUtil.getSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
    
            session.save(u);
  
            session.getTransaction().commit();
            session.close();
    
            return true;
    
    
        } catch (Exception e) {
            return false;
       }
        
        
        
    
    
    }  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private User user;  
    private User newuser;  
    private List < User > DaoAllUsers;  
    private List < User > DaoSearchUserList;  
    //Session session;  
    public List < User > AllUsers()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            DaoAllUsers = session.createCriteria(User.class).list();  
            int count = DaoAllUsers.size();  
            // FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "List Size", Integer.toString(count));//Debugging Purpose  
            //RequestContext.getCurrentInstance().showMessageInDialog(message1);  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
        return DaoAllUsers;  
    }  
    public Integer getId()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        String hql = "select max(U.id) from User U";  
        Query query = session.createQuery(hql);  
        List < Integer > results = query.list();  
        Integer userId = 1;  
        if (results.get(0) != null)  
        {  
            userId = results.get(0) + 1;  
        }  
        session.flush();  
        session.close();  
        return userId;  
    }  
    public List < User > SearchByRecordNo(String RecordNo)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        List < User > daoSearchList = new ArrayList < > ();  
        try  
        {  
            session.beginTransaction();  
            Query qu = session.createQuery("From User U where U.recordNo =:recordNo"); //User is the entity not the table  
            qu.setParameter("recordNo", RecordNo);  
            daoSearchList = qu.list();  
            int count = daoSearchList.size();  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        finally  
        {  
            session.close();  
        }  
        return daoSearchList;  
    }  
    public void add(User newuser)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String Id = Integer.toString(newuser.getId());  
            // begin a transaction  
            session.beginTransaction();  
            session.merge(newuser);  
            session.flush();  
            System.out.println("NewUser saved, id: " +  
                newuser.getId());  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void delete(User user)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String name = user.getName();  
            session.beginTransaction();  
            session.delete(user);  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void update(User user)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            session.update(user);  
            session.flush();  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
}  
