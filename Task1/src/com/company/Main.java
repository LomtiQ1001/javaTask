package com.company;

import java.util.ArrayList;
import java.util.Collection;
import ru.pflb.mq.dummy.exception.DummyException;
import ru.pflb.mq.dummy.implementation.ConnectionImpl;
import ru.pflb.mq.dummy.interfaces.Connection;
import ru.pflb.mq.dummy.interfaces.Destination;
import ru.pflb.mq.dummy.interfaces.Producer;
import ru.pflb.mq.dummy.interfaces.Session;

public class Main {

    public static void main(String[] args) throws DummyException, InterruptedException {
        Collection<String> list_message = new ArrayList<String>();
        list_message.add("Раз");
        list_message.add("Два");
        list_message.add("Три");

        Connection connection = new ConnectionImpl();
        Session session = connection.createSession(true);//new SessionImpl();
        Destination destination = session.createDestination("Queue");
        Producer producer = session.createProducer(destination);

        //connection.start();

        var iterator = list_message.iterator();
        while (iterator.hasNext()){
            producer.send(iterator.next());
            Thread.sleep(2000);
        }
        session.close();
        connection.close();
    }
}
