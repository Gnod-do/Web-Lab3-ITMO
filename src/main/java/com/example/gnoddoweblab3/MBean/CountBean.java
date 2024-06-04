package com.example.gnoddoweblab3.MBean;

import com.example.gnoddoweblab3.Check;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.management.*;
import java.lang.management.ManagementFactory;

@ManagedBean(name = "Count", eager = true)
@SessionScoped
public class CountBean extends NotificationBroadcasterSupport implements CountBeanMBean {

    private int allCounter = 0;
    private int missCounter = 0;
    private int sequenceNumber = 0;

    private int hitCounter = 0;

    public CountBean() {
        try {
            ObjectName objectName = new ObjectName("com.example.gnoddoweblab3:name=Count");
            MBeanServer server = ManagementFactory.getPlatformMBeanServer();
            server.registerMBean(this,objectName);
        } catch (MalformedObjectNameException | NotCompliantMBeanException | InstanceAlreadyExistsException |
                 MBeanRegistrationException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Check point){
        allCounter++;

        if (point.isResult()){
            hitCounter++;
        }
        else {
            missCounter++;
        }

        if (Math.abs(point.getX()) > point.getR() || Math.abs(point.getY()) > point.getR()){
            Notification notification = new Notification("Out of range!",this,sequenceNumber++, "The point position is outside the display area");
            sendNotification(notification);
            System.out.println("Out of range");
        }

    }

    @Override
    public int getAllCounter() {
        return allCounter;
    }

    @Override
    public int getMissCounter() {
        return missCounter;
    }

    @Override
    public int getHitCounter() {return hitCounter;}
}
