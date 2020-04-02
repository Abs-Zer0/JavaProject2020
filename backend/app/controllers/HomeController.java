package controllers;

import models.JavaApplicationDatabase;
import play.mvc.*;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    private JavaApplicationDatabase jadb;

    @Inject
    public HomeController(JavaApplicationDatabase jadb) {
        this.jadb = jadb;
    }

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        /*try{
            String url = "jdbc:h2:tcp://localhost/~/test";
            String username = "admin";
            String password = "admin";
            Class.forName("org.h2.Driver").getDeclaredConstructor().newInstance();
            String sql = "create table \"test\"(id int)";
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                Statement statement = conn.createStatement();
                statement.executeUpdate(sql);
                System.out.println("Connection to Store DB succesfull!");
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex.getMessage());
        }*/
        jadb.updateSomething();
        return ok(views.html.index.render());
    }

}
