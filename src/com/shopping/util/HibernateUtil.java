package com.shopping.util;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.shopping.entity.CartItem;
import com.shopping.entity.Product;
import com.shopping.entity.User;
import com.shopping.entity.WishListItem;


/**
 * @author vincent
 *
 */
public class HibernateUtil {
  private static StandardServiceRegistry registry;
  private static SessionFactory sessionFactory;
  private static Session session;

  public static SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
      try {
        Configuration configuration = new Configuration();

        Properties settings = new Properties();
        settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        settings.put(Environment.URL, "jdbc:mysql://localhost:3306/shoppingcart");
        settings.put(Environment.USER, "root");
        settings.put(Environment.PASS, "leee5501");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.HBM2DDL_AUTO, "update");
        settings.put(Environment.DIALECT,"org.hibernate.dialect.MySQL55Dialect");


        configuration.setProperties(settings);
        
        

        configuration.addAnnotatedClass(Product.class)
            		 .addAnnotatedClass(CartItem.class)
            		 .addAnnotatedClass(User.class)
            		 .addAnnotatedClass(WishListItem.class);

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
        		.applySettings(configuration.getProperties()).build();
        
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

      } catch (Exception e) {
    	  e.printStackTrace();
//        System.out.println("SessionFactory creation failed");
        if (registry != null) {
          StandardServiceRegistryBuilder.destroy(registry);
        }
      }
    }
    return sessionFactory;
  }

  public static void shutdown() {
    if (registry != null) {
      StandardServiceRegistryBuilder.destroy(registry);
    }
  }
}
