package com.otus.hw_15.listeners;

import com.otus.hw_15.messageSystem.Address;
import com.otus.hw_15.messageSystem.MessageSystem;
import com.otus.hw_15.services.dbService.DBService;
import com.otus.hw_15.entities.dataset.AddressDataSet;
import com.otus.hw_15.entities.dataset.PhoneDataSet;
import com.otus.hw_15.entities.dataset.UserDataSet;
import com.otus.hw_15.services.frontendService.FrontendService;
import com.otus.hw_15.services.messageSystemContextService.MessageSystemContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebListener
public class AppInitializerListener implements ServletContextListener
{
    private MessageSystem messageSystem;
    private MessageSystemContext messageSystemContext;
    private DBService dbService;
    private FrontendService frontendService;

    @Override
    public void contextInitialized(final ServletContextEvent sce)
    {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
        sce.getServletContext().setAttribute("applicationContext", ac);

        this.messageSystem = ac.getBean(MessageSystem.class);
        this.messageSystemContext = ac.getBean(MessageSystemContext.class);

        Address frontAddress = new Address("FRONT");
        messageSystemContext.setFrontAddress(frontAddress);
        Address dbAddress = new Address("DB");
        messageSystemContext.setDbAddress(dbAddress);

        this.frontendService = ac.getBean(FrontendService.class);
        this.dbService = ac.getBean(DBService.class);

        dbService.init();
        frontendService.init();

        List<AddressDataSet> addresses = getAddressList();
        for (int i = 0; i < 11; i++) {
            dbService.save(getUser("user" + i, addresses.get(new Random().nextInt(5))));
        }

        messageSystem.start();
    }

    @Override
    public void contextDestroyed(final ServletContextEvent sce)
    {
    }

    public static UserDataSet getUser(final String name, final AddressDataSet address)
    {
        UserDataSet user = new UserDataSet();
        user.setName(name);
        user.setAge(new Random().nextInt(50) + 15);
        user.addAddress(address);
        user.addPhoneNumber(new PhoneDataSet("00000 555888"));
        user.addPhoneNumber(new PhoneDataSet("22222 444777"));
        return user;
    }

    public static List<AddressDataSet> getAddressList()
    {
        List<AddressDataSet> addresses = new ArrayList<>();
        addresses.add(new AddressDataSet("Brick Lane"));
        addresses.add(new AddressDataSet("Old Str"));
        addresses.add(new AddressDataSet("Liverpool Str"));
        addresses.add(new AddressDataSet("Chelsea"));
        addresses.add(new AddressDataSet("Bristol"));
        return addresses;
    }
}
