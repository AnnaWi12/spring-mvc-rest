package ue04.coronaoverview.springmvcrest.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ue04.coronaoverview.springmvcrest.domain.Entry;
import ue04.coronaoverview.springmvcrest.repositories.EntryRepository;

import java.util.Calendar;
import java.util.Date;

//import sun.tools.jar.CommandLine;
//import java.sql.Timestamp;

@Component
public class BootStrapData implements CommandLineRunner {

    private final EntryRepository entryRepository;

    public BootStrapData(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Loading Corona Entry Data");
        Date now = new Date();

        Calendar calNow = Calendar.getInstance();


        calNow.add(Calendar.MONTH, -1);


        Date dateBeforeAMonth = calNow.getTime();

        Entry e1= new Entry();
        e1.setStatus("i");
        e1.setDate(new Date());
        e1.setDistrict("Linz");
        entryRepository.save(e1);

        Entry e2= new Entry();
        e2.setStatus("q");
        e2.setDate(new Date());
        e2.setDistrict("Perg");
        entryRepository.save(e2);


        Entry e3= new Entry();
        e3.setStatus("i");
        e3.setDate(new Date());
        e3.setDistrict("Freistadt");
        entryRepository.save(e3);

        Entry e4= new Entry();
        e4.setStatus("i");
        e4.setDate(new Date());
        e4.setDistrict("Linz");
        entryRepository.save(e4);

        Entry e5= new Entry();
        e5.setStatus("q");
        e5.setDate(new Date());
        e5.setDistrict("Perg");
        entryRepository.save(e5);


        Entry e6= new Entry();
        e6.setStatus("i");
        e6.setDate(new Date());
        e6.setDistrict("Freistadt");
        entryRepository.save(e6);

        Entry e7= new Entry();
        e7.setStatus("i");
        e7.setDate(new Date());
        e7.setDistrict("Linz");
        entryRepository.save(e7);

        Entry e8= new Entry();
        e8.setStatus("q");
        e8.setDate(new Date());
        e8.setDistrict("Perg");
        entryRepository.save(e8);


        Entry e9= new Entry();
        e9.setStatus("i");
        e9.setDate(new Date());
        e9.setDistrict("Freistadt");
        entryRepository.save(e9);

        Entry e10= new Entry();
        e10.setStatus("i");
        e10.setDate(new Date());
        e10.setDistrict("Linz");
        entryRepository.save(e10);

        Entry e11= new Entry();
        e11.setStatus("t");
        e11.setDate(new Date());
        e11.setDistrict("Perg");
        entryRepository.save(e11);


        Entry e13= new Entry();
        e13.setStatus("i");
        e13.setDate(dateBeforeAMonth);
        e13.setDistrict("Freistadt");
        entryRepository.save(e13);

        Entry e12= new Entry();
        e12.setStatus("i");
        e12.setDate(new Date(now.getTime()-3600000*24*7)); // 1 Woche vor jetzt
        e12.setDistrict("Linz-Land");
        entryRepository.save(e12);

        Entry e14= new Entry();
        e14.setStatus("i");
        e14.setDate(new Date(now.getTime()-3600000*24)); // 1 Tag vor jetzt
        e14.setDistrict("Grieskirchen");
        entryRepository.save(e14);


        Entry e15= new Entry();
        e15.setStatus("t");
        e15.setDate(new Date(now.getTime()-3600000)); // 1 h vor jetzt
        e15.setDistrict("Freistadt");
        entryRepository.save(e15);

        System.out.println("Entries: " + entryRepository.count());
    }
}
